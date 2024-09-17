package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.models.Departamento;
import cloud.masteroflie.sgpa.models.Servico;
import cloud.masteroflie.sgpa.models.Usuario;
import cloud.masteroflie.sgpa.service.DepartamentoService;
import cloud.masteroflie.sgpa.service.ServicoService;
import cloud.masteroflie.sgpa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DepartamentoService departamentoService;


    public String index(Model model) {
        return "admin/index";
    }

    @GetMapping("/servicos")
    public String listarServicos(Model model) {
        addRoleAttributes(model);
        List<Servico> servicoList = servicoService.listarTodos();
        model.addAttribute("servicos", servicoList);
        return "admin/servico/index";
    }
    @GetMapping("/servico/criar")
    public String criarServico(Model model) {
        addRoleAttributes(model);
        model.addAttribute("servico", new Servico());
        return "admin/servico/novo-servico";
    }
    @PostMapping("/servico/criar")
    public String criarServicoPost(@ModelAttribute Servico servico, Model model) {
        servicoService.criarServico(servico);
        return "redirect:/admin/servico";
    }

    @GetMapping("/servico/editar/{id}")
    public String editarServico(@PathVariable("id") Long id, Model model) {
        addRoleAttributes(model);
        model.addAttribute("servico", servicoService.buscarServico(id));
        return "admin/servico/editar-servico";
    }
    @PostMapping("/servico/editar")
    public String editarServicoPost(@RequestParam(value = "isActive", required = false) boolean activo, @ModelAttribute Servico servico, Model model) {
        Servico salvarServico = servicoService.buscarServico(servico.getId());
        salvarServico.setNome(servico.getNome());
        salvarServico.setDescricao(servico.getDescricao());
        salvarServico.setActive(activo);
        servicoService.atualizarServico(salvarServico);
        return "redirect:/admin/servicos";
    }

//    -------------------- USUARIO ADMINITRAÇÃO -----------------------------
    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        addRoleAttributes(model);
        model.addAttribute("usuarios",usuarioService.listarTodos());
        return "admin/usuario/index";
    }
    @GetMapping("/usuario/editar/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model) {
        addRoleAttributes(model);
        model.addAttribute("usuario_editavel", usuarioService.buscarPorId(id));
        model.addAttribute("alldepartamentos", departamentoService.listaTodos());
        model.addAttribute("departamentos", usuarioService.buscarPorId(id).getDepartamentos());
        return "admin/usuario/editar-usuario";
    }
    @PostMapping("/usuarios/atualizar")
    public String atualizarUsuario(@RequestParam(value = "isEnabled", required = false) boolean activo, @RequestParam(value = "departamentos", required = false) List<Long> departamentoIds, @ModelAttribute Usuario usuario, Model model) {
        usuario.setEnabled(activo);
        System.out.println(usuario.getRole());
        usuarioService.atualizarDepartamento(usuario, departamentoIds);
        return "redirect:/admin/usuarios";
    }


//    --------------------- DEPARTAMENTO --------------------

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
