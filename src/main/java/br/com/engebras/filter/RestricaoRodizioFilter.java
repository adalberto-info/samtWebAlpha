/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 09/08/2016
 * 
 */
package br.com.engebras.filter;

import java.io.Serializable;

public class RestricaoRodizioFilter implements Serializable {


    private static final long serialVersionUID = 1L; 
    
    private Integer nr_diaSemana; 

    public Integer getNr_diaSemana() {
        return nr_diaSemana;
    }

    public void setNr_diaSemana(Integer nr_diaSemana) {
        this.nr_diaSemana = nr_diaSemana;
    }
    
    
    
}
