package enumeradores;

import java.util.ArrayList;
import java.util.List;

public enum Status {
	ATIVO("Ativo"), INATIVO("Inativo");
	
	private String status;
	
	Status(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public static List<String> getComboStatus() {
		List<String> lista = new ArrayList<>();
		for (Status s : Status.values()) {
			lista.add(s.getStatus());
		}
		return lista;
	}
	
}
