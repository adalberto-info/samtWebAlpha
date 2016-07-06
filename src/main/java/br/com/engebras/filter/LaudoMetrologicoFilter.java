/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 06/07/2016
 *
 */
package br.com.engebras.filter;

import java.io.Serializable;

public class LaudoMetrologicoFilter implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String dc_serieEquipamento; 

    public String getDc_serieEquipamento() {
        return dc_serieEquipamento;
    }

    public void setDc_serieEquipamento(String dc_serieEquipamento) {
        this.dc_serieEquipamento = dc_serieEquipamento;
    }
    
    
    
    
}
