package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.config.BaseController;
import cloud.masteroflie.sgpa.models.Processo;
import cloud.masteroflie.sgpa.service.ProcessoService;
import cloud.masteroflie.sgpa.service.ServicoService;
import cloud.masteroflie.sgpa.service.SetorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/portal")
public class PortalController extends BaseController {

    @Autowired
    private SetorService setorService;

    @Autowired
    private ProcessoService processoService;
    @Autowired
    private ServicoService servicoService;

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
    @GetMapping("/processos/criar/{servicoID}")
    public String portalCriarProcesso(Model model,@PathVariable UUID servicoID, Authentication auth) {
        usuarioLogado(model);
        try {
            model.addAttribute("servico", servicoService.buscarServico(servicoID));
        }catch (Exception e){
            log.error(e.getMessage());
        }
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
