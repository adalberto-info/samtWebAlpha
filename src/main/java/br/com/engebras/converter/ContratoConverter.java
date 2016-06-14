package br.com.engebras.converter;

/**
 * @author Adalberto
 * dt. criação: 14/06/2016
 */

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.engebras.model.entities.Contrato;

@FacesConverter(forClass = Contrato.class)
public class ContratoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Contrato) uiComponent.getAttributes().get(value);
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent uiComponent, Object value) {
        if (value instanceof Contrato){
            Contrato entity = (Contrato) value;
            if (entity != null && entity instanceof Contrato && entity.getNr_crd() != null){
                uiComponent.getAttributes().put(entity.getNr_crd().toString(), entity);
                return entity.getNr_crd().toString();
            }
        }
        return "";
    }
    
}
