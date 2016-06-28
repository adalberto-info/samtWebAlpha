package br.com.engebras.controller;

/**
 * @author Adalberto
 * dt. criação: 28/06/2016
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import br.com.engebras.util.FacesContextUtil;
import br.com.engebras.util.HibernateUtil;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.entities.Area;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;


@ManagedBean(name="mbArea")
@SessionScoped
public class MbArea implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Area area = new Area(); 
    
    private List<Area> areas;
    
    public MbArea(){
        
    }

    private InterfaceDAO<Area> areaDAO(){
        InterfaceDAO<Area> areaDAO = new HibernateDAO<Area>(Area.class, FacesContextUtil.getRequestSession());
    
    return areaDAO; 
    }
    
    public String limpaArea(){
        area = new Area();
        return editArea(); 
    } 
   
    public String editArea(){
        return "/restrict/cad_area.xhtml";
    }
    
    public String editarArea(String dc_codArea){
        this.area = porDc_codArea(dc_codArea);
        return editArea(); 
    }
}
