package br.com.engebras.controller;

/**
 * @author Adalberto Kamida
 * dt. criação: 24/06/2016
 */

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
import br.com.engebras.model.entities.GrupoAutuador;
import br.com.engebras.filter.GrupoAutuadorFilter;


@ManagedBean(name="mbPesquisaGrupoAutuador")
@ViewScoped
public class MbPesquisaGrupoAutuador implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    private EntityManager manager;
    
    public GrupoAutuadorFilter filtro; 
    private List<GrupoAutuador> gruposAutuadores;
    public List<GrupoAutuador> grupoAutuadorFiltrados;
    private GrupoAutuador grupoAutuador = new GrupoAutuador(); 
    
    public MbPesquisaGrupoAutuador(){
        filtro = new GrupoAutuadorFilter();
    }
    
    private InterfaceDAO<GrupoAutuador> grupoAutuadorDAO(){
        InterfaceDAO<GrupoAutuador> grupoAutuadorDAO = new HibernateDAO<GrupoAutuador>(GrupoAutuador.class, FacesContextUtil.getRequestSession());
        return grupoAutuadorDAO;
    }
    
    public void deleteGrupoAutuador(){
        grupoAutuadorDAO().remove(grupoAutuador);
        pesquisar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!",""));
    }

    public void pesquisar(){
        grupoAutuadorFiltrados = filtrados(filtro);
    }

    public List<GrupoAutuador> filtrados(GrupoAutuadorFilter filtro){
        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(GrupoAutuador.class);
        
        if (StringUtils.isNotBlank(filtro.getDc_codigo())){
            criteria.add(Restrictions.eq("dc_codigo", filtro.getDc_codigo()));
        }

        return criteria.addOrder(Order.asc("dc_codigo")).list(); 
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public GrupoAutuadorFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(GrupoAutuadorFilter filtro) {
        this.filtro = filtro;
    }

    public List<GrupoAutuador> getGruposAutuadores() {
        return gruposAutuadores;
    }

    public void setGruposAutuadores(List<GrupoAutuador> gruposAutuadores) {
        this.gruposAutuadores = gruposAutuadores;
    }

    public List<GrupoAutuador> getGrupoAutuadorFiltrados() {
        return grupoAutuadorFiltrados;
    }

    public void setGrupoAutuadorFiltrados(List<GrupoAutuador> grupoAutuadorFiltrados) {
        this.grupoAutuadorFiltrados = grupoAutuadorFiltrados;
    }

    public GrupoAutuador getGrupoAutuador() {
        return grupoAutuador;
    }

    public void setGrupoAutuador(GrupoAutuador grupoAutuador) {
        this.grupoAutuador = grupoAutuador;
    }

    
}
