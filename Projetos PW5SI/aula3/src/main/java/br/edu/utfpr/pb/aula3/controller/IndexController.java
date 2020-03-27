package br.edu.utfpr.pb.aula3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping
	public String index(Model model) {
		
		model.addAttribute("teste", "Aula de Spring Web MVC com Thymeleaf!");
		return "index";
	}
	
	@GetMapping(value = {"teste", "teste2"})
	public String teste() {
		return "index";
	}
	
}
