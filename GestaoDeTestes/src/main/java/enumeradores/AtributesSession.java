package enumeradores;

public enum AtributesSession {

	USUARIO_LOGADO("usuarioLogado"), LOGIN_URL("/GestaoTestes/faces/login.xhtml\n");

	private String value;

	private AtributesSession(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
