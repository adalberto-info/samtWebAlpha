/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 14/07/2016
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
@Table(name="feriado")
public class Feriado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nr_codigo", length=10)
    private Integer nr_codigo;
    @Temporal(TemporalType.DATE)
    @Column(name="dt_feriado", nullable=false)
    private Date dt_feriado; 
    @Column(name="dc_feriado", nullable=false, length=30)
    private String dc_feriado; 

    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }

    public Date getDt_feriado() {
        return dt_feriado;
    }

    public void setDt_feriado(Date dt_feriado) {
        this.dt_feriado = dt_feriado;
    }

    public String getDc_feriado() {
        return dc_feriado;
    }

    public void setDc_feriado(String dc_feriado) {
        this.dc_feriado = dc_feriado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Feriado other = (Feriado) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }

    
    
}
