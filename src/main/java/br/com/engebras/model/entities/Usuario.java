package br.com.engebras.model.entities;

/**
 * @author Adalberto
 * dt. criação: 31/05/2016
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
@Table(name="usuario")
public class Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nr_codigo", length=6)
    private Integer nr_codigo; 
    @Column(name="dc_nomeUsuario", nullable=false, length=30)
    private String dc_nomeUsuario; 
    @Column(name="dc_login", nullable=false, length=25)
    private String dc_login; 
    @Column(name="dc_senha", nullable=false, length=10)
    private String dc_senha; 
    @Column(name="nr_nivel", nullable=false, length=2)
    private Integer nr_nivel; 
    @Column(name="dc_cargo", nullable=false, length=30)
    private String dc_cargo; 
    @Column(name="nr_codFilial", nullable=false, length=5)
    private Integer nr_codFilial;
    @Column(name="lg_ativo", nullable=false, length=1)
    private Integer lg_ativo; 

    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }

    public String getDc_nomeUsuario() {
        return dc_nomeUsuario;
    }

    public void setDc_nomeUsuario(String dc_nomeUsuario) {
        this.dc_nomeUsuario = dc_nomeUsuario;
    }


    public String getDc_login() {
        return dc_login;
    }

    public void setDc_login(String dc_login) {
        this.dc_login = dc_login;
    }

    public String getDc_senha() {
        return dc_senha;
    }

    public void setDc_senha(String dc_senha) {
        this.dc_senha = dc_senha;
    }

    public Integer getNr_nivel() {
        return nr_nivel;
    }

    public void setNr_nivel(Integer nr_nivel) {
        this.nr_nivel = nr_nivel;
    }

    public String getDc_cargo() {
        return dc_cargo;
    }

    public void setDc_cargo(String dc_cargo) {
        this.dc_cargo = dc_cargo;
    }

    public Integer getNr_codFilial() {
        return nr_codFilial;
    }

    public void setNr_codFilial(Integer nr_codFilial) {
        this.nr_codFilial = nr_codFilial;
    }

    public Integer getLg_ativo() {
        return lg_ativo;
    }

    public void setLg_ativo(Integer lg_ativo) {
        this.lg_ativo = lg_ativo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.nr_codigo);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }
    
    
}
