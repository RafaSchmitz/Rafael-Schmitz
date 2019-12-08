package br.edu.utfpr.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class CompraServico implements AbstractModel<Serializable>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo quantidade deve ser preenchido.")
    @Column(nullable = false)
    private Integer quantidade;

    @DecimalMin(value = "0.01", 
            message = "O valor deve ser maior que R$ 0.00.")
    @Column(nullable = false)
    private Double valor;
    
    @NotNull(message = "O campo data da compra deve ser selecionado.")
    @Column(nullable = false)
    private LocalDate date;
    
    @ManyToOne()
    @JoinColumn(name = "reservaQuartoCliente_id", referencedColumnName = "id")
    private ReservaQuartoCliente reservaQuartoCliente;

    @ManyToOne
    @JoinColumn(name = "servico_id", referencedColumnName = "id")
    private Servico servico;

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public CompraServico() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ReservaQuartoCliente getReservaQuartoCliente() {
        return reservaQuartoCliente;
    }

    public void setReservaQuartoCliente(ReservaQuartoCliente reservaQuartoCliente) {
        this.reservaQuartoCliente = reservaQuartoCliente;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CompraServico other = (CompraServico) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
