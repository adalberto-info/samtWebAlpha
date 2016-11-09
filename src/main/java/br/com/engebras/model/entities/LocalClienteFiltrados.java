/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 07/11/2016
 * 
 */
package br.com.engebras.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class LocalClienteFiltrados implements Serializable{
    
    private static final long serialVersionUID = 1L; 

    private Integer nr_codigo; 
    private Integer nr_codLocal; 
    private Integer nr_codLocalCliente; 
    private Integer nr_tipoFiscalizacao; 
    private String dc_infracao; 
    private Date dt_inclusao; 
    private Date dt_inicioVigencia; 
    private Integer nr_porteVeiculo;
    private String dc_tipoFiscalizacao; 

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

    public Date getDt_inicioVigencia() {
        return dt_inicioVigencia;
    }

    public void setDt_inicioVigencia(Date dt_inicioVigencia) {
        this.dt_inicioVigencia = dt_inicioVigencia;
    }

    public Integer getNr_porteVeiculo() {
        return nr_porteVeiculo;
    }

    public void setNr_porteVeiculo(Integer nr_porteVeiculo) {
        this.nr_porteVeiculo = nr_porteVeiculo;
    }

    public String getDc_tipoFiscalizacao() {
        return dc_tipoFiscalizacao;
    }

    public void setDc_tipoFiscalizacao(String dc_tipoFiscalizacao) {
        this.dc_tipoFiscalizacao = dc_tipoFiscalizacao;
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
        final LocalClienteFiltrados other = (LocalClienteFiltrados) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }
    
    
    
}
