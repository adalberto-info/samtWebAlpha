/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 14/07/2016
 * 
 */
package br.com.engebras.converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.engebras.model.entities.Feriado;

@FacesConverter(forClass = Feriado.class)
public class FeriadoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()){
            return (Feriado) uiComponent.getAttributes().get(value);
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent uiComponent, Object value) {
        if (value instanceof Feriado) {
            Feriado entity = (Feriado) value; 
            if (entity != null && entity instanceof Feriado && entity.getNr_codigo() != null){
                uiComponent.getAttributes().put(entity.getNr_codigo().toString(), entity); 
                return entity.getNr_codigo().toString();
            }
        }
        return "";
        
    }
    
}
