package enumeradores;

import java.util.ArrayList;
import java.util.List;

public enum StatusProjeto {

	ATIVO("Ativo"), INATIVO("Inativo"), ENTREGUE("Entregue"), CANCELADO("Cancelado");

	private String status;

	StatusProjeto(String status) {
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
