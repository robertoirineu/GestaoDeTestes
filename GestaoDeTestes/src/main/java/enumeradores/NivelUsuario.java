package enumeradores;

import java.util.ArrayList;
import java.util.List;

public enum NivelUsuario {
	JUNIOR("Júnior"), SENIOR("Sênior"), PLENO("Plêno");
	
	private String nivel;

	NivelUsuario(String nivel) {
		this.nivel = nivel;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public static List<String> getComboNivelUsuario() {
		List<String> lista = new ArrayList<>();
		for (NivelUsuario s : NivelUsuario.values()) {
			lista.add(s.getNivel());
		}
		return lista;
	}
}
