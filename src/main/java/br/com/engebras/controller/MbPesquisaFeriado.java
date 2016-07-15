/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 14/07/2016
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
import br.com.engebras.model.entities.Feriado;
import br.com.engebras.filter.FeriadoFilter;

@ManagedBean(name="mbPesquisaFeriado")
@ViewScoped
public class MbPesquisaFeriado implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private EntityManager manager; 
    
    public FeriadoFilter filtro; 
    private List<Feriado> feriadosFiltrados; 
    private Feriado feriado = new Feriado(); 
    
    public MbPesquisaFeriado(){
        filtro = new FeriadoFilter(); 
    }
    
    private InterfaceDAO<Feriado> feriadoDAO(){
        InterfaceDAO<Feriado> feriadoDAO = new HibernateDAO<Feriado>(Feriado.class, FacesContextUtil.getRequestSession());
        return feriadoDAO;
    }
    
    public void deleteFeriado(){
        feriadoDAO().remove(feriado);
        pesquisar(); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!",""));
    }
    
    public void pesquisar(){
        feriadosFiltrados = filtrados(filtro);
    }
    
    public List<Feriado> filtrados(FeriadoFilter filtro){
        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(Feriado.class);
        
        if (StringUtils.isNotBlank(filtro.getDt_feriado())){
            criteria.add(Restrictions.eq("dt_feriado", filtro.getDt_feriado()));
        }
        
        return criteria.addOrder(Order.asc("dt_feriado")).list();
        
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public FeriadoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(FeriadoFilter filtro) {
        this.filtro = filtro;
    }

    public List<Feriado> getFeriadosFiltrados() {
        return feriadosFiltrados;
    }

    public void setFeriadosFiltrados(List<Feriado> feriadosFiltrados) {
        this.feriadosFiltrados = feriadosFiltrados;
    }

    public Feriado getFeriado() {
        return feriado;
    }

    public void setFeriado(Feriado feriado) {
        this.feriado = feriado;
    }

}
