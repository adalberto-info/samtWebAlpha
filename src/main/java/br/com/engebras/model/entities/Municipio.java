package br.com.engebras.model.entities;

/**
 * @author Adalberto Kamida
 * dt. criação: 14/06/2016
 */

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
@Table(name="municipio")
public class Municipio implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    public Municipio(){
        
    }

    @Id
    @Column(name="nr_codigo", length=5)
    private Integer nr_codigo; 
    @Column(name="dc_uf", length=2)
    private String dc_uf; 
    @Column(name="dc_municipio", length=40)
    private String dc_municipio; 

    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }

    public String getDc_uf() {
        return dc_uf;
    }

    public void setDc_uf(String dc_uf) {
        this.dc_uf = dc_uf;
    }

    public String getDc_municipio() {
        return dc_municipio;
    }

    public void setDc_municipio(String dc_municipio) {
        this.dc_municipio = dc_municipio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.nr_codigo);
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
        final Municipio other = (Municipio) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }
    
    

    
}
