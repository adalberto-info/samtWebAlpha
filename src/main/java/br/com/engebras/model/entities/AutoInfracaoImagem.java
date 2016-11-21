/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 21/11/2016
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
@Table(name="autoInfracaoImagem")
public class AutoInfracaoImagem implements Serializable  {

    private static final long serialVersionUID = 1L; 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nr_codigo", length=10)
    private Integer nr_codigo;
    @Column(name="dc_nr_multa", nullable = false, length=18)
    private String dc_nr_multa; 
    @Column(name="nr_codTipoImagem", nullable=false, length=2)
    private Integer nr_codTipoImagem; 
    @Column(name="dc_nomeImagem", nullable=false, length=20)
    private String dc_nomeImagem; 
    @Column(name="nr_tempoImagem", nullable=false, precision=6, scale=2)
    private Float nr_tempoImagem; 

    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }

    public String getDc_nr_multa() {
        return dc_nr_multa;
    }

    public void setDc_nr_multa(String dc_nr_multa) {
        this.dc_nr_multa = dc_nr_multa;
    }

    public Integer getNr_codTipoImagem() {
        return nr_codTipoImagem;
    }

    public void setNr_codTipoImagem(Integer nr_codTipoImagem) {
        this.nr_codTipoImagem = nr_codTipoImagem;
    }

    public String getDc_nomeImagem() {
        return dc_nomeImagem;
    }

    public void setDc_nomeImagem(String dc_nomeImagem) {
        this.dc_nomeImagem = dc_nomeImagem;
    }

    public Float getNr_tempoImagem() {
        return nr_tempoImagem;
    }

    public void setNr_tempoImagem(Float nr_tempoImagem) {
        this.nr_tempoImagem = nr_tempoImagem;
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
        final AutoInfracaoImagem other = (AutoInfracaoImagem) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
