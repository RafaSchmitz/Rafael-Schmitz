package br.edu.utfpr.projetofinal.repository;

import br.edu.utfpr.projetofinal.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
