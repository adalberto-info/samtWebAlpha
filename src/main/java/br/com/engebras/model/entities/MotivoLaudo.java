/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 07/07/2016
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
@Table(name="moivosLaudos")
public class MotivoLaudo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="nr_codigo", length=5)
    private Integer nr_codigo; 
    @Column(name="dc_motivoLaudo", length=30)
    private String dc_motivoLaudo; 

    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }

    public String getDc_motivoLaudo() {
        return dc_motivoLaudo;
    }

    public void setDc_motivoLaudo(String dc_motivoLaudo) {
        this.dc_motivoLaudo = dc_motivoLaudo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nr_codigo);
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
        final MotivoLaudo other = (MotivoLaudo) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }
    
    
    
    
        
}
