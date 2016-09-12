/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 12/09/2016
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
import br.com.engebras.model.entities.LocalVelocidade;

@ManagedBean(name = "mbLocalVelocidade" )
@SessionScoped
public class MbLocalVelocidade implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private LocalVelocidade localVelocidade = new LocalVelocidade();
    
}
