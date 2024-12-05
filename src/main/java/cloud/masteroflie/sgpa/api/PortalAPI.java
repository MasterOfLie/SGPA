package cloud.masteroflie.sgpa.api;
import cloud.masteroflie.sgpa.dto.ProcessoPortalDTO;
import cloud.masteroflie.sgpa.models.Processo;
import cloud.masteroflie.sgpa.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/portal")
public class PortalAPI {

    @Autowired
    private ProcessoService processoService;

    @PostMapping("/processo")
    public ResponseEntity<Map<String, String>> criarProcesso(@RequestBody ProcessoPortalDTO processoPortalDTO, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try {
            Processo processoCriado = processoService.criarProcessoPortal(processoPortalDTO, authentication);
            msg.put("OK", "Processo Criado com Sucesso!");
            msg.put("id", processoCriado.getUuid().toString());
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }

}
