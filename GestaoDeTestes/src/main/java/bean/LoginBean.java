package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.DaoLogin;
import dao.DaoUsuario;
import dominio.Usuario;
import enumeradores.AtributesSession;
import enumeradores.DireitoUsuario;
import factory.SessionContextFactory;

@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean {
	Usuario usuario;

	public LoginBean() {
		usuario = new Usuario();
	}

	public String logar() {
		if (usuario.getEmail() == null || usuario.getEmail().equals("")) {
			FacesContext context = FacesContext.getCurrentInstance();	         
	        context.addMessage(null, new FacesMessage("Aviso",  "Preencha o campo e-mail") );
		}
		
		if (usuario.getPaswUsuario() == null || usuario.getPaswUsuario().equals("")) {
			FacesContext context = FacesContext.getCurrentInstance();	         
	        context.addMessage(null, new FacesMessage("Aviso",  "Preencha o campo senha") );
		}
		
		if (DaoLogin.logar(usuario)) {
			usuario = DaoUsuario.buscaUsuario(usuario.getEmail());

			SessionContextFactory.getInstance().setAttribute(AtributesSession.USUARIO_LOGADO.getValue(), usuario);

			if (usuario.getDireitoUsuario().equals(DireitoUsuario.GERENTE))
				return "/dashboard2.xhtml?faces-redirect=true";

			return "/dashboard.xhtml?faces-redirect=true";

		} else {
			FacesContext context = FacesContext.getCurrentInstance();	         
	        context.addMessage(null, new FacesMessage("Aviso",  "Senha ou E-mail incorreto.") );
		}
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
