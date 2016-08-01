/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 01/08/2016
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
@Table(name="restricaoFxExclusiva")
public class RestricaoFxExclusiva implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nr_codigo", length=10)
    private Integer nr_codigo; 
    @Column(name="nr_codLocal", nullable=false, length=5)
    private Integer nr_codLocal;
    @Column(name="nr_diaSemana", nullable=false, length=1)
    private Integer nr_diaSemana; 
    @Column(name="dc_horaIni", nullable=false, length=8)
    private String dc_horaIni; 
    @Column(name="dc_horaFim", nullable=false, length=8)
    private String dc_horaFim; 
    @Column(name="dt_inicio", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dt_inicio; 
    @Column(name="dt_fim", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dt_fim;

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

    public Integer getNr_diaSemana() {
        return nr_diaSemana;
    }

    public void setNr_diaSemana(Integer nr_diaSemana) {
        this.nr_diaSemana = nr_diaSemana;
    }

    public String getDc_horaIni() {
        return dc_horaIni;
    }

    public void setDc_horaIni(String dc_horaIni) {
        this.dc_horaIni = dc_horaIni;
    }

    public String getDc_horaFim() {
        return dc_horaFim;
    }

    public void setDc_horaFim(String dc_horaFim) {
        this.dc_horaFim = dc_horaFim;
    }

    public Date getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(Date dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public Date getDt_fim() {
        return dt_fim;
    }

    public void setDt_fim(Date dt_fim) {
        this.dt_fim = dt_fim;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final RestricaoFxExclusiva other = (RestricaoFxExclusiva) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
