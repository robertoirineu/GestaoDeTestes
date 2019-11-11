package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import dao.DaoProjeto;
import dominio.Projeto;

@FacesConverter("projetoConverter")
public class ProjetoConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String nomeProjeto) {
		if (nomeProjeto == null || nomeProjeto.isEmpty()) {
			return null;
		} else {
			try {
				return DaoProjeto.buscaProjeto(nomeProjeto);				
			} catch (Exception e) {
				throw new ConverterException("Erro no projeto converter\n" + e.getMessage());
			}
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (!(value instanceof Projeto))
			return null;

		Projeto projeto = (Projeto) value;
		if (projeto == null || projeto.getNomeProjeto() == null)
			return null;
		
		return projeto.getNomeProjeto();
	}
}
