package br.com.engebras.repository;

import java.io.Serializable;
import javax.persistence.EntityManager;
import br.com.engebras.model.entities.Filial;
import br.com.engebras.repository.filter.FilialFilter;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Adalberto
 * dt. criação: 19/05/2016
 */
public class Filiais implements Serializable {

    private static final long serialVersionUID = 1L; 

    private EntityManager manager;
    
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
        
    }

    
    public Filial porNr_codigo(int nr_codigo){
        return manager.find(Filial.class, nr_codigo);
    }
    
    
}
