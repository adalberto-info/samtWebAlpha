package br.com.engebras.filter;

/**
 *
 * @author Adalberto Kamida
 * dt. criacao: 02/11/2016
 */

import java.io.Serializable;

public class LocalClienteFilter implements Serializable{
    
    private static final long serialVersionUID = 1L; 
    
    private Integer nr_codLocal; 

    public Integer getNr_codLocal() {
        return nr_codLocal;
    }

    public void setNr_codLocal(Integer nr_codLocal) {
        this.nr_codLocal = nr_codLocal;
    }
    
    
}
