package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensagem {
	
	public static void sucesso(String menssagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso",menssagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
		
	}
	
	public static void falha(String menssagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atenção",menssagem));		
	}
}
