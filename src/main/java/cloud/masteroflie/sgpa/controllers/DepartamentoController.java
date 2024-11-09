package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.config.BaseController;
import cloud.masteroflie.sgpa.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController extends BaseController {

    @Autowired
    private DepartamentoService departamentoService;


    @GetMapping
    public String index(Model model) {
        usuarioLogado(model);
        model.addAttribute("departamentos", departamentoService.listarDepartamentos());
        return "departamento/index";
    }

    @GetMapping("/criar")
    public String criarDepartamento(Model model) {
        usuarioLogado(model);
        return "departamento/criar";
    }

    @GetMapping("/editar/{departamentoID}")
    public String editarPerfil(@PathVariable("departamentoID") UUID departamentoID, Model model) {
        try {
            usuarioLogado(model);
            model.addAttribute("departamento", departamentoService.buscarDepartamento(departamentoID));
            return "departamento/editar";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/departamentos";
    }
}
