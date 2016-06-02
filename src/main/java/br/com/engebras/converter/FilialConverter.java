package br.com.engebras.converter;

/**
 * @author Adalberto
 * dt. criação: 02/06/2016
 */

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.engebras.model.entities.Filial;

@FacesConverter(forClass = Filial.class)
public class FilialConverter implements Converter {
    
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value){
        if (value != null && !value.isEmpty()){
            return (Filial) uiComponent.getAttributes().get(value);
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext facescontext, UIComponent uiComponent, Object value) {
        if (value instanceof Filial) {
            Filial entity = (Filial) value; 
            if (entity != null && entity instanceof Filial && entity.getDc_filial() != null){
                uiComponent.getAttributes().put(entity.getDc_filial(), entity); 
                return entity.getDc_filial();
            }
        }
        return "";
    }
    
}
