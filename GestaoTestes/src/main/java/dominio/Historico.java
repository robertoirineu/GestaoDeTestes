package dominio;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import enumeradores.AcaoHistorico;
import enumeradores.PrioridadeHistorico;
import enumeradores.StatusHistorioco;
import enumeradores.TipoAcaoHistorico;

@Entity
public class Historico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "datafechamento")
	private Date dataFechamento;

	@Column(name = "datarealizacoa")
	private Date dataRealizacao;

	@Column(name = "comentariohistorico")
	private String comentarioHistorico;

	@Column(name = "contato")
	private String contato;

	@Column(name = "justificativaatraso")
	private String justificativaAtraso;

	@Column(name = "tarefaatrasada")
	private boolean tarefaAtrasada;

	@Enumerated(EnumType.STRING)
	@Column(name = "statushistorico")
	private StatusHistorioco statusHistorico;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipoacao")
	private TipoAcaoHistorico tipoAcao;

	@Enumerated(EnumType.STRING)
	@Column(name = "acaohistorico")
	private AcaoHistorico acaoHistorico;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "prioridade")
	private PrioridadeHistorico prioridade;

	@ManyToOne
	private Usuario responsavel;

	@ManyToOne
	private Projeto projeto;

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public StatusHistorioco getStatusHistorico() {
		return statusHistorico;
	}

	public void setStatusHistorico(StatusHistorioco statusHistorico) {
		this.statusHistorico = statusHistorico;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public TipoAcaoHistorico getTipoAcao() {
		return tipoAcao;
	}

	public void setTipoAcao(TipoAcaoHistorico tipoAcao) {
		this.tipoAcao = tipoAcao;
	}

	public AcaoHistorico getAcaoHistorico() {
		return acaoHistorico;
	}

	public void setAcaoHistorico(AcaoHistorico acaoHistorico) {
		this.acaoHistorico = acaoHistorico;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public String getComentarioHistorico() {
		return comentarioHistorico;
	}

	public void setComentarioHistorico(String comentarioHistorico) {
		this.comentarioHistorico = comentarioHistorico;
	}

	public String getJustificativaAtraso() {
		return justificativaAtraso;
	}

	public void setJustificativaAtraso(String justificativaAtraso) {
		this.justificativaAtraso = justificativaAtraso;
	}

	public PrioridadeHistorico getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(PrioridadeHistorico prioridade) {
		this.prioridade = prioridade;
	}

	public boolean isTarefaAtrasada() {
		Calendar calendarOntem = Calendar.getInstance();
		calendarOntem.setTime(new Date());
		calendarOntem.set(Calendar.DAY_OF_MONTH, calendarOntem.get(Calendar.DAY_OF_MONTH) - 1);
		
		if (dataRealizacao != null && dataRealizacao.before(calendarOntem.getTime())) {
			tarefaAtrasada = true;
		} else {
			tarefaAtrasada = false;
		}
		return tarefaAtrasada;
	}

	public void setTarefaAtrasada(boolean tarefaAtrasada) {
		this.tarefaAtrasada = tarefaAtrasada;
	}

}
