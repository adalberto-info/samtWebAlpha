package br.com.engebras.filter;

/**
 * @author Adalberto
 * dt. criação: 28/06/2016
 */

import java.io.Serializable;


public class AreaFilter implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private String dc_codArea; 

    public String getDc_codArea() {
        return dc_codArea;
    }

    public void setDc_codArea(String dc_codArea) {
        this.dc_codArea = dc_codArea;
    }
    
    
}
