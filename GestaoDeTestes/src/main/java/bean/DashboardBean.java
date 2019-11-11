package bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;

import dao.DaoCliente;
import dao.DaoHistorico;
import dao.DaoProjeto;
import dao.DaoUsuario;
import dominio.CheckPoint;
import dominio.Historico;
import dominio.Projeto;
import dominio.Usuario;
import enumeradores.AcaoHistorico;
import enumeradores.AtributesSession;
import enumeradores.DireitoUsuario;
import enumeradores.Periodo;
import enumeradores.StatusHistorioco;
import enumeradores.StatusProjeto;
import enumeradores.TipoAcaoHistorico;
import factory.SessionContextFactory;
import util.Mensagem;

@ManagedBean(name = "dashboardBean")
@ViewScoped
public class DashboardBean {

	private Periodo periodoProjeto;
	private Periodo periodoHistorico;
	private Projeto projetoSelecionado;
	private Historico historicoSelecionado;
	private List<Usuario> usuariosAutoComplete;
	private boolean desabilitarCamposHistorico;
	private List<Projeto> projetos;
	private List<Historico> historicos;
	private List<Historico> historicosAtrasados;
	private Map<AcaoHistorico, AcaoHistorico> acao;

	@PostConstruct
	public void init() {
		this.acao = new HashMap<AcaoHistorico, AcaoHistorico>();
		Usuario usuario = (Usuario) SessionContextFactory.getInstance()
				.getAttribute(AtributesSession.USUARIO_LOGADO.getValue());

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		String dash2 = request.getContextPath() + "/faces/dashboard2.xhtml";
		String dash = request.getContextPath() + "/faces/dashboard.xhtml";
		String login = request.getContextPath() + "/faces/login.xhtml";
		String uri = request.getRequestURI();

		try {

			if (usuario.getDireitoUsuario().equals(DireitoUsuario.ANALISTA) && uri.equals(dash2)) {
				FacesContext.getCurrentInstance().getExternalContext().redirect(dash);

			} else if (!SessionContextFactory.isSessionOpen()) {
				FacesContext.getCurrentInstance().getExternalContext().redirect(login);
			}

		} catch (Exception e) {
			Mensagem.falha(e.getMessage());
		}

		this.historicoSelecionado = new Historico();
		this.projetoSelecionado = new Projeto();

		this.projetos = DaoProjeto.projetos(true, usuario, null, null, false, false);

		this.historicos = DaoHistorico.historicos(false, false, false, false, usuario, null, null);

		this.usuariosAutoComplete = DaoUsuario.listarUsuariosAtivos();

		Calendar tresDias = Calendar.getInstance();
		tresDias.setTime(new Date());
		tresDias.set(Calendar.DAY_OF_MONTH, tresDias.get(Calendar.DAY_OF_MONTH) - 3);

		this.historicosAtrasados = DaoHistorico.historicos(false, false, false, false, null, null, tresDias.getTime());

		calculaProgresso(projetos);
	}

	public void filtrarProjetoPorData() {

		if (this.periodoProjeto != null) {
			Calendar calendar = Calendar.getInstance();

			Calendar calendarOntem = Calendar.getInstance();
			calendarOntem.setTime(new Date());
			calendarOntem.set(Calendar.DAY_OF_MONTH, calendarOntem.get(Calendar.DAY_OF_MONTH) - 1);

			switch (this.periodoProjeto) {
			case DIA:
				this.projetos = DaoProjeto.projetos(true,
						(Usuario) SessionContextFactory.getInstance()
								.getAttribute(AtributesSession.USUARIO_LOGADO.getValue()),
						calendarOntem.getTime(), new Date(), false, false);
				calculaProgresso(projetos);
				break;

			case SEMANA:
				calendar.setTime(new Date());
				calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 8);

				this.projetos = DaoProjeto.projetos(true,
						(Usuario) SessionContextFactory.getInstance()
								.getAttribute(AtributesSession.USUARIO_LOGADO.getValue()),
						calendarOntem.getTime(), calendar.getTime(), false, false);
				calculaProgresso(projetos);
				break;

			case MES:
				calendar.setTime(new Date());
				calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);

				this.projetos = DaoProjeto.projetos(true,
						(Usuario) SessionContextFactory.getInstance()
								.getAttribute(AtributesSession.USUARIO_LOGADO.getValue()),
						calendarOntem.getTime(), calendar.getTime(), false, false);
				calculaProgresso(projetos);
				break;

			case TODO_PERIODO:
				this.projetos = DaoProjeto
						.projetos(true,
								(Usuario) SessionContextFactory.getInstance()
										.getAttribute(AtributesSession.USUARIO_LOGADO.getValue()),
								null, null, false, false);
				calculaProgresso(projetos);
				break;

			default:
				break;

			}
		}
	}

	public String corAtraso(Historico historico) {
		if (historico.isTarefaAtrasada()) {
			return "red";
		}
		return null;
	}

	public void filtrarHistoricoPorData() {
		if (this.periodoHistorico != null) {
			Calendar calendar = Calendar.getInstance();

			Calendar calendarOntem = Calendar.getInstance();
			calendarOntem.setTime(new Date());
			calendarOntem.set(Calendar.DAY_OF_MONTH, calendarOntem.get(Calendar.DAY_OF_MONTH) - 1);

			switch (this.periodoHistorico) {
			case DIA:
				this.historicos = DaoHistorico.historicos(false, false, false, false,
						(Usuario) SessionContextFactory.getInstance()
								.getAttribute(AtributesSession.USUARIO_LOGADO.getValue()),
						calendarOntem.getTime(), new Date());
				break;

			case SEMANA:
				calendar.setTime(new Date());
				calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 8);

				this.historicos = DaoHistorico.historicos(false, true, false, false,
						(Usuario) SessionContextFactory.getInstance()
								.getAttribute(AtributesSession.USUARIO_LOGADO.getValue()),
						calendarOntem.getTime(), calendar.getTime());
				break;

			case MES:
				calendar.setTime(new Date());
				calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);

				this.historicos = DaoHistorico.historicos(false, true, false, false,
						(Usuario) SessionContextFactory.getInstance()
								.getAttribute(AtributesSession.USUARIO_LOGADO.getValue()),
						calendarOntem.getTime(), calendar.getTime());
				break;

			case TODO_PERIODO:
				this.historicos = DaoHistorico.historicos(false, false, false, false, (Usuario) SessionContextFactory
						.getInstance().getAttribute(AtributesSession.USUARIO_LOGADO.getValue()), null, null);
				break;

			default:
				break;

			}
		}

	}

	public void adicionaCheckPoint() {

		this.projetoSelecionado.getCheckPoint().setProjeto(this.projetoSelecionado);
		if (DaoProjeto.salvar(this.projetoSelecionado)) {

			this.projetos = DaoProjeto
					.projetos(true,
							(Usuario) SessionContextFactory.getInstance()
									.getAttribute(AtributesSession.USUARIO_LOGADO.getValue()),
							null, null, false, false);

			calculaProgresso(this.projetos);
			this.projetoSelecionado = DaoProjeto.buscaProjeto(this.projetoSelecionado.getNomeProjeto());

			Mensagem.sucesso("Alteração salva com sucesso!");

		} else
			Mensagem.falha("Não foi possível salvar o checkpoint");

	}

	public void atualizaHistoricoSelecionado() {
		if (DaoHistorico.salvaHistorico(historicoSelecionado)) {

			historicoSelecionado = DaoHistorico.buscarHistorico(historicoSelecionado.getId());

			desabilitarCamposHistorico = historicoSelecionado.isTarefaAtrasada()
					&& (historicoSelecionado.getJustificativaAtraso().equals("")
							|| historicoSelecionado.getJustificativaAtraso() == null);

			this.historicos = DaoHistorico.historicos(false, false, false, false, (Usuario) SessionContextFactory
					.getInstance().getAttribute(AtributesSession.USUARIO_LOGADO.getValue()), null, null);

			Mensagem.sucesso("Histórico atualizado com sucesso");
		} else {
			Mensagem.falha("Erro ao atualizar histórico");
		}

	}

	@SuppressWarnings("deprecation")
	public void onRowSelect(SelectEvent event) {
		this.historicoSelecionado = (Historico) event.getObject();

		desabilitarCamposHistorico = historicoSelecionado.isTarefaAtrasada()
				&& (historicoSelecionado.getJustificativaAtraso() == null
						|| historicoSelecionado.getJustificativaAtraso().equals(""));
		onTipoAcaoChange();

		org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('HistoricoDialog').show()");
	}

	public void calculaProgresso(List<Projeto> projetos) {
		for (Projeto projeto : projetos) {
			double progresso = 0;
			double concluido = 0;

			if (projeto.getCheckPoint() == null) {
				projeto.setCheckPoint(new CheckPoint());
				break;
			}

			if (projeto.getCheckPoint().isColetaLogs())
				concluido += 1;
			if (projeto.getCheckPoint().isCapacityPlanner())
				concluido += 1;
			if (projeto.getCheckPoint().isEntregaRelatório())
				concluido += 1;
			if (projeto.getCheckPoint().isInstrumentacao())
				concluido += 1;
			if (projeto.getCheckPoint().isLevantamentoRequisitos())
				concluido += 1;
			if (projeto.getCheckPoint().isPerformance())
				concluido += 1;
			if (projeto.getCheckPoint().isReuniaoEntendimento())
				concluido += 1;
			if (projeto.getCheckPoint().isStresTest())
				concluido += 1;

			if (concluido == 0) {
				projeto.getCheckPoint().setProgresso(0);
			} else {
				progresso = (concluido * 100) / 8;
				projeto.getCheckPoint().setProgresso(progresso);
			}
		}

	}

	public void onTipoAcaoChange() {
		this.acao = new HashMap<AcaoHistorico, AcaoHistorico>();
		if (historicoSelecionado.getTipoAcao().getTipoAcao().equals(TipoAcaoHistorico.EMAIL.getTipoAcao())) {
			this.acao.put(AcaoHistorico.EMAIL_RECEBIDO, AcaoHistorico.EMAIL_RECEBIDO);
			this.acao.put(AcaoHistorico.EMAILENVIADO, AcaoHistorico.EMAILENVIADO);
		}
		if (historicoSelecionado.getTipoAcao().getTipoAcao().equals(TipoAcaoHistorico.REUNIAO.getTipoAcao())) {
			this.acao.put(AcaoHistorico.REUNIAO_AGENDADA, AcaoHistorico.REUNIAO_AGENDADA);
			this.acao.put(AcaoHistorico.REUNIAO_REALIZADA, AcaoHistorico.REUNIAO_REALIZADA);
		}
		if (historicoSelecionado.getTipoAcao().getTipoAcao().equals(TipoAcaoHistorico.TELEFONEMA.getTipoAcao())) {
			this.acao.put(AcaoHistorico.TELEFONEMA_REALIZADO, AcaoHistorico.TELEFONEMA_REALIZADO);
			this.acao.put(AcaoHistorico.TELEFONEMA_RECEBIDO, AcaoHistorico.TELEFONEMA_RECEBIDO);
		}
	}

	public List<Usuario> usuarioAutoComplete(String complete) {
		List<Usuario> usuariosFiltrados = new ArrayList<Usuario>();
		for (Usuario usuario : usuariosAutoComplete) {
			if (usuario.getNomeUsuario().toLowerCase().contains(complete.toLowerCase()))
				usuariosFiltrados.add(usuario);
		}
		return usuariosFiltrados;
	}

	public Periodo[] getPeriodo() {
		return Periodo.values();
	}

	public StatusProjeto[] getStatus() {
		return StatusProjeto.values();
	}

	public StatusHistorioco[] getStatusHistorico() {
		return StatusHistorioco.values();
	}

	public Map<AcaoHistorico, AcaoHistorico> getAcaoHistorico() {
		return this.acao;
	}

	public TipoAcaoHistorico[] getTipoAcaoHistorico() {
		return TipoAcaoHistorico.values();
	}

	public Periodo getPeriodoProjeto() {
		return periodoProjeto;
	}

	public void setPeriodoProjeto(Periodo periodoProjeto) {
		this.periodoProjeto = periodoProjeto;
	}

	public Periodo getPeriodoHistorico() {
		return periodoHistorico;
	}

	public void setPeriodoHistorico(Periodo periodoHistorico) {
		this.periodoHistorico = periodoHistorico;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public List<Historico> getHistoricos() {
		return historicos;
	}

	public void setHistoricos(List<Historico> historicos) {
		this.historicos = historicos;
	}

	public List<Historico> getHistoricosAtrasados() {
		return historicosAtrasados;
	}

	public void setHistoricosAtrasados(List<Historico> historicosAtrasados) {
		this.historicosAtrasados = historicosAtrasados;
	}

	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}

	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
	}

	public Historico getHistoricoSelecionado() {
		return historicoSelecionado;
	}

	public void setHistoricoSelecionado(Historico historicoSelecionado) {
		this.historicoSelecionado = historicoSelecionado;
	}

	public boolean isDesabilitarCamposHistorico() {
		return desabilitarCamposHistorico;
	}

	public void setDesabilitarCamposHistorico(boolean desabilitarCamposHistorico) {
		this.desabilitarCamposHistorico = desabilitarCamposHistorico;
	}

	@SuppressWarnings("static-access")
	public int getTotalProjetos() {
		// Total projeto cadastrado no sistema (ativos, inativos, cancelado...)
		return new DaoProjeto().projetos(false, null, null, null, false, false).size();
	}

	@SuppressWarnings("static-access")
	public int getTotalProjetosEmAndamento() {
		// Total projeto cadastrado no sistema ativo
		return new DaoProjeto().projetos(true, null, null, null, false, false).size();
	}

	@SuppressWarnings("static-access")
	public int getTotalProjetosEntregues() {
		// Total projeto entregues
		return new DaoProjeto().projetos(false, null, null, null, false, true).size();
	}

	@SuppressWarnings("static-access")
	public int getTotalDeClientesAtivos() {
		// Total de clientes
		return new DaoCliente().listarClientesAtivos().size();
	}

	public Map<AcaoHistorico, AcaoHistorico> getAcao() {
		return acao;
	}

	public void setAcao(Map<AcaoHistorico, AcaoHistorico> acao) {
		this.acao = acao;
	}

}
