package br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.controller;

import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.model.Editora;
import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.model.Genero;
import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.repository.EditoraRepository;
import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.repository.GeneroRepository;
import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("genero", generoService.findAll());
        return "genero/list";
    }

    @GetMapping(value = {"new"})
    public String form(Model model) {
        model.addAttribute("genero", new Genero());
        return "genero/form";
    }

    @PostMapping
    public String save(@Valid Genero genero,
                       BindingResult result,
                       Model model,
                       RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("genero", genero);
            return "genero/form";
        }

        generoService.save(genero);
        attributes.addFlashAttribute("sucesso",
                "Registro salvo com sucesso!");
        return "redirect:/genero";
    }

    @GetMapping("{id}")
    public String form(@PathVariable Long id, Model model) {
        model.addAttribute("genero", generoService.findOne(id));
        return "genero/form";
    }

    @DeleteMapping("{id}")
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
