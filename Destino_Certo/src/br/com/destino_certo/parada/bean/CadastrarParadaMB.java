package br.com.destino_certo.parada.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.destino_certo.itinerario.modelo.Itinerario;
import br.com.destino_certo.parada.modelo.Parada;
import br.com.destino_certo.util.fachada.Fachada;

@ManagedBean
@ViewScoped
public class CadastrarParadaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fachada fachada;
	private Parada parada;
	private MapModel mapa;
	private Long idItinerario;
	private Itinerario itinerario;
	private List<Itinerario> listaItinerarios;
	private List<Parada>listaParadas;
	
	public CadastrarParadaMB() {
		// TODO Auto-generated constructor stub
		fachada = Fachada.getInstance();
		parada = new Parada();
		listaItinerarios = fachada.itinerarioListar();
	}
	
	 public void novo(PointSelectEvent event) {
	        LatLng coord = event.getLatLng();
	        parada.setLatitude(coord.getLat());
	        parada.setLongitude(coord.getLng());
	    }
	 
	 public void selecionarItinerario(){
		 itinerario = fachada.itinerarioProcurar(idItinerario);
		 System.out.println(itinerario.getNome());
	 }
	 
	 public void gravar() {
	        parada.setItinerario(itinerario);
		    fachada.paradaCadastrar(parada);
	        parada = new Parada();
	        carregarLocais();
	    }

	public List<Itinerario> getListaItinerarios() {
		return listaItinerarios;
	}
	
	public List<Parada> listaParada(){
		List<Parada> listaP;
		if(itinerario==null){
			listaP = new ArrayList<Parada>();
		}else{
			listaP = fachada.paradaListar("itinerario.numero", itinerario.getNumero());
		}
		return listaP;
	}
		
	private void carregarLocais() {
        mapa = new DefaultMapModel();
        List<Parada> locaisList = listaParada();
        for (Parada pa : locaisList) {
            mapa.addOverlay(
                    new Marker(
                    new LatLng(pa.getLatitude(), pa.getLongitude()),
                    pa.getNome()));
        }
    }
	
	  public MapModel getLocais() {
	        if (listaParadas == null) {
	            carregarLocais();
	        }
	        return mapa;
	    }

	public void setListaItinerarios(List<Itinerario> listaItinerarios) {
		this.listaItinerarios = listaItinerarios;
	}

	public Parada getParada() {
		return parada;
	}

	public void setParada(Parada parada) {
		this.parada = parada;
	}

	public List<Parada> getListaParadas() {
		return listaParadas;
	}

	public void setListaParadas(List<Parada> listaParadas) {
		this.listaParadas = listaParadas;
	}

	public Long getIdItinerario() {
		return idItinerario;
	}

	public void setIdItinerario(Long idItinerario) {
		this.idItinerario = idItinerario;
	}
	
	

}
