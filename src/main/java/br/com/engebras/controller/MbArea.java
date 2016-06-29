package br.com.engebras.controller;

/**
 * @author Adalberto dt. criação: 28/06/2016
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
import br.com.engebras.model.entities.Area;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

@ManagedBean(name = "mbArea")
@SessionScoped
public class MbArea implements Serializable {

    private static final long serialVersionUID = 1L;

    private Area area = new Area();

    private List<Area> areas;
    private boolean vll_novaArea = true;

    public MbArea() {

    }

    private InterfaceDAO<Area> areaDAO() {
        InterfaceDAO<Area> areaDAO = new HibernateDAO<Area>(Area.class, FacesContextUtil.getRequestSession());

        return areaDAO;
    }

    public String limpaArea() {
        area = new Area();
        vll_novaArea = true;
        return editArea();
    }

    public String editArea() {
        return "/restrict/cad_area.xhtml";
    }

    public String editarArea(String dc_codArea) {
        this.area = porDc_codArea(dc_codArea);
        vll_novaArea = false;
        return editArea();
    }

    public void addArea() {

        if (verificaDuplicidade(area.getDc_area()) == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe uma área cadastrada com o código: " + area.getDc_area(), ""));
        } else if (vll_novaArea == true) {
            insertArea();
        } else {
            updateArea();
        }

    }

    public void insertArea() {
        areaDAO().save(area);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!!!", ""));
    }

    public void updateArea() {
        areaDAO().update(area);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização realizada com sucesso!!!", ""));
    }

    private boolean verificaDuplicidade(String dc_codArea) {
        boolean vll_retorno = true;

        String vlc_sql = "";
        List consArea;

        Session session = FacesContextUtil.getRequestSession();
        vlc_sql = "select a.dc_codArea from area a where a.dc_codArea = " + dc_codArea;

        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consArea = query.list();

        if (vll_novaArea == true) {
            if (consArea.size() > 0) {
                vll_retorno = true;
            } else {
                vll_retorno = false;
            }
        }

        if (vll_novaArea == false) {
            if (consArea.size() > 1) {
                vll_retorno = true;
            } else {
                vll_retorno = false;
            }
        }
        for (Object oArea : consArea) {
            Map row = (Map) oArea;
        }

        consArea = null;

        return vll_retorno;
    }
    
    public Area porDc_codArea(String dc_codArea){
        return areaDAO().getEntity(dc_codArea);
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public boolean isVll_novaArea() {
        return vll_novaArea;
    }

    public void setVll_novaArea(boolean vll_novaArea) {
        this.vll_novaArea = vll_novaArea;
    }
    
    
}
