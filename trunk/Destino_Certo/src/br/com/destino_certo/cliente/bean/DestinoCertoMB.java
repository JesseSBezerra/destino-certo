package br.com.destino_certo.cliente.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polyline;

import br.com.destino_certo.itinerario.modelo.Itinerario;
import br.com.destino_certo.parada.modelo.Parada;
import br.com.destino_certo.util.decode.Decode;
import br.com.destino_certo.util.fachada.Fachada;

@ManagedBean
@ViewScoped
public class DestinoCertoMB {

	
	private Fachada fachada;
	private Parada parada;
	private MapModel mapa;
	private Long idItinerario;
	private String posicao;
	private Itinerario itinerario;
	private List<Parada> listaParadas;
	private List<Parada> listaParada2;
	private List<Itinerario> listaItinerario;
	private Map<String, String> valores;
	
	private MapModel polylineModel;

	public DestinoCertoMB() {
		fachada = Fachada.getInstance();
		parada = new Parada();
		listaItinerario = fachada.itinerarioListar();
		posicao = "-8.04161,-34.89818";
		polylineModel = new DefaultMapModel();
		
	}

	public MapModel getPolylineModel() {
		return polylineModel;
	}
	
	 
	 public void selecionarItinerario(){
		 itinerario = fachada.itinerarioProcurar(idItinerario);
		 valores = Decode.consultaMaps(itinerario.getOrigem().getCodeBusca(), itinerario.getDestino().getCodeBusca());
		 carregarLocais();
		 carregarParadas(listaParada(itinerario));
	 }
	 
	 public void carregarParadas(){
		 listaParada2 = listaParada(itinerario);
	 }
	
	 public List<Parada> listaParada(Itinerario itinerario){
			List<Parada> listaP;
			if(itinerario==null){
				listaP = new ArrayList<Parada>();
			}else{
				listaP = fachada.paradaListar("itinerario.numero", itinerario.getNumero());
			}
			return listaP;
		}
	
	public List<Parada> atualizarParadas(List<Parada> lista){
		List<Parada> listaParada = new ArrayList<Parada>();
		for(Parada parada:lista){
			if(!parada.getNome().toLowerCase().equals("contorno")){
				listaParada.add(parada);
				polylineModel.addOverlay(
	                    new Marker(
	                    new LatLng(parada.getLatitude(), parada.getLongitude()),
	                    parada.getNome()));
			}
		}
		return listaParada;
	}
	
	public void carregarParadas(List<Parada> lista){
		for(Parada parada:lista){
			Marker marker = new Marker(new LatLng(parada.getLatitude(), parada.getLongitude()),parada.getNome());
			marker.setIcon("http://png-5.findicons.com/files/icons/903/travel/32/bus.png");	
			polylineModel.addOverlay(marker);
		}
	}

	
	private void carregarLocais() {
        polylineModel = new DefaultMapModel();
        Polyline polyline = new Polyline();
        for (br.com.destino_certo.util.decode.LatLng latLng : Decode.decodePolyLine(valores.get("poly"))) {
            LatLng coordenada = new LatLng(latLng.getLatitude(), latLng.getLongitude());
            polyline.getPaths().add(coordenada);
            
        }
        posicao = polyline.getPaths().get(0).getLat()+","+polyline.getPaths().get(0).getLng();
        Marker markerInicio = new Marker(polyline.getPaths().get(0));
        markerInicio.setIcon("http://www.google.com/intl/en_us/mapfiles/ms/micons/green-dot.png");
        Marker markerFim = new Marker(polyline.getPaths().get(polyline.getPaths().size()-1)); 
        markerFim.setIcon("http://www.google.com/intl/en_us/mapfiles/ms/micons/red-dot.png");
        polylineModel.addOverlay(markerFim);
        polylineModel.addOverlay(markerInicio);
        polyline.setStrokeWeight(3);
		polyline.setStrokeColor("#8B0000");
		polyline.setStrokeOpacity(0.7);
		polylineModel.addOverlay(polyline);
		
    }
	
	  public List<Parada> getListaParada2() {
		return listaParada2;
	}


	public void setListaParada2(List<Parada> listaParada2) {
		this.listaParada2 = listaParada2;
	}


	public MapModel getLocais() {
	        if (listaParadas == null) {
	            carregarLocais();
	        }
	        return mapa;
	    }


	public Parada getParada() {
		return parada;
	}

	public void setParada(Parada parada) {
		this.parada = parada;
	}

	public Itinerario getItinerario() {
		return itinerario;
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	public List<Parada> getListaParadas() {
		return listaParadas;
	}

	public void setListaParadas(List<Parada> listaParadas) {
		this.listaParadas = listaParadas;
	}

	public List<Itinerario> getListaItinerario() {
		return listaItinerario;
	}

	public void setListaItinerario(List<Itinerario> listaItinerario) {
		this.listaItinerario = listaItinerario;
	}

	public Long getIdItinerario() {
		return idItinerario;
	}

	public void setIdItinerario(Long idItinerario) {
		this.idItinerario = idItinerario;
	}

	public MapModel getMapa() {
		return mapa;
	}

	public void setMapa(MapModel mapa) {
		this.mapa = mapa;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}	

}
