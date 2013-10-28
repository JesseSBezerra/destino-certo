package br.com.destino_certo.parada.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polyline;

import br.com.destino_certo.itinerario.modelo.Itinerario;
import br.com.destino_certo.parada.modelo.Parada;
import br.com.destino_certo.util.autenticar.FacesContextUtil;
import br.com.destino_certo.util.decode.Decode;
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
	private String posicao;
	private Itinerario itinerario;
	private List<Itinerario> listaItinerarios;
	private List<Parada>listaParadas;
	private Map<String, String> valores;
	
	private MapModel polylineModel;
	
	public CadastrarParadaMB() {
		// TODO Auto-generated constructor stub
		fachada = Fachada.getInstance();
		parada = new Parada();
		listaItinerarios = fachada.itinerarioListar();
		posicao = "-8.126106,-34.92289";
		polylineModel = new DefaultMapModel();
	}
	
	 public void novo(PointSelectEvent event) {
	        LatLng coord = event.getLatLng();
	        parada.setLatitude(coord.getLat());
	        parada.setLongitude(coord.getLng());
		 
	    }
	 
	 public void selecionarItinerario(){
		 itinerario = fachada.itinerarioProcurar(idItinerario);
		 valores = Decode.consultaMaps(itinerario.getOrigem().getCodeBusca(), itinerario.getDestino().getCodeBusca());
		 carregarLocais();
		 carregarParadas(listaParada(itinerario));
	 }
	 
	 public void gravar() {
		 if(itinerario!=null){
	        parada.setItinerario(itinerario);
		    FacesContextUtil.setMessageInformacao("Info", fachada.paradaCadastrar(parada));
	        parada = new Parada();
	        carregarLocais();
	        carregarParadas(listaParada(itinerario));
		 }else{
			 FacesContextUtil.setMessageErro("Info","Itinerario não informado!");
		 }
	    }

	public List<Itinerario> getListaItinerarios() {
		return listaItinerarios;
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

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public MapModel getPolylineModel() {
		return polylineModel;
	}

	public void setPolylineModel(MapModel polylineModel) {
		this.polylineModel = polylineModel;
	}
	
	
	

}
