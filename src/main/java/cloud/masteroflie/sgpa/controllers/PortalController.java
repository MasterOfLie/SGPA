package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.config.BaseController;
import cloud.masteroflie.sgpa.models.Processo;
import cloud.masteroflie.sgpa.service.ProcessoService;
import cloud.masteroflie.sgpa.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/portal")
public class PortalController extends BaseController {

    @Autowired
    private SetorService setorService;

    @Autowired
    private ProcessoService processoService;

    @GetMapping
    public String index(Model model) {
        usuarioLogado(model);
        model.addAttribute("setores", setorService.listarSetor());
        return "portal/index";
    }
    @GetMapping("/processos")
    public String processos(Model model, Authentication auth) {
        usuarioLogado(model);
        model.addAttribute("processos", processoService.meusProcessos(auth));
        return "portal/processos";
    }
    @GetMapping("/processos/criar")
    public String portalCriarProcesso(Model model, Authentication auth) {
        usuarioLogado(model);
        for (Processo processo : processoService.meusProcessos(auth)){
            model.addAttribute("processo", processo);
        }
        model.addAttribute("processos", processoService.meusProcessos(auth));
        return "portal/criar";
    }
    @GetMapping("/processo/{idProcesso}")
    public String visualizarProcesso(Model model, Authentication auth, @PathVariable UUID idProcesso) {
        usuarioLogado(model);
        try {
            model.addAttribute("processo", processoService.buscarProcesso(idProcesso));
            model.addAttribute("arquivos", null);
            return "portal/visualizar";
        }catch (Exception e){
            return "redirect:/portal/processos";
        }
    }

}
