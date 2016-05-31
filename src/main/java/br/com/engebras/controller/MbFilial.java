package br.com.engebras.controller;

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
import br.com.engebras.model.entities.Uf;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
/**
 * @author Adalberto 
 * dt. criacao: 22/03/2016
 */
@ManagedBean(name = "mbFilial")
@SessionScoped
public class MbFilial implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Filial filial = new Filial();

    private List<Filial> filiais;
    private List ufs = new ArrayList<>();

    public MbFilial() {
        geraListaUfs();
    }

    private InterfaceDAO<Filial> filialDAO() {
        InterfaceDAO<Filial> filialDAO = new HibernateDAO<Filial>(Filial.class, FacesContextUtil.getRequestSession());
        return filialDAO;
    }

    public String limpaFilial() {
        filial = new Filial();
        return editFilial();
    }

    public String editFilial() {
        return "/restrict/cadFiliais.faces";
    }

    public String editarFilial(Filial filial){
        this.filial = filial; 
        return editFilial();
    }
    
    public void addFilial() {
        if (verificaDuplicidade(filial.getDc_filial()) == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Já existe uma filial cadastrada com o nome:" + filial.getDc_filial(), ""));
        } else if (filial.getNr_codigo() == null || filial.getNr_codigo() == 0) {
            insertFilial();
        } else {
            updateFilial();
        }
        limpaFilial();
    }

    public void insertFilial() {
        filialDAO().save(filial);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!!!", ""));
    }

    public void updateFilial() {
        filialDAO().update(filial);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!!!", ""));
    }

    public void deleteFilial() {
        filialDAO().remove(filial);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!", ""));
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public List<Filial> getFiliais() {
        filiais = filialDAO().getEntities();
        return filiais;
    }

    public void setFiliais(List<Filial> filiais) {
        this.filiais = filiais;
    }

    private boolean verificaDuplicidade(String nomeFilial) {
        boolean vll_retorno = false;
        String vlc_sql = "";
        List consFiliais;

        Session session = FacesContextUtil.getRequestSession();
        vlc_sql = "select f.dc_filial from filial f where f.dc_filial = '" + nomeFilial + "' ";
        if (filial.getNr_codigo() != null && filial.getNr_codigo() != 0)
            vlc_sql = vlc_sql + "and f.nr_codigo <> " + filial.getNr_codigo();
        
        //     consFiliais = session.createSQLQuery(vlc_sql).list();
        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consFiliais = query.list();

        if (consFiliais.size() > 0) {
            vll_retorno = true;
        } else {
            vll_retorno = false;
        }

        for (Object oFilial : consFiliais) {
            Map row = (Map) oFilial;
        }

        consFiliais = null;

        return vll_retorno;
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

    public List getUfs() {
        return ufs;
    }

    public void setUfs(List ufs) {
        this.ufs = ufs;
    }

    
}
