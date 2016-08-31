/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 31/08/2016
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
@Table(name="grupoLocalItem")
public class GrupoLocalItem implements Serializable{

    private static final long serialVersionUID = 1L; 
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nr_codigo", length= 10)
    private Integer nr_codigo; 
    @Column(name="nr_codGrupoLocal", nullable=false, length=5)
    private Integer nr_codGrupoLocal;
    @Column(name="nr_codLocalInfracao", nullable=false, length=5)
    private Integer nr_codLocalInfracao; 

    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }

    public Integer getNr_codGrupoLocal() {
        return nr_codGrupoLocal;
    }

    public void setNr_codGrupoLocal(Integer nr_codGrupoLocal) {
        this.nr_codGrupoLocal = nr_codGrupoLocal;
    }

    public Integer getNr_codLocalInfracao() {
        return nr_codLocalInfracao;
    }

    public void setNr_codLocalInfracao(Integer nr_codLocalInfracao) {
        this.nr_codLocalInfracao = nr_codLocalInfracao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.nr_codigo);
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
        final GrupoLocalItem other = (GrupoLocalItem) obj;
        if (!Objects.equals(this.nr_codigo, other.nr_codigo)) {
            return false;
        }
        return true;
    }
    
    
}
