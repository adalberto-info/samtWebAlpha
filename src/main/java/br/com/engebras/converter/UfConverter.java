package br.com.engebras.converter;

import br.com.engebras.model.entities.Uf;
import br.com.engebras.repository.Ufs;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;




/**
 * @author Adalberto
 * dt. criação: 28/04/2016
 */
@FacesConverter(forClass = Uf.class)
public class UfConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Uf retorno = null;
        
        if (value != null){
            String dc_uf = new String(value);
            return Ufs.porDc_uf(dc_uf);
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null){
            return ((Uf) value).getDc_uf().toString();
        }
        
        return "";
    }
    
}
