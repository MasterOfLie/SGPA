package cloud.masteroflie.sgpa.api;

import cloud.masteroflie.sgpa.dto.PasswordDTO;
import cloud.masteroflie.sgpa.models.Usuario;
import cloud.masteroflie.sgpa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/solicitante")
public class SolicitanteAPI {

    @Autowired
    private UsuarioService usuarioService;

    @PutMapping("/senha")
    public ResponseEntity<Map<String, String>> senha(@RequestBody PasswordDTO passwordDTO) {
        Map<String, String> msg = new HashMap<>();
        try{
            usuarioService.alterarSenha(passwordDTO.getNewPassword(), passwordDTO.getIdUsuario());
            msg.put("OK", "Senha alterada com sucesso!");
            return ResponseEntity.ok(msg);
        }catch (Exception e){
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }
    @PutMapping("/dados")
    public ResponseEntity<Map<String, String>> atualizarSolicitante(@RequestBody Usuario usuario, Authentication authentication) {
        System.out.println(usuario.getCpfCnpj() + "      " +  usuario.getEmail());
        Map<String, String> msg = new HashMap<>();
        try{
            usuarioService.atualizarSolicitante(usuario, authentication);
            msg.put("OK", "Solicitante alterada com sucesso!");
            return ResponseEntity.ok(msg);
        }catch (Exception e){
            msg.put("Error", e.getMessage());
            return ResponseEntity.ok(msg);
        }
    }

}
