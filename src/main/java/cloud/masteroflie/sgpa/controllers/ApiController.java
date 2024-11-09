package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.dto.PermissionDTO;
import cloud.masteroflie.sgpa.dto.UsuarioDTO;
import cloud.masteroflie.sgpa.models.Perfil;
import cloud.masteroflie.sgpa.models.Usuario;
import cloud.masteroflie.sgpa.service.PerfilService;
import cloud.masteroflie.sgpa.service.UsuarioService;
import jakarta.persistence.PostRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/")
public class ApiController {
    @Autowired
    private PerfilService perfilService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("auth/register")
    public ResponseEntity<Map<String, String>> criarConta(@RequestBody Usuario Usuario) {
        Map<String, String> msg = new HashMap<>();
        try{
            usuarioService.cadastrarUsuario(Usuario);
            msg.put("OK", "Cadastro realizado com sucesso!");
            return ResponseEntity.ok(msg);
        }catch (Exception e){
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }


//    ------------------------- USUARIO -------------------------
    @PutMapping("/usuario/add")
    public ResponseEntity<Map<String, String>> addPerfil(@RequestBody UsuarioDTO usuarioDTO) {
        Map<String, String> msg = new HashMap<>();
        try{
            usuarioService.addPerfil(usuarioDTO);
            msg.put("OK", "Perfil Adicionado com Sucesso!");
            return ResponseEntity.ok(msg);
        }catch (Exception e){
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @PutMapping("/usuario/remove")
    public ResponseEntity<Map<String, String>> removePerfil(@RequestBody UsuarioDTO usuarioDTO) {
        System.out.println(usuarioDTO.getIdUsuario()+ " "+  usuarioDTO.getPerfil());
        Map<String, String> msg = new HashMap<>();
        try{
            usuarioService.removerPerfil(usuarioDTO);
            msg.put("OK", "Perfil Removido com Sucesso!");
            return ResponseEntity.ok(msg);
        }catch (Exception e){
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }

}
