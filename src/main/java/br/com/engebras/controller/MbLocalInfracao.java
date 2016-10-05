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
    private boolean lg_excessoVelocidade;
    private boolean lg_avancoSemaforo;
    private boolean lg_rodizio;
    private boolean lg_paradaSobreFaixa;
    private boolean lg_faixaExclusiva;
    private boolean lg_zmrc;
    private boolean lg_faixaNaoDestinada;
    private boolean lg_transitarAcostamento;
    private boolean lg_retornoProibido;
    private boolean lg_conversaoProibida;
    private boolean lg_contraMao;
    private boolean lg_velocidadeAbaixoPermitida;
    private boolean lg_zmrf;
    private boolean lg_localNaoPermitidoMoto;
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
        lg_excessoVelocidade = false;
        lg_avancoSemaforo = false;
        lg_rodizio = false;
        lg_paradaSobreFaixa = false;
        lg_faixaExclusiva = false;
        lg_zmrc = false;
        lg_faixaNaoDestinada = false;
        lg_transitarAcostamento = false;
        lg_retornoProibido = false;
        lg_conversaoProibida = false;
        lg_contraMao = false;
        lg_velocidadeAbaixoPermitida = false;
        lg_zmrf = false;
        lg_localNaoPermitidoMoto = false;
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
        
        if (lg_excessoVelocidade == false &&
            lg_avancoSemaforo == false &&
            lg_rodizio == false &&
            lg_paradaSobreFaixa == false &&
            lg_faixaExclusiva == false &&
            lg_zmrc == false &&
            lg_faixaNaoDestinada == false &&
            lg_transitarAcostamento == false &&
            lg_retornoProibido == false &&
            lg_conversaoProibida == false &&
            lg_contraMao == false &&
            lg_velocidadeAbaixoPermitida == false &&
            lg_zmrf == false &&
            lg_localNaoPermitidoMoto == false) {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Selecione um tipo de fiscalização.", ""));
            return;
        }

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

    public boolean isLg_excessoVelocidade() {
        return lg_excessoVelocidade;
    }

    public void setLg_excessoVelocidade(boolean lg_excessoVelocidade) {
        this.lg_excessoVelocidade = lg_excessoVelocidade;
    }

    public boolean isLg_avancoSemaforo() {
        return lg_avancoSemaforo;
    }

    public void setLg_avancoSemaforo(boolean lg_avancoSemaforo) {
        this.lg_avancoSemaforo = lg_avancoSemaforo;
    }

    public boolean isLg_rodizio() {
        return lg_rodizio;
    }

    public void setLg_rodizio(boolean lg_rodizio) {
        this.lg_rodizio = lg_rodizio;
    }

    public boolean isLg_paradaSobreFaixa() {
        return lg_paradaSobreFaixa;
    }

    public void setLg_paradaSobreFaixa(boolean lg_paradaSobreFaixa) {
        this.lg_paradaSobreFaixa = lg_paradaSobreFaixa;
    }

    public boolean isLg_faixaExclusiva() {
        return lg_faixaExclusiva;
    }

    public void setLg_faixaExclusiva(boolean lg_faixaExclusiva) {
        this.lg_faixaExclusiva = lg_faixaExclusiva;
    }

    public boolean isLg_zmrc() {
        return lg_zmrc;
    }

    public void setLg_zmrc(boolean lg_zmrc) {
        this.lg_zmrc = lg_zmrc;
    }

    public boolean isLg_faixaNaoDestinada() {
        return lg_faixaNaoDestinada;
    }

    public void setLg_faixaNaoDestinada(boolean lg_faixaNaoDestinada) {
        this.lg_faixaNaoDestinada = lg_faixaNaoDestinada;
    }

    public boolean isLg_transitarAcostamento() {
        return lg_transitarAcostamento;
    }

    public void setLg_transitarAcostamento(boolean lg_transitarAcostamento) {
        this.lg_transitarAcostamento = lg_transitarAcostamento;
    }

    public boolean isLg_retornoProibido() {
        return lg_retornoProibido;
    }

    public void setLg_retornoProibido(boolean lg_retornoProibido) {
        this.lg_retornoProibido = lg_retornoProibido;
    }

    public boolean isLg_conversaoProibida() {
        return lg_conversaoProibida;
    }

    public void setLg_conversaoProibida(boolean lg_conversaoProibida) {
        this.lg_conversaoProibida = lg_conversaoProibida;
    }

    public boolean isLg_contraMao() {
        return lg_contraMao;
    }

    public void setLg_contraMao(boolean lg_contraMao) {
        this.lg_contraMao = lg_contraMao;
    }

    public boolean isLg_zmrf() {
        return lg_zmrf;
    }

    public void setLg_zmrf(boolean lg_zmrf) {
        this.lg_zmrf = lg_zmrf;
    }

    public boolean isLg_localNaoPermitidoMoto() {
        return lg_localNaoPermitidoMoto;
    }

    public void setLg_localNaoPermitidoMoto(boolean lg_localNaoPermitidoMoto) {
        this.lg_localNaoPermitidoMoto = lg_localNaoPermitidoMoto;
    }

    public List getTipoFixacaoRadares() {
        return tipoFixacaoRadares;
    }

    public void setTipoFixacaoRadares(List tipoFixacaoRadares) {
        this.tipoFixacaoRadares = tipoFixacaoRadares;
    }

    public boolean isLg_velocidadeAbaixoPermitida() {
        return lg_velocidadeAbaixoPermitida;
    }

    public void setLg_velocidadeAbaixoPermitida(boolean lg_velocidadeAbaixoPermitida) {
        this.lg_velocidadeAbaixoPermitida = lg_velocidadeAbaixoPermitida;
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
