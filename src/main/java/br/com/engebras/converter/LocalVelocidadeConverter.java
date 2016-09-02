/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 02/09/2016
 * 
 */
package br.com.engebras.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.engebras.model.entities.LocalVelocidade;

@FacesConverter(forClass = LocalVelocidade.class)
public class LocalVelocidadeConverter implements Converter{

    private static final long serialVersionUID = 1L; 

    @Override
    public Object getAsObject(FacesContext context, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()){
            return (LocalVelocidade) uiComponent.getAttributes().get(value);
        }
        
        return null;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent uiComponent, Object value) {
        if (value instanceof LocalVelocidade) {
            LocalVelocidade entity = (LocalVelocidade) value; 
            if (entity != null && entity instanceof LocalVelocidade && entity.getNr_codigo() != null){
                uiComponent.getAttributes().put(entity.getNr_codigo().toString(), entity); 
                return entity.getNr_codigo().toString();
            }
        }
        return "";
    }
    
}
