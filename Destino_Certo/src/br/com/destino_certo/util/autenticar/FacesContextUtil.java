package br.com.destino_certo.util.autenticar;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.hibernate.Session;

public class FacesContextUtil {

	private static final String HIBERNATE_SESSION = "hibernate_session";
	
	//Metodo que seta a sessão da requisiçaõ
	public static void setRequestSession(Session s) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(HIBERNATE_SESSION, s);
	}
	
	//Metodo que pega a sessão da requisição
	public static Session getRequestSession() {
		return (Session) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(HIBERNATE_SESSION);
	}
	
	//Metodo responsavel por setar mensagens do facemessage
	public static void setMessageErro(String titulo, String mensagem){
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, mensagem);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	public static void setMessageInformacao(String titulo, String mensagem){
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}	
	
	//Metodo responsavel por setar objetos na sessao
	@SuppressWarnings("unchecked")
	public static void setSessionAttribute(String attributeName, Object attributeValue) {    
		try {    
		    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();    
		    if (ec != null){    
		        @SuppressWarnings("rawtypes")
				Map attrMap = ec.getSessionMap();       
		        if (attrMap != null) {    
		            attrMap.put(attributeName, attributeValue);    
		        }     
		    }     
		} catch (Exception e) {    
		    e.printStackTrace();    
		}    
		} 	
	
	//Metodo responsavel por pegar objetos na sessão
	public static Object getSessionAttribute(String nomeAtributo){		
		try {    
		    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();    
		    if (ec != null){    
		        @SuppressWarnings("rawtypes")
				Map attrMap = ec.getSessionMap();       
		        if (attrMap != null) {    
		            return attrMap.get(nomeAtributo);    
		        } else {    
		            return null;    
		        }    
		    } else {    
		        return null;    
		    }    
		} catch (Exception e) {    
		    System.out.println("Erro ao tentar capturar o atributo da sessão: "+e.getMessage());    
		    return null;    
		} 
	}
	
	//Metodo que seta a navegação(faz navegação forçada)
	public static void setNavegacao(String outcome){
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, outcome);
	}
	
	public static void logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		setNavegacao("login");
	}
	
}
