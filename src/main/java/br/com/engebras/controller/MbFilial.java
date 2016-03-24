package br.com.engebras.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import br.com.engebras.model.entities.Filial;
import br.com.engebras.util.FacesContextUtil;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.entities.Filial;

/**
 * @author Adalberto dt. criacao: 22/03/2016
 */
@ManagedBean(name = "mbFilial")
@SessionScoped
public class MbFilial implements Serializable {

    private static final long serialVersionUID = 1L;

    private Filial filial = new Filial();

    private List<Filial> filiais;

    public MbFilial() {
    }

    private InterfaceDAO<Filial> filialDAO(){
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

    public String addFilial() {
        if (filial.getNr_codigo() == null || filial.getNr_codigo() == 0) {
            insertFilial();
        } else {
            updateFilial();
        }
        limpaFilial();
        return null;
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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!",""));
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
    
    
}
