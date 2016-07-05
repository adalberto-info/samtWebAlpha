package br.com.engebras.controller;

/**
 * @author Adalberto Kamida
 * dt. criacao: 30/06/2016
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
    private boolean vll_lg_56732, vll_lg_56810, vll_lg_56900, vll_lg_57030, vll_lg_57461, vll_lg_57462, vll_lg_57463 ; 
    private boolean vll_lg_60411, vll_lg_60412, vll_lg_60503, vll_lg_74550, vll_lg_74630, vll_lg_74710, vll_lg_57461M; 
    private boolean vll_novoMotivoInconsistenciaImagem;
    
    public MbMotivoInconsistenciaImagem(){
        vll_lg_56732 = false;
        vll_lg_56810 = false;
        vll_lg_56900 = false;
        vll_lg_57030 = false;
        vll_lg_57461 = false;
        vll_lg_57462 = false;
        vll_lg_57463 = false;
        vll_lg_60411 = false;
        vll_lg_60412 = false;
        vll_lg_60503 = false;
        vll_lg_74550 = false;
        vll_lg_74630 = false;
        vll_lg_74710 = false;
        vll_lg_57461M = false; 
        vll_novoMotivoInconsistenciaImagem = true;
    }

    private InterfaceDAO<MotivoInconsistenciaImagem> motivoInconsistenciaImagemDAO(){
        InterfaceDAO<MotivoInconsistenciaImagem> motivoInconsistenciaImagemDAO = new HibernateDAO<MotivoInconsistenciaImagem>(MotivoInconsistenciaImagem.class, FacesContextUtil.getRequestSession());
        return motivoInconsistenciaImagemDAO; 
    }

    public String limpaMotivoInconsistenciaImagem(){
        motivoInconsistenciaImagem = new MotivoInconsistenciaImagem(); 
        vll_lg_56732 = false;
        vll_lg_56810 = false;
        vll_lg_56900 = false;
        vll_lg_57030 = false;
        vll_lg_57461 = false;
        vll_lg_57462 = false;
        vll_lg_57463 = false;
        vll_lg_60411 = false;
        vll_lg_60412 = false;
        vll_lg_60503 = false;
        vll_lg_74550 = false;
        vll_lg_74630 = false;
        vll_lg_74710 = false;
        vll_lg_57461M = false; 
        vll_novoMotivoInconsistenciaImagem = true;
        return editMotivoInconsistenciaImagem(); 
    }
    
    public String editMotivoInconsistenciaImagem(){
        return "/restrict/cadMotivoInconsistenciaImagem.xhtml"; 
    }

    public String editarMotivoInconsistenciaImagem(Integer nr_codigo){
        motivoInconsistenciaImagem = porNr_codigo(nr_codigo); 
        vll_lg_56732 = (motivoInconsistenciaImagem.getLg_56732() == 1 ? true:false);
        vll_lg_56810 = (motivoInconsistenciaImagem.getLg_56810() == 1 ? true:false);
        vll_lg_56900 = (motivoInconsistenciaImagem.getLg_56900() == 1 ? true:false);
        vll_lg_57030 = (motivoInconsistenciaImagem.getLg_57030() == 1 ? true:false);
        vll_lg_57461 = (motivoInconsistenciaImagem.getLg_57461() == 1 ? true:false);
        vll_lg_57462 = (motivoInconsistenciaImagem.getLg_57462() == 1 ? true:false);
        vll_lg_57463 = (motivoInconsistenciaImagem.getLg_57463() == 1 ? true:false);
        vll_lg_60411 = (motivoInconsistenciaImagem.getLg_60411() == 1 ? true:false);
        vll_lg_60412 = (motivoInconsistenciaImagem.getLg_60412() == 1 ? true:false);
        vll_lg_60503 = (motivoInconsistenciaImagem.getLg_60503() == 1 ? true:false);
        vll_lg_74550 = (motivoInconsistenciaImagem.getLg_74550() == 1 ? true:false);
        vll_lg_74630 = (motivoInconsistenciaImagem.getLg_74630() == 1 ? true:false);
        vll_lg_74710 = (motivoInconsistenciaImagem.getLg_74710() == 1 ? true:false);
        vll_lg_57461M = (motivoInconsistenciaImagem.getLg_57461M() == 1 ? true:false); 
        vll_novoMotivoInconsistenciaImagem = false;
        return editMotivoInconsistenciaImagem(); 
    }    

    public void addMotivoInconsistenciaImagem(){ 

        if (motivoInconsistenciaImagem.getNr_codigo() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe o código!",""));
            return;
        }
        
        motivoInconsistenciaImagem.setLg_56732(vll_lg_56732 == true ? 1:0);
        motivoInconsistenciaImagem.setLg_56810(vll_lg_56810 == true ? 1:0);
        motivoInconsistenciaImagem.setLg_56900(vll_lg_56900 == true ? 1:0);
        motivoInconsistenciaImagem.setLg_57030(vll_lg_57030 == true ? 1:0);
        motivoInconsistenciaImagem.setLg_57461(vll_lg_57461 == true ? 1:0);
        motivoInconsistenciaImagem.setLg_57462(vll_lg_57462 == true ? 1:0);
        motivoInconsistenciaImagem.setLg_57463(vll_lg_57463 == true ? 1:0);
        motivoInconsistenciaImagem.setLg_60411(vll_lg_60411 == true ? 1:0);
        motivoInconsistenciaImagem.setLg_60412(vll_lg_60412 == true ? 1:0);
        motivoInconsistenciaImagem.setLg_60503(vll_lg_60503 == true ? 1:0);
        motivoInconsistenciaImagem.setLg_74550(vll_lg_74550 == true ? 1:0);
        motivoInconsistenciaImagem.setLg_74630(vll_lg_74630 == true ? 1:0);
        motivoInconsistenciaImagem.setLg_74710(vll_lg_74710 == true ? 1:0);
        motivoInconsistenciaImagem.setLg_57461M(vll_lg_57461M == true ? 1:0);


        if (verificaDuplicidade(motivoInconsistenciaImagem.getDc_inconsistencia()) == true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe um motivo inconsistência imagem com o código: " + motivoInconsistenciaImagem.getNr_codigo(),""));
        } else if(vll_novoMotivoInconsistenciaImagem == true ) {
            insertMotivoInconsistenciaImagem();
        } else {
            updateMotivoInconsistenciaImagem();
        }
        
        limpaMotivoInconsistenciaImagem();
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
        
        vlc_sql = "select m.nr_codigo from motivoInconsistenciaImagem m where m.dc_inconsistencia = '" + dc_inconsistencia + "' "; 
        
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consMotivoInconsistenciaImagem = query.list(); 

        if (vll_novoMotivoInconsistenciaImagem == true) {
            if (consMotivoInconsistenciaImagem.size() > 0 ) {
                vll_retorno = true; 
            } else {
                vll_retorno = false; 
            }
        } else {
            if (consMotivoInconsistenciaImagem.size() > 1 ) {
                vll_retorno = true; 
            } else {
                vll_retorno = false; 
            }
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

    public boolean isVll_lg_56732() {
        return vll_lg_56732;
    }

    public void setVll_lg_56732(boolean vll_lg_56732) {
        this.vll_lg_56732 = vll_lg_56732;
    }

    public boolean isVll_lg_56810() {
        return vll_lg_56810;
    }

    public void setVll_lg_56810(boolean vll_lg_56810) {
        this.vll_lg_56810 = vll_lg_56810;
    }

    public boolean isVll_lg_56900() {
        return vll_lg_56900;
    }

    public void setVll_lg_56900(boolean vll_lg_56900) {
        this.vll_lg_56900 = vll_lg_56900;
    }

    public boolean isVll_lg_57030() {
        return vll_lg_57030;
    }

    public void setVll_lg_57030(boolean vll_lg_57030) {
        this.vll_lg_57030 = vll_lg_57030;
    }

    public boolean isVll_lg_57461() {
        return vll_lg_57461;
    }

    public void setVll_lg_57461(boolean vll_lg_57461) {
        this.vll_lg_57461 = vll_lg_57461;
    }

    public boolean isVll_lg_57462() {
        return vll_lg_57462;
    }

    public void setVll_lg_57462(boolean vll_lg_57462) {
        this.vll_lg_57462 = vll_lg_57462;
    }

    public boolean isVll_lg_57463() {
        return vll_lg_57463;
    }

    public void setVll_lg_57463(boolean vll_lg_57463) {
        this.vll_lg_57463 = vll_lg_57463;
    }

    public boolean isVll_lg_60411() {
        return vll_lg_60411;
    }

    public void setVll_lg_60411(boolean vll_lg_60411) {
        this.vll_lg_60411 = vll_lg_60411;
    }

    public boolean isVll_lg_60412() {
        return vll_lg_60412;
    }

    public void setVll_lg_60412(boolean vll_lg_60412) {
        this.vll_lg_60412 = vll_lg_60412;
    }

    public boolean isVll_lg_60503() {
        return vll_lg_60503;
    }

    public void setVll_lg_60503(boolean vll_lg_60503) {
        this.vll_lg_60503 = vll_lg_60503;
    }

    public boolean isVll_lg_74550() {
        return vll_lg_74550;
    }

    public void setVll_lg_74550(boolean vll_lg_74550) {
        this.vll_lg_74550 = vll_lg_74550;
    }

    public boolean isVll_lg_74630() {
        return vll_lg_74630;
    }

    public void setVll_lg_74630(boolean vll_lg_74630) {
        this.vll_lg_74630 = vll_lg_74630;
    }

    public boolean isVll_lg_74710() {
        return vll_lg_74710;
    }

    public void setVll_lg_74710(boolean vll_lg_74710) {
        this.vll_lg_74710 = vll_lg_74710;
    }

    public boolean isVll_lg_57461M() {
        return vll_lg_57461M;
    }

    public void setVll_lg_57461M(boolean vll_lg_57461M) {
        this.vll_lg_57461M = vll_lg_57461M;
    }
        
    

}
