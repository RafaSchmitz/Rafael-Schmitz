/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.dao;

import br.edu.utfpr.model.Contatos;
import java.io.Serializable;

/**
 *
 * @author Rafa
 */
public class ContatoDao extends GenericDao<Contatos, Long> {

    public ContatoDao() {
        super(Contatos.class);
    }

}