package cloud.masteroflie.sgpa.api;

import cloud.masteroflie.sgpa.dto.ServicoDTO;
import cloud.masteroflie.sgpa.models.Servico;
import cloud.masteroflie.sgpa.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/servicos/")
public class ServicosAPI {

    @Autowired
    private ServicoService servicoService;

    @PostMapping("criar")
    public ResponseEntity<Map<String, String>> criarSetor(@RequestBody ServicoDTO servicoDTO, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try {
            Servico servicoCriado = servicoService.criarServico(servicoDTO, authentication);
            msg.put("OK", "Servico Criado com Sucesso!");
            msg.put("id", servicoCriado.getId().toString());
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @DeleteMapping("delete")
    public ResponseEntity<Map<String, String>> delete(@RequestBody ServicoDTO servicoDTO, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try {
            servicoService.deleteServico(servicoDTO.getId(), authentication);
            msg.put("OK", "Servico deletado com Sucesso!");
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @PutMapping("status")
    public ResponseEntity<Map<String, String>> status(@RequestBody ServicoDTO servicoDTO, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try {
            msg.put("OK", servicoService.statusServico(servicoDTO.getId(), authentication));
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @PutMapping("editar")
    public ResponseEntity<Map<String, String>> editarServico(@RequestBody ServicoDTO servicoDTO, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try {
            servicoService.atualizarServico(servicoDTO, authentication);
            msg.put("OK", "Servico Atualizado com Sucesso!");
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }

}
