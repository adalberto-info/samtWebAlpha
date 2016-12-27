/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 21/11/2016
 * 
 */
package br.com.engebras.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="autoInfracao")
public class AutoInfracao implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    @Id
    @Column(name="dc_nr_multa", nullable=false, length=18)
    private String dc_nr_multa; 
    @Column(name="dc_placa", nullable=false, length=7)
    private String dc_placa; 
    @Column(name="nr_codCategoria", nullable=false, length=2)
    private Integer nr_codCategoria; 
    @Column(name="nr_codMarca", nullable=false, length=6)
    private Integer nr_codMarca; 
    @Column(name="nr_codTipo", nullable=false, length=2)
    private Integer nr_codTipo; 
    @Column(name="nr_codCor", nullable=false, length=3)
    private Integer nr_codCor; 
    @Column(name="nr_codEspecie", nullable=false, length=3)
    private Integer nr_codEspecie; 
    @Column(name="nr_codMunicipio", nullable=false, length=5)
    private Integer nr_codMunicipio; 
    @Column(name="dc_uf", nullable=false, length=2)
    private String dc_uf; 
    @Column(name="nr_anoModelo", nullable=false, length=4)
    private Integer nr_anoModelo; 
    @Column(name="nr_codLocal", nullable=false, length=5)
    private Integer nr_codLocal; 
    @Column(name="dc_serieEquipamento", nullable=false, length=15)
    private String dc_serieEquipamento; 
    @Column(name="nr_velocidadePermitida", nullable=false, length=3)
    private Integer nr_velocidadePermitida; 
    @Column(name="nr_velocidadeAferida", nullable=false, length=3)
    private Integer nr_velocidadeAferida; 
    @Column(name="nr_velocidadeConsiderada", nullable=false, length=3)
    private Integer nr_velocidadeConsiderada; 
    @Column(name="dt_infracao", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dt_infracao; 
    @Column(name="dc_horaInfracao", nullable=false, length=8)
    private String dc_horaInfracao; 
    @Column(name="dc_codAgente", nullable=false, length=12)
    private String dc_codAgente; 
    @Column(name="nr_faixa", nullable=false, length=1)
    private Integer nr_faixa; 
    @Column(name="lg_entreFaixa", nullable=false, length=1)
    private Integer lg_entreFaixa; 
    @Column(name="nr_codEnquadramento", nullable=false, length=5)
    private Integer nr_codEnquadramento; 
    @Column(name="nr_porteVeiculo", nullable=false, length=2)
    private Integer nr_porteVeiculo; 
    @Column(name="nr_tamanhoVeiculo", nullable=false, precision=5, scale=1)
    private Float nr_tamanhoVeiculo; 
    @Column(name="dc_placaOCR", nullable=false, length=7)
    private String dc_placaOCR; 
    @Column(name="nr_codTipoFiscalizacao", nullable=false, length=2)
    private Integer nr_codTipoFiscalizacao; 
    @Column(name="dc_numeroLaudo", nullable=false, length=16)
    private String dc_numeroLaudo; 
    @Column(name="dt_validadeLaudo", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dt_validadeLaudo; 
    @Column(name="dt_verificacaoLaudo", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dt_verificacaoLaudo; 
    @Column(name="dc_tipoIsencao", nullable=false, length=3)
    private String dc_tipoIsencao; 
    @Column(name="lg_renainf", nullable=false, length=1)
    private Integer lg_renainf; 
    @Column(name="nr_imagem", nullable=false, length=10)
    private Integer nr_imagem; 
    @Column(name="nr_codPais", nullable=false, length=2)
    private Integer nr_codPais; 
    @Column(name="lg_uso", nullable=false, length=1)
    private Integer lg_uso; 
    @Column(name="nr_codClassificacaoTamanho", nullable=false, length=1)
    private Integer nr_codClassificacaoTamanho; 
    @Column(name="nr_tempoAferido", nullable=false, precision=6, scale=2)
    private Float nr_tempoAferido; 
    @Column(name="nr_tempoLimite", nullable=false, length=5)
    private Integer nr_tempoLimite; 
    @Column(name="nr_status", nullable=false, length=3)
    private Integer nr_status; 
    @Column(name="dt_envio", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dt_envio; 
    @Column(name="dt_recebe", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dt_recebe; 
    @Column(name="nr_usuarioDigitacao", nullable=false, length=6)
    private Integer nr_usuarioDigitacao; 
    @Column(name="nr_usuarioConferencia", nullable=false, length=6)
    private Integer nr_usuarioConferencia; 
    @Column(name="dt_digitacao", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dt_digitacao; 
    @Column(name="dt_conferencia", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dt_conferencia; 

    public String getDc_nr_multa() {
        return dc_nr_multa;
    }

    public void setDc_nr_multa(String dc_nr_multa) {
        this.dc_nr_multa = dc_nr_multa;
    }

    public String getDc_placa() {
        return dc_placa;
    }

    public void setDc_placa(String dc_placa) {
        this.dc_placa = dc_placa;
    }

    public Integer getNr_codCategoria() {
        return nr_codCategoria;
    }

    public void setNr_codCategoria(Integer nr_codCategoria) {
        this.nr_codCategoria = nr_codCategoria;
    }

    public Integer getNr_codMarca() {
        return nr_codMarca;
    }

    public void setNr_codMarca(Integer nr_codMarca) {
        this.nr_codMarca = nr_codMarca;
    }

    public Integer getNr_codTipo() {
        return nr_codTipo;
    }

    public void setNr_codTipo(Integer nr_codTipo) {
        this.nr_codTipo = nr_codTipo;
    }

    public Integer getNr_codCor() {
        return nr_codCor;
    }

    public void setNr_codCor(Integer nr_codCor) {
        this.nr_codCor = nr_codCor;
    }

    public Integer getNr_codEspecie() {
        return nr_codEspecie;
    }

    public void setNr_codEspecie(Integer nr_codEspecie) {
        this.nr_codEspecie = nr_codEspecie;
    }

    public Integer getNr_codMunicipio() {
        return nr_codMunicipio;
    }

    public void setNr_codMunicipio(Integer nr_codMunicipio) {
        this.nr_codMunicipio = nr_codMunicipio;
    }

    public String getDc_uf() {
        return dc_uf;
    }

    public void setDc_uf(String dc_uf) {
        this.dc_uf = dc_uf;
    }

    public Integer getNr_anoModelo() {
        return nr_anoModelo;
    }

    public void setNr_anoModelo(Integer nr_anoModelo) {
        this.nr_anoModelo = nr_anoModelo;
    }

    public Integer getNr_codLocal() {
        return nr_codLocal;
    }

    public void setNr_codLocal(Integer nr_codLocal) {
        this.nr_codLocal = nr_codLocal;
    }

    public String getDc_serieEquipamento() {
        return dc_serieEquipamento;
    }

    public void setDc_serieEquipamento(String dc_serieEquipamento) {
        this.dc_serieEquipamento = dc_serieEquipamento;
    }

    public Integer getNr_velocidadePermitida() {
        return nr_velocidadePermitida;
    }

    public void setNr_velocidadePermitida(Integer nr_velocidadePermitida) {
        this.nr_velocidadePermitida = nr_velocidadePermitida;
    }

    public Integer getNr_velocidadeAferida() {
        return nr_velocidadeAferida;
    }

    public void setNr_velocidadeAferida(Integer nr_velocidadeAferida) {
        this.nr_velocidadeAferida = nr_velocidadeAferida;
    }

    public Integer getNr_velocidadeConsiderada() {
        return nr_velocidadeConsiderada;
    }

    public void setNr_velocidadeConsiderada(Integer nr_velocidadeConsiderada) {
        this.nr_velocidadeConsiderada = nr_velocidadeConsiderada;
    }

    public Date getDt_infracao() {
        return dt_infracao;
    }

    public void setDt_infracao(Date dt_infracao) {
        this.dt_infracao = dt_infracao;
    }

    public String getDc_horaInfracao() {
        return dc_horaInfracao;
    }

    public void setDc_horaInfracao(String dc_horaInfracao) {
        this.dc_horaInfracao = dc_horaInfracao;
    }

    public String getDc_codAgente() {
        return dc_codAgente;
    }

    public void setDc_codAgente(String dc_codAgente) {
        this.dc_codAgente = dc_codAgente;
    }

    public Integer getNr_faixa() {
        return nr_faixa;
    }

    public void setNr_faixa(Integer nr_faixa) {
        this.nr_faixa = nr_faixa;
    }

    public Integer getLg_entreFaixa() {
        return lg_entreFaixa;
    }

    public void setLg_entreFaixa(Integer lg_entreFaixa) {
        this.lg_entreFaixa = lg_entreFaixa;
    }

    public Integer getNr_codEnquadramento() {
        return nr_codEnquadramento;
    }

    public void setNr_codEnquadramento(Integer nr_codEnquadramento) {
        this.nr_codEnquadramento = nr_codEnquadramento;
    }

    public Integer getNr_porteVeiculo() {
        return nr_porteVeiculo;
    }

    public void setNr_porteVeiculo(Integer nr_porteVeiculo) {
        this.nr_porteVeiculo = nr_porteVeiculo;
    }

    public Float getNr_tamanhoVeiculo() {
        return nr_tamanhoVeiculo;
    }

    public void setNr_tamanhoVeiculo(Float nr_tamanhoVeiculo) {
        this.nr_tamanhoVeiculo = nr_tamanhoVeiculo;
    }

    public String getDc_placaOCR() {
        return dc_placaOCR;
    }

    public void setDc_placaOCR(String dc_placaOCR) {
        this.dc_placaOCR = dc_placaOCR;
    }

    public Integer getNr_codTipoFiscalizacao() {
        return nr_codTipoFiscalizacao;
    }

    public void setNr_codTipoFiscalizacao(Integer nr_codTipoFiscalizacao) {
        this.nr_codTipoFiscalizacao = nr_codTipoFiscalizacao;
    }

    public String getDc_numeroLaudo() {
        return dc_numeroLaudo;
    }

    public void setDc_numeroLaudo(String dc_numeroLaudo) {
        this.dc_numeroLaudo = dc_numeroLaudo;
    }

    public Date getDt_validadeLaudo() {
        return dt_validadeLaudo;
    }

    public void setDt_validadeLaudo(Date dt_validadeLaudo) {
        this.dt_validadeLaudo = dt_validadeLaudo;
    }

    public Date getDt_verificacaoLaudo() {
        return dt_verificacaoLaudo;
    }

    public void setDt_verificacaoLaudo(Date dt_verificacaoLaudo) {
        this.dt_verificacaoLaudo = dt_verificacaoLaudo;
    }

    public String getDc_tipoIsencao() {
        return dc_tipoIsencao;
    }

    public void setDc_tipoIsencao(String dc_tipoIsencao) {
        this.dc_tipoIsencao = dc_tipoIsencao;
    }

    public Integer getLg_renainf() {
        return lg_renainf;
    }

    public void setLg_renainf(Integer lg_renainf) {
        this.lg_renainf = lg_renainf;
    }

    public Integer getNr_imagem() {
        return nr_imagem;
    }

    public void setNr_imagem(Integer nr_imagem) {
        this.nr_imagem = nr_imagem;
    }

    public Integer getNr_codPais() {
        return nr_codPais;
    }

    public void setNr_codPais(Integer nr_codPais) {
        this.nr_codPais = nr_codPais;
    }

    public Integer getLg_uso() {
        return lg_uso;
    }

    public void setLg_uso(Integer lg_uso) {
        this.lg_uso = lg_uso;
    }

    public Integer getNr_codClassificacaoTamanho() {
        return nr_codClassificacaoTamanho;
    }

    public void setNr_codClassificacaoTamanho(Integer nr_codClassificacaoTamanho) {
        this.nr_codClassificacaoTamanho = nr_codClassificacaoTamanho;
    }

    public Float getNr_tempoAferido() {
        return nr_tempoAferido;
    }

    public void setNr_tempoAferido(Float nr_tempoAferido) {
        this.nr_tempoAferido = nr_tempoAferido;
    }

    public Integer getNr_tempoLimite() {
        return nr_tempoLimite;
    }

    public void setNr_tempoLimite(Integer nr_tempoLimite) {
        this.nr_tempoLimite = nr_tempoLimite;
    }

    public Integer getNr_status() {
        return nr_status;
    }

    public void setNr_status(Integer nr_status) {
        this.nr_status = nr_status;
    }

    public Date getDt_envio() {
        return dt_envio;
    }

    public void setDt_envio(Date dt_envio) {
        this.dt_envio = dt_envio;
    }

    public Date getDt_recebe() {
        return dt_recebe;
    }

    public void setDt_recebe(Date dt_recebe) {
        this.dt_recebe = dt_recebe;
    }

    public Integer getNr_usuarioDigitacao() {
        return nr_usuarioDigitacao;
    }

    public void setNr_usuarioDigitacao(Integer nr_usuarioDigitacao) {
        this.nr_usuarioDigitacao = nr_usuarioDigitacao;
    }

    public Integer getNr_usuarioConferencia() {
        return nr_usuarioConferencia;
    }

    public void setNr_usuarioConferencia(Integer nr_usuarioConferencia) {
        this.nr_usuarioConferencia = nr_usuarioConferencia;
    }

    public Date getDt_digitacao() {
        return dt_digitacao;
    }

    public void setDt_digitacao(Date dt_digitacao) {
        this.dt_digitacao = dt_digitacao;
    }

    public Date getDt_conferencia() {
        return dt_conferencia;
    }

    public void setDt_conferencia(Date dt_conferencia) {
        this.dt_conferencia = dt_conferencia;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.dc_nr_multa);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AutoInfracao other = (AutoInfracao) obj;
        if (!Objects.equals(this.dc_nr_multa, other.dc_nr_multa)) {
            return false;
        }
        return true;
    }
    
    
}
