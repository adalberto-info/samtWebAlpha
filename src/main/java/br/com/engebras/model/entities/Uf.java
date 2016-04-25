package br.com.engebras.model.entities;

/**
 * @author Adalberto
 * 25/04/2016
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
@Table(name="uf")
public class Uf implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public Uf(){
    }

    public Uf(String dc_uf, String dc_descricao){
      this.dc_uf = dc_uf;
      this.dc_descricao = dc_descricao; 
    }
    
    @Id
    @Column(name="dc_uf", length=2)
    private String dc_uf; 
    @Column(name="dc_descricao", length=30)
    private String dc_descricao; 

    public String getDc_uf() {
        return dc_uf;
    }

    public void setDc_uf(String dc_uf) {
        this.dc_uf = dc_uf;
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
        hash = 37 * hash + Objects.hashCode(this.dc_uf);
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
        final Uf other = (Uf) obj;
        if (!Objects.equals(this.dc_uf, other.dc_uf)) {
            return false;
        }
        return true;
    }
    
    
}
