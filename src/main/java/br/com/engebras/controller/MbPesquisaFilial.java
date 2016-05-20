package br.com.engebras.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;

import br.com.engebras.model.entities.Filial;
import br.com.engebras.repository.filter.FilialFilter;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;

/**
 * @author Adalberto
 * dt. criação: 19/05/2016
 */


@ManagedBean(name="mbPesquisaFilial")
@ViewScoped
public class MbPesquisaFilial implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private EntityManager manager;
    
    public FilialFilter filtro;
    private List<Filial> filiaisFiltradas;

    private Filial filialSelecionada;
    
    
    public MbPesquisaFilial(){
        filtro = new FilialFilter();
    }

    
    public Filial guardar(Filial filial){
        return manager.merge(filial);
    }
    
    public void remover(Filial filial){
        try{
            filial = porNr_codigo(filial.getNr_codigo());
            manager.remove(filial);
            manager.flush();
        } catch (PersistenceException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Filial não pode ser excluída.", ""));
        }
    }

    public List<Filial> filtrados(FilialFilter filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Filial.class);
        
        if (StringUtils.isNotBlank(filtro.getDc_filial())){
            criteria.add(Restrictions.like("dc_filial",filtro.getDc_filial(), MatchMode.ANYWHERE));
        }
     
        return criteria.addOrder(Order.asc("dc_filial")).list();
    }

    
    public Filial porNr_codigo(int nr_codigo){
        return manager.find(Filial.class, nr_codigo);
    }

    public List<Filial> porDc_filial(String dc_filial) {
        return this.manager.createQuery("from filial where upper(dc_filial) like :dc_filial", Filial.class).setParameter("dc_filial", dc_filial.toUpperCase() + "%").getResultList();
    }
    
}
