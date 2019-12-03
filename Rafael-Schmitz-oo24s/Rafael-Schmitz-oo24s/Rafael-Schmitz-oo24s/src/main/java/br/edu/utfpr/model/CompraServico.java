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

@Entity
public class CompraServico implements AbstractModel<Serializable>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double valor;
    
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
