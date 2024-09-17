package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.models.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

public abstract class BaseController {
    protected void addRoleAttributes(Model model) {
        Usuario nome_usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Nome: " + nome_usuario.getNome());
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            model.addAttribute("isAdmin", true);
            model.addAttribute("isStaff", true);
            model.addAttribute("isUsuario", true);
        }
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STAFF"))) {
            model.addAttribute("isStaff", true);
        }
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USUARIO"))) {
            model.addAttribute("isUsuario", true);
        }
//        boolean isAdmin = authentication.getAuthorities().stream()
//                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
//        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("user", nome_usuario);
    }
}
