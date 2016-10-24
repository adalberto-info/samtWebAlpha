/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 24/10/2016
 * 
 */
package br.com.engebras.filter;

import java.io.Serializable;

public class Local_equipamentoFilter implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    private Integer nr_codigo; 

    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }
    
    
    
}
