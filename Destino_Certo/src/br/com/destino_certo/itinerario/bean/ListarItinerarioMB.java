package br.com.destino_certo.itinerario.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.destino_certo.itinerario.modelo.Itinerario;
import br.com.destino_certo.util.autenticar.FacesContextUtil;
import br.com.destino_certo.util.fachada.Fachada;

@ManagedBean
@ViewScoped
public class ListarItinerarioMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Itinerario> listaItinerario;
	private List<Itinerario> itinerarioSelecionado;
	private Itinerario itinerario;
	private Fachada fachada;
	
	public ListarItinerarioMB() {
		// TODO Auto-generated constructor stub
		fachada = Fachada.getInstance();
		itinerario = new Itinerario();
		this.listaItinerario = fachada.itinerarioListar("ativo", true);
	}

	public Itinerario getItinerario() {
		return itinerario;
	}
	
	public void selecionar(Itinerario itinerario){
		this.itinerario = itinerario;
	}
	
	public void editar(){
		FacesContextUtil.setMessageInformacao("info", fachada.itinerarioEditar(itinerario));
		itinerario = new Itinerario();
	}
	
	public void remover(){
		FacesContextUtil.setMessageInformacao("info", fachada.itinerarioRemover(itinerario));
		listaItinerario.remove(itinerario);
		itinerario = new Itinerario();
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	public List<Itinerario> getListaItinerario() {
		return listaItinerario;
	}

	public void setListaItinerario(List<Itinerario> listaItinerario) {
		this.listaItinerario = listaItinerario;
	}

	public List<Itinerario> getItinerarioSelecionado() {
		return itinerarioSelecionado;
	}

	public void setItinerarioSelecionado(List<Itinerario> itinerarioSelecionado) {
		this.itinerarioSelecionado = itinerarioSelecionado;
	}
	
	
	

}
