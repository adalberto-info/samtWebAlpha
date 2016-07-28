/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 22/07/2016 
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

import br.com.engebras.util.FacesContextUtil;
import br.com.engebras.util.HibernateUtil;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.entities.RestricaoZmrc;
import br.com.engebras.model.entities.Area;

@ManagedBean(name = "mbRestricaoZmrc")
@SessionScoped
public class MbRestricaoZmrc implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private RestricaoZmrc restricaoZmrc = new RestricaoZmrc(); 
    private List<RestricaoZmrc> restricaoZmrcs;
    private List<Area> areas = new ArrayList<>();
    
    public MbRestricaoZmrc(){
        geraListaAreas();
    }

    private InterfaceDAO<RestricaoZmrc> restricaoZmrcDAO() {
        InterfaceDAO<RestricaoZmrc> restricaoZmrcDAO = new HibernateDAO<RestricaoZmrc>(RestricaoZmrc.class, FacesContextUtil.getRequestSession());
        return restricaoZmrcDAO; 
    }
    
    public String limpaRestricaoZmrc(){
        restricaoZmrc = new RestricaoZmrc(); 
        return editRestricaoZmrc(); 
    }
    
    public String editRestricaoZmrc(){
        return "/restrict/cadRestricaoZmrc.faces";
    }
    
    public String editarRestricaoZmrc(Integer nr_codigo){
        this.restricaoZmrc = porNr_codigo(nr_codigo); 
        return editRestricaoZmrc();
    }
    
    
    public void addRestricaoZmrc(){
        if (verificaDuplicidade() == true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe uma restrição ZMRC para o dia" + restricaoZmrc.getNr_diaSemana() + " e hora inicial: " + restricaoZmrc.getDc_horaIni() + " e hora fim: " + restricaoZmrc.getDc_horaFim()+".","" ));
        }else if(restricaoZmrc.getNr_codigo() == null || restricaoZmrc.getNr_codigo() == 0){
            insertRestricaoZmrc(); 
        }else {
            updateRestricaoZmrc();
        }
        
        limpaRestricaoZmrc();
    }
    
    public void insertRestricaoZmrc(){
        restricaoZmrcDAO().save(restricaoZmrc);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!!!",""));
    }
    
    public void updateRestricaoZmrc(){
        restricaoZmrcDAO().update(restricaoZmrc);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização realizada com sucesso!!!",""));
    }
    
    private boolean verificaDuplicidade(){
        boolean vll_retorno = false; 
        String vlc_sql = ""; 
        List consRestricaoZmrc; 
        
        Session session = FacesContextUtil.getRequestSession(); 
        vlc_sql = "select a.dc_codArea, a.nr_diaSemana, a.dc_horaIni, a.dc_horaFim, a.dt_inicio, a.dt_fim "; 
        vlc_sql += "from restricaoZmrc a ";
        vlc_sql += "where a.dc_codArea = '" + restricaoZmrc.getDc_codArea() + "' ";
        vlc_sql += "and a.nr_diaSemana = " + restricaoZmrc.getNr_diaSemana() + " ";
        vlc_sql += "and a.dt_inicio = '" + restricaoZmrc.getDt_inicio() + "' ";
        vlc_sql += "and a.dt_fim = '" + restricaoZmrc.getDt_fim() + "' ";
        vlc_sql += "and a.dc_horaIni = '" + restricaoZmrc.getDc_horaIni() + "' ";
        vlc_sql += "and a.dc_horaFim = '" + restricaoZmrc.getDc_horaFim() + "' ";
        if (restricaoZmrc.getNr_codigo() != null && restricaoZmrc.getNr_codigo() != 0){
            vlc_sql += "and a.nr_codigo <> " + restricaoZmrc.getNr_codigo();
        }
        
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consRestricaoZmrc = query.list();

        if (consRestricaoZmrc.size() > 0) {
            vll_retorno = true;
        } else {
            vll_retorno = false;
        }

        for (Object oFilial : consRestricaoZmrc) {
            Map row = (Map) oFilial;
        }

        consRestricaoZmrc = null;

        return vll_retorno;
    }
    
    public RestricaoZmrc porNr_codigo(Integer nr_codigo) {

        return restricaoZmrcDAO().getEntity(nr_codigo);
    }

    public void geraListaAreas(){
        List listaSQL; 
        String vlc_sql; 
        
        vlc_sql = "select dc_codArea, dc_area from area order by dc_codArea";
        
        Session session = FacesContextUtil.getRequestSession();
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        listaSQL = query.list();
        
        areas = listaSQL;
        
    }
    
    public RestricaoZmrc getRestricaoZmrc() {
        return restricaoZmrc;
    }

    public void setRestricaoZmrc(RestricaoZmrc restricaoZmrc) {
        this.restricaoZmrc = restricaoZmrc;
    }

    public List<RestricaoZmrc> getRestricaoZmrcs() {
        return restricaoZmrcs;
    }

    public void setRestricaoZmrcs(List<RestricaoZmrc> restricaoZmrcs) {
        this.restricaoZmrcs = restricaoZmrcs;
    }
    
    
    
}
