package br.com.engebras.converter;

/**
 * @author Adalberto
 * dt. criação: 21/06/2016
 */

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.engebras.model.entities.Equipamento;


@FacesConverter(forClass = Equipamento.class)
public class EquipamentoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()){
            return (Equipamento) uiComponent.getAttributes().get(value);
        }
        
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent uiComponent, Object value) {
        if (value instanceof Equipamento) {
            Equipamento entity = (Equipamento) value; 
            if (entity != null && entity instanceof Equipamento && entity.getNr_codigo() != null){
                uiComponent.getAttributes().put(entity.getNr_codigo().toString(), entity); 
                return entity.getNr_codigo().toString();
            }
        }
        return "";

    }
    
}
