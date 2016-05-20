package br.com.engebras.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;

import br.com.engebras.model.entities.Filial;
import br.com.engebras.repository.filter.FilialFilter;

/**
 * @author Adalberto
 * dt. criação: 19/05/2016
 */


@ManagedBean(name="mbPesquisaFilial")
@ViewScoped
public class MbPesquisaFilial implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public FilialFilter filtro;
    private List<Filial> filiaisFiltradas;

    private Filial filialSelecionada;
    
    
    public MbPesquisaFilial(){
        filtro = new FilialFilter();
    }
    
}
