package br.edu.utfpr.projetofinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id.pedido")
    private List<PedidoProduto> pedidoProdutos;

    public double getValorTotal() {
        return pedidoProdutos.stream()
                .map(cp -> cp.getValor() * cp.getQuantidade())
                .collect(Collectors.summingDouble(Double::doubleValue));
    }
}