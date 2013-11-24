package br.com.destino_certo.paradaonibus.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.destino_certo.onibus.modelo.Onibus;
import br.com.destino_certo.parada.modelo.Parada;
import br.com.destino_certo.util.autenticar.FacesContextUtil;
import br.com.destino_certo.util.fachada.Fachada;

@ManagedBean
@ViewScoped
public class CadastrarParadaOnibusMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Long getIdOnibus() {
		return idOnibus;
	}


	public void setIdOnibus(Long idOnibus) {
		this.idOnibus = idOnibus;
	}


	private Fachada fachada;
	private Parada parada;
	private MapModel mapa;
	private Long idItinerario;
	private Long idOnibus;
	private String posicao;
	private Onibus onibus;
	private List<Parada>listaParadas;
	private List<Onibus> listaOnibus;
	
	private MapModel polylineModel;
	
	public CadastrarParadaOnibusMB() {
		// TODO Auto-generated constructor stub
		fachada = Fachada.getInstance();
		parada = new Parada();
		listaParadas = fachada.paradaListar();
		setListaOnibus(fachada.onibusListar());
		posicao = "-8.126106,-34.92289";
	}
	
	 
	 public void selecionarParada(){
		 parada = fachada.paradaProcurar(idItinerario);
		 carregarLocais();
		 carregarParada();
	 }
	 
	 public void cadastrarParadaOnibus(){
		 try{
		 onibus = fachada.onibusProcurar(idOnibus);
		 parada.getOnibus().add(onibus);
		 fachada.paradaEditar(parada);
		 carregarLocais();
		 carregarParada();
		 FacesContextUtil.setMessageInformacao("Info", "Cadastrada com Sucesso!");
		 }catch(Exception e){
			 FacesContextUtil.setMessageErro("ERRO", "Erro ao cadastrar " + e.getCause());
		 }
	 }
	
	
	public void carregarParada(){
			Marker marker = new Marker(new LatLng(parada.getLatitude(), parada.getLongitude()),parada.getLocal());
			marker.setIcon("http://png-5.findicons.com/files/icons/903/travel/32/bus.png");	
			polylineModel.addOverlay(marker);
		}
	
	
	
	private void carregarLocais() {
        polylineModel = new DefaultMapModel();
        posicao = parada.getLatitude()+","+parada.getLongitude();
		
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


	public List<Onibus> getListaOnibus() {
		return listaOnibus;
	}


	public void setListaOnibus(List<Onibus> listaOnibus) {
		this.listaOnibus = listaOnibus;
	}
	

}
