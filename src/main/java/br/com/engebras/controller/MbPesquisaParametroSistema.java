/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 13/07/2016
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
import br.com.engebras.model.entities.ParametroSistema;
import br.com.engebras.filter.ParametroSistemaFilter;

@ManagedBean(name="mbPesquisaParametroSistema")
@ViewScoped
public class MbPesquisaParametroSistema implements Serializable{
    
    private static final long serialVersionUID = 1L; 
    
    private EntityManager manager; 
    
    public ParametroSistemaFilter filtro; 
    
    private List<ParametroSistema> parametroSistemasFiltrados; 
    private ParametroSistema parametroSistema = new ParametroSistema(); 
    
    public MbPesquisaParametroSistema(){
        filtro = new ParametroSistemaFilter(); 
    }
    
    
    private InterfaceDAO<ParametroSistema> parametroSistemaDAO(){
        InterfaceDAO<ParametroSistema> parametroSistemaDAO = new HibernateDAO<ParametroSistema>(ParametroSistema.class, FacesContextUtil.getRequestSession());
        return parametroSistemaDAO; 
    }
    
    public void deleteParametroSistema(){
        parametroSistemaDAO().remove(parametroSistema);
        pesquisar(); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!",""));
    }
    
    public void pesquisar(){
        parametroSistemasFiltrados = filtrados(filtro);
    }
    
    public List<ParametroSistema> filtrados(ParametroSistemaFilter filtro){
        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(ParametroSistema.class);
        
        if (StringUtils.isNotBlank(filtro.getDc_parametro())){
            criteria.add(Restrictions.eq("dc_parametro", filtro));
        }
        
        return criteria.addOrder(Order.asc("dc_parametro")).list();
        
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public ParametroSistemaFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(ParametroSistemaFilter filtro) {
        this.filtro = filtro;
    }

    public List<ParametroSistema> getParametroSistemasFiltrados() {
        return parametroSistemasFiltrados;
    }

    public void setParametroSistemasFiltrados(List<ParametroSistema> parametroSistemasFiltrados) {
        this.parametroSistemasFiltrados = parametroSistemasFiltrados;
    }

    public ParametroSistema getParametroSistema() {
        return parametroSistema;
    }

    public void setParametroSistema(ParametroSistema parametroSistema) {
        this.parametroSistema = parametroSistema;
    }
    


    
}
