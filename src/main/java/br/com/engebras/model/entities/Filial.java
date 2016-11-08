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


/**
 *
 * @author Adalberto
 * @dt. criação: 21/03/2016
 */
@Entity
@Table(name="filial")
public class Filial implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nr_codigo", length=5 )
    private Integer nr_codigo;
    @Column(name="dc_filial", nullable=false, length=30)    
    private String dc_filial;
    @Column(name="dc_responsavel", nullable=false, length=30)
    private String dc_responsavel;
    @Column(name="dc_endereco", nullable=false, length=30)
    private String dc_endereco;
    @Column(name="dc_numero", nullable=false, length=15)
    private String dc_numero;
    @Column(name="dc_bairro", nullable=false, length=25)
    private String dc_bairro; 
    @Column(name="dc_cidade", nullable=false, length=30)
    private String dc_cidade; 
    @Column(name="dc_uf", nullable=false, length=2)
    private String dc_uf;    
    @Column(name="dc_telefone", nullable=false, length=25)
    private String dc_telefone;

    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }

    public String getDc_filial() {
        return dc_filial;
    }

    public void setDc_filial(String dc_filial) {
        this.dc_filial = dc_filial;
    }

    public String getDc_responsavel() {
        return dc_responsavel;
    }

    public void setDc_responsavel(String dc_responsavel) {
        this.dc_responsavel = dc_responsavel;
    }

    public String getDc_endereco() {
        return dc_endereco;
    }

    public void setDc_endereco(String dc_endereco) {
        this.dc_endereco = dc_endereco;
    }

    public String getDc_numero() {
        return dc_numero;
    }

    public void setDc_numero(String dc_numero) {
        this.dc_numero = dc_numero;
    }

    public String getDc_bairro() {
        return dc_bairro;
    }

    public void setDc_bairro(String dc_bairro) {
        this.dc_bairro = dc_bairro;
    }

    public String getDc_cidade() {
        return dc_cidade;
    }

    public void setDc_cidade(String dc_cidade) {
        this.dc_cidade = dc_cidade;
    }

    public String getDc_uf() {
        return dc_uf;
    }

    public void setDc_uf(String dc_uf) {
        this.dc_uf = dc_uf;
    }

    public String getDc_telefone() {
        return dc_telefone;
    }

    public void setDc_telefone(String dc_telefone) {
        this.dc_telefone = dc_telefone;
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
        final Filial other = (Filial) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }
    
    
}