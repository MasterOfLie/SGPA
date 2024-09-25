package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.dto.ProcessoResponse;
import cloud.masteroflie.sgpa.dto.SolicitanteDTO;
import cloud.masteroflie.sgpa.dto.UsuarioDTO;
import cloud.masteroflie.sgpa.mapper.MapperUtil;
import cloud.masteroflie.sgpa.models.Arquivos;
import cloud.masteroflie.sgpa.models.Processo;
import cloud.masteroflie.sgpa.models.Usuario;
import cloud.masteroflie.sgpa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/")
public class StaffController extends BaseController {

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private BlobService fileService;

    @Autowired
    private ArquivoService arquivoService;

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
    public String processosCriarPage(Model model) {
        addRoleAttributes(model);
        model.addAttribute("processoResponse", new ProcessoResponse());
        model.addAttribute("servicos", servicoService.listarTodos());
        model.addAttribute("departamentos", departamentoService.listaTodos());
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "staff/criar-processo";
    }
    @GetMapping("processos/editar/{id}")
    public String processosEditarPage(@PathVariable("id") Long id, Model model) {
        addRoleAttributes(model);
        Set<Arquivos> arquivos = processoService.buscarPorID(id).getArquivos();
        model.addAttribute("servicos", servicoService.listarTodos());
        model.addAttribute("departamentos", departamentoService.listaTodos());
        model.addAttribute("processo", processoService.buscarPorID(id));
        model.addAttribute("arquivos", arquivos);
        return "staff/editar-processo";
    }
    @PostMapping("processos/editar")
    public String editarProcesso(@RequestParam(value = "processoID", required = false) Long processoID, @RequestParam("file") MultipartFile[] files, ProcessoResponse processoResponse, Model model) throws IOException {
        Processo processo = processoService.atualizarProcesso(processoResponse, processoID);
        MultipartFile[] arquivos = files;
        fileService.salvarArquivos(files, processo.getId());
        addRoleAttributes(model);
        return "redirect:/processos/editar/" + processo.getId();
    }

    @PostMapping("processos/criar")
    public String processosCriar(@RequestParam("file") MultipartFile[] files, ProcessoResponse processoResponse, Model model) throws IOException {
        Processo processo = processoService.criarProcesso(processoResponse);
        fileService.salvarArquivos(files, processo.getId());

        return "redirect:/processos";
    }
    @GetMapping("processos/arquivo/{id}")
    public String visualizarArquivo(@PathVariable("id") Long arquivoID, Model model) {
        String url =fileService.urlArquivos(arquivoService.buscarArquivo(arquivoID).getNomeBlob());
        return "redirect:" + url;
    }
    @DeleteMapping("processos/arquivo/remover/{id}")
    public ResponseEntity removerArquivo(@PathVariable("id") Long arquivoID, Model model) {
        fileService.removerArquivo(arquivoService.buscarArquivo(arquivoID).getNomeBlob());
        arquivoService.removerArquivo(arquivoID);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/solicitantes")
    public String listarSolicitantes(Model model) {
        addRoleAttributes(model);
        model.addAttribute("usuarios", mapperUtil.SolicitanteResponse());
        return "staff/solicitantes/index";
    }
    @GetMapping("/solicitante/editar/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model) {
        addRoleAttributes(model);
        model.addAttribute("usuario_editavel", usuarioService.buscarPorId(id));
        model.addAttribute("alldepartamentos", departamentoService.listaTodos());
        model.addAttribute("departamentos", usuarioService.buscarPorId(id).getDepartamentos());
        return "staff/solicitantes/editar-solicitante";
    }
    @PostMapping("/solicitante/atualizar")
    public String atualizarUsuario(@ModelAttribute SolicitanteDTO solicitanteDTO, Model model,
                                   RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STAFF"))) {
            redirectAttributes.addFlashAttribute("usuario_editavel", "Solicitante " + solicitanteDTO.getNome() + " atualizado com sucesso");
            redirectAttributes.addFlashAttribute("atualizado", true);
            usuarioService.atualizarSolicitantes(solicitanteDTO);
        } else {
            redirectAttributes.addFlashAttribute("atualizado", true);
            redirectAttributes.addFlashAttribute("usuario_editavel", "Você não tem permissão para alterar esse solicitante");
        }
        return "redirect:/solicitantes";
    }
}
