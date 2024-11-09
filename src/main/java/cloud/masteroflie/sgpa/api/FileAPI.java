package cloud.masteroflie.sgpa.api;


import cloud.masteroflie.sgpa.dto.MovimentacaoDTO;
import cloud.masteroflie.sgpa.dto.ProcessoDTO;
import cloud.masteroflie.sgpa.models.Arquivos;
import cloud.masteroflie.sgpa.service.ArquivosService;
import cloud.masteroflie.sgpa.service.AzureService;
import com.azure.core.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/files/")
public class FileAPI {

    @Autowired
    private AzureService azureService;

    @Autowired
    private ArquivosService arquivosService;

    @PostMapping("upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file")  MultipartFile file, @RequestHeader("idProcesso") UUID idProcesso, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try {
            Arquivos arquivos = azureService.upload(file, idProcesso, authentication);
            LocalDateTime dataCriacao = arquivos.getDataCriacao();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String formattedDate = dataCriacao.format(formatter);
            msg.put("id", arquivos.getId().toString());
            msg.put("Nome", arquivos.getNomeArquivo());
            msg.put("Usuario", arquivos.getUsuario().getName());
            msg.put("Data", formattedDate);
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @PutMapping("externo")
    public ResponseEntity<Map<String, String>> acessoExterno(@RequestBody Arquivos arquivo, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try {
            msg.put("OK", arquivosService.acessoExterno(arquivo, authentication));
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @PutMapping("download")
    public ResponseEntity<Map<String, String>> downloadArquivo(@RequestBody Arquivos arquivo) {
        Map<String, String> msg = new HashMap<>();
        try {
            msg.put("OK", arquivosService.downloadArquivo(arquivo));
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @PutMapping("delete")
    public ResponseEntity<Map<String, String>> deleteFile(@RequestBody Arquivos arquivo) {
        Map<String, String> msg = new HashMap<>();
        try {
            msg.put("OK", arquivosService.deleteArquivo(arquivo));
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }

}
