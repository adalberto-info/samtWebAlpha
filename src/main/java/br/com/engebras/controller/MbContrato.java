package br.com.engebras.controller;

/**
 * @author Adalberto
 * dt. criacao: 09/06/2016
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
import br.com.engebras.model.entities.Filial;
import br.com.engebras.model.entities.Contrato;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;


@ManagedBean(name="mbContrato")
@SessionScoped
public class MbContrato implements Serializable{

    private static final long serialVersionUID = 1L; 
    
    private Contrato contrato = new Contrato(); 
    
    private List<Contrato> contratos; 
    private List filiais = new ArrayList<>(); 
    
    public MbContrato(){
        
    }
    
    private InterfaceDAO<Contrato> contratoDAO() {
        InterfaceDAO<Contrato> contratoDAO = new HibernateDAO<Contrato>(Contrato.class, FacesContextUtil.getRequestSession());
        return contratoDAO; 
    }

    public String limpaContrato(){
        contrato = new Contrato(); 
        return editContrato(); 
    }

    public String editContrato(){
        return "/restrict/cadContrato.xhtml"; 
    }
    
}
