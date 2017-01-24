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
import java.sql.SQLException;

import br.com.engebras.util.FacesContextUtil;
import br.com.engebras.util.HibernateUtil;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.entities.AutoInfracao;
import br.com.engebras.model.entities.AutoInfracaoImagem;
import br.com.engebras.model.entities.VeiculoMarcaCET;
import br.com.engebras.model.entities.MotivoInconsistenciaImagem;
import java.util.Date;

@ManagedBean(name = "mbDigitacaoPlacas")
@SessionScoped
public class MbDigitacaoPlacas implements Serializable {

    private static final long serialVersionUID = 1L;

    private AutoInfracao autoInfracao = new AutoInfracao();
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
    private String vpc_dc_placa; 
    private String vpc_dc_nr_multa;
    private Integer vpn_nr_codInconsistencia;
    private List veiculoMarcaCETs = new ArrayList<>();
    private List motivoInconsistenciaImagens = new ArrayList<>();
    private Date dt_atual;
    
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();

        images.add("000010102488718260_00.jpg");
        images.add("000010102488718260_01.jpg");
    }

    public List<String> getImages() {
        return images;
    }

    public MbDigitacaoPlacas() {
        vpc_dc_mensagem = "";
        vpc_dc_local = "";
        vpc_dc_enquadramento = "";
        vpc_dc_marca = "";
        vpc_dc_especie = "";
        vpc_dc_categoria = "";
        vpc_dc_tipo = "";
        vpc_dc_cor = "";
        vpc_dc_municipio = "";
        vpc_dc_nr_multa = "";
        dt_atual = new Date();

        geraListaVeiculoMarcaCET();
        geraListaMotivoInconsistenciaImagem();

        boolean vll_retorno;
        vll_retorno = proximaInfracao();
    }

    private InterfaceDAO<AutoInfracao> autoInfracaoDAO() {
        InterfaceDAO<AutoInfracao> autoInfracaoDAO = new HibernateDAO<AutoInfracao>(AutoInfracao.class, FacesContextUtil.getRequestSession());
        return autoInfracaoDAO;
    }

    public String limpaInfracao() {
        autoInfracao = new AutoInfracao();
        return editAutoInfracao();
    }

    public String editAutoInfracao() {
        return "/restrict/digitacaoPlacas.xhtml";
    }

    public String inicioPagina() {
        return "/Inicio.xhtml";
    }

    public boolean proximaInfracao() {
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

        if (!dc_nr_multa.equals(" ")) {
            autoInfracao = porDc_nr_multa(dc_nr_multa);

            atualizaTela(dc_nr_multa);

            return true;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Não existe mais infrações para digitação...", ""));
            return false;
        }

    }

    public AutoInfracao porDc_nr_multa(String dc_nr_multa) {

        return autoInfracaoDAO().getEntity(dc_nr_multa);
    }

    public void atualizaTela(String dc_nr_multa) {

        String vlc_sql = "";
        List consulta;
        SQLQuery query;
        Session session = FacesContextUtil.getRequestSession();

        if (!dc_nr_multa.isEmpty()) {
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
            vlc_sql += "where a.dc_nr_multa = '" + dc_nr_multa + "' ";

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

    public void geraListaVeiculoMarcaCET() {
        List listaSQL;
        String vlc_sql;
        vlc_sql = "select nr_codigo, nr_codigoDV, dc_marca from veiculoMarcaCET order by dc_marca";

        Session session = FacesContextUtil.getRequestSession();

        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        listaSQL = query.list();

        this.veiculoMarcaCETs = listaSQL;
    }

    public void geraListaMotivoInconsistenciaImagem() {
        List listaSQL;
        String vlc_sql;
        vlc_sql = "select a.nr_codigo, a.dc_inconsistencia from motivoInconsistenciaImagem a order by a.dc_inconsistencia";

        Session session = FacesContextUtil.getRequestSession();

        SQLQuery query = session.createSQLQuery(vlc_sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        listaSQL = query.list();

        this.motivoInconsistenciaImagens = listaSQL;
    }

    public void consultaPlaca(String dc_placa) {
        String vlc_sql = "";
        List consulta;
        SQLQuery query;
        
        if (dc_placa.isEmpty() || validaPlaca(dc_placa) == false) {

            if (dc_placa.isEmpty()){
                vpc_dc_mensagem = "";
            }else{
                vpc_dc_mensagem = "Placa inválida.";
            }
            vpc_dc_marca = "";
            vpc_dc_especie = "";
            vpc_dc_categoria = "";
            vpc_dc_tipo = "";
            vpc_dc_cor = "";
            vpc_dc_municipio = "";
            autoInfracao.setNr_codCategoria(0);
            autoInfracao.setNr_codMarca(0);
            autoInfracao.setNr_codEspecie(0);
            autoInfracao.setNr_codMunicipio(0);
            autoInfracao.setNr_codCor(0);
            autoInfracao.setNr_codTipo(0);
            autoInfracao.setNr_anoModelo(0);
            autoInfracao.setNr_codMarcaCET(0);
            autoInfracao.setDc_uf("");
            return;
        }

        Session session = FacesContextUtil.getRequestSession();

        if (!dc_placa.isEmpty()) {
            vpc_dc_local = "";
            vlc_sql = "select ifnull(b.dc_categoria, space(30)) as dc_categoria, ";
            vlc_sql += "ifnull(c.dc_marca, space(35)) as dc_marca, ";
            vlc_sql += "ifnull(d.dc_especie, space(15)) as dc_especie, ";
            vlc_sql += "ifnull(e.dc_municipio, space(40)) as dc_municipio, ";
            vlc_sql += "ifnull(f.dc_cor, space(15)) as dc_cor, ";
            vlc_sql += "ifnull(g.dc_descricao, space(30)) as dc_tipo, ";
            vlc_sql += "a.nr_codCategoria, a.nr_codMarcaDenatran, a.nr_codEspecie, a.nr_codMunicipio,";
            vlc_sql += "a.nr_codCor, a.nr_codTipoDenatran, a.nr_codMarcaCET, ";
            vlc_sql += "a.nr_anoModelo, e.dc_uf ";
            vlc_sql += "from veiculo a left outer join veiculoCategoria b on a.nr_codCategoria = b.nr_codigo ";
            vlc_sql += "left outer join veiculoMarcaDenatran c on a.nr_codMarcaDenatran = c.nr_codigo ";
            vlc_sql += "left outer join veiculoEspecie d on a.nr_codEspecie = d.nr_codigo ";
            vlc_sql += "left outer join municipio e on a.nr_codMunicipio = e.nr_codigo ";
            vlc_sql += "left outer join veiculoCor f on a.nr_codCor = f.nr_codigo ";
            vlc_sql += "left outer join veiculoTipoDenatran g on a.nr_codTipoDenatran = g.nr_codigo ";
            vlc_sql += "where a.dc_placa = '" + dc_placa + "' ";

            query = session.createSQLQuery(vlc_sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            consulta = query.list();

            if (consulta.size() > 0) {

                for (Object oInfracao : consulta) {
                    Map row = (Map) oInfracao;

                    vpc_dc_mensagem = "";    
                    vpc_dc_marca = row.get("dc_marca").toString();
                    vpc_dc_especie = row.get("dc_especie").toString();
                    vpc_dc_categoria = row.get("dc_categoria").toString();
                    vpc_dc_tipo = row.get("dc_tipo").toString();
                    vpc_dc_cor = row.get("dc_cor").toString();
                    vpc_dc_municipio = row.get("dc_municipio").toString();

                    autoInfracao.setNr_codCategoria(Integer.parseInt(row.get("nr_codCategoria").toString()));
                    autoInfracao.setNr_codMarca(Integer.parseInt(row.get("nr_codMarcaDenatran").toString()));
                    autoInfracao.setNr_codEspecie(Integer.parseInt(row.get("nr_codEspecie").toString()));
                    autoInfracao.setNr_codMunicipio(Integer.parseInt(row.get("nr_codMunicipio").toString()));
                    autoInfracao.setNr_codCor(Integer.parseInt(row.get("nr_codCor").toString()));
                    autoInfracao.setNr_codTipo(Integer.parseInt(row.get("nr_codTipoDenatran").toString()));
                    autoInfracao.setNr_anoModelo(Integer.parseInt(row.get("nr_anoModelo").toString()));
                    autoInfracao.setNr_codMarcaCET(Integer.parseInt(row.get("nr_codMarcaCET").toString()));
                    autoInfracao.setDc_uf(row.get("dc_uf").toString());
                }
            } else {
                vpc_dc_mensagem = "Placa não encontrada no cadastro de veículos.";
                vpc_dc_marca = "";
                vpc_dc_especie = "";
                vpc_dc_categoria = "";
                vpc_dc_tipo = "";
                vpc_dc_cor = "";
                vpc_dc_municipio = "";

                autoInfracao.setNr_codCategoria(0);
                autoInfracao.setNr_codMarca(0);
                autoInfracao.setNr_codEspecie(0);
                autoInfracao.setNr_codMunicipio(0);
                autoInfracao.setNr_codCor(0);
                autoInfracao.setNr_codTipo(0);
                autoInfracao.setNr_anoModelo(0);
                autoInfracao.setNr_codMarcaCET(0);
                autoInfracao.setDc_uf("");

            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta de Placa realizada com sucesso...", ""));
        }

    }

    public boolean validaPlaca(String dc_placa) {

        boolean vll_retorno = false;
        boolean vll_erro = false;
        char vlc_char;

        if (dc_placa.length() != 7) {
            vll_erro = true;
        } else {
            for (int vln_i = 0; vln_i < dc_placa.length(); vln_i++) {
                vlc_char = dc_placa.charAt(vln_i);
                if (vln_i <= 2) {
                    if (!Character.isLetter(vlc_char)) {
                        vll_erro = true;
                        break;
                    }
                } else if (!Character.isDigit(vlc_char)) {
                    vll_erro = true;
                    break;
                }
            }
        }
        if (vll_erro == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Placa inválida !", ""));
            vll_retorno = false;
        } else {
            vll_retorno = true;
        }

        return vll_retorno;

    }

    public void proximoRegistro(){

    String vlc_sql = "";
    Integer vln_resultado = 0;

    List consulta;
    SQLQuery query;
        
    vpc_dc_mensagem = "";
    vpc_dc_placa = autoInfracao.getDc_placa(); 
    vpn_nr_codInconsistencia = autoInfracao.getNr_codInconsistencia(); 
    vpc_dc_nr_multa = autoInfracao.getDc_nr_multa();

 
    if (vpc_dc_placa.isEmpty() && vpn_nr_codInconsistencia == 0){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe a Placa !", ""));
        return;
    }    

    if (!vpc_dc_placa.equals(" ") && vpn_nr_codInconsistencia != 0){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Para este motivo de inconsistência a placa deve estar em branco !", ""));
        return;
    }

    Session session = FacesContextUtil.getRequestSession();

    try{
        vlc_sql = "update autoInfracao set dc_placa = '" + vpc_dc_placa +  "', nr_codInconsistencia = " + vpn_nr_codInconsistencia;
        vlc_sql += ", lg_uso = 0, nr_status = 2, nr_usuarioDigitacao = 9000, dt_digitacao = '" + dt_atual + "'  where dc_nr_multa = '" + vpc_dc_nr_multa + "' ";
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
    }catch(Exception erro){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro a tentar atualizar a tabela autoInfracao !", ""));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: " + erro + ".", ""));
        return;
    }
    proximaInfracao();
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

    public List getVeiculoMarcaCETs() {
        return veiculoMarcaCETs;
    }

    public void setVeiculoMarcaCETs(List veiculoMarcaCETs) {
        this.veiculoMarcaCETs = veiculoMarcaCETs;
    }

    public List getMotivoInconsistenciaImagens() {
        return motivoInconsistenciaImagens;
    }

    public void setMotivoInconsistenciaImagens(List motivoInconsistenciaImagens) {
        this.motivoInconsistenciaImagens = motivoInconsistenciaImagens;
    }

}
