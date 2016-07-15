/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 14/07/2016
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
import java.util.Date;

import br.com.engebras.util.FacesContextUtil;
import br.com.engebras.util.HibernateUtil;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.entities.Feriado;

@ManagedBean(name="mbFeriado")
@SessionScoped
public class MbFeriado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Feriado feriado = new Feriado(); 
    private List<Feriado> feriados; 
    
    public MbFeriado(){
        
    }

    private InterfaceDAO<Feriado> feriadoDAO(){
        InterfaceDAO<Feriado> feriadoDAO = new HibernateDAO<Feriado>(Feriado.class, FacesContextUtil.getRequestSession());

        return feriadoDAO; 
    }

    public String limpaFeriado(){
        feriado = new Feriado(); 
        return editFeriado();
    }
    
    public String editFeriado(){
        return "/restrict/cadFeriado.faces";
    }
    
    public String editarFeriado(Integer nr_codigo){
        feriado = porNr_codigo(nr_codigo); 
        return editFeriado(); 
    }
    
    public void addFeriado(){
        if (verificaDuplicidade(feriado.getDt_feriado()) == true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe um feriado na data " + feriado.getDt_feriado() + ".",""));
        } else if(feriado.getNr_codigo() != null && feriado.getNr_codigo() != 0){
            insertFeriado();
        } else {
            updateFeriado(); 
        }
        limpaFeriado();
    }
    
    public void insertFeriado(){
        feriadoDAO().save(feriado);
    }
    

    public void updateFeriado(){
        feriadoDAO().update(feriado);
    }
    
    private boolean verificaDuplicidade(Date dt_feriado){
        boolean vll_retorno = false; 
        String vlc_sql = ""; 
        List consFeriado;
        
        Session session = FacesContextUtil.getRequestSession();
        vlc_sql = "select a.dt_feriado from feriado a where dt_feriado = '" + dt_feriado + "' "; 
        if (feriado.getNr_codigo() != null && feriado.getNr_codigo() != 0){
            vlc_sql += "and a.nr_codigo <> " + feriado.getNr_codigo();
        }
        
        SQLQuery query = session.createSQLQuery(vlc_sql); 
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consFeriado = query.list();
        
        if (consFeriado.size() > 0){
            vll_retorno = true;
        } else {
            vll_retorno = false; 
        }

        consFeriado = null; 
        
        return vll_retorno; 
    }
    
    
    public Feriado porNr_codigo(Integer nr_codigo){
        
        return feriadoDAO().getEntity(nr_codigo);
    }

    public Feriado getFeriado() {
        return feriado;
    }

    public void setFeriado(Feriado feriado) {
        this.feriado = feriado;
    }

    public List<Feriado> getFeriados() {
        return feriados;
    }

    public void setFeriados(List<Feriado> feriados) {
        this.feriados = feriados;
    }
    
    
    
}
