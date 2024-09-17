package cloud.masteroflie.sgpa.service.impl;

import cloud.masteroflie.sgpa.service.FileService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    private static final String UPLOAD_DIR = "C:/uploads/";
    @Override
    public String salvarArquivos(MultipartFile[] files, Long protocoloID) {
        StringBuilder result = new StringBuilder();

        // Define o caminho do diretório
        File directory = new File(UPLOAD_DIR + protocoloID);

        // Verifica se o diretório existe e cria se não existir
        if (!directory.exists()) {
            boolean created = directory.mkdirs(); // Cria diretórios, se necessário
            if (!created) {
                result.append("Failed to create directory: ").append(directory.getAbsolutePath()).append("\n");
                return "redirect:/processos"; // Retorna se a criação do diretório falhar
            }
        }

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                result.append("Failed to upload file: ").append(file.getOriginalFilename()).append("\n");
                continue;
            }

            try {
                // Salva o arquivo
                byte[] bytes = file.getBytes();
                Path path = Paths.get(directory.getAbsolutePath(), file.getOriginalFilename());
                Files.write(path, bytes);

                result.append("File uploaded successfully: ").append(file.getOriginalFilename()).append("\n");

            } catch (IOException e) {
                result.append("Failed to upload file: ").append(file.getOriginalFilename()).append("\n");
                e.printStackTrace(); // Adiciona um print stack trace para depuração
            }
        }
        return null;
    }

    @Override
    public File[] arquivos(Long protocoloID) {
        File directory = new File(UPLOAD_DIR + protocoloID);
        File[] files = directory.listFiles();

        if (files != null) {
            return files; // Retorna a lista de arquivos
        } else {
            return new File[0]; // Retorna uma lista vazia se o diretório não existir ou estiver vazio
        }
    }
}
