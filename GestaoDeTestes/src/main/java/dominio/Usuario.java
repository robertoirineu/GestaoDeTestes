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
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import enumeradores.CargoUsuario;
import enumeradores.DireitoUsuario;
import enumeradores.NivelUsuario;
import enumeradores.Status;
import util.Encode;

@Entity(name = "Usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codUsuario;

	@Column(nullable = false, length = 60, unique = true)
	private String email;

	@Column(nullable = false, length = 60)
	private String paswUsuario;

	@Column(nullable = false, length = 60)
	private String nomeUsuario;

	@Column(updatable = false)
	private Date dataCadastro;
	
	@Lob
	private byte[] imagen;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 40)
	private CargoUsuario cargo;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private NivelUsuario nivel;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 40)
	private DireitoUsuario direitoUsuario;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private Status statusUsuario;

	@OneToMany(mappedBy = "responsavel", fetch = FetchType.LAZY)
	private List<Historico> historicos;

	public Usuario() {
	}

	public Long getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(Long codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPaswUsuario() {
		return paswUsuario;
	}

	public void setPaswUsuario(String paswUsuario) {
		this.paswUsuario = Encode.encrip(paswUsuario);
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public CargoUsuario getCargo() {
		return cargo;
	}

	public void setCargo(CargoUsuario cargo) {
		this.cargo = cargo;
	}

	public NivelUsuario getNivel() {
		return nivel;
	}

	public void setNivel(NivelUsuario nivel) {
		this.nivel = nivel;
	}

	public DireitoUsuario getDireitoUsuario() {
		return direitoUsuario;
	}

	public void setDireitoUsuario(DireitoUsuario direitoUsuario) {
		this.direitoUsuario = direitoUsuario;
	}

	public Status getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(Status statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public List<Historico> getHistoricos() {
		return historicos;
	}

	public void setHistoricos(List<Historico> historicos) {
		this.historicos = historicos;
	}
}
