package br.edu.utfpr.projetofinal.service;

import br.edu.utfpr.projetofinal.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoService extends CrudService<Produto, Long>{

    Page<Produto> findAllByCategoriaId(Long id, Pageable pageable);

}
