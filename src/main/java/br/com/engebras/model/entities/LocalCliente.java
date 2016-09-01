/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 01/09/2016
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
@Table(name = "localCliente")
public class LocalCliente implements Serializable{

    private static final long serialVersionUID = 1L; 

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nr_codigo", length=10)
    private Integer nr_codigo; 
    @Column(name="nr_codLocal", nullable=false, length=5)
    private Integer nr_codLocal; 
    @Column(name="nr_codLocalCliente", nullable=false, length=10)
    private Integer nr_codLocalCliente; 
    @Column(name="nr_tipoFiscalizacao", nullable=false, length=2)
    private Integer nr_tipoFiscalizacao; 
    @Column(name="dc_infracao", nullable=false, length=30)
    private String dc_infracao; 
    @Temporal(TemporalType.DATE)
    @Column(name="dt_inclusao", nullable=false)
    private Date dt_inclusao; 
    @Temporal(TemporalType.DATE)
    @Column(name="dt_inicioVigencia", nullable=false)
    private Date dt_inciioVigencia; 
    @Column(name="nr_porteVeiculo", nullable=false, length=1)
    private Integer nr_porteVeiculo;

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

    public Integer getNr_codLocalCliente() {
        return nr_codLocalCliente;
    }

    public void setNr_codLocalCliente(Integer nr_codLocalCliente) {
        this.nr_codLocalCliente = nr_codLocalCliente;
    }

    public Integer getNr_tipoFiscalizacao() {
        return nr_tipoFiscalizacao;
    }

    public void setNr_tipoFiscalizacao(Integer nr_tipoFiscalizacao) {
        this.nr_tipoFiscalizacao = nr_tipoFiscalizacao;
    }

    public String getDc_infracao() {
        return dc_infracao;
    }

    public void setDc_infracao(String dc_infracao) {
        this.dc_infracao = dc_infracao;
    }

    public Date getDt_inclusao() {
        return dt_inclusao;
    }

    public void setDt_inclusao(Date dt_inclusao) {
        this.dt_inclusao = dt_inclusao;
    }

    public Date getDt_inciioVigencia() {
        return dt_inciioVigencia;
    }

    public void setDt_inciioVigencia(Date dt_inciioVigencia) {
        this.dt_inciioVigencia = dt_inciioVigencia;
    }

    public Integer getNr_porteVeiculo() {
        return nr_porteVeiculo;
    }

    public void setNr_porteVeiculo(Integer nr_porteVeiculo) {
        this.nr_porteVeiculo = nr_porteVeiculo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.nr_codigo);
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
        final LocalCliente other = (LocalCliente) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }
    
    
    
}
