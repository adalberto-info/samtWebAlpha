/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 06/07/2016
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
import br.com.engebras.model.entities.LaudoMetrologico;
import br.com.engebras.filter.LaudoMetrologicoFilter;

@ManagedBean(name="mbPesquisaLaudoMetrologico")
@ViewScoped
public class MbPesquisaLaudoMetrologico implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    private EntityManager manager; 
    
    public LaudoMetrologicoFilter filtro; 
    private List<LaudoMetrologico> laudoMetrologicosFiltrados;
    private LaudoMetrologico laudoMetrologico = new LaudoMetrologico(); 
    
    
    public MbPesquisaLaudoMetrologico(){
        filtro = new LaudoMetrologicoFilter(); 
    }
    
    private InterfaceDAO<LaudoMetrologico> laudoMetrologicoDAO(){
        InterfaceDAO<LaudoMetrologico> laudoMetrologicoDAO = new HibernateDAO<LaudoMetrologico>(LaudoMetrologico.class, FacesContextUtil.getRequestSession());
        return laudoMetrologicoDAO; 
    }
         
    public void deleteLaudoMetrologico(){
        laudoMetrologicoDAO().remove(laudoMetrologico);
        pesquisar(); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!",""));
    }
    
    public void pesquisar(){
        laudoMetrologicosFiltrados = filtrados(filtro);
    }
    
    public List<LaudoMetrologico> filtrados(LaudoMetrologicoFilter filtro){
        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(LaudoMetrologico.class);
        
        if (StringUtils.isBlank(filtro.getDc_serieEquipamento())){
            criteria.add(Restrictions.eq("dc_serieEquipamento", filtro.getDc_serieEquipamento()));
        }
        
        return criteria.addOrder(Order.asc("dc_serieEquipamento")).list(); 
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public LaudoMetrologicoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(LaudoMetrologicoFilter filtro) {
        this.filtro = filtro;
    }

    public List<LaudoMetrologico> getLaudoMetrologicosFiltrados() {
        return laudoMetrologicosFiltrados;
    }

    public void setLaudoMetrologicosFiltrados(List<LaudoMetrologico> laudoMetrologicosFiltrados) {
        this.laudoMetrologicosFiltrados = laudoMetrologicosFiltrados;
    }

    public LaudoMetrologico getLaudoMetrologico() {
        return laudoMetrologico;
    }

    public void setLaudoMetrologico(LaudoMetrologico laudoMetrologico) {
        this.laudoMetrologico = laudoMetrologico;
    }
    
    
}
