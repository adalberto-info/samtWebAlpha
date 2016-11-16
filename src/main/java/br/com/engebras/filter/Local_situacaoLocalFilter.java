/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 16/11/2016
 * 
 */
package br.com.engebras.filter;

import java.io.Serializable;

public class Local_situacaoLocalFilter implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private Integer nr_codLocal; 

    public Integer getNr_codLocal() {
        return nr_codLocal;
    }

    public void setNr_codLocal(Integer nr_codLocal) {
        this.nr_codLocal = nr_codLocal;
    }
    
    
    
}
