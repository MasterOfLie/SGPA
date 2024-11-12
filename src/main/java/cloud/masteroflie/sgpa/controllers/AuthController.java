package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    public static boolean isFirstAccess = true;
    @GetMapping("login")
    public String login() {
        if (isFirstAccess) {
            if (usuarioService.countUsuarios() == 0) {
                return "redirect:setup";
            }else{
                isFirstAccess = false;
            }
        }
        return "auth/login";
    }
    @GetMapping("register")
    public String register () {
        return "auth/register";
    }

    @GetMapping("setup")
    public String setup() {
        if (!isFirstAccess) {
            return "redirect:login";
        }else{
            if (usuarioService.countUsuarios() == 0) {
                return "auth/setup";
            }else{
                isFirstAccess = false;
                return "redirect:login";
            }
        }
    }
}
