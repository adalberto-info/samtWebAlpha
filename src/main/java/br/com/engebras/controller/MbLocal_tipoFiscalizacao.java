/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 30/09/2016
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
import br.com.engebras.model.entities.Local_tipoFiscalizacao;


@ManagedBean(name="mbLocal_tipoFiscalizacao")
@SessionScoped
public class MbLocal_tipoFiscalizacao implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private Local_tipoFiscalizacao local_tipoFiscalizacao = new Local_tipoFiscalizacao(); 
    private Integer vpn_nr_codLocal; 
    
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

    
    public MbLocal_tipoFiscalizacao(){
        
    }
    
    public void Init(){
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

    }
    
    private InterfaceDAO<Local_tipoFiscalizacao> local_tipoFiscalizacaoDAO() {
        InterfaceDAO<Local_tipoFiscalizacao> local_tipoFiscalizacaoDAO = new HibernateDAO<Local_tipoFiscalizacao>(Local_tipoFiscalizacao.class, FacesContextUtil.getRequestSession());
        return local_tipoFiscalizacaoDAO;
    }
    
    public void limpaLocal_tipoFiscalizacao(){
        local_tipoFiscalizacao = new Local_tipoFiscalizacao();
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
        
    }

    public void editarLocal_tipoFiscalizacao(Integer nr_codLocal){
        String vlc_sql = "";
        int vln_nr_codTipoFiscalizacao = 0;
        List consLocal_tipoFiscalizacao;

        limpaLocal_tipoFiscalizacao();
        
        Session session = FacesContextUtil.getRequestSession();
        vlc_sql = "select a.nr_codLocal, a.nr_codTipoFiscalizacao from local_tipoFiscalizacao a where a.nr_codLocal = " + nr_codLocal + " ";

        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consLocal_tipoFiscalizacao = query.list();

        for (Object oLocal_tipoFiscalizacao : consLocal_tipoFiscalizacao) {
            Map row = (Map) oLocal_tipoFiscalizacao;
            
            vln_nr_codTipoFiscalizacao = Integer.parseInt(row.get("nr_codTipoFiscalizacao").toString());
            
            if (vln_nr_codTipoFiscalizacao == 1){
                lg_excessoVelocidade = true;
            } else if(vln_nr_codTipoFiscalizacao == 2){
                lg_avancoSemaforo = true; 
            } else if(vln_nr_codTipoFiscalizacao == 3){
                lg_rodizio = true; 
            } else if(vln_nr_codTipoFiscalizacao == 4){
                lg_paradaSobreFaixa = true; 
            } else if(vln_nr_codTipoFiscalizacao == 5){
                lg_faixaExclusiva = true; 
            } else if(vln_nr_codTipoFiscalizacao == 6){
                lg_zmrc = true; 
            } else if(vln_nr_codTipoFiscalizacao == 7){
                lg_faixaNaoDestinada = true; 
            } else if(vln_nr_codTipoFiscalizacao == 8){
                lg_transitarAcostamento = true; 
            } else if(vln_nr_codTipoFiscalizacao == 9){
                lg_retornoProibido = true; 
            } else if(vln_nr_codTipoFiscalizacao == 10){
                lg_conversaoProibida = true; 
            } else if(vln_nr_codTipoFiscalizacao == 11){
                lg_contraMao = true;
            } else if(vln_nr_codTipoFiscalizacao == 12){
                lg_velocidadeAbaixoPermitida = true;
            } else if(vln_nr_codTipoFiscalizacao == 13){
                lg_zmrf = true;
            } else if(vln_nr_codTipoFiscalizacao == 14){
                lg_localNaoPermitidoMoto = true;
            } 
        }

        consLocal_tipoFiscalizacao = null;

        
    }
    
    public void addLocal_tipoFiscalizacao(Integer nr_codLocal){
        
        String vlc_sql; 
        vlc_sql = "";
        boolean vll_existe;
        vll_existe = true;

        List consLocal_tipoFiscalizacao;

        if (nr_codLocal == null || nr_codLocal == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Código do local está zerado...", ""));
            return;
        }
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
        
        if (lg_excessoVelocidade == true){
            addItemLocal_tipoFiscalizacao(nr_codLocal, 1, true);
        } else {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 1, false);
        }

        if (lg_avancoSemaforo == true){
            addItemLocal_tipoFiscalizacao(nr_codLocal, 2, true);
        } else {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 2, false);
        }
        
        if (lg_rodizio == true){
            addItemLocal_tipoFiscalizacao(nr_codLocal, 3, true);
        } else {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 3, false);
        }
        
        if (lg_paradaSobreFaixa == true){
            addItemLocal_tipoFiscalizacao(nr_codLocal, 4, true);
        } else {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 4, false);
        }
        
        if (lg_faixaExclusiva == true) {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 5, true);
        } else {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 5, false);
        }
        
        if (lg_zmrc == true) {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 6, true);
        } else {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 6, false);
        }
        
        if (lg_faixaNaoDestinada == true) {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 7, true);
        } else {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 7, false);
        }
        
        if (lg_transitarAcostamento == true) {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 7, true);
        } else {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 7, false);
        }
        
        if (lg_retornoProibido == true) {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 8, true);
        } else {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 8, false);
        }
        
        if (lg_conversaoProibida == true) {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 9, true);
        } else {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 9, false);
        }
        
        if (lg_contraMao == true) {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 10, true);
        } else {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 10, false);
        }
        
        if (lg_velocidadeAbaixoPermitida == true){
            addItemLocal_tipoFiscalizacao(nr_codLocal, 11, true);
        } else {
            addItemLocal_tipoFiscalizacao(nr_codLocal, 11, false);
        }
        
        
    }
    
    public void addItemLocal_tipoFiscalizacao(Integer nr_codLocal, Integer nr_codTipoFiscalizacao, boolean vll_inserir){

        Session session = FacesContextUtil.getRequestSession();
        SQLQuery query;
        String vlc_sql; 
        boolean vll_existe;
        vll_existe = true;
        List consLocal_tipoFiscalizacao;

        vlc_sql = "select a.* from local_tipoFiscalizacao a where a.nr_codLocal =  " + nr_codLocal + " and a.nr_codTipoFiscalizacao = " + nr_codTipoFiscalizacao + " " ; 
        
        query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consLocal_tipoFiscalizacao = query.list();
        
        vll_existe = (consLocal_tipoFiscalizacao.size() > 0);
        
        if (vll_inserir == true){
            if (vll_existe == false){
                local_tipoFiscalizacao.setNr_codLocal(nr_codLocal);
                local_tipoFiscalizacao.setNr_codTipoFiscalizacao(nr_codTipoFiscalizacao);
                insertLocal_tipoFiscalizacao();
            }
        } else {
           if (vll_existe == true){
                local_tipoFiscalizacao.setNr_codLocal(nr_codLocal);
                local_tipoFiscalizacao.setNr_codTipoFiscalizacao(nr_codTipoFiscalizacao);
                deleteLocalTipoFiscalizacao();
           } 
        }
        
    }
    
    public void deleteLocalTipoFiscalizacao(){
        local_tipoFiscalizacaoDAO().remove(local_tipoFiscalizacao);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!",""));
    }


    public void insertLocal_tipoFiscalizacao() {
        local_tipoFiscalizacaoDAO().save(local_tipoFiscalizacao);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!!!", ""));
    }

    public void updateLocal_tipoFiscalizacao() {
        local_tipoFiscalizacaoDAO().update(local_tipoFiscalizacao);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!!!", ""));
    }
    
    
    public Local_tipoFiscalizacao getLocal_tipoFiscalizacao() {
        return local_tipoFiscalizacao;
    }

    public void setLocal_tipoFiscalizacao(Local_tipoFiscalizacao local_tipoFiscalizacao) {
        this.local_tipoFiscalizacao = local_tipoFiscalizacao;
    }

    public Integer getVpn_nr_codLocal() {
        return vpn_nr_codLocal;
    }

    public void setVpn_nr_codLocal(Integer vpn_nr_codLocal) {
        this.vpn_nr_codLocal = vpn_nr_codLocal;
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

    public boolean isLg_velocidadeDifPorte() {
        return lg_velocidadeDifPorte;
    }

    public void setLg_velocidadeDifPorte(boolean lg_velocidadeDifPorte) {
        this.lg_velocidadeDifPorte = lg_velocidadeDifPorte;
    }
    
    
}
