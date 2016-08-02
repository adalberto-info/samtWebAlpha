/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 02/08/2016
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
@Table(name="localInfracao")
public class LocalInfracao implements Serializable{

    private static final long serialVersionUID = 1L; 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nr_codigo", length=5)
    private Integer nr_codigo; 
    @Column(name="dc_local", length=80)
    private String dc_local; 
    @Column(name="dc_bairro", length=30)
    private String dc_bairro; 
    @Column(name="nr_codMunicipio", length=5)
    private Integer nr_codMuncipio; 
    @Column(name="dc_uf", length=2)
    private String dc_uf; 
    @Column(name="lg_ativo", length=1)
    private Integer lg_ativo; 
    @Column(name="nr_qtdFaixas", length=1)
    private Integer nr_qtdFaixas; 
    @Column(name="nr_faixa1", length=1)
    private Integer nr_faixa1; 
    @Column(name="nr_faixa2", length=1)
    private Integer nr_faixa2; 
    @Column(name="dc_ladoFaixa1", length=1)
    private String dc_ladoFaixa1; 
    @Column(name="dc_ladoFaixa2", length=1)
    private String dc_ladoFaixa2; 
    @Column(name="dc_sentido", length=1)
    private String dc_sentido; 
    @Column(name="lg_velocidadeDifPorte", length=1)
    private Integer lg_velocidadeDifPorte; 
    @Column(name="dc_latitude", length=17)
    private String dc_latitude; 
    @Column(name="dc_longitude", length=17)
    private String dc_logitude; 
    @Column(name="dt_inicio")
    @Temporal(TemporalType.DATE)
    private Date dt_inicio; 
    @Column(name="dt_ultimaAtualizacao", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dt_ultimaAtualizacao; 
    @Column(name="nr_codStatus", nullable=false, length=2)
    private Integer nr_codStatus; 
    @Column(name="nr_codTipoFixacao", nullable=false, length=5)
    private Integer nr_codTipoFixacao; 

    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }

    public String getDc_local() {
        return dc_local;
    }

    public void setDc_local(String dc_local) {
        this.dc_local = dc_local;
    }

    public String getDc_bairro() {
        return dc_bairro;
    }

    public void setDc_bairro(String dc_bairro) {
        this.dc_bairro = dc_bairro;
    }

    public Integer getNr_codMuncipio() {
        return nr_codMuncipio;
    }

    public void setNr_codMuncipio(Integer nr_codMuncipio) {
        this.nr_codMuncipio = nr_codMuncipio;
    }

    public String getDc_uf() {
        return dc_uf;
    }

    public void setDc_uf(String dc_uf) {
        this.dc_uf = dc_uf;
    }

    public Integer getLg_ativo() {
        return lg_ativo;
    }

    public void setLg_ativo(Integer lg_ativo) {
        this.lg_ativo = lg_ativo;
    }

    public Integer getNr_qtdFaixas() {
        return nr_qtdFaixas;
    }

    public void setNr_qtdFaixas(Integer nr_qtdFaixas) {
        this.nr_qtdFaixas = nr_qtdFaixas;
    }

    public Integer getNr_faixa1() {
        return nr_faixa1;
    }

    public void setNr_faixa1(Integer nr_faixa1) {
        this.nr_faixa1 = nr_faixa1;
    }

    public Integer getNr_faixa2() {
        return nr_faixa2;
    }

    public void setNr_faixa2(Integer nr_faixa2) {
        this.nr_faixa2 = nr_faixa2;
    }

    public String getDc_ladoFaixa1() {
        return dc_ladoFaixa1;
    }

    public void setDc_ladoFaixa1(String dc_ladoFaixa1) {
        this.dc_ladoFaixa1 = dc_ladoFaixa1;
    }

    public String getDc_ladoFaixa2() {
        return dc_ladoFaixa2;
    }

    public void setDc_ladoFaixa2(String dc_ladoFaixa2) {
        this.dc_ladoFaixa2 = dc_ladoFaixa2;
    }

    public String getDc_sentido() {
        return dc_sentido;
    }

    public void setDc_sentido(String dc_sentido) {
        this.dc_sentido = dc_sentido;
    }

    public Integer getLg_velocidadeDifPorte() {
        return lg_velocidadeDifPorte;
    }

    public void setLg_velocidadeDifPorte(Integer lg_velocidadeDifPorte) {
        this.lg_velocidadeDifPorte = lg_velocidadeDifPorte;
    }

    public String getDc_latitude() {
        return dc_latitude;
    }

    public void setDc_latitude(String dc_latitude) {
        this.dc_latitude = dc_latitude;
    }

    public String getDc_logitude() {
        return dc_logitude;
    }

    public void setDc_logitude(String dc_logitude) {
        this.dc_logitude = dc_logitude;
    }

    public Date getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(Date dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public Date getDt_ultimaAtualizacao() {
        return dt_ultimaAtualizacao;
    }

    public void setDt_ultimaAtualizacao(Date dt_ultimaAtualizacao) {
        this.dt_ultimaAtualizacao = dt_ultimaAtualizacao;
    }

    public Integer getNr_codStatus() {
        return nr_codStatus;
    }

    public void setNr_codStatus(Integer nr_codStatus) {
        this.nr_codStatus = nr_codStatus;
    }

    public Integer getNr_codTipoFixacao() {
        return nr_codTipoFixacao;
    }

    public void setNr_codTipoFixacao(Integer nr_codTipoFixacao) {
        this.nr_codTipoFixacao = nr_codTipoFixacao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.nr_codigo);
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
        final LocalInfracao other = (LocalInfracao) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
