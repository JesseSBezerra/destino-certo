package br.com.destino_certo.onibus.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.destino_certo.onibus.modelo.Onibus;
import br.com.destino_certo.util.autenticar.FacesContextUtil;
import br.com.destino_certo.util.fachada.Fachada;

@ManagedBean
@RequestScoped
public class CadastrarOnibusMB {

	private Onibus onibus;
	private Fachada fachada;
	public CadastrarOnibusMB() {
		// TODO Auto-generated constructor stub
		onibus = new Onibus();
		fachada = Fachada.getInstance();
	}
	
	public void cadastrar(){
		onibus.setAtivo(true);
		FacesContextUtil.setMessageInformacao("Info", fachada.onibusCadastrar(onibus));
		onibus = new Onibus();
	}

	public Onibus getOnibus() {
		return onibus;
	}

	public void setOnibus(Onibus onibus) {
		this.onibus = onibus;
	}
	
	
	
	
	
}
