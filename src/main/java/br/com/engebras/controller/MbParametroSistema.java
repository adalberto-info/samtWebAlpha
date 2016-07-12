/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 12/07/2016
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
import br.com.engebras.model.entities.ParametroSistema;

@ManagedBean(name="mbParametroSistema")
@SessionScoped
public class MbParametroSistema implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private ParametroSistema parametroSistema = new ParametroSistema();
    private List<ParametroSistema> parametroSistemas; 
    
    public MbParametroSistema(){
        
    }
    
    private InterfaceDAO<ParametroSistema> parametroSistemaDAO() {
        InterfaceDAO<ParametroSistema> parametroSistemaDAO = new HibernateDAO<ParametroSistema>(ParametroSistema.class, FacesContextUtil.getRequestSession()); 
        return parametroSistemaDAO; 
    }


    public String limpaParametroSistema(){
        parametroSistema = new ParametroSistema(); 
        return editParametroSistema();
    }

    public String editParametroSistema(){
        return  "/restrict/cadParametroSistema.faces"; 
    }

    public String editarParametroSistema(Integer nr_codigo){

        parametroSistema = porNr_codigo(); 
        return editParametroSistema(); 
    }

    public void addParametroSistema(){
        if (VerificaDuplicidade(parametroSistema.getDc_parametro()) == true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe um parâmetro cadastrado com o parâmetro: " + parametroSistema.getDc_parametro()+ ".", ""));
        } else if (parametroSistema.getNr_codigo() == null || parametroSistema.getNr_codigo() == 0){
            insertParametroSistema(); 
        } else {
            updateParametroSistema(); 
        }
    }

    public void insertParametroSistema(){
        parametroSistemaDAO().save(parametroSistema);
    
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!!!",""));
    }

    public void updateParametroSistema(){
        parametroSistemaDAO().update(parametroSistema);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!!!", "")); 
        
    }
    
    private boolean verificaDuplicidade(String dc_parametro){
        
        boolean vll_retorno = false; 
        String vlc_sql = ""; 
        List consParametroSistema; 
        
        Session session = FacesContextUtil.getRequestSession();
        vlc_sql = "select p.dc_parametro from parametroSistema p where dc_parametro = '" + dc_parametro + "' "; 
        if (parametroSistema.getNr_codigo() != null && parametroSistema.getNr_codigo() != 0)
            vlc_sql += "where p.nr_codigo <> " + parametroSistema.getNr_codigo();

        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consParametroSistema = query.list(); 
        
        if (consParametroSistema.size()>0){
            vll_retorno = true;
        } else {
            vll_retorno = false; 
        }
        
        for (Object oParametroSistema : consParametroSistema){
            Map row = (Map) oParametroSistema; 
        }
        
        consParametroSistema = null; 
        
        return vll_retorno; 
    }

    
    public ParametroSistema porNr_codigo(Integer nr_codigo) {
        return parametroSistemaDAO().getEntity(nr_codigo); 
    }

    public ParametroSistema getParametroSistema() {
        return parametroSistema;
    }

    public void setParametroSistema(ParametroSistema parametroSistema) {
        this.parametroSistema = parametroSistema;
    }

    public List<ParametroSistema> getParametroSistemas() {
        return parametroSistemas;
    }

    public void setParametroSistemas(List<ParametroSistema> parametroSistemas) {
        this.parametroSistemas = parametroSistemas;
    }

    
    
    
    
    
    
}
