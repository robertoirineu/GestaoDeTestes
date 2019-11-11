package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class CheckPoint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Projeto projeto;
		
	@Column
	private boolean reuniaoEntendimento;

	@Column
	private boolean instrumentacao;
	
	@Column
	private boolean performance;
	
	@Column
	private boolean stresTest;
	
	@Column
	private boolean levantamentoRequisitos;
	@Column
	
	private boolean coletaLogs;
	
	@Column
	private boolean capacityPlanner;
	
	@Column
	private boolean entregaRelatório;
	
	@Transient
	private double progresso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isReuniaoEntendimento() {
		return reuniaoEntendimento;
	}

	public void setReuniaoEntendimento(boolean reuniaoEntendimento) {
		this.reuniaoEntendimento = reuniaoEntendimento;
	}

	public boolean isInstrumentacao() {
		return instrumentacao;
	}

	public void setInstrumentacao(boolean instrumentacao) {
		this.instrumentacao = instrumentacao;
	}

	public boolean isPerformance() {
		return performance;
	}

	public void setPerformance(boolean performance) {
		this.performance = performance;
	}

	public boolean isStresTest() {
		return stresTest;
	}

	public void setStresTest(boolean stresTest) {
		this.stresTest = stresTest;
	}

	public boolean isLevantamentoRequisitos() {
		return levantamentoRequisitos;
	}

	public void setLevantamentoRequisitos(boolean levantamentoRequisitos) {
		this.levantamentoRequisitos = levantamentoRequisitos;
	}

	public boolean isColetaLogs() {
		return coletaLogs;
	}

	public void setColetaLogs(boolean coletaLogs) {
		this.coletaLogs = coletaLogs;
	}

	public boolean isCapacityPlanner() {
		return capacityPlanner;
	}

	public void setCapacityPlanner(boolean capacityPlanner) {
		this.capacityPlanner = capacityPlanner;
	}

	public boolean isEntregaRelatório() {
		return entregaRelatório;
	}

	public void setEntregaRelatório(boolean entregaRelatório) {
		this.entregaRelatório = entregaRelatório;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public double getProgresso() {
		return progresso;
	}

	public void setProgresso(double progresso) {
		this.progresso = progresso;
	}
}
