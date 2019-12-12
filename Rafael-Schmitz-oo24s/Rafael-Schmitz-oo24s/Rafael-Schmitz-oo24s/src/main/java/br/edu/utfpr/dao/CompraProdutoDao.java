/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.dao;

import br.edu.utfpr.model.CompraProduto;
import javax.persistence.Query;

/**
 *
 * @author Rafa
 */
public class CompraProdutoDao extends GenericDao<CompraProduto, Long>{

    public CompraProdutoDao() {
        super(CompraProduto.class);
    }
    
        public double sumValProd(Long id){
        Query query = em.createQuery("SELECT sum(valor) "
                + "FROM CompraProduto "
                + "WHERE reservaquartocliente_id = :id");
        query.setParameter("id", id);
        
        return ((Number)query.getSingleResult()).doubleValue();
        
    }
}
