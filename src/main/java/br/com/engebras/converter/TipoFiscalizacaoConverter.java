/*
 * 
 *  Autor: Adalberto Kamida
 *  Dt. Criacao: 07/11/2016
 * 
 */
package br.com.engebras.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.engebras.model.entities.TipoFiscalizacao;


@FacesConverter(forClass = TipoFiscalizacao.class)
public class TipoFiscalizacaoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()){
            return (TipoFiscalizacao) uiComponent.getAttributes().get(value);
        }
        
        return null;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent uiComponent, Object value) {
        if (value instanceof TipoFiscalizacao) {
            TipoFiscalizacao entity = (TipoFiscalizacao) value; 
            if (entity != null && entity instanceof TipoFiscalizacao && entity.getNr_codigo() != null){
                uiComponent.getAttributes().put(entity.getNr_codigo().toString(), entity); 
                return entity.getNr_codigo().toString();
            }
        }
        return "";
        
    }
    
}
