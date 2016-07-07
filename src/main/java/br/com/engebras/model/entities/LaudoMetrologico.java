/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 05/07/2016 
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
@Table(name="laudoMetrologico")
public class LaudoMetrologico implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nr_codigo", nullable=false, length=10)
    private Integer nr_codigo; 
    @Column(name="dc_serieEquipamento", nullable=false, length=15)
    private String dc_serieEquipamento; 
    @Column(name="dc_matriculaTecnico", nullable=false, length=10)
    private String dc_matriculaTecnico; 
    @Column(name="dc_nomeTecnico", nullable=false, length=30)
    private String dc_nomeTecnico; 
    @Column(name="dc_orgao", nullable=false, length=40)
    private String dc_orgao; 
    @Column(name="dc_numeroLaudo", nullable=false, length=16)
    private String dc_numeroLaudo; 
    @Column(name="dc_numeroLacre", nullable=false, length=17)
    private String dc_numeroLacre; 
    @Column(name="dc_numeroLacre2", nullable=false, length=17)
    private String dc_numeroLacre2;
    @Column(name="dt_verificacao", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dt_verificacao;
    @Column(name="dt_validade", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dt_validade; 
    @Column(name="nr_codMotivoLaudo", nullable=false, length=5)
    private Integer nr_codMotivoLaudo;
    @Column(name="dc_observacao" )
    private String dc_observacao; 

    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }

    public String getDc_serieEquipamento() {
        return dc_serieEquipamento;
    }

    public void setDc_serieEquipamento(String dc_serieEquipamento) {
        this.dc_serieEquipamento = dc_serieEquipamento;
    }

    public String getDc_matriculaTecnico() {
        return dc_matriculaTecnico;
    }

    public void setDc_matriculaTecnico(String dc_matriculaTecnico) {
        this.dc_matriculaTecnico = dc_matriculaTecnico;
    }

    public String getDc_nomeTecnico() {
        return dc_nomeTecnico;
    }

    public void setDc_nomeTecnico(String dc_nomeTecnico) {
        this.dc_nomeTecnico = dc_nomeTecnico;
    }

    public String getDc_orgao() {
        return dc_orgao;
    }

    public void setDc_orgao(String dc_orgao) {
        this.dc_orgao = dc_orgao;
    }

    public String getDc_numeroLaudo() {
        return dc_numeroLaudo;
    }

    public void setDc_numeroLaudo(String dc_numeroLaudo) {
        this.dc_numeroLaudo = dc_numeroLaudo;
    }

    public String getDc_numeroLacre() {
        return dc_numeroLacre;
    }

    public void setDc_numeroLacre(String dc_numeroLacre) {
        this.dc_numeroLacre = dc_numeroLacre;
    }

    public String getDc_numeroLacre2() {
        return dc_numeroLacre2;
    }

    public void setDc_numeroLacre2(String dc_numeroLacre2) {
        this.dc_numeroLacre2 = dc_numeroLacre2;
    }

    public Date getDt_verificacao() {
        return dt_verificacao;
    }

    public void setDt_verificacao(Date dt_verificacao) {
        this.dt_verificacao = dt_verificacao;
    }

    public Date getDt_validade() {
        return dt_validade;
    }

    public void setDt_validade(Date dt_validade) {
        this.dt_validade = dt_validade;
    }

    public Integer getNr_codMotivoLaudo() {
        return nr_codMotivoLaudo;
    }

    public void setNr_codMotivoLaudo(Integer nr_codMotivoLaudo) {
        this.nr_codMotivoLaudo = nr_codMotivoLaudo;
    }

    public String getDc_observacao() {
        return dc_observacao;
    }

    public void setDc_observacao(String dc_observacao) {
        this.dc_observacao = dc_observacao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final LaudoMetrologico other = (LaudoMetrologico) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
