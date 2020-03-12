package br.edu.utfpr.pb.Aula1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("produto")
public class ProdutoController {

    @GetMapping("produto1")
    @ResponseBody
    public String getProdutos(){

        return "Produto 1, 2, 3, 4...";

    }

}
