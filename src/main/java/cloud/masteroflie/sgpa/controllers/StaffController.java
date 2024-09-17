package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.dto.ProcessoResponse;
import cloud.masteroflie.sgpa.mapper.MapperUtil;
import cloud.masteroflie.sgpa.models.Processo;
import cloud.masteroflie.sgpa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

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
    private FileService fileService;

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
        model.addAttribute("servicos", servicoService.listarTodos());
        model.addAttribute("departamentos", departamentoService.listaTodos());
        model.addAttribute("processo", processoService.buscarPorID(id));
        model.addAttribute("files", fileService.arquivos(id));
        return "staff/editar-processo";
    }
    @PostMapping("processos/editar")
    public String editarProcesso(@RequestParam(value = "processoID", required = false) Long processoID, @RequestParam("file") MultipartFile[] files, ProcessoResponse processoResponse, Model model) throws IOException {
        Processo processo = processoService.atualizarProcesso(processoResponse, processoID);
        MultipartFile[] arquivos = files;
        fileService.salvarArquivos(files, processo.getId());
        addRoleAttributes(model);
        return "redirect:/processos";
    }

    @PostMapping("processos/criar")
    public String processosCriar(@RequestParam("file") MultipartFile[] files, ProcessoResponse processoResponse, Model model) throws IOException {
        Processo processo = processoService.criarRequest(processoResponse);
        MultipartFile[] arquivos = files;
        fileService.salvarArquivos(files, processo.getId());
        addRoleAttributes(model);
        return "redirect:/processos";
    }

}
