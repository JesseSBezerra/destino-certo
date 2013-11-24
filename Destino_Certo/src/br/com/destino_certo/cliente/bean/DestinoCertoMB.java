package br.com.destino_certo.cliente.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polyline;

import br.com.destino_certo.itinerario.modelo.Itinerario;
import br.com.destino_certo.onibus.controlador.ControladorOnibus;
import br.com.destino_certo.onibus.modelo.Onibus;
import br.com.destino_certo.parada.modelo.Parada;
import br.com.destino_certo.util.decode.Decode;
import br.com.destino_certo.util.decode.DecodeOnibus;
import br.com.destino_certo.util.fachada.Fachada;

@ManagedBean
@RequestScoped
public class DestinoCertoMB {

	
	private Fachada fachada;
	private Parada parada;
	private String origem;
	private String destino;
	private String onibusPegar;
	private String sOnibusPegar;
	private MapModel mapa;
	private String posicao;
	private Itinerario itinerario;
	private List<Parada> listaParadas;
	private List<Parada> listaParada2;
	private Map valores;
	
	private MapModel polylineModel;

	public DestinoCertoMB() {
		fachada = Fachada.getInstance();
		parada = new Parada();
		posicao = "-8.04161,-34.89818";
		polylineModel = new DefaultMapModel();
		carregarParadas(fachada.paradaListar());
		
	}

	public MapModel getPolylineModel() {
		return polylineModel;
	}
	
	 
	 public void selecionarDestino(){
		 try{
		 valores = DecodeOnibus.tracaRotaOnibus(fachada.onibusAjustarConsulta(origem), fachada.onibusAjustarConsulta(destino));
		 Boolean teste = (Boolean) valores.get("origemDestino");
		 if(!teste){
			 Onibus onibusDI = (Onibus) valores.get("onibusDI");
			 Onibus onibusOD = (Onibus) valores.get("onibusOD");
			 onibusPegar = "Onibus a pegar:\n"+onibusDI.getNome()+"\n"+onibusOD.getNome();
		 }
		 carregarPrimeiraParada();
		 carregarSegundaParada();
		 carregarTerceiraParada();
		 carregarParadafinal();
//		 carregarLocais();
		 }catch(Exception e){
			 
		 }
//		 carregarParadas(listaParada(itinerario));
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
			if(!parada.getLocal().toLowerCase().equals("contorno")){
				listaParada.add(parada);
				polylineModel.addOverlay(
	                    new Marker(
	                    new LatLng(parada.getLatitude(), parada.getLongitude()),
	                    parada.getLocal()));
			}
		}
		return listaParada;
	}
	
	public void carregarParadas(List<Parada> lista){
		for(Parada parada:lista){
			Marker marker = new Marker(new LatLng(parada.getLatitude(), parada.getLongitude()),parada.getLocal());
			marker.setIcon("http://png-5.findicons.com/files/icons/903/travel/32/bus.png");	
			polylineModel.addOverlay(marker);
		}
	}

	private void carregarPrimeiraParada() {
//      polylineModel = new DefaultMapModel();
	  Parada parada = (Parada) valores.get("paradaOrigem");
	  br.com.destino_certo.util.decode.LatLng latLng = (br.com.destino_certo.util.decode.LatLng) valores.get("localOrigem");
	  String origem = latLng.getLatitude()+","+latLng.getLongitude();
	  String destino = parada.getLatitude()+","+parada.getLongitude();
	  Map mapa = Decode.consultaMaps(origem, destino);
	  String poly = (String) mapa.get("poly");
      Polyline polyline = new Polyline();
      for (br.com.destino_certo.util.decode.LatLng latLng2 : Decode.decodePolyLine(poly)) {
          LatLng coordenada = new LatLng(latLng2.getLatitude(), latLng2.getLongitude());
          polyline.getPaths().add(coordenada);
          
      }
      posicao = polyline.getPaths().get(0).getLat()+","+polyline.getPaths().get(0).getLng();
      Marker markerInicio = new Marker(polyline.getPaths().get(0));
      markerInicio.setIcon("http://www.google.com/intl/en_us/mapfiles/ms/micons/red-dot.png");
      Marker markerFim = new Marker(polyline.getPaths().get(polyline.getPaths().size()-1)); 
      markerFim.setIcon("http://www.google.com/intl/en_us/mapfiles/ms/micons/blue-dot.png");
      polylineModel.addOverlay(markerFim);
      polylineModel.addOverlay(markerInicio);
      polyline.setStrokeWeight(3);
		polyline.setStrokeColor("#000080");
		polyline.setStrokeOpacity(0.7);
		polylineModel.addOverlay(polyline);
		
  }
	
	private void carregarSegundaParada() {
//      polylineModel = new DefaultMapModel();
	  Parada parada = (Parada) valores.get("paradaOrigem");
	  Parada paradaIntermediaria = (Parada) valores.get("paradaIntermediaria");
	  String origem = parada.getLatitude()+","+parada.getLongitude();
	  String intermediaria = paradaIntermediaria.getLatitude()+","+paradaIntermediaria.getLongitude();
	  Map mapa = new HashMap();
	  mapa = Decode.consultaMaps(origem, intermediaria);;
	  String poly = (String) mapa.get("poly");
      Polyline polyline = new Polyline();
      for (br.com.destino_certo.util.decode.LatLng latLng2 : Decode.decodePolyLine(poly)) {
          LatLng coordenada = new LatLng(latLng2.getLatitude(), latLng2.getLongitude());
          polyline.getPaths().add(coordenada);
          
      }
      posicao = polyline.getPaths().get(0).getLat()+","+polyline.getPaths().get(0).getLng();
      Marker markerFim = new Marker(polyline.getPaths().get(polyline.getPaths().size()-1)); 
      markerFim.setIcon("http://www.google.com/intl/en_us/mapfiles/ms/micons/blue-dot.png");
      polylineModel.addOverlay(markerFim);
      polyline.setStrokeWeight(3);
		polyline.setStrokeColor("#006400");
		polyline.setStrokeOpacity(0.7);
		polylineModel.addOverlay(polyline);
		
  }
	
	private void carregarTerceiraParada() {
//      polylineModel = new DefaultMapModel();
	  Parada parada = (Parada) valores.get("paradaIntermediaria");
	  Parada paradaIntermediaria = (Parada) valores.get("paradaDestino");
	  String origem = parada.getLatitude()+","+parada.getLongitude();
	  String intermediaria = paradaIntermediaria.getLatitude()+","+paradaIntermediaria.getLongitude();
	  Map mapa = new HashMap();
	  mapa = Decode.consultaMaps(origem, intermediaria);;
	  String poly = (String) mapa.get("poly");
      Polyline polyline = new Polyline();
      for (br.com.destino_certo.util.decode.LatLng latLng2 : Decode.decodePolyLine(poly)) {
          LatLng coordenada = new LatLng(latLng2.getLatitude(), latLng2.getLongitude());
          polyline.getPaths().add(coordenada);
          
      }
      posicao = polyline.getPaths().get(0).getLat()+","+polyline.getPaths().get(0).getLng();
      Marker markerFim = new Marker(polyline.getPaths().get(polyline.getPaths().size()-1)); 
      markerFim.setIcon("http://www.google.com/intl/en_us/mapfiles/ms/micons/blue-dot.png");
      polylineModel.addOverlay(markerFim);
      polyline.setStrokeWeight(3);
		polyline.setStrokeColor("#006400");
		polyline.setStrokeOpacity(0.7);
		polylineModel.addOverlay(polyline);
		
  }
	

	private void carregarParadafinal() {
//      polylineModel = new DefaultMapModel();
	  Parada parada = (Parada) valores.get("paradaDestino");
	  br.com.destino_certo.util.decode.LatLng latLng = (br.com.destino_certo.util.decode.LatLng) valores.get("localDestino");
	  String origem = parada.getLatitude()+","+parada.getLongitude();
	  String intermediaria = latLng.getLatitude()+","+latLng.getLongitude();
	  Map mapa = new HashMap();
	  mapa = Decode.consultaMaps(origem, intermediaria);;
	  String poly = (String) mapa.get("poly");
      Polyline polyline = new Polyline();
      for (br.com.destino_certo.util.decode.LatLng latLng2 : Decode.decodePolyLine(poly)) {
          LatLng coordenada = new LatLng(latLng2.getLatitude(), latLng2.getLongitude());
          polyline.getPaths().add(coordenada);
          
      }
      posicao = polyline.getPaths().get(0).getLat()+","+polyline.getPaths().get(0).getLng();
      Marker markerFim = new Marker(polyline.getPaths().get(polyline.getPaths().size()-1)); 
      markerFim.setIcon("http://www.google.com/intl/en_us/mapfiles/ms/micons/green-dot.png");
      polylineModel.addOverlay(markerFim);
      polyline.setStrokeWeight(3);
		polyline.setStrokeColor("#006400");
		polyline.setStrokeOpacity(0.7);
		polylineModel.addOverlay(polyline);
		
  }
	
	
	  public String getOnibusPegar() {
		return onibusPegar;
	}

	public void setOnibusPegar(String onibusPegar) {
		this.onibusPegar = onibusPegar;
	}

	public String getsOnibusPegar() {
		return sOnibusPegar;
	}

	public void setsOnibusPegar(String sOnibusPegar) {
		this.sOnibusPegar = sOnibusPegar;
	}

	public List<Parada> getListaParada2() {
		return listaParada2;
	}


	public void setListaParada2(List<Parada> listaParada2) {
		this.listaParada2 = listaParada2;
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

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	

}
