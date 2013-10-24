package br.com.destino_certo.cliente.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polyline;



import br.com.destino_certo.endereco.modelo.Endereco;
import br.com.destino_certo.parada.modelo.Parada;
import br.com.destino_certo.taxi.modelo.Taxi;
import br.com.destino_certo.util.decode.Decode;
import br.com.destino_certo.util.fachada.Fachada;

@ManagedBean
@RequestScoped
public class ConsultarTaxiMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Endereco> lista;
	private Fachada fachada;
	
	private String origem;
	private String destino;
	private String posicao;
	private String distancia;
	private String valorAPagar;
	
	private MapModel mapa;
	private MapModel polylineModel;
	
	private boolean exibir = false;
	
	
	private List<Taxi> listaTaxi;
	
	public ConsultarTaxiMB() {
		// TODO Auto-generated constructor stub
		fachada = Fachada.getInstance();
		lista = fachada.listaEndereco();
		listaTaxi = fachada.taxiListar();
		setPosicao("-8.04161,-34.89818");
		polylineModel = new DefaultMapModel();
		carregarParadas(listaTaxi);
	}
	
	public List<String> origem(String busca){
		List<String> results = new ArrayList<String>();
		for(Endereco endereco: lista){
			if(endereco.getLogradouro().toUpperCase().contains(busca.toUpperCase())){
				results.add(endereco.getLogradouro());
			}
		}
		return results;
	}
	
	public void carregarParadas(List<Taxi> lista){
		for(Taxi taxi:lista){
			Marker marker = new Marker(new LatLng(taxi.getLatitude(), taxi.getLongitude()),taxi.getNome());
			marker.setIcon("http://png-1.findicons.com/files/icons/415/travel/32/taxi.png");	
			polylineModel.addOverlay(marker);
		}
	}
	
	public List<String> destino(String busca){
		List<String> results = new ArrayList<String>();
		for(Endereco endereco: lista){
			if(endereco.getLogradouro().toUpperCase().contains(busca.toUpperCase())){
				results.add(endereco.getLogradouro());
			}
		}
		return results;
	}
	
	public void consultar(){
		try{
			Endereco endereco = fachada.procurar(destino);
			Endereco endereco2 = fachada.procurar(origem);
			carregarLocais(endereco.getCodeBusca(),endereco2.getCodeBusca());	
			carregarParadas(listaTaxi);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	private void carregarLocais(String origem, String destino) {
        polylineModel = new DefaultMapModel();
        Polyline polyline = new Polyline();
        Map<String, String> locaisList = fachada.consultaMaps(origem, destino);
        distancia = "Distância :  " + locaisList.get("distancia");
        Long metros = Long.parseLong(locaisList.get("metros"));
        Double valor = ((metros /1000) * 1.90) + 4 ;
        valorAPagar = "Valor da Corrida:  R$ " + valor; 
        
        
        for (br.com.destino_certo.util.decode.LatLng latLng : Decode.decodePolyLine(locaisList.get("poly"))) {
            LatLng coordenada = new LatLng(latLng.getLatitude(), latLng.getLongitude());
            polyline.getPaths().add(coordenada);
            
        }
        Marker markerInicio = new Marker(polyline.getPaths().get(0));
        markerInicio.setIcon("http://www.google.com/intl/en_us/mapfiles/ms/micons/red-dot.png");
        Marker markerFim = new Marker(polyline.getPaths().get(polyline.getPaths().size()-1)); 
        markerFim.setIcon("http://www.google.com/intl/en_us/mapfiles/ms/micons/green-dot.png");
        polylineModel.addOverlay(markerFim);
        polylineModel.addOverlay(markerInicio);
        polyline.setStrokeWeight(3);
		polyline.setStrokeColor("#8B0000");
		polyline.setStrokeOpacity(0.7);
		polylineModel.addOverlay(polyline);
		
    }
	

	public List<Endereco> getLista() {
		return lista;
	}

	public void setLista(List<Endereco> lista) {
		this.lista = lista;
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

	public MapModel getMapa() {
		return mapa;
	}

	public void setMapa(MapModel mapa) {
		this.mapa = mapa;
	}

	public MapModel getPolylineModel() {
		return polylineModel;
	}

	public void setPolylineModel(MapModel polylineModel) {
		this.polylineModel = polylineModel;
	}

	public List<Taxi> getListaTaxi() {
		return listaTaxi;
	}

	public void setListaTaxi(List<Taxi> listaTaxi) {
		this.listaTaxi = listaTaxi;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public boolean isExibir() {
		return exibir;
	}

	public void setExibir(boolean exibir) {
		this.exibir = exibir;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	public String getValorAPagar() {
		return valorAPagar;
	}

	public void setValorAPagar(String valorAPagar) {
		this.valorAPagar = valorAPagar;
	}
	
	
	
}
