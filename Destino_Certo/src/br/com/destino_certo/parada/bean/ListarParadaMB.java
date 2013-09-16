package br.com.destino_certo.parada.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.destino_certo.parada.modelo.Parada;
import br.com.destino_certo.util.autenticar.FacesContextUtil;
import br.com.destino_certo.util.fachada.Fachada;

@ManagedBean
@ViewScoped
public class ListarParadaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fachada fachada;
	private Parada parada;
	private List<Parada> listaParadas;
	private List<Parada> listaSelecionada;
	
	public ListarParadaMB() {
		// TODO Auto-generated constructor stub
		fachada = Fachada.getInstance();
		listaParadas = fachada.paradaListar();
	}

	public List<Parada> getListaParadas() {
		return listaParadas;
	}
	
	public void removerParada(Parada parada){
		FacesContextUtil.setMessageInformacao("Info", fachada.paradaRemover(parada));
	}

	public void setListaParadas(List<Parada> listaParadas) {
		this.listaParadas = listaParadas;
	}

	public Parada getParada() {
		return parada;
	}

	public void setParada(Parada parada) {
		this.parada = parada;
	}

	public List<Parada> getListaSelecionada() {
		return listaSelecionada;
	}

	public void setListaSelecionada(List<Parada> listaSelecionada) {
		this.listaSelecionada = listaSelecionada;
	}
	
	

}
