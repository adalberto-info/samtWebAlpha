package br.com.engebras.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.engebras.model.entities.Uf;

/**
 * @author Adalberto
 * dt. criação: 28/04/2016
 */
@FacesConverter(forClass = Uf.class)
public class UfConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Uf) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Uf) {
            Uf entity= (Uf) value;
            if (entity != null && entity instanceof Uf && entity.getDc_uf() != null) {
                uiComponent.getAttributes().put(entity.getDc_uf(), entity);
                return entity.getDc_uf();
            }
        }
        return "";
    }
}