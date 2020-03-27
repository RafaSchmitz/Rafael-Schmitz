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

import br.edu.utfpr.pb.aula3.model.Produtora;
import br.edu.utfpr.pb.aula3.service.ProdutoraService;

@Controller
@RequestMapping("produtora")
public class ProdutoraController {
	
	@Autowired
	private ProdutoraService produtoraService;
	
	@GetMapping
	public String list( Model model ) {
		model.addAttribute("produtoras",
				produtoraService.findAll());
		return "produtora/list";
	}
	
	@GetMapping({"new", "novo"})
	public String form( Model model) {
		model.addAttribute("produtora", new Produtora());
		return "produtora/form";
	}
	
	@PostMapping
	public String save(@Valid Produtora produtora,
					   BindingResult result,
					   Model model,
					   RedirectAttributes attributes) {
		if (result.hasErrors()) {
			model.addAttribute("produtora", produtora);
			return "produtora/form";
		}
		produtoraService.save(produtora);
		attributes.addFlashAttribute("sucesso", 
				"Registro salvo com sucesso!");
		return "redirect:/produtora";
	}
	
	@GetMapping("{id}")
	public String form(@PathVariable Long id, Model model) {
		model.addAttribute("produtora", produtoraService.findOne(id));
		return "produtora/form";
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable Long id, 
			 			 Model model,
			 			 RedirectAttributes attributes) {
		try {
			produtoraService.delete(id);
			attributes.addFlashAttribute("sucesso", 
					"Registro removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("erro", 
					"Falha ao remover registro");
		}
		return "redirect:/produtora";
	}
}




