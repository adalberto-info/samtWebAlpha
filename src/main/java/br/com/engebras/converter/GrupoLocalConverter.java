/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 31/08/2016
 * 
 */
package br.com.engebras.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.engebras.model.entities.GrupoLocal;

@FacesConverter(forClass = GrupoLocal.class)
public class GrupoLocalConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent uiComponent, String value) {

        if (value != null && !value.isEmpty()){
            return (GrupoLocal) uiComponent.getAttributes().get(value);
        }
        
        return null;


    }

    @Override
    public String getAsString(FacesContext context, UIComponent uiComponent, Object value) {

        if (value instanceof GrupoLocal) {
            GrupoLocal entity = (GrupoLocal) value; 
            if (entity != null && entity instanceof GrupoLocal && entity.getNr_codigo() != null){
                uiComponent.getAttributes().put(entity.getNr_codigo().toString(), entity); 
                return entity.getNr_codigo().toString();
            }
        }
        return "";

    }


    
}
