package cloud.masteroflie.sgpa.api;

import cloud.masteroflie.sgpa.dto.PermissionDTO;
import cloud.masteroflie.sgpa.models.Perfil;
import cloud.masteroflie.sgpa.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class PerfilAPI {
    @Autowired
    private PerfilService perfilService;

    @PostMapping("perfil/criar")
    public ResponseEntity<Map<String, String>> criarPerfil(@RequestBody Perfil perfil) {
        Map<String, String> msg = new HashMap<>();
        try{
            Perfil perfilCriado = perfilService.criarPerfil(perfil);
            msg.put("OK", "Perfil Criado com Sucesso!");
            msg.put("id", perfilCriado.getId().toString());
            return ResponseEntity.ok(msg);
        }catch (Exception e){
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @PutMapping("perfil/status")
    public ResponseEntity<Map<String, String>> statusPerfil(@RequestBody Perfil perfil) {
        Map<String, String> msg = new HashMap<>();
        try{
            msg.put("OK", perfilService.statusPerfil(perfil.getId()));
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @DeleteMapping("perfil/delete")
    public ResponseEntity<Map<String, String>> deletarPerfil(@RequestBody Perfil perfilID) {
        Map<String, String> msg = new HashMap<>();
        try{
            perfilService.deletarPerfil(perfilID.getId());
            msg.put("OK", "Perfil Deletado com Sucesso!");
            return ResponseEntity.ok(msg);
        }catch (Exception e){
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @PutMapping("/perfil/add")
    public ResponseEntity<Map<String, String>> addPermission(@RequestBody PermissionDTO permissionDTO) {
        Map<String, String> msg = new HashMap<>();
        try{
            perfilService.addPermission(permissionDTO);
            msg.put("OK", "Permissão Adicionada com Sucesso!");
            return ResponseEntity.ok(msg);
        }catch (Exception e){
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @PutMapping("/perfil/remove")
    public ResponseEntity<Map<String, String>> removePermission(@RequestBody PermissionDTO permissionDTO) {
        Map<String, String> msg = new HashMap<>();
        try{
            perfilService.removePermission(permissionDTO);
            msg.put("OK", "Permissão Removida com Sucesso!");
            return ResponseEntity.ok(msg);
        }catch (Exception e){
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @PutMapping("/perfil/editar")
    public ResponseEntity<Map<String, String>> editarPerfil(@RequestBody Perfil perfil, Authentication auth) {
        Map<String, String> msg = new HashMap<>();
        try{
            perfilService.editPerfil(perfil, auth);
            msg.put("OK", "Perfil atualizado com Sucesso!");
            return ResponseEntity.ok(msg);
        }catch (Exception e){
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
}
