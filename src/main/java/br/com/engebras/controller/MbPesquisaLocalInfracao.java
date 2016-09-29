/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 15/08/2016
 * 
 */
package br.com.engebras.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;

import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.util.FacesContextUtil;
import br.com.engebras.model.entities.LocalInfracao;
import br.com.engebras.filter.LocalInfracaoFilter;

@ManagedBean(name = "mbPesquisaLocalInfracao")
@ViewScoped
public class MbPesquisaLocalInfracao implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private EntityManager manager; 
    
    public LocalInfracaoFilter filtro; 
    private List<LocalInfracao> localInfracaoFiltrados; 
    private LocalInfracao localInfracao = new LocalInfracao(); 
    private LocalInfracao localInfracaoSelecionado;
    
    public MbPesquisaLocalInfracao(){
        filtro = new LocalInfracaoFilter();
    }
    
    private InterfaceDAO<LocalInfracao> localInfracaoDAO() {
        InterfaceDAO<LocalInfracao> localInfracaoDAO = new HibernateDAO<LocalInfracao>(LocalInfracao.class, FacesContextUtil.getRequestSession());
        return localInfracaoDAO; 
    }
    
    public void deleteLocalInfracao(){
        localInfracaoDAO().remove(localInfracao);
        pesquisar(); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!",""));
    }

    public void pesquisar(){
        localInfracaoFiltrados = filtrados(filtro);
    }
    
    public List<LocalInfracao> filtrados(LocalInfracaoFilter filtro){
        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(LocalInfracao.class);
        
        if (filtro.getNr_codigo() != null && filtro.getNr_codigo() >0 ){
            criteria.add(Restrictions.eq("nr_codigo", filtro.getNr_codigo()));
        }
        
        if (StringUtils.isNotBlank(filtro.getDc_local())){
            criteria.add(Restrictions.like("dc_local", filtro.getDc_local(), MatchMode.ANYWHERE));
        }
        
        return criteria.addOrder(Order.asc("nr_codigo")).list();
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public LocalInfracaoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(LocalInfracaoFilter filtro) {
        this.filtro = filtro;
    }

    public List<LocalInfracao> getLocalInfracaoFiltrados() {
        return localInfracaoFiltrados;
    }

    public void setLocalInfracaoFiltrados(List<LocalInfracao> localInfracaoFiltrados) {
        this.localInfracaoFiltrados = localInfracaoFiltrados;
    }

    public LocalInfracao getLocalInfracao() {
        return localInfracao;
    }

    public void setLocalInfracao(LocalInfracao localInfracao) {
        this.localInfracao = localInfracao;
    }

    public LocalInfracao getLocalInfracaoSelecionado() {
        return localInfracaoSelecionado;
    }

    public void setLocalInfracaoSelecionado(LocalInfracao localInfracaoSelecionado) {
        this.localInfracaoSelecionado = localInfracaoSelecionado;
    }
    
    
    
    
}
