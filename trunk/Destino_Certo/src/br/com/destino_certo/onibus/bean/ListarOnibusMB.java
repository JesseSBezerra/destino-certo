package br.com.destino_certo.onibus.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.destino_certo.onibus.modelo.Onibus;
import br.com.destino_certo.util.autenticar.FacesContextUtil;
import br.com.destino_certo.util.fachada.Fachada;

@ManagedBean
@ViewScoped
public class ListarOnibusMB {

	private List<Onibus> lista, listaSelecionada;
	private Onibus onibus;
	private Fachada fachada;
	public ListarOnibusMB() {
		// TODO Auto-generated constructor stub
		fachada = Fachada.getInstance();
		onibus = new Onibus();
		lista = fachada.onibusListar("ativo",true);
	}
	
	public void selecionarOnibus(Onibus onibus){
    this.onibus = onibus;		
	}
	
	public void editar(){
		FacesContextUtil.setMessageInformacao("Info", fachada.onibusEditar(onibus));
		onibus = new Onibus();
	}
	
	public void remover(){
		lista.remove(onibus);
		FacesContextUtil.setMessageInformacao("Info", fachada.onibusRemover(onibus));
		onibus = new Onibus();
	}
	
	public List<Onibus> getLista() {
		return lista;
	}
	public void setLista(List<Onibus> lista) {
		this.lista = lista;
	}
	public List<Onibus> getListaSelecionada() {
		return listaSelecionada;
	}
	public void setListaSelecionada(List<Onibus> listaSelecionada) {
		this.listaSelecionada = listaSelecionada;
	}

	public Onibus getOnibus() {
		return onibus;
	}

	public void setOnibus(Onibus onibus) {
		this.onibus = onibus;
	}

	
	
	
}
