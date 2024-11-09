package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.config.BaseController;
import cloud.masteroflie.sgpa.models.Setor;
import cloud.masteroflie.sgpa.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/setores")
public class SetorController extends BaseController {

    @Autowired
    private SetorService setorService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("setores", setorService.listarSetor());
        usuarioLogado(model);
        return "setor/index";
    }

    @GetMapping("/criar")
    public String criarSetor(Model model) {
        usuarioLogado(model);
        return "setor/criar";
    }
    @GetMapping("/editar/{idSetor}")
    public String editarSetor(@PathVariable("idSetor") UUID idSetor, Model model) {
        usuarioLogado(model);
        try{
            model.addAttribute("setor", setorService.buscarSetor(idSetor));
        }catch(Exception e){
            e.printStackTrace();
        }
        return "setor/editar";
    }
}
