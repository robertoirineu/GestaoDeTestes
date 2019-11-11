package enumeradores;

public enum AcaoHistorico {
	EMAILENVIADO("E-mail enviado"), EMAIL_RECEBIDO("E-mail recebido"), TELEFONEMA_RECEBIDO(
			"Telefonema recebido"), TELEFONEMA_REALIZADO("Telefonema realizado"), REUNIAO_AGENDADA(
					"Reunião agendada"), REUNIAO_REALIZADA("Reunião realizada");

	private String acaoHistorico;

	AcaoHistorico(String acaoHistorico) {
		this.acaoHistorico = acaoHistorico;
	}

	public String getAcaoHistorico() {
		return this.acaoHistorico;
	}
}
