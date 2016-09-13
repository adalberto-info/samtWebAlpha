/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 13/09/2016
 * 
 */
package br.com.engebras.filter;

import java.io.Serializable;


public class LocalVelocidadeFilter implements Serializable{

    private static final long serialVersionUID = 1L; 
    
    private Integer nr_codLocal; 

    public Integer getNr_codLocal() {
        return nr_codLocal;
    }

    public void setNr_codLocal(Integer nr_codLocal) {
        this.nr_codLocal = nr_codLocal;
    }
    
    
}
