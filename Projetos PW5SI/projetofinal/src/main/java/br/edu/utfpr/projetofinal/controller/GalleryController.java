package br.edu.utfpr.projetofinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("gallery")
public class GalleryController {

    @GetMapping
    public String gallery() {
        return "gallery/index";
    }

}
