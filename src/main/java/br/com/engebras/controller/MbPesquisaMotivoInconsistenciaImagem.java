package br.com.engebras.controller;

/**
 * @author Adalberto Kamida
 * dt. criacao: 30/06/2016
 */
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;

import br.com.engebras.model.entities.MotivoInconsistenciaImagem;
import br.com.engebras.filter.MotivoInconsistenciaImagemFilter;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.util.FacesContextUtil;

@ManagedBean(name="mbPesquisaMotivoInconsistenciaImagem")
@ViewScoped
public class MbPesquisaMotivoInconsistenciaImagem implements Serializable {
    private static final long serialVersionUID = 1L; 
    
    private EntityManager manager; 
    
    private MotivoInconsistenciaImagemFilter filtro; 
    private List<MotivoInconsistenciaImagem> motivoInconsistenciaImagensFiltradas; 
    
    private MotivoInconsistenciaImagem motivoInconsistenciaImagem = new MotivoInconsistenciaImagem() ; 
    
    public MbPesquisaMotivoInconsistenciaImagem(){
        filtro = new MotivoInconsistenciaImagemFilter();
    }
    
    private InterfaceDAO<MotivoInconsistenciaImagem> motivoInconsistenciaImagemDAO(){
        InterfaceDAO<MotivoInconsistenciaImagem> motivoInconsistenciaImagemDAO = new HibernateDAO<MotivoInconsistenciaImagem>(MotivoInconsistenciaImagem.class, FacesContextUtil.getRequestSession());
        return motivoInconsistenciaImagemDAO;
    }
    
    public void deleteMotivoInconsistenciaImagem(){
        motivoInconsistenciaImagemDAO().remove(motivoInconsistenciaImagem);
        pesquisar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!!!",""));
    }
    
    public void pesquisar(){
        motivoInconsistenciaImagensFiltradas = filtrados(filtro);
    }
    
    public List<MotivoInconsistenciaImagem> filtrados(MotivoInconsistenciaImagemFilter filtro){
        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(MotivoInconsistenciaImagem.class);
        if (filtro.getNr_codigo() != null && filtro.getNr_codigo() > 0){
            criteria.add(Restrictions.eq("nr_codigo", filtro.getNr_codigo()));
        }
        return criteria.addOrder(Order.asc("nr_codigo")).list(); 
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public MotivoInconsistenciaImagemFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(MotivoInconsistenciaImagemFilter filtro) {
        this.filtro = filtro;
    }

    public List<MotivoInconsistenciaImagem> getMotivoInconsistenciaImagensFiltradas() {
        return motivoInconsistenciaImagensFiltradas;
    }

    public void setMotivoInconsistenciaImagensFiltradas(List<MotivoInconsistenciaImagem> motivoInconsistenciaImagensFiltradas) {
        this.motivoInconsistenciaImagensFiltradas = motivoInconsistenciaImagensFiltradas;
    }

    public MotivoInconsistenciaImagem getMotivoInconsistenciaImagem() {
        return motivoInconsistenciaImagem;
    }

    public void setMotivoInconsistenciaImagem(MotivoInconsistenciaImagem motivoInconsistenciaImagem) {
        this.motivoInconsistenciaImagem = motivoInconsistenciaImagem;
    }
    
    
}
