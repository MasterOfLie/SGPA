package cloud.masteroflie.sgpa.api;

import cloud.masteroflie.sgpa.dto.DepartamentoDTO;
import cloud.masteroflie.sgpa.models.Departamento;
import cloud.masteroflie.sgpa.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoAPI {

    @Autowired
    private DepartamentoService departamentoService;

    @PostMapping("criar")
    public ResponseEntity<Map<String, String>> criarDepartamento(@RequestBody Departamento departamento, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try {
            Departamento departamentoCriado = departamentoService.criarDepartamento(departamento, authentication);
            msg.put("OK", "Departamento Criado com Sucesso!");
            msg.put("id", departamentoCriado.getId().toString());
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @PutMapping("add")
    public ResponseEntity<Map<String, String>> addDepartamento(@RequestBody DepartamentoDTO departamento, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try {
            departamentoService.addDepartamento(departamento, authentication);
            msg.put("OK", "Departamento atualizado com Sucesso!");
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }

    @PutMapping("status")
    public ResponseEntity<Map<String, String>> stautsDepartamento(@RequestBody DepartamentoDTO departamentoID, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try {
            msg.put("OK", departamentoService.statusDepartamento(departamentoID, authentication));
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }

    @PutMapping("editar")
    public ResponseEntity<Map<String, String>> editarDepartamento(@RequestBody Departamento departamento, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try {
            msg.put("OK", departamentoService.editarDepartamento(departamento, authentication));
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<Map<String, String>> deteleDepartamento(@RequestBody DepartamentoDTO departamentoID, Authentication authentication) {
        Map<String, String> msg = new HashMap<>();
        try {
            msg.put("OK", departamentoService.deleteDepartamento(departamentoID, authentication));
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }

}
