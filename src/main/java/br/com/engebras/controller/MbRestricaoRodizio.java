/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 09/08/2016
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
import br.com.engebras.model.entities.RestricaoRodizio;
import br.com.engebras.model.entities.LocalInfracao;

@ManagedBean(name = "mbRestricaoRodizio")
@SessionScoped
public class MbRestricaoRodizio implements Serializable {

    private static final long SerialVersionUID = 1L; 
    
    private RestricaoRodizio restricaoRodizio = new RestricaoRodizio(); 
    private List<RestricaoRodizio> restricaoRodizios; 
    private List<LocalInfracao> localInfracoes;


    public MbRestricaoRodizio(){
        geraListaLocalInfracoes();
    }
    
    private InterfaceDAO<RestricaoRodizio> restricaoRodizioDAO(){
        InterfaceDAO<RestricaoRodizio> restricaoRodizioDAO = new HibernateDAO<RestricaoRodizio>(RestricaoRodizio.class, FacesContextUtil.getRequestSession());
        return restricaoRodizioDAO; 
    }
 
    public String limpaRestricaoRodizio(){
        restricaoRodizio = new RestricaoRodizio(); 
        return editRestricaoRodizio(); 
    }
    
    public String editRestricaoRodizio(){
        return "/restrict/cadRestricaoRodizio.faces";
    }
    
    public String editarRestricaoRodizio(Integer nr_codigo){
        restricaoRodizio = porNr_codigo(nr_codigo);
        return editRestricaoRodizio(); 
    }
    
    public void addRestricaoRodizio(){
        if (verificaDuplicidade() == true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe uma restrição de rodízio para o dia " + restricaoRodizio.getNr_diaSemana() + ".",""));
        } else if(restricaoRodizio.getNr_codigo() == null || restricaoRodizio.getNr_codigo() == 0){
            insertRestricaoRodizio();
        } else {
            updateRestricaoRodizio(); 
        }
        limpaRestricaoRodizio(); 
    }
    
    public void insertRestricaoRodizio(){
        restricaoRodizioDAO().save(restricaoRodizio);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!!!",""));
    }
    
    public void updateRestricaoRodizio(){
        restricaoRodizioDAO().update(restricaoRodizio);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!!!",""));
    }

    public void deleteRestricaoRodizio(){
        restricaoRodizioDAO().remove(restricaoRodizio);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!",""));

    }

    private boolean verificaDuplicidade() {
        boolean vll_retorno = false;
        String vlc_sql = "";
        List consRestricaoRodizio;

        Session session = FacesContextUtil.getRequestSession();
        vlc_sql = "select a.nr_codLocal, a.nr_diaSemana, a.dt_inicio, a.dc_horaIni, a.dt_fim, a.dc_horaFim from restricaoRodizio a where ";
        vlc_sql += "a.nr_codLocal = " + restricaoRodizio.getNr_codLocal() + " and ";
        vlc_sql += "a.nr_diaSemana = " + restricaoRodizio.getNr_diaSemana() + " and ";
        vlc_sql += "a.dt_inicio = '" + restricaoRodizio.getDt_inicio() + "' and ";
        vlc_sql += "a.dt_fim = '" + restricaoRodizio.getDt_fim() + "' and "; 
        vlc_sql += "a.dc_horaIni = '" + restricaoRodizio.getDc_horaIni() + "' and "; 
        vlc_sql += "a.dc_horaFim = '" + restricaoRodizio.getDc_horaFim() + "' ";
        if (restricaoRodizio.getNr_codigo() != null && restricaoRodizio.getNr_codigo() != 0)
            vlc_sql = vlc_sql + "and a.nr_codigo <> " + restricaoRodizio.getNr_codigo();
        
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consRestricaoRodizio = query.list();

        if (consRestricaoRodizio.size() > 0) {
            vll_retorno = true;
        } else {
            vll_retorno = false;
        }

        for (Object oFilial : consRestricaoRodizio) {
            Map row = (Map) oFilial;
        }

        consRestricaoRodizio = null;

        return vll_retorno;
    }


    public void geraListaLocalInfracoes(){
        List listaSQL; 
        String vlc_sql; 
        vlc_sql = "select a.nr_codigo, concat(a.nr_codigo,'-',a.dc_local) as dc_local from localInfracao a order by a.nr_codigo";
        
        Session session = FacesContextUtil.getRequestSession();
        
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        listaSQL = query.list();
        
        this.localInfracoes = listaSQL; 
        listaSQL = null;
    } 

        public RestricaoRodizio porNr_codigo(Integer nr_codigo) {

        return restricaoRodizioDAO().getEntity(nr_codigo);
    }

    public RestricaoRodizio getRestricaoRodizio() {
        return restricaoRodizio;
    }

    public void setRestricaoRodizio(RestricaoRodizio restricaoRodizio) {
        this.restricaoRodizio = restricaoRodizio;
    }

    public List<RestricaoRodizio> getRestricaoRodizios() {
        return restricaoRodizios;
    }

    public void setRestricaoRodizios(List<RestricaoRodizio> restricaoRodizios) {
        this.restricaoRodizios = restricaoRodizios;
    }

    public List<LocalInfracao> getLocalInfracoes() {
        return localInfracoes;
    }

    public void setLocalInfracoes(List<LocalInfracao> localInfracoes) {
        this.localInfracoes = localInfracoes;
    }

        
}
