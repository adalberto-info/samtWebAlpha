/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 12/07/2016
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
@Table(name="parametroSistema")
public class ParametroSistema implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="nr_codigo")
   private Integer nr_codigo; 
   @Column(name="dc_parametro", length=30)
   private String dc_parametro; 
   @Column(name="dc_tipo", length=1)
   private String dc_tipo; 
   @Column(name="dc_valor", length=250)
   private String dc_valor; 

    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }

    public String getDc_parametro() {
        return dc_parametro;
    }

    public void setDc_parametro(String dc_parametro) {
        this.dc_parametro = dc_parametro;
    }

    public String getDc_tipo() {
        return dc_tipo;
    }

    public void setDc_tipo(String dc_tipo) {
        this.dc_tipo = dc_tipo;
    }

    public String getDc_valor() {
        return dc_valor;
    }

    public void setDc_valor(String dc_valor) {
        this.dc_valor = dc_valor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final ParametroSistema other = (ParametroSistema) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }
   
   
   
    
}
