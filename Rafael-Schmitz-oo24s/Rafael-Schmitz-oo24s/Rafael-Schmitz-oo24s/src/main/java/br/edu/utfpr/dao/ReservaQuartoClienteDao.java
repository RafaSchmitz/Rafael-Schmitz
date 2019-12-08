/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.dao;

import br.edu.utfpr.model.ReservaQuartoCliente;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Rafa
 */
public class ReservaQuartoClienteDao extends GenericDao<ReservaQuartoCliente, Long>{

    public ReservaQuartoClienteDao() {
        super(ReservaQuartoCliente.class);
    }  
    
}
