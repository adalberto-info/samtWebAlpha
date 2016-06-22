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
    
    public String editarEquipamento(Integer nr_codigo){
        this.equipamento = porNr_codigo(nr_codigo);
        return editEquipamento(); 
    }
    
    public void addEquipamento(){
        if (verificaDuplicidade(equipamento.getDc_serieEquipamento())==true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe um equipamento com esta série de equipamento: " + equipamento.getDc_serieEquipamento()+ ".",""));
        } else if(equipamento.getNr_codigo() == null || equipamento.getNr_codigo() == 0){
            insertEquipamento();
        } else{
            updateEquipamento();
        }
        limpaEquipamento();
    }
    
    public void insertEquipamento(){
        equipamentoDAO().save(equipamento);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso !!!", ""));
    }
 
    public void updateEquipamento(){
        equipamentoDAO().update(equipamento);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso !!!",""));
    }
    
    public void deleteEquipamento(){
        equipamentoDAO().remove(equipamento);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro excluído com sucesso !!!",""));
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
    
    private boolean verificaDuplicidade(String dc_serieEquipamento){
        boolean vll_retorno = true;
        
        String vlc_sql; 
        List consEquipamentos; 
        
        Session session = FacesContextUtil.getRequestSession();
        vlc_sql = "select e.dc_serieEquipamento from equipamento e where e.dc_serieEquipamento = '" + dc_serieEquipamento + "' ";
        if (equipamento.getNr_codigo() != null && equipamento.getNr_codigo() != 0)
            vlc_sql = vlc_sql + "and e.nr_codigo <> " + equipamento.getNr_codigo();
        
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consEquipamentos = query.list();
        
        if (consEquipamentos.size() >0){
            vll_retorno = true;
        } else{
            vll_retorno = false;
        }
        
        for (Object oEquipamento : consEquipamentos){
            Map row = (Map) oEquipamento;
        }
        
        consEquipamentos = null;
        
        return vll_retorno;
    }
    
    
    public Equipamento porNr_codigo(Integer nr_codigo){
        return equipamentoDAO().getEntity(nr_codigo);
    }
    
}
