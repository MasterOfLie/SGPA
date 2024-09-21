package cloud.masteroflie.sgpa.service;

import org.springframework.web.multipart.MultipartFile;


public interface BlobService {

    String salvarArquivos (MultipartFile[] files, Long protocoloID);

    String urlArquivos (String blobName);

}
