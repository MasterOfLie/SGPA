package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.models.Departamento;
import cloud.masteroflie.sgpa.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class DepartamentoController extends BaseController{

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/departamento")
    public String listarDepartamento(Model model) {
        addRoleAttributes(model);
        model.addAttribute("departamentos", departamentoService.listaTodos());
        return "admin/departamento/index";
    }

    @GetMapping("/departamento/criar")
    public String criarDepartamentoView(@ModelAttribute Departamento departamento, Model model) {
        addRoleAttributes(model);
        model.addAttribute("departamento", new Departamento());
        return "admin/departamento/criar-departamento";
    }

    @PostMapping("/departamento/criar")
    public String criarDepartamento(@ModelAttribute Departamento departamento, Model model) {
        addRoleAttributes(model);
        departamentoService.criarDepartamento(departamento);
        return"redirect:/admin/departamento";
    }
    @GetMapping("/departamento/editar/{id}")
    public String editarDepartamento(@PathVariable("id") Long id, Model model) {
        addRoleAttributes(model);
        model.addAttribute("departamento", departamentoService.buscaPorId(id));
        return "admin/departamento/editar-departamento";
    }
    @PostMapping("/departamento/editar")
    public String editarDepartamento(@RequestParam(value = "isActive", required = false) boolean activo, @ModelAttribute Departamento departamento, Model model) {
        departamentoService.salvarDepartamento(departamento, activo);
        return "redirect:/admin/departamento";
    }
}
