package br.com.engebras.filter;

/**
 * @author Adalberto
 * dt. criação: 31/05/2016
 */

import java.io.Serializable;

public class UsuarioFilter implements Serializable{
    
    private static final long serialVersionUID = 1L; 
    
    private String dc_login; 

    public String getDc_login() {
        return dc_login;
    }

    public void setDc_login(String dc_login) {
        this.dc_login = dc_login;
    }
    
    
    
    
    
}
