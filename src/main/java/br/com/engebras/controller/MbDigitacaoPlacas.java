
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
    private String vlc_data;
    private Integer vpn_ptn_images;
    private List veiculoMarcaCETs = new ArrayList<>();
    private List motivoInconsistenciaImagens = new ArrayList<>();
    private Date dt_atual;
    private StreamedContent imagemInfracao;
    private CroppedImage croppedImage;
    
    private String imagemVeiculo;
    private String imagemOriginal;
    private String imagemAtual;
    private String imagemNova;
    private String vpc_dirUpload;
    private String vpc_dirImagens;
    private String imagemObliteracao;
    private Boolean vll_controle;
    
    /**
     *
     * @throws FileNotFoundException
     */
    @PostConstruct
    public void init() {
        boolean vll_retorno;
        try{
            vpn_ptn_images = 0;
            images = new ArrayList<String>();
            vll_retorno = proximaInfracao();
            atualizaTela(vpc_dc_nr_multa);
            atualizaListaImagem();
            getImagemInfracao();
        }catch(Exception erro){

        }
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
        iniciaVariaveis();
        geraListaVeiculoMarcaCET();
        geraListaMotivoInconsistenciaImagem();
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

    public void proximoRegistro(){

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
    
    if (autoInfracao.getNr_codInconsistencia() == null){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Código inconsistência: null.", ""));
        return;
    }
    
    if (autoInfracao.getNr_codInconsistencia() == 99){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe o Motivo Invalidação !", ""));
        return;
    }
    
    if (vpc_dc_placa.isEmpty() && autoInfracao.getNr_codInconsistencia() == 0){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe a Placa !", ""));
        return;
    }
    
    if (!vpc_dc_placa.isEmpty() && autoInfracao.getNr_codInconsistencia() > 0){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Para este motivo de inconsistência a placa deve estar em branco !", ""));
        return;
    }

    Session session = FacesContextUtil.getRequestSession();

    try{
        vlc_sql = "update autoInfracao set dc_placa = '" + vpc_dc_placa +  "', nr_codInconsistencia = " + autoInfracao.getNr_codInconsistencia();
        vlc_sql += ", lg_uso = 0, nr_status = 2, nr_usuarioDigitacao = 9000, dt_digitacao = '" + vlc_data + "'  where dc_nr_multa = '" + vpc_dc_nr_multa + "' ";
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
    }catch(Exception erro){
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
        if (images.size() == 0){
            foto = new File(vpc_dirImagens + "BRANCO.jpg");
            imagemVeiculo = "";
        }else {
            foto = new File(vpc_dirImagens + images.get(vpn_ptn_images));
            imagemVeiculo = images.get(vpn_ptn_images);
        }
       

        if (!foto.exists()){
            foto = new File(vpc_dirImagens + "BRANCO.jpg");
            imagemVeiculo = "";
        }

        imagemObliteracao = vpc_dirUpload + imagemVeiculo;
        
        DefaultStreamedContent content=null;
        try{
            BufferedInputStream in=new BufferedInputStream(new FileInputStream(foto));
            byte[] bytes=new byte[in.available()];
            in.read(bytes);
            in.close();
            content=new DefaultStreamedContent(new ByteArrayInputStream(bytes),"image/jpeg");
        }catch(Exception e){
            ;
        }
        return content;
    }
    
    public void uploadImagem() {

        File arqOrigem = new File(vpc_dirImagens + imagemVeiculo);

        FacesContext facesContext = FacesContext.getCurrentInstance();  
        ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();  
//        String destino = scontext.getRealPath("/resources/upload/") + "/" + nomeArquivo;
        String destino = scontext.getRealPath(vpc_dirUpload) + "/" + imagemVeiculo;  
        File arqDestino = new File(destino);

        try{
        if (copiaArquivos(arqOrigem, arqDestino) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Problemas para copiar o arquivo: " + imagemVeiculo + " para o diretorio temporário. Arquivo não foi atualizado !", ""));
            return;
        };
        } catch(Exception erro){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro: " + erro + ",", ""));
        }

        
    }    
    
    public boolean copiaArquivos(File arqOrigem, File arqDestino) throws IOException{
        boolean retorno = true; 
        
        if (arqDestino.exists())
        {
            arqDestino.delete();
        }
        
        FileChannel origemChannel = null; 
        FileChannel destinoChannel = null; 
        
        try 
        {
            origemChannel = new FileInputStream(arqOrigem).getChannel(); 
            destinoChannel = new FileOutputStream(arqDestino).getChannel();
            origemChannel.transferTo(0, origemChannel.size(), destinoChannel);
        }catch(Exception erro)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao tentar copiar o arquivo: " + arqOrigem.toString() + ". Erro: " + erro, ""));
            retorno = false;
        }
        finally {
            if (origemChannel != null && origemChannel.isOpen())
            {
                origemChannel.close();
            }
            if (destinoChannel != null && destinoChannel.isOpen())
            {
                destinoChannel.close();
            }
        }
        
        return retorno; 
        
    }

    
    public void navegaImagem(String direcao){
        
        if (direcao.equals("PROXIMO")){
            if (vpn_ptn_images >= (images.size()-1)){
                vpn_ptn_images = (images.size()-1);
            }else  {
                vpn_ptn_images ++;
            }
                
        }else if (direcao.equals("ANTERIOR")){
            if (vpn_ptn_images > 0){
                vpn_ptn_images --;
            }
        }
        
        getImagemInfracao();
        
    }
    
    public void atualizaListaImagem(){

        String vlc_sql = "";
        List consulta;
        SQLQuery query;
        Session session = FacesContextUtil.getRequestSession();
                
        images.clear();
        if (vpc_dc_nr_multa.isEmpty()){
            images.add("BRANCO.jpg");    
        }else {
 
            vlc_sql = "select a.dc_nr_multa, a.dc_nomeImagem ";
            vlc_sql += "from autoInfracaoImagem a ";
            vlc_sql += "where a.dc_nr_multa = '" + vpc_dc_nr_multa + "' ";

            query = session.createSQLQuery(vlc_sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            consulta = query.list();
            
            if (consulta.size() == 0){
                images.add("BRANCO.jpg");
            }else {
                for (Object oInfracao : consulta) {
                    Map row = (Map) oInfracao;

                    images.add(row.get("dc_nomeImagem").toString());

                }
            }
 
        }    
        consulta =  null;
        
    }
    
    public void crop() throws IOException{
        if(croppedImage == null){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "croppedImage está null... ",""));
        }
       ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
       imagemOriginal = imagemVeiculo;
       imagemAtual = imagemVeiculo;
       //geraNovaImagem();
       imagemNova = getFileName(imagemOriginal) + "_obl.jpg";
       BufferedImage image = ImageIO.read(new File(servletContext.getRealPath(vpc_dirUpload) + "/" + imagemAtual));
       FacesContext facesContext = FacesContext.getCurrentInstance();  
 
       Graphics2D graphics = image.createGraphics();
       graphics.setColor(Color.BLACK);
       graphics.fillRect(croppedImage.getLeft(), croppedImage.getTop(), croppedImage.getWidth(), croppedImage.getHeight());
       graphics.dispose();
//       File ImagemDestino = new File(servletContext.getRealPath("") + File.separator + "upload" + File.separator + imagemNova);
       File ImagemNova = new File(servletContext.getRealPath(vpc_dirUpload) + "/" + imagemNova);
       ImageIO.write(image, "jpg", ImagemNova);
//       imagemVeiculo = servletContext.getRealPath("") + File.separator + "temp" + File.separator + imagemNova;
//       ImagemDestino = new File(servletContext.getRealPath("") + File.separator + "upload" + File.separator + imagemVeiculo);
       File ImagemDestino = new File(servletContext.getRealPath(vpc_dirUpload) + "/" + imagemVeiculo);
       ImageIO.write(image, "jpg", ImagemDestino);
//       setImagemVeiculo(imagemNova);
//       setImagemAtual(imagemNova);
//       setImagemObliteracao(vpc_dirUpload + imagemNova);
       File arqDestino = new File(vpc_dirImagens + imagemVeiculo);
        try{
        if (copiaArquivos(ImagemNova, arqDestino) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Problemas para copiar o arquivo: " + imagemVeiculo + " para o repositório. Arquivo não foi atualizado !", ""));
            return;
        };
        } catch(Exception erro){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro: " + erro + ",", ""));
        }

//       ImagemNova.delete();
       if (vll_controle == true){
           setImagemObliteracao(vpc_dirUpload + imagemNova);
           vll_controle = false;
       } else {
           setImagemObliteracao(vpc_dirUpload + imagemVeiculo);
           vll_controle = true;
       }
//       imagemVeiculo = imagemAtual;
//       setImagemObliteracao(vpc_dirUpload + imagemVeiculo);
       
    }
    
    private void geraNovaImagem() {
        imagemNova = getFileName(imagemOriginal) + getNumeroRandomico() + ".jpg";
    }

    private String getNumeroRandomico() {
      int i = (int) (Math.random() * 10000);
      return String.valueOf(i);
    }

    public static String getFileName(String fileName) {
		if(fileName == null) {
			return null;
		}
		if(fileName.equals("")) {
			return "";
		}
		String[] valores = fileName.split("\\.");

		if(valores.length == 1) {
			return fileName;
		}
		StringBuffer sbResult = new StringBuffer();
		for(int i=0;i<valores.length -1;i++){
		    if(i!=0)
		        sbResult.append(".");
		    sbResult.append(valores[i]);
		}		
		return sbResult.toString();
    }

    public void atualizaObliteracao(){
        
    }

    
    
    private void iniciaVariaveis(){
        vpc_dirUpload = "/resources/upload/" ;
        vpc_dirImagens = "c:\\samt\\sp\\";
        vll_controle = true;

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

    public String getImagemOriginal() {
        return imagemOriginal;
    }

    public void setImagemOriginal(String imagemOriginal) {
        this.imagemOriginal = imagemOriginal;
    }

    public String getImagemAtual() {
        return imagemAtual;
    }

    public void setImagemAtual(String imagemAtual) {
        this.imagemAtual = imagemAtual;
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


}
