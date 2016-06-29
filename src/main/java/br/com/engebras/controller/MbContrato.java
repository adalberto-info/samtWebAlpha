package br.com.engebras.controller;

/**
 * @author Adalberto 
 * dt. criacao: 09/06/2016
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import br.com.engebras.util.FacesContextUtil;
import br.com.engebras.util.HibernateUtil;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.entities.Filial;
import br.com.engebras.model.entities.Contrato;
import br.com.engebras.model.entities.Municipio;
import br.com.engebras.model.entities.Uf;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

@ManagedBean(name = "mbContrato")
@SessionScoped
public class MbContrato implements Serializable {

    private static final long serialVersionUID = 1L;

    private Contrato contrato = new Contrato();

    private List<Contrato> contratos;
    private List filiais = new ArrayList<>();
    private List municipios = new ArrayList<>();
    private List ufs = new ArrayList<>();
    private boolean vll_novoContrato = true;

    public MbContrato() {
        geraListaFiliais();
        geraListaMunicipios();
        geraListaUfs();
    }

    private InterfaceDAO<Contrato> contratoDAO() {
        InterfaceDAO<Contrato> contratoDAO = new HibernateDAO<Contrato>(Contrato.class, FacesContextUtil.getRequestSession());
        return contratoDAO;
    }

    public String limpaContrato() {
        contrato = new Contrato();
        vll_novoContrato = true;
        return editContrato();
    }

    public String editContrato() {
        return "/restrict/cadContrato.xhtml";
    }

    public String editarContrato(Integer nr_crd) {
        this.contrato = porNr_crd(nr_crd);
        this.vll_novoContrato = false; 
        return editContrato();
    }

    public void addContrato() {
        if (verificaDuplicidade(contrato.getNr_crd()) == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe um contrato cadastrado com este nr. CRD." + contrato.getNr_crd(), ""));
        } else if (vll_novoContrato == true) {
            insertContrato();
        } else {
            updateContrato();
        }
        limpaContrato();
    }

    public void insertContrato() {
        contratoDAO().save(contrato);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso !!!", ""));
    }

    public void updateContrato() {
        contratoDAO().update(contrato);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização realizada com sucesso !!!", ""));
    }

    public void deleteFilial() {
        contratoDAO().remove(contrato);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso !!!", ""));
    }

    private boolean verificaDuplicidade(Integer nr_crd) {
        boolean vll_retorno = false;

        String vlc_sql = "";
        List consContrato;

            Session session = FacesContextUtil.getRequestSession();
            vlc_sql = "select c.nr_crd from contrato c where c.nr_crd = " + nr_crd;

            SQLQuery query = session.createSQLQuery(vlc_sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            consContrato = query.list();

            if (vll_novoContrato == true) {

                if (consContrato.size() > 0) {
                    vll_retorno = true;
                } else {
                    vll_retorno = false;
                }
            }

            if (vll_novoContrato == false) {
                
                if (consContrato.size() > 1){
                    vll_retorno = true;
                } else {
                    vll_retorno = false;
                }
            }
            
            for (Object oContrato : consContrato) {
                Map row = (Map) oContrato;
            }

            consContrato = null;
        return vll_retorno;
    }
    
    public void geraListaFiliais(){
        List listaSQL; 
        String vlc_sql; 
        vlc_sql = "select dc_filial, nr_codigo from filial order by dc_filial";
        
        Session session = FacesContextUtil.getRequestSession();
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        listaSQL = query.list();
        
        this.filiais = listaSQL; 
    }
    
    public void geraListaMunicipios(){
        List listaSQL; 
        String vlc_sql; 
        vlc_sql = "select concat(a.dc_uf, ' | ', a.dc_municipio) as dc_municipio, a.nr_codigo, a.dc_uf ";
        vlc_sql += "from municipio a ";
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

    
    public Contrato porNr_crd(Integer nr_crd){
        return contratoDAO().getEntity(nr_crd);
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public List getFiliais() {
        return filiais;
    }

    public void setFiliais(List filiais) {
        this.filiais = filiais;
    }

    public boolean isVll_novoContrato() {
        return vll_novoContrato;
    }

    public void setVll_novoContrato(boolean vll_novoContrato) {
        this.vll_novoContrato = vll_novoContrato;
    }

    public List getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List municipios) {
        this.municipios = municipios;
    }

    public List getUfs() {
        return ufs;
    }

    public void setUfs(List ufs) {
        this.ufs = ufs;
    }
    
}
