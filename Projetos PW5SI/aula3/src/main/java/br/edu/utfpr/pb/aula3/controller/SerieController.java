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

import br.edu.utfpr.pb.aula3.model.Serie;
import br.edu.utfpr.pb.aula3.service.GeneroService;
import br.edu.utfpr.pb.aula3.service.ProdutoraService;
import br.edu.utfpr.pb.aula3.service.SerieService;

@Controller
@RequestMapping("serie")
public class SerieController {
	
	@Autowired
	private SerieService serieService;
	@Autowired
	private GeneroService generoService;
	@Autowired
	private ProdutoraService produtoraService;
	
	
	@GetMapping
	public String list( Model model ) {
		model.addAttribute("series", serieService.findAll());
		return "serie/list";
	}
	
	@GetMapping({"new", "novo"})
	public String form( Model model) {
		
		model.addAttribute("serie", new Serie());
		
		carregarCombos(model);
		
		return "serie/form";
	}
	
	private void carregarCombos(Model model) {
		model.addAttribute("generos", generoService.findAll() );
		
		model.addAttribute("produtoras", produtoraService.findAll() );
	}
	
	@PostMapping
	public String save(@Valid Serie serie,
					   BindingResult result,
					   Model model,
					   RedirectAttributes attributes) {
		if (result.hasErrors()) {
			model.addAttribute("serie", serie);
			carregarCombos(model);
			return "serie/form";
		}
		serieService.save(serie);
		attributes.addFlashAttribute("sucesso", 
				"Registro salvo com sucesso!");
		return "redirect:/serie";
	}
	
	@GetMapping("{id}")
	public String form(@PathVariable Long id, Model model) {
		model.addAttribute("serie", serieService.findOne(id));
		carregarCombos(model);
		return "serie/form";
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable Long id, 
			 			 Model model,
			 			 RedirectAttributes attributes) {
		try {
			serieService.delete(id);
			attributes.addFlashAttribute("sucesso", 
					"Registro removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("erro", 
					"Falha ao remover registro");
		}
		return "redirect:/serie";
	}
}




