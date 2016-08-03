/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 01/08/2016
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
import br.com.engebras.model.entities.RestricaoFxExclusiva;
import br.com.engebras.model.entities.LocalInfracao;

@ManagedBean(name = "mbRestricaoFxExclusiva")
@SessionScoped
public class MbRestricaoFxExclusiva implements Serializable {

    private static final long serialVersionUID = 1L;
    private RestricaoFxExclusiva restricaoFxExclusiva = new RestricaoFxExclusiva();
    private List<RestricaoFxExclusiva> restricaoFxExclusivas;
    private List<LocalInfracao> LocalInfracoes;

    public MbRestricaoFxExclusiva() {
        geraListaLocalInfracoes();
    }

    private InterfaceDAO<RestricaoFxExclusiva> restricaoFxExclusivaDAO() {
        InterfaceDAO<RestricaoFxExclusiva> restricaoFxExclusivaDAO = new HibernateDAO<RestricaoFxExclusiva>(RestricaoFxExclusiva.class, FacesContextUtil.getRequestSession());
        return restricaoFxExclusivaDAO;
    }

    public String limpaRestricaoFxExclusiva() {
        restricaoFxExclusiva = new RestricaoFxExclusiva(); 
        return editRestricaoFxExclusiva();
    }

    public String editRestricaoFxExclusiva(){
        return "/restrict/cadRestricaoFxExclusiva.faces";
    }
    
    
    public String editarRestricaoFxExclusiva(Integer nr_codigo){
        this.restricaoFxExclusiva = porNr_codigo(nr_codigo);
        return editRestricaoFxExclusiva();
    }
    
    public void addRestricaoFxExclusiva(){
        if (verificaDuplicidade() == true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe uma restrição cadastrada com estes dados.",""));
        } else if(restricaoFxExclusiva.getNr_codigo() == null || restricaoFxExclusiva.getNr_codigo() == 0){
            insertRestricaoFxExclusiva();
        } else {
            updateRestricaoFxExclusiva();
        }
        limpaRestricaoFxExclusiva();
    }
    
    
    public void insertRestricaoFxExclusiva(){
        restricaoFxExclusivaDAO().save(restricaoFxExclusiva);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!!!",""));
    }

    public void updateRestricaoFxExclusiva(){
        restricaoFxExclusivaDAO().update(restricaoFxExclusiva);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efeturada com sucesso!!!",""));
    }
    
    private boolean verificaDuplicidade(){
                boolean vll_retorno = false; 
        String vlc_sql = ""; 
        List consRestricaoFxExclusiva; 
        
        Session session = FacesContextUtil.getRequestSession(); 
        vlc_sql = "select a.dc_codArea, a.nr_diaSemana, a.dc_horaIni, a.dc_horaFim, a.dt_inicio, a.dt_fim "; 
        vlc_sql += "from restricaoZmrc a ";
        vlc_sql += "where a.dc_codArea = '" + restricaoFxExclusiva.getNr_codLocal() + "' ";
        vlc_sql += "and a.nr_diaSemana = " + restricaoFxExclusiva.getNr_diaSemana() + " ";
        vlc_sql += "and a.dt_inicio = '" + restricaoFxExclusiva.getDt_inicio() + "' ";
        vlc_sql += "and a.dt_fim = '" + restricaoFxExclusiva.getDt_fim() + "' ";
        vlc_sql += "and a.dc_horaIni = '" + restricaoFxExclusiva.getDc_horaIni() + "' ";
        vlc_sql += "and a.dc_horaFim = '" + restricaoFxExclusiva.getDc_horaFim() + "' ";
        if (restricaoFxExclusiva.getNr_codigo() != null && restricaoFxExclusiva.getNr_codigo() != 0){
            vlc_sql += "and a.nr_codigo <> " + restricaoFxExclusiva.getNr_codigo();
        }
        
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consRestricaoFxExclusiva = query.list();

        if (consRestricaoFxExclusiva.size() > 0) {
            vll_retorno = true;
        } else {
            vll_retorno = false;
        }

        for (Object oFilial : consRestricaoFxExclusiva) {
            Map row = (Map) oFilial;
        }

        consRestricaoFxExclusiva = null;

        return vll_retorno;

    }
    
     public RestricaoFxExclusiva porNr_codigo(Integer nr_codigo) {

        return restricaoFxExclusivaDAO().getEntity(nr_codigo);
    }

    public void geraListaLocalInfracoes(){
        List listaSQL; 
        String vlc_sql; 
        vlc_sql = "select a.nr_codigo, concat(a.nr_codigo,'-',a.dc_local) as dc_local from localInfracao a order by a.nr_codigo";
        
        Session session = FacesContextUtil.getRequestSession();
        
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        listaSQL = query.list();
        
        this.LocalInfracoes = listaSQL; 
        listaSQL = null;
    } 
     
     
    public RestricaoFxExclusiva getRestricaoFxExclusiva() {
        return restricaoFxExclusiva;
    }

    public void setRestricaoFxExclusiva(RestricaoFxExclusiva restricaoFxExclusiva) {
        this.restricaoFxExclusiva = restricaoFxExclusiva;
    }

    public List<RestricaoFxExclusiva> getRestricaoFxExclusivas() {
        return restricaoFxExclusivas;
    }

    public void setRestricaoFxExclusivas(List<RestricaoFxExclusiva> restricaoFxExclusivas) {
        this.restricaoFxExclusivas = restricaoFxExclusivas;
    }

    public List<LocalInfracao> getLocalInfracoes() {
        return LocalInfracoes;
    }

    public void setLocalInfracoes(List<LocalInfracao> LocalInfracoes) {
        this.LocalInfracoes = LocalInfracoes;
    }
    
    
}
