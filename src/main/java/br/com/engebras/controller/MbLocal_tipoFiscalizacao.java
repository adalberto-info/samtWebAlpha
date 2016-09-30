/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 30/09/2016
 * 
 */
package br.com.engebras.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.engebras.util.FacesContextUtil;
import br.com.engebras.util.HibernateUtil;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.entities.Local_tipoFiscalizacao;


@ManagedBean(name="mbLocal_tipoFiscalizacao")
@SessionScoped
public class MbLocal_tipoFiscalizacao implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private Local_tipoFiscalizacao local_tipoFiscalizacao = new Local_tipoFiscalizacao(); 
    private Integer vpn_nr_codLocal; 
    
    public MbLocal_tipoFiscalizacao(){
        
    }
    
    public void addLocal_tipoFiscalizacao(){
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Executei o metodo addLocal_tipoFiscalizacao...", ""));

    }

    public Local_tipoFiscalizacao getLocal_tipoFiscalizacao() {
        return local_tipoFiscalizacao;
    }

    public void setLocal_tipoFiscalizacao(Local_tipoFiscalizacao local_tipoFiscalizacao) {
        this.local_tipoFiscalizacao = local_tipoFiscalizacao;
    }

    public Integer getVpn_nr_codLocal() {
        return vpn_nr_codLocal;
    }

    public void setVpn_nr_codLocal(Integer vpn_nr_codLocal) {
        this.vpn_nr_codLocal = vpn_nr_codLocal;
    }
 
    
    
    
}
