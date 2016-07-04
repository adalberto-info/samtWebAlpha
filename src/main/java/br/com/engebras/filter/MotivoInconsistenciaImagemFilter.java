package br.com.engebras.filter;

/**
 * @author Adalberto Kamida
 * dt. criacao: 30/06/2016
 */

import java.io.Serializable;

public class MotivoInconsistenciaImagemFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer nr_codigo;

    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }
    
}
