/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 12/08/2016
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

import br.com.engebras.util.FacesContextUtil;
import br.com.engebras.util.HibernateUtil;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.entities.LocalInfracao;
import br.com.engebras.model.entities.TipoFixacaoRadar;
import br.com.engebras.model.entities.StatusLocal;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "mbLocalInfracao")
@SessionScoped
public class MbLocalInfracao implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalInfracao localInfracao = new LocalInfracao();
    private List<LocalInfracao> localInfracoes;
    private List tipoFixacaoRadares = new ArrayList<>();
    private List statusLocais = new ArrayList<>();
    private List municipios = new ArrayList<>();
    private List ufs = new ArrayList<>();
    private String dc_codStatus;
    private boolean lg_ativo = true;
    private boolean lg_novoRegistro;
    private boolean lg_velocidadeDifPorte;
    private Date dt_atual;

    public MbLocalInfracao() {
        lg_novoRegistro = true;
        geraListaTipoFixacaoRadares();
        geraListaStatusLocais();
        geraListaUfs();
    }

    public void init() {
        dc_codStatus = " ";
        lg_ativo = true;
        lg_velocidadeDifPorte = false;

    }

    private InterfaceDAO<LocalInfracao> localInfracaoDAO() {
        InterfaceDAO<LocalInfracao> localInfracaoDAO = new HibernateDAO<LocalInfracao>(LocalInfracao.class, FacesContextUtil.getRequestSession());
        return localInfracaoDAO;
    }

    public String limpaLocalInfracao() {
        localInfracao = new LocalInfracao();
        lg_novoRegistro = true;
        return editLocalInfracao();
    }

    public String editLocalInfracao() {
        return "/restrict/cadLocalInfracao.xhtml";
    }

    public String editarLocalInfracao(Integer nr_codigo) {
        this.localInfracao = porNr_codigo(nr_codigo);
        lg_novoRegistro = false;
        return editLocalInfracao();
    }

    public void addLocalInfracao() {
        
        dt_atual = new Date();
        localInfracao.setDt_ultimaAtualizacao(dt_atual);

        if (lg_ativo == true){
            localInfracao.setLg_ativo(1);
        } else {
            localInfracao.setLg_ativo(0);
        }

        if (lg_velocidadeDifPorte == true){
            localInfracao.setLg_velocidadeDifPorte(1);
        } else {
            localInfracao.setLg_velocidadeDifPorte(0);
        }
        

        if (verificaDuplicidade(localInfracao.getDc_local()) == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Já existe um local infração cadastrado com o código:" + localInfracao.getNr_codigo() + ".", ""));
        } else if (localInfracao.getNr_codigo() == null || localInfracao.getNr_codigo() == 0) {
            insertLocalInfracao();
        } else {
            updateLocalInfracao();
        }
        //limpaLocalInfracao();
    }

    public void insertLocalInfracao() {
        localInfracaoDAO().save(localInfracao);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!!!", ""));
    }

    public void updateLocalInfracao() {
        localInfracaoDAO().update(localInfracao);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!!!", ""));
    }

    private boolean verificaDuplicidade(String dc_local) {
        boolean vll_retorno = false;
        String vlc_sql = "";
        List consLocalInfracao;

        Session session = FacesContextUtil.getRequestSession();
        vlc_sql = "select a.nr_codigo, a.dc_local  from localInfracao a where a.dc_local = '" + dc_local + "' ";
        if (localInfracao.getNr_codigo() != null && localInfracao.getNr_codigo() != 0) {
            vlc_sql = vlc_sql + "and a.nr_codigo <> " + localInfracao.getNr_codigo();
        }

        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consLocalInfracao = query.list();

        if (consLocalInfracao.size() > 0) {
            vll_retorno = true;
        } else {
            vll_retorno = false;
        }

        for (Object oLocalInfracao : consLocalInfracao) {
            Map row = (Map) oLocalInfracao;
        }

        consLocalInfracao = null;

        return vll_retorno;
    }

    public LocalInfracao porNr_codigo(Integer nr_codigo) {

        return localInfracaoDAO().getEntity(nr_codigo);
    }

    public void geraListaTipoFixacaoRadares() {
        List listaSQL;
        String vlc_sql;
        vlc_sql = "select nr_codigo, dc_descricao from tipoFixacaoRadar order by nr_codigo";

        Session session = FacesContextUtil.getRequestSession();

        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        listaSQL = query.list();

        this.tipoFixacaoRadares = listaSQL;
    }

    public void geraListaStatusLocais() {
        List listaSQL;
        String vlc_sql;
        vlc_sql = "select nr_codigo, dc_descricao from statusLocal order by nr_codigo";

        Session session = FacesContextUtil.getRequestSession();

        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        listaSQL = query.list();

        this.statusLocais = listaSQL;
    }

    public void geraListaMunicipios() {
        List listaSQL;
        String vlc_sql;
        vlc_sql = "select concat(a.dc_uf, ' | ', a.dc_municipio) as dc_municipio, a.nr_codigo, a.dc_uf ";
        vlc_sql += "from municipio a ";
        if ( localInfracao.getDc_uf() != null && !localInfracao.getDc_uf().equals("") ){
            vlc_sql += "where a.dc_uf = '" + localInfracao.getDc_uf() + "' ";
        }
        vlc_sql += "order by a.dc_uf, a.dc_municipio ";

        Session session = FacesContextUtil.getRequestSession();
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        listaSQL = query.list();

        this.municipios = listaSQL;
    }

    public void geraListaUfs() {
        List listaSQL;
        String vlc_sql;
        vlc_sql = "select dc_uf, dc_descricao from uf order by dc_uf";

        Session session = FacesContextUtil.getRequestSession();

        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        listaSQL = query.list();

        this.ufs = listaSQL;
    }
    

    public LocalInfracao getLocalInfracao() {
        return localInfracao;
    }

    public void setLocalInfracao(LocalInfracao localInfracao) {
        this.localInfracao = localInfracao;
    }

    public List<LocalInfracao> getLocalInfracoes() {
        return localInfracoes;
    }

    public void setLocalInfracoes(List<LocalInfracao> localInfracoes) {
        this.localInfracoes = localInfracoes;
    }

    public String getDc_codStatus() {
        return dc_codStatus;
    }

    public void setDc_codStatus(String dc_codStatus) {
        this.dc_codStatus = dc_codStatus;
    }

    public boolean isLg_ativo() {
        return lg_ativo;
    }

    public void setLg_ativo(boolean lg_ativo) {
        this.lg_ativo = lg_ativo;
    }

    public List getTipoFixacaoRadares() {
        return tipoFixacaoRadares;
    }

    public void setTipoFixacaoRadares(List tipoFixacaoRadares) {
        this.tipoFixacaoRadares = tipoFixacaoRadares;
    }

    public boolean isLg_novoRegistro() {
        return lg_novoRegistro;
    }

    public void setLg_novoRegistro(boolean lg_novoRegistro) {
        this.lg_novoRegistro = lg_novoRegistro;
    }

    public List getStatusLocais() {
        return statusLocais;
    }

    public void setStatusLocais(List statusLocais) {
        this.statusLocais = statusLocais;
    }

    public List getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List municipios) {
        this.municipios = municipios;
    }

    public Date getDt_atual() {
        return dt_atual;
    }

    public void setDt_atual(Date dt_atual) {
        this.dt_atual = dt_atual;
    }

    public List getUfs() {
        return ufs;
    }

    public void setUfs(List ufs) {
        this.ufs = ufs;
    }

    public boolean isLg_velocidadeDifPorte() {
        return lg_velocidadeDifPorte;
    }

    public void setLg_velocidadeDifPorte(boolean lg_velocidadeDifPorte) {
        this.lg_velocidadeDifPorte = lg_velocidadeDifPorte;
    }

    
}
