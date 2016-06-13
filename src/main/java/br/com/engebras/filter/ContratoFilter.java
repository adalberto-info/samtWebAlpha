package br.com.engebras.filter;

/**
 * @author Adalberto
 * dt. criação: 13/06/2016
 */

import java.io.Serializable;

public class ContratoFilter implements Serializable {

    private static final long serialVersionUID = 1L;
    
    Integer nr_crd; 

    public Integer getNr_crd() {
        return nr_crd;
    }

    public void setNr_crd(Integer nr_crd) {
        this.nr_crd = nr_crd;
    }
    
    
}
