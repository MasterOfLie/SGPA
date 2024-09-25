package cloud.masteroflie.sgpa.service.impl;

import cloud.masteroflie.sgpa.models.Arquivos;
import cloud.masteroflie.sgpa.repository.AplicacaoRepository;
import cloud.masteroflie.sgpa.repository.ArquivoRepository;
import cloud.masteroflie.sgpa.repository.ProcessoRepository;
import cloud.masteroflie.sgpa.service.AplicacaoService;
import cloud.masteroflie.sgpa.service.BlobService;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;

@Service
public class BlobServiceImpl implements BlobService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private AplicacaoRepository aplicacaoRepository;

    @Value("${blob.container.clientet}")
    private String blobContainerClient;
    @Value("${blob.connect.clientet}")
    private String connectString;

    private BlobServiceClient blobServiceClient;
    private BlobContainerClient containerClient;

    @PostConstruct
    public void init() {
        blobServiceClient = new BlobServiceClientBuilder().connectionString(connectString).buildClient();
        containerClient = blobServiceClient.getBlobContainerClient(blobContainerClient);
    }
    public BlobContainerClient getContainerClient() {
        return this.containerClient = containerClient;
    }

    @Override
    public String salvarArquivos(MultipartFile[] files, Long protocoloID) {

        if (!containerClient.exists()) {
            containerClient.create();
            System.out.println("Container 'developer' criado.");
        }

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            try {

                String originalFileName = file.getOriginalFilename();
                String newFileName = generateNewFileName(originalFileName, protocoloID);

                BlobClient blobClient = containerClient.getBlobClient(newFileName);

                BlobHttpHeaders headers = new BlobHttpHeaders().setContentType(file.getContentType());
                blobClient.upload(file.getInputStream(), file.getSize(), true);
                blobClient.setHttpHeaders(headers);

                Arquivos arquivos = new Arquivos(processoRepository.findById(protocoloID).get(), newFileName, newFileName);
                arquivoRepository.save(arquivos);

            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    public String urlArquivos(String blobName) {
        BlobClient blobClient = containerClient.getBlobClient(blobName);

        BlobServiceSasSignatureValues values = new BlobServiceSasSignatureValues(
                OffsetDateTime.now().plusHours(1),
                BlobSasPermission.parse("r")
        );

        String sasToken = blobClient.generateSas(values);
        return blobClient.getBlobUrl() + "?" + sasToken;
    }

    @Override
    public Void removerArquivo(String blobName) {
            BlobClient blobClient = containerClient.getBlobClient(blobName);
            blobClient.delete();
            return null;
    }

    private String generateNewFileName(String originalFileName, Long protocoloID) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return protocoloID + "-" + timestamp + "_" + originalFileName;
    }

}
