package cloud.masteroflie.sgpa.controllers;

import aj.org.objectweb.asm.commons.TryCatchBlockSorter;
import cloud.masteroflie.sgpa.config.BaseController;
import cloud.masteroflie.sgpa.models.Departamento;
import cloud.masteroflie.sgpa.service.DepartamentoService;
import cloud.masteroflie.sgpa.service.ServicoService;
import cloud.masteroflie.sgpa.service.SetorService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/servicos")
public class ServicoController extends BaseController {

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private SetorService setorService;

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public String index(Model model) {
        usuarioLogado(model);
        model.addAttribute("servicos", servicoService.listarServicos());
        return "servico/index";
    }

    @GetMapping("/criar")
    public String criar(Model model) {
        usuarioLogado(model);
        model.addAttribute("departamentos", departamentoService.listarDepartamentos());
        model.addAttribute("setores", setorService.listarSetor());
        return "servico/criar";
    }
    @GetMapping("/editar/{idServico}")
    public String editar(Model model, @PathVariable("idServico") UUID idServico) {
        try{
            usuarioLogado(model);
            model.addAttribute("processos", servicoService.buscarServico(idServico).getProcessos().stream().count());
            model.addAttribute("servico", servicoService.buscarServico(idServico));
            model.addAttribute("departamentos", departamentoService.listarDepartamentos());
            model.addAttribute("setores", setorService.listarSetor());
            return "servico/editar";
        }catch (Exception e) {
            e.printStackTrace();
            return "redirect:/servicos";
        }
    }

}
