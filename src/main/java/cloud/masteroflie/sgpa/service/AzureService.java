package cloud.masteroflie.sgpa.service;

import cloud.masteroflie.sgpa.models.Arquivos;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public interface AzureService {

    Arquivos upload(MultipartFile file, UUID idProcesso, Authentication authentication) throws Exception;
    String viewFile(String filename) throws Exception;
    String deleteFile(String filename) throws Exception;
}
