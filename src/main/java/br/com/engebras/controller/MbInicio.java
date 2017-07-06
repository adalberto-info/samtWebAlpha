/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 06/07/2017
 * 
 */

package br.com.engebras.controller;

import java.io.File;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "mbInicio")
@SessionScoped
public class MbInicio implements Serializable{
    private static final long serialVersionUID = 1L;



    public MbInicio(){
        
    }

    private void iniciaAplicacao(){
        //Criando os diretórios do sistema... 

        File dirUpload = new File("/resources/upload/"); 
        if (!dirUpload.isDirectory()){
            dirUpload.mkdir();
        }
    }
}



