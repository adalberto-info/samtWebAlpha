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
import br.com.engebras.model.entities.SituacaoLocal;

@FacesConverter(forClass = SituacaoLocal.class)
public class SituacaoLocalConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()){
            return (SituacaoLocal) uiComponent.getAttributes().get(value);
        }
        
        return null;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent uiComponent, Object value) {
        if (value instanceof SituacaoLocal) {
            SituacaoLocal entity = (SituacaoLocal) value; 
            if (entity != null && entity instanceof SituacaoLocal && entity.getDc_codigo()!= null){
                uiComponent.getAttributes().put(entity.getDc_codigo(), entity); 
                return entity.getDc_codigo();
            }
        }
        return "";
        
    }
    
}
