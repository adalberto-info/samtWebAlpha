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
        
    }

    public void editarLocal_tipoFiscalizacao(Integer nr_codLocal){
        String vlc_sql = "";
        int vln_nr_codTipoFiscalizacao = 0;
        List consLocal_tipoFiscalizacao;

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
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Executei o metodo addLocal_tipoFiscalizacao...", ""));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Código local: " + nr_codLocal + ".", ""));

        if (nr_codLocal == null || nr_codLocal == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Código do local está zerado...", ""));
            return;
        }
        
        vpn_nr_codLocal = nr_codLocal;
        
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
 
    
    
    
}
