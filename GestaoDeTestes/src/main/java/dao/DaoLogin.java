package dao;

import dao.DaoUsuario;
import dominio.Usuario;
import enumeradores.Status;

public class DaoLogin {
	public static boolean logar(Usuario usuario) {
		Usuario v = DaoUsuario.buscaUsuario(usuario.getEmail());
		if (v != null) {
			if (v.getStatusUsuario() == Status.INATIVO)
				return false;
			return (usuario.getEmail().equals(v.getEmail()) && usuario.getPaswUsuario().equals(v.getPaswUsuario()));

		} else {
			return false;
		}
	}
}
