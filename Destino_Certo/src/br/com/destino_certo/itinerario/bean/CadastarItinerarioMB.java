package br.com.destino_certo.itinerario.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.destino_certo.itinerario.modelo.Itinerario;
import br.com.destino_certo.onibus.modelo.Onibus;
import br.com.destino_certo.util.autenticar.FacesContextUtil;
import br.com.destino_certo.util.fachada.Fachada;

@ManagedBean
@ViewScoped
public class CadastarItinerarioMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Itinerario itinerario;
	private Long numeroOnibus;
	private List<Onibus> lista;
	private Fachada fachada;
	
	public CadastarItinerarioMB() {
		// TODO Auto-generated constructor stub
	    itinerario = new Itinerario();
		fachada = Fachada.getInstance();
		lista = fachada.onibusListar("ativo", true);
	}
	
	public void cadastrar(){
		itinerario.setAtivo(true);
		if(numeroOnibus==0){
			FacesContextUtil.setMessageInformacao("Info", "O campo onibus está vasio!");
		}else{
		itinerario.setOnibus(fachada.onibusProcurar(numeroOnibus));
		FacesContextUtil.setMessageInformacao("Info", fachada.itinerarioCadastrar(itinerario));
		itinerario = new Itinerario();
		numeroOnibus = null;
		}
	}
	

	public Itinerario getItinerario() {
		return itinerario;
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	public List<Onibus> getLista() {
		return lista;
	}

	public void setLista(List<Onibus> lista) {
		this.lista = lista;
	}

	public Long getNumeroOnibus() {
		return numeroOnibus;
	}

	public void setNumeroOnibus(Long numeroOnibus) {
		this.numeroOnibus = numeroOnibus;
	}


	
	

}
