/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 02/08/2016
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
import br.com.engebras.model.entities.RestricaoFxExclusiva;
import br.com.engebras.filter.RestricaoFxExclusivaFilter;

@ManagedBean(name="mbPesquisaRestricaoFxExclusiva")
@ViewScoped
public class MbPesquisaRestricaoFxExclusiva implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private EntityManager manager; 
    private RestricaoFxExclusivaFilter filtro;
    private List<RestricaoFxExclusiva> restricaoFxExclusivaFiltradas;
    private RestricaoFxExclusiva restricaoFxExclusiva = new RestricaoFxExclusiva(); 

    
    public MbPesquisaRestricaoFxExclusiva(){
        filtro = new RestricaoFxExclusivaFilter(); 
    }
    
    private InterfaceDAO<RestricaoFxExclusiva> restricaoFxExclusivaDAO(){
        InterfaceDAO<RestricaoFxExclusiva> restricaoFxExclusivaDAO = new HibernateDAO<RestricaoFxExclusiva>(RestricaoFxExclusiva.class, FacesContextUtil.getRequestSession());
        return restricaoFxExclusivaDAO; 
    }
    
    public void deleteRestricaoFxExclusiva() {
        restricaoFxExclusivaDAO().remove(restricaoFxExclusiva);
        pesquisar(); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!", ""));
    }
    
    public void pesquisar(){
        restricaoFxExclusivaFiltradas = filtrados(filtro);
    }
    
    public List<RestricaoFxExclusiva> filtrados(RestricaoFxExclusivaFilter filtro){
        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(RestricaoFxExclusiva.class);
        
        if (filtro.getNr_diaSemana() > 0){
            criteria.add(Restrictions.eq("nr_diaSemana", filtro.getNr_diaSemana()));
        }
        
        return criteria.addOrder(Order.asc("nr_diaSemana")).list();
    
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public RestricaoFxExclusivaFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(RestricaoFxExclusivaFilter filtro) {
        this.filtro = filtro;
    }

    public List<RestricaoFxExclusiva> getRestricaoFxExclusivaFiltradas() {
        return restricaoFxExclusivaFiltradas;
    }

    public void setRestricaoFxExclusivaFiltradas(List<RestricaoFxExclusiva> restricaoFxExclusivaFiltradas) {
        this.restricaoFxExclusivaFiltradas = restricaoFxExclusivaFiltradas;
    }

    public RestricaoFxExclusiva getRestricaoFxExclusiva() {
        return restricaoFxExclusiva;
    }

    public void setRestricaoFxExclusiva(RestricaoFxExclusiva restricaoFxExclusiva) {
        this.restricaoFxExclusiva = restricaoFxExclusiva;
    }
    
    
}
