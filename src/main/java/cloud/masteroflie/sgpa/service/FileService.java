package cloud.masteroflie.sgpa.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


public interface FileService {

    String salvarArquivos (MultipartFile[] files, Long protocoloID);

    File[] arquivos (Long protocoloID);
}
