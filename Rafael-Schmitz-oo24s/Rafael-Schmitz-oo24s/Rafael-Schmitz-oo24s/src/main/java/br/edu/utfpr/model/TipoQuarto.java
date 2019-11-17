/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model;

/**
 *
 * @author Rafa
 */
public enum TipoQuarto {
    
    ECONOMICO(1),
    SUPERIOR(2),
    LUXO(3);
    
    private final Integer id;

    TipoQuarto(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public static TipoQuarto findById(Integer id) {
        for (TipoQuarto tipoQuarto : TipoQuarto.values()) {
            if (tipoQuarto.getId().equals(id)) return tipoQuarto;
        }
        throw new IllegalArgumentException("Tipo do quarto inválido!");
    }
    
}
