package br.edu.utfpr.projetofinal.controller;


import br.edu.utfpr.projetofinal.model.Pedido;
import br.edu.utfpr.projetofinal.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

import java.time.LocalDate;

@Controller
@RequestMapping("cart")
public class CartController {


    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public String cart() {
        return "cart/index";
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid Pedido pedido,
                                  BindingResult result,
                                  Model model) {
        try {
            if (result.hasErrors()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            pedido.getPedidoProdutos().forEach(cp -> cp.getId().setPedido(pedido));
            pedido.setData(LocalDate.now());
            pedidoService.save(pedido);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }


}
