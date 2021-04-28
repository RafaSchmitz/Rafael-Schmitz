package br.edu.utfpr.projetofinal.repository;

import br.edu.utfpr.projetofinal.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}