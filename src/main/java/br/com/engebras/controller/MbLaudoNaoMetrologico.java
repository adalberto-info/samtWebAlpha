/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 08/07/2016 
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
import br.com.engebras.model.entities.LaudoNaoMetrologico;

@ManagedBean(name="mbLaudoNaoMetrologico")
@SessionScoped
public class MbLaudoNaoMetrologico implements Serializable{

    private static final long serialVersionUID = 1L; 
    
    
    private LaudoNaoMetrologico laudoNaoMetrologico = new LaudoNaoMetrologico(); 
    private List<LaudoNaoMetrologico> laudoNaoMetrologicos; 
    private List motivoLaudos = new ArrayList<>();
    
    public MbLaudoNaoMetrologico(){
        geraListaMotivoLaudos(); 
    }
    
    private InterfaceDAO<LaudoNaoMetrologico> laudoNaoMetrologicoDAO(){
        InterfaceDAO<LaudoNaoMetrologico> laudoNaoMetrologicoDAO = new HibernateDAO<LaudoNaoMetrologico>(LaudoNaoMetrologico.class, FacesContextUtil.getRequestSession());
        return laudoNaoMetrologicoDAO; 
    } 
    
    public String limpaLaudoNaoMetrologico(){
        laudoNaoMetrologico = new LaudoNaoMetrologico();
        return editLaudoNaoMetrologico(); 
    }

    public String editLaudoNaoMetrologico(){
        return "/restrict/cadLaudoNaoMetrologico.xhtml"; 
    }
    
    public String editarLaudoNaoMetrologico(Integer nr_codigo){
        this.laudoNaoMetrologico = porNr_codigo(nr_codigo); 
        return editLaudoNaoMetrologico();
    }
    
    public void addLaudoNaoMetrologico(){
        if (verificaDuplicidade(laudoNaoMetrologico.getDc_serieEquipamento()) == true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe um laudo não metrológico com a série equipamento: " + laudoNaoMetrologico.getDc_serieEquipamento()+ ".", ""));
        } else if(laudoNaoMetrologico.getNr_codigo() == null || laudoNaoMetrologico.getNr_codigo() == 0 ){
            insertLaudoNaoMetrologico();
        } else {
            updateLaudoNaoMetrologico();
        }
        limpaLaudoNaoMetrologico(); 
    }
    
    public void insertLaudoNaoMetrologico(){
        laudoNaoMetrologicoDAO().save(laudoNaoMetrologico);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!!!", ""));
    }
           
    public void updateLaudoNaoMetrologico(){
        laudoNaoMetrologicoDAO().update(laudoNaoMetrologico);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!!!",""));
    }

    private boolean verificaDuplicidade(String dc_serieEquipamento){
        boolean vll_retorno = false; 
        String vlc_sql = ""; 
        List consLaudoNaoMetrologico; 
        
        Session session = FacesContextUtil.getRequestSession();
        vlc_sql = "select l.dc_serieEquipamento from laudoNaoMetrologico l where l.dc_serieEquipamento = '" + dc_serieEquipamento + "' "; 
        
        if (laudoNaoMetrologico.getNr_codigo() != null && laudoNaoMetrologico.getNr_codigo() > 0)
                vlc_sql += "and l.nr_codigo <> " + laudoNaoMetrologico.getNr_codigo();
        
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consLaudoNaoMetrologico = query.list();
        
        if (consLaudoNaoMetrologico.size() > 0)
            vll_retorno = true;
        else
            vll_retorno = false; 
        
        for (Object oLaudoNaoMetrologico : consLaudoNaoMetrologico){
            Map row = (Map) oLaudoNaoMetrologico;
        }
        
        consLaudoNaoMetrologico = null;
        
        return vll_retorno;
    }
    
    public LaudoNaoMetrologico porNr_codigo(Integer nr_codigo) {
        return laudoNaoMetrologicoDAO().getEntity(nr_codigo);
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

    public LaudoNaoMetrologico getLaudoNaoMetrologico() {
        return laudoNaoMetrologico;
    }

    public void setLaudoNaoMetrologico(LaudoNaoMetrologico laudoNaoMetrologico) {
        this.laudoNaoMetrologico = laudoNaoMetrologico;
    }

    public List<LaudoNaoMetrologico> getLaudoNaoMetrologicos() {
        return laudoNaoMetrologicos;
    }

    public void setLaudoNaoMetrologicos(List<LaudoNaoMetrologico> laudoNaoMetrologicos) {
        this.laudoNaoMetrologicos = laudoNaoMetrologicos;
    }

    public List getMotivoLaudos() {
        return motivoLaudos;
    }

    public void setMotivoLaudos(List motivoLaudos) {
        this.motivoLaudos = motivoLaudos;
    }

    
}
