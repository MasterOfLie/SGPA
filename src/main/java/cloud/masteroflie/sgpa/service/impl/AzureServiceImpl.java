package cloud.masteroflie.sgpa.service.impl;
import cloud.masteroflie.sgpa.config.AzureBlobConfig;
import cloud.masteroflie.sgpa.models.Arquivos;
import cloud.masteroflie.sgpa.models.Processo;
import cloud.masteroflie.sgpa.models.Usuario;
import cloud.masteroflie.sgpa.repository.ArquivoRepository;
import cloud.masteroflie.sgpa.repository.ProcessoRepository;
import cloud.masteroflie.sgpa.repository.UsuarioRepository;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AzureServiceImpl implements AzureService {
    private final ProcessoRepository processoRepository;
    private final ArquivoRepository arquivoRepository;
    private final UsuarioRepository usuarioRepository;
    private final AzureBlobConfig azureBlobConfig;

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
        BlobClient blobClient = azureBlobConfig.blobClient(newName);
        BlobHttpHeaders headers = new BlobHttpHeaders().setContentType(file.getContentType());
        blobClient.upload(file.getInputStream(), file.getSize(), true);
        blobClient.setHttpHeaders(headers);
        return arquivo;
    }

    @Override
    public String viewFile(String filename) throws Exception {
        BlobClient blobClient = azureBlobConfig.blobClient(filename);
        BlobServiceSasSignatureValues values = new BlobServiceSasSignatureValues(
                OffsetDateTime.now().plusHours(1),
                new BlobSasPermission().setReadPermission(true)
        );

        String sasToken = blobClient.generateSas(values);
        return blobClient.getBlobUrl() + "?" + sasToken;
    }
    public String deleteFile(String filename) throws Exception {
        BlobClient blobClient = azureBlobConfig.blobClient(filename);
        if (blobClient.exists()) {
            blobClient.delete();
            return "Arquivo deletado com sucesso";
        }else {
            return "Arquivo não encontrado.";
        }
    }
}
