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
@Table(name="situacaoLocal")
public class SituacaoLocal implements Serializable{

    private static final long serialVersionUID = 1L; 
    
    @Id 
    @Column(name="dc_codigo",nullable=false, length=3)
    private String dc_codigo; 
    @Column(name="nr_nivel", nullable=false, length=2)
    private Integer nr_nivel; 
    @Column(name="lg_problema", nullable=false, length=1)
    private Integer lg_problema; 

    public String getDc_codigo() {
        return dc_codigo;
    }

    public void setDc_codigo(String dc_codigo) {
        this.dc_codigo = dc_codigo;
    }

    public Integer getNr_nivel() {
        return nr_nivel;
    }

    public void setNr_nivel(Integer nr_nivel) {
        this.nr_nivel = nr_nivel;
    }

    public Integer getLg_problema() {
        return lg_problema;
    }

    public void setLg_problema(Integer lg_problema) {
        this.lg_problema = lg_problema;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.dc_codigo);
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
        final SituacaoLocal other = (SituacaoLocal) obj;
        if (!Objects.equals(this.dc_codigo, other.dc_codigo)) {
            return false;
        }
        return true;
    }
    
    
}
