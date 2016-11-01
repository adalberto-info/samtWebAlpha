/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 24/10/2016
 * 
 */
package br.com.engebras.filter;

import java.io.Serializable;

public class Local_equipamentoFilter implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    private Integer nr_codigo; 
    private String dc_serieEquipamento; 

    public Integer getNr_codigo() {
        return nr_codigo;
    }

    public void setNr_codigo(Integer nr_codigo) {
        this.nr_codigo = nr_codigo;
    }

    public String getDc_serieEquipamento() {
        return dc_serieEquipamento;
    }

    public void setDc_serieEquipamento(String dc_serieEquipamento) {
        this.dc_serieEquipamento = dc_serieEquipamento;
    }
    
    
    
}
