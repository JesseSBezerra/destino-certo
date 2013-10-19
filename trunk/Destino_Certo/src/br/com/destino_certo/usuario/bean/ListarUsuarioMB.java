package br.com.destino_certo.usuario.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.destino_certo.usuario.modelo.Usuario;
import br.com.destino_certo.util.autenticar.FacesContextUtil;
import br.com.destino_certo.util.fachada.Fachada;

@ManagedBean
@ViewScoped
public class ListarUsuarioMB {
	
	private List<Usuario> lista;
	private List<Usuario> listaSelecionada;
	private Fachada fachada;
	private Usuario usuario;
	
	public ListarUsuarioMB() {
		// TODO Auto-generated constructor stub
    fachada = Fachada.getInstance();
    lista = fachada.listar();
	}
	
	public void selecionar(Usuario usuario){
		this.usuario = usuario;
	}
	
	public void remover(){
		lista.remove(usuario);
		FacesContextUtil.setMessageInformacao("Info", fachada.usuarioRemover(usuario));
		usuario = new Usuario();
	}
	
	public void editar(){
		FacesContextUtil.setMessageInformacao("Info", fachada.usuarioEditar(usuario));
		usuario = new Usuario();
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaSelecionada() {
		return listaSelecionada;
	}
	

	public void setListaSelecionada(List<Usuario> listaSelecionada) {
		this.listaSelecionada = listaSelecionada;
	}

}
