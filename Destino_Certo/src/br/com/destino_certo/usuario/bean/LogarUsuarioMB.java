package br.com.destino_certo.usuario.bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import br.com.destino_certo.usuario.modelo.Usuario;
import br.com.destino_certo.util.autenticar.FacesContextUtil;
import br.com.destino_certo.util.fachada.Fachada;

@ManagedBean
@RequestScoped
public class LogarUsuarioMB {

	private String login;
	private String senha;
	private Usuario usuario;
	private Fachada fachada;
	public LogarUsuarioMB() {
		// TODO Auto-generated constructor stub
		usuario = new Usuario();
		fachada = Fachada.getInstance();
	}
	
	public void logar(){
		System.out.println(login+"\n"+senha);
		usuario = fachada.usuarioProcurar(login);
		if(usuario != null){
			System.out.println(usuario.getNome());
			if(usuario.getSenha().equals(senha.trim())){
				FacesContextUtil.setSessionAttribute("usuario", usuario);
				FacesContextUtil.setNavegacao("usuario_cadastro");
			}else{
				FacesContextUtil.setMessageInformacao("Info", "login ou senha inválido");
			}
		}else{
			FacesContextUtil.setMessageInformacao("Info", "login ou senha inválido");
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
