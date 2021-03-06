/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 21/11/2016
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
import java.sql.SQLException;
import org.hibernate.Transaction;

import br.com.engebras.util.FacesContextUtil;
import br.com.engebras.util.HibernateUtil;
import br.com.engebras.model.dao.InterfaceDAO;
import br.com.engebras.model.dao.HibernateDAO;
import br.com.engebras.model.entities.AutoInfracao;
import br.com.engebras.model.entities.AutoInfracaoImagem;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "mbInicializaDigitacao")
@ViewScoped
public class MbInicializaDigitacao implements Serializable {
 
    private static final long serialVersionUID = 1L; 
    
    public MbInicializaDigitacao(){
        
    }
    
    public void iniciaTabelasDigitacao(){
        String vlc_sql = "";
        SQLQuery query;
        int vln_resultado = 0;
        
        Session session = FacesContextUtil.getRequestSession();

        //excluindo todos os registros da tabela autoInfracao... 
        vlc_sql = "delete from autoInfracao ";
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        
        //excluindo todos os registros da tabela autoInfracaoImagem... 
        vlc_sql = "delete from autoInfracaoImagem ";
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
            
        
        //inserindo registros na tabela autoInfracao... 

        //01
        vlc_sql = "insert into autoInfracao (dc_nr_multa, dc_placa, nr_codCategoria, nr_codMarca,";
        vlc_sql += "nr_codTipo, nr_codCor, nr_codEspecie, nr_codMunicipio, dc_uf, nr_anoModelo, nr_codLocal,";
        vlc_sql += "dc_serieEquipamento, nr_velocidadePermitida, nr_velocidadeAferida, nr_velocidadeConsiderada, ";
        vlc_sql += "dt_infracao, dc_hr_infracao, dc_codAgente, nr_faixa, lg_entrefaixa, nr_codEnquadramento, nr_PorteVeiculo,";
        vlc_sql += "nr_tamanhoVeiculo, dc_placaOCR, nr_codTipoFiscalizacao, dc_numeroLaudo, dt_validadeLaudo, ";
        vlc_sql += "dt_verificacaoLaudo, dc_tipoIsencao, lg_renainf, nr_imagem, nr_codPais, lg_uso, nr_codClassificacaoTamanho,";
        vlc_sql += "nr_tempoAferido, nr_tempoLimite, nr_status, dt_envio,dt_recebe, nr_codInconsistencia) ";
	vlc_sql += "values ('000010102488718260', '', 0, 0, 0,0,0,0,'', 0, 6942, '0001', 50, 61, 54, '20161120', '110346', '0001', 1, 0, 74550, 0,1111, 'EPZ8570', 1, '4444', '20171231', '20161124', '1', 0, 1, 1, 0, 1, 0,0,1,'1900-01-01','1900-01-01',99) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();

        //02
        vlc_sql = "insert into autoInfracao (dc_nr_multa, dc_placa, nr_codCategoria, nr_codMarca,";
        vlc_sql += "nr_codTipo, nr_codCor, nr_codEspecie, nr_codMunicipio, dc_uf, nr_anoModelo, nr_codLocal,";
        vlc_sql += "dc_serieEquipamento, nr_velocidadePermitida, nr_velocidadeAferida, nr_velocidadeConsiderada, ";
        vlc_sql += "dt_infracao, dc_hr_infracao, dc_codAgente, nr_faixa, lg_entrefaixa, nr_codEnquadramento, nr_PorteVeiculo,";
        vlc_sql += "nr_tamanhoVeiculo, dc_placaOCR, nr_codTipoFiscalizacao, dc_numeroLaudo, dt_validadeLaudo, ";
        vlc_sql += "dt_verificacaoLaudo, dc_tipoIsencao, lg_renainf, nr_imagem, nr_codPais, lg_uso, nr_codClassificacaoTamanho,";
        vlc_sql += "nr_tempoAferido, nr_tempoLimite, nr_status, dt_envio,dt_recebe, nr_codInconsistencia) ";
	vlc_sql += "values ('000010102488718500', '', 0, 0, 0,0,0,0,'', 0, 6942, '0001', 50, 70, 63, '20161120', '110410', '0001', 1, 0, 74630, 0,1111, '', 1, '4444', '20171231', '20161124', '1', 0, 1, 1, 0, 1, 0,0,1,'1900-01-01','1900-01-01',99) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();

        //03
        vlc_sql = "insert into autoInfracao (dc_nr_multa, dc_placa, nr_codCategoria, nr_codMarca,";
        vlc_sql += "nr_codTipo, nr_codCor, nr_codEspecie, nr_codMunicipio, dc_uf, nr_anoModelo, nr_codLocal,";
        vlc_sql += "dc_serieEquipamento, nr_velocidadePermitida, nr_velocidadeAferida, nr_velocidadeConsiderada, ";
        vlc_sql += "dt_infracao, dc_hr_infracao, dc_codAgente, nr_faixa, lg_entrefaixa, nr_codEnquadramento, nr_PorteVeiculo,";
        vlc_sql += "nr_tamanhoVeiculo, dc_placaOCR, nr_codTipoFiscalizacao, dc_numeroLaudo, dt_validadeLaudo, ";
        vlc_sql += "dt_verificacaoLaudo, dc_tipoIsencao, lg_renainf, nr_imagem, nr_codPais, lg_uso, nr_codClassificacaoTamanho,";
        vlc_sql += "nr_tempoAferido, nr_tempoLimite, nr_status, dt_envio,dt_recebe, nr_codInconsistencia) ";
	vlc_sql += "values ('000010102488718950', '', 0, 0, 0,0,0,0,'', 0, 6942, '0001', 50, 60, 53, '20161120', '110455', '0001', 1, 0, 74550, 0,1111, '', 1, '4444', '20171231', '20161124', '1', 0, 1, 1, 0, 1, 0,0,1,'1900-01-01','1900-01-01',99) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();

        //04
        vlc_sql = "insert into autoInfracao (dc_nr_multa, dc_placa, nr_codCategoria, nr_codMarca,";
        vlc_sql += "nr_codTipo, nr_codCor, nr_codEspecie, nr_codMunicipio, dc_uf, nr_anoModelo, nr_codLocal,";
        vlc_sql += "dc_serieEquipamento, nr_velocidadePermitida, nr_velocidadeAferida, nr_velocidadeConsiderada, ";
        vlc_sql += "dt_infracao, dc_hr_infracao, dc_codAgente, nr_faixa, lg_entrefaixa, nr_codEnquadramento, nr_PorteVeiculo,";
        vlc_sql += "nr_tamanhoVeiculo, dc_placaOCR, nr_codTipoFiscalizacao, dc_numeroLaudo, dt_validadeLaudo, ";
        vlc_sql += "dt_verificacaoLaudo, dc_tipoIsencao, lg_renainf, nr_imagem, nr_codPais, lg_uso, nr_codClassificacaoTamanho,";
        vlc_sql += "nr_tempoAferido, nr_tempoLimite, nr_status, dt_envio,dt_recebe, nr_codInconsistencia) ";
	vlc_sql += "values ('000010102488719990', '', 0, 0, 0,0,0,0,'', 0, 6942, '0001', 50, 80, 73, '20161120', '110639', '0001', 1, 0, 74630, 0,1111, 'EIE0591', 1, '4444', '20171231', '20161124', '1', 0, 1, 1, 0, 1, 0,0,1,'1900-01-01','1900-01-01',99) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        
        //05
        vlc_sql = "insert into autoInfracao (dc_nr_multa, dc_placa, nr_codCategoria, nr_codMarca,";
        vlc_sql += "nr_codTipo, nr_codCor, nr_codEspecie, nr_codMunicipio, dc_uf, nr_anoModelo, nr_codLocal,";
        vlc_sql += "dc_serieEquipamento, nr_velocidadePermitida, nr_velocidadeAferida, nr_velocidadeConsiderada, ";
        vlc_sql += "dt_infracao, dc_hr_infracao, dc_codAgente, nr_faixa, lg_entrefaixa, nr_codEnquadramento, nr_PorteVeiculo,";
        vlc_sql += "nr_tamanhoVeiculo, dc_placaOCR, nr_codTipoFiscalizacao, dc_numeroLaudo, dt_validadeLaudo, ";
        vlc_sql += "dt_verificacaoLaudo, dc_tipoIsencao, lg_renainf, nr_imagem, nr_codPais, lg_uso, nr_codClassificacaoTamanho,";
        vlc_sql += "nr_tempoAferido, nr_tempoLimite, nr_status, dt_envio,dt_recebe, nr_codInconsistencia) ";
	vlc_sql += "values ('000010102488720040', '', 0, 0, 0,0,0,0,'', 0, 6942, '0001', 50, 60, 53, '20161120', '110644', '0001', 1, 0, 74550, 0,1111, 'FLQ8500', 1, '4444', '20171231', '20161124', '1', 0, 1, 1, 0, 1, 0,0,1,'1900-01-01','1900-01-01',99) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();

        //06
        vlc_sql = "insert into autoInfracao (dc_nr_multa, dc_placa, nr_codCategoria, nr_codMarca,";
        vlc_sql += "nr_codTipo, nr_codCor, nr_codEspecie, nr_codMunicipio, dc_uf, nr_anoModelo, nr_codLocal,";
        vlc_sql += "dc_serieEquipamento, nr_velocidadePermitida, nr_velocidadeAferida, nr_velocidadeConsiderada, ";
        vlc_sql += "dt_infracao, dc_hr_infracao, dc_codAgente, nr_faixa, lg_entrefaixa, nr_codEnquadramento, nr_PorteVeiculo,";
        vlc_sql += "nr_tamanhoVeiculo, dc_placaOCR, nr_codTipoFiscalizacao, dc_numeroLaudo, dt_validadeLaudo, ";
        vlc_sql += "dt_verificacaoLaudo, dc_tipoIsencao, lg_renainf, nr_imagem, nr_codPais, lg_uso, nr_codClassificacaoTamanho,";
        vlc_sql += "nr_tempoAferido, nr_tempoLimite, nr_status, dt_envio,dt_recebe, nr_codInconsistencia) ";
	vlc_sql += "values ('000010102488721150', '', 0, 0, 0,0,0,0,'', 0, 6942, '0001', 50, 60, 53, '20161120', '110835', '0001', 1, 0, 74550, 0,1111, 'FXQ5327', 1, '4444', '20171231', '20161124', '1', 0, 1, 1, 0, 1, 0,0,1,'1900-01-01','1900-01-01',99) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();

        //07
        vlc_sql = "insert into autoInfracao (dc_nr_multa, dc_placa, nr_codCategoria, nr_codMarca,";
        vlc_sql += "nr_codTipo, nr_codCor, nr_codEspecie, nr_codMunicipio, dc_uf, nr_anoModelo, nr_codLocal,";
        vlc_sql += "dc_serieEquipamento, nr_velocidadePermitida, nr_velocidadeAferida, nr_velocidadeConsiderada, ";
        vlc_sql += "dt_infracao, dc_hr_infracao, dc_codAgente, nr_faixa, lg_entrefaixa, nr_codEnquadramento, nr_PorteVeiculo,";
        vlc_sql += "nr_tamanhoVeiculo, dc_placaOCR, nr_codTipoFiscalizacao, dc_numeroLaudo, dt_validadeLaudo, ";
        vlc_sql += "dt_verificacaoLaudo, dc_tipoIsencao, lg_renainf, nr_imagem, nr_codPais, lg_uso, nr_codClassificacaoTamanho,";
        vlc_sql += "nr_tempoAferido, nr_tempoLimite, nr_status, dt_envio,dt_recebe, nr_codInconsistencia) ";
	vlc_sql += "values ('000010102488724190', '', 0, 0, 0,0,0,0,'', 0, 6942, '0001', 50, 60, 53, '20161120', '111339', '0001', 1, 0, 74550, 0,1111, 'FZH3578', 1, '4444', '20171231', '20161124', '1', 0, 1, 1, 0, 1, 0,0,1,'1900-01-01','1900-01-01',99) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        
        //08
        vlc_sql = "insert into autoInfracao (dc_nr_multa, dc_placa, nr_codCategoria, nr_codMarca,";
        vlc_sql += "nr_codTipo, nr_codCor, nr_codEspecie, nr_codMunicipio, dc_uf, nr_anoModelo, nr_codLocal,";
        vlc_sql += "dc_serieEquipamento, nr_velocidadePermitida, nr_velocidadeAferida, nr_velocidadeConsiderada, ";
        vlc_sql += "dt_infracao, dc_hr_infracao, dc_codAgente, nr_faixa, lg_entrefaixa, nr_codEnquadramento, nr_PorteVeiculo,";
        vlc_sql += "nr_tamanhoVeiculo, dc_placaOCR, nr_codTipoFiscalizacao, dc_numeroLaudo, dt_validadeLaudo, ";
        vlc_sql += "dt_verificacaoLaudo, dc_tipoIsencao, lg_renainf, nr_imagem, nr_codPais, lg_uso, nr_codClassificacaoTamanho,";
        vlc_sql += "nr_tempoAferido, nr_tempoLimite, nr_status, dt_envio,dt_recebe, nr_codInconsistencia) ";
	vlc_sql += "values ('000010102488725980', '', 0, 0, 0,0,0,0,'', 0, 6942, '0001', 50, 60, 53, '20161120', '111638', '0001', 1, 0, 74550, 0,1111, 'EPZ8570', 1, '4444', '20171231', '20161124', '1', 0, 1, 1, 0, 1, 0,0,1,'1900-01-01','1900-01-01',99) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();

        //09
        vlc_sql = "insert into autoInfracao (dc_nr_multa, dc_placa, nr_codCategoria, nr_codMarca,";
        vlc_sql += "nr_codTipo, nr_codCor, nr_codEspecie, nr_codMunicipio, dc_uf, nr_anoModelo, nr_codLocal,";
        vlc_sql += "dc_serieEquipamento, nr_velocidadePermitida, nr_velocidadeAferida, nr_velocidadeConsiderada, ";
        vlc_sql += "dt_infracao, dc_hr_infracao, dc_codAgente, nr_faixa, lg_entrefaixa, nr_codEnquadramento, nr_PorteVeiculo,";
        vlc_sql += "nr_tamanhoVeiculo, dc_placaOCR, nr_codTipoFiscalizacao, dc_numeroLaudo, dt_validadeLaudo, ";
        vlc_sql += "dt_verificacaoLaudo, dc_tipoIsencao, lg_renainf, nr_imagem, nr_codPais, lg_uso, nr_codClassificacaoTamanho,";
        vlc_sql += "nr_tempoAferido, nr_tempoLimite, nr_status, dt_envio,dt_recebe, nr_codInconsistencia) ";
	vlc_sql += "values ('000010102488726590', '', 0, 0, 0,0,0,0,'', 0, 6942, '0001', 50, 62, 55, '20161120', '111739', '0001', 1, 0, 74550, 0,1111, '', 1, '4444', '20171231', '20161124', '1', 0, 1, 1, 0, 1, 0,0,1,'1900-01-01','1900-01-01', 99) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();

        //10
        vlc_sql = "insert into autoInfracao (dc_nr_multa, dc_placa, nr_codCategoria, nr_codMarca,";
        vlc_sql += "nr_codTipo, nr_codCor, nr_codEspecie, nr_codMunicipio, dc_uf, nr_anoModelo, nr_codLocal,";
        vlc_sql += "dc_serieEquipamento, nr_velocidadePermitida, nr_velocidadeAferida, nr_velocidadeConsiderada, ";
        vlc_sql += "dt_infracao, dc_hr_infracao, dc_codAgente, nr_faixa, lg_entrefaixa, nr_codEnquadramento, nr_PorteVeiculo,";
        vlc_sql += "nr_tamanhoVeiculo, dc_placaOCR, nr_codTipoFiscalizacao, dc_numeroLaudo, dt_validadeLaudo, ";
        vlc_sql += "dt_verificacaoLaudo, dc_tipoIsencao, lg_renainf, nr_imagem, nr_codPais, lg_uso, nr_codClassificacaoTamanho,";
        vlc_sql += "nr_tempoAferido, nr_tempoLimite, nr_status, dt_envio,dt_recebe, nr_codInconsistencia) ";
	vlc_sql += "values ('000010102488728920', '', 0, 0, 0,0,0,0,'', 0, 6942, '0001', 50, 64, 57, '20161120', '112132', '0001', 1, 0, 74550, 0,1111, 'CRP0505', 1, '4444', '20171231', '20161124', '1', 0, 1, 1, 0, 1, 0,0,1,'1900-01-01','1900-01-01',99) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        

        // inserindo na tabela autoInfracaoImagem...
        //01
        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488718260', 0,'000010102488718260_00.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();

        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488718260', 1,'000010102488718260_01.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        
        
        //02
        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488718500', 0,'000010102488718500_00.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();

        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488718500', 1,'000010102488718500_01.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();

        //03
        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488718950', 0,'000010102488718950_00.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        
        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488718950', 1,'000010102488718950_01.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        //04
        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488719990', 0,'000010102488719990_00.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        
        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488719990', 1,'000010102488719990_01.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        //05
        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488720040', 0,'000010102488720040_00.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();

        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488720040', 1,'000010102488720040_01.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        //06
        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488721150', 0,'000010102488721150_00.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();

        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488721150', 1,'000010102488721150_01.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        //07
        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488724190', 0,'000010102488724190_00.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();

        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488724190', 1,'000010102488724190_01.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        //08
        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488725980', 0,'000010102488725980_00.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();

        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488725980', 1,'000010102488725980_01.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        //09
        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488726590', 0,'000010102488726590_00.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();

        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488726590', 1,'000010102488726590_01.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
       //10
        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488728920', 0,'000010102488728920_00.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        
        vlc_sql = "insert into autoInfracaoImagem (dc_nr_multa, nr_codTipoImagem, dc_nomeImagem, nr_tempoImagem) ";
	vlc_sql += "values ('000010102488728920', 1,'000010102488728920_01.JPG',0) ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registros inseridos com sucesso!!!", ""));
        mensagemJS("Registros inseridos com sucesso!!!");
        //inserindo registros na tabela autoInfracaoImagem... 
        
    }

    public void mensagemJS(String plc_mensagem){
        RequestContext.getCurrentInstance().execute("mensagemAlerta('" + plc_mensagem + "');");
        
    }
    
}
