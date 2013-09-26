package br.com.destino_certo.taxi.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.destino_certo.itinerario.modelo.Itinerario;
import br.com.destino_certo.taxi.modelo.Taxi;
import br.com.destino_certo.util.autenticar.FacesContextUtil;
import br.com.destino_certo.util.fachada.Fachada;

public class CadastrarTaxiMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fachada fachada;
	private Taxi taxi;
	private MapModel mapa;
	private Long idItinerario;
	private String posicao;
	private Itinerario itinerario;
	private List<Itinerario> listaItinerarios;
	private List<Taxi>listaTaxis;
	
	public CadastrarTaxiMB() {
		// TODO Auto-generated constructor stub
		fachada = Fachada.getInstance();
		taxi = new Taxi();
		listaItinerarios = fachada.itinerarioListar();
		posicao = "-8.126106,-34.92289";
	}
	
	 public void novo(PointSelectEvent event) {
	        LatLng coord = event.getLatLng();
	        taxi.setLatitude(coord.getLat());
	        taxi.setLongitude(coord.getLng());
	    }
	 
	 public void selecionarItinerario(){
		 itinerario = fachada.itinerarioProcurar(idItinerario);
		 System.out.println(itinerario.getNome());
	 }
	 
	 public void gravar() {
		    FacesContextUtil.setMessageInformacao("Info", fachada.taxiCadastrar(taxi));
	        taxi = new Taxi();
	        carregarLocais();
	    }

	public List<Itinerario> getListaItinerarios() {
		return listaItinerarios;
	}
	
	public List<Taxi> listaTaxi(){
		List<Taxi> listaP;
		if(itinerario==null){
			listaP = new ArrayList<Taxi>();
		}else{
			listaP = fachada.taxiListar();
		}
		return listaP;
	}
		
	private void carregarLocais() {
        mapa = new DefaultMapModel();
        List<Taxi> locaisList = listaTaxi();
        for (Taxi pa : locaisList) {
            mapa.addOverlay(
                    new Marker(
                    new LatLng(pa.getLatitude(), pa.getLongitude()),
                    pa.getNome()));
        }
        if(locaisList.size()>0){
        	 posicao = locaisList.get(1).getLatitude()+" , "+locaisList.get(1).getLongitude(); 	
        }        	
        System.out.println(posicao);
    }
	
	  public MapModel getLocais() {
	        if (listaTaxis == null) {
	            carregarLocais();
	        }
	        return mapa;
	    }

	public void setListaItinerarios(List<Itinerario> listaItinerarios) {
		this.listaItinerarios = listaItinerarios;
	}

	public Taxi getTaxi() {
		return taxi;
	}

	public void setTaxi(Taxi taxi) {
		this.taxi = taxi;
	}

	public List<Taxi> getListaTaxis() {
		return listaTaxis;
	}

	public void setListaTaxis(List<Taxi> listaTaxis) {
		this.listaTaxis = listaTaxis;
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
	

}
