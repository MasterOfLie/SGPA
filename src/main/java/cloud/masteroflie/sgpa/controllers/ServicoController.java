package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.models.Servico;
import cloud.masteroflie.sgpa.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ServicoController extends BaseController{

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/servicos")
    public String listarServicos(Model model) {
        addRoleAttributes(model);
        List<Servico> servicoList = servicoService.listarTodos();
        model.addAttribute("servicos", servicoList);
        return "admin/servico/index";
    }
    @GetMapping("/servicos/criar")
    public String criarServico(Model model) {
        addRoleAttributes(model);
        model.addAttribute("servico", new Servico());
        return "admin/servico/criar-servico";
    }
    @PostMapping("/servicos/criar")
    public String criarServicoPost(@ModelAttribute Servico servico, Model model, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            redirectAttributes.addFlashAttribute("usuario_editavel", "Você não tem permissão para criar um serviço");
            redirectAttributes.addFlashAttribute("atualizado", true);
            servicoService.salvar(servico);
        } else {
            redirectAttributes.addFlashAttribute("atualizado", true);
            redirectAttributes.addFlashAttribute("usuario_editavel", "Você não tem permissão para criar um serviço");
        }
        return "redirect:/admin/servicos";
    }

    @GetMapping("/servicos/editar/{id}")
    public String editarServico(@PathVariable("id") Long id, Model model) {
        addRoleAttributes(model);
        model.addAttribute("servico", servicoService.buscarServico(id));
        return "admin/servico/editar-servico";
    }
    @PostMapping("/servicos/editar")
    public String editarServicoPost(@RequestParam(value = "isActive", required = false) boolean activo, @ModelAttribute Servico servico, Model model, RedirectAttributes redirectAttributes) {
        Servico salvarServico = servicoService.buscarServico(servico.getId());
        salvarServico.setActive(activo);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            redirectAttributes.addFlashAttribute("usuario_editavel", "Você não tem permissão para alterar esse serviço");
            redirectAttributes.addFlashAttribute("atualizado", true);
            servicoService.salvar(salvarServico);
        } else {
            redirectAttributes.addFlashAttribute("atualizado", true);
            redirectAttributes.addFlashAttribute("usuario_editavel", "Você não tem permissão para alterar esse serviço");
        }
        return "redirect:/admin/servicos";
    }
}
