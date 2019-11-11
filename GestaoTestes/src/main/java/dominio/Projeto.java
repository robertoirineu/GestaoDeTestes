package dominio;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import enumeradores.StatusProjeto;

@Entity(name = "Projeto")
public class Projeto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codProjeto;

	@Column(nullable = false, length = 60)
	private String nomeProjeto;

	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	private Usuario analista;

	@ManyToOne
	private Usuario gerente;

	@OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
	private List<Historico> Historicos;

	@OneToOne(mappedBy = "projeto", cascade = CascadeType.ALL)
	private CheckPoint checkPoint;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private StatusProjeto statusProjeto;

	@Column(nullable = false)
	private Date dataProjetoInicio;

	@Column(nullable = false)
	private Date dataProjetoFim;

	@Column
	private Date dataFimEfetivo;

	@Column
	private String observacoes;

	public Long getCodProjeto() {
		return codProjeto;
	}

	public void setCodProjeto(Long codProjeto) {
		this.codProjeto = codProjeto;
	}

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public StatusProjeto getStatusProjeto() {
		return statusProjeto;
	}

	public void setStatusProjeto(StatusProjeto statusProjeto) {
		this.statusProjeto = statusProjeto;
	}

	public Date getDataProjetoInicio() {
		return dataProjetoInicio;
	}

	public void setDataProjetoInicio(Date dataProjetoInicio) {
		this.dataProjetoInicio = dataProjetoInicio;
	}

	public Date getDataProjetoFim() {
		return dataProjetoFim;
	}

	public void setDataProjetoFim(Date dataProjetoFim) {
		this.dataProjetoFim = dataProjetoFim;
	}

	public Date getDataFimEfetivo() {
		return dataFimEfetivo;
	}

	public void setDataFimEfetivo(Date dataFimEfetivo) {
		this.dataFimEfetivo = dataFimEfetivo;
	}

	public Usuario getAnalista() {
		return analista;
	}

	public void setAnalista(Usuario analista) {
		this.analista = analista;
	}

	public Usuario getGerente() {
		return gerente;
	}

	public void setGerente(Usuario gerente) {
		this.gerente = gerente;
	}

	public List<Historico> getHistoricos() {
		return Historicos;
	}

	public void setHistoricos(List<Historico> historicos) {
		Historicos = historicos;
	}

	public CheckPoint getCheckPoint() {
		return checkPoint;
	}

	public void setCheckPoint(CheckPoint checkPoint) {
		this.checkPoint = checkPoint;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

}
