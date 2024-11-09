package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.config.BaseController;
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
@RequestMapping("/solicitantes")
public class SolicitanteController extends BaseController {
    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilService perfilService;

    @GetMapping()
    public String index(Model model) {
        try {
            usuarioLogado(model);
            model.addAttribute("usuarios", usuarioService.listarSolicitantes());
            return "solicitante/index";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/solicitantes";
        }
    }
    @GetMapping("criar")
    public String cadastrarSolicitante(Model model) {
        try {
            usuarioLogado(model);
            return "solicitante/criar";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/solicitantes";
        }
    }

    @GetMapping("/editar/{idUsuario}")
    public String editar(Model model, @PathVariable("idUsuario") UUID idUsuario) {
        try {
            usuarioLogado(model);
            model.addAttribute("usuario", usuarioService.buscarUsuario(idUsuario));
            return "solicitante/editar";
        }catch (Exception e) {
            e.printStackTrace();
            return "redirect:/solicitantes";
        }
    }
}

