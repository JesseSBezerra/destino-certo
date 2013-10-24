package br.com.destino_certo.endereco.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.destino_certo.endereco.modelo.Endereco;
import br.com.destino_certo.util.autenticar.FacesContextUtil;
import br.com.destino_certo.util.fachada.Fachada;

@ManagedBean
@ViewScoped
public class ListarEndercoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fachada fachada;
	private Endereco endereco;
	private List<Endereco> listaEnderecos;
	private List<Endereco> listaSelecionada;
	
	public ListarEndercoMB() {
		// TODO Auto-generated constructor stub
		fachada = Fachada.getInstance();
		listaEnderecos = fachada.listaEndereco();
		endereco = new Endereco();
	}
	
	public List<Endereco> listaAtiva(List<Endereco> lista){
		List<Endereco> listaFiltrada = new ArrayList<Endereco>();
		for(Endereco endereco:lista){
			if(!endereco.getLogradouro().toLowerCase().equals("contorno")){
				listaFiltrada.add(endereco);
			}
		}
		return listaFiltrada;
	}

	public List<Endereco> getListaEnderecos() {
		return listaAtiva(listaEnderecos);
	}
	
	public void selecionar(Endereco endereco){
		this.endereco = endereco;
	}
	
	public void editar(){
		FacesContextUtil.setMessageInformacao("Info", fachada.enderecoEditar(endereco));
	}
	
	public void remover(){
		FacesContextUtil.setMessageInformacao("Info", fachada.enderecoRemover(endereco));
		listaEnderecos.remove(endereco);
		endereco = new Endereco();
	}

	public void setListaEnderecos(List<Endereco> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Endereco> getListaSelecionada() {
		return listaSelecionada;
	}

	public void setListaSelecionada(List<Endereco> listaSelecionada) {
		this.listaSelecionada = listaSelecionada;
	}
	
	

}
