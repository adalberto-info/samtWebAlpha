package br.com.engebras.model.entities;

/**
 * @author Adalberto
 * dt. criação: 17/06/2016
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
@Table(name="equipamento")
public class Equipamento implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nr_codigo", length=10)
    private Integer nr_codigo; 
    @Column(name="dc_serieEquipamento", length=15)
    private String dc_serieEquipamento; 
    @Column(name="dc_fabricante", length=20)
    private String dc_fabricante;
    @Column(name="dc_modelo", length=15)
    private String dc_modelo;
    @Column(name="dc_empresa", length=20)
    private String dc_empresa; 
    @Column(name="dc_status", length=1)
    private String dc_status; 

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

    public String getDc_fabricante() {
        return dc_fabricante;
    }

    public void setDc_fabricante(String dc_fabricante) {
        this.dc_fabricante = dc_fabricante;
    }

    public String getDc_modelo() {
        return dc_modelo;
    }

    public void setDc_modelo(String dc_modelo) {
        this.dc_modelo = dc_modelo;
    }

    public String getDc_empresa() {
        return dc_empresa;
    }

    public void setDc_empresa(String dc_empresa) {
        this.dc_empresa = dc_empresa;
    }

    public String getDc_status() {
        return dc_status;
    }

    public void setDc_status(String dc_status) {
        this.dc_status = dc_status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.nr_codigo);
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
        final Equipamento other = (Equipamento) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }
     
    
}
