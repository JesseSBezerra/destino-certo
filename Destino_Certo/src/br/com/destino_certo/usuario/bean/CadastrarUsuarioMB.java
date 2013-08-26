package br.com.destino_certo.usuario.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.destino_certo.usuario.modelo.Usuario;
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
		fachada.usuarioCadastrar(usuario);
		limpar();
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
