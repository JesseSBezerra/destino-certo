package br.com.destino_certo.util.teste;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
@ViewScoped
public class LocalBean {
    
	private Local local;
	private List<Local> lista;
	private MapModel locais;
	
	public LocalBean() {
		// TODO Auto-generated constructor stub
		lista = new ArrayList<Local>();
	}
	 
    public void novo(PointSelectEvent event) {
        local = new Local();
        LatLng coord = event.getLatLng();
        local.setLatitude(coord.getLat());
        local.setLongitude(coord.getLng());
    }
 
    public void gravar() {
        lista.add(local);
        carregarLocais();
    }
 
    private void carregarLocais() {
        locais = new DefaultMapModel();
 
        List<Local> locaisList = lista;
        for (Local l1 : locaisList) {
            locais.addOverlay(
                    new Marker(
                    new LatLng(l1.getLatitude(), l1.getLongitude()),
                    l1.getDescricao()));
        }
    }
 
    /*
     * Getters e Setters
     */
    
    
    public MapModel getLocais() {
        if (locais == null) {
            carregarLocais();
        }
        return locais;
    }

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public List<Local> getLista() {
		return lista;
	}

	public void setLista(List<Local> lista) {
		this.lista = lista;
	}

	public void setLocais(MapModel locais) {
		this.locais = locais;
	}
 
}
