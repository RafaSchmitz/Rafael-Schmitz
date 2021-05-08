package br.edu.utfpr.projetofinal.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"pedido", "produto"})
public class PedidoProdutoPk implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private Pedido pedido;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

}
