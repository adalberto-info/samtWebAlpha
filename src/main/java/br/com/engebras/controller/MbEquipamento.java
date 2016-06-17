package br.com.engebras.controller;

/**
 * @author Adalberto
 * dt. criação: 17/06/2016
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
import br.com.engebras.model.entities.Equipamento;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

@ManagedBean(name="mbEquipamento")
@SessionScoped
public class MbEquipamento implements Serializable {

    private static final long seriaVersionUId = 1L;

    private Equipamento equipamento = new Equipamento();

    private List<Equipamento> equipamentos; 
    
    public MbEquipamento(){
    }
    
    private InterfaceDAO<Equipamento> equipamentoDAO() {
        InterfaceDAO<Equipamento> equipamentoDAO = new HibernateDAO<Equipamento>(Equipamento.class, FacesContextUtil.getRequestSession());
        return equipamentoDAO;
    }

    public String limpaEquipamento(){
        this.equipamento = new Equipamento(); 
        return editEquipamento(); 
    }
    
    public String editEquipamento(){
        return "/restrict/cadEquipamento.faces";
    }
    
    public String editarFilial(Integer nr_codigo){
        this.equipamento = porNr_codigo(nr_codigo);
        return editEquipamento(); 
    }
    
}
