/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 24/10/2016
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.engebras.util.FacesContextUtil;
import br.com.engebras.util.HibernateUtil;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.entities.Local_equipamento;
import br.com.engebras.filter.Local_equipamentoFilter; 


@ManagedBean(name="mbLocal_equipamento")
@SessionScoped
public class MbLocal_equipamento {

    private static final long serialVersionUID = 1L; 

    
    private Local_equipamento local_equipamento = new Local_equipamento();
    private Integer vpn_nr_codLocal; 
    private List<Local_equipamento> local_equipamentoFiltrados; 
    private Local_equipamento local_equipamentoSelecionado; 
    public Local_equipamentoFilter filtro; 
    
    
    public MbLocal_equipamento(){
        
    }
    
    private InterfaceDAO<Local_equipamento> local_equipamento() {
        InterfaceDAO<Local_equipamento> local_equipamentoDAO = new HibernateDAO<Local_equipamento>(Local_equipamento.class, FacesContextUtil.getRequestSession());
        return local_equipamentoDAO;
    }

    public void limpaLocal_equipamento(){
        local_equipamento = new Local_equipamento();
    }
    
    public void editarLocal_equipamento(){


    }
    
    
}
