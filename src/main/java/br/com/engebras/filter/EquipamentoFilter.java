package br.com.engebras.filter;

/**
 * @author Adalberto
 * dt. criação: 20/06/2016
 */

import java.io.Serializable;


public class EquipamentoFilter implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String dc_serieEquipamento; 

    public String getDc_serieEquipamento() {
        return dc_serieEquipamento;
    }

    public void setDc_serieEquipamento(String dc_serieEquipamento) {
        this.dc_serieEquipamento = dc_serieEquipamento;
    }
    
    
}
