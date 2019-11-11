package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import dao.DaoUsuario;
import dominio.Usuario;

@FacesConverter("usuarioConverter")
public class UsuarioConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		} else {
			try {
				return DaoUsuario.buscaUsuarioPeloNome(value);				
			} catch (Exception e) {
				throw new ConverterException("Erro no usuario converter\n" + e.getMessage());
			}
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (!(value instanceof Usuario))
			return null;

		Usuario usuario = (Usuario) value;
		if (usuario == null || usuario.getNomeUsuario() == null)
			return null;
		
		return usuario.getNomeUsuario();
	}

}
