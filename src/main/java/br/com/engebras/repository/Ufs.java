package br.com.engebras.repository;

import br.com.engebras.model.entities.Uf;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Adalberto
 * dt. criaçao: 28/04/2016
 */
public class Ufs implements Serializable{
    private static final long serialVersionUID = 1L; 
    
    @Inject
    private EntityManager manager;
    
    public Uf porDc_uf(String dc_uf){
        return manager.find(Uf.class, dc_uf);
    }
}
