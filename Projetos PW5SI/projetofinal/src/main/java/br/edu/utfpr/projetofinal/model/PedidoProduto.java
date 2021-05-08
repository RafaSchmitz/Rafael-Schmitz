package br.edu.utfpr.projetofinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PedidoProduto implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PedidoProdutoPk id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double valor;
}
