package br.com.destino_certo.itinerario.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.destino_certo.endereco.modelo.Endereco;
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
	private List<Endereco> listaEndereco;
	
	private String origem;
	private String destino;
	
	public CadastarItinerarioMB() {
		// TODO Auto-generated constructor stub
	    itinerario = new Itinerario();
		fachada = Fachada.getInstance();
		lista = fachada.onibusListar("ativo", true);
		listaEndereco = fachada.listaEndereco();
	}
	
	public List<String> origem(String busca){
		List<String> results = new ArrayList<String>();
		for(Endereco endereco: listaEndereco){
			if(endereco.getLogradouro().toUpperCase().contains(busca.toUpperCase())){
				results.add(endereco.getLogradouro());
			}
		}
		return results;
	}
	
	public List<String> destino(String busca){
		List<String> results = new ArrayList<String>();
		for(Endereco endereco: listaEndereco){
			if(endereco.getLogradouro().toUpperCase().contains(busca.toUpperCase())){
				results.add(endereco.getLogradouro());
			}
		}
		return results;
	}
	
	public void cadastrar(){
		itinerario.setAtivo(true);
		if(numeroOnibus==0){
			FacesContextUtil.setMessageInformacao("Info", "O campo onibus está vasio!");
		}else{
		itinerario.setOnibus(fachada.onibusProcurar(numeroOnibus));
		itinerario.setOrigem(fachada.procurar(origem));
		itinerario.setDestino(fachada.procurar(destino));
		FacesContextUtil.setMessageInformacao("Info", fachada.itinerarioCadastrar(itinerario));
		itinerario = new Itinerario();
		origem = "";
		destino = "";
		numeroOnibus = null;
		}
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
