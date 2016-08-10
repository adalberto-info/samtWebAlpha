/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 10/08/2016
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
import br.com.engebras.model.entities.RestricaoRodizio;
import br.com.engebras.filter.RestricaoRodizioFilter;

@ManagedBean(name = "mbPesquisaRestricaoRodizio")
@ViewScoped
public class MbPesquisaRestricaoRodizio implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private EntityManager manager; 
    public RestricaoRodizioFilter filtro; 
    private List<RestricaoRodizio> restricaoRodizioFiltrados; 
    private RestricaoRodizio restricaoRodizio = new RestricaoRodizio(); 
    
    public MbPesquisaRestricaoRodizio(){
        filtro = new RestricaoRodizioFilter(); 
    }
    
    private InterfaceDAO<RestricaoRodizio> restricaoRodizioDAO(){
        InterfaceDAO<RestricaoRodizio> restricaoRodizioDAO = new HibernateDAO<RestricaoRodizio>(RestricaoRodizio.class, FacesContextUtil.getRequestSession());
        return restricaoRodizioDAO; 
    }
    
    public void deleteRestricaoRodizio(){
        restricaoRodizioDAO().remove(restricaoRodizio);
        pesquisar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!",""));
    }
    
    public void pesquisar(){
        restricaoRodizioFiltrados = filtrados(filtro);
    }
    
        public List<RestricaoRodizio> filtrados(RestricaoRodizioFilter filtro) {
        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(RestricaoRodizio.class);
        
        if (filtro.getNr_diaSemana() > 0){
            criteria.add(Restrictions.eq("nr_diaSemana",filtro.getNr_diaSemana()));
        }
     
        return criteria.addOrder(Order.asc("nr_diaSemana")).list();
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public RestricaoRodizioFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(RestricaoRodizioFilter filtro) {
        this.filtro = filtro;
    }

    public List<RestricaoRodizio> getRestricaoRodizioFiltrados() {
        return restricaoRodizioFiltrados;
    }

    public void setRestricaoRodizioFiltrados(List<RestricaoRodizio> restricaoRodizioFiltrados) {
        this.restricaoRodizioFiltrados = restricaoRodizioFiltrados;
    }

    public RestricaoRodizio getRestricaoRodizio() {
        return restricaoRodizio;
    }

    public void setRestricaoRodizio(RestricaoRodizio restricaoRodizio) {
        this.restricaoRodizio = restricaoRodizio;
    }

    
    
    
    
    
}
