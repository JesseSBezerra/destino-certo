package br.com.destino_certo.util.fachada;

import java.util.List;

import br.com.destino_certo.usuario.controlador.ControladorUsuario;
import br.com.destino_certo.usuario.modelo.IRepositorioUsuario;
import br.com.destino_certo.usuario.modelo.RepositorioUsuario;
import br.com.destino_certo.usuario.modelo.Usuario;

public class Fachada {

	private static Fachada instance;
	private ControladorUsuario controladorUsuario;
	private IRepositorioUsuario iRepositorioUsuario;
	
	public static Fachada getInstance(){
		if(instance == null){
			instance = new Fachada();
		}
		return instance;
			
	}
	
	private Fachada() {
		// TODO Auto-generated constructor stub
		instanciarRepositorios();
	}
	
	private void instanciarRepositorios(){
        iRepositorioUsuario = new RepositorioUsuario();
		controladorUsuario = new ControladorUsuario(iRepositorioUsuario);
	}
	
	public void usuarioCadastrar(Usuario usuario){
		controladorUsuario.cadastrar(usuario);
	}
	
	public Usuario usuarioProcurar(String login){
		return controladorUsuario.procurar(login);
	}
	
	public List<Usuario> listar(){
		return controladorUsuario.listar();
	}

}
