package br.edu.utfpr.projetofinal.controller;

import br.edu.utfpr.projetofinal.model.Produto;
import br.edu.utfpr.projetofinal.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("catalog")
public class CatalogController {

    @Autowired
    private ProdutoService produtoService;

//    @GetMapping
//    public String catalog() {
//        return "catalog/index";
//    }

//    @GetMapping("old")
//    public String list(Model model) {
//        model.addAttribute("produtos", produtoService.findAll());
//        return "catalog/index";
//    }

    @GetMapping() // /produto?page=1&size=6
    public String list(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);

        Page<Produto> list = this.produtoService.findAll(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("produtos", list);

        if (list.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, list.getTotalPages())
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "catalog/index";
    }



}
