package br.com.engebras.controller;

/**
 * @author Adalberto
 * dt. criação: 31/05/2016
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
import br.com.engebras.model.entities.Usuario;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

@ManagedBean(name="mbusuario")
@SessionScoped
public class MbUsuario implements Serializable {
    
    private static final long servialVersionUID = 1L; 
    
    private Usuario usuario = new Usuario(); 

    private List<Usuario> usuarios; 
    
    public MbUsuario(){
        
    }
    
    private InterfaceDAO<Usuario> usuarioDAO() {
    InterfaceDAO<Usuario> usuarioDAO = new HibernateDAO<Usuario>(Usuario.class, FacesContextUtil.getRequestSession());
    return usuarioDAO;
    }

    public String limpaUsuario(){
        usuario = new Usuario();
        return editUsuario();
    }
    
    public String editUsuario(){
        return "/restric/cadUsuario.faces"; 
    }
    
    public String editarUsuario(Usuario usuario){
        this.usuario = usuario; 
        return editUsuario();
    }
    
    public void addUsuario(){
        if (verificaDuplicidade(usuario.getDc_login())== true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Já existe um usuário cadastrado com o login: " + usuario.getDc_login(),""));
        }else if(usuario.getNr_codigo() == null && usuario.getNr_codigo() == 0){
            insertUsuario();
        }else {
            updateUsuario();
        }
        limpaUsuario();
    }
    
    public void insertUsuario(){
        usuarioDAO().save(usuario); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso!",""));
    }
    
    public void updateUsuario(){
        usuarioDAO().update(usuario);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    private boolean verificaDuplicidade(String dc_login){
        boolean vll_retorno = false; 
        
        String vlc_sql = ""; 
        
        List consUsuarios;
        
        Session session = FacesContextUtil.getRequestSession(); 
        vlc_sql = "select u.dc_login from usuario u where u.dc_login = '" + dc_login + "' "; 
        if (usuario.getNr_codigo() != null && usuario.getNr_codigo() != 0 )
            vlc_sql = vlc_sql + "and u.nr_codigo <> " + usuario.getNr_codigo();
        
        SQLQuery query = session.createSQLQuery(vlc_sql); 
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP); 
        consUsuarios = query.list(); 
        
        if (consUsuarios.size() > 0){
            vll_retorno = true; 
        }else 
            vll_retorno = false; 
        
        for (Object oUsuario : consUsuarios){
            Map row = (Map) oUsuario; 
        }
        
        consUsuarios = null; 

        return vll_retorno; 
        
    }
}
