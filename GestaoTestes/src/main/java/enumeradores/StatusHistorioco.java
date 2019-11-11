package enumeradores;

public enum StatusHistorioco {
	ABERTO("Aberto"), FECHADO("Fechado");

	private String statusHistorico;

	StatusHistorioco(String statusHistorico) {
		this.statusHistorico = statusHistorico;
	}
	
	public String getStatusHistorico() {
		return statusHistorico;
	}
	public void setStatusHistorico(String statusHistorico) {
		this.statusHistorico = statusHistorico;
	}
}
