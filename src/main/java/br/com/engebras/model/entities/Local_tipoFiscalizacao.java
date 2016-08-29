/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 29/08/2016
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
@Table(name="local_tipoFiscalizacao")
public class Local_tipoFiscalizacao implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    @Id
    @Column(name="nr_codLocal", nullable=false, length=5)
    private Integer nr_codLocal; 
    @Id
    @Column(name="nr_codTipoFiscalizacao", nullable=false, length=10)
    private Integer nr_codTipoFiscalizacao; 

    public Integer getNr_codLocal() {
        return nr_codLocal;
    }

    public void setNr_codLocal(Integer nr_codLocal) {
        this.nr_codLocal = nr_codLocal;
    }

    public Integer getNr_codTipoFiscalizacao() {
        return nr_codTipoFiscalizacao;
    }

    public void setNr_codTipoFiscalizacao(Integer nr_codTipoFiscalizacao) {
        this.nr_codTipoFiscalizacao = nr_codTipoFiscalizacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nr_codLocal);
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
        final Local_tipoFiscalizacao other = (Local_tipoFiscalizacao) obj;
        if (!Objects.equals(this.nr_codLocal, other.nr_codLocal)) {
            return false;
        }
        return true;
    }
    
    
    
}
