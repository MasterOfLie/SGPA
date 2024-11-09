package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.models.Usuario;
import cloud.masteroflie.sgpa.repository.UsuarioRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/")
public class AuthController {

//    @Autowired
//    private UsuarioRepository usuarioRepository;
// // criar usuario (apagar)
//    private final PasswordEncoder passwordEncoder;
//
//    public AuthController(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    @GetMapping("login")
    public String login() {
        return "auth/login";
    }
    @GetMapping("register")
    public String register () {
        return "auth/register";
    }
}
