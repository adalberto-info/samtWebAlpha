/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 12/09/2016
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.engebras.util.FacesContextUtil;
import br.com.engebras.util.HibernateUtil;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.entities.LocalVelocidade;
import br.com.engebras.filter.LocalVelocidadeFilter;

@ManagedBean(name = "mbLocalVelocidade" )
@SessionScoped
public class MbLocalVelocidade implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private LocalVelocidade localVelocidade = new LocalVelocidade();
    private Integer vpn_nr_codLocal;
    private List<LocalVelocidade> localVelocidadeFiltrados;
    private LocalVelocidade localVelocidadeSelecionado;
    public LocalVelocidadeFilter filtro; 
    
    public MbLocalVelocidade(){
        filtro = new LocalVelocidadeFilter();
    }
    
    private InterfaceDAO<LocalVelocidade> localVelocidadeDAO() {
        InterfaceDAO<LocalVelocidade> localVelocidadeDAO = new HibernateDAO<LocalVelocidade>(LocalVelocidade.class, FacesContextUtil.getRequestSession());
        return localVelocidadeDAO;
    }
    
    public String limpaLocalVelocidade(){
        localVelocidade = new LocalVelocidade(); 
        return editLocalVelocidade(); 
    }
    
    public String editLocalVelocidade(){
        return "/restrict/cadlLocalInfracao_velocidade.xhtml";
    }
    
    public String editarLocalVelocidade(Integer nr_codigo){
        this.localVelocidade = porNr_codigo(nr_codigo);
        return editLocalVelocidade();
    }
    
    public void addLocalVelocidade(Integer nr_codLocal){
        vpn_nr_codLocal = nr_codLocal;
        localVelocidade.setNr_codLocal(nr_codLocal);
        if (verificaDuplicidade(localVelocidade.getNr_velocidadePermitida()) == true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe uma velocidade permitida cadastrada com a velocidade: " + localVelocidade.getNr_velocidadePermitida() + ".",""));
        } else if(localVelocidade.getNr_codigo() == null || localVelocidade.getNr_codigo() == 0){
            insertLocalVelocidade();
        } else {
            updateLocalVelocidade();
        }
        
        limpaLocalVelocidade();
    }
    
    public void insertLocalVelocidade() {
        localVelocidadeDAO().save(localVelocidade);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!!!", ""));
    }
    
    public void updateLocalVelocidade() {
        localVelocidadeDAO().update(localVelocidade);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!!!", ""));
    }
    
    public void deleteLocalVelocidade() {
        localVelocidadeDAO().remove(localVelocidade);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!", ""));
    }

    private boolean verificaDuplicidade(Integer nr_velocidadePermitida) {
        boolean vll_retorno = false;
        String vlc_sql = "";
        List consLocalVelocidade;

        Session session = FacesContextUtil.getRequestSession();
        vlc_sql = "select a.nr_velocidadePermitida from localVelocidade a ";
        vlc_sql += "where a.nr_velocidadePermitida = " + nr_velocidadePermitida + " ";
        vlc_sql += "and a.nr_codLocal = " + vpn_nr_codLocal;
        
        if (localVelocidade.getNr_codigo() != null && localVelocidade.getNr_codigo() != 0)
            vlc_sql = vlc_sql + "and a.nr_codigo <> " + localVelocidade.getNr_codigo();
        
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consLocalVelocidade = query.list();

        if (consLocalVelocidade.size() > 0) {
            vll_retorno = true;
        } else {
            vll_retorno = false;
        }

        for (Object oLocalVelocidade : consLocalVelocidade) {
            Map row = (Map) oLocalVelocidade;
        }

        consLocalVelocidade = null;

        return vll_retorno;
    }

    public LocalVelocidade porNr_codigo(Integer nr_codigo) {

        return localVelocidadeDAO().getEntity(nr_codigo);
    }

     public void pesquisar(){
        localVelocidadeFiltrados = filtrados(filtro);
    }

    
    public List<LocalVelocidade> filtrados(LocalVelocidadeFilter filtro) {

        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(LocalVelocidade.class);
        
        if (filtro.getNr_codLocal() != 0){
            criteria.add(Restrictions.eq("nr_codLocal",filtro.getNr_codLocal()));
        }
     
        return criteria.addOrder(Order.asc("nr_codLocal")).list();
    }
    
    public void inicializar(Integer nr_codLocal){
         vpn_nr_codLocal = nr_codLocal; 
    }
    
    
    public LocalVelocidade getLocalVelocidade() {
        return localVelocidade;
    }

    public void setLocalVelocidade(LocalVelocidade localVelocidade) {
        this.localVelocidade = localVelocidade;
    }

    public Integer getVpn_nr_codLocal() {
        return vpn_nr_codLocal;
    }

    public void setVpn_nr_codLocal(Integer vpn_nr_codLocal) {
        this.vpn_nr_codLocal = vpn_nr_codLocal;
    }

    public List<LocalVelocidade> getLocalVelocidadeFiltrados() {
        return localVelocidadeFiltrados;
    }

    public void setLocalVelocidadeFiltrados(List<LocalVelocidade> localVelocidadeFiltrados) {
        this.localVelocidadeFiltrados = localVelocidadeFiltrados;
    }

    public LocalVelocidade getLocalVelocidadeSelecionado() {
        return localVelocidadeSelecionado;
    }

    public void setLocalVelocidadeSelecionado(LocalVelocidade localVelocidadeSelecionado) {
        this.localVelocidadeSelecionado = localVelocidadeSelecionado;
    }

    public LocalVelocidadeFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(LocalVelocidadeFilter filtro) {
        this.filtro = filtro;
    }

    
}
