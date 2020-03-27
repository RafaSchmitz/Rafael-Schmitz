package br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.controller;

import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.model.Autor;
import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.model.Livro;
import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.repository.AutorRepository;
import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.repository.CidadeRepository;
import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.repository.EditoraRepository;
import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.repository.LivroRepository;
import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private GeneroService generoService;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    private void carregarCombos(Model model) {
        model.addAttribute("generos", generoService.findAll() );

        model.addAttribute("editoras", editoraRepository.findAll() );

        model.addAttribute("autores", autorRepository.findAll() );

        model.addAttribute("cidades", cidadeRepository.findAll() );
    }


    @GetMapping
    public String list(Model model) {
        model.addAttribute("livro", livroRepository.findAll());
        return "livro/list";
    }

    @GetMapping(value = {"new"})
    public String form(Model model) {
        model.addAttribute("livro", new Livro());
        carregarCombos(model);
        return "livro/form";
    }

    @PostMapping
    public String save(@Valid Livro livro,
                       BindingResult result,
                       Model model,
                       RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("livro", livro);
            carregarCombos(model);
            return "livro/form";
        }

        livroRepository.save(livro);
        attributes.addFlashAttribute("sucesso",
                "Registro salvo com sucesso!");
        return "redirect:/livro";
    }

    @GetMapping("{id}")
    public String form(@PathVariable Long id, Model model) {
        model.addAttribute("livro", livroRepository.findById(id));
        carregarCombos(model);
        return "livro/form";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id,
                         Model model,
                         RedirectAttributes attributes) {
        try {
            livroRepository.deleteById(id);
            attributes.addFlashAttribute("sucesso",
                    "Registro removido com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("erro",
                    "Falha ao remover o registro!");
        }
        return "redirect:/livro";


    }


}
