/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 30/08/2016
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
@Table(name="local_equipamento")
public class Local_equipamento implements Serializable{

    private static final long serialVersionUID = 1L; 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nr_codigo", length=10)
    private Integer nr_codigo; 
    @Column(name="nr_codLocal", nullable=false, length=5)
    private Integer nr_codLocal; 
    @Column(name="dc_serieEquipamento", nullable=false, length=15)
    private String dc_serieEquipamento; 
    @Temporal(TemporalType.DATE)
    @Column(name="dt_inicio", nullable=false)
    private Date dt_inicio; 
    @Temporal(TemporalType.DATE)
    @Column(name="dt_fim", nullable=false)
    private Date dt_fim; 
    @Column(name="dc_hr_inicio", nullable=false)
    private String dc_hr_inicio; 
    @Column(name="dc_hr_fim", nullable=false)
    private String dc_hr_fim; 

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

    public String getDc_serieEquipamento() {
        return dc_serieEquipamento;
    }

    public void setDc_serieEquipamento(String dc_serieEquipamento) {
        this.dc_serieEquipamento = dc_serieEquipamento;
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

    public String getDc_hr_inicio() {
        return dc_hr_inicio;
    }

    public void setDc_hr_inicio(String dc_hr_inicio) {
        this.dc_hr_inicio = dc_hr_inicio;
    }

    public String getDc_hr_fim() {
        return dc_hr_fim;
    }

    public void setDc_hr_fim(String dc_hr_fim) {
        this.dc_hr_fim = dc_hr_fim;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.nr_codigo);
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
        final Local_equipamento other = (Local_equipamento) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }
    
    
    
}
