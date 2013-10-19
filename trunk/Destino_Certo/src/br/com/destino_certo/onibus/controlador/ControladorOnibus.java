package br.com.destino_certo.onibus.controlador;

import java.io.Serializable;
import java.util.List;

import br.com.destino_certo.onibus.modelo.IRepositorioOnibus;
import br.com.destino_certo.onibus.modelo.Onibus;
import br.com.destino_certo.onibus.modelo.OnibusJaCadastradoException;
import br.com.destino_certo.onibus.modelo.OnibusNaoEncontradoException;

public class ControladorOnibus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IRepositorioOnibus iRepositorioOnibus;

	public ControladorOnibus(IRepositorioOnibus iRepositorioOnibus) {
		// TODO Auto-generated constructor stub
		this.iRepositorioOnibus = iRepositorioOnibus;
	}
	
	public String cadastrar(Onibus onibus){
		String mensagem = null;
		if(existe(onibus)){
			mensagem = "Onibus já cadastrado!";
		}else{
			try {
				iRepositorioOnibus.cadastrar(onibus);
				mensagem = "Cadastrado com sucesso!";
			} catch (OnibusJaCadastradoException e) {
				// TODO Auto-generated catch block
				mensagem = e.getMessage();
			}
		}
		return mensagem;
	}
	
	public String editar(Onibus onibus){
		String mensagem = null;
		if(existe(onibus)){
			try {
				iRepositorioOnibus.editar(onibus);
				mensagem = "Editado com sucesso";
			} catch (OnibusNaoEncontradoException e) {
				// TODO Auto-generated catch block
				mensagem = e.getMessage();
			}
		}else{
			mensagem = "Erro ao editar";
		}
		return mensagem;
	}
	
	public String remover(Onibus onibus){
		String mensagem = null;
		if(existe(onibus)){
			try {
				onibus.setAtivo(false);
				iRepositorioOnibus.editar(onibus);
				mensagem = "Removido com sucesso";
			} catch (OnibusNaoEncontradoException e) {
				// TODO Auto-generated catch block
				mensagem = e.getMessage();
			}
		}else{
			mensagem = "Erro ao Remover";
		}
		return mensagem;
	}
	
	public Onibus procurar(Long numero){
		Onibus onibus = null;
		try {
			onibus = iRepositorioOnibus.procurar(numero);
		} catch (OnibusNaoEncontradoException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		return onibus;
	}
	
	public List<Onibus> listar(){
		return iRepositorioOnibus.listar();
	}
	
	public List<Onibus> listar(String nomeCampo,boolean valorCampo){
		return iRepositorioOnibus.listar(nomeCampo,valorCampo);
	}
	
	private boolean existe(Onibus onibus){
		boolean jaCadastrado = false;
		try {
			Onibus onibusExiste = procurar(onibus.getNumero());
			if(!onibusExiste.equals(null)){
				jaCadastrado = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return jaCadastrado;
	}

}
