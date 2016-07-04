package br.com.engebras.model.entities;

/**
 * @author Adalberto dt. criação: 23/06/2016
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
@Table(name = "grupoAutuador")
public class GrupoAutuador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nr_codigo", length = 5)
    private Integer nr_codigo;
    @Column(name = "dc_codigo", length = 2)
    private String dc_codigo;
    @Column(name = "nr_codEnquadramento", length = 5)
    private Integer nr_codEnquadramento;
    @Column(name = "dc_descricao", length = 40)
    private String dc_descricao;

    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }
    
    public String getDc_codigo() {
        return dc_codigo;
    }

    public void setDc_codigo(String dc_codigo) {
        this.dc_codigo = dc_codigo;
    }

    public Integer getNr_codEnquadramento() {
        return nr_codEnquadramento;
    }

    public void setNr_codEnquadramento(Integer nr_codEnquadramento) {
        this.nr_codEnquadramento = nr_codEnquadramento;
    }

    public String getDc_descricao() {
        return dc_descricao;
    }

    public void setDc_descricao(String dc_descricao) {
        this.dc_descricao = dc_descricao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final GrupoAutuador other = (GrupoAutuador) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }

    

}
