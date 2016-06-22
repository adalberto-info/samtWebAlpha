package br.com.engebras.controller;

/**
 * @author Adalberto
 * dt. criação: 20/06/2016
 */

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;

import br.com.engebras.model.entities.Equipamento;
import br.com.engebras.filter.EquipamentoFilter;
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

@ManagedBean(name="mbPesquisaEquipamento")
@ViewScoped
public class MbPesquisaEquipamento implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private EntityManager manager; 
    
    public EquipamentoFilter filtro; 
    private List<Equipamento> equipamentosFiltrados; 
    
    private Equipamento equipamento = new Equipamento(); 
    
    public MbPesquisaEquipamento(){
        filtro = new EquipamentoFilter(); 
    }
    
    
    private InterfaceDAO<Equipamento> equipamentoDAO(){
        InterfaceDAO<Equipamento> equipamentoDAO = new HibernateDAO<Equipamento>(Equipamento.class, FacesContextUtil.getRequestSession());
        return equipamentoDAO;
    }
    
    public void deleteEquipamento(){
        equipamentoDAO().remove(equipamento);
        pesquisar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!",""));
    }

    public void pesquisar(){
        equipamentosFiltrados = filtrados(filtro);
    }

    public List<Equipamento> filtrados(EquipamentoFilter filtro){
        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(Equipamento.class);
        
        if (StringUtils.isNotBlank(filtro.getDc_serieEquipamento())){
            criteria.add(Restrictions.like("dc_serieEquipamento", filtro.getDc_serieEquipamento(), MatchMode.ANYWHERE));
        }
        
        return criteria.addOrder(Order.asc("dc_serieEquipamento")).list();
    }
    
    public List<Equipamento> porDc_serieEquipamento(String dc_serieEquipamento) {
        return this.manager.createQuery("from equipamento where upper(dc_serieEquipamento) like :dc_serieEquipamento", Equipamento.class).setParameter("dc_serieEquipamento", dc_serieEquipamento.toUpperCase() + "%").getResultList();
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public EquipamentoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(EquipamentoFilter filtro) {
        this.filtro = filtro;
    }

    public List<Equipamento> getEquipamentosFiltrados() {
        return equipamentosFiltrados;
    }

    public void setEquipamentosFiltrados(List<Equipamento> equipamentosFiltrados) {
        this.equipamentosFiltrados = equipamentosFiltrados;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
    
}
