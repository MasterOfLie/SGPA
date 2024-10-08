package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.repository.AplicacaoRepository;
import cloud.masteroflie.sgpa.repository.UserRepository;
import cloud.masteroflie.sgpa.service.AplicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AuthController extends BaseController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AplicacaoService aplicacaoService;
    @Autowired
    AplicacaoRepository aplicacaoRepository;


    @GetMapping("auth")
    public String index(Model model) {
        model.addAttribute("cssFile", aplicacaoService.getAplicacao("aplicacao").getTheme() + ".css");
        System.out.println(aplicacaoRepository.findByAplicacao("aplicacao").getContainerBlob());
        return "auth/login.html";
    }

    @PostMapping("auth")
    public String post( Model model) {
        return "auth/login.html";
    }
}
