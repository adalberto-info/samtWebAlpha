/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 12/08/2016
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
import br.com.engebras.model.entities.LocalInfracao;

@ManagedBean(name = "mbLocalInfracao")
@SessionScoped
public class MbLocalInfracao implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private LocalInfracao localInfracao = new LocalInfracao();
    private List<LocalInfracao> localInfracoes; 
    
    
    public MbLocalInfracao(){
        
    }

    private InterfaceDAO<LocalInfracao> localInfracaoDAO(){
        InterfaceDAO<LocalInfracao> localInfracaoDAO = new HibernateDAO<LocalInfracao>(LocalInfracao.class, FacesContextUtil.getRequestSession());
        return localInfracaoDAO;
    }

    public String limpaLocalInfracao(){
        localInfracao = new LocalInfracao(); 
        return editLocalInfracao(); 
    }

    public String editLocalInfracao(){
        return "/restrict/cadLocalInfracao.xhtml";
    }
    
    public String editarLocalInfracao(Integer nr_codigo){
        this.localInfracao = porNr_codigo(nr_codigo);
        return editLocalInfracao(); 
    }
    
    public void addLocalInfracao() {
        if (verificaDuplicidade(localInfracao.getDc_local()) == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Já existe um local infração cadastrado com o código:" + localInfracao.getNr_codigo() + ".", ""));
        } else if (localInfracao.getNr_codigo() == null || localInfracao.getNr_codigo() == 0) {
            insertLocalInfracao();
        } else {
            updateLocalInfracao();
        }
        limpaLocalInfracao();
    }

    public void insertLocalInfracao() {
        localInfracaoDAO().save(localInfracao);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!!!", ""));
    }
    
    public void updateLocalInfracao() {
        localInfracaoDAO().update(localInfracao);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!!!", ""));
    }

    private boolean verificaDuplicidade(String dc_local) {
        boolean vll_retorno = false;
        String vlc_sql = "";
        List consLocalInfracao;

        Session session = FacesContextUtil.getRequestSession();
        vlc_sql = "select a.nr_codigo, a.dc_local  from localInfracao a where a.dc_local = '" + dc_local + "' ";
        if (localInfracao.getNr_codigo() != null && localInfracao.getNr_codigo() != 0)
            vlc_sql = vlc_sql + "and a.nr_codigo <> " + localInfracao.getNr_codigo();
        
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consLocalInfracao = query.list();

        if (consLocalInfracao.size() > 0) {
            vll_retorno = true;
        } else {
            vll_retorno = false;
        }

        for (Object oLocalInfracao : consLocalInfracao) {
            Map row = (Map) oLocalInfracao;
        }

        consLocalInfracao = null;

        return vll_retorno;
    }

    public LocalInfracao porNr_codigo(Integer nr_codigo) {

        return localInfracaoDAO().getEntity(nr_codigo);
    }

    public LocalInfracao getLocalInfracao() {
        return localInfracao;
    }

    public void setLocalInfracao(LocalInfracao localInfracao) {
        this.localInfracao = localInfracao;
    }

    public List<LocalInfracao> getLocalInfracoes() {
        return localInfracoes;
    }

    public void setLocalInfracoes(List<LocalInfracao> localInfracoes) {
        this.localInfracoes = localInfracoes;
    }

    

    
}
