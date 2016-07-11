/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 11/07/2016
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
import br.com.engebras.model.entities.LaudoNaoMetrologico;
import br.com.engebras.filter.LaudoNaoMetrologicoFilter;

@ManagedBean(name="mbPesquisaLaudoNaoMetrologico")
@ViewScoped
public class MbPesquisaLaudoNaoMetrologico implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    private EntityManager manager; 
    
    public LaudoNaoMetrologicoFilter filtro; 
    private List<LaudoNaoMetrologico> laudoNaoMetrologicosFiltrados;
    private LaudoNaoMetrologico laudoNaoMetrologico = new LaudoNaoMetrologico(); 
    
    
    public MbPesquisaLaudoNaoMetrologico(){
        filtro = new LaudoNaoMetrologicoFilter(); 
    }
    
    private InterfaceDAO<LaudoNaoMetrologico> laudoNaoMetrologicoDAO(){
        InterfaceDAO<LaudoNaoMetrologico> laudoNaoMetrologicoDAO = new HibernateDAO<LaudoNaoMetrologico>(LaudoNaoMetrologico.class, FacesContextUtil.getRequestSession());
        return laudoNaoMetrologicoDAO; 
    }
         
    public void deleteLaudoNaoMetrologico(){
        laudoNaoMetrologicoDAO().remove(laudoNaoMetrologico);
        pesquisar(); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!",""));
    }
    
    public void pesquisar(){
        laudoNaoMetrologicosFiltrados = filtrados(filtro);
    }
    
    public List<LaudoNaoMetrologico> filtrados(LaudoNaoMetrologicoFilter filtro){
        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(LaudoNaoMetrologico.class);
        
        if (StringUtils.isNotBlank(filtro.getDc_serieEquipamento())){
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

    public LaudoNaoMetrologicoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(LaudoNaoMetrologicoFilter filtro) {
        this.filtro = filtro;
    }

    public List<LaudoNaoMetrologico> getLaudoNaoMetrologicosFiltrados() {
        return laudoNaoMetrologicosFiltrados;
    }

    public void setLaudoNaoMetrologicosFiltrados(List<LaudoNaoMetrologico> laudoNaoMetrologicosFiltrados) {
        this.laudoNaoMetrologicosFiltrados = laudoNaoMetrologicosFiltrados;
    }

    public LaudoNaoMetrologico getLaudoNaoMetrologico() {
        return laudoNaoMetrologico;
    }

    public void setLaudoNaoMetrologico(LaudoNaoMetrologico laudoNaoMetrologico) {
        this.laudoNaoMetrologico = laudoNaoMetrologico;
    }
    
    
}
