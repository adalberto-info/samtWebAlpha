/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 06/07/2016
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
import br.com.engebras.model.entities.LaudoMetrologico;

@ManagedBean(name="mbLaudoMetrologico")
@SessionScoped
public class MbLaudoMetrologico implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private LaudoMetrologico laudoMetrologico = new LaudoMetrologico(); 
    private List<LaudoMetrologico> laudoMetrologicos; 
    private List motivoLaudos = new ArrayList<>();
    
    public MbLaudoMetrologico(){
        geraListaMotivoLaudos(); 
    }
    
    private InterfaceDAO<LaudoMetrologico> laudoMetrologicoDAO(){
        InterfaceDAO<LaudoMetrologico> laudoMetrologicoDAO = new HibernateDAO<LaudoMetrologico>(LaudoMetrologico.class, FacesContextUtil.getRequestSession());
        return laudoMetrologicoDAO; 
    } 
    
    public String limpaLaudoMetrologico(){
        laudoMetrologico = new LaudoMetrologico();
        return editLaudoMetrologico(); 
    }

    public String editLaudoMetrologico(){
        return "/restrict/cadLaudoMetrologico.xhtml"; 
    }
    
    public String editarLaudoMetrologico(Integer nr_codigo){
        this.laudoMetrologico = porNr_codigo(nr_codigo); 
        return editLaudoMetrologico();
    }
    
    public void addLaudoMetrologico(){
        if (verificaDuplicidade(laudoMetrologico.getDc_serieEquipamento()) == true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe um laudo metrológico com a série equipamento: " + laudoMetrologico.getDc_serieEquipamento()+ ".", ""));
        } else if(laudoMetrologico.getNr_codigo() == null || laudoMetrologico.getNr_codigo() == 0 ){
            insertLaudoMetrologico();
        } else {
            updateLaudoMetrologico();
        }
        limpaLaudoMetrologico(); 
    }
    
    public void insertLaudoMetrologico(){
        laudoMetrologicoDAO().save(laudoMetrologico);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!!!", ""));
    }
           
    public void updateLaudoMetrologico(){
        laudoMetrologicoDAO().update(laudoMetrologico);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!!!",""));
    }

    private boolean verificaDuplicidade(String dc_serieEquipamento){
        boolean vll_retorno = false; 
        String vlc_sql = ""; 
        List consLaudoMetrologico; 
        
        Session session = FacesContextUtil.getRequestSession();
        vlc_sql = "select l.dc_serieEquipamento from laudoMetrologico l where l.dc_serieEquipamento = '" + dc_serieEquipamento + "' "; 
        
        if (laudoMetrologico.getNr_codigo() != null && laudoMetrologico.getNr_codigo() > 0)
                vlc_sql += "and l.nr_codigo <> " + laudoMetrologico.getNr_codigo();
        
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consLaudoMetrologico = query.list();
        
        if (consLaudoMetrologico.size() > 0)
            vll_retorno = true;
        else
            vll_retorno = false; 
        
        for (Object oLaudoMetrologico : consLaudoMetrologico){
            Map row = (Map) oLaudoMetrologico;
        }
        
        consLaudoMetrologico = null;
        
        return vll_retorno;
    }
    
    public LaudoMetrologico porNr_codigo(Integer nr_codigo) {
        return laudoMetrologicoDAO().getEntity(nr_codigo);
    }

    public void geraListaMotivoLaudos(){
        List listaSQL; 
        String vlc_sql; 
        vlc_sql = "select nr_codigo, dc_motivoLaudo from motivoLaudo order by nr_codigo"; 
        
        Session session = FacesContextUtil.getRequestSession();
        
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        listaSQL = query.list();
        
        this.motivoLaudos = listaSQL; 
        
    }

    public List getMotivoLaudos() {
        return motivoLaudos;
    }

    public void setMotivoLaudos(List motivoLaudos) {
        this.motivoLaudos = motivoLaudos;
    }
    
    
    public LaudoMetrologico getLaudoMetrologico() {
        return laudoMetrologico;
    }

    public void setLaudoMetrologico(LaudoMetrologico laudoMetrologico) {
        this.laudoMetrologico = laudoMetrologico;
    }

    public List<LaudoMetrologico> getLaudoMetrologicos() {
        return laudoMetrologicos;
    }

    public void setLaudoMetrologicos(List<LaudoMetrologico> laudoMetrologicos) {
        this.laudoMetrologicos = laudoMetrologicos;
    }
    
}
