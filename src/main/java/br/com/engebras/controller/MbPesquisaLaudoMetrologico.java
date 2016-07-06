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
    
    private InterfaceDAO 

    
    
    
    
}
