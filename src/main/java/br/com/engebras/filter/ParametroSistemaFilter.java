/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 12/07/2016
 * 
 */
package br.com.engebras.filter;

import java.io.Serializable;


public class ParametroSistemaFilter implements Serializable{

    private static final long serialVersionUID = 1L; 
    
    private String dc_parametro; 

    public String getDc_parametro() {
        return dc_parametro;
    }

    public void setDc_parametro(String dc_parametro) {
        this.dc_parametro = dc_parametro;
    }
    
    
    
}
