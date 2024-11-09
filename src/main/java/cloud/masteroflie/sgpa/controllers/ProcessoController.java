package cloud.masteroflie.sgpa.controllers;

import aj.org.objectweb.asm.commons.TryCatchBlockSorter;
import cloud.masteroflie.sgpa.config.BaseController;
import cloud.masteroflie.sgpa.models.Movimentacao;
import cloud.masteroflie.sgpa.repository.ProcessoRepository;
import cloud.masteroflie.sgpa.service.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.UUID;

@Controller
@RequestMapping("/processos")
public class ProcessoController extends BaseController {

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SetorService setorService;

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private ServicoService servicoService;

    @GetMapping()
    public String processos(Model model) {
        usuarioLogado(model);
        model.addAttribute("processos", processoService.listarProcessos());
        return "processos/index";
    }

    @GetMapping("/analise")
    public String analise(Model model, Authentication authentication) {
        try{
            usuarioLogado(model);
            model.addAttribute("processos", processoService.emAnalise(authentication));
            return "processos/analise";
        }catch (Exception e){
            return "redirect:/";
        }
    }


    @GetMapping("/criar")
    public String criarProcesso(Model model) {
        usuarioLogado(model);
        model.addAttribute("departamentos", departamentoService.listarDepartamentos());
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        model.addAttribute("servicos", servicoService.listarServicos());
        return "processos/criar";
    }

    @GetMapping("/editar/{idProcesso}")
    public String editarProcesso(@PathVariable("idProcesso") UUID idProcesso, Model model) {
        try{
            usuarioLogado(model);
            model.addAttribute("departamentos", departamentoService.listarDepartamentos());
            model.addAttribute("usuarios", usuarioService.listarUsuarios());
            model.addAttribute("servicos", servicoService.listarServicos());
            model.addAttribute("processo", processoService.buscarProcesso(idProcesso));
            model.addAttribute("movimentacoes", processoService.buscarProcesso(idProcesso).getMovimentacoes().stream().sorted(Comparator.comparing((Movimentacao::getDataCriacao))));
            return "processos/editar";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/processos/analise";
        }
    }
    @GetMapping("/{idProcesso}")
    public String visualizarProcesso(@PathVariable("idProcesso") UUID idProcesso, Model model) {
        try{
            usuarioLogado(model);
            model.addAttribute("departamentos", departamentoService.listarDepartamentos());
            model.addAttribute("usuarios", usuarioService.listarUsuarios());
            model.addAttribute("servicos", servicoService.listarServicos());
            model.addAttribute("processo", processoService.buscarProcesso(idProcesso));
            model.addAttribute("movimentacoes", processoService.buscarProcesso(idProcesso).getMovimentacoes().stream().sorted(Comparator.comparing((Movimentacao::getDataCriacao))));
            return "processos/visualizar";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/processos";
        }
    }

}
