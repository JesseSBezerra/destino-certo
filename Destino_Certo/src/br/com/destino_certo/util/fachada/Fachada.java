package br.com.destino_certo.util.fachada;

import java.util.List;

import br.com.destino_certo.onibus.controlador.ControladorOnibus;
import br.com.destino_certo.onibus.modelo.IRepositorioOnibus;
import br.com.destino_certo.onibus.modelo.Onibus;
import br.com.destino_certo.onibus.modelo.RepositorioOnibus;
import br.com.destino_certo.usuario.controlador.ControladorUsuario;
import br.com.destino_certo.usuario.modelo.IRepositorioUsuario;
import br.com.destino_certo.usuario.modelo.RepositorioUsuario;
import br.com.destino_certo.usuario.modelo.Usuario;

public class Fachada {

	private static Fachada instance;
	private ControladorUsuario controladorUsuario;
	private IRepositorioUsuario iRepositorioUsuario;
	private ControladorOnibus controladorOnibus;
	private IRepositorioOnibus iRepositorioOnibus;
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
		iRepositorioOnibus = new RepositorioOnibus();
		controladorOnibus = new ControladorOnibus(iRepositorioOnibus);
	}
	
	//TODO USUARIO
	
	public void usuarioCadastrar(Usuario usuario){
		controladorUsuario.cadastrar(usuario);
	}
	
	public String usuarioRemover(Usuario usuario){
		return controladorUsuario.remover(usuario.getLogin());
	}
	
	public String usuarioEditar(Usuario usuario){
		return controladorUsuario.editar(usuario);			
	}
	
	public Usuario usuarioProcurar(String login){
		return controladorUsuario.procurar(login);
	}
	
	public List<Usuario> listar(){
		return controladorUsuario.listar();
	}
	
	//TODO ONIBUS
	
	public String onibusCadastrar(Onibus onibus){
		return controladorOnibus.cadastrar(onibus);
	}
	
	public String onibusRemover(Onibus onibus){
		return controladorOnibus.remover(onibus);
	}
	
	public String onibusEditar(Onibus onibus){
		return controladorOnibus.editar(onibus);			
	}
	
	public Onibus onibusProcurar(Long numero){
		return controladorOnibus.procurar(numero);
	}
	
	public List<Onibus> onibusListar(){
		return controladorOnibus.listar();
	}
	
	public List<Onibus> onibusListar(String nomeCampo, boolean valorCampo){
		return controladorOnibus.listar(nomeCampo,valorCampo);
	}

}
