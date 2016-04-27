package br.com.engebras.suport;

import br.com.engebras.model.entities.Uf;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;

/**
 * @author Adalberto
 * dt. criação: 27/04/2016
 */
@ManagedBean(name="bbUf")
@RequestScoped
public class BbUf implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public BbUf(){
    }


    public List<Uf> getUfs(){
        InterfaceDAO<Uf> ufDAO = new HibernateDAO<Uf>(Uf.class, FacesContextUtil.getRequestSession());
        return ufDAO.getEntities();
    }
    
}
