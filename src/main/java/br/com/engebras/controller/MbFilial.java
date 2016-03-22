package br.com.engebras.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import br.com.engebras.model.entities.Filial;

/**
 * @author Adalberto dt. criacao: 22/03/2016
 */
@ManagedBean(name = "mbfilial")
@SessionScoped
public class MbFilial implements Serializable {

    private static final long serialVersionUID = 1L;

    private Filial filial = new Filial();

    private List<Filial> filiais;

    public MbFilial() {
    }

    public String limpaFilial() {
        return editFilial();
    }

    public String editFilial() {
        return "restrict/cadFiliais.faces";
    }

    public String addFilial() {
        if (filial.getNr_codigo() == null || filial.getNr_codigo() == 0) {
            insertFilial();
        } else {
            updateFilial();
        }
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
        return filiais;
    }

    public void setFiliais(List<Filial> filiais) {
        this.filiais = filiais;
    }
    
    
}
