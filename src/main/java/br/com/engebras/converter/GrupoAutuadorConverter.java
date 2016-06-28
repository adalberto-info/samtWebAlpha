package br.com.engebras.converter;

/**
 * @author Adalberto kamida
 * dt. criação: 23/06/2016
 */

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.engebras.model.entities.GrupoAutuador;

@FacesConverter(forClass = GrupoAutuador.class)
public class GrupoAutuadorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()){
            return (GrupoAutuador) uiComponent.getAttributes().get(value);
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent uiComponent, Object value) {
        if (value instanceof GrupoAutuador) {
            GrupoAutuador entity = (GrupoAutuador) value; 
            if (entity != null && entity instanceof GrupoAutuador && entity.getNr_codigo() != null){
                uiComponent.getAttributes().put(entity.getNr_codigo().toString(), entity); 
                return entity.getNr_codigo().toString();
            }
        }
        return "";
    }


    
}
