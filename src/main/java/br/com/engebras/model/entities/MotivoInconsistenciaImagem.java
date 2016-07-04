package br.com.engebras.model.entities;

/**
 * @author Adalberto Kamida
 * dt. criação: 29/06/2016
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
@Table(name="motivoInconsistenciaImagem")
public class MotivoInconsistenciaImagem implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    @Id
    @Column(name="nr_codigo", length=2)
    private Integer nr_codigo; 
    @Column(name="dc_inconsistencia", length=100)
    private String dc_inconsistencia;
    @Column(name="lg_56732",  length=1)
    private Integer lg_56732;
    @Column(name="lg_56810", length=1)
    private Integer lg_56810;
    @Column(name="lg_56900", length=1)
    private Integer lg_56900; 
    @Column(name="lg_57030", length=1)
    private Integer lg_57030; 
    @Column(name="lg_57461", length=1)
    private Integer lg_57461; 
    @Column(name="lg_57462", length=1)
    private Integer lg_57462; 
    @Column(name="lg_57463", length=1)
    private Integer lg_57463;
    @Column(name="lg_60411", length=1)
    private Integer lg_60411; 
    @Column(name="lg_60412", length=1)
    private Integer lg_60412;
    @Column(name="lg_60503", length=1)
    private Integer lg_60503;
    @Column(name="lg_74550", length=1)
    private Integer lg_74550;
    @Column(name="lg_74630", length=1)
    private Integer lg_74630;
    @Column(name="lg_74710", length=1)
    private Integer lg_74710; 
    @Column(name="lg_57461M", length=1)
    private Integer lg_57461M; 

    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }

    public String getDc_inconsistencia() {
        return dc_inconsistencia;
    }

    public void setDc_inconsistencia(String dc_inconsistencia) {
        this.dc_inconsistencia = dc_inconsistencia;
    }

    public Integer getLg_56732() {
        return lg_56732;
    }

    public void setLg_56732(Integer lg_56732) {
        this.lg_56732 = lg_56732;
    }

    public Integer getLg_56810() {
        return lg_56810;
    }

    public void setLg_56810(Integer lg_56810) {
        this.lg_56810 = lg_56810;
    }

    public Integer getLg_56900() {
        return lg_56900;
    }

    public void setLg_56900(Integer lg_56900) {
        this.lg_56900 = lg_56900;
    }

    public Integer getLg_57030() {
        return lg_57030;
    }

    public void setLg_57030(Integer lg_57030) {
        this.lg_57030 = lg_57030;
    }

    public Integer getLg_57461() {
        return lg_57461;
    }

    public void setLg_57461(Integer lg_57461) {
        this.lg_57461 = lg_57461;
    }

    public Integer getLg_57462() {
        return lg_57462;
    }

    public void setLg_57462(Integer lg_57462) {
        this.lg_57462 = lg_57462;
    }

    public Integer getLg_57463() {
        return lg_57463;
    }

    public void setLg_57463(Integer lg_57463) {
        this.lg_57463 = lg_57463;
    }

    public Integer getLg_60411() {
        return lg_60411;
    }

    public void setLg_60411(Integer lg_60411) {
        this.lg_60411 = lg_60411;
    }

    public Integer getLg_60412() {
        return lg_60412;
    }

    public void setLg_60412(Integer lg_60412) {
        this.lg_60412 = lg_60412;
    }

    public Integer getLg_60503() {
        return lg_60503;
    }

    public void setLg_60503(Integer lg_60503) {
        this.lg_60503 = lg_60503;
    }

    public Integer getLg_74550() {
        return lg_74550;
    }

    public void setLg_74550(Integer lg_74550) {
        this.lg_74550 = lg_74550;
    }

    public Integer getLg_74630() {
        return lg_74630;
    }

    public void setLg_74630(Integer lg_74630) {
        this.lg_74630 = lg_74630;
    }

    public Integer getLg_74710() {
        return lg_74710;
    }

    public void setLg_74710(Integer lg_74710) {
        this.lg_74710 = lg_74710;
    }

    public Integer getLg_57461M() {
        return lg_57461M;
    }

    public void setLg_57461M(Integer lg_57461M) {
        this.lg_57461M = lg_57461M;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nr_codigo);
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
        final MotivoInconsistenciaImagem other = (MotivoInconsistenciaImagem) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }

    
    
    
}
