/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 05/08/2016
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
import br.com.engebras.model.entities.RestricaoConversaoProibida;

@ManagedBean(name = "mbRestricaoConversaoProibida")
@SessionScoped
public class MbRestricaoConversaoProibida implements Serializable{

    private static final long serialVersionUID = 1L; 
    
    private RestricaoConversaoProibida restricaoConversaoProibida = new RestricaoConversaoProibida(); 
    
    private List<RestricaoConversaoProibida> restricaoConversaoProibidas; 
    
    
    public MbRestricaoConversaoProibida(){
    }
    
    private InterfaceDAO<RestricaoConversaoProibida> restricaoConversaoProibidaDAO(){
        InterfaceDAO<RestricaoConversaoProibida> restricaoConversaoProibidaDAO = new HibernateDAO<RestricaoConversaoProibida>(RestricaoConversaoProibida.class, FacesContextUtil.getRequestSession());
        
        return restricaoConversaoProibidaDAO; 
    }

    public String limpaRestricaoConversaoProibida(){
        restricaoConversaoProibida = new RestricaoConversaoProibida(); 
        return editRestricaoConversaoProibida(); 
    }
    
    public String editRestricaoConversaoProibida(){
        return "/restrict/cadRestricaoConversaoProibida.faces";
    }
    
    
    public String editarRestricaoConversaoProibida(Integer nr_codigo){
        restricaoConversaoProibida = porNr_codigo(nr_codigo); 
        return editRestricaoConversaoProibida(); 
        
    }

    public void addRestricaoConversaoProibida(){
        
        if (verificaDuplicidade() == true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe uma restrição de conversão proibida cadastrada com estas informações!",""));
        }else if (restricaoConversaoProibida.getNr_codigo() == null || restricaoConversaoProibida.getNr_codigo() == 0){
            insertRestricaoConversaoProibida(); 
        }else {
            updateRestricaoConversaoProibida(); 
        }
        
        limpaRestricaoConversaoProibida();
    }

    public void insertRestricaoConversaoProibida(){
        
        restricaoConversaoProibidaDAO().save(restricaoConversaoProibida);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!!!",""));
    }
    
    public void updateRestricaoConversaoProibida(){
        restricaoConversaoProibidaDAO().update(restricaoConversaoProibida);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!!!",""));
    }
    
    public void deleteRestricaoConversaoProibida(){
        restricaoConversaoProibidaDAO().remove(restricaoConversaoProibida);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!",""));
    }
    
    private boolean verificaDuplicidade() {
        boolean vll_retorno = false;
        String vlc_sql = "";
        List consRestricaoConversaoProibida;

        Session session = FacesContextUtil.getRequestSession();
        vlc_sql = "select a.nr_codLocal, a.nr_diaSemana, a.dt_inicio, a.dc_horaIni, a.dt_fim, a.dc_horaFim from restricaoConversaoProibida where ";
        vlc_sql += "a.nr_codLocal = " + restricaoConversaoProibida.getNr_codLocal() + " ";
        vlc_sql += "a.nr_diaSemana = " + restricaoConversaoProibida.getNr_diaSemana() + " ";
        vlc_sql += "a.dt_inicio = '" + restricaoConversaoProibida.getDt_inicio() + "' ";
        vlc_sql += "a.dt_fim = '" + restricaoConversaoProibida.getDt_fim() + " "; 
        vlc_sql += "a.dc_horaIni = '" + restricaoConversaoProibida.getDc_horaIni() + " "; 
        vlc_sql += "a.dc_horaFim = '" + restricaoConversaoProibida.getDc_horaFim() + " ";
        if (restricaoConversaoProibida.getNr_codigo() != null && restricaoConversaoProibida.getNr_codigo() != 0)
            vlc_sql = vlc_sql + "and a.nr_codigo <> " + restricaoConversaoProibida.getNr_codigo();
        
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consRestricaoConversaoProibida = query.list();

        if (consRestricaoConversaoProibida.size() > 0) {
            vll_retorno = true;
        } else {
            vll_retorno = false;
        }

        for (Object oFilial : consRestricaoConversaoProibida) {
            Map row = (Map) oFilial;
        }

        consRestricaoConversaoProibida = null;

        return vll_retorno;
    }

    public RestricaoConversaoProibida porNr_codigo(Integer nr_codigo) {

        return restricaoConversaoProibidaDAO().getEntity(nr_codigo);
    }

    public RestricaoConversaoProibida getRestricaoConversaoProibida() {
        return restricaoConversaoProibida;
    }

    public void setRestricaoConversaoProibida(RestricaoConversaoProibida restricaoConversaoProibida) {
        this.restricaoConversaoProibida = restricaoConversaoProibida;
    }

    public List<RestricaoConversaoProibida> getRestricaoConversaoProibidas() {
        return restricaoConversaoProibidas;
    }

    public void setRestricaoConversaoProibidas(List<RestricaoConversaoProibida> restricaoConversaoProibidas) {
        this.restricaoConversaoProibidas = restricaoConversaoProibidas;
    }
    
}
