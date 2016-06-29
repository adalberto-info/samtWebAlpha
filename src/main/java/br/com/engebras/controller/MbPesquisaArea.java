package br.com.engebras.controller;

/**
 * @author Adalberto
 * dt. criação: 29/06/2016
 */

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;

import br.com.engebras.model.entities.Area;
import br.com.engebras.filter.AreaFilter;
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


@ManagedBean(name="mbPesquisaArea")
@ViewScoped
public class MbPesquisaArea implements Serializable {
    
    
    private static final long serialVersionUID = 1L; 
    
    private EntityManager manager; 
    
    public AreaFilter filtro; 
    private List<Area> areasFiltradas; 
    
    private Area area = new Area(); 
    
    public MbPesquisaArea(){
        filtro = new AreaFilter(); 
    }
    
    private InterfaceDAO<Area> areaDAO(){
        InterfaceDAO<Area> areaDAO = new HibernateDAO<Area>(Area.class, FacesContextUtil.getRequestSession());
        return areaDAO;
    }
    
    public void deleteArea(){
        areaDAO().remove(area);
        pesquisar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!",""));
    }
    
    public void pesquisar(){
        areasFiltradas = filtrados(filtro);
    }
    
    
    public List<Area> filtrados(AreaFilter filtro) {
        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(Area.class);
        
        if (StringUtils.isNotBlank(filtro.getDc_codArea())){
            criteria.add(Restrictions.eq("dc_codArea", filtro.getDc_codArea()));
        }

        return criteria.addOrder(Order.asc("dc_codArea")).list(); 
    }

    public AreaFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(AreaFilter filtro) {
        this.filtro = filtro;
    }

    public List<Area> getAreasFiltradas() {
        return areasFiltradas;
    }

    public void setAreasFiltradas(List<Area> areasFiltradas) {
        this.areasFiltradas = areasFiltradas;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    
    
}
