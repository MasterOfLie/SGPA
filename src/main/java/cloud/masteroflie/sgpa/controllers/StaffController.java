package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.mapper.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/")
public class StaffController extends BaseController {

    @Autowired
    private MapperUtil mapperUtil;

    @GetMapping()
    public String home(Model model) {
        addRoleAttributes(model);
        return "index";
    }
    @GetMapping("processos")
    public String processos(Model model, Map map) {
        addRoleAttributes(model);
        model.addAttribute("processos", mapperUtil.processosUsuario());
        return "staff/index";
    }
    @GetMapping("processos/criar")
    public String processosCriarPage(Model model, Map map) {
        addRoleAttributes(model);
        return "staff/criar-processo";
    }

}
