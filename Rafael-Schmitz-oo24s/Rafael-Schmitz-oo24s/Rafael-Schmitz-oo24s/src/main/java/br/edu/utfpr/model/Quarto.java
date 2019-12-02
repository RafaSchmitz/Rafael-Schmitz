/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Rafa
 */
@Entity
public class Quarto implements AbstractModel<Serializable>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numQuarto", nullable = false)
    private Integer numQuarto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoQuarto tipoQuarto;

    @Column(name = "qtdCamas", nullable = false)
    private Integer qtdCamas;

    @Column(name = "qtdPessoas", nullable = false)
    private Integer qtdPessoas;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "quarto")
    private ReservaQuartoCliente reservaQuarto;
    
    public Quarto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumQuarto() {
        return numQuarto;
    }

    public void setNumQuarto(Integer numQuarto) {
        this.numQuarto = numQuarto;
    }

    public TipoQuarto getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(TipoQuarto tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public Integer getQtdCamas() {
        return qtdCamas;
    }

    public void setQtdCamas(Integer qtdCamas) {
        this.qtdCamas = qtdCamas;
    }

    public Integer getQtdPessoas() {
        return qtdPessoas;
    }

    public void setQtdPessoas(Integer qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
    }

    public ReservaQuartoCliente getReservaQuarto() {
        return reservaQuarto;
    }

    public void setReservaQuarto(ReservaQuartoCliente reservaQuarto) {
        this.reservaQuarto = reservaQuarto;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Quarto other = (Quarto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return numQuarto + " - " + tipoQuarto ;
    }
    
    

}
