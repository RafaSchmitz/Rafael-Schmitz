/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.dao;

import br.edu.utfpr.model.Cliente;
import java.io.Serializable;

/**
 *
 * @author Rafa
 */
public class ClienteDao extends GenericDao<Cliente, Integer>{

    public ClienteDao() {
        super(Cliente.class);
    }
    
    
    
}
