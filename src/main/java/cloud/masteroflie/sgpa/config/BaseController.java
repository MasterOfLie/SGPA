package cloud.masteroflie.sgpa.config;

import cloud.masteroflie.sgpa.models.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

public abstract class BaseController {
    protected void usuarioLogado(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Usuario) {
            Usuario usuario = (Usuario) authentication.getPrincipal();
            model.addAttribute("usuarioLogado", usuario.getName());
            model.addAttribute("usuario", usuario);
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PROFILE_VIEW"))){
                model.addAttribute("view_profile", true);
            }else {
                model.addAttribute("view_profile", false);
            }
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER_VIEW"))){
                model.addAttribute("view_user", true);
            }else {
                model.addAttribute("view_user", false);
            }
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SECTOR_VIEW"))){
                model.addAttribute("view_sector", true);
            }else {
                model.addAttribute("view_sector", false);
            }
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SERVICE_VIEW"))){
                model.addAttribute("view_service", true);
            }else {
                model.addAttribute("view_service", false);
            }
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DEPARTMENT_VIEW"))){
                model.addAttribute("view_department", true);
            }else {
                model.addAttribute("view_department", false);
            }
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_APPLICATION_EDIT"))){
                model.addAttribute("view_administration", true);
            }else {
                model.addAttribute("view_administration", false);
            }
        } else {
            model.addAttribute("usuarioLogado", "Usuário não autenticado");
        }
    }
}
