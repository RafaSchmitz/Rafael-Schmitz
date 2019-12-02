/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.dao;

import br.edu.utfpr.model.CompraProduto;
import java.io.Serializable;

/**
 *
 * @author Rafa
 */
public class CompraProdutoDao extends GenericDao<CompraProduto, Long>{

    public CompraProdutoDao() {
        super(CompraProduto.class);
    }
    
}
