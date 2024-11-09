package cloud.masteroflie.sgpa.api;

import cloud.masteroflie.sgpa.dto.MovimentacaoDTO;
import cloud.masteroflie.sgpa.dto.ProcessoDTO;
import cloud.masteroflie.sgpa.models.Processo;
import cloud.masteroflie.sgpa.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/processos/")
public class ProcessoAPI {

    @Autowired
    private ProcessoService processoService;

    @PostMapping("criar")
    public ResponseEntity<Map<String, String>> criarProcesso(@RequestBody ProcessoDTO processoDTO, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try {
            Processo processoCriado = processoService.criarProcesso(processoDTO, authentication);
            msg.put("OK", "Processo Criado com Sucesso!");
            msg.put("id", processoCriado.getUuid().toString());
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @PutMapping("movimentar")
    public ResponseEntity<Map<String, String>> movimentarProcesso(@RequestBody MovimentacaoDTO movimentacaoDTO, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try {
            processoService.movimentarProcesso(movimentacaoDTO, authentication);
            msg.put("OK", "Processo movimentado com Sucesso!");
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @PutMapping("status")
    public ResponseEntity<Map<String, String>> alterarStatus(@RequestBody ProcessoDTO processoDTO, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try {
            msg.put("OK", processoService.alterarStatus(processoDTO, authentication));
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
}
