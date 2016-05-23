package br.com.engebras.filter;

import java.io.Serializable;

/**
 * @author Adalberto
 * dt. criação: 20/05/2016
 */
public class FilialFilter implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private String dc_filial; 

    public String getDc_filial() {
        return dc_filial;
    }

    public void setDc_filial(String dc_filial) {
        this.dc_filial = dc_filial;
    }
    
    
}
