/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 06/07/2017
 * 
 */

package br.com.engebras.controller;

import java.io.File;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

@ManagedBean(name = "mbInicio")
@SessionScoped
public class MbInicio implements Serializable{
    private static final long serialVersionUID = 1L;



    public MbInicio(){
        
    }

    public void iniciaAplicacao(){
        //Criando os diretórios do sistema... 

        FacesContext facesContext = FacesContext.getCurrentInstance();  
        ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();  
        String destino = scontext.getRealPath("/resources/upload/");
        
        File dirUpload = new File(destino); 
        if (!dirUpload.isDirectory()){
            dirUpload.mkdirs();
        }
    }
}



