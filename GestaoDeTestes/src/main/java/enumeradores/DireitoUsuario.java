package enumeradores;

import java.util.ArrayList;
import java.util.List;

public enum DireitoUsuario {
	ANALISTA("Analista"), GERENTE("Gerente");

	private String direito;

	DireitoUsuario(String direito) {
		this.direito = direito;
	}

	public String getDireito() {
		return this.direito;
	}
	
	public void setDireito(String direito) {
		this.direito = direito;
	}

	public static List<String> getComboDireitoUsuario() {
		List<String> lista = new ArrayList<>();
		for (DireitoUsuario s : DireitoUsuario.values()) {
			lista.add(s.getDireito());
		}
		return lista;
	}
}
