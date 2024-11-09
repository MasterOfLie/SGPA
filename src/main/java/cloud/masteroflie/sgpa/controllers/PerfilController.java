package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.config.BaseController;
import cloud.masteroflie.sgpa.models.Perfil;
import cloud.masteroflie.sgpa.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/perfils")
public class PerfilController  extends BaseController {
    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public String homePerfils(Model model) {
        try{
            usuarioLogado(model);
            model.addAttribute("perfils", perfilService.listarPerfil());
            return "perfil/index";
        }catch (Exception e){
            return "perfil/index";
        }
    }
    @GetMapping("/criar")
    public String newPerfils(Model model) {
        usuarioLogado(model);
        return "perfil/criar";
    }
    @GetMapping("/editar/{perfilID}")
    public String editarPerfil(@PathVariable("perfilID") UUID idPerfil, Model model) {
        try{
            usuarioLogado(model);
            model.addAttribute("permissions", perfilService.buscarPerfil(idPerfil).getPermissions());
            model.addAttribute("perfil", perfilService.buscarPerfil(idPerfil));
            return "perfil/editar";
        }catch (Exception e){
            return "perfil/editar";
        }
    }
}
