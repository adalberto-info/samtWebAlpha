/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 12/08/2016
 * 
 */
package br.com.engebras.filter;

import java.io.Serializable;

public class LocalInfracaoFilter implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private Integer nr_codigo;
    private String dc_local;
    
    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }

    public String getDc_local() {
        return dc_local;
    }

    public void setDc_local(String dc_local) {
        this.dc_local = dc_local;
    }
    
    
    
}
