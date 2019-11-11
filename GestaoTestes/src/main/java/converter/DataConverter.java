package converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("dataConverter")
public class DataConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date data = simpleDateFormat.parse(value);
			return data;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (!(value instanceof Date))
			return null;
		Date data = (Date) value;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataConverter = simpleDateFormat.format(data);

		return dataConverter;
	}

}
