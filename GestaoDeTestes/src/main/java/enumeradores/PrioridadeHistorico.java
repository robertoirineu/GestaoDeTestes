package enumeradores;

public enum PrioridadeHistorico {
	BAIXA("Baixa"),NORMAL("Normal"),ALTA("Alta");
	
	private String prioridade;
	
	private PrioridadeHistorico(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	
	
}
