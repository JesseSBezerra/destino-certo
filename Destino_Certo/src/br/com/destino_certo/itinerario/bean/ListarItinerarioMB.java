package br.com.destino_certo.itinerario.bean;

import java.io.Serializable;
import java.util.List;

import br.com.destino_certo.itinerario.modelo.Itinerario;
import br.com.destino_certo.util.fachada.Fachada;

public class ListarItinerarioMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Itinerario> listaItinerario;
	private Fachada fachada;
	
	public ListarItinerarioMB() {
		// TODO Auto-generated constructor stub
		fachada = Fachada.getInstance();
		this.listaItinerario = fachada.itinerarioListar("ativo", true);
	}

	public List<Itinerario> getListaItinerario() {
		return listaItinerario;
	}

	public void setListaItinerario(List<Itinerario> listaItinerario) {
		this.listaItinerario = listaItinerario;
	}
	
	
	

}
