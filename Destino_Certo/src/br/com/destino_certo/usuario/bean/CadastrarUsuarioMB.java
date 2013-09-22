package br.com.destino_certo.usuario.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.destino_certo.usuario.modelo.Usuario;
import br.com.destino_certo.util.autenticar.FacesContextUtil;
import br.com.destino_certo.util.fachada.Fachada;

@ManagedBean
@ViewScoped
public class CadastrarUsuarioMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Fachada fachada;
	public CadastrarUsuarioMB() {
		// TODO Auto-generated constructor stub
		usuario = new Usuario();
		fachada = Fachada.getInstance();		
	}
	
	public void cadastrarUsuario(){
		if(!usuario.getNome().trim().equals("") || !usuario.getEmail().trim().equals("") || !usuario.getSenha().trim().equals("") || !usuario.getLogin().trim().equals("")){
		FacesContextUtil.setMessageInformacao("Info", fachada.usuarioCadastrar(usuario));
		limpar();
		}else{
			FacesContextUtil.setMessageInformacao("Info", "há campos obrigatórios vazios");
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	private void limpar(){
		usuario = new Usuario();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
