/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 19/12/2016
 * 
 */
package br.com.engebras.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import javax.annotation.PostConstruct;

import br.com.engebras.util.FacesContextUtil;
import br.com.engebras.util.HibernateUtil;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.entities.AutoInfracao;
import br.com.engebras.model.entities.AutoInfracaoImagem;


@ManagedBean(name = "mbDigitacaoPlacas")
@SessionScoped
public class MbDigitacaoPlacas implements Serializable{

    private static final long serialVersionUID = 1L; 
    
    private AutoInfracao autoInfracao = new  AutoInfracao(); 
    private List<String> images;

    private String vpc_dc_mensagem; 
    private String vpc_dc_local;
    private String vpc_dc_enquadramento; 
    private String vpc_dc_marca; 
    private String vpc_dc_especie; 
    private String vpc_dc_categoria; 
    private String vpc_dc_tipo; 
    private String vpc_dc_cor; 
    private String vpc_dc_municipio; 
    
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();

        images.add("000010102488718260_00.jpg");
        images.add("000010102488718260_01.jpg");
    }
 
    public List<String> getImages() {
        return images;
    }

    public MbDigitacaoPlacas(){
        vpc_dc_mensagem = ""; 
        vpc_dc_local = "";
        vpc_dc_enquadramento = ""; 
        vpc_dc_marca = ""; 
        vpc_dc_especie = ""; 
        vpc_dc_categoria = ""; 
        vpc_dc_tipo = ""; 
        vpc_dc_cor = ""; 
        vpc_dc_municipio = ""; 

        boolean vll_retorno;
        vll_retorno = proximaInfracao();
    }
    
    private InterfaceDAO<AutoInfracao> autoInfracaoDAO() {
        InterfaceDAO<AutoInfracao> autoInfracaoDAO = new HibernateDAO<AutoInfracao>(AutoInfracao.class, FacesContextUtil.getRequestSession());
        return autoInfracaoDAO;
    }

    public String limpaInfracao(){
        autoInfracao = new AutoInfracao(); 
        return editAutoInfracao(); 
    }
    
    public String editAutoInfracao(){
        return "/restrict/digitacaoPlacas.xhtml";
    }

    public String inicioPagina(){
        return "/Inicio.xhtml";
    }
    
    public boolean proximaInfracao(){
        String vlc_sql = ""; 
        int vln_resultado = 0;
        String dc_nr_multa = " ";
        List consInfracao;
        SQLQuery query;
        Session session = FacesContextUtil.getRequestSession();
                
        vlc_sql = "update autoInfracao set lg_uso = 1, nr_usuarioDigitacao = 9000 where nr_status = 1 and lg_uso = 0 limit 1 ";

        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        
        vlc_sql = "select a.* from autoInfracao a where a.nr_status = 1 and a.lg_uso = 1 and a.nr_usuarioDigitacao = 9000 limit 1 ";
        
        query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        consInfracao = query.list();

        for (Object oInfracao : consInfracao) {
            Map row = (Map) oInfracao;
            
            dc_nr_multa = row.get("dc_nr_multa").toString();
        }        
        
        if (!dc_nr_multa.equals(" ")){
            autoInfracao = porDc_nr_multa(dc_nr_multa);
            
            atualizaTela(dc_nr_multa);
            
            return true;
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Não existe mais infrações para digitação...", ""));
            return false;
        }
        
    }
    
    public AutoInfracao porDc_nr_multa(String dc_nr_multa) {

        return autoInfracaoDAO().getEntity(dc_nr_multa);
    }


    public void atualizaTela(String dc_nr_multa){

        String vlc_sql = ""; 
        List consulta;
        SQLQuery query;
        Session session = FacesContextUtil.getRequestSession();

        if (!dc_nr_multa.isEmpty()){
            vpc_dc_local = "";
            vlc_sql = "select ifnull(b.dc_local, space(80)) as dc_local, ifnull(c.dc_categoria, space(30)) as dc_categoria, ";
            vlc_sql += "ifnull(d.dc_marca, space(35)) as dc_marca, ";
            vlc_sql += "ifnull(e.dc_especie, space(15)) as dc_especie, ";
            vlc_sql += "ifnull(f.dc_municipio, space(40)) as dc_municipio, ";
            vlc_sql += "ifnull(g.dc_descricao, space(300)) as dc_enquadramento, ";
            vlc_sql += "ifnull(h.dc_cor, space(15)) as dc_cor, ";
            vlc_sql += "ifnull(i.dc_descricao, space(30)) as dc_tipo ";
            vlc_sql += "from autoInfracao a left outer join localInfracao b on a.nr_codLocal = b.nr_codigo ";
            vlc_sql += "left outer join veiculoCategoria c on a.nr_codCategoria = c.nr_codigo ";
            vlc_sql += "left outer join veiculoMarcaDenatran d on a.nr_codMarca = d.nr_codigo ";
            vlc_sql += "left outer join veiculoEspecie e on a.nr_codEspecie = e.nr_codigo ";
            vlc_sql += "left outer join municipio f on a.nr_codMunicipio = f.nr_codigo ";
            vlc_sql += "left outer join enquadramento g on a.nr_codEnquadramento = g.nr_codigo ";
            vlc_sql += "left outer join veiculoCor h on a.nr_codCor = h.nr_codigo ";
            vlc_sql += "left outer join veiculoTipoDenatran i on a.nr_codTipo = i.nr_codigo ";
            vlc_sql += "where a.dc_nr_multa = '" + dc_nr_multa + "' " ;
        
            query = session.createSQLQuery(vlc_sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            consulta = query.list();
            for (Object oInfracao : consulta) {
                Map row = (Map) oInfracao;
            
                vpc_dc_local = row.get("dc_local").toString();
                vpc_dc_enquadramento = row.get("dc_enquadramento").toString(); 
                vpc_dc_marca = row.get("dc_marca").toString(); 
                vpc_dc_especie = row.get("dc_especie").toString(); 
                vpc_dc_categoria = row.get("dc_categoria").toString(); 
                vpc_dc_tipo = row.get("dc_tipo").toString(); 
                vpc_dc_cor = row.get("dc_cor").toString(); 
                vpc_dc_municipio = row.get("dc_municipio").toString(); 

            }        
            
        }
        
    }

    public AutoInfracao getAutoInfracao() {
        return autoInfracao;
    }

    public void setAutoInfracao(AutoInfracao autoInfracao) {
        this.autoInfracao = autoInfracao;
    }

    public String getVpc_dc_mensagem() {
        return vpc_dc_mensagem;
    }

    public void setVpc_dc_mensagem(String vpc_dc_mensagem) {
        this.vpc_dc_mensagem = vpc_dc_mensagem;
    }

    public String getVpc_dc_local() {
        return vpc_dc_local;
    }

    public void setVpc_dc_local(String vpc_dc_local) {
        this.vpc_dc_local = vpc_dc_local;
    }

    public String getVpc_dc_enquadramento() {
        return vpc_dc_enquadramento;
    }

    public void setVpc_dc_enquadramento(String vpc_dc_enquadramento) {
        this.vpc_dc_enquadramento = vpc_dc_enquadramento;
    }

    public String getVpc_dc_marca() {
        return vpc_dc_marca;
    }

    public void setVpc_dc_marca(String vpc_dc_marca) {
        this.vpc_dc_marca = vpc_dc_marca;
    }

    public String getVpc_dc_especie() {
        return vpc_dc_especie;
    }

    public void setVpc_dc_especie(String vpc_dc_especie) {
        this.vpc_dc_especie = vpc_dc_especie;
    }

    public String getVpc_dc_categoria() {
        return vpc_dc_categoria;
    }

    public void setVpc_dc_categoria(String vpc_dc_categoria) {
        this.vpc_dc_categoria = vpc_dc_categoria;
    }

    public String getVpc_dc_tipo() {
        return vpc_dc_tipo;
    }

    public void setVpc_dc_tipo(String vpc_dc_tipo) {
        this.vpc_dc_tipo = vpc_dc_tipo;
    }

    public String getVpc_dc_cor() {
        return vpc_dc_cor;
    }

    public void setVpc_dc_cor(String vpc_dc_cor) {
        this.vpc_dc_cor = vpc_dc_cor;
    }

    public String getVpc_dc_municipio() {
        return vpc_dc_municipio;
    }

    public void setVpc_dc_municipio(String vpc_dc_municipio) {
        this.vpc_dc_municipio = vpc_dc_municipio;
    }

    
}
