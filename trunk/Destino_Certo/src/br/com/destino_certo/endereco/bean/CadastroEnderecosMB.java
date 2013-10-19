package br.com.destino_certo.endereco.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.destino_certo.endereco.controlador.ControladorEndereco;
import br.com.destino_certo.endereco.modelo.Endereco;
import br.com.destino_certo.endereco.modelo.RepositorioEndereco;
import br.com.destino_certo.util.autenticar.FacesContextUtil;

@ManagedBean
@RequestScoped
public class CadastroEnderecosMB {

	private Endereco endereco;
	private ControladorEndereco controladorEndereco;
	
	public CadastroEnderecosMB() {
		// TODO Auto-generated constructor stub
		endereco = new Endereco();
		init();
	}
	
	public void cadastrar(){
		try{
		controladorEndereco.cadastrar(endereco);
		endereco = new Endereco();
		FacesContextUtil.setMessageInformacao("Info", "Cadastrado com sucesso!");
		}catch(Exception e){
			
		}
	}
	
	public void init(){
		controladorEndereco = new ControladorEndereco(new RepositorioEndereco());
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	
}
