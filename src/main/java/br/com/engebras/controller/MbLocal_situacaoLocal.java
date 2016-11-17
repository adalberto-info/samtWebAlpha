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
import br.com.engebras.filter.Local_situacaoLocalFilter; 
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.entities.Local_situacaoLocal;
import br.com.engebras.model.entities.SituacaoLocal;
import br.com.engebras.model.entities.SituacaoLocal;

@ManagedBean(name="mbLocal_situacaoLocal")
@SessionScoped
public class MbLocal_situacaoLocal implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private Local_situacaoLocal local_situacaoLocal = new Local_situacaoLocal(); 
    private Integer vpn_nr_codLocal; 
    private List<Local_situacaoLocal> local_situacaoLocalFiltrados; 
    private Local_situacaoLocal local_situacaoLocalSelecionado; 
    private Local_situacaoLocalFilter filtro; 
    private List situacaoLocais = new ArrayList<>();
    
    public MbLocal_situacaoLocal(){
        filtro = new Local_situacaoLocalFilter(); 
        geraListaSituacaoLocais();
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

    public void updateLocal_situacaoLocal() {
        local_situacaoLocalDAO().update(local_situacaoLocal);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!!!", ""));
    }
   
    public void deleteLocal_situacaoLocal(Integer nr_codigo) {
        this.local_situacaoLocal = porNr_codigo(nr_codigo);
        local_situacaoLocalDAO().remove(local_situacaoLocal);
        pesquisar(vpn_nr_codLocal);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!", ""));
    }

    private boolean verificaDuplicidade(String dc_codSituacao) {
        boolean vll_retorno = false;
        String vlc_sql = "";
        List consLocal_situacaoLocal;

        Session session = FacesContextUtil.getRequestSession();
        vlc_sql = "select a.nr_codigo, a.dc_codSituacao, a.nr_codLocal, a.dt_inicio, a.dt_fim from local_situacaoLocal a ";
        vlc_sql += "where a.dc_codSituacao = '" + dc_codSituacao + "' ";
        vlc_sql += "and a.nr_codLocal = " + vpn_nr_codLocal + " ";
        vlc_sql += "and a.dt_inicio <= '" + local_situacaoLocal.getDt_fim() + "' ";
        vlc_sql += "and a.dt_fim >= '" + local_situacaoLocal.getDt_inicio() + "' ";
        
        if (local_situacaoLocal.getNr_codigo() != null && local_situacaoLocal.getNr_codigo() != 0)
            vlc_sql = vlc_sql + "and a.nr_codigo <> " + local_situacaoLocal.getNr_codigo();
        
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consLocal_situacaoLocal = query.list();

        if (consLocal_situacaoLocal.size() > 0) {
            vll_retorno = true;
        } else {
            vll_retorno = false;
        }

        for (Object oLocal_situacaoLocal : consLocal_situacaoLocal) {
            Map row = (Map) oLocal_situacaoLocal;
        }

        consLocal_situacaoLocal = null;

        return vll_retorno;
    }
    
    public void pesquisar(Integer nr_codLocal){

         vpn_nr_codLocal = nr_codLocal;
         local_situacaoLocalFiltrados = filtrados(filtro);
    }

    public List<Local_situacaoLocal> filtrados(Local_situacaoLocalFilter filtro) {

        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(Local_situacaoLocal.class);
        
        if (vpn_nr_codLocal != null && vpn_nr_codLocal != 0){
            criteria.add(Restrictions.eq("nr_codLocal",vpn_nr_codLocal));
        }
     
        return criteria.addOrder(Order.asc("dt_inicio")).list();
    }
    
    public void inicializar(Integer nr_codLocal){
         vpn_nr_codLocal = nr_codLocal; 
    }
    
    private void geraListaSituacaoLocais(){
        List listaSQL; 
        String vlc_sql; 
        vlc_sql = "select a.dc_codigo, a.dc_descricao ";
        vlc_sql += "from situacaoLocal a ";
        vlc_sql += "order by a.dc_codigo ";
        
        Session session = FacesContextUtil.getRequestSession();
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        listaSQL = query.list();
        
        this.situacaoLocais = listaSQL; 

    }
    

    public Local_situacaoLocal getLocal_situacaoLocal() {
        return local_situacaoLocal;
    }

    public void setLocal_situacaoLocal(Local_situacaoLocal local_situacaoLocal) {
        this.local_situacaoLocal = local_situacaoLocal;
    }

    public Integer getVpn_nr_codLocal() {
        return vpn_nr_codLocal;
    }

    public void setVpn_nr_codLocal(Integer vpn_nr_codLocal) {
        this.vpn_nr_codLocal = vpn_nr_codLocal;
    }

    public List<Local_situacaoLocal> getLocal_situacaoLocalFiltrados() {
        return local_situacaoLocalFiltrados;
    }

    public void setLocal_situacaoLocalFiltrados(List<Local_situacaoLocal> local_situacaoLocalFiltrados) {
        this.local_situacaoLocalFiltrados = local_situacaoLocalFiltrados;
    }

    public Local_situacaoLocal getLocal_situacaoLocalSelecionado() {
        return local_situacaoLocalSelecionado;
    }

    public void setLocal_situacaoLocalSelecionado(Local_situacaoLocal local_situacaoLocalSelecionado) {
        this.local_situacaoLocalSelecionado = local_situacaoLocalSelecionado;
    }

    public Local_situacaoLocalFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(Local_situacaoLocalFilter filtro) {
        this.filtro = filtro;
    }

    public List getSituacaoLocais() {
        return situacaoLocais;
    }

    public void setSituacaoLocais(List situacaoLocais) {
        this.situacaoLocais = situacaoLocais;
    }
    


}
