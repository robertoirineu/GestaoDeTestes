package bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.DaoCliente;
import dominio.Cliente;
import enumeradores.Status;
import enumeradores.UF;
import util.Mensagem;

@ManagedBean(name = "cadastroClienteBean")
@ViewScoped
public class ClienteBean {
	private Cliente cliente;
	private String clientePesquisado;
	private List<Cliente> clientesPesquisados;
	private List<Cliente> clientes;

	public ClienteBean() {

		this.cliente = new Cliente();
	}

	@PostConstruct
	public void init() {
		this.clientes = (ArrayList<Cliente>) DaoCliente.listarClientes();
	}

	public void salvarCliente() {
		boolean jaExisteNomeCliente = false;
		try {
			for (Cliente cli : this.clientes) {
				if (cli.getNomeCliente().equals(cliente.getNomeCliente())) {
					jaExisteNomeCliente = true;
					break;
				}
			}
			if (jaExisteNomeCliente && cliente.getCodCliente() == null) {
				Mensagem.falha(
						"Cliente ja está cadastrado com esse nome\ncaso deseje altera-lo faça uma busca\nNão foi possível salvar");
			} else {
				DaoCliente.salvar(this.cliente);
				this.limparCampo();
				this.clientes = DaoCliente.listarClientes();

				Mensagem.sucesso("Cadastro salvo com sucesso!");
			}

		} catch (Exception e) {
			Mensagem.falha("Não foi possível salvar/n" + e.toString());
		}
	}

	public void limparCampo() {
		DaoCliente.detachCliente(this.cliente);
		this.cliente = new Cliente();
		this.clientesPesquisados = null;
	}

	public void buscarClientes() {
		this.limparCampo();
		this.clientesPesquisados = new ArrayList<Cliente>();
		if (this.clientePesquisado.length() < 1 || this.clientePesquisado == null) {
			Mensagem.falha("Prencha o nome do cliente para realizar a pesquisa");
		} else {
			for (Cliente nome : this.clientes) {
				if (nome.getNomeCliente().toLowerCase().contains(this.clientePesquisado.toLowerCase())) {
					Cliente cli = new Cliente();
					cli = nome;
					clientesPesquisados.add(cli);
				}
			}
			if (clientesPesquisados.size() == 0) {
				Mensagem.falha("Cliente não existe");
			}
			clientePesquisado = null;
		}
	}

	public Status[] getStatus() {
		return Status.values();
	}

	public UF[] getUF() {
		return UF.values();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getClientePesquisado() {
		return clientePesquisado;
	}

	public void setClientePesquisado(String clientePesquisado) {
		this.clientePesquisado = clientePesquisado;
	}

	public List<Cliente> getClientesPesquisados() {
		return clientesPesquisados;
	}

	public void setClientesPesquisados(List<Cliente> clientesPesquisados) {
		this.clientesPesquisados = clientesPesquisados;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
