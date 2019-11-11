package dominio;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import enumeradores.Status;
import enumeradores.UF;

@Entity(name = "Cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codCliente;

	@Column(nullable = false, length = 60, unique = true)
	private String nomeCliente;

	@Column(nullable = false, length = 15)
	private String telCliente;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private Status statusCliente;

	@Column(length = 60)
	private String enderecoCliente;

	@Column(length = 5)
	private Long numEndCliente;

	@Column(length = 30)
	private String bairroCliente;

	@Column(length = 9)
	private String cepCliente;

	@Column(length = 60)
	private String cidadeCliente;

	@Enumerated(EnumType.STRING)
	@Column(length = 2)
	private UF uf;

	@Column
	private Date dataCadastro;

	@Column(length = 1000)
	private String obsCLiente;

	@OneToMany(mappedBy="cliente", fetch=FetchType.EAGER)
	private List<Projeto> projetos;
	
	public Cliente() {
	}	

	public Long getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTelCliente() {
		return telCliente;
	}

	public void setTelCliente(String telCliente) {
		this.telCliente = telCliente;
	}

	public Status getStatusCliente() {
		return statusCliente;
	}

	public void setStatusCliente(Status statusCliente) {
		this.statusCliente = statusCliente;
	}

	public String getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public Long getNumEndCliente() {
		return numEndCliente;
	}

	public void setNumEndCliente(Long numEndCliente) {
		this.numEndCliente = numEndCliente;
	}

	public String getBairroCliente() {
		return bairroCliente;
	}

	public void setBairroCliente(String bairroCliente) {
		this.bairroCliente = bairroCliente;
	}

	public String getCepCliente() {
		return cepCliente;
	}

	public void setCepCliente(String cepCliente) {
		this.cepCliente = cepCliente;
	}

	public String getCidadeCliente() {
		return cidadeCliente;
	}

	public void setCidadeCliente(String cidadeCliente) {
		this.cidadeCliente = cidadeCliente;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getObsCLiente() {
		return obsCLiente;
	}

	public void setObsCLiente(String obsCLiente) {
		this.obsCLiente = obsCLiente;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
}
