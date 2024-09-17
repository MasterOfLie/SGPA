package cloud.masteroflie.sgpa.controllers;

import cloud.masteroflie.sgpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("auth")
    public String index(Model model) {
        return "/auth/login";
    }

    @PostMapping("auth")
    public String post(Model model) {
        System.out.println("post");
        addRoleAttributes(model);
        return "redirect:/";
    }
}
