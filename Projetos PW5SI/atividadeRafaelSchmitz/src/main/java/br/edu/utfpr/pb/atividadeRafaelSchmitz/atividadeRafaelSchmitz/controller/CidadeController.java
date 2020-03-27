package br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.controller;

import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.model.Autor;
import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.model.Cidade;
import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.repository.AutorRepository;
import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("cidade")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("cidade", cidadeRepository.findAll());
        return "cidade/list";
    }

    @GetMapping(value = {"new"})
    public String form(Model model) {
        model.addAttribute("cidade", new Cidade());
        return "cidade/form";
    }

    @PostMapping
    public String save(@Valid Cidade cidade,
                       BindingResult result,
                       Model model,
                       RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("cidade", cidade);
            return "cidade/form";
        }

        cidadeRepository.save(cidade);
        attributes.addFlashAttribute("sucesso",
                "Registro salvo com sucesso!");
        return "redirect:/cidade";
    }

    @GetMapping("{id}")
    public String form(@PathVariable Long id, Model model) {
        model.addAttribute("cidade", cidadeRepository.findById(id));
        return "cidade/form";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes attributes) {
        try {
            cidadeRepository.deleteById(id);
            attributes.addFlashAttribute("sucesso",
                    "Registro removido com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("erro",
                    "Falha ao remover o registro!");
        }
            return "redirect:/cidade";


    }


}
