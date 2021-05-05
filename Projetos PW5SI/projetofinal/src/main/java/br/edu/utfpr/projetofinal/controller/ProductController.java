package br.edu.utfpr.projetofinal.controller;

import br.edu.utfpr.projetofinal.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("{id}")
    public String form(@PathVariable Long id, Model model) {
        model.addAttribute("produto", produtoService.findOne(id));
        return "product/product";
    }
}
