/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 31/08/2016
 * 
 */
package br.com.engebras.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="local_situacaoLocal")
public class Local_situacaoLocal implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nr_codigo", length=10)
    private Integer nr_codigo; 
    @Column(name="nr_codLocal", nullable=false, length=5 )
    private Integer nr_codLocal; 
    @Column(name="dc_codSituacao", nullable=false, length=3)
    private String dc_codSituacao; 

    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }

    public Integer getNr_codLocal() {
        return nr_codLocal;
    }

    public void setNr_codLocal(Integer nr_codLocal) {
        this.nr_codLocal = nr_codLocal;
    }

    public String getDc_codSituacao() {
        return dc_codSituacao;
    }

    public void setDc_codSituacao(String dc_codSituacao) {
        this.dc_codSituacao = dc_codSituacao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.nr_codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Local_situacaoLocal other = (Local_situacaoLocal) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
