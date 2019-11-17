/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.dao;

import br.edu.utfpr.model.EnderecoCliente;
import java.io.Serializable;

/**
 *
 * @author Rafa
 */
public class EnderecoClienteDao extends GenericDao<EnderecoCliente, Integer>{

    public EnderecoClienteDao() {
        super(EnderecoCliente.class);
    }
  
}
