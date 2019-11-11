package factory;

import java.io.InputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class SessionContextFactory {
	private static SessionContextFactory instance;

	public static SessionContextFactory getInstance() {
		if (instance == null) {
			instance = new SessionContextFactory();
		}
		return instance;
	}

	private SessionContextFactory() {
	}

	private ExternalContext currentExternalContext() {
		if (FacesContext.getCurrentInstance() == null) {
			throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
		} else {
			return FacesContext.getCurrentInstance().getExternalContext();
		}
	}

	public InputStream getResourceAsStream(String filePath) {
		return currentExternalContext().getResourceAsStream(filePath);
	}

	public void encerrarSessao() {
		currentExternalContext().invalidateSession();
	}

	public Object getAttribute(String nome) {
		return currentExternalContext().getSessionMap().get(nome);
	}

	public void setAttribute(String nome, Object valor) {
		currentExternalContext().getSessionMap().put(nome, valor);
	}

	public void removeSessionMap() {
		currentExternalContext().getSessionMap().clear();
	}

	public static boolean isSessionOpen() {
		if (instance == null)
			return false;
		return true;
	}

	public static void setNulInstanceSessionContext() {
		instance = null;
	}
}
