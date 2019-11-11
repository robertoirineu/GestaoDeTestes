package enumeradores;

import java.util.ArrayList;
import java.util.List;

public enum CargoUsuario {
	TESTER("Tester"),PROGRAMADOR("Programador"), ENGENHEIRO("Engenheiro");
	
	String cargo;
	
	private CargoUsuario(String cargo) {
		this.cargo = cargo;
	}
	
	public String getCargo() {
		return this.cargo;
	}
	
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public static List<String> getComboCargoUsuario() {
		List<String> lista = new ArrayList<>();
		for (CargoUsuario s : CargoUsuario.values()) {
			lista.add(s.getCargo());
		}
		return lista;
	}
}
