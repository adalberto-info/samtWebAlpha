/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 01/08/2016
 * 
 */
package br.com.engebras.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import br.com.engebras.util.FacesContextUtil;
import br.com.engebras.util.HibernateUtil;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.entities.RestricaoFxExclusiva;

@ManagedBean(name = "mbRestricaoFxExclusiva")
@SessionScoped
public class MbRestricaoFxExclusiva implements Serializable {

    private static final long serialVersionUID = 1L;
    private RestricaoFxExclusiva restricaoFxExclusiva = new RestricaoFxExclusiva();
    private List<RestricaoFxExclusiva> restricaoFxExclusivas;

    public MbRestricaoFxExclusiva() {

    }

    private InterfaceDAO<RestricaoFxExclusiva> restricaoFxExclusivaDAO() {
        InterfaceDAO<RestricaoFxExclusiva> restricaoFxExclusivaDAO = new HibernateDAO<RestricaoFxExclusiva>(RestricaoFxExclusiva.class, FacesContextUtil.getRequestSession());
        return restricaoFxExclusivaDAO;
    }

    public String limpaRestricaoFxExclusiva() {
        return editRestricaoFxExclusiva();
    }

    public String editRestricaoFxExclusiva(){
        return "/restrict/cadRestricaoFxExclusiva.faces";
    }
    
}
