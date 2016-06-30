package br.com.engebras.controller;

/**
 * @author Adalberto Kamida
 * dt. criação: 30/06/2016
 */

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
import br.com.engebras.model.entities.MotivoInconsistenciaImagem;


@ManagedBean(name="mbMotivoInconsistenciaImagem")
@SessionScoped
public class MbMotivoInconsistenciaImagem implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private MotivoInconsistenciaImagem motivoInconsistenciaImagem = new MotivoInconsistenciaImagem(); 
    
    private List<MotivoInconsistenciaImagem> motivoInconsistenciaImagens;
    
    public MbMotivoInconsistenciaImagem(){
        
    }

    private InterfaceDAO<MotivoInconsistenciaImagem> motivoInconsistenciaImagemDAO(){
        InterfaceDAO<MotivoInconsistenciaImagem> motivoInconsistenciaImagemDAO = new HibernateDAO<MotivoInconsistenciaImagem>(MotivoInconsistenciaImagem.class, FacesContextUtil.getRequestSession());
        return motivoInconsistenciaImagemDAO; 
    }

    public String limpaMotivoInconsistenciaImagem(){
        motivoInconsistenciaImagem = new MotivoInconsistenciaImagem(); 
        return editMotivoInconsistenciaImagem(); 
    }
    
    public String editMotivoInconsistenciaImagem(){
        return "/restrict/cadMotivoInconsistenciaImagem.xhtml"; 
    }

    public String editarMotivoInconsistenciaImagem(Integer nr_codigo){
        motivoInconsistenciaImagem = porNr_codigo(nr_codigo); 
        return editMotivoInconsistenciaImagem(); 
    }    

    public void addMotivoInconsistenciaImagem(){ 
        if (verificaDuplicidade(motivoInconsistenciaImagem.getDc_inconsistencia()) == true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe um motivo inconsistência imagem com o código: " + motivoInconsistenciaImagem.getNr_codigo(),""));
        } else if(motivoInconsistenciaImagem.getNr_codigo() == null || motivoInconsistenciaImagem.getNr_codigo() ==0 ) {
            insertMotivoInconsistenciaImagem();
        } else {
            updateMotivoInconsistenciaImagem();
        }
    }
    
    public void insertMotivoInconsistenciaImagem(){
        motivoInconsistenciaImagemDAO().save(motivoInconsistenciaImagem);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso !!!",""));
    }

    public void updateMotivoInconsistenciaImagem(){ 
        motivoInconsistenciaImagemDAO().update(motivoInconsistenciaImagem);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização realizada com sucesso !!!", ""));
    }
    
    private boolean verificaDuplicidade(String dc_inconsistencia){
        
        boolean vll_retorno = true; 
        
        String vlc_sql = ""; 
        List consMotivoInconsistenciaImagem; 
        
        Session session = FacesContextUtil.getRequestSession(); 
        
        vlc_sql = "select m.nr_codigo from motivoInconsistenciaImagem m where m.dc_inconsistencia = " + dc_inconsistencia + " "; 
        if (motivoInconsistenciaImagem.getNr_codigo() != null && motivoInconsistenciaImagem.getNr_codigo() > 0)
            vlc_sql += "and m.nr_codigo <> " + motivoInconsistenciaImagem.getNr_codigo(); 
        
        
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consMotivoInconsistenciaImagem = query.list(); 
        
        if (consMotivoInconsistenciaImagem.size() > 0 ) {
            vll_retorno = true; 
        } else {
            vll_retorno = false; 
        }

        for (Object oMotivoInconsistenciaImagem : consMotivoInconsistenciaImagem) {
            Map row = (Map) oMotivoInconsistenciaImagem;
        }
        
        consMotivoInconsistenciaImagem = null; 
        
        return vll_retorno; 
    }

    public MotivoInconsistenciaImagem porNr_codigo(Integer nr_codigo){
        return motivoInconsistenciaImagemDAO().getEntity(nr_codigo);
    }

    public MotivoInconsistenciaImagem getMotivoInconsistenciaImagem() {
        return motivoInconsistenciaImagem;
    }

    public void setMotivoInconsistenciaImagem(MotivoInconsistenciaImagem motivoInconsistenciaImagem) {
        this.motivoInconsistenciaImagem = motivoInconsistenciaImagem;
    }

    public List<MotivoInconsistenciaImagem> getMotivoInconsistenciaImagens() {
        return motivoInconsistenciaImagens;
    }

    public void setMotivoInconsistenciaImagens(List<MotivoInconsistenciaImagem> motivoInconsistenciaImagens) {
        this.motivoInconsistenciaImagens = motivoInconsistenciaImagens;
    }
        


}
