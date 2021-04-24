package br.edu.utfpr.projetofinal.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("checkout")
public class CheckOutController {

    @GetMapping
    public String checkout() {
        return "checkout/index";
    }
}
