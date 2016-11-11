package br.com.engebras.controller;

/**
 *
 * @author Adalberto Kamida
 * dt. criacao: 02/11/2016
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import java.util.Date;
import org.hibernate.sql.JoinType;

import br.com.engebras.util.FacesContextUtil;
import br.com.engebras.util.HibernateUtil;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.entities.LocalCliente;
import br.com.engebras.filter.LocalClienteFilter;
import br.com.engebras.model.entities.TipoFiscalizacao;

@ManagedBean(name = "mbLocalCliente" )
@SessionScoped
public class MbLocalCliente implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    private LocalCliente localCliente = new LocalCliente();
    private Integer vpn_nr_codLocal; 
    private List<LocalCliente> localClienteFiltrados; 
    private LocalCliente localClienteSelecionado; 
    private List tipoFiscalizacoes = new ArrayList<>();
    public LocalClienteFilter filtro; 
    private Date dt_atual;
    
    public MbLocalCliente(){
        filtro = new LocalClienteFilter(); 
        geraListaTipoFiscalizacoes();
    }
    
    private InterfaceDAO<LocalCliente> localClienteDAO() {
        InterfaceDAO<LocalCliente> localClienteDAO = new HibernateDAO<LocalCliente>(LocalCliente.class, FacesContextUtil.getRequestSession());
        return localClienteDAO;
    }

    public String limpaLocalCliente(){
        localCliente = new LocalCliente(); 
        return editLocalCliente(); 
    }

    public String editLocalCliente(){

        return "";
//        return "/restrict/cadLocalInfracao_LocalCliente.xhtml";
   }

    public String editarLocalCliente(Integer nr_codigo){
        this.localCliente = porNr_codigo(nr_codigo);
        return editLocalCliente();
    }

    public void addLocalCliente(Integer nr_codLocal){
        vpn_nr_codLocal = nr_codLocal;
        localCliente.setNr_codLocal(nr_codLocal);
        if(localCliente.getNr_codigo() == null || localCliente.getNr_codigo() == 0){
            dt_atual = new Date();
            localCliente.setDt_inclusao(dt_atual);
            insertLocalCliente();
        } else {
            updateLocalCliente();
        }
        
        pesquisar(vpn_nr_codLocal);
        limpaLocalCliente();
    }
    
    public void insertLocalCliente() {
        localClienteDAO().save(localCliente);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!!!", ""));
    }

    public void updateLocalCliente() {
        localClienteDAO().update(localCliente);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!!!", ""));
    }
    
    public void deleteLocalCliente(Integer nr_codigo) {
        this.localCliente = porNr_codigo(nr_codigo);
        localClienteDAO().remove(localCliente);
        pesquisar(vpn_nr_codLocal);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!", ""));
    }

    private boolean verificaDuplicidade(Integer nr_codLocalCliente) {
        boolean vll_retorno = false;
        String vlc_sql = "";
        List consLocalCliente;

        Session session = FacesContextUtil.getRequestSession();
        vlc_sql = "select a.nr_codLocalCliente from localCliente a ";
        vlc_sql += "where a.nr_codLocalCliente = " + nr_codLocalCliente + " ";
        vlc_sql += "and a.nr_codLocal = " + vpn_nr_codLocal + " ";
        
        if (localCliente.getNr_codigo() != null && localCliente.getNr_codigo() != 0)
            vlc_sql = vlc_sql + "and a.nr_codigo <> " + localCliente.getNr_codigo();
        
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consLocalCliente = query.list();

        if (consLocalCliente.size() > 0) {
            vll_retorno = true;
        } else {
            vll_retorno = false;
        }

        for (Object oLocalCliente : consLocalCliente) {
            Map row = (Map) oLocalCliente;
        }

        consLocalCliente = null;

        return vll_retorno;
    }

    public LocalCliente porNr_codigo(Integer nr_codigo) {

        return localClienteDAO().getEntity(nr_codigo);
    }
    
    
    public void pesquisar(Integer nr_codLocal){

         vpn_nr_codLocal = nr_codLocal;
         localClienteFiltrados = filtrados(filtro);
    }
    
    public List<LocalCliente> filtrados(LocalClienteFilter filtro) {

        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(LocalCliente.class);
        
        if (vpn_nr_codLocal != null && vpn_nr_codLocal != 0){
            criteria.add(Restrictions.eq("nr_codLocal",vpn_nr_codLocal));
        }
     
        return criteria.addOrder(Order.asc("nr_codigo")).list();

    }
    
    public void inicializar(Integer nr_codLocal){
         vpn_nr_codLocal = nr_codLocal; 
    }

    public void geraListaTipoFiscalizacoes() {
        List listaSQL;
        String vlc_sql;
        vlc_sql = "select nr_codigo, dc_descricao from tipoFiscalizacao order by dc_descricao";

        Session session = FacesContextUtil.getRequestSession();

        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        listaSQL = query.list();

        this.tipoFiscalizacoes = listaSQL;
    }

    public LocalCliente getLocalCliente() {
        return localCliente;
    }

    public void setLocalCliente(LocalCliente localCliente) {
        this.localCliente = localCliente;
    }

    public Integer getVpn_nr_codLocal() {
        return vpn_nr_codLocal;
    }

    public void setVpn_nr_codLocal(Integer vpn_nr_codLocal) {
        this.vpn_nr_codLocal = vpn_nr_codLocal;
    }

    public List<LocalCliente> getLocalClienteFiltrados() {
        return localClienteFiltrados;
    }

    public void setLocalClienteFiltrados(List<LocalCliente> localClienteFiltrados) {
        this.localClienteFiltrados = localClienteFiltrados;
    }

    public LocalCliente getLocalClienteSelecionado() {
        return localClienteSelecionado;
    }

    public void setLocalClienteSelecionado(LocalCliente localClienteSelecionado) {
        this.localClienteSelecionado = localClienteSelecionado;
    }

    public List getTipoFiscalizacoes() {
        return tipoFiscalizacoes;
    }

    public void setTipoFiscalizacoes(List tipoFiscalizacoes) {
        this.tipoFiscalizacoes = tipoFiscalizacoes;
    }

    public LocalClienteFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(LocalClienteFilter filtro) {
        this.filtro = filtro;
    }

    public Date getDt_atual() {
        return dt_atual;
    }

    public void setDt_atual(Date dt_atual) {
        this.dt_atual = dt_atual;
    }
    
    

}
