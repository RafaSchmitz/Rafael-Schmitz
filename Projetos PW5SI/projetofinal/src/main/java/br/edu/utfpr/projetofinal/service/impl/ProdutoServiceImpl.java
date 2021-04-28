package br.edu.utfpr.projetofinal.service.impl;

import br.edu.utfpr.projetofinal.model.Produto;
import br.edu.utfpr.projetofinal.repository.ProdutoRepository;
import br.edu.utfpr.projetofinal.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl extends CrudServiceImpl<Produto, Long>  implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    protected JpaRepository<Produto, Long> getRepository() {
        return produtoRepository;
    }

}

