CREATE TABLE area (
  dc_codArea CHAR(4) NOT NULL AUTO_INCREMENT,
  dc_area VARCHAR(30) NULL,
  PRIMARY KEY(dc_codArea)
);

CREATE TABLE autoInfracao (
  dc_nr_multa CHAR(18) NOT NULL AUTO_INCREMENT,
  dc_placa CHAR(7) NULL,
  nr_codCategoria NUMERIC(2) NULL,
  nr_codMarca NUMERIC(6) NULL,
  nr_codTipo NUMERIC(2) NULL,
  nr_codCor NUMERIC(3) NULL,
  nr_codEspecie NUMERIC(3) NULL,
  nr_codMunicipio NUMERIC(5) NULL,
  dc_uf CHAR(2) NULL,
  nr_anoModelo NUMERIC(4) NULL,
  nr_codLocal NUMERIC(5) NULL,
  dc_serieEquipamento CHAR(15) NULL,
  nr_velocidadePermitida NUMERIC(3) NULL,
  nr_velocidadeAferida NUMERIC(3) NULL,
  nr_velocidadeConsiderada NUMERIC(3) NULL,
  dt_infracao DATE NULL,
  hr_infracao CHAR(08) NULL,
  dc_codAgente CHAR(12) NULL,
  nr_faixa NUMERIC(1) NULL,
  lg_entrefaixa NUMERIC(1) NULL,
  nr_codEnquadramento NUMERIC(5) NULL,
  nr_PorteVeiculo NUMERIC(2) NULL,
  nr_tamanhoVeiculo NUMERIC(5,1) NULL,
  dc_placaOCR CHAR(7) NULL,
  nr_codTipoFiscalizacao NUMERIC(2) NULL,
  dc_numeroLaudo VARCHAR(16) NULL,
  dt_validadeLaudo DATE NULL,
  dt_verificacaoLaudo DATE NULL,
  dc_tipoIsencao CHAR(3) NULL,
  lg_renainf NUMERIC(1) NULL,
  nr_imagem NUMERIC(10) NULL,
  nr_codPais NUMERIC(2) NULL,
  lg_uso NUMERIC(1) NULL,
  nr_codClassificacaoTamanho NUMERIC(1) NULL,
  nr_tempoAferido NUMERIC(6,2) NULL,
  nr_tempoLimite NUMERIC(5) NULL,
  nr_status NUMERIC(03) NULL,
  dt_envio DATE NULL,
  dt_recebe DATE NULL,
  PRIMARY KEY(dc_nr_multa)
);

CREATE TABLE autoInfracaoImagem (
  dc_nr_multa CHAR(18) NOT NULL AUTO_INCREMENT,
  nr_codTipoImagem NUMERIC(2) NULL,
  dc_nomeImagem VARCHAR(20) NULL,
  nr_tempoImagem NUMERIC(6,2) NULL,
  PRIMARY KEY(dc_nr_multa)
);

CREATE TABLE autoInfracao_AAAAMM (
  dc_nr_multa CHAR(18) NOT NULL AUTO_INCREMENT,
  dc_placa CHAR(7) NULL,
  nr_codCategoria NUMERIC(2) NULL,
  nr_codMarca NUMERIC(6) NULL,
  nr_codTipo NUMERIC(2) NULL,
  nr_codCor NUMERIC(3) NULL,
  nr_codEspecie NUMERIC(3) NULL,
  nr_codMunicipio NUMERIC(5) NULL,
  dc_uf CHAR(2) NULL,
  nr_anoModelo NUMERIC(4) NULL,
  nr_codLocal NUMERIC(5) NULL,
  dc_serieEquipamento CHAR(15) NULL,
  nr_velocidadePermitida NUMERIC(3) NULL,
  nr_velocidadeAferida NUMERIC(3) NULL,
  nr_velocidadeConsiderada NUMERIC(3) NULL,
  dt_infracao DATE NULL,
  hr_infracao CHAR(08) NULL,
  dc_codAgente CHAR(12) NULL,
  nr_faixa NUMERIC(1) NULL,
  lg_entrefaixa NUMERIC(1) NULL,
  nr_codEnquadramento NUMERIC(5) NULL,
  nr_PorteVeiculo NUMERIC(2) NULL,
  nr_tamanhoVeiculo NUMERIC(5,1) NULL,
  dc_placaOCR CHAR(7) NULL,
  nr_codTipoFiscalizacao NUMERIC(2) NULL,
  dc_numeroLaudo VARCHAR(16) NULL,
  dt_validadeLaudo DATE NULL,
  dt_verificacaoLaudo DATE NULL,
  dc_tipoIsencao CHAR(3) NULL,
  lg_renainf NUMERIC(1) NULL,
  nr_imagem NUMERIC(10) NULL,
  nr_codPais NUMERIC(2) NULL,
  lg_uso NUMERIC(1) NULL,
  nr_codClassificacaoTamanho NUMERIC(1) NULL,
  nr_tempoAferido NUMERIC(6,2) NULL,
  nr_tempoLimite NUMERIC(5) NULL,
  nr_status NUMERIC(03) NULL,
  dt_envio DATE NULL,
  dt_recebe DATE NULL,
  PRIMARY KEY(dc_nr_multa)
);

CREATE TABLE cadLote (
  dc_codGrupoAutuador CHAR(2) NOT NULL,
  nr_lote NUMERIC(6) NOT NULL,
  nr_revisao NUMERIC(1) NOT NULL,
  dt_geracaoLote DATE NULL,
  nr_qtdRegistros NUMERIC(4) NULL,
  nr_usuarioGeracao NUMERIC(6) NULL,
  dc_status CHAR(1) NULL,
  dt_envio DATE NULL,
  dt_recebe DATE NULL,
  PRIMARY KEY(dc_codGrupoAutuador, nr_lote)
);

CREATE TABLE cadLoteRep (
  dc_codGrupoAutuador CHAR(2) NOT NULL,
  nr_lote NUMERIC(6) NOT NULL,
  nr_revisao NUMERIC(1) NOT NULL,
  dt_geracaoLote DATE NULL,
  nr_qtdRegistros NUMERIC(4) NULL,
  dc_status CHAR(1) NULL,
  dt_envio DATE NULL,
  dt_recebe DATE NULL,
  dt_envioRevisao DATE NULL,
  dt_recebeRevisao DATE NULL,
  PRIMARY KEY(dc_codGrupoAutuador, nr_lote, nr_revisao)
);

CREATE TABLE contador (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  dc_contador CHAR(20) NULL,
  nr_inicial NUMERIC(10) NULL,
  nr_final NUMERIC(10) NULL,
  nr_ultimo NUMERIC(10) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE contrato (
  nr_crd NUMERIC(6) NOT NULL AUTO_INCREMENT,
  nr_codFilial NUMERIC(5) NULL,
  dc_contrato VARCHAR(30) NULL,
  dt_inicioContrato DATE NULL,
  dt_fimContrato DATE NULL,
  dc_uf CHAR(2) NULL,
  nr_codMunicipio NUMERIC(5) NULL,
  dc_responsavel VARCHAR(25) NULL,
  PRIMARY KEY(nr_crd)
);

CREATE TABLE enquadramento (
  nr_codigo NUMERIC(5) NOT NULL AUTO_INCREMENT,
  nr_artigo NUMERIC(3) NULL,
  dc_paragrafo CHAR(6) NULL,
  dc_item CHAR(2) NULL,
  dc_descricao VARCHAR(300) NULL,
  nr_pontos NUMERIC(2) NULL,
  dc_classificacao CHAR(10) NULL,
  nr_qtdUfir NUMERIC(6,2) NULL,
  nr_tipoInfrator NUMERIC(2) NULL,
  dc_unidadeMedida CHAR(10) NULL,
  dc_medicao CHAR(1) NULL,
  dc_veiculoAutuado CHAR(1) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE equipamento (
  nr_codigo NUMERIC(10) NOT NULL,
  dc_serieEquipamento CHAR(15) NOT NULL AUTO_INCREMENT,
  dc_fabricante CHAR(20) NOT NULL,
  dc_modelo CHAR(15) NOT NULL,
  dc_empresa CHAR(20) NOT NULL,
  dc_status CHAR(1) NOT NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE equipamentoCliente (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  dc_serieEquipamento CHAR(15) NULL,
  dc_equipamentoCliente CHAR(6) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE escalaEstatico (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  nr_codLocalCli NUMERIC(5) NULL,
  nr_faixa NUMERIC(1) NULL,
  dt_alocacaoIni DATE NULL,
  dt_alocacaoFim DATE NULL,
  dc_funcionamentoIni CHAR(08) NULL,
  dc_funcionamentoFim CHAR(08) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE feriado (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  dt_feriado DATE NULL,
  dc_feriado VARCHAR(30) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE filial (
  nr_codigo NUMERIC(5) NOT NULL AUTO_INCREMENT,
  dc_filial VARCHAR(30) NULL,
  dc_responsavel VARCHAR(30) NULL,
  dc_endereco VARCHAR(30) NULL,
  dc_numero CHAR(15) NULL,
  dc_bairro VARCHAR(25) NULL,
  dc_cidade VARCHAR(30) NULL,
  dc_uf CHAR(2) NULL,
  dc_telefone VARCHAR(25) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE grupoAutuador (
  nr_codigo NUMERIC(5) NOT NULL,
  dc_codigo CHAR(2) NOT NULL AUTO_INCREMENT,
  nr_codEnquadramento NUMERIC(5) NOT NULL,
  dc_descricao VARCHAR(40) NOT NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE grupoLocal (
  nr_codigo NUMERIC(5) NOT NULL AUTO_INCREMENT,
  dc_descricao VARCHAR(40) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE grupoLocalItem (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  nr_codGrupoLocal NUMERIC(5) NULL,
  nr_codLocalInfracao NUMERIC(5) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE importaTXT001 (
  dt_infracao CHAR(10) NULL,
  dc_horaInfracao CHAR(11) NULL,
  dc_classificacao CHAR(6) NULL,
  dc_local VARCHAR(80) NULL,
  dc_serie_equipamento CHAR(10) NULL,
  dt_afericao CHAR(10) NULL,
  nr_faixa NUMERIC(1) NULL,
  nr_velocidadelRegulamentada CHAR(7) NULL,
  nr_velocidadeMedida CHAR(7) NULL,
  nr_velocidadeConsiderada CHAR(7) NULL,
  nr_imagem NUMERIC(7) NULL,
  nr_enquadramento NUMERIC(5) NULL,
  dc_enquadramento VARCHAR(80) NULL,
  nr_codLocal CHAR(4) NULL,
  dc_empresa CHAR(2) NULL,
  dc_nome_foto VARCHAR(40) NULL
);

CREATE TABLE importaTXT003 (
  dt_infracao CHAR(10) NULL,
  dc_horaInfracao CHAR(11) NULL,
  dc_local VARCHAR(80) NULL,
  dc_serieEquipamento CHAR(10) NULL,
  nr_faixa NUMERIC(1) NULL,
  dc_diaSemana CHAR(4) NULL AUTO_INCREMENT,
  nr_imagem NUMERIC(7) NULL,
  nr_enquadramento NUMERIC(5) NULL,
  dc_enquadramentoDescricao VARCHAR(80) NULL,
  nr_codLocal CHAR(4) NULL,
  dc_empresa CHAR(2) NULL,
  dc_placaOCR CHAR(7) NULL,
  dc_nomeFoto VARCHAR(40) NULL
);

CREATE TABLE importaTXT004 (
  dt_infracao CHAR(10) NULL,
  dc_horaInfracao CHAR(11) NULL,
  dc_local CHAR(80) NULL,
  dc_serieEquipamento CHAR(10) NULL,
  dc_horarioRestricao VARCHAR(20) NULL,
  nr_faixa NUMERIC(1) NULL,
  dc_dentroEstado CHAR(3) NULL,
  nr_imagem NUMERIC(7) NULL,
  nr_enquadramento NUMERIC(5) NULL,
  dc_enquadramento VARCHAR(80) NULL,
  nr_codLocal CHAR(4) NULL,
  dc_dia_sem CHAR(4) NULL,
  dc_empresa CHAR(2) NULL,
  dc_nomeFoto VARCHAR(40) NULL
);

CREATE TABLE importaTXT005 (
  dt_infracao CHAR(10) NULL,
  dc_horaInfracao CHAR(11) NULL,
  dc_local VARCHAR(80) NULL,
  dc_serieEquipamento CHAR(10) NULL,
  dc_horarioProibido VARCHAR(20) NULL,
  nr_faixa NUMERIC(1) NULL,
  dc_dentroEstado CHAR(3) NULL,
  nr_imagem NUMERIC(7) NULL,
  nr_enquadramento NUMERIC(5) NULL,
  dc_enquadramento VARCHAR(80) NULL,
  nr_codLocal CHAR(4) NULL,
  dc_classificacao CHAR(6) NULL,
  dc_diaSemana CHAR(4) NULL,
  dc_empresa CHAR(2) NULL,
  dc_nomeFoto VARCHAR(40) NULL,
  dc_equipamento_m CHAR(10) NULL,
  dc_equipamento_i CHAR(10) NULL,
  dt_afericacao CHAR(10) NULL,
  nr_velRegulamentada CHAR(7) NULL,
  nr_velMedida CHAR(7) NULL,
  nr_velConsiderada CHAR(7) NULL
);

CREATE TABLE importaTXT006 (
  dt_infracao CHAR(10) NULL,
  dc_horaInfracao CHAR(11) NULL,
  dc_local VARCHAR(80) NULL,
  dc_serieEquipameto CHAR(10) NULL,
  nr_faixa NUMERIC(1) NULL,
  dc_dentroEstado CHAR(3) NULL,
  nr_imagem NUMERIC(7) NULL,
  nr_enquadramento NUMERIC(5) NULL,
  dc_enquadramento VARCHAR(80) NULL,
  nr_codLocal CHAR(4) NULL,
  dc_empresa CHAR(2) NULL,
  dc_placaOCR CHAR(7) NULL,
  dc_nomeFoto VARCHAR(40) NULL,
  dc_diaSemana CHAR(4) NULL
);

CREATE TABLE importaTXT009 (
  dt_infracao CHAR(10) NULL,
  dc_horaInfracao CHAR(11) NULL,
  dc_classificacao CHAR(6) NULL,
  dc_local VARCHAR(80) NULL,
  dc_serieEquipamento CHAR(10) NULL,
  nr_tempoVermelho CHAR(5) NULL,
  nr_tempoRetardo CHAR(5) NULL,
  nr_faixa NUMERIC(1) NULL,
  nr_imagem NUMERIC(7) NULL,
  dc_tipoInfracao VARCHAR(80) NULL,
  dc_dentroEstado CHAR(3) NULL,
  dt_afericao CHAR(10) NULL,
  nr_enquadramento NUMERIC(5) NULL,
  dc_enquadramento VARCHAR(80) NOT NULL,
  nr_codLocal CHAR(4) NULL,
  dc_empresa CHAR(2) NULL,
  dc_horarioProibido VARCHAR(20) NULL,
  dc_nomeFoto VARCHAR(40) NULL,
  nr_velRegulamentada CHAR(7) NULL,
  nr_velMedida CHAR(7) NULL,
  dc_diaSemana CHAR(4) NULL,
  nr_velConsiderada CHAR(7) NULL,
  dc_equipamento_m CHAR(10) NULL,
  dc_equipamento_I CHAR(10) NULL
);

CREATE TABLE importaTXT011 (
  dt_infracao CHAR(10) NULL,
  dc_horaInfracao CHAR(11) NULL,
  dc_classificacao CHAR(6) NULL,
  dc_local VARCHAR(80) NULL,
  dc_serieEquipamento CHAR(10) NULL,
  nr_imagem NUMERIC(7) NULL,
  nr_enquadramento NUMERIC(5) NULL,
  dc_enquadramento VARCHAR(80) NULL,
  nr_codLocal CHAR(4) NULL,
  dc_empresa CHAR(2) NULL,
  dc_nomeFoto VARCHAR(40) NULL,
  nr_tempoVermelho CHAR(5) NULL,
  nr_tempoRetardo CHAR(5) NULL,
  nr_faixa NUMERIC(1) NULL,
  dc_tipoInfracao VARCHAR(80) NULL,
  dc_dentroEstado CHAR(3) NULL,
  dt_afericao CHAR(10) NULL,
  dc_horarioProibido VARCHAR(20) NULL,
  nr_velRegulamentada CHAR(7) NULL,
  dc_diaSemana CHAR(4) NULL,
  nr_velMedida CHAR(7) NULL,
  nr_velConsiderada CHAR(7) NULL
);

CREATE TABLE isencaoEnquadramento (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  nr_codEnquadramento NUMERIC(5) NULL,
  dt_diaIsencao DATE NULL,
  dc_horaIni CHAR(08) NULL,
  dc_horaFim CHAR(08) NULL,
  dc_motivo TEXT NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE isencaoRodizio (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  dt_diaIsencao DATE NULL,
  dc_horaIni CHAR(08) NULL,
  dc_horaFim CHAR(08) NULL,
  lg_automovel NUMERIC(1) NULL,
  lg_caminhao NUMERIC(1) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE isencaoTaxi (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  nr_codLocal NUMERIC(5) NULL,
  nr_diaSemana NUMERIC(1) NULL,
  dc_horaIni CHAR(08) NULL,
  dc_horaFim  CHAR(08) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE isencaoZMRC (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  cd_area CHAR(4) NULL,
  dc_placa CHAR(7) NULL,
  dc_horaIni CHAR(08) NULL,
  dc_horaFim CHAR(08) NULL,
  dt_inicio DATE NULL,
  dt_fim DATE NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE laudoMetrologico (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  dc_serieEquipamento CHAR(15) NULL,
  dc_matriculaTecnico VARCHAR(10) NULL,
  dc_nomeTecnico VARCHAR(30) NULL,
  dc_orgao VARCHAR(40) NULL,
  dc_numeroLaudo VARCHAR(16) NULL,
  dc_numeroLacre VARCHAR(17) NULL,
  dc_numeroLacre2 VARCHAR(17) NULL,
  dt_verificacao DATE NULL,
  dt_validade DATE NULL,
  nr_codMotivoLaudo NUMERIC(5) NULL,
  dc_observacao TEXT NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE laudoNaoMetrologico (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  dc_serieEquipamento CHAR(15) NULL,
  dc_matriculaTecnico VARCHAR(10) NULL,
  dc_nomeTecnico VARCHAR(30) NULL,
  dc_orgao VARCHAR(40) NULL,
  dc_numeroLaudo VARCHAR(16) NULL,
  dc_numeroLacre VARCHAR(17) NULL,
  dc_numeroLacre2 VARCHAR(17) NULL,
  dt_verificacao DATE NULL,
  dt_validade DATE NULL,
  nr_codMotivoLaudo NUMERIC(5) NULL,
  dc_observacao TEXT NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE localArea (
  nr_codigo NUMERIC(10) NOT NULL,
  nr_codLocal NUMERIC(5) NOT NULL AUTO_INCREMENT,
  cd_area CHAR(4) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE localCliente (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  nr_codLocal NUMERIC(5) NULL,
  nr_codLocalCliente NUMERIC(10) NULL,
  nr_tipoFiscalizacao NUMERIC(2) NULL,
  dt_inclusao DATE NULL,
  dt_inicioVigencia DATE NULL,
  nr_porteVeiculo NUMERIC(1) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE localInfracao (
  nr_codigo NUMERIC(5) NOT NULL AUTO_INCREMENT,
  dc_local VARCHAR(80) NULL,
  dc_bairro VARCHAR(30) NULL,
  nr_codMunicipio NUMERIC(5) NULL,
  dc_uf CHAR(2) NULL,
  lg_ativo NUMERIC(1) NULL,
  nr_qtdFaixas NUMERIC(1) NULL,
  nr_faixa1 NUMERIC(1) NULL,
  nr_faixa2 NUMERIC(1) NULL,
  dc_ladoFaixa1 CHAR(1) NULL,
  dc_ladoFaixa2 CHAR(1) NULL,
  dc_sentido CHAR(1) NULL,
  lg_velocidadeDifPorte NUMERIC(1) NULL,
  dc_latitude VARCHAR(17) NULL,
  dc_longitude VARCHAR(17) NULL,
  dt_inicio DATE NULL,
  dt_ultimaAtualizacao DATE NULL,
  nr_codStatus NUMERIC(2) NULL,
  nr_codTipoFixacao NUMERIC(5) NULL,
  nr_codTipoEquipamento NUMERIC(2) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE localVelocidade (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  nr_codLocal NUMERIC(5) NULL,
  nr_velocidadePermitida NUMERIC(3) NULL,
  nr_velocidadeConsiderada NUMERIC(6,1) NULL,
  nr_velocidadeTolerada NUMERIC(6,1) NULL,
  nr_velocidadeGrave NUMERIC(3) NULL,
  nr_velocidadeGravissima NUMERIC(3) NULL,
  nr_codPorteVeiculo NUMERIC(2) NULL,
  dt_inicio DATE NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE local_equipamento (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  nr_codLocal NUMERIC(5) NOT NULL,
  dc_serieEquipamento CHAR(15) NOT NULL,
  dt_inicio DATE NOT NULL,
  dt_fim DATE NOT NULL,
  dc_hr_inicio CHAR(08)) NOT NULL,
  dc_hr_fim CHAR(08) NOT NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE local_situacaoLocal (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  nr_codLocal NUMERIC(5) NULL,
  dc_codSituacao CHAR(3) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE local_tipoFiscalizacao (
  nr_codLocal NUMERIC(5) NOT NULL AUTO_INCREMENT,
  nr_codTipoFiscalizacao NUMERIC(2) NULL,
  PRIMARY KEY(nr_codLocal)
);

CREATE TABLE logOperacao (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  nr_codContrato NUMERIC(5) NULL,
  dc_tipoOperacao CHAR(5) NULL,
  dt_operacao DATE NULL,
  dc_horaOperacao CHAR(08) NULL,
  dc_mensagem TEXT NULL,
  nr_codUsuario NUMERIC(06) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE logUsuario (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  nr_codUsuario NUMERIC(6) NULL,
  dt_login DATE NULL,
  dc_horaLogin CHAR(08) NULL,
  dt_logout DATE NULL,
  dc_horaLogout CHAR(08) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE motivoInconsistenciaImagem (
  nr_codigo NUMERIC(2) NOT NULL AUTO_INCREMENT,
  dc_inconsistencia VARCHAR(100) NOT NULL,
  lg_56732 NUMERIC(1) NULL,
  lg_56810 NUMERIC(1) NULL,
  lg_56900 NUMERIC(1) NULL,
  lg_57030 NUMERIC(1) NULL,
  lg_57461 NUMERIC(1) NULL,
  lg_57462 NUMERIC(1) NULL,
  lg_57463 NUMERIC(1) NULL,
  lg_60411 NUMERIC(1) NULL,
  lg_60412 NUMERIC(1) NULL,
  lg_60503 NUMERIC(1) NULL,
  lg_74550 NUMERIC(1) NULL,
  lg_74630 NUMERIC(1) NULL,
  lg_74710 NUMERIC(1) NULL,
  lg_57461M NUMERIC(1) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE motivoLaudo (
  nr_codigo NUMERIC(5) NOT NULL AUTO_INCREMENT,
  dc_motivoLaudo VARCHAR(30) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE movimentoLote (
  dc_codGrupoAutuador CHAR(2) NOT NULL,
  nr_lote NUMERIC(6) NOT NULL,
  nr_registroLote NUMERIC(4) NOT NULL,
  nr_tipoRegistro NUMERIC(1) NOT NULL AUTO_INCREMENT,
  dt_geracaoLote DATE NULL,
  nr_registroEquipamento NUMERIC(7) NULL,
  dc_placa CHAR(7) NULL,
  nr_codPais NUMERIC(2) NULL,
  nr_codMarca NUMERIC(3) NULL,
  nr_codEspecie NUMERIC(3) NULL,
  nr_codEnquadramento NUMERIC(5) NULL,
  nr_codLocal NUMERIC(4) NULL,
  dc_local VARCHAR(80) NULL,
  nr_codEquipamento NUMERIC(4) NULL,
  dt_registro DATE NULL,
  dc_horaRegistro CHAR(6) NULL,
  nr_velMedida NUMERIC(3) NULL,
  nr_velConsiderada NUMERIC(3) NULL,
  nr_velRegulamentada NUMERIC(3) NULL,
  nr_faixa NUMERIC(1) NULL,
  nr_codOperador NUMERIC(6) NULL,
  dt_analise DATE NULL,
  nr_registroMontante NUMERIC(4) NULL,
  dc_consistenciaCAI CHAR(1) NULL,
  nr_codInconsistenciaCAI NUMERIC(2) NULL,
  nr_imagemNotificacao NUMERIC(1) NULL,
  dc_consistenciaCAV CHAR(1) NULL,
  nr_codInconsistenciaCAV NUMERIC(5) NULL,
  nr_codAuditor NUMERIC(6) NULL,
  dc_origemMulta CHAR(2) NULL,
  dt_auditoria DATE NULL,
  lg_erro NUMERIC(1) NULL,
  lg_corrigiuObliteracao NUMERIC(1) NULL,
  dc_placaOrigem CHAR(7) NULL,
  nr_codMarcaOrigem NUMERIC(3) NULL,
  dc_observacao TEXT NULL,
  dc_status CHAR(1) NULL,
  PRIMARY KEY(dc_codGrupoAutuador, nr_lote, nr_registroLote)
);

CREATE TABLE movimentoLoteRep (
  dc_codGrupoAutuador CHAR(2) NOT NULL,
  nr_lote NUMERIC(6) NOT NULL,
  nr_revisao NUMERIC(1) NOT NULL,
  nr_registroLote NUMERIC(4) NOT NULL,
  nr_tipoRegistro NUMERIC(1) NOT NULL AUTO_INCREMENT,
  dt_geracaoLote DATE NULL,
  nr_registroEquipamento NUMERIC(7) NULL,
  dc_placa CHAR(7) NULL,
  nr_codPais NUMERIC(2) NULL,
  nr_codMarca NUMERIC(3) NULL,
  nr_codEspecie NUMERIC(3) NULL,
  nr_codEnquadramento NUMERIC(5) NULL,
  nr_codLocal NUMERIC(4) NULL,
  dc_local VARCHAR(80) NULL,
  nr_codEquipamento NUMERIC(4) NULL,
  dt_registro DATE NULL,
  dc_horaRegistro CHAR(6) NULL,
  nr_velMedida NUMERIC(3) NULL,
  nr_velConsiderada NUMERIC(3) NULL,
  nr_velRegulamentada NUMERIC(3) NULL,
  nr_faixa NUMERIC(1) NULL,
  nr_codOperador NUMERIC(6) NULL,
  dt_analise DATE NULL,
  nr_registroMontante NUMERIC(4) NULL,
  dc_consistenciaCAI CHAR(1) NULL,
  nr_codInconsistenciaCAI NUMERIC(2) NULL,
  nr_imagemNotificacao NUMERIC(1) NULL,
  dc_consistenciaCAV CHAR(1) NULL,
  nr_codInconsistenciaCAV NUMERIC(5) NULL,
  nr_codAuditor NUMERIC(6) NULL,
  dc_origemMulta CHAR(2) NULL,
  dt_auditoria DATE NULL,
  lg_erro NUMERIC(1) NULL,
  lg_corrigiuObliteracao NUMERIC(1) NULL,
  dc_placaOrigem CHAR(7) NULL,
  nr_codMarcaOrigem NUMERIC(3) NULL,
  dc_observacao TEXT NULL,
  dc_status CHAR(1) NULL,
  nr_codRevisor NUMERIC(6) NULL,
  dt_revisao DATE NULL,
  PRIMARY KEY(dc_codGrupoAutuador, nr_lote, nr_revisao, nr_registroLote)
);

CREATE TABLE movimentoLote_AAAAMM (
  dc_codGrupoAutuador CHAR(2) NOT NULL,
  nr_lote NUMERIC(6) NOT NULL,
  nr_revisao NUMERIC(1) NOT NULL,
  nr_registroLote NUMERIC(4) NOT NULL,
  nr_tipoRegistro NUMERIC(1) NOT NULL AUTO_INCREMENT,
  dt_geracaoLote DATE NULL,
  nr_registroEquipamento NUMERIC(7) NULL,
  dc_placa CHAR(7) NULL,
  nr_codPais NUMERIC(2) NULL,
  nr_codMarca NUMERIC(3) NULL,
  nr_codEspecie NUMERIC(3) NULL,
  nr_codEnquadramento NUMERIC(5) NULL,
  nr_codLocal NUMERIC(4) NULL,
  dc_local VARCHAR(80) NULL,
  nr_codEquipamento NUMERIC(4) NULL,
  dt_registro DATE NULL,
  dc_horaRegistro CHAR(6) NULL,
  nr_velMedida NUMERIC(3) NULL,
  nr_velConsiderada NUMERIC(3) NULL,
  nr_velRegulamentada NUMERIC(3) NULL,
  nr_faixa NUMERIC(1) NULL,
  nr_codOperador NUMERIC(6) NULL,
  dt_analise DATE NULL,
  nr_registroMontante NUMERIC(4) NULL,
  dc_consistenciaCAI CHAR(1) NULL,
  nr_codInconsistenciaCAI NUMERIC(2) NULL,
  nr_imagemNotificacao NUMERIC(1) NULL,
  dc_consistenciaCAV CHAR(1) NULL,
  nr_codInconsistenciaCAV NUMERIC(5) NULL,
  nr_codAuditor NUMERIC(6) NULL,
  dc_origemMulta CHAR(2) NULL,
  dt_auditoria DATE NULL,
  lg_erro NUMERIC(1) NULL,
  lg_corrigiuObliteracao NUMERIC(1) NULL,
  dc_placaOrigem CHAR(7) NULL,
  nr_codMarcaOrigem NUMERIC(3) NULL,
  dc_observacao TEXT NULL,
  dc_status CHAR(1) NULL,
  PRIMARY KEY(dc_codGrupoAutuador, nr_lote, nr_revisao, nr_registroLote)
);

CREATE TABLE municipio (
  nr_codigo NUMERIC(5) NOT NULL AUTO_INCREMENT,
  dc_uf CHAR(2) NULL,
  dc_municipio VARCHAR(40) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE nivelUsuario (
  nr_codigo NUMERIC(2) NOT NULL AUTO_INCREMENT,
  dc_nivelUsuario VARCHAR(30) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE pais (
  nr_codigo NUMERIC(2) NOT NULL AUTO_INCREMENT,
  dc_pais VARCHAR(50) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE parametroSistema (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  dc_parametro VARCHAR(30) NULL,
  dc_tipo CHAR(1) NULL,
  dc_valor VARCHAR(250) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE porteVeiculo (
  nr_codigo NUMERIC(2) NOT NULL AUTO_INCREMENT,
  dc_descricao VARCHAR(30) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE processoDescricao (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  dc_processo VARCHAR(40) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE processoSequencia (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  nr_sequencia NUMERIC(5) NULL,
  nr_status NUMERIC(3) NULL,
  nr_codProcessoPre NUMERIC(10) NULL,
  nr_codProcessoPos NUMERIC(10) NULL,
  nr_codTabela NUMERIC(10) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE processoTabela (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  dc_tabela VARCHAR(40) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE restricaoConversaoProibida (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  nr_codLocal NUMERIC(5) NULL,
  nr_diaSemana NUMERIC(1) NULL,
  dt_inicio DATE NULL,
  dc_horaIni CHAR(08) NULL,
  dt_fim DATE NULL,
  dc_horaFim CHAR(08) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE restricaoFxExclusiva (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  nr_codLocal NUMERIC(5) NULL,
  nr_diaSemana NUMERIC(1) NULL,
  dc_horaIni CHAR(08) NULL,
  dc_horaFim CHAR(08) NULL,
  dt_inicio DATE NULL,
  dt_fim DATE NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE restricaoRodizio (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  nr_codLocal NUMERIC(5) NULL,
  nr_diaSemana NUMERIC(1) NULL,
  dc_finalPlaca CHAR(1)) NULL,
  dc_horaIni CHAR(08) NULL,
  dc_horaFim CHAR(08) NULL,
  dt_inicio DATE NULL,
  dt_fim DATE NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE restricaoZMRC (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  dc_codArea CHAR(4) NULL,
  nr_diaSemana NUMERIC(1) NULL,
  dc_horaIni CHAR(08) NULL,
  dc_horaFim CHAR(08) NULL,
  dt_inicio DATE NULL,
  dt_fim DATE NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE situacaoLocal (
  dc_codigo CHAR(3) NOT NULL AUTO_INCREMENT,
  dc_descricao VARCHAR(35) NULL,
  nr_nivel NUMERIC(2) NULL,
  lg_problema NUMERIC(1) NULL,
  PRIMARY KEY(dc_codigo)
);

CREATE TABLE statusLocal (
  nr_codigo NUMERIC(2) NOT NULL AUTO_INCREMENT,
  dc_descricao VARCHAR(25) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE statusProcesso (
  nr_codigo NUMERIC(3) NOT NULL AUTO_INCREMENT,
  dc_statusProcesso VARCHAR(30) NULL,
  nr_codTabela NUMERIC(10) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE tabelaProcesso (
  nr_codigo NUMERIC(10) NOT NULL AUTO_INCREMENT,
  dc_tabela VARCHAR(30) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE tipoFiscalizacao (
  nr_codigo NUMERIC(2) NOT NULL AUTO_INCREMENT,
  dc_descricao VARCHAR(50) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE tipoFiscalizacao_enquadramento (
  nr_codTipoFiscalizacao NUMERIC(2) NOT NULL AUTO_INCREMENT,
  nr_codEnquadramento NUMERIC(5) NOT NULL,
  PRIMARY KEY(nr_codTipoFiscalizacao, nr_codEnquadramento)
);

CREATE TABLE TipoFixacaoRadar (
  nr_codigo NUMERIC(5) NOT NULL AUTO_INCREMENT,
  dc_descricao VARCHAR(25) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE tipoImagem (
  nr_codigo NUMERIC(2) NOT NULL AUTO_INCREMENT,
  dc_tipoImagem VARCHAR(30) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE tipoLogOperacao (
  nr_codigo NUMERIC(5) NOT NULL AUTO_INCREMENT,
  dc_tipoLogOperacao VARCHAR(30) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE UF (
  dc_uf CHAR(2) NOT NULL AUTO_INCREMENT,
  dc_descricao VARCHAR(30) NULL,
  PRIMARY KEY(dc_uf)
);

CREATE TABLE ultimoLote (
  tp_lote CHAR(2) NOT NULL AUTO_INCREMENT,
  dc_codGrupoAutuador CHAR(2) NULL,
  nr_lote NUMERIC(06) NULL,
  nr_registroLote NUMERIC(4) NULL,
  nr_revisao NUMERIC(1) NULL,
  nr_codLocal NUMERIC(5) NULL,
  dc_nr_multa CHAR(18) NULL,
  nr_codMotivoInconsis NUMERIC(2) NULL,
  dc_status CHAR(2) NULL,
  dt_infracao DATE NULL,
  dc_horaInfracao CHAR(08) NULL,
  dt_inclusao DATE NULL,
  dt_geracaoLote CHAR(08) NULL,
  PRIMARY KEY(tp_lote)
);

CREATE TABLE usuario (
  nr_codigo NUMERIC(6) NOT NULL AUTO_INCREMENT,
  dc_nomeUsuario VARCHAR(30) NULL,
  dc_login VARCHAR(25) NULL,
  dc_senha CHAR(10) NULL,
  nr_nivel NUMERIC(2) NULL,
  dc_cargo VARCHAR(30) NULL,
  nr_codFilial NUMERIC(5) NULL,
  lg_ativo NUMERIC(1) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE veiculo (
  dc_placa CHAR(7) NOT NULL AUTO_INCREMENT,
  nr_codCategoria NUMERIC(2) NULL,
  nr_codMunicipio NUMERIC(5) NULL,
  nr_codMarcaCet NUMERIC(3) NULL,
  nr_codMarcaCetDv NUMERIC(1) NULL,
  nr_codMarcaDenatran NUMERIC(6) NULL,
  nr_codTipoCET NUMERIC(2) NULL,
  nr_codTipoDenatran NUMERIC(2) NULL,
  nr_codCor NUMERIC(3) NULL,
  nr_anoModelo NUMERIC(4) NULL,
  nr_codEspecie NUMERIC(3) NULL,
  PRIMARY KEY(dc_placa)
);

CREATE TABLE veiculoCategoria (
  nr_codigo NUMERIC(2) NOT NULL AUTO_INCREMENT,
  dc_categoria VARCHAR(30) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE veiculoCor (
  nr_codigo NUMERIC(3) NOT NULL AUTO_INCREMENT,
  dc_cor CHAR(15) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE veiculoEspecie (
  nr_codigo NUMERIC(1) NOT NULL AUTO_INCREMENT,
  dc_especie CHAR(15) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE veiculoMarcaCET (
  nr_codigo NUMERIC(5) NOT NULL AUTO_INCREMENT,
  nr_codigoDV NUMERIC(1) NULL,
  dc_marca VARCHAR(35) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE veiculoMarcaDenatran (
  nr_codigo NUMERIC(6) NOT NULL AUTO_INCREMENT,
  dc_marca VARCHAR(35) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE veiculoTipoCET (
  nr_codigo NUMERIC(2) NOT NULL AUTO_INCREMENT,
  dc_descricao VARCHAR(30) NULL,
  PRIMARY KEY(nr_codigo)
);

CREATE TABLE veiculoTipoDenatran (
  nr_codigo NUMERIC(2) NOT NULL AUTO_INCREMENT,
  dc_descricao VARCHAR(30) NULL,
  PRIMARY KEY(nr_codigo)
);


