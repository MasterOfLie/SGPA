package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.models.Usuario;
import cloud.masteroflie.sgpa.service.DepartamentoService;
import cloud.masteroflie.sgpa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
@RequestMapping("/admin")
public class UsuarioController extends BaseController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        addRoleAttributes(model);
        model.addAttribute("usuarios",usuarioService.listarTodos());
        return "admin/usuario/index";
    }
    @GetMapping("/usuario/editar/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model) {
        addRoleAttributes(model);
        model.addAttribute("usuario_editavel", usuarioService.buscarPorId(id));
        model.addAttribute("alldepartamentos", departamentoService.listaTodos());
        model.addAttribute("departamentos", usuarioService.buscarPorId(id).getDepartamentos());
        return "admin/usuario/editar-usuario";
    }
    @PostMapping("/usuarios/atualizar")
    public String atualizarUsuario(@RequestParam(value = "isEnabled", required = false) boolean activo,
                                   @RequestParam(value = "departamentos", required = false) List<Long> departamentoIds,
                                   @ModelAttribute Usuario usuario, Model model,
                                   RedirectAttributes redirectAttributes) {
        usuario.setEnabled(activo);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            redirectAttributes.addFlashAttribute("usuario_editavel", "Usuario " + usuario.getNome() + " atualizado com sucesso");
            redirectAttributes.addFlashAttribute("atualizado", true);
            usuarioService.atualizarUsuario(usuario, departamentoIds);
        } else {
            redirectAttributes.addFlashAttribute("atualizado", true);
            redirectAttributes.addFlashAttribute("usuario_editavel", "Você não tem permissão para alterar esse usuário");
        }
        return "redirect:/admin/usuarios";
    }
}
