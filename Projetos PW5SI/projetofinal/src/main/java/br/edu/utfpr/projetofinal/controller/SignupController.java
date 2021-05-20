package br.edu.utfpr.projetofinal.controller;

import br.edu.utfpr.projetofinal.model.Permissao;
import br.edu.utfpr.projetofinal.model.Usuario;
import br.edu.utfpr.projetofinal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("signup")
public class SignupController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "new")
    public String form(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "signup/index";
    }

    @PostMapping
    public String save(@Valid Usuario usuario,
                       BindingResult result,
                       Model model,
                       RedirectAttributes attributes) {
        if ( result.hasErrors() ) {
            model.addAttribute("usuario", usuario);
            return "signup/index";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);

        usuarioService.save(usuario);

        attributes.addFlashAttribute("sucesso",
                "Registro salvo com sucesso!");
        return "redirect:/";
    }


}
