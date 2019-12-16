/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.dao;

import br.edu.utfpr.model.ReservaQuartoCliente;
import java.time.LocalDate;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Rafa
 */
public class ReservaQuartoClienteDao extends GenericDao<ReservaQuartoCliente, Long> {

    public ReservaQuartoClienteDao() {
        super(ReservaQuartoCliente.class);
    }

    public int verfDt(LocalDate dtIni, LocalDate dtFim, Integer id) {

        try {
            Query query = em.createQuery("SELECT r.quarto.id "
                    + "FROM ReservaQuartoCliente r "
                    + "WHERE r.dtReserva BETWEEN :dtIni and :dtFim"
                    + " and r.quarto.id = :id");

            query.setParameter("id", id);
            query.setParameter("dtIni", dtIni);
            query.setParameter("dtFim", dtFim);

            return ((Number) query.getSingleResult()).intValue();
        }catch(NoResultException nre){
            return 0;
        }
    }
}
