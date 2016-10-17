/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 14/10/2016
 * 
 */
package br.com.engebras.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.engebras.model.entities.Local_tipoFiscalizacao;

@FacesConverter(forClass = Local_tipoFiscalizacao.class)
public class Local_tipoFiscalizacaoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent uiComponent, String value) {
       if (value != null && !value.isEmpty()){
            return (Local_tipoFiscalizacao) uiComponent.getAttributes().get(value);
        }
        
        return null;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent uiComponent, Object value) {
       if (value instanceof Local_tipoFiscalizacao) {
            Local_tipoFiscalizacao entity = (Local_tipoFiscalizacao) value; 
            if (entity != null && entity instanceof Local_tipoFiscalizacao && entity.getNr_codigo() != null){
                uiComponent.getAttributes().put(entity.getNr_codigo().toString(), entity); 
                return entity.getNr_codigo().toString();
            }
        }
        return "";        
    }
    
}
