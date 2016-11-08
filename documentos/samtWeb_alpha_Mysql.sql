CREATE TABLE area (
  dc_codArea CHAR(4) NOT NULL DEFAULT '',
  dc_area VARCHAR(30) NOT NULL DEFAULT '',
  PRIMARY KEY(cd_area)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE autoInfracao (
  dc_nr_multa CHAR(18) NOT NULL DEFAULT '',
  dc_placa CHAR(7) NOT NULL DEFAULT '',
  nr_codCategoria NUMERIC(2) NOT NULL DEFAULT 0,
  nr_codMarca NUMERIC(6) NOT NULL DEFAULT 0,
  nr_codTipo NUMERIC(2) NOT NULL DEFAULT 0,
  nr_codCor NUMERIC(3) NOT NULL DEFAULT 0,
  nr_codEspecie NUMERIC(3)  NOT NULL DEFAULT 0,
  nr_codMunicipio NUMERIC(5)  NOT NULL DEFAULT 0,
  dc_uf CHAR(2)  NOT NULL DEFAULT '',
  nr_anoModelo NUMERIC(4) NOT NULL DEFAULT 0,
  nr_codLocal NUMERIC(5) NOT NULL DEFAULT 0,
  dc_serieEquipamento CHAR(15) NOT NULL DEFAULT '',
  nr_velocidadePermitida NUMERIC(3) NOT NULL DEFAULT 0,
  nr_velocidadeAferida NUMERIC(3) NOT NULL DEFAULT 0,
  nr_velocidadeConsiderada NUMERIC(3) NOT NULL DEFAULT 0,
  dt_infracao DATE NOT NULL DEFAULT '0000-00-00',
  hr_infracao CHAR(08) NOT NULL DEFAULT '',
  dc_codAgente CHAR(12) NOT NULL DEFAULT '',
  nr_faixa NUMERIC(1) NOT NULL DEFAULT 0,
  lg_entrefaixa NUMERIC(1) NOT NULL DEFAULT 0,
  nr_codEnquadramento NUMERIC(5) NOT NULL DEFAULT 0,
  nr_PorteVeiculo NUMERIC(2) NOT NULL DEFAULT 0,
  nr_tamanhoVeiculo NUMERIC(5,1) NOT NULL DEFAULT 0,
  dc_placaOCR CHAR(7) NOT NULL DEFAULT '',
  nr_codTipoFiscalizacao NUMERIC(2) NOT NULL DEFAULT 0,
  dc_numeroLaudo VARCHAR(16) NOT NULL DEFAULT '',
  dt_validadeLaudo DATE NOT NULL DEFAULT '0000-00-00',
  dt_verificacaoLaudo DATE NOT NULL DEFAULT '0000-00-00',
  dc_tipoIsencao CHAR(3) NOT NULL DEFAULT '',
  lg_renainf NUMERIC(1) NOT NULL DEFAULT 0,
  nr_imagem NUMERIC(10) NOT NULL DEFAULT 0,
  nr_codPais NUMERIC(2) NOT NULL DEFAULT 0,
  lg_uso NUMERIC(1) NOT NULL DEFAULT 0,
  nr_codClassificacaoTamanho NUMERIC(1) NOT NULL DEFAULT 0,
  nr_tempoAferido NUMERIC(6,2) NOT NULL DEFAULT 0,
  nr_tempoLimite NUMERIC(5) NOT NULL DEFAULT 0,
  nr_status NUMERIC(03) NOT NULL DEFAULT 0,
  dt_envio DATE NOT NULL DEFAULT '0000-00-00',
  dt_recebe DATE NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY(dc_nr_multa)
) 

CREATE INDEX ix_dc_placa on autoInfracao (dc_placa) ;
CREATE INDEX ix_dt_infracao on autoInfracao (dt_infracao) ; 
CREATE INDEX ix_hr_infracao on autoInfracao (hr_infracao) ; 
CREATE INDEX ix_nr_codEnquadramento on autoInfracao (nr_codEnquadramento) ;
CREATE INDEX ix_nr_status on autoInfracao (nr_status) ;


CREATE TABLE autoInfracaoImagem (
  dc_nr_multa CHAR(18) NOT NULL DEFAULT '',
  nr_codTipoImagem NUMERIC(2) NOT NULL DEFAULT 0,
  dc_nomeImagem VARCHAR(20) NOT NULL DEFAULT '',
  nr_tempoImagem NUMERIC(6,2) NOT NULL DEFAULT 0,
  PRIMARY KEY(dc_nr_multa, nr_codtipoImagem)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_nr_codTipoImagem on autoInfracaoImagem (nr_codTipoImagem)


CREATE TABLE autoInfracao_AAAAMM (
  dc_nr_multa CHAR(18) NOT NULL DEFAULT '',
  dc_placa CHAR(7) NOT NULL DEFAULT '',
  nr_codCategoria NUMERIC(2) NOT NULL DEFAULT 0,
  nr_codMarca NUMERIC(6) NOT NULL DEFAULT 0,
  nr_codTipo NUMERIC(2) NOT NULL DEFAULT 0,
  nr_codCor NUMERIC(3) NOT NULL DEFAULT 0,
  nr_codEspecie NUMERIC(3) NOT NULL DEFAULT 0,
  nr_codMunicipio NUMERIC(5) NOT NULL DEFAULT 0, 
  dc_uf CHAR(2) NOT NULL DEFAULT '',
  nr_anoModelo NUMERIC(4) NOT NULL DEFAULT 0,
  nr_codLocal NUMERIC(5) NOT NULL DEFAULT 0,
  dc_serieEquipamento CHAR(15) NOT NULL DEFAULT '',
  nr_velocidadePermitida NUMERIC(3) NOT NULL DEFAULT 0,
  nr_velocidadeAferida NUMERIC(3) NOT NULL DEFAULT 0,
  nr_velocidadeConsiderada NUMERIC(3) NOT NULL DEFAULT 0,
  dt_infracao DATE NOT NULL DEFAULT '0000-00-00',
  hr_infracao CHAR(08) NOT NULL DEFAULT '',
  dc_codAgente CHAR(12) NOT NULL DEFAULT '',
  nr_faixa NUMERIC(1) NOT NULL DEFAULT 0,
  lg_entrefaixa NUMERIC(1) NOT NULL DEFAULT 0,
  nr_codEnquadramento NUMERIC(5) NOT NULL DEFAULT 0,
  nr_PorteVeiculo NUMERIC(2) NOT NULL DEFAULT 0,
  nr_tamanhoVeiculo NUMERIC(5,1) NOT NULL DEFAULT 0,
  dc_placaOCR CHAR(7) NOT NULL DEFAULT '',
  nr_codTipoFiscalizacao NUMERIC(2) NOT NULL DEFAULT 0,
  dc_numeroLaudo VARCHAR(16) NOT NULL DEFAULT '',
  dt_validadeLaudo DATE NOT NULL DEFAULT '0000-00-00',
  dt_verificacaoLaudo DATE NOT NULL DEFAULT '0000-00-00',
  dc_tipoIsencao CHAR(3) NOT NULL DEFAULT '',
  lg_renainf NUMERIC(1) NOT NULL DEFAULT 0,
  nr_imagem NUMERIC(10) NOT NULL DEFAULT 0,
  nr_codPais NUMERIC(2) NOT NULL DEFAULT 0,
  lg_uso NUMERIC(1) NOT NULL DEFAULT 0,
  nr_codClassificacaoTamanho NUMERIC(1) NOT NULL DEFAULT 0,
  nr_tempoAferido NUMERIC(6,2) NOT NULL DEFAULT 0,
  nr_tempoLimite NUMERIC(5) NOT NULL DEFAULT 0,
  nr_status NUMERIC(03) NOT NULL DEFAULT 0,
  dt_envio DATE NOT NULL DEFAULT '0000-00-00',
  dt_recebe DATE NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY(dc_nr_multa)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_dc_placa on autoInfracao_AAAAMM (dc_placa) ;
CREATE INDEX ix_dt_infracao on autoInfracao_AAAAMM (dt_infracao) ; 
CREATE INDEX ix_hr_infracao on autoInfracao_AAAAMM (hr_infracao) ; 
CREATE INDEX ix_nr_codEnquadramento on autoInfracao_AAAAMM (nr_codEnquadramento) ;
CREATE INDEX ix_nr_status on autoInfracao_AAAAMM (nr_status) ;


CREATE TABLE cadLote (
  dc_codGrupoAutuador CHAR(2) NOT NULL DEFAULT '' ,
  nr_lote NUMERIC(6) NOT NULL DEFAULT 0,
  nr_revisao NUMERIC(1) NOT NULL DEFAULT 0,
  dt_geracaoLote DATE NOT NULL DEFAULT '0000-00-00',
  nr_qtdRegistros NUMERIC(4) NOT NULL DEFAULT 0,
  nr_usuarioGeracao NUMERIC(6) NOT NULL DEFAULT 0,
  dc_status CHAR(1) NOT NULL DEFAULT '',
  dt_envio DATE NOT NULL DEFAULT '0000-00-00',
  dt_recebe DATE NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY(dc_codGrupoAutuador, nr_lote)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE cadLoteRep (
  dc_codGrupoAutuador CHAR(2) NOT NULL DEFAULT '',
  nr_lote NUMERIC(6) NOT NULL DEFAULT 0,
  nr_revisao NUMERIC(1) NOT NULL DEFAULT 0,
  dt_geracaoLote DATE NOT NULL DEFAULT '0000-00-00',
  nr_qtdRegistros NUMERIC(4) NOT NULL DEFAULT 0,
  dc_status CHAR(1) NOT NULL DEFAULT '',
  dt_envio DATE NOT NULL DEFAULT '0000-00-00',
  dt_recebe DATE NOT NULL DEFAULT '0000-00-00',
  dt_envioRevisao DATE NOT NULL DEFAULT '0000-00-00',
  dt_recebeRevisao DATE NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY(dc_codGrupoAutuador, nr_lote, nr_revisao)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE contador (
  nr_codigo int(10) AUTO_INCREMENT,
  dc_contador CHAR(20) NOT NULL DEFAULT '',
  nr_inicial NUMERIC(10) NOT NULL DEFAULT 0,
  nr_final NUMERIC(10) NOT NULL DEFAULT 0,
  nr_ultimo NUMERIC(10) NOT NULL DEFAULT 0,
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE contrato (
  nr_crd NUMERIC(6) NOT NULL DEFAULT 0,
  nr_codFilial NUMERIC(5) NOT NULL DEFAULT 0,
  dc_contrato VARCHAR(30) NOT NULL DEFAULT 0,
  dt_inicioContrato DATE NOT NULL DEFAULT '0000-00-00',
  dt_fimContrato DATE NOT NULL DEFAULT '0000-00-00',
  dc_uf CHAR(2) NOT NULL DEFAULT '',
  nr_codMunicipio NUMERIC(5) NOT NULL DEFAULT 0,
  dc_responsavel VARCHAR(25) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_crd)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE enquadramento (
  nr_codigo NUMERIC(5) NOT NULL DEFAULT 0,
  nr_artigo NUMERIC(3) NOT NULL DEFAULT 0,
  dc_paragrafo CHAR(3) NOT NULL DEFAULT '',
  dc_item CHAR(2) NOT NULL DEFAULT '',
  dc_descricao VARCHAR(100) NOT NULL DEFAULT '',
  nr_pontos NUMERIC(2) NOT NULL DEFAULT 0,
  dc_classificacao CHAR(10) NOT NULL DEFAULT '',
  nr_qtdUfir NUMERIC(6,2) NOT NULL DEFAULT 0,
  nr_tipoInfrator NUMERIC(2) NOT NULL DEFAULT 0,
  dc_unidadeMedida CHAR(10) NOT NULL DEFAULT '',
  dc_medicao CHAR(1) NOT NULL DEFAULT '',
  dc_veiculoAutuado CHAR(1) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE equipamento (
  nr_codigo INT(10) AUTO_INCREMENT,
  dc_serieEquipamento CHAR(15) NOT NULL DEFAULT '',
  dc_fabricante CHAR(20) NOT NULL DEFAULT '',
  dc_modelo CHAR(15) NOT NULL DEFAULT '',
  dc_empresa CHAR(20) NOT NULL DEFAULT '',
  dc_status CHAR(1) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE equipamentoCliente (
  nr_codigo int(10) AUTO_INCREMENT,
  dc_serieEquipamento CHAR(15) NOT NULL DEFAULT '',
  dc_equipamentoCliente CHAR(6) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_dc_serieEquipamento on equipamentoCliente (dc_serieEquipamento) ; 
CREATE INDEX ix_dc_equipamentoCliente on equipamentoCliente (dc_equipamentoCliente) ;

CREATE TABLE escalaEstatico (
  nr_codigo INT(10) AUTO_INCREMENT,
  nr_codLocalCli NUMERIC(5) NOT NULL DEFAULT 0,
  nr_faixa NUMERIC(1) NOT NULL DEFAULT 0,
  dt_alocacaoIni DATE NOT NULL DEFAULT 0,
  dt_alocacaoFim DATE NOT NULL DEFAULT '0000-00-00',
  dc_funcionamentoIni CHAR(08) NOT NULL DEFAULT '',
  dc_funcionamentoFim CHAR(08) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_nr_codLocalCli on escalaEstatico (nr_codLocalCli) ;

CREATE TABLE feriado (
  nr_codigo INT(10) AUTO_INCREMENT,
  dt_feriado DATE NOT NULL DEFAULT '0000-00-00',
  dc_feriado VARCHAR(30) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_dt_feriado on feriado (dt_feriado);

CREATE TABLE filial (
  nr_codigo INT(5) AUTO_INCREMENT,
  dc_filial VARCHAR(30) NOT NULL DEFAULT '',
  dc_responsavel VARCHAR(30) NOT NULL DEFAULT '',
  dc_endereco VARCHAR(30) NOT NULL DEFAULT '',
  dc_numero CHAR(15) NOT NULL DEFAULT '',
  dc_bairro VARCHAR(25) NOT NULL DEFAULT '',
  dc_cidade VARCHAR(30) NOT NULL DEFAULT '',
  dc_uf CHAR(2) NOT NULL DEFAULT '',
  dc_telefone VARCHAR(25) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE grupoAutuador (
  nr_codigo INT(5) AUTO_INCREMENT,	
  dc_codigo CHAR(2) NOT NULL DEFAULT '',
  nr_codEnquadramento NUMERIC(5) NOT NULL DEFAULT 0,
  dc_descricao VARCHAR(40) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_nr_codEnquadramento on grupoAutuador (nr_codEnquadramento);

CREATE TABLE grupoLocal (
  nr_codigo INT(5) AUTO_INCREMENT,
  dc_descricao VARCHAR(40) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE grupoLocalItem (
  nr_codigo INT(10) AUTO_INCREMENT,
  nr_codGrupoLocal NUMERIC(5) NOT NULL DEFAULT 0,
  nr_codLocalInfracao NUMERIC(5) NOT NULL DEFAULT 0,
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_nr_codGrupoLocal on grupoLocalItem (nr_codGrupoLocal) ;
CREATE INDEX ix_nr_codLocalInfracao on grupoLocalItem (nr_codLocalInfracao) ;

CREATE TABLE importaTXT001 (
  dt_infracao CHAR(10) NOT NULL DEFAULT '',
  dc_horaInfracao CHAR(11) NOT NULL DEFAULT '',
  dc_classificacao CHAR(6) NOT NULL DEFAULT '',
  dc_local VARCHAR(80) NOT NULL DEFAULT '',
  dc_serie_equipamento CHAR(10) NOT NULL DEFAULT '',
  dt_afericao CHAR(10) NOT NULL DEFAULT '',
  nr_faixa NUMERIC(1) NOT NULL DEFAULT 0,
  nr_velocidadelRegulamentada CHAR(7) NOT NULL DEFAULT 0,
  nr_velocidadeMedida CHAR(7) NOT NULL DEFAULT 0,
  nr_velocidadeConsiderada CHAR(7) NOT NULL DEFAULT 0,
  nr_imagem NUMERIC(7) NOT NULL DEFAULT 0,
  nr_enquadramento NUMERIC(5) NOT NULL DEFAULT 0,
  dc_enquadramento VARCHAR(80) NOT NULL DEFAULT 0,
  nr_codLocal CHAR(4) NOT NULL DEFAULT 0,
  dc_empresa CHAR(2) NOT NULL DEFAULT '',
  dc_nome_foto VARCHAR(40) NOT NULL DEFAULT ''
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE importaTXT003 (
  dt_infracao CHAR(10) NOT NULL DEFAULT '',
  dc_horaInfracao CHAR(11) NOT NULL DEFAULT '',
  dc_local VARCHAR(80) NOT NULL DEFAULT '',
  dc_serieEquipamento CHAR(10) NOT NULL DEFAULT '',
  nr_faixa NUMERIC(1) NOT NULL DEFAULT 0,
  dc_diaSemana CHAR(4) NOT NULL DEFAULT '',
  nr_imagem NUMERIC(7) NOT NULL DEFAULT 0,
  nr_enquadramento NUMERIC(5) NOT NULL DEFAULT 0,
  dc_enquadramentoDescricao VARCHAR(80) NOT NULL DEFAULT '',
  nr_codLocal CHAR(4) NOT NULL DEFAULT 0,
  dc_empresa CHAR(2) NOT NULL DEFAULT '',
  dc_placaOCR CHAR(7) NOT NULL DEFAULT '',
  dc_nomeFoto VARCHAR(40) NOT NULL DEFAULT ''
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE importaTXT004 (
  dt_infracao CHAR(10) NOT NULL DEFAULT '',
  dc_horaInfracao CHAR(11) NOT NULL DEFAULT '',
  dc_local CHAR(80) NOT NULL DEFAULT '',
  dc_serieEquipamento CHAR(10) NOT NULL DEFAULT '',
  dc_horarioRestricao VARCHAR(20) NOT NULL DEFAULT '',
  nr_faixa NUMERIC(1) NOT NULL DEFAULT 0,
  dc_dentroEstado CHAR(3) NOT NULL DEFAULT '',
  nr_imagem NUMERIC(7) NOT NULL DEFAULT 0,
  nr_enquadramento NUMERIC(5) NOT NULL DEFAULT 0,
  dc_enquadramento VARCHAR(80) NOT NULL DEFAULT '',
  nr_codLocal CHAR(4) NOT NULL DEFAULT 0,
  dc_dia_sem CHAR(4) NOT NULL DEFAULT '',
  dc_empresa CHAR(2) NOT NULL DEFAULT '',
  dc_nomeFoto VARCHAR(40) NOT NULL DEFAULT ''
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE importaTXT005 (
  dt_infracao CHAR(10) NOT NULL DEFAULT '',
  dc_horaInfracao CHAR(11) NOT NULL DEFAULT '',
  dc_local VARCHAR(80) NOT NULL DEFAULT '',
  dc_serieEquipamento CHAR(10) NOT NULL DEFAULT '',
  dc_horarioProibido VARCHAR(20) NOT NULL DEFAULT '',
  nr_faixa NUMERIC(1) NOT NULL DEFAULT 0,
  dc_dentroEstado CHAR(3) NOT NULL DEFAULT '',
  nr_imagem NUMERIC(7) NOT NULL DEFAULT 0,
  nr_enquadramento NUMERIC(5) NOT NULL DEFAULT 0,
  dc_enquadramento VARCHAR(80) NOT NULL DEFAULT '',
  nr_codLocal CHAR(4) NOT NULL DEFAULT 0,
  dc_classificacao CHAR(6) NOT NULL DEFAULT '',
  dc_diaSemana CHAR(4) NOT NULL DEFAULT '',
  dc_empresa CHAR(2) NOT NULL DEFAULT '',
  dc_nomeFoto VARCHAR(40) NOT NULL DEFAULT '',
  dc_equipamento_m CHAR(10) NOT NULL DEFAULT '',
  dc_equipamento_i CHAR(10) NOT NULL DEFAULT '',
  dt_afericacao CHAR(10) NOT NULL DEFAULT '',
  nr_velRegulamentada CHAR(7) NOT NULL DEFAULT 0,
  nr_velMedida CHAR(7) NOT NULL DEFAULT 0,
  nr_velConsiderada CHAR(7) NOT NULL DEFAULT 0
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE importaTXT006 (
  dt_infracao CHAR(10) NOT NULL DEFAULT '',
  dc_horaInfracao CHAR(11) NOT NULL DEFAULT '',
  dc_local VARCHAR(80) NOT NULL DEFAULT '',
  dc_serieEquipameto CHAR(10) NOT NULL DEFAULT '',
  nr_faixa NUMERIC(1) NOT NULL DEFAULT 0,
  dc_dentroEstado CHAR(3) NOT NULL DEFAULT '',
  nr_imagem NUMERIC(7) NOT NULL DEFAULT 0,
  nr_enquadramento NUMERIC(5) NOT NULL DEFAULT 0,
  dc_enquadramento VARCHAR(80) NOT NULL DEFAULT '',
  nr_codLocal CHAR(4) NOT NULL DEFAULT 0,
  dc_empresa CHAR(2) NOT NULL DEFAULT '',
  dc_placaOCR CHAR(7) NOT NULL DEFAULT '',
  dc_nomeFoto VARCHAR(40) NOT NULL DEFAULT '',
  dc_diaSemana CHAR(4) NOT NULL DEFAULT ''
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE importaTXT009 (
  dt_infracao CHAR(10) NOT NULL DEFAULT '',
  dc_horaInfracao CHAR(11) NOT NULL DEFAULT '',
  dc_classificacao CHAR(6) NOT NULL DEFAULT '',
  dc_local VARCHAR(80) NOT NULL DEFAULT '',
  dc_serieEquipamento CHAR(10) NOT NULL DEFAULT '',
  nr_tempoVermelho CHAR(5) NOT NULL DEFAULT 0,
  nr_tempoRetardo CHAR(5) NOT NULL DEFAULT 0,
  nr_faixa NUMERIC(1) NOT NULL DEFAULT 0,
  nr_imagem NUMERIC(7) NOT NULL DEFAULT 0,
  dc_tipoInfracao VARCHAR(80) NOT NULL DEFAULT '',
  dc_dentroEstado CHAR(3) NOT NULL DEFAULT '',
  dt_afericao CHAR(10) NOT NULL DEFAULT '',
  nr_enquadramento NUMERIC(5) NOT NULL DEFAULT 0,
  dc_enquadramento VARCHAR(80) NOT NULL DEFAULT '',
  nr_codLocal CHAR(4) NOT NULL DEFAULT 0,
  dc_empresa CHAR(2) NOT NULL DEFAULT '',
  dc_horarioProibido VARCHAR(20) NOT NULL DEFAULT '',
  dc_nomeFoto VARCHAR(40) NOT NULL DEFAULT '',
  nr_velRegulamentada CHAR(7) NOT NULL DEFAULT 0,
  nr_velMedida CHAR(7) NOT NULL DEFAULT 0,
  dc_diaSemana CHAR(4) NOT NULL DEFAULT 0,
  nr_velConsiderada CHAR(7) NOT NULL DEFAULT 0,
  dc_equipamento_m CHAR(10) NOT NULL DEFAULT '',
  dc_equipamento_I CHAR(10) NOT NULL DEFAULT ''
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE importaTXT011 (
  dt_infracao CHAR(10) NOT NULL DEFAULT '',
  dc_horaInfracao CHAR(11) NOT NULL DEFAULT '',
  dc_classificacao CHAR(6) NOT NULL DEFAULT '',
  dc_local VARCHAR(80) NOT NULL DEFAULT '',
  dc_serieEquipamento CHAR(10) NOT NULL DEFAULT '',
  nr_imagem NUMERIC(7) NOT NULL DEFAULT 0,
  nr_enquadramento NUMERIC(5) NOT NULL DEFAULT 0,
  dc_enquadramento VARCHAR(80) NOT NULL DEFAULT '',
  nr_codLocal CHAR(4) NOT NULL DEFAULT '',
  dc_empresa CHAR(2) NOT NULL DEFAULT '',
  dc_nomeFoto VARCHAR(40) NOT NULL DEFAULT '',
  nr_tempoVermelho CHAR(5) NOT NULL DEFAULT '',
  nr_tempoRetardo CHAR(5) NOT NULL DEFAULT '',
  nr_faixa NUMERIC(1) NOT NULL DEFAULT 0,
  dc_tipoInfracao VARCHAR(80) NOT NULL DEFAULT '',
  dc_dentroEstado CHAR(3) NOT NULL DEFAULT '',
  dt_afericao CHAR(10) NOT NULL DEFAULT '',
  dc_horarioProibido VARCHAR(20) NOT NULL DEFAULT '',
  nr_velRegulamentada CHAR(7) NOT NULL DEFAULT '',
  dc_diaSemana CHAR(4) NOT NULL DEFAULT '',
  nr_velMedida CHAR(7) NOT NULL DEFAULT'',
  nr_velConsiderada CHAR(7) NOT NULL DEFAULT ''
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE isencaoEnquadramento (
  nr_codigo INT(10) NOT NULL AUTO_INCREMENT,
  nr_codEnquadramento NUMERIC(5) NOT NULL DEFAULT 0,
  dt_diaIsencao DATE NOT NULL DEFAULT '0000-00-00',
  dc_horaIni CHAR(08) NOT NULL DEFAULT '',
  dc_horaFim CHAR(08) NOT NULL DEFAULT '',
  dc_motivo TEXT NOT NULL ,
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_nr_codEnquadramento on isencaoEnquadramento (nr_codEnquadramento)  ;
CREATE INDEX ix_dt_diaIsencao on isencaoEnquadramento (dt_diaIsencao) ;

CREATE TABLE isencaoRodizio (
  nr_codigo INT(10)  AUTO_INCREMENT,
  dt_diaIsencao DATE NOT NULL DEFAULT '0000-00-00',
  dc_horaIni CHAR(08) NOT NULL DEFAULT '',
  dc_horaFim CHAR(08) NOT NULL DEFAULT '',
  lg_automovel NUMERIC(1) NOT NULL DEFAULT 0,
  lg_caminhao NUMERIC(1) NOT NULL DEFAULT 0,
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_dt_diaIsencao on isencaoRodizio (dt_diaIsencao);

CREATE TABLE isencaoTaxi (
  nr_codigo INT(10) AUTO_INCREMENT,
  nr_codLocal NUMERIC(5) NOT NULL DEFAULT 0,
  nr_diaSemana NUMERIC(1) NOT NULL DEFAULT 0,
  dc_horaIni CHAR(08) NOT NULL DEFAULT '',
  dc_horaFim  CHAR(08) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_nr_codLocal on isencaoTaxi (nr_codLocal) ;
CREATE INDEX ix_nr_diaSemana on isencaoTaxi (nr_diaSemana) ;

CREATE TABLE isencaoZMRC (
  nr_codigo INT(10) AUTO_INCREMENT,
  cd_area CHAR(4) NOT NULL DEFAULT '',
  dc_placa CHAR(7) NOT NULL DEFAULT '',
  dc_horaIni CHAR(08) NOT NULL DEFAULT '',
  dc_horaFim CHAR(08) NOT NULL DEFAULT '',
  dt_inicio DATE NOT NULL DEFAULT '0000-00-00',
  dt_fim DATE NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_cd_area on isencaoZMRC (cd_area) ;
CREATE INDEX ix_dc_placa on isencaoZMRC (dc_placa) ; 

CREATE TABLE laudoMetrologico (
  nr_codigo INT(10) AUTO_INCREMENT,
  dc_serieEquipamento CHAR(15) NOT NULL DEFAULT '',
  dc_matriculaTecnico VARCHAR(10) NOT NULL DEFAULT '',
  dc_nomeTecnico VARCHAR(30) NOT NULL DEFAULT '',
  dc_orgao VARCHAR(40) NOT NULL DEFAULT '',
  dc_numeroLaudo VARCHAR(16) NOT NULL DEFAULT '',
  dc_numeroLacre VARCHAR(17) NOT NULL DEFAULT '',
  dc_numeroLacre2 VARCHAR(17) NOT NULL DEFAULT '',
  dt_verificacao DATE NOT NULL DEFAULT '0000-00-00',
  dt_validade DATE NOT NULL DEFAULT '0000-00-00',
  nr_codMotivoLaudo NUMERIC(5) NOT NULL DEFAULT 0, 
  dc_observacao TEXT NOT NULL ,
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_dc_serieEquipamento on laudoMetrologico (dc_serieEquipamento);

CREATE TABLE laudoNaoMetrologico (
  nr_codigo INT(10) AUTO_INCREMENT,
  dc_serieEquipamento CHAR(15) NOT NULL DEFAULT '',
  dc_matriculaTecnico VARCHAR(10) NOT NULL DEFAULT '',
  dc_nomeTecnico VARCHAR(30) NOT NULL DEFAULT '',
  dc_orgao VARCHAR(40) NOT NULL DEFAULT '',
  dc_numeroLaudo VARCHAR(16) NOT NULL DEFAULT '',
  dc_numeroLacre VARCHAR(17) NOT NULL DEFAULT '',
  dc_numeroLacre2 VARCHAR(17) NOT NULL DEFAULT '',
  dt_verificacao DATE NOT NULL DEFAULT '0000-00-00',
  dt_validade DATE NOT NULL DEFAULT '0000-00-00',
  nr_codMotivoLaudo NUMERIC(5) NOT NULL DEFAULT 0,
  dc_observacao TEXT NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_dc_serieEquipamento on laudoNaoMetrologico (dc_serieEquipamento);

CREATE TABLE localArea (
  nr_codigo INT(10) AUTO_INCREMENT,
  nr_codLocal NUMERIC(5) NOT NULL DEFAULT 0,
  cd_area CHAR(4) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_nr_codLocal on localArea (nr_codLocal);

CREATE TABLE localCliente (
  nr_codigo INT(10) AUTO_INCREMENT,
  nr_codLocal NUMERIC(5) NOT NULL DEFAULT 0,
  nr_codLocalCliente NUMERIC(10) NOT NULL DEFAULT 0,
  nr_tipoFiscalizacao NUMERIC(2) NOT NULL DEFAULT 0,
  dt_inclusao DATE NOT NULL DEFAULT '0000-00-00',
  dt_inicioVigencia DATE NOT NULL DEFAULT '0000-00-00',
  nr_porteVeiculo NUMERIC(1) NOT NULL DEFAULT 0,
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_nr_codLocal on localCliente (nr_codLocal) ;
CREATE INDEX ix_nr_codLocalCliente on localCliente (nr_codLocalCliente) ;

CREATE TABLE localInfracao (
  nr_codigo INT(5) AUTO_INCREMENT,
  dc_local VARCHAR(80) NOT NULL DEFAULT '',
  dc_bairro VARCHAR(30) NOT NULL DEFAULT '',
  nr_codMunicipio NUMERIC(5) NOT NULL DEFAULT 0,
  dc_uf CHAR(2) NOT NULL DEFAULT '',
  lg_ativo NUMERIC(1) NOT NULL DEFAULT 0,
  nr_qtdFaixas NUMERIC(1) NOT NULL DEFAULT 0,
  nr_faixa1 NUMERIC(1) NOT NULL DEFAULT 0,
  nr_faixa2 NUMERIC(1) NOT NULL DEFAULT 0,
  dc_ladoFaixa1 CHAR(1) NOT NULL DEFAULT '',
  dc_ladoFaixa2 CHAR(1) NOT NULL DEFAULT '',
  dc_sentido CHAR(1) NOT NULL DEFAULT '',
  lg_velocidadeDifPorte NUMERIC(1) NOT NULL DEFAULT 0,
  dc_latitude VARCHAR(17) NOT NULL DEFAULT '',
  dc_longitude VARCHAR(17) NOT NULL DEFAULT '',
  dt_inicio DATE NOT NULL DEFAULT '0000-00-00',
  dt_ultimaAtualizacao DATE NOT NULL DEFAULT '0000-00-00',
  nr_codStatus NUMERIC(2) NOT NULL DEFAULT 0,
  nr_codTipoFixacao NUMERIC(5) NOT NULL DEFAULT 0,
  nr_codTipoEquipamento NUMERIC(2) NOT NULL DEFAULT 0,
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE localVelocidade (
  nr_codigo INT(10) AUTO_INCREMENT,
  nr_codLocal NUMERIC(5) NOT NULL DEFAULT 0,
  nr_velocidadePermitida NUMERIC(3) NOT NULL DEFAULT 0,
  nr_velocidadeConsiderada NUMERIC(6,1) NOT NULL DEFAULT 0,
  nr_velocidadeTolerada NUMERIC(6,1) NOT NULL DEFAULT 0,
  nr_velocidadeGrave NUMERIC(3) NOT NULL DEFAULT 0,
  nr_velocidadeGravissima NUMERIC(3) NOT NULL DEFAULT 0,
  nr_codPorteVeiculo NUMERIC(2) NOT NULL DEFAULT 0,
  dt_inicio DATE NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_nr_codLocal on localVelocidade (nr_codLocal) ;

CREATE TABLE local_equipamento (
  nr_codigo INT(10) AUTO_INCREMENT,	
  nr_codLocal NUMERIC(5) NOT NULL DEFAULT 0,
  dc_serieEquipamento CHAR(15) NOT NULL DEFAULT '',
  dt_inicio DATE NOT NULL DEFAULT '0000-00-00',
  dt_fim DATE NOT NULL DEFAULT '0000-00-00',
  dc_hr_inicio CHAR(08) NOT NULL DEFAULT '',
  dc_hr_fim CHAR(08) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE INDEX ix_nr_codLocal on local_equipamento (nr_codLocal); 
CREATE INDEX ix_dc_serieEquipamento on local_equipamento (dc_serieEquipamento); 


CREATE TABLE local_grupoLocal (
  nr_codGrupoLocal NUMERIC(5) NOT NULL DEFAULT 0,
  nr_codLocal NUMERIC(5) NOT NULL DEFAULT 0,
  PRIMARY KEY(nr_codGrupoLocal,nr_codLocal)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE local_situacaoLocal (
  nr_codigo NUMERIC(10) AUTO_INCREMENT,
  nr_codLocal NUMERIC(5) NOT NULL DEFAULT 0,
  dc_codSituacao CHAR(3) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_nr_codLocal on local_situacaoLocal (nr_codLocal) ;
CREATE INDEX ix_dc_codSituacao on local_situacaoLocal(dc_codSituacao) ;

CREATE TABLE local_tipoFiscalizacao (
  nr_codLocal NUMERIC(5) NOT NULL DEFAULT 0,
  nr_codTipoFiscalizacao NUMERIC(2) NOT NULL DEFAULT 0,
  PRIMARY KEY(nr_codLocal,nr_codTipoFiscalizacao)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE logOperacao (
  nr_codigo INT(10) AUTO_INCREMENT,
  nr_codContrato NUMERIC(5) NOT NULL DEFAULT 0,
  dc_tipoOperacao CHAR(5) NOT NULL DEFAULT '',
  dt_operacao DATE NOT NULL DEFAULT '0000-00-00',
  dc_horaOperacao CHAR(08) NOT NULL DEFAULT '',
  dc_mensagem TEXT NOT NULL ,
  nr_codUsuario NUMERIC(06) NOT NULL DEFAULT 0,
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_dc_tipoOperacao on logOperacao (dc_tipoOperacao) ;
CREATE INDEX ix_dt_operacao on logOperacao (dt_operacao) ;

CREATE TABLE logUsuario (
  nr_codigo INT(10) AUTO_INCREMENT,
  nr_codUsuario NUMERIC(6) NOT NULL DEFAULT 0,
  dt_login DATE NOT NULL DEFAULT '0000-00-00',
  dc_horaLogin CHAR(08) NOT NULL DEFAULT '',
  dt_logout DATE NOT NULL DEFAULT '0000-00-00',
  dc_horaLogout CHAR(08) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_nr_codUsuario on logUsuario (nr_codUsuario);

CREATE TABLE motivoInconsistenciaImagem (
  nr_codigo NUMERIC(2) NOT NULL DEFAULT 0,
  dc_inconsistencia VARCHAR(100) NOT NULL DEFAULT '',
  lg_56732 NUMERIC(1) NOT NULL DEFAULT 0,
  lg_56810 NUMERIC(1) NOT NULL DEFAULT 0,
  lg_56900 NUMERIC(1) NOT NULL DEFAULT 0,
  lg_57030 NUMERIC(1) NOT NULL DEFAULT 0,
  lg_57461 NUMERIC(1) NOT NULL DEFAULT 0,
  lg_57462 NUMERIC(1) NOT NULL DEFAULT 0,
  lg_57463 NUMERIC(1) NOT NULL DEFAULT 0,
  lg_60411 NUMERIC(1) NOT NULL DEFAULT 0,
  lg_60412 NUMERIC(1) NOT NULL DEFAULT 0,
  lg_60503 NUMERIC(1) NOT NULL DEFAULT 0,
  lg_74550 NUMERIC(1) NOT NULL DEFAULT 0,
  lg_74630 NUMERIC(1) NOT NULL DEFAULT 0,
  lg_74710 NUMERIC(1) NOT NULL DEFAULT 0,
  lg_57461M NUMERIC(1) NOT NULL DEFAULT 0,
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE motivoLaudo (
  nr_codigo INT(10) AUTO_INCREMENT,
  dc_motivoLaudo VARCHAR(30) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE movimentoLote (
  dc_codGrupoAutuador CHAR(2) NOT NULL DEFAULT '',
  nr_lote NUMERIC(6) NOT NULL DEFAULT 0,
  nr_registroLote NUMERIC(4) NOT NULL DEFAULT 0,
  nr_tipoRegistro NUMERIC(1)  NOT NULL DEFAULT 0,
  dt_geracaoLote DATE NOT NULL DEFAULT '0000-00-00',
  nr_registroEquipamento NUMERIC(7) NOT NULL DEFAULT 0,
  dc_placa CHAR(7) NOT NULL DEFAULT '',
  nr_codPais NUMERIC(2) NOT NULL DEFAULT 0,
  nr_codMarca NUMERIC(3) NOT NULL DEFAULT 0,
  nr_codEspecie NUMERIC(3) NOT NULL DEFAULT 0,
  nr_codEnquadramento NUMERIC(5) NOT NULL DEFAULT 0,
  nr_codLocal NUMERIC(4) NOT NULL DEFAULT 0,
  dc_local VARCHAR(80) NOT NULL DEFAULT '',
  nr_codEquipamento NUMERIC(4) NOT NULL DEFAULT 0,
  dt_registro DATE NOT NULL DEFAULT '0000-00-00',
  dc_horaRegistro CHAR(6) NOT NULL DEFAULT '',
  nr_velMedida NUMERIC(3) NOT NULL DEFAULT 0,
  nr_velConsiderada NUMERIC(3) NOT NULL DEFAULT 0,
  nr_velRegulamentada NUMERIC(3) NOT NULL DEFAULT 0,
  nr_faixa NUMERIC(1) NOT NULL DEFAULT 0,
  nr_codOperador NUMERIC(6) NOT NULL DEFAULT 0,
  dt_analise DATE NOT NULL DEFAULT '0000-00-00',
  nr_registroMontante NUMERIC(4) NOT NULL DEFAULT 0,
  dc_consistenciaCAI CHAR(1) NOT NULL DEFAULT '',
  nr_codInconsistenciaCAI NUMERIC(2) NOT NULL DEFAULT 0,
  nr_imagemNotificacao NUMERIC(1) NOT NULL DEFAULT 0,
  dc_consistenciaCAV CHAR(1) NOT NULL DEFAULT '',
  nr_codInconsistenciaCAV NUMERIC(5) NOT NULL DEFAULT 0,
  nr_codAuditor NUMERIC(6) NOT NULL DEFAULT 0,
  dc_origemMulta CHAR(2) NOT NULL DEFAULT '',
  dt_auditoria DATE NOT NULL DEFAULT '0000-00-00',
  lg_erro NUMERIC(1) NOT NULL DEFAULT 0,
  lg_corrigiuObliteracao NUMERIC(1) NOT NULL DEFAULT 0,
  dc_placaOrigem CHAR(7) NOT NULL DEFAULT '',
  nr_codMarcaOrigem NUMERIC(3) NOT NULL DEFAULT 0,
  dc_observacao TEXT NOT NULL,
  dc_status CHAR(1) NOT NULL DEFAULT '',
  PRIMARY KEY(dc_codGrupoAutuador, nr_lote, nr_registroLote)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_dc_placa on movimentoLote (dc_placa) ;
CREATE INDEX ix_dt_geracaoLote on movimentoLote (dt_geracaoLote) ;
CREATE INDEX ix_nr_codEnquadramento on movimentoLote (nr_codEnquadramento) ;

CREATE TABLE movimentoLoteRep (
  dc_codGrupoAutuador CHAR(2) NOT NULL DEFAULT '',
  nr_lote NUMERIC(6) NOT NULL DEFAULT 0,
  nr_revisao NUMERIC(1) NOT NULL DEFAULT 0,
  nr_registroLote NUMERIC(4) NOT NULL DEFAULT 0,
  nr_tipoRegistro NUMERIC(1)  NOT NULL DEFAULT 0,
  dt_geracaoLote DATE NOT NULL DEFAULT '0000-00-00',
  nr_registroEquipamento NUMERIC(7) NOT NULL DEFAULT 0,
  dc_placa CHAR(7) NOT NULL DEFAULT '',
  nr_codPais NUMERIC(2) NOT NULL DEFAULT 0,
  nr_codMarca NUMERIC(3) NOT NULL DEFAULT 0,
  nr_codEspecie NUMERIC(3) NOT NULL DEFAULT 0,
  nr_codEnquadramento NUMERIC(5) NOT NULL DEFAULT 0,
  nr_codLocal NUMERIC(4) NOT NULL DEFAULT 0,
  dc_local VARCHAR(80) NOT NULL DEFAULT '',
  nr_codEquipamento NUMERIC(4) NOT NULL DEFAULT 0,
  dt_registro DATE NOT NULL DEFAULT '0000-00-00',
  dc_horaRegistro CHAR(6) NOT NULL DEFAULT '',
  nr_velMedida NUMERIC(3) NOT NULL DEFAULT 0,
  nr_velConsiderada NUMERIC(3) NOT NULL DEFAULT 0,
  nr_velRegulamentada NUMERIC(3) NOT NULL DEFAULT 0,
  nr_faixa NUMERIC(1) NOT NULL DEFAULT 0,
  nr_codOperador NUMERIC(6) NOT NULL DEFAULT 0,
  dt_analise DATE NOT NULL DEFAULT '0000-00-00',
  nr_registroMontante NUMERIC(4) NOT NULL DEFAULT 0,
  dc_consistenciaCAI CHAR(1) NOT NULL DEFAULT '',
  nr_codInconsistenciaCAI NUMERIC(2) NOT NULL DEFAULT 0,
  nr_imagemNotificacao NUMERIC(1) NOT NULL DEFAULT 0,
  dc_consistenciaCAV CHAR(1) NOT NULL DEFAULT '',
  nr_codInconsistenciaCAV NUMERIC(5) NOT NULL DEFAULT 0,
  nr_codAuditor NUMERIC(6) NOT NULL DEFAULT 0,
  dc_origemMulta CHAR(2) NOT NULL DEFAULT '',
  dt_auditoria DATE NOT NULL DEFAULT '0000-00-00',
  lg_erro NUMERIC(1) NOT NULL DEFAULT 0,
  lg_corrigiuObliteracao NUMERIC(1) NOT NULL DEFAULT 0,
  dc_placaOrigem CHAR(7) NOT NULL DEFAULT '',
  nr_codMarcaOrigem NUMERIC(3) NOT NULL DEFAULT 0,
  dc_observacao TEXT NOT NULL ,
  dc_status CHAR(1) NOT NULL DEFAULT '',
  nr_codRevisor NUMERIC(6) NOT NULL DEFAULT 0,
  dt_revisao DATE NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY(dc_codGrupoAutuador, nr_lote, nr_revisao, nr_registroLote)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_dc_placa on movimentoLoteRep (dc_placa) ;
CREATE INDEX ix_dt_geracaoLote on movimentoLoteRep (dt_geracaoLote) ;
CREATE INDEX ix_nr_codEnquadramento on movimentoLoteRep (nr_codEnquadramento) ;

CREATE TABLE movimentoLote_AAAAMM (
  dc_codGrupoAutuador CHAR(2) NOT NULL DEFAULT '',
  nr_lote NUMERIC(6) NOT NULL DEFAULT 0,
  nr_revisao NUMERIC(1) NOT NULL DEFAULT 0,
  nr_registroLote NUMERIC(4) NOT NULL DEFAULT 0,
  nr_tipoRegistro NUMERIC(1) NOT NULL DEFAULT 0,
  dt_geracaoLote DATE NOT NULL DEFAULT '0000-00-00',
  nr_registroEquipamento NUMERIC(7) NOT NULL DEFAULT 0,
  dc_placa CHAR(7) NOT NULL DEFAULT '',
  nr_codPais NUMERIC(2) NOT NULL DEFAULT 0,
  nr_codMarca NUMERIC(3) NOT NULL DEFAULT 0,
  nr_codEspecie NUMERIC(3) NOT NULL DEFAULT 0,
  nr_codEnquadramento NUMERIC(5) NOT NULL DEFAULT 0,
  nr_codLocal NUMERIC(4) NOT NULL DEFAULT 0,
  dc_local VARCHAR(80) NOT NULL DEFAULT '',
  nr_codEquipamento NUMERIC(4) NOT NULL DEFAULT 0,
  dt_registro DATE NOT NULL DEFAULT '0000-00-00',
  dc_horaRegistro CHAR(6) NOT NULL DEFAULT '',
  nr_velMedida NUMERIC(3) NOT NULL DEFAULT 0,
  nr_velConsiderada NUMERIC(3) NOT NULL DEFAULT 0,
  nr_velRegulamentada NUMERIC(3) NOT NULL DEFAULT 0,
  nr_faixa NUMERIC(1) NOT NULL DEFAULT 0,
  nr_codOperador NUMERIC(6) NOT NULL DEFAULT 0,
  dt_analise DATE NOT NULL DEFAULT '0000-00-00',
  nr_registroMontante NUMERIC(4) NOT NULL DEFAULT 0,
  dc_consistenciaCAI CHAR(1) NOT NULL DEFAULT '',
  nr_codInconsistenciaCAI NUMERIC(2) NOT NULL DEFAULT 0,
  nr_imagemNotificacao NUMERIC(1) NOT NULL DEFAULT 0,
  dc_consistenciaCAV CHAR(1) NOT NULL DEFAULT '',
  nr_codInconsistenciaCAV NUMERIC(5) NOT NULL DEFAULT 0,
  nr_codAuditor NUMERIC(6) NOT NULL DEFAULT 0,
  dc_origemMulta CHAR(2) NOT NULL DEFAULT '',
  dt_auditoria DATE NOT NULL DEFAULT '0000-00-00',
  lg_erro NUMERIC(1) NOT NULL DEFAULT 0,
  lg_corrigiuObliteracao NUMERIC(1) NOT NULL DEFAULT 0,
  dc_placaOrigem CHAR(7) NOT NULL DEFAULT '',
  nr_codMarcaOrigem NUMERIC(3) NOT NULL DEFAULT 0,
  dc_observacao TEXT NOT NULL ,
  dc_status CHAR(1) NOT NULL DEFAULT '',
  PRIMARY KEY(dc_codGrupoAutuador, nr_lote, nr_registroLote)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_dc_placa on movimentoLote_AAAAMM (dc_placa) ;
CREATE INDEX ix_dt_geracaoLote on movimentoLote_AAAAMM (dt_geracaoLote) ;
CREATE INDEX ix_nr_codEnquadramento on movimentoLote_AAAAMM (nr_codEnquadramento) ;

CREATE TABLE municipio (
  nr_codigo NUMERIC(5) NOT NULL DEFAULT 0,
  dc_uf CHAR(2) NOT NULL DEFAULT '',
  dc_municipio VARCHAR(40) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_dc_uf on municipio (dc_uf);

CREATE TABLE nivelUsuario (
  nr_codigo NUMERIC(2) NOT NULL DEFAULT 0,
  dc_nivelUsuario VARCHAR(30) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE pais (
  nr_codigo NUMERIC(2) NOT NULL DEFAULT 0,
  dc_pais VARCHAR(50) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE parametroSistema (
  nr_codigo INT(10) AUTO_INCREMENT,
  dc_parametro VARCHAR(30) NOT NULL DEFAULT '',
  dc_tipo CHAR(1) NOT NULL DEFAULT '',
  dc_valor VARCHAR(250) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE porteVeiculo (
  nr_codigo NUMERIC(2) NOT NULL DEFAULT 0,
  dc_descricao VARCHAR(30) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE processoDescricao (
  nr_codigo NUMERIC(10) NOT NULL DEFAULT 0,
  dc_processo VARCHAR(40) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE processoSequencia (
  nr_codigo INT(10) AUTO_INCREMENT,
  nr_sequencia NUMERIC(5) NOT NULL DEFAULT 0,
  nr_status NUMERIC(3) NOT NULL DEFAULT 0,
  nr_codProcessoPre NUMERIC(10) NOT NULL DEFAULT 0,
  nr_codProcessoPos NUMERIC(10) NOT NULL DEFAULT 0,
  nr_codTabela NUMERIC(10) NOT NULL DEFAULT 0,
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE processoTabela (
  nr_codigo INT(10) AUTO_INCREMENT,
  dc_tabela VARCHAR(40) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE restricaoConversaoProibida (
  nr_codigo INT(10) AUTO_INCREMENT,
  nr_codLocal NUMERIC(5) NOT NULL DEFAULT 0,
  nr_diaSemana NUMERIC(1) NOT NULL DEFAULT 0,
  dt_inicio DATE NOT NULL DEFAULT '0000-00-00',
  dc_horaIni CHAR(08) NOT NULL DEFAULT '',
  dt_fim DATE NOT NULL DEFAULT '0000-00-00',
  dc_horaFim CHAR(08) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_nr_codLocal on restricaoConversaoProibida (nr_codLocal);

CREATE TABLE restricaoFxExclusiva (
  nr_codigo INT(10) AUTO_INCREMENT,
  nr_codLocal NUMERIC(5) NOT NULL DEFAULT 0,
  nr_diaSemana NUMERIC(1) NOT NULL DEFAULT 0,
  dc_horaIni CHAR(08) NOT NULL DEFAULT '',
  dc_horaFim CHAR(08) NOT NULL DEFAULT '',
  dt_inicio DATE NOT NULL DEFAULT '0000-00-00',
  dt_fim DATE NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_nr_codLocal on restricaoFxExclusiva (nr_codLocal);

CREATE TABLE restricaoRodizio (
  nr_codigo INT(10) AUTO_INCREMENT,
  nr_codLocal NUMERIC(5) NOT NULL DEFAULT 0,
  nr_diaSemana NUMERIC(1) NOT NULL DEFAULT 0,
  dc_finalPlaca CHAR(1) NOT NULL DEFAULT '',
  dc_horaIni CHAR(08) NOT NULL DEFAULT '',
  dc_horaFim CHAR(08) NOT NULL DEFAULT '',
  dt_inicio DATE NOT NULL DEFAULT '0000-00-00',
  dt_fim DATE NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_nr_codLocal on restricaoRodizio (nr_codLocal);

CREATE TABLE restricaoZMRC (
  nr_codigo INT(10) AUTO_INCREMENT,
  dc_codArea CHAR(4) NOT NULL DEFAULT '',
  nr_diaSemana NUMERIC(1) NOT NULL DEFAULT 0,
  dc_horaIni CHAR(08) NOT NULL DEFAULT '',
  dc_horaFim CHAR(08) NOT NULL DEFAULT '',
  dt_inicio DATE NOT NULL DEFAULT '0000-00-00',
  dt_fim DATE NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_dc_CodArea on restricaoZMRC (dc_codArea);

CREATE TABLE situacaoLocal (
  dc_codigo CHAR(3) NOT NULL DEFAULT '',
  dc_descricao VARCHAR(35) NOT NULL DEFAULT '',
  nr_nivel NUMERIC(2) NOT NULL DEFAULT 0,
  lg_problema NUMERIC(1) NOT NULL DEFAULT 0,
  PRIMARY KEY(dc_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE statusLocal (
  nr_codigo NUMERIC(2) NOT NULL DEFAULT 0,
  dc_descricao VARCHAR(25) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE statusProcesso (
  nr_codigo NUMERIC(3) NOT NULL DEFAULT 0,
  dc_statusProcesso VARCHAR(30) NOT NULL DEFAULT '',
  nr_codTabela NUMERIC(10) NOT NULL DEFAULT 0,
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE tabelaProcesso (
  nr_codigo INT(10) AUTO_INCREMENT,
  dc_tabela VARCHAR(30) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE tipoFiscalizacao (
  nr_codigo NUMERIC(2) NOT NULL DEFAULT 0,
  dc_descricao VARCHAR(50) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE tipoFiscalizacao_enquadramento (
  nr_codTipoFiscalizacao NUMERIC(2) NOT NULL DEFAULT 0,
  nr_codEnquadramento NUMERIC(5) NOT NULL DEFAULT 0,
  PRIMARY KEY(nr_codTipoFiscalizacao, nr_codEnquadramento)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE TipoFixacaoRadar (
  nr_codigo NUMERIC(5) NOT NULL DEFAULT 0,
  dc_descricao VARCHAR(25) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE tipoImagem (
  nr_codigo NUMERIC(2) NOT NULL DEFAULT 0,
  dc_tipoImagem VARCHAR(30) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE tipoLogOperacao (
  nr_codigo NUMERIC(5) NOT NULL DEFAULT 0,
  dc_tipoLogOperacao VARCHAR(30) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE UF (
  dc_uf CHAR(2) NOT NULL DEFAULT '',
  dc_descricao VARCHAR(30) NOT NULL DEFAULT '',
  PRIMARY KEY(dc_uf)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ultimoLote (
  tp_lote CHAR(2) NOT NULL DEFAULT '',
  dc_codGrupoAutuador CHAR(2) NOT NULL DEFAULT '',
  nr_lote NUMERIC(06) NOT NULL DEFAULT 0,
  nr_registroLote NUMERIC(4) NOT NULL DEFAULT 0,
  nr_revisao NUMERIC(1) NOT NULL DEFAULT 0,
  nr_codLocal NUMERIC(5) NOT NULL DEFAULT 0,
  dc_nr_multa CHAR(18) NOT NULL DEFAULT '',
  nr_codMotivoInconsis NUMERIC(2) NOT NULL DEFAULT 0,
  dc_status CHAR(2) NOT NULL DEFAULT '',
  dt_infracao DATE NOT NULL DEFAULT '0000-00-00',
  dc_horaInfracao CHAR(08) NOT NULL DEFAULT '',
  dt_inclusao DATE NOT NULL DEFAULT '0000-00-00',
  dt_geracaoLote CHAR(08) NOT NULL DEFAULT '',
  PRIMARY KEY(tp_lote, dc_codGrupoAutuador, nr_lote, nr_registroLote)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE usuario (
  nr_codigo INT(6) AUTO_INCREMENT,
  dc_nomeUsuario VARCHAR(30) NOT NULL DEFAULT '',
  dc_login VARCHAR(25) NOT NULL DEFAULT '',
  dc_senha CHAR(10) NOT NULL DEFAULT '',
  nr_nivel NUMERIC(2) NOT NULL DEFAULT 0,
  dc_cargo VARCHAR(30) NOT NULL DEFAULT '',
  nr_codFilial NUMERIC(5) NOT NULL DEFAULT 0,
  lg_ativo NUMERIC(1) NOT NULL DEFAULT 0,
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX ix_dc_login on usuario (dc_login)

CREATE TABLE veiculo (
  dc_placa CHAR(7) NOT NULL DEFAULT '',
  nr_codCategoria NUMERIC(2) NOT NULL DEFAULT 0,
  nr_codMunicipio NUMERIC(5) NOT NULL DEFAULT 0,
  nr_codMarcaCet NUMERIC(3) NOT NULL DEFAULT 0,
  nr_codMarcaCetDv NUMERIC(1) NOT NULL DEFAULT 0,
  nr_codMarcaDenatran NUMERIC(6) NOT NULL DEFAULT 0,
  nr_codTipoCET NUMERIC(2) NOT NULL DEFAULT 0,
  nr_codTipoDenatran NUMERIC(2) NOT NULL DEFAULT 0,
  nr_codCor NUMERIC(3) NOT NULL DEFAULT 0,
  nr_anoModelo NUMERIC(4) NOT NULL DEFAULT 0,
  nr_codEspecie NUMERIC(3) NOT NULL DEFAULT 0,
  PRIMARY KEY(dc_placa)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE veiculoCategoria (
  nr_codigo NUMERIC(2) NOT NULL DEFAULT 0,
  dc_categoria VARCHAR(30) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE veiculoCor (
  nr_codigo NUMERIC(3) NOT NULL DEFAULT 0,
  dc_cor CHAR(15) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE veiculoEspecie (
  nr_codigo NUMERIC(1) NOT NULL DEFAULT 0,
  dc_especie CHAR(15) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE veiculoMarcaCET (
  nr_codigo NUMERIC(6) NOT NULL DEFAULT 0,
  dc_marca VARCHAR(35) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE veiculoMarcaDenatran (
  nr_codigo NUMERIC(6) NOT NULL DEFAULT 0,
  dc_marca VARCHAR(35) NOT NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE veiculoTipoCET (
  nr_codigo NUMERIC(2) NOT NULL DEFAULT 0,
  dc_descricao VARCHAR(30) NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE veiculoTipoDenatran (
  nr_codigo NUMERIC(2) NOT NULL DEFAULT 0,
  dc_tipo VARCHAR(30) NULL DEFAULT '',
  PRIMARY KEY(nr_codigo)
);



