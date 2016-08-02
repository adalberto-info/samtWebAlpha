/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 02/08/2016
 * 
 */
package br.com.engebras.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.engebras.model.entities.LocalInfracao;

@FacesConverter(forClass = LocalInfracao.class)
public class LocalInfracaoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent uiComponent, String value) {
       if (value != null && !value.isEmpty()){
            return (LocalInfracao) uiComponent.getAttributes().get(value);
        }
        
        return null;
         
    }

    @Override
    public String getAsString(FacesContext context, UIComponent uiComponent, Object value) {
       if (value instanceof LocalInfracao) {
            LocalInfracao entity = (LocalInfracao) value; 
            if (entity != null && entity instanceof LocalInfracao && entity.getNr_codigo() != null){
                uiComponent.getAttributes().put(entity.getNr_codigo().toString(), entity); 
                return entity.getNr_codigo().toString();
            }
        }
        return "";
         
    }
    
}
