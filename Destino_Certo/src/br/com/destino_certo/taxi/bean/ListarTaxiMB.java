package br.com.destino_certo.taxi.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.destino_certo.taxi.modelo.Taxi;
import br.com.destino_certo.util.autenticar.FacesContextUtil;
import br.com.destino_certo.util.fachada.Fachada;

@ManagedBean
@ViewScoped
public class ListarTaxiMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fachada fachada;
	private Taxi taxi;
	private List<Taxi> listaTaxis;
	private List<Taxi> listaSelecionada;
	
	public ListarTaxiMB() {
		// TODO Auto-generated constructor stub
		fachada = Fachada.getInstance();
		listaTaxis = fachada.taxiListar();
		taxi = new Taxi();
	}
	
	public List<Taxi> listaAtiva(List<Taxi> lista){
		List<Taxi> listaFiltrada = new ArrayList<Taxi>();
		for(Taxi taxi:lista){
			if(!taxi.getNome().toLowerCase().equals("contorno")){
				listaFiltrada.add(taxi);
			}
		}
		return listaFiltrada;
	}

	public List<Taxi> getListaTaxis() {
		return listaAtiva(listaTaxis);
	}
	
	public void selecionar(Taxi taxi){
		this.taxi = taxi;
	}
	
	public void editar(){
		FacesContextUtil.setMessageInformacao("Info", fachada.taxiEditar(taxi));
	}
	
	public void remover(){
		FacesContextUtil.setMessageInformacao("Info", fachada.taxiRemover(taxi));
		listaTaxis.remove(taxi);
	}

	public void setListaTaxis(List<Taxi> listaTaxis) {
		this.listaTaxis = listaTaxis;
	}

	public Taxi getTaxi() {
		return taxi;
	}

	public void setTaxi(Taxi taxi) {
		this.taxi = taxi;
	}

	public List<Taxi> getListaSelecionada() {
		return listaSelecionada;
	}

	public void setListaSelecionada(List<Taxi> listaSelecionada) {
		this.listaSelecionada = listaSelecionada;
	}
	
	

}
