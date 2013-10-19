package br.com.destino_certo.taxi.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.destino_certo.taxi.modelo.Taxi;
import br.com.destino_certo.util.autenticar.FacesContextUtil;
import br.com.destino_certo.util.fachada.Fachada;

@ManagedBean
@ViewScoped
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
	private List<Taxi>listaTaxis;
	
	public CadastrarTaxiMB() {
		// TODO Auto-generated constructor stub
		fachada = Fachada.getInstance();
		taxi = new Taxi();
		posicao = "-8.126106,-34.92289";
	}
	
	 public void novo(PointSelectEvent event) {
	        LatLng coord = event.getLatLng();
	        taxi.setLatitude(coord.getLat());
	        taxi.setLongitude(coord.getLng());
	    }
	 
	 
	 public void gravar() {
		    FacesContextUtil.setMessageInformacao("Info", fachada.taxiCadastrar(taxi));
	        taxi = new Taxi();
	        carregarLocais();
	    }

	
	public List<Taxi> listaTaxi(){
		List<Taxi> listaP;
			listaP = fachada.taxiListar();
		return listaP;
	}
		
	private void carregarLocais() {
        mapa = new DefaultMapModel();
        List<Taxi> locaisList = listaTaxi();
        for (Taxi pa : locaisList) {
            Marker marker = new Marker(new LatLng(pa.getLatitude(), pa.getLongitude()),pa.getNome());
            marker.setIcon("http://png-1.findicons.com/files/icons/415/travel/32/taxi.png");
        	mapa.addOverlay(
                    marker);      	
        }
        if(locaisList.size()>0){
        	 posicao = locaisList.get(0).getLatitude()+" , "+locaisList.get(0).getLongitude(); 	
        }        	
    }
	
	  public MapModel getLocais() {
	        if (listaTaxis == null) {
	            carregarLocais();
	        }
	        return mapa;
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
