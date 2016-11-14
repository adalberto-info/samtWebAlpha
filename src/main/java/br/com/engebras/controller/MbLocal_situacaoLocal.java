/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 14/11/2016
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.engebras.util.FacesContextUtil;
import br.com.engebras.util.HibernateUtil;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.entities.Local_situacaoLocal;
import br.com.engebras.filter.Local_situacaoLocalFilter; 
import br.com.engebras.model.entities.SituacaoLocal;

@ManagedBean(name="mbLocal_situacaoLocal")
@SessionScoped
public class MbLocal_situacaoLocal implements Serializable {

    private static final serialVersionUID = 1L; 
    
    private Local_situacaoLocal local_situacaoLocal = new Local_situacaoLocal(); 
    private Integer vpn_nr_codLocal; 
    private List<Local_situacaoLocal> local_situacaoLocalFiltrados; 
    private Local_situacaoLocal local_situacaoLocalSelecionado; 
    private Local_situacaoLocalFilter filtro; 
    
    public MbLocal_situacaoLocal(){
        filtro = new Local_situacaoLocalFilter(); 
    }

    private InterfaceDAO<Local_situacaoLocal> local_situacaoLocalDAO() {
        InterfaceDAO<Local_situacaoLocal> local_situacaoLocalDAO = new HibernateDAO<Local_situacaoLocal>(Local_situacaoLocal.class, FacesContextUtil.getRequestSession());
        return local_situacaoLocalDAO;
    }

    public String limpaLocal_situacaoLocal(){
        local_situacaoLocal = new Local_situacaoLocal();
        return editLocal_situacaoLocal();
    }
    
    public String editLocal_situacaoLocal(){
      return "";  
    }
    
    public String editarLocal_situacaoLocal(Integer nr_codigo){
        this.local_situacaoLocal = porNr_codigo(nr_codigo);
        return editLocal_situacaoLocal();
    }
    
    public Local_situacaoLocal porNr_codigo(Integer nr_codigo) {

        return local_situacaoLocalDAO().getEntity(nr_codigo);
    }

    public void addLocal_situacaoLocal(Integer nr_codLocal){
        vpn_nr_codLocal = nr_codLocal;
        local_situacaoLocal.setNr_codLocal(nr_codLocal);
        if (verificaDuplicidade(local_situacaoLocal.getDc_codSituacao()) == true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe uma situação para este período: " + local_situacaoLocal.getDc_codSituacao()+ " para o local: " + local_situacaoLocal.getNr_codLocal() + ".",""));
        } else if(local_situacaoLocal.getNr_codigo() == null || local_situacaoLocal.getNr_codigo() == 0){
            insertLocal_situacaoLocal();
        } else {
            updateLocal_situacaoLocal();
        }
        
        pesquisar(vpn_nr_codLocal);
        limpaLocal_situacaoLocal();
    }

    public void insertLocal_situacaoLocal() {
        local_situacaoLocalDAO().save(local_situacaoLocal);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!!!", ""));
    }

    
    
}
