/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model;

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
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Rafa
 */
@Entity
public class Cliente implements AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "O campo nome deve ser preenchido.")
    @Column(name = "nome", length = 244, nullable = false)
    private String nome;

    @NotEmpty(message = "O campo cpf deve ser preenchido.")
    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    @NotEmpty(message = "O campo rg deve ser preenchido.")
    @Column(name = "rg", length = 9, nullable = false)
    private String rg;

    @NotEmpty(message = "O campo numero do passaporte deve ser preenchido.")
    @Column(name = "numPassaporte", length = 8, nullable = false)
    private String numPassaporte;

    @NotEmpty(message = "O campo endereço deve ser preenchido.")
    @Column(name = "endereco", length = 254, nullable = false)
    private String endereco;

    @NotEmpty(message = "O campo cep deve ser preenchido.")
    @Column(name = "cep", length = 15, nullable = false)
    private String cep;
    
    @NotEmpty(message = "O campo bairro deve ser preenchido.")
    @Column(name = "bairro", length = 254, nullable = false)
    private String bairro;
    
    @NotNull(message = "O campo cidade deve ser selecionado.")
    @ManyToOne
    @JoinColumn(name = "cidade_id", referencedColumnName = "id")
    private Cidade cidade;

    @NotEmpty(message = "O campo telefone deve ser preenchido.")
    @Column(name = "telefone", length = 254, nullable = false)
    private String telefone;

    @NotEmpty(message = "O campo email deve ser preenchido.")
    @Column(name = "email", length = 254, nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "cliente")
    private ReservaQuartoCliente reservaQuarto;

    public Cliente() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNumPassaporte() {
        return numPassaporte;
    }

    public void setNumPassaporte(String numPassaporte) {
        this.numPassaporte = numPassaporte;
    }

    public ReservaQuartoCliente getReservaQuarto() {
        return reservaQuarto;
    }

    public void setReservaQuarto(ReservaQuartoCliente reservaQuarto) {
        this.reservaQuarto = reservaQuarto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    

}
