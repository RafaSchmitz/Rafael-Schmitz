package br.edu.utfpr.pb.aula3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.utfpr.pb.aula3.model.Genero;
import br.edu.utfpr.pb.aula3.service.GeneroService;

@Controller
@RequestMapping("genero")
public class GeneroController {
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("generos", generoService.findAll());
		return "genero/list";
	}
	
	@GetMapping(value = {"new", "novo", "form"})
	public String form(Model model) {
		model.addAttribute("genero", new Genero());
		return "genero/form";
	}

	@PostMapping
	public String save(@Valid Genero genero, 
					   BindingResult result,
					   Model model,
					   RedirectAttributes attributes) {
		if ( result.hasErrors() ) {
			model.addAttribute("genero", genero);
			return "genero/form";
		}
		
		generoService.save(genero);
		attributes.addFlashAttribute("sucesso", 
									 "Registro salvo com sucesso!");
		return "redirect:/genero";
	}

	@GetMapping("{id}") // /genero/1
 	public String form(@PathVariable Long id, Model model) {
		model.addAttribute("genero", generoService.findOne(id));
		return "genero/form";
	}
	
	@DeleteMapping("{id}") // /genero/25
	public String delete(@PathVariable Long id,
						 RedirectAttributes attributes) {
		try {
			generoService.delete(id);
			attributes.addFlashAttribute("sucesso", 
					"Registro removido com sucesso!");
		} catch (Exception e) {
			attributes.addFlashAttribute("erro", 
					"Falha ao remover o registro!");
		}
		return "redirect:/genero";
	}
}






