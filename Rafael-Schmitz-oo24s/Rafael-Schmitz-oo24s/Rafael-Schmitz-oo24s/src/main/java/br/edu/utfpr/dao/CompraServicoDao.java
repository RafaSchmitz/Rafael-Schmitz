/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.dao;

import br.edu.utfpr.model.CompraServico;
import javax.persistence.Query;

/**
 *
 * @author Rafa
 */
public class CompraServicoDao extends GenericDao<CompraServico, Long> {

    public CompraServicoDao() {
        super(CompraServico.class);
    }

    public double sumValServ(Long id) {
        Query query = em.createQuery("SELECT sum(valor) "
                + "FROM CompraServico "
                + "WHERE reservaquartocliente_id = :id");
        query.setParameter("id", id);
        
        return ((Number)query.getSingleResult()).doubleValue();
    }
}
