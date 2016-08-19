/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 19/08/2016
 * 
 */
package br.com.engebras.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.engebras.model.entities.TipoFixacaoRadar;


@FacesConverter(forClass = TipoFixacaoRadar.class)
public class TipoFixacaoRadarConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()){
            return (TipoFixacaoRadar) uiComponent.getAttributes().get(value);
        }
        
        return null;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent uiComponent, Object value) {
        if (value instanceof TipoFixacaoRadar) {
            TipoFixacaoRadar entity = (TipoFixacaoRadar) value; 
            if (entity != null && entity instanceof TipoFixacaoRadar && entity.getNr_codigo() != null){
                uiComponent.getAttributes().put(entity.getNr_codigo().toString(), entity); 
                return entity.getNr_codigo().toString();
            }
        }
        return "";
    }
    
}
