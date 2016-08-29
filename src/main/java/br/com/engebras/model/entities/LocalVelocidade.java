/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 24/08/2016
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
@Table(name="localVelocidade")
public class LocalVelocidade implements Serializable {


    private static final long serialVersionUID = 1L; 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nr_codigo", length=10 )
    private Integer nr_codigo;
    @Column(name="nr_codLocal", nullable=false, length=5)
    private Integer nr_codLocal; 
    @Column(name="nr_velocidadePermitida", nullable=false, length=3)
    private Integer nr_velocidadePermitida; 
    @Column(name="nr_velocidadeConsiderada", nullable=false, precision=6, scale=1)
    private Float nr_velocidadeConsiderada; 
    @Column(name="nr_velocidadeTolerada", nullable=false, precision=6, scale=1)
    private Float nr_velocidadeTolerada; 
    @Column(name="nr_velocidadeGrave", nullable=false, length=3)
    private Integer nr_velocidadeGrave; 
    @Column(name="nr_velocidadeGravissima", nullable=false, length=3)
    private Integer nr_velocidadeGravissima; 
    @Column(name="nr_codPorteVeiculo", nullable=false, length=2)
    private Integer nr_codPorteVeiculo; 
    @Temporal(TemporalType.DATE)
    @Column(name="dt_inicio", nullable=false)
    private Date dt_inicio; 

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

    public Integer getNr_velocidadePermitida() {
        return nr_velocidadePermitida;
    }

    public void setNr_velocidadePermitida(Integer nr_velocidadePermitida) {
        this.nr_velocidadePermitida = nr_velocidadePermitida;
    }

    public Float getNr_velocidadeConsiderada() {
        return nr_velocidadeConsiderada;
    }

    public void setNr_velocidadeConsiderada(Float nr_velocidadeConsiderada) {
        this.nr_velocidadeConsiderada = nr_velocidadeConsiderada;
    }

    public Float getNr_velocidadeTolerada() {
        return nr_velocidadeTolerada;
    }

    public void setNr_velocidadeTolerada(Float nr_velocidadeTolerada) {
        this.nr_velocidadeTolerada = nr_velocidadeTolerada;
    }

    public Integer getNr_velocidadeGrave() {
        return nr_velocidadeGrave;
    }

    public void setNr_velocidadeGrave(Integer nr_velocidadeGrave) {
        this.nr_velocidadeGrave = nr_velocidadeGrave;
    }

    public Integer getNr_velocidadeGravissima() {
        return nr_velocidadeGravissima;
    }

    public void setNr_velocidadeGravissima(Integer nr_velocidadeGravissima) {
        this.nr_velocidadeGravissima = nr_velocidadeGravissima;
    }

    public Integer getNr_codPorteVeiculo() {
        return nr_codPorteVeiculo;
    }

    public void setNr_codPorteVeiculo(Integer nr_codPorteVeiculo) {
        this.nr_codPorteVeiculo = nr_codPorteVeiculo;
    }

    public Date getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(Date dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.nr_codigo);
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
        final LocalVelocidade other = (LocalVelocidade) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }
    
    
}
