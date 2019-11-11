package enumeradores;

public enum TipoAcaoHistorico {
	EMAIL("E-mail"),TELEFONEMA("Telefonema"),REUNIAO("Reunião");

	private String tipoAcao;

	TipoAcaoHistorico(String tipoAcao) {
		this.tipoAcao = tipoAcao;
	}

	public String getTipoAcao() {
		return this.tipoAcao;
	}
}
