package br.com.engebras.model.entities;

/**
 * @author Adalberto
 * dt. criação: 28/06/2016
 */


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import br.com.engebras.model.entities.Uf;

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
@Table(name="area")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="dc_codArea")
    private String dc_codArea; 
    @Column(name="dc_area")
    private String dc_area; 

    public String getDc_codArea() {
        return dc_codArea;
    }

    public void setDc_codArea(String dc_codArea) {
        this.dc_codArea = dc_codArea;
    }

    public String getDc_area() {
        return dc_area;
    }

    public void setDc_area(String dc_area) {
        this.dc_area = dc_area;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.dc_codArea);
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
        final Area other = (Area) obj;
        if (!Objects.equals(this.dc_codArea, other.dc_codArea)) {
            return false;
        }
        return true;
    }
    
}
