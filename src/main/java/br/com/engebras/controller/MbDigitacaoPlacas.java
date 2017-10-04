
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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import static org.apache.log4j.LogSF.log;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import com.sun.jna.Library;
import com.sun.jna.Native;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.faces.bean.ViewScoped;
import javax.imageio.stream.FileImageOutputStream;
import javax.faces.context.ExternalContext;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "mbDigitacaoPlacas")
@ViewScoped
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
    private String vlc_data;
    private Integer vpn_ptn_images;
    private Integer vpn_nr_obliteracao;
    private List veiculoMarcaCETs = new ArrayList<>();
    private List motivoInconsistenciaImagens = new ArrayList<>();
    private Date dt_atual;
    private StreamedContent imagemInfracao;
    private CroppedImage croppedImage;

    private String imagemVeiculo;
    private String imagemNova;
    private String vpc_dirUpload;
    private String vpc_dirImagens;
    private String imagemObliteracao;
    private String imagemOrigem;
    private Integer vln_controle;
    private String newImageName;
    private Libegb lib;
    private String mensagemObliteracao;

    private Integer vpn_X1_top;
    private Integer vpn_Y1_top;
    private Integer vpn_X1_bottom;
    private Integer vpn_Y1_bottom;

    private Integer vpn_X2_top;
    private Integer vpn_Y2_top;
    private Integer vpn_X2_bottom;
    private Integer vpn_Y2_bottom;

    private Integer vpn_X3_top;
    private Integer vpn_Y3_top;
    private Integer vpn_X3_bottom;
    private Integer vpn_Y3_bottom;


    /**
     *
     * @throws FileNotFoundException
     */
    @PostConstruct
    public void init() {
        vln_controle = 0;
        vpn_nr_obliteracao = 0;
    }

    public List<String> getImages() {
        return images;
    }

    public MbDigitacaoPlacas() {
        boolean vll_retorno;
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
        iniciaVariaveis();
        geraListaVeiculoMarcaCET();
        geraListaMotivoInconsistenciaImagem();
        vpn_ptn_images = 0;
        images = new ArrayList<String>();
        try {
            vll_retorno = proximaInfracao();
            atualizaTela(vpc_dc_nr_multa);
        } catch (Exception erro) {

        }
        atualizaListaImagem();
        getImagemInfracao();
        uploadImagem();
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
            vpc_dc_nr_multa = dc_nr_multa;
            atualizaTela(dc_nr_multa);

            return true;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Não existe mais infrações para digitação...", ""));
            vpc_dc_nr_multa = "";
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

                vpc_dc_local = row.get("dc_local").toString().trim();
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
        vlc_sql = "select a.dc_inconsistencia, a.nr_codigo from motivoInconsistenciaImagem a order by a.dc_inconsistencia";

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

            if (dc_placa.isEmpty()) {
                vpc_dc_mensagem = "";
            } else {
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

    public void proximoRegistro() {

        String vlc_sql = "";
        Integer vln_resultado = 0;

        List consulta;
        SQLQuery query;

        vpc_dc_mensagem = "";
        vpc_dc_placa = autoInfracao.getDc_placa();
        vpc_dc_nr_multa = autoInfracao.getDc_nr_multa();
        vpn_ptn_images = 0;

        dt_atual = new Date();

        String data_formato = "yyyyMMdd";
        SimpleDateFormat data_formatada = new SimpleDateFormat(data_formato);
        vlc_data = data_formatada.format(dt_atual);

        if (autoInfracao.getNr_codInconsistencia() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Código inconsistência: null.", ""));
            return;
        }

        if (autoInfracao.getNr_codInconsistencia() == 99) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe o Motivo Invalidação !", ""));
            return;
        }

        if (vpc_dc_placa.isEmpty() && autoInfracao.getNr_codInconsistencia() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe a Placa !", ""));
            return;
        }

        if (!vpc_dc_placa.isEmpty() && autoInfracao.getNr_codInconsistencia() > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Para este motivo de inconsistência a placa deve estar em branco !", ""));
            return;
        }

        Session session = FacesContextUtil.getRequestSession();

        try {
            vlc_sql = "update autoInfracao set dc_placa = '" + vpc_dc_placa + "', nr_codInconsistencia = " + autoInfracao.getNr_codInconsistencia();
            vlc_sql += ", lg_uso = 0, nr_status = 2, nr_usuarioDigitacao = 9000, dt_digitacao = '" + vlc_data + "'  where dc_nr_multa = '" + vpc_dc_nr_multa + "' ";
            query = session.createSQLQuery(vlc_sql);
            vln_resultado = query.executeUpdate();
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao tentar atualizar a tabela autoInfracao !", ""));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: " + erro + ".", ""));
            return;
        }

        proximaInfracao();
        atualizaListaImagem();

    }

    public StreamedContent getImagemInfracao() {

//        File foto=new File("c:\\SAMT\\SP\\000010102488718260_00.jpg");
        File foto;
        if (images.size() == 0) {
            foto = new File(vpc_dirImagens + "BRANCO.jpg");
            imagemVeiculo = "";
        } else {
            foto = new File(vpc_dirImagens + images.get(vpn_ptn_images));
            imagemVeiculo = images.get(vpn_ptn_images);
        }

        if (!foto.exists()) {
            foto = new File(vpc_dirImagens + "BRANCO.jpg");
            imagemVeiculo = "";
        }

        mensagemJS("imagem infracao: " + foto + ".");
        imagemObliteracao = vpc_dirUpload + imagemVeiculo;

        DefaultStreamedContent content = null;
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(foto));
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
            content = new DefaultStreamedContent(new ByteArrayInputStream(bytes), "image/jpeg");
        } catch (Exception e) {
            ;
        }
        return content;
    }

    public void iniciaObliteracao(){
        vpn_nr_obliteracao = 0;
        vpn_X1_top = 0;
        vpn_Y1_top = 0;
        vpn_X1_bottom = 0;
        vpn_Y1_bottom = 0;

        vpn_X2_top = 0;
        vpn_Y2_top = 0;
        vpn_X2_bottom = 0;
        vpn_Y2_bottom = 0;

        vpn_X3_top = 0;
        vpn_Y3_top = 0;
        vpn_X3_bottom = 0;
        vpn_Y3_bottom = 0;
        mensagemObliteracao = "Mensagem...";
        uploadImagem();

    }

    public void uploadImagem() {

        File arqOrigem = new File(vpc_dirImagens + imagemVeiculo);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
//        String destino = scontext.getRealPath("/resources/upload/") + "/" + nomeArquivo;
        String destino = scontext.getRealPath(vpc_dirUpload) + "/" + imagemVeiculo;
        File arqDestino = new File(destino);
        try {
            if (copiaArquivos(arqOrigem, arqDestino) == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Problemas para copiar o arquivo: " + imagemVeiculo + " para o diretorio temporário. Arquivo não foi atualizado !", ""));
                return;
            };
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro: " + erro + ",", ""));
        }

        imagemOrigem = getFileName(imagemVeiculo) + "_origem.jpg";
        destino = scontext.getRealPath(vpc_dirUpload) + "/" + imagemOrigem;
        arqDestino = new File(destino);
        try {
            if (copiaArquivos(arqOrigem, arqDestino) == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Problemas para copiar o arquivo: " + imagemOrigem + " para o diretorio temporário. Arquivo não foi atualizado !", ""));
                return;
            };
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro: " + erro + ",", ""));
        }


    }

    public boolean copiaArquivos(File arqOrigem, File arqDestino) throws IOException {
        boolean retorno = true;

        if (arqDestino.exists()) {
            arqDestino.delete();
        }

        FileChannel origemChannel = null;
        FileChannel destinoChannel = null;

        try {
            origemChannel = new FileInputStream(arqOrigem).getChannel();
            destinoChannel = new FileOutputStream(arqDestino).getChannel();
            origemChannel.transferTo(0, origemChannel.size(), destinoChannel);
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao tentar copiar o arquivo: " + arqOrigem.toString() + ". Erro: " + erro, ""));
            retorno = false;
        } finally {
            if (origemChannel != null && origemChannel.isOpen()) {
                origemChannel.close();
            }
            if (destinoChannel != null && destinoChannel.isOpen()) {
                destinoChannel.close();
            }
        }

        return retorno;

    }

    public void navegaImagem(String direcao) {

        if (direcao.equals("PROXIMO")) {
            if (vpn_ptn_images >= (images.size() - 1)) {
                vpn_ptn_images = (images.size() - 1);
            } else {
                vpn_ptn_images++;
            }

        } else if (direcao.equals("ANTERIOR")) {
            if (vpn_ptn_images > 0) {
                vpn_ptn_images--;
            }
        }

        getImagemInfracao();

    }

    public void atualizaListaImagem() {

        String vlc_sql = "";
        List consulta;
        SQLQuery query;
        Session session = FacesContextUtil.getRequestSession();

        images.clear();
        if (vpc_dc_nr_multa.isEmpty()) {
            images.add("BRANCO.jpg");
        } else {

            vlc_sql = "select a.dc_nr_multa, a.dc_nomeImagem ";
            vlc_sql += "from autoInfracaoImagem a ";
            vlc_sql += "where a.dc_nr_multa = '" + vpc_dc_nr_multa + "' ";

            query = session.createSQLQuery(vlc_sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            consulta = query.list();

            if (consulta.size() == 0) {
                images.add("BRANCO.jpg");
            } else {
                for (Object oInfracao : consulta) {
                    Map row = (Map) oInfracao;

                    images.add(row.get("dc_nomeImagem").toString());

                }
            }

        }
        consulta = null;

    }

    public void crop_libegb() throws IOException {

        int vln_retornoOblitera;
        vln_retornoOblitera = 0;
        int vln_h_top, vln_v_top, vln_h_bottom, vln_v_bottom;
        vln_h_top = 0;
        vln_v_top = 0;
        vln_h_bottom = 0;
        vln_v_bottom = 0;

        if (croppedImage == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "croppedImage está null... ", ""));
        }

        vln_controle++;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        imagemNova = getFileName(imagemVeiculo) + "_" + vln_controle + ".jpg";
        BufferedImage image = ImageIO.read(new File(servletContext.getRealPath(vpc_dirUpload) + "/" + imagemVeiculo));
        FacesContext facesContext = FacesContext.getCurrentInstance();

//       Graphics2D graphics = image.createGraphics();
//       graphics.setColor(Color.BLACK);
//       graphics.fillRect(croppedImage.getLeft(), croppedImage.getTop(), croppedImage.getWidth(), croppedImage.getHeight());
//       graphics.dispose();
        vln_h_top = croppedImage.getLeft();
        vln_v_top = croppedImage.getTop();
        vln_h_bottom = croppedImage.getLeft() + croppedImage.getWidth();
        vln_v_bottom = croppedImage.getTop() + croppedImage.getHeight();

        String imgOrigem = externalContext.getRealPath(vpc_dirUpload) + File.separator + imagemVeiculo;
        String imgDestino = externalContext.getRealPath(vpc_dirUpload) + File.separator + imagemNova;

        vln_retornoOblitera = lib.egb_win_oblitera_imagem(imgOrigem, imgDestino, vln_h_top, vln_v_top, vln_h_bottom, vln_v_bottom, 0, 0, 0, 0, 0, 0, 0, 0, 2);

        mensagemObliteracao = "Resultado teste Obliteração: " + vln_retornoOblitera + "...";
        File ImagemNova = new File(servletContext.getRealPath(vpc_dirUpload) + "/" + imagemNova);
//       ImageIO.write(image, "jpg", ImagemNova);
        File ImagemDestino = new File(servletContext.getRealPath(vpc_dirUpload) + "/" + imagemVeiculo);
//       ImageIO.write(image, "jpg", ImagemDestino);

        try {
            if (copiaArquivos(ImagemNova, ImagemDestino) == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Problemas para copiar o arquivo: " + imagemVeiculo + " para o diretório upload !", ""));
                return;
            };
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Problemas para copiar o arquivo: " + imagemVeiculo + " para o diretório upload !", ""));
        }

        File arqDestino = new File(vpc_dirImagens + imagemVeiculo);
        try {
            if (copiaArquivos(ImagemNova, arqDestino) == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Problemas para copiar o arquivo: " + imagemVeiculo + " para o repositório. Arquivo não foi atualizado !", ""));
                return;
            };
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro: " + erro + ",", ""));
        }

        setImagemObliteracao(vpc_dirUpload + imagemNova);

    }

    public void crop() throws IOException {
        if (croppedImage == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "croppedImage está null... ", ""));
        }

        if (vpn_nr_obliteracao >= 3){
            mensagemObliteracao = "Número máximo de áreas de obliteração é 3.";
            return;
        }
        vln_controle++;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        imagemNova = getFileName(imagemVeiculo) + "_" + vln_controle + ".jpg";
        BufferedImage image = ImageIO.read(new File(servletContext.getRealPath(vpc_dirUpload) + "/" + imagemVeiculo));
        FacesContext facesContext = FacesContext.getCurrentInstance();

        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(croppedImage.getLeft(), croppedImage.getTop(), croppedImage.getWidth(), croppedImage.getHeight());
        graphics.dispose();
        File ImagemNova = new File(servletContext.getRealPath(vpc_dirUpload) + "/" + imagemNova);
        ImageIO.write(image, "jpg", ImagemNova);
        File ImagemDestino = new File(servletContext.getRealPath(vpc_dirUpload) + "/" + imagemVeiculo);
        ImageIO.write(image, "jpg", ImagemDestino);
        File arqDestino = new File(vpc_dirImagens + imagemVeiculo);
        try {
            if (copiaArquivos(ImagemNova, arqDestino) == false) {
                mensagemObliteracao = "Problemas para copiar o arquivo: " + imagemVeiculo + " para o repositório. Arquivo não foi atualizado !";
                return;
            }
        } catch (Exception erro) {
            mensagemObliteracao = "Erro: " + erro + ".";
            return;
        }

        vpn_nr_obliteracao++;

        switch (vpn_nr_obliteracao) {
            case 1:
                vpn_X1_top = croppedImage.getLeft();
                vpn_Y1_top = croppedImage.getTop();
                vpn_X1_bottom = croppedImage.getLeft() + croppedImage.getWidth();
                vpn_Y1_bottom = croppedImage.getTop() + croppedImage.getHeight();
                break;
            case 2:
                vpn_X2_top = croppedImage.getLeft();
                vpn_Y2_top = croppedImage.getTop();
                vpn_X2_bottom = croppedImage.getLeft() + croppedImage.getWidth();
                vpn_Y2_bottom = croppedImage.getTop() + croppedImage.getHeight();
                break;
            case 3:
                vpn_X3_top = croppedImage.getLeft();
                vpn_Y3_top = croppedImage.getTop();
                vpn_X3_bottom = croppedImage.getLeft() + croppedImage.getWidth();
                vpn_Y3_bottom = croppedImage.getTop() + croppedImage.getHeight();
                break;
        }


        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Área selecionada para obliteração com sucesso... ", ""));
        setImagemObliteracao(vpc_dirUpload + imagemNova);
    }

    public void finalizaObliteracao(){

        int vln_retornoOblitera;
        vln_retornoOblitera = 0;

        vln_controle++;

        mensagemObliteracao = "Finalizando obliteração...";

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        imagemNova = getFileName(imagemVeiculo) + "_" + vln_controle + ".jpg";
        String imgOrigem = externalContext.getRealPath(vpc_dirUpload) + File.separator + imagemOrigem;
        String imgDestino = externalContext.getRealPath(vpc_dirUpload) + File.separator + imagemNova;


        if (vpn_X1_bottom > 0 && vpn_Y1_bottom > 0){
            vln_retornoOblitera = lib.egb_win_oblitera_imagem(imgOrigem, imgDestino, vpn_X1_top, vpn_Y1_top, vpn_X1_bottom, vpn_Y1_bottom, vpn_X2_top, vpn_Y2_top, vpn_X2_bottom, vpn_Y2_bottom, vpn_X3_top, vpn_Y3_top, vpn_X3_bottom, vpn_Y3_bottom,2);
            mensagemObliteracao = "Retorno da obliteração: " + vln_retornoOblitera + ".";
        } else{
            mensagemObliteracao = "Nenhuma área selecionada para obliteração!";
            return;
        }

        switch (vln_retornoOblitera) {
            case 0:
                File ImagemNova = new File(servletContext.getRealPath(vpc_dirUpload) + "/" + imagemNova);
                File ImagemDestino = new File(servletContext.getRealPath(vpc_dirUpload) + "/" + imagemVeiculo);

                try {
                    if (copiaArquivos(ImagemNova, ImagemDestino) == false) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Problemas para copiar o arquivo: " + imagemVeiculo + " para o diretório upload !", ""));
                        return;
                    };
                } catch (Exception erro) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Problemas para copiar o arquivo: " + imagemVeiculo + " para o diretório upload !", ""));
                }

                File arqDestino = new File(vpc_dirImagens + imagemVeiculo);
                try {
                    if (copiaArquivos(ImagemNova, arqDestino) == false) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Problemas para copiar o arquivo: " + imagemVeiculo + " para o repositório. Arquivo não foi atualizado !", ""));
                        return;
                    }
                } catch (Exception erro) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro: " + erro + ",", ""));
                }

                setImagemObliteracao(vpc_dirUpload + imagemNova);
                break;
            case -3:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Imagem já está obliterada!", ""));
                break;
            default:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao tentar obliterar a imagem!", ""));
        }    
    }

    public void voltarObliteracao(){
        mensagemObliteracao = "Executei o voltarObliteracao...";

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        
        for (int i = 1; i <= vln_controle; i++){
            imagemNova = getFileName(imagemVeiculo) + "_" + i + ".jpg";
            
            File imgTemp = new File(externalContext.getRealPath(vpc_dirUpload) + File.separator + imagemNova);
            if (imgTemp.exists()){
                imgTemp.delete();
            }
        }    
    }
    
    public void limpaObliteracao(){

        int vln_desObliterou; 
        vln_desObliterou = 0;
        
//        vln_desObliterou = egb_win_desoblitera_imagem(alltrim(CUR_movimentoLote.dc_diretorioImagem) + "\"  + thisformset.VOC_dc_nomeImagemZoom, VGC_dirTemp + thisformset.VOC_dc_nomeImagemDesobliterada)

        vln_controle++;

        mensagemObliteracao = "Limpa obliteração...";

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        imagemNova = getFileName(imagemVeiculo) + "_" + vln_controle + ".jpg";
        String imgOrigem = externalContext.getRealPath(vpc_dirUpload) + File.separator + imagemOrigem;
        String imgDestino = externalContext.getRealPath(vpc_dirUpload) + File.separator + imagemNova;

        vln_desObliterou = lib.egb_win_desoblitera_imagem(imgOrigem, imgDestino);
        mensagemObliteracao = "Retorno da obliteração: " + vln_desObliterou + ".";

        if (vln_desObliterou == 0){
            File ImagemNova = new File(servletContext.getRealPath(vpc_dirUpload) + "/" + imagemNova);
            File ImagemDestino = new File(servletContext.getRealPath(vpc_dirUpload) + "/" + imagemVeiculo);

            try {
                if (copiaArquivos(ImagemNova, ImagemDestino) == false) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Problemas para copiar o arquivo: " + imagemVeiculo + " para o diretório upload !", ""));
                    return;
                };
            } catch (Exception erro) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Problemas para copiar o arquivo: " + imagemVeiculo + " para o diretório upload !", ""));
            }

            File arqDestino = new File(vpc_dirImagens + imagemVeiculo);
            try {
                if (copiaArquivos(ImagemNova, arqDestino) == false) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Problemas para copiar o arquivo: " + imagemVeiculo + " para o repositório. Arquivo não foi atualizado !", ""));
                    return;
                }
            } catch (Exception erro) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro: " + erro + ",", ""));
            }

            setImagemObliteracao(vpc_dirUpload + imagemNova);
        }
    }


    public void crop_retanguloPreto() throws IOException {
        if (croppedImage == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "croppedImage está null... ", ""));
        }

        vln_controle++;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        setNewImageName(getFileName(imagemVeiculo) + "_" + vln_controle + "_cut");
        String newFileName = externalContext.getRealPath(vpc_dirUpload) + File.separator + getNewImageName() + ".jpg";
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
            imageOutput.close();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ocorreu um erro ao recortar a imagem."));
            return;
        }

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        imagemNova = getFileName(imagemVeiculo) + "_" + vln_controle + ".jpg";
        BufferedImage image = ImageIO.read(new File(servletContext.getRealPath(vpc_dirUpload) + "/" + imagemVeiculo));
        FacesContext facesContext = FacesContext.getCurrentInstance();

        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(croppedImage.getLeft(), croppedImage.getTop(), croppedImage.getWidth(), croppedImage.getHeight());
        graphics.dispose();
        File ImagemNova = new File(servletContext.getRealPath(vpc_dirUpload) + "/" + imagemNova);
        ImageIO.write(image, "jpg", ImagemNova);
        File ImagemDestino = new File(servletContext.getRealPath(vpc_dirUpload) + "/" + imagemVeiculo);
        ImageIO.write(image, "jpg", ImagemDestino);
        File arqDestino = new File(vpc_dirImagens + imagemVeiculo);
        try {
            if (copiaArquivos(ImagemNova, arqDestino) == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Problemas para copiar o arquivo: " + imagemVeiculo + " para o repositório. Arquivo não foi atualizado !", ""));
                return;
            };
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro: " + erro + ",", ""));
        }

        setImagemObliteracao(vpc_dirUpload + imagemNova);

    }

    private void geraNovaImagem() {
        imagemNova = getFileName(imagemVeiculo) + getNumeroRandomico() + ".jpg";
    }

    private String getNumeroRandomico() {
        int i = (int) (Math.random() * 10000);
        return String.valueOf(i);
    }

    public static String getFileName(String fileName) {
        if (fileName == null) {
            return null;
        }
        if (fileName.equals("")) {
            return "";
        }
        String[] valores = fileName.split("\\.");

        if (valores.length == 1) {
            return fileName;
        }
        StringBuffer sbResult = new StringBuffer();
        for (int i = 0; i < valores.length - 1; i++) {
            if (i != 0) {
                sbResult.append(".");
            }
            sbResult.append(valores[i]);
        }
        return sbResult.toString();
    }

    private void iniciaVariaveis() {
        vpc_dirUpload = "/resources/upload/";
        vpc_dirImagens = "c:\\samt\\sp\\";
        imagemOrigem = "";
//        try {
//            System.load("C:/dirlib/libegb.dll");
//        } catch (UnsatisfiedLinkError e) {
//            System.err.println("Native code library falhou ao ler.\n" + e);
//            System.exit(1);
//        }

        vpn_X1_top = 0;
        vpn_Y1_top = 0;
        vpn_X1_bottom = 0;
        vpn_Y1_bottom = 0;

        vpn_X2_top = 0;
        vpn_Y2_top = 0;
        vpn_X2_bottom = 0;
        vpn_Y2_bottom = 0;

        vpn_X3_top = 0;
        vpn_Y3_top = 0;
        vpn_X3_bottom = 0;
        vpn_Y3_bottom = 0;

  //     lib = (Libegb) Native.loadLibrary("c:/dirlib/libegb.dll", Libegb.class);
        mensagemObliteracao = "Mensagem...";
    }

    public interface Libegb extends Library {

        public int egb_win_versao_egblib();

        public int egb_win_get_dados_multa_file(String filename, byte[] st_dados2);

        public void egb_win_converte_data_jul(int data_jul, int data_base, byte[] sDadosImagem3);

        public int egb_win_oblitera_imagem(String arqOrigem, String arqDestino, int Xinicio1, int Yinicio1, int Xfim1, int Yfim1, int Xinicio2, int Yinicio2, int Xfim2, int Yfim2, int Xinicio3, int Yinicio3, int Xfim3, int Yfim3, int oblit_mode);

        public int egb_win_desoblitera_imagem(String arqOrigem, String arqDestino);

    }

    public void executarJS(){

        RequestContext.getCurrentInstance().execute("trocaImagemZoom('/samtWebAlpha/resources/upload/image_nova.JPG','/samtWebAlpha/resources/upload/image_nova_zoom.JPG');");
    }

    public void mensagemJS(String plc_mensagem){
        RequestContext.getCurrentInstance().execute("mensagemAlerta('" + plc_mensagem + "');");
        
    }

    
    public boolean copiaArquivos2() throws IOException {
        boolean retorno = true;

        File arqOrigem;
        File arqDestino;
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        
        arqOrigem = new File("C:/SAMT/teste/image_teste.jpg");
        arqDestino = new File(servletContext.getRealPath(vpc_dirUpload) + "/xxx.jpg");
        
        if (arqDestino.exists()) {
            arqDestino.delete();
        }

        Files.copy(arqOrigem.toPath(), arqDestino.toPath(),StandardCopyOption.REPLACE_EXISTING);
        
        
        return retorno;

    }
    
    
    
    
    public void setImagemInfracao(StreamedContent imagemInfracao) {
        this.imagemInfracao = imagemInfracao;
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

    public void setVpc_dc_local(String vpc_dc_local2) {
        this.vpc_dc_local = vpc_dc_local2;
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

    public String getVpc_dc_placa() {
        return vpc_dc_placa;
    }

    public void setVpc_dc_placa(String vpc_dc_placa) {
        this.vpc_dc_placa = vpc_dc_placa;
    }

    public String getVpc_dc_nr_multa() {
        return vpc_dc_nr_multa;
    }

    public void setVpc_dc_nr_multa(String vpc_dc_nr_multa) {
        this.vpc_dc_nr_multa = vpc_dc_nr_multa;
    }

    public String getVlc_data() {
        return vlc_data;
    }

    public void setVlc_data(String vlc_data) {
        this.vlc_data = vlc_data;
    }

    public Date getDt_atual() {
        return dt_atual;
    }

    public void setDt_atual(Date dt_atual) {
        this.dt_atual = dt_atual;
    }

    public Integer getVpn_ptn_images() {
        return vpn_ptn_images;
    }

    public void setVpn_ptn_images(Integer vpn_ptn_images) {
        this.vpn_ptn_images = vpn_ptn_images;
    }

    public CroppedImage getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }

    public String getImagemVeiculo() {
        return imagemVeiculo;
    }

    public void setImagemVeiculo(String imagemVeiculo) {
        this.imagemVeiculo = imagemVeiculo;
    }

    public String getImagemNova() {
        return imagemNova;
    }

    public void setImagemNova(String imagemNova) {
        this.imagemNova = imagemNova;
    }

    public String getVpc_dirUpload() {
        return vpc_dirUpload;
    }

    public void setVpc_dirUpload(String vpc_dirUpload) {
        this.vpc_dirUpload = vpc_dirUpload;
    }

    public String getVpc_dirImagens() {
        return vpc_dirImagens;
    }

    public void setVpc_dirImagens(String vpc_dirImagens) {
        this.vpc_dirImagens = vpc_dirImagens;
    }

    public String getImagemObliteracao() {
        return imagemObliteracao;
    }

    public void setImagemObliteracao(String imagemObliteracao) {
        this.imagemObliteracao = imagemObliteracao;
    }

    public String getNewImageName() {
        return newImageName;
    }

    public void setNewImageName(String newImageName) {
        this.newImageName = newImageName;
    }

    public String getMensagemObliteracao() {
        return mensagemObliteracao;
    }

    public void setMensagemObliteracao(String mensagemObliteracao) {
        this.mensagemObliteracao = mensagemObliteracao;
    }

    public Integer getVpn_nr_obliteracao() {
        return vpn_nr_obliteracao;
    }

    public void setVpn_nr_obliteracao(Integer vpn_nr_obliteracao) {
        this.vpn_nr_obliteracao = vpn_nr_obliteracao;
    }

    public Integer getVpn_X1_top() {
        return vpn_X1_top;
    }

    public void setVpn_X1_top(Integer vpn_X1_top) {
        this.vpn_X1_top = vpn_X1_top;
    }

    public Integer getVpn_Y1_top() {
        return vpn_Y1_top;
    }

    public void setVpn_Y1_top(Integer vpn_Y1_top) {
        this.vpn_Y1_top = vpn_Y1_top;
    }

    public Integer getVpn_X1_bottom() {
        return vpn_X1_bottom;
    }

    public void setVpn_X1_bottom(Integer vpn_X1_bottom) {
        this.vpn_X1_bottom = vpn_X1_bottom;
    }

    public Integer getVpn_Y1_bottom() {
        return vpn_Y1_bottom;
    }

    public void setVpn_Y1_bottom(Integer vpn_Y1_bottom) {
        this.vpn_Y1_bottom = vpn_Y1_bottom;
    }

    public Integer getVpn_X2_top() {
        return vpn_X2_top;
    }

    public void setVpn_X2_top(Integer vpn_X2_top) {
        this.vpn_X2_top = vpn_X2_top;
    }

    public Integer getVpn_Y2_top() {
        return vpn_Y2_top;
    }

    public void setVpn_Y2_top(Integer vpn_Y2_top) {
        this.vpn_Y2_top = vpn_Y2_top;
    }

    public Integer getVpn_X2_bottom() {
        return vpn_X2_bottom;
    }

    public void setVpn_X2_bottom(Integer vpn_X2_bottom) {
        this.vpn_X2_bottom = vpn_X2_bottom;
    }

    public Integer getVpn_Y2_bottom() {
        return vpn_Y2_bottom;
    }

    public void setVpn_Y2_bottom(Integer vpn_Y2_bottom) {
        this.vpn_Y2_bottom = vpn_Y2_bottom;
    }

    public Integer getVpn_X3_top() {
        return vpn_X3_top;
    }

    public void setVpn_X3_top(Integer vpn_X3_top) {
        this.vpn_X3_top = vpn_X3_top;
    }

    public Integer getVpn_Y3_top() {
        return vpn_Y3_top;
    }

    public void setVpn_Y3_top(Integer vpn_Y3_top) {
        this.vpn_Y3_top = vpn_Y3_top;
    }

    public Integer getVpn_X3_bottom() {
        return vpn_X3_bottom;
    }

    public void setVpn_X3_bottom(Integer vpn_X3_bottom) {
        this.vpn_X3_bottom = vpn_X3_bottom;
    }

    public Integer getVpn_Y3_bottom() {
        return vpn_Y3_bottom;
    }

    public void setVpn_Y3_bottom(Integer vpn_Y3_bottom) {
        this.vpn_Y3_bottom = vpn_Y3_bottom;
    }

    public String getImagemOrigem() {
        return imagemOrigem;
    }

    public void setImagemOrigem(String imagemOrigem) {
        this.imagemOrigem = imagemOrigem;
    }


}
