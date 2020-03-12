package br.edu.utfpr.pb.Aula1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping
    @ResponseBody
    public String Index(){
        return "Hi mundo!";
    }

    @GetMapping("teste")
    @ResponseBody
    public String teste(){
        return "Teste";
    }

}
