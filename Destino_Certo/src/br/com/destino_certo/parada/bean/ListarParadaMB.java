package br.com.destino_certo.parada.bean;

import java.io.Serializable;
import java.util.ArrayList;
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
		parada = new Parada();
	}
	
	public List<Parada> listaAtiva(List<Parada> lista){
		List<Parada> listaFiltrada = new ArrayList<Parada>();
		for(Parada parada:lista){
			if(!parada.getNome().toLowerCase().equals("contorno")){
				listaFiltrada.add(parada);
			}
		}
		return listaFiltrada;
	}

	public List<Parada> getListaParadas() {
		return listaAtiva(listaParadas);
	}
	
	public void selecionar(Parada parada){
		this.parada = parada;
	}
	
	public void editar(){
		FacesContextUtil.setMessageInformacao("Info", fachada.paradaEditar(parada));
	}
	
	public void remover(){
		FacesContextUtil.setMessageInformacao("Info", fachada.paradaRemover(parada));
		listaParadas.remove(parada);
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
