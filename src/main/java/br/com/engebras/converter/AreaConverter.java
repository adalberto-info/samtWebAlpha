package br.com.engebras.converter;

/**
 * @author Adalberto
 * dt. criação: 28/06/2016
 */
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.engebras.model.entities.Area;

@FacesConverter(forClass = Area.class)
public class AreaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()){
            return (Area) uiComponent.getAttributes().get(value);
        }
        
        return null;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent uiComponent, Object value) {
        if (value instanceof Area) {
            Area entity = (Area) value; 
            if (entity != null && entity instanceof Area && entity.getDc_codArea() != null){
                uiComponent.getAttributes().put(entity.getDc_codArea(), entity); 
                return entity.getDc_codArea();
            }
        }
        return "";
        
    }
    
    
}
