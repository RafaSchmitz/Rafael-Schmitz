/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Rafa
 */
@Entity
public class ReservaQuartoCliente implements AbstractModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "O campo cliente deve ser selecionado.")
    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @NotNull(message = "O campo quarto deve ser selecionado.")
    @OneToOne
    @JoinColumn(name = "quarto_id", referencedColumnName = "id")
    private Quarto quarto;
    
    @NotNull(message = "O campo Data da reserva deve ser selecionado.")
    @Column(name = "DtReserva", nullable = false)
    private LocalDate dtReserva;
    
    @Column(name = "Motivo", nullable = true)
    private String motivo;
    
    @Column(name = "hospedes", nullable = true)
    private Integer hospedes;

    @NotNull(message = "O campo CheckIn de camas deve ser selecionado.")
    @Column(name = "CheckIn", nullable = true)
    private LocalDate dtIni;

    @Column(name = "CheckOut", nullable = true)
    private LocalDate dtFim;

    @DecimalMin(value = "0.01", 
            message = "O valor deve ser maior que R$ 0.00.")
    @Column(name = "VlrDiaria", nullable = false)
    private Double vlrDiaria;
    
    @OneToMany(mappedBy = "reservaQuartoCliente", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<CompraProduto> compraProdutos;
    
    @OneToMany(mappedBy = "reservaQuartoCliente", cascade = CascadeType.ALL)
    private List<CompraServico> compraServicos;
    
    @OneToMany(mappedBy = "reservaQuartoCliente", cascade = CascadeType.ALL)
    private List<Usuario> usuario;
        

    public ReservaQuartoCliente() {
    }

    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }

    public Integer getHospedes() {
        return hospedes;
    }

    public void setHospedes(Integer hospedes) {
        this.hospedes = hospedes;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDtIni() {
        return dtIni;
    }

    public void setDtIni(LocalDate dtIni) {
        this.dtIni = dtIni;
    }

    public LocalDate getDtFim() {
        return dtFim;
    }

    public void setDtFim(LocalDate dtFim) {
        this.dtFim = dtFim;
    }

    public LocalDate getDtReserva() {
        return dtReserva;
    }

    public void setDtReserva(LocalDate dtReserva) {
        this.dtReserva = dtReserva;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Double getVlrDiaria() {
        return vlrDiaria;
    }

    public void setVlrDiaria(Double vlrDiaria) {
        this.vlrDiaria = vlrDiaria;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public List<CompraProduto> getCompraProdutos() {
        return compraProdutos;
    }

    public void setCompraProdutos(List<CompraProduto> compraProdutos) {
        this.compraProdutos = compraProdutos;
    }

    public List<CompraServico> getCompraServicos() {
        return compraServicos;
    }

    public void setCompraServicos(List<CompraServico> compraServicos) {
        this.compraServicos = compraServicos;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final ReservaQuartoCliente other = (ReservaQuartoCliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    

  

}
