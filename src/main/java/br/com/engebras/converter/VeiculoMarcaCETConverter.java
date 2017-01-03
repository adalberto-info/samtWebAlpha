/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 02/01/2016
 * 
 */
package br.com.engebras.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.engebras.model.entities.VeiculoMarcaCET;

@FacesConverter(forClass = VeiculoMarcaCET.class)
public class VeiculoMarcaCETConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()){
            return (VeiculoMarcaCET) uiComponent.getAttributes().get(value);
        }
        
        return null;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent uiComponent, Object value) {
        if (value instanceof VeiculoMarcaCET) {
            VeiculoMarcaCET entity = (VeiculoMarcaCET) value; 
            if (entity != null && entity instanceof VeiculoMarcaCET && entity.getNr_codigo() != null){
                uiComponent.getAttributes().put(entity.getNr_codigo().toString(), entity); 
                return entity.getNr_codigo().toString();
            }
        }
        return "";
        
    }
    
}
