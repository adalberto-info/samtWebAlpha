package br.com.engebras.model.entities;

/**
 * @author Adalberto
 * dt. início: 07/06/2016
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
@Table(name="contrato")
public class Contrato implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    
    @Id
    @Column(name="nr_crd", nullable=false, length=6)
    private Integer nr_crd;
    @Column(name="nr_codFilial", nullable=false, length=5)
    private Integer nr_codFilial; 
    @Column(name="dc_contrato", nullable=false, length=30)
    private String dc_contrato; 
    @Column(name="dt_inicioContrato", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dt_inicioContrato; 
    @Column(name="dt_fimContrato", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dt_fimContrato; 
    @Column(name="dc_uf", nullable=false, length=2)
    private String dc_uf; 
    @Column(name="nr_codMunicipio", nullable=false, length=5)
    private Integer nr_codMunicipio; 
    @Column(name="dc_responsavel", nullable=false, length=25)
    private String dc_responsavel; 

    public Integer getNr_crd() {
        return nr_crd;
    }

    public void setNr_crd(Integer nr_crd) {
        this.nr_crd = nr_crd;
    }

    public Integer getNr_codFilial() {
        return nr_codFilial;
    }

    public void setNr_codFilial(Integer nr_codFilial) {
        this.nr_codFilial = nr_codFilial;
    }

    public String getDc_contrato() {
        return dc_contrato;
    }

    public void setDc_contrato(String dc_contrato) {
        this.dc_contrato = dc_contrato;
    }

    public Date getDt_inicioContrato() {
        return dt_inicioContrato;
    }

    public void setDt_inicioContrato(Date dt_inicioContrato) {
        this.dt_inicioContrato = dt_inicioContrato;
    }

    public Date getDt_fimContrato() {
        return dt_fimContrato;
    }

    public void setDt_fimContrato(Date dt_fimContrato) {
        this.dt_fimContrato = dt_fimContrato;
    }

    public String getDc_uf() {
        return dc_uf;
    }

    public void setDc_uf(String dc_uf) {
        this.dc_uf = dc_uf;
    }

    public Integer getNr_codMunicipio() {
        return nr_codMunicipio;
    }

    public void setNr_codMunicipio(Integer nr_codMunicipio) {
        this.nr_codMunicipio = nr_codMunicipio;
    }

    public String getDc_responsavel() {
        return dc_responsavel;
    }

    public void setDc_responsavel(String dc_responsavel) {
        this.dc_responsavel = dc_responsavel;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.nr_crd);
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
        final Contrato other = (Contrato) obj;
        if (!Objects.equals(this.nr_crd, other.nr_crd)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
