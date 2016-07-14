/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 14/07/2016
 * 
 */
package br.com.engebras.filter;

import java.io.Serializable;


public class FeriadoFilter implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private String dt_feriado;

    public String getDt_feriado() {
        return dt_feriado;
    }

    public void setDt_feriado(String dt_feriado) {
        this.dt_feriado = dt_feriado;
    }
    
    
    
}
