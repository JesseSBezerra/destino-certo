package br.com.destino_certo.usuario.controlador;


import java.util.List;

import br.com.destino_certo.usuario.modelo.IRepositorioUsuario;
import br.com.destino_certo.usuario.modelo.Usuario;
import br.com.destino_certo.usuario.modelo.UsuarioJaCadastradoException;
import br.com.destino_certo.usuario.modelo.UsuarioNaoEncontradoExcepition;

public class ControladorUsuario {

	private IRepositorioUsuario iRepositorioUsuario;
	public ControladorUsuario(IRepositorioUsuario iRepositorioUsuario) {
		// TODO Auto-generated constructor stub
		this.iRepositorioUsuario = iRepositorioUsuario;
	}
	
	public String cadastrar(Usuario usuario){
		String mensagem = "";
		try {
			if(existe(usuario)){
			mensagem = "usuario já cadastrado!";
			}else{
				iRepositorioUsuario.cadastrar(usuario);
				mensagem = "Usuario cadastrado com sucesso!";
			}
		} catch (UsuarioJaCadastradoException e) {
			// TODO Auto-generated catch block
	    	mensagem = e.getMessage();
		}
		return mensagem;
	}
	
	public Usuario procurar(String login){
		Usuario usuario = null;
		try {
			usuario = iRepositorioUsuario.procurar(login);
		} catch (UsuarioNaoEncontradoExcepition e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		} 
		return usuario;
	}
	
	public String remover(String login){
		String mensagem = null;
		try {
			Usuario usuario = procurar(login);
			iRepositorioUsuario.remover(usuario);
			mensagem = "removido com sucesso!";
		} catch (UsuarioNaoEncontradoExcepition e) {
			// TODO Auto-generated catch block
			mensagem = e.getMessage();
		}
		return mensagem;
	}
	
	public String editar(Usuario usuario){
		String mensagem = "";
		try {
			if(existe(usuario)){
			iRepositorioUsuario.editar(usuario);
			mensagem = "Editado com sucesso!";
			}else{
				mensagem = "Erro ao editar";
			}
		} catch (UsuarioNaoEncontradoExcepition e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		} 
		return mensagem;
	}
	
	private boolean existe(Usuario usuario){
		boolean existe = false;
			try {
				Usuario usuarioExiste = procurar(usuario.getLogin());
				if(usuarioExiste != null){
					existe = true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			return existe;
	}
	
	public List<Usuario> listar(){
		return iRepositorioUsuario.listarUsuarios();
	}
}
