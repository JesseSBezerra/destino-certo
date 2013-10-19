package br.com.destino_certo.cliente.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polyline;

import br.com.destino_certo.itinerario.modelo.Itinerario;
import br.com.destino_certo.parada.modelo.Parada;
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
	
	
	private MapModel polylineModel;

	public DestinoCertoMB() {
		fachada = Fachada.getInstance();
		parada = new Parada();
		listaItinerario = fachada.itinerarioListar();
		posicao = "-8.04161,-34.89818";
//		 polylineModel = new DefaultMapModel();
//		  Polyline polyline = new Polyline();
//		  polyline.getPaths().add(new LatLng( -8041610.0,  -3.489818E7)); 
//          polyline.getPaths().add(new LatLng( -8040660.0,  -3.48986E7)); 
//          polyline.getPaths().add(new LatLng( -8039510.0,  -3.489945E7)); 
//          polyline.getPaths().add(new LatLng( -8038160.0,  -3.490067E7)); 
//          polyline.getPaths().add(new LatLng( -8037530.0,  -3.490123E7)); 
//          polyline.getPaths().add(new LatLng( -8036910.0,  -3.490171E7)); 
//          polyline.getPaths().add(new LatLng( -8035660.0,  -3.490226E7)); 
//          polyline.getPaths().add(new LatLng( -8034710.0,  -3.490264E7)); 
//          polyline.getPaths().add(new LatLng( -8034700.0,  -3.490232E7)); 
//          polyline.getPaths().add(new LatLng( -8034850.0,  -3.490152E7)); 
//          polyline.getPaths().add(new LatLng( -8035270.0,  -3.489924E7)); 
//          polyline.getPaths().add(new LatLng( -8035370.0,  -3.489854E7)); 
//          polyline.getPaths().add(new LatLng( -8035640.0,  -3.489759E7)); 
//          polyline.getPaths().add(new LatLng( -8035870.0,  -3.489686E7)); 
//          polyline.getPaths().add(new LatLng( -8036040.0,  -3.48964E7)); 
//          polyline.getPaths().add(new LatLng( -8036130.0,  -3.489616E7)); 
//          polyline.getPaths().add(new LatLng( -8036610.0,  -3.489499E7)); 
//          polyline.getPaths().add(new LatLng( -8037670.0,  -3.489531E7)); 
//          polyline.getPaths().add(new LatLng( -8037950.0,  -3.489542E7)); 
//          polyline.getPaths().add(new LatLng( -8037990.0,  -3.489529E7));    
//          polyline.setStrokeWeight(3);
//  		polyline.setStrokeColor("#FF9900");
//  		polyline.setStrokeOpacity(0.7);
//  		polylineModel.addOverlay(polyline);
		
	}

	public MapModel getPolylineModel() {
		return polylineModel;
	}
	
	 
	 public void selecionarItinerario(){
		 itinerario = fachada.itinerarioProcurar(idItinerario);
		 carregarLocais();
	 }
	 
	 public void carregarParadas(){
		 listaParada2 = listaParada();
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
	
	private void carregarLocais() {
        polylineModel = new DefaultMapModel();
        Polyline polyline = new Polyline();
        List<Parada> locaisList = listaParada();
        for (Parada pa : locaisList) {
            LatLng coordenada = new LatLng(pa.getLatitude(), pa.getLongitude());
           
            polyline.getPaths().add(new LatLng( -8041610.0,  -3.489818E7)); 
            polyline.getPaths().add(new LatLng( -8040660.0,  -3.48986E7)); 
            polyline.getPaths().add(new LatLng( -8039510.0,  -3.489945E7)); 
            polyline.getPaths().add(new LatLng( -8038160.0,  -3.490067E7)); 
            polyline.getPaths().add(new LatLng( -8037530.0,  -3.490123E7)); 
            polyline.getPaths().add(new LatLng( -8036910.0,  -3.490171E7)); 
            polyline.getPaths().add(new LatLng( -8035660.0,  -3.490226E7)); 
            polyline.getPaths().add(new LatLng( -8034710.0,  -3.490264E7)); 
            polyline.getPaths().add(new LatLng( -8034700.0,  -3.490232E7)); 
            polyline.getPaths().add(new LatLng( -8034850.0,  -3.490152E7)); 
            polyline.getPaths().add(new LatLng( -8035270.0,  -3.489924E7)); 
            polyline.getPaths().add(new LatLng( -8035370.0,  -3.489854E7)); 
            polyline.getPaths().add(new LatLng( -8035640.0,  -3.489759E7)); 
            polyline.getPaths().add(new LatLng( -8035870.0,  -3.489686E7)); 
            polyline.getPaths().add(new LatLng( -8036040.0,  -3.48964E7)); 
            polyline.getPaths().add(new LatLng( -8036130.0,  -3.489616E7)); 
            polyline.getPaths().add(new LatLng( -8036610.0,  -3.489499E7)); 
            polyline.getPaths().add(new LatLng( -8037670.0,  -3.489531E7)); 
            polyline.getPaths().add(new LatLng( -8037950.0,  -3.489542E7)); 
            polyline.getPaths().add(new LatLng( -8037990.0,  -3.489529E7));      
           polyline.getPaths().add(coordenada);
            
        }
        listaParada2 = atualizarParadas(locaisList);
        posicao = polyline.getPaths().get(1).getLat() +" , "+polyline.getPaths().get(1).getLng();
        polyline.setStrokeWeight(3);
		polyline.setStrokeColor("#FF9900");
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
