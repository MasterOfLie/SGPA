package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.dto.ConfigDTO;
import cloud.masteroflie.sgpa.service.AplicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    
    @Autowired
    private AplicacaoService aplicacaoService;

    public String index(Model model) {
        return "admin/index";
    }

    @GetMapping("/theme")
    public String escolherTema(@RequestParam(value = "theme", required = false, defaultValue = "default") String theme, Model model) {
        // O tema pode ser passado por um parâmetro de URL, por exemplo ?theme=dark
        model.addAttribute("cssFile", theme + ".css"); // Passa o arquivo CSS correspondente
        return "index";
    }

    @GetMapping("/config")
    public String aplicacao(Model model) {
        addRoleAttributes(model);
        model.addAttribute("configAplicacao", aplicacaoService.getAplicacao("aplicacao"));
        return "admin/config/index";
    }
    @PostMapping("/config")
    public String atualizarAplicacao(@ModelAttribute ConfigDTO config, Model model) {
        aplicacaoService.salvarAplicacao(config);
        return "redirect:/admin/usuarios";
    }

}
