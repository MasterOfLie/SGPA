package cloud.masteroflie.sgpa.api;

import cloud.masteroflie.sgpa.dto.SetupDTO;
import cloud.masteroflie.sgpa.service.UsuarioService;
import cloud.masteroflie.sgpa.service.impl.SettingsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static cloud.masteroflie.sgpa.controllers.AuthController.isFirstAccess;

@RestController
@RequestMapping("/api/setup")
public class SetupAPI {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SettingsServiceImpl settingsServiceImpl;

    @PostMapping("/")
    public ResponseEntity<Map<String, String>> setupAPP(@RequestBody SetupDTO setupDTO) {
        System.out.println(setupDTO);
        Map<String, String> msg = new HashMap<>();
        try {
            if (!isFirstAccess) {
                msg.put("Error", "ACESSO NEGADO");
                System.out.println("isFirstAccess");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(msg);
            }
            if (usuarioService.countUsuarios() != 0) {
                msg.put("Error", "ACESSO NEGADO");
                System.out.println("countUsuarios");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(msg);
            }
            msg.put("OK", settingsServiceImpl.SetupApp(setupDTO));
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            msg.put("Error", e.getMessage());
            System.out.println("Exception");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg); // Status 500
        }
    }
}
