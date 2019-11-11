package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import dao.DaoCliente;
import dominio.Cliente;


@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		} else {
			try {
				return DaoCliente.buscaClientePeloNome(value);				
			} catch (Exception e) {
				throw new ConverterException("Erro no cliente converter\n" + e.getMessage());
			}
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (!(value instanceof Cliente))
			return null;

		Cliente cliente = (Cliente) value;
		if (cliente == null || cliente.getNomeCliente() == null)
			return null;
		
		return cliente.getNomeCliente();
	}

}
