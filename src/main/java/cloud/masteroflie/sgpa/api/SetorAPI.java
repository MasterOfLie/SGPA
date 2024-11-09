package cloud.masteroflie.sgpa.api;

import cloud.masteroflie.sgpa.models.Perfil;
import cloud.masteroflie.sgpa.models.Setor;
import cloud.masteroflie.sgpa.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/setores/")
public class SetorAPI {

    @Autowired
    private SetorService setorService;

    @PostMapping("criar")
    public ResponseEntity<Map<String, String>> criarSetor(@RequestBody Setor setor, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try {
            Setor setorCriado = setorService.criarSetor(setor, authentication);
            msg.put("OK", "Setor Criado com Sucesso!");
            msg.put("id", setorCriado.getId().toString());
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @PutMapping("status")
    public ResponseEntity<Map<String, String>> statusSetor(@RequestBody Setor setor, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try{
            msg.put("OK", setorService.statusSetor(setor.getId(), authentication));
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @PutMapping("editar")
    public ResponseEntity<Map<String, String>> editarSetor(@RequestBody Setor setor, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try{
            setorService.editarSetor(setor, authentication);
            msg.put("OK", "Setor Editado com Sucesso!");
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
}
