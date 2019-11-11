package enumeradores;

public enum Periodo {
	TODO_PERIODO("Todo período"), DIA("Dia"), SEMANA("Semana"), MES("Mês");

	private String periodo;

	Periodo(String periodo) {
		this.periodo = periodo;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
