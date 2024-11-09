package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.config.BaseController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {
    @GetMapping
    public String Home(Authentication auth, Model model) {
        System.out.println(auth.getAuthorities());
        if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DEFAULT"))){
            return "redirect:/portal";
        }
        usuarioLogado(model);
        return "redirect:/processos";
    }
}
