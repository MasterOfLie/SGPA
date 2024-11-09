package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.config.BaseController;
import cloud.masteroflie.sgpa.models.Usuario;
import cloud.masteroflie.sgpa.repository.UsuarioRepository;
import cloud.masteroflie.sgpa.service.DepartamentoService;
import cloud.masteroflie.sgpa.service.PerfilService;
import cloud.masteroflie.sgpa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController extends BaseController {

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilService perfilService;

    @GetMapping()
    public String index(Model model) {
        usuarioLogado(model);
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "usuario/index";
    }
    @GetMapping("/editar/{idUsuario}")
    public String editar(Model model, @PathVariable("idUsuario") UUID idUsuario) {
        try {
            usuarioLogado(model);
            model.addAttribute("departamentos", departamentoService.listarDepartamentos());
            model.addAttribute("perfils", perfilService.listarPerfil());
            model.addAttribute("usuario", usuarioService.buscarUsuario(idUsuario));
            return "usuario/editar";
        }catch (Exception e) {
            e.getMessage();
            return "redirect:/usuarios";
        }
    }
}
