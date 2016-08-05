/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 05/08/2016
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
import br.com.engebras.model.entities.RestricaoConversaoProibida;
import br.com.engebras.filter.RestricaoConversaoProibidaFilter;

@ManagedBean(name = "mbRestricaoConversaoProibida")
@ViewScoped
public class MbPesquisaRestricaoConversaoProibida implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private EntityManager manager; 
    public RestricaoConversaoProibidaFilter filtro; 
    private List<RestricaoConversaoProibida> restricaoConversaoProibidaFiltradas; 
    private RestricaoConversaoProibida restricaoConversaoProibida = new RestricaoConversaoProibida(); 
    
    public MbPesquisaRestricaoConversaoProibida(){
        filtro = new RestricaoConversaoProibidaFilter(); 
    }
    
    private InterfaceDAO<RestricaoConversaoProibida> restricaoConversaoProibidaDAO() {
        InterfaceDAO<RestricaoConversaoProibida> restricaoConversaoProibidaDAO = new HibernateDAO<RestricaoConversaoProibida>(RestricaoConversaoProibida.class, FacesContextUtil.getRequestSession());
        return restricaoConversaoProibidaDAO;
    }
    
    public void deleteRestricaoConversaoProibida() {
        restricaoConversaoProibidaDAO().remove(restricaoConversaoProibida);
        pesquisar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!", ""));
    }

    public void pesquisar() {
        restricaoConversaoProibidaFiltradas = filtrados(filtro);
    }

    public List<RestricaoConversaoProibida> filtrados(RestricaoConversaoProibidaFilter filtro) {
        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(RestricaoConversaoProibida.class);

        if (filtro.getNr_diaSemana() > 0) {
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

    public RestricaoConversaoProibidaFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(RestricaoConversaoProibidaFilter filtro) {
        this.filtro = filtro;
    }

    public List<RestricaoConversaoProibida> getRestricaoConversaoProibidaFiltradas() {
        return restricaoConversaoProibidaFiltradas;
    }

    public void setRestricaoConversaoProibidaFiltradas(List<RestricaoConversaoProibida> restricaoConversaoProibidaFiltradas) {
        this.restricaoConversaoProibidaFiltradas = restricaoConversaoProibidaFiltradas;
    }

    public RestricaoConversaoProibida getRestricaoConversaoProibida() {
        return restricaoConversaoProibida;
    }

    public void setRestricaoConversaoProibida(RestricaoConversaoProibida restricaoConversaoProibida) {
        this.restricaoConversaoProibida = restricaoConversaoProibida;
    }

 
}
