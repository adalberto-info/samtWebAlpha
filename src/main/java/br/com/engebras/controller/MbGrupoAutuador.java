package br.com.engebras.controller;

/**
 * @author Adalberto Kamida dt. criação: 23/06/2016
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
import br.com.engebras.model.entities.GrupoAutuador;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;

@ManagedBean(name = "mbGrupoAutuador")
@SessionScoped
public class MbGrupoAutuador implements Serializable {

    private static final long serialVersionUID = 1L;

    private GrupoAutuador grupoAutuador = new GrupoAutuador();

    private List<GrupoAutuador> gruposAutuadores;

    public MbGrupoAutuador() {

    }

    private InterfaceDAO<GrupoAutuador> grupoAutuadorDAO() {
        InterfaceDAO<GrupoAutuador> grupoAutuadorDAO = new HibernateDAO<GrupoAutuador>(GrupoAutuador.class, FacesContextUtil.getRequestSession());
        return grupoAutuadorDAO;
    }

    public String limpaGrupoAutuador() {
        grupoAutuador = new GrupoAutuador();
        return editGrupoAutuador();
    }

    public String editGrupoAutuador() {
        return "/restrict/cadGrupoAutuador.faces";
    }

    public String editarGrupoAutuador(Integer nr_codigo) {
        this.grupoAutuador = porNr_codigo(nr_codigo);
        return editGrupoAutuador();
    }

    public void addGrupoAutuador() {
        if (verificaDuplicidade(grupoAutuador.getDc_codigo(), grupoAutuador.getNr_codEnquadramento()) == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe um grupo autuador cadastrado com o código: " + grupoAutuador.getDc_codigo(), ""));
        } else if (grupoAutuador.getNr_codigo() == null || grupoAutuador.getNr_codigo() == 0) {
            insertGrupoAutuador();
        } else {
            updateGrupoAutuador();
        }

        limpaGrupoAutuador();
    }

    public void insertGrupoAutuador() {
        grupoAutuadorDAO().save(grupoAutuador);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!!!", ""));
    }

    public void updateGrupoAutuador() {
        grupoAutuadorDAO().update(grupoAutuador);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!!!", ""));
    }

    public void deleteGrupoAutuador() {
        grupoAutuadorDAO().remove(grupoAutuador);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!", ""));
    }

    private boolean verificaDuplicidade(String dc_codigo, Integer nr_codEnquadramento) {
        boolean vll_retorno = true;

        String vlc_sql = "";
        List consGrupoAutuador;

        Session session = FacesContextUtil.getRequestSession();
        vlc_sql = "select g.dc_codigo from grupoAutuador g where g.dc_codigo = '" + dc_codigo + "' ";
        vlc_sql += "and g.nr_codEnquadramento = " + nr_codEnquadramento + " ";
        if (grupoAutuador.getNr_codigo() != null && grupoAutuador.getNr_codigo() != 0) {
            vlc_sql += "and g.nr_codigo <> " + grupoAutuador.getNr_codigo();
        }

        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consGrupoAutuador = query.list();

        if (consGrupoAutuador.size() > 0) {
            vll_retorno = true;
        } else {
            vll_retorno = false;
        }

        for (Object oGrupoAutuador : consGrupoAutuador) {
            Map row = (Map) oGrupoAutuador;
        }
        consGrupoAutuador = null;

        return vll_retorno;
    }

    public GrupoAutuador porNr_codigo(Integer nr_codigo) {
        return grupoAutuadorDAO().getEntity(nr_codigo);
    }

    public GrupoAutuador getGrupoAutuador() {
        return grupoAutuador;
    }

    public void setGrupoAutuador(GrupoAutuador grupoAutuador) {
        this.grupoAutuador = grupoAutuador;
    }

    public List<GrupoAutuador> getGruposAutuadores() {
        return gruposAutuadores;
    }

    public void setGruposAutuadores(List<GrupoAutuador> gruposAutuadores) {
        this.gruposAutuadores = gruposAutuadores;
    }

}
