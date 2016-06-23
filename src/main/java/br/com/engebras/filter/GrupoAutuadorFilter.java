package br.com.engebras.filter;

/**
 * @author Adalberto Kamida
 * dt. criação: 23/06/2016
 */

import java.io.Serializable;


public class GrupoAutuadorFilter implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private String dc_codigo;

    public String getDc_codigo() {
        return dc_codigo;
    }

    public void setDc_codigo(String dc_codigo) {
        this.dc_codigo = dc_codigo;
    }
    
    
    
    
}
