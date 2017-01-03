/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 03/01/2017
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
@Table(name="veiculoMarcaCET")
public class VeiculoMarcaCET implements Serializable {
    
    private static final long serialVersionUID =1L; 
    
    @Id
    @Column(name="nr_codigo", nullable=false, length=5)
    private Integer nr_codigo; 
    @Column(name="nr_codigoDV", nullable=false, length=1)
    private Integer nr_codigoDV; 
    @Column(name="dc_marca", nullable=false, length=35)
    private String dc_marca; 

    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }

    public Integer getNr_codigoDV() {
        return nr_codigoDV;
    }

    public void setNr_codigoDV(Integer nr_codigoDV) {
        this.nr_codigoDV = nr_codigoDV;
    }

    public String getDc_marca() {
        return dc_marca;
    }

    public void setDc_marca(String dc_marca) {
        this.dc_marca = dc_marca;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.nr_codigo);
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
        final VeiculoMarcaCET other = (VeiculoMarcaCET) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
