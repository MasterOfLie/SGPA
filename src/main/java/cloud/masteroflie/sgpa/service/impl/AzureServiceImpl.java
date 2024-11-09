package cloud.masteroflie.sgpa.service.impl;
import cloud.masteroflie.sgpa.models.Arquivos;
import cloud.masteroflie.sgpa.models.Processo;
import cloud.masteroflie.sgpa.models.Usuario;
import cloud.masteroflie.sgpa.repository.ArquivoRepository;
import cloud.masteroflie.sgpa.repository.ProcessoRepository;
import cloud.masteroflie.sgpa.repository.UsuarioRepository;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import cloud.masteroflie.sgpa.service.AzureService;
import com.azure.storage.blob.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class AzureServiceImpl implements AzureService {
    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Value("${azure.storage.connection-string}")
    private String connectionString;

    @Value("${azure.storage.container-name}")
    private String containerName;


    @Override
    public Arquivos upload(MultipartFile file, UUID idProcesso, Authentication authentication) throws Exception {
        Processo processo = processoRepository.findByUuid(idProcesso);
        Arquivos arquivo = new Arquivos();
        Usuario usuario = (Usuario) authentication.getPrincipal();
        if (file.isEmpty()) {
            throw new Exception("File is empty");
        }
        String originalFilename = file.getOriginalFilename();
        String newName = processo.getId() + "_" + UUID.randomUUID().toString() + "_" + originalFilename;

        arquivo.setNomeArquivo(originalFilename);
        arquivo.setBlobName(newName);
        arquivo.setProcesso(processo);
        arquivo.setUsuario(usuario);
        arquivoRepository.save(arquivo);

        // Cria o BlobServiceClient usando a connection string
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        // Cria o container se não existir
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        if (!containerClient.exists()) {
            containerClient.create();
        }

        // Cria o BlobClient
        BlobClient blobClient = containerClient.getBlobClient(newName);

        BlobHttpHeaders headers = new BlobHttpHeaders().setContentType(file.getContentType());
        // Faz o upload do arquivo
        blobClient.upload(file.getInputStream(), file.getSize(), true);
        blobClient.setHttpHeaders(headers);
        return arquivo;
    }

    @Override
    public String viewFile(String filename) throws Exception {
        // Cria o BlobServiceClient
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        // Cria o BlobClient
        BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(filename);

        // Gerar o SAS token para o blob
        BlobServiceSasSignatureValues values = new BlobServiceSasSignatureValues(
                OffsetDateTime.now().plusHours(1), // Expira em 1 hora
                new BlobSasPermission().setReadPermission(true) // Permissão de leitura
        );

        String sasToken = blobClient.generateSas(values);
        return blobClient.getBlobUrl() + "?" + sasToken;
    }
    public String deleteFile(String filename) throws Exception {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
        BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(filename);
        if (blobClient.exists()) {
            blobClient.delete();
            return "Arquivo deletado com sucesso";
        }else {
            return "Arquivo deletado com sucesso";
        }
    }
}
