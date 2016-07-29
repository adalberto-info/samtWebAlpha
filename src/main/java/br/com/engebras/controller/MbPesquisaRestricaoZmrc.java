/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 27/07/2016
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
import br.com.engebras.model.entities.RestricaoZmrc;
import br.com.engebras.filter.RestricaoZmrcFilter;

@ManagedBean(name = "mbPesquisaRestricaoZmrc")
@ViewScoped
public class MbPesquisaRestricaoZmrc implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    public RestricaoZmrcFilter filtro;
    private List<RestricaoZmrc> restricaoZmrcFiltradas;
    private RestricaoZmrc restricaoZmrc = new RestricaoZmrc();

    public MbPesquisaRestricaoZmrc() {
        filtro = new RestricaoZmrcFilter();
    }

    private InterfaceDAO<RestricaoZmrc> restricaoZmrcDAO() {
        InterfaceDAO<RestricaoZmrc> restricaoZmrcDAO = new HibernateDAO<RestricaoZmrc>(RestricaoZmrc.class, FacesContextUtil.getRequestSession());
        return restricaoZmrcDAO;
    }

    public void deleteRestricaoZmrc() {
        restricaoZmrcDAO().remove(restricaoZmrc);
        pesquisar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!", ""));
    }

    public void pesquisar() {
        restricaoZmrcFiltradas = filtrados(filtro);
    }

    public List<RestricaoZmrc> filtrados(RestricaoZmrcFilter filtro) {
        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(RestricaoZmrc.class);

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

    public RestricaoZmrcFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(RestricaoZmrcFilter filtro) {
        this.filtro = filtro;
    }

    public List<RestricaoZmrc> getRestricaoZmrcFiltradas() {
        return restricaoZmrcFiltradas;
    }

    public void setRestricaoZmrcFiltradas(List<RestricaoZmrc> restricaoZmrcFiltradas) {
        this.restricaoZmrcFiltradas = restricaoZmrcFiltradas;
    }

    public RestricaoZmrc getRestricaoZmrc() {
        return restricaoZmrc;
    }

    public void setRestricaoZmrc(RestricaoZmrc restricaoZmrc) {
        this.restricaoZmrc = restricaoZmrc;
    }

}
