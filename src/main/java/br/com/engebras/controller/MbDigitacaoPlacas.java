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
import br.com.engebras.model.entities.AutoInfracao;
import br.com.engebras.model.entities.AutoInfracaoImagem;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "mbDigitacaoPlacas")
@ViewScoped
public class MbDigitacaoPlacas implements Serializable{

    private static final long serialVersionUID = 1L; 
    
    private AutoInfracao autoInfracao = new  AutoInfracao(); 
    
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

    public MbDigitacaoPlacas(){
        proximaInfracao();
    }
    
    private InterfaceDAO<AutoInfracao> autoInfracaoDAO() {
        InterfaceDAO<AutoInfracao> autoInfracaoDAO = new HibernateDAO<AutoInfracao>(AutoInfracao.class, FacesContextUtil.getRequestSession());
        return autoInfracaoDAO;
    }

    public String limpaInfracao(){
        autoInfracao = new AutoInfracao(); 
        return editAutoInfracao(); 
    }
    
    public String editAutoInfracao(){
        return "/restrict/digitacaoPlacas.xhtml";
    }

    public String inicioPagina(){
        return "/Inicio.xhtml";
    }
    
    public boolean proximaInfracao(){
        String vlc_sql = ""; 
        int vln_resultado = 0;
        String dc_nr_multa = " ";
        List consInfracao;
        SQLQuery query;
        Session session = FacesContextUtil.getRequestSession();
                
        vlc_sql = "update autoInfracao set lg_uso = 1, nr_usuarioDigitacao = 9000 where nr_status = 1 and lg_uso = 0 limit 1 ";

        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        
        vlc_sql = "select a.* from autoInfracao a where a.nr_status = 1 and a.lg_uso = 1 and a.nr_usuarioDigitacao = 9000 limit 1 ";
        
        query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consInfracao = query.list();

        for (Object oInfracao : consInfracao) {
            Map row = (Map) oInfracao;
            
            dc_nr_multa = row.get("dc_nr_multa").toString();
        }        
        
        if (!dc_nr_multa.equals(" ")){
            autoInfracao = porDc_nr_multa(dc_nr_multa);
            return true;
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Não existe mais infrações para digitação...", ""));
            return false;
        }
        
    }
    
    public AutoInfracao porDc_nr_multa(String dc_nr_multa) {

        return autoInfracaoDAO().getEntity(dc_nr_multa);
    }

    public AutoInfracao getAutoInfracao() {
        return autoInfracao;
    }

    public void setAutoInfracao(AutoInfracao autoInfracao) {
        this.autoInfracao = autoInfracao;
    }

    
}
