/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.dao;

import br.edu.utfpr.model.ReservaQuartoCliente;
import java.time.LocalDate;
import javax.persistence.Query;

/**
 *
 * @author Rafa
 */
public class ReservaQuartoClienteDao extends GenericDao<ReservaQuartoCliente, Long>{

    public ReservaQuartoClienteDao() {
        super(ReservaQuartoCliente.class);
    }  
    
    public int verfDt(LocalDate dtIni, LocalDate dtFim, Integer id) {
        
        Query query = em.createQuery("SELECT ReservaQuartoCliente.quarto_id "
                + "FROM ReservaQuartoCliente "
                + "WHERE dtreserva BETWEEN :dtIni and :dtFim"
                + " and ReservaQuartoCliente.quarto_id = :id");
        
        query.setParameter("id", id);
        query.setParameter("dtIni", dtIni);
        query.setParameter("dtFim", dtFim);

        return ((Number)query.getSingleResult()).intValue();
    }
}
