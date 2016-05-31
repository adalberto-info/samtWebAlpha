package br.com.engebras.controller;

/**
 * @author Adalberto
 * dt. criação: 31/05/2016
 */

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;

import br.com.engebras.model.entities.Usuario;
import br.com.engebras.filter.UsuarioFilter;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.util.FacesContextUtil;
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


@ManagedBean(name="mbPesquisaUsuario")
@ViewScoped
public class MbPesquisaUsuario implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    private EntityManager manager; 
    
    UsuarioFilter filtro; 
    private List<Usuario> usuariosFiltrados; 
    
    private Usuario usuario = new Usuario(); 
    
    public MbPesquisaUsuario(){
        filtro = new UsuarioFilter(); 
    }
    
    private InterfaceDAO<Usuario> usuarioDAO(){ 
        InterfaceDAO<Usuario> usuarioDAO = new HibernateDAO<Usuario>(Usuario.class, FacesContextUtil.getRequestSession()); 
        return usuarioDAO; 
    }    
    
    public void deleteUsuario(){
        usuarioDAO().remove(usuario);
        pesquisar(); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso!", ""));
    }
    
    public void pesquisar(){
        usuariosFiltrados = filtrados(filtro);
    }

    public List<Usuario> filtrados(UsuarioFilter filtro){
        
        Session session = FacesContextUtil.getRequestSession();
        Criteria criteria = session.createCriteria(Usuario.class);
        
        if (StringUtils.isNotBlank(filtro.getDc_login())){
            criteria.add(Restrictions.like("dc_login", filtro.getDc_login(), MatchMode.ANYWHERE));
        }
        
        return criteria.addOrder(Order.asc("dc_login")).list();
    }

    public UsuarioFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(UsuarioFilter filtro) {
        this.filtro = filtro;
    }

    public List<Usuario> getUsuariosFiltrados() {
        return usuariosFiltrados;
    }

    public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
        this.usuariosFiltrados = usuariosFiltrados;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
