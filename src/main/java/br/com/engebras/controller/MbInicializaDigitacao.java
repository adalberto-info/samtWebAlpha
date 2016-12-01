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

@ManagedBean(name = "mbInicializaDigitacao")
@SessionScoped
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
        vlc_sql = "delete autoInfracao ";
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
        
        //excluindo todos os registros da tabela autoInfracaoImagem... 
        vlc_sql = "delete from autoInfracaoImagem ";
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();
            
        
        //inserindo registros na tabela autoInfracao... 
        
        vlc_sql = "insert into autoInfracao (dc_nr_multa, dc_placa, nr_codCategoria, nr_codMarca,";
        vlc_sql += "nr_codTipo, nr_codCor, nr_codEspecie, nr_codMunicipio, dc_uf, nr_anoModelo, nr_codLocal,";
        vlc_sql += "dc_serieEquipamento, nr_velocidadePermitida, nr_velocidadeAferida, nr_velocidadeConsiderada, ";
        vlc_sql += "dt_infracao, dc_hr_infracao, dc_codAgente, nr_faixa, lg_entrefaixa, nr_codEnquadramento, nr_PorteVeiculo,";
        vlc_sql += "nr_tamanhoVeiculo, dc_placaOCR, nr_codTipoFiscalizacao, dc_numeroLaudo, dt_validadeLaudo, ";
        vlc_sql += "dt_verificacaoLaudo, dc_tipoIsencao, lg_renainf, nr_imagem, nr_codPais, lg_uso, nr_codClassificacaoTamanho,";
        vlc_sql += "nr_tempoAferido, nr_tempoLimite, nr_status, dt_envio,dt_recebe) ";
	vlc_sql += "values ('000001012488718260', '', 0, 0, 0,0,0,0,'', 0, 6942, '0001', 50, 61, 54, '20161120', '110346', '0001', 1, 0, 74550, 0,1111, 'EPZ8570', 1, '4444', '20171231', '20161124', '1', 0, 1, 1, 0, 1, 0,0,1,'0000-00-00','0000-00-00') ";
        
        query = session.createSQLQuery(vlc_sql);
        vln_resultado = query.executeUpdate();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro inserido com sucesso!!!", ""));

        //inserindo registros na tabela autoInfracaoImagem... 
        
    }
    
}
