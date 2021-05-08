package br.edu.utfpr.projetofinal.service.impl;

import br.edu.utfpr.projetofinal.model.Pedido;
import br.edu.utfpr.projetofinal.repository.PedidoRepository;
import br.edu.utfpr.projetofinal.service.CrudService;
import br.edu.utfpr.projetofinal.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl extends CrudServiceImpl<Pedido, Long>  implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    protected JpaRepository<Pedido, Long> getRepository() {
        return pedidoRepository;
    }

}
