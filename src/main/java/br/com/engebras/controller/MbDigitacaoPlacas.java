/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 19/12/2016
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
import javax.annotation.PostConstruct;

import br.com.engebras.util.FacesContextUtil;
import br.com.engebras.util.HibernateUtil;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.model.dao.HibernateDAO;


@ManagedBean(name = "mbDigitacaoPlacas")
@SessionScoped
public class MbDigitacaoPlacas implements Serializable{

    private static final long serialVersionUID = 1L; 
    
private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();

        images.add("000010102488718260_00.jpg");
        images.add("000010102488718260_01.jpg");
    }
 
    public List<String> getImages() {
        return images;
    }


    
}
