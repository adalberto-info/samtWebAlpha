/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 24/10/2016
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
import br.com.engebras.model.entities.Local_equipamento;
import br.com.engebras.filter.Local_equipamentoFilter; 
import br.com.engebras.model.entities.Equipamento;

@ManagedBean(name="mbLocal_equipamento")
@SessionScoped
public class MbLocal_equipamento {

    private static final long serialVersionUID = 1L; 

    
    private Local_equipamento local_equipamento = new Local_equipamento();
    private Integer vpn_nr_codLocal; 
    private List<Local_equipamento> local_equipamentoFiltrados; 
    private Local_equipamento local_equipamentoSelecionado; 
    public Local_equipamentoFilter filtro; 
    private List serieEquipamentos = new ArrayList<>();
    
    
    public MbLocal_equipamento(){
         geraListaEquipamentos();
    }
    
    private InterfaceDAO<Local_equipamento> local_equipamentoDAO() {
        InterfaceDAO<Local_equipamento> local_equipamentoDAO = new HibernateDAO<Local_equipamento>(Local_equipamento.class, FacesContextUtil.getRequestSession());
        return local_equipamentoDAO;
    }

    public void limpaLocal_equipamento(){
        local_equipamento = new Local_equipamento();
    }
    
    public String editLocal_equipamento(){
        return "/restrict/cadLocalInfracao_equipamento.xhtml";
    }


    public String editarLocal_equipamento(Integer nr_codigo){
        this.local_equipamento = porNr_codigo(nr_codigo);
        return editLocal_equipamento();
    }
    
    public Local_equipamento porNr_codigo(Integer nr_codigo) {

        return local_equipamentoDAO().getEntity(nr_codigo);
    }

    public void addLocal_equipamento(Integer nr_codLocal){
        vpn_nr_codLocal = nr_codLocal;
        local_equipamento.setNr_codLocal(nr_codLocal);
        if (verificaDuplicidade(local_equipamento.getDc_serieEquipamento()) == true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe um equipamento: " + local_equipamento.getDc_serieEquipamento() + " para o local: " + local_equipamento.getNr_codLocal() + ".",""));
        } else if(local_equipamento.getNr_codigo() == null || local_equipamento.getNr_codigo() == 0){
            insertLocal_equipamento();
        } else {
            updateLocal_equipamento();
        }
        
        pesquisar(vpn_nr_codLocal);
        limpaLocal_equipamento();
    }

   public void insertLocal_equipamento() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executei o insertLocal_equipamento!!!", ""));
        local_equipamentoDAO().save(local_equipamento);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!!!", ""));
    }

    public void updateLocal_equipamento() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executei o updateLocal_equipamento!!!", ""));
        local_equipamentoDAO().update(local_equipamento);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!!!", ""));
    }

    public void deleteLocal_equipamento(Integer nr_codigo) {
        this.local_equipamento = porNr_codigo(nr_codigo);
        local_equipamentoDAO().remove(local_equipamento);
        pesquisar(vpn_nr_codLocal);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!", ""));
    }
   
    private boolean verificaDuplicidade(String dc_serieEquipamento) {
        boolean vll_retorno = false;
        String vlc_sql = "";
        List consLocal_equipamento;

        Session session = FacesContextUtil.getRequestSession();
        vlc_sql = "select a.nr_codigo, a.dc_serieEquipamento, a.nr_codLocal from local_equipamento a ";
        vlc_sql += "where a.dc_serieEquipamento = " + dc_serieEquipamento + " ";
        vlc_sql += "and a.nr_codLocal = " + vpn_nr_codLocal + " ";
        
        if (local_equipamento.getNr_codigo() != null && local_equipamento.getNr_codigo() != 0)
            vlc_sql = vlc_sql + "and a.nr_codigo <> " + local_equipamento.getNr_codigo();
        
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consLocal_equipamento = query.list();

        if (consLocal_equipamento.size() > 0) {
            vll_retorno = true;
        } else {
            vll_retorno = false;
        }

        for (Object oLocal_equipamento : consLocal_equipamento) {
            Map row = (Map) oLocal_equipamento;
        }

        consLocal_equipamento = null;

        return vll_retorno;
    }

    public void pesquisar(Integer nr_codLocal){

         vpn_nr_codLocal = nr_codLocal;
         local_equipamentoFiltrados = filtrados(filtro);
    }

    public List<Local_equipamento> filtrados(Local_equipamentoFilter filtro) {

        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(Local_equipamento.class);
        
        if (vpn_nr_codLocal != null && vpn_nr_codLocal != 0){
            criteria.add(Restrictions.eq("nr_codLocal",vpn_nr_codLocal));
        }
     
        return criteria.addOrder(Order.asc("nr_codLocal")).list();
    }

    public void inicializar(Integer nr_codLocal){
         vpn_nr_codLocal = nr_codLocal; 
    }

    public void geraListaEquipamentos(){
        List listaSQL; 
        String vlc_sql; 
        vlc_sql = "select a.dc_serieEquipamento ";
        vlc_sql += "from equipamento a ";
        vlc_sql += "order by a.dc_serieEquipamento ";
        
        Session session = FacesContextUtil.getRequestSession();
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        listaSQL = query.list();
        
        this.serieEquipamentos = listaSQL; 
    }

    public Local_equipamento getLocal_equipamento() {
        return local_equipamento;
    }

    public void setLocal_equipamento(Local_equipamento local_equipamento) {
        this.local_equipamento = local_equipamento;
    }

    public Integer getVpn_nr_codLocal() {
        return vpn_nr_codLocal;
    }

    public void setVpn_nr_codLocal(Integer vpn_nr_codLocal) {
        this.vpn_nr_codLocal = vpn_nr_codLocal;
    }

    public List<Local_equipamento> getLocal_equipamentoFiltrados() {
        return local_equipamentoFiltrados;
    }

    public void setLocal_equipamentoFiltrados(List<Local_equipamento> local_equipamentoFiltrados) {
        this.local_equipamentoFiltrados = local_equipamentoFiltrados;
    }

    public Local_equipamento getLocal_equipamentoSelecionado() {
        return local_equipamentoSelecionado;
    }

    public void setLocal_equipamentoSelecionado(Local_equipamento local_equipamentoSelecionado) {
        this.local_equipamentoSelecionado = local_equipamentoSelecionado;
    }

    public Local_equipamentoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(Local_equipamentoFilter filtro) {
        this.filtro = filtro;
    }

    public List getSerieEquipamentos() {
        return serieEquipamentos;
    }

    public void setSerieEquipamentos(List serieEquipamentos) {
        this.serieEquipamentos = serieEquipamentos;
    }
    
}
