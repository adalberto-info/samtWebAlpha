package br.com.engebras.controller;

/**
 * @author Adalberto
 * dt. criação: 13/06/2016
 */

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;

import br.com.engebras.model.entities.Contrato;
import br.com.engebras.filter.ContratoFilter;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.util.FacesContextUtil;
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


@ManagedBean(name="mbPesquisaContrato")
@ViewScoped
public class MbPesquisaContrato implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private EntityManager manager; 
    
    public ContratoFilter filtro; 
    private List<Contrato> contratosFiltrados; 
    
    private Contrato contrato = new Contrato(); 
    
    public MbPesquisaContrato(){
        filtro = new ContratoFilter();
    }

    private InterfaceDAO<Contrato> contratoDAO(){
        InterfaceDAO<Contrato> contratoDAO = new HibernateDAO<Contrato>(Contrato.class, FacesContextUtil.getRequestSession());
        return contratoDAO;
    }

    public void deleteContrato(){
        contratoDAO().remove(contrato);
        pesquisar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso !!!",""));
    }
    
    public void pesquisar(){
        contratosFiltrados = filtrados(filtro); 
    }
    
    public List<Contrato> filtrados(ContratoFilter filtro){
        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(Contrato.class);
        
        if (filtro.getNr_crd() > 0 ){
            criteria.add(Restrictions.eq("nr_crd", filtro.getNr_crd()));
        }
        return criteria.addOrder(Order.asc("nr_crd")).list(); 
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public ContratoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(ContratoFilter filtro) {
        this.filtro = filtro;
    }

    public List<Contrato> getContratosFiltrados() {
        return contratosFiltrados;
    }

    public void setContratosFiltrados(List<Contrato> contratosFiltrados) {
        this.contratosFiltrados = contratosFiltrados;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }



}
