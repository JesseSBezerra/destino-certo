package br.com.destino_certo.parada.controlador;

import java.io.Serializable;
import java.util.List;

import br.com.destino_certo.parada.modelo.IRepositorioParada;
import br.com.destino_certo.parada.modelo.Parada;
import br.com.destino_certo.parada.modelo.ParadaJaCadastradaExcepetion;
import br.com.destino_certo.parada.modelo.ParadaNaoEncontradaException;

public class ControladorParada implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IRepositorioParada iRepositorioParada;

	public ControladorParada(IRepositorioParada iRepositorioParada) {
		// TODO Auto-generated constructor stub
		this.iRepositorioParada = iRepositorioParada;
	}
	
	public String cadastrar(Parada parada){
		String mensagem = null;
		if(existe(parada)){
			mensagem = "Parada já cadastrada!";
		}else{
			try {
				iRepositorioParada.cadastrar(parada);
				mensagem = "Cadastrado com sucesso!";
			} catch (ParadaJaCadastradaExcepetion e) {
				// TODO Auto-generated catch block
				mensagem = e.getMessage();
			}
		}
		return mensagem;
	}
	
	public String editar(Parada parada){
		String mensagem = null;
		if(existe(parada)){
			try {
				iRepositorioParada.editar(parada);
				mensagem = "Editado com sucesso";
			} catch (ParadaNaoEncontradaException e) {
				// TODO Auto-generated catch block
				mensagem = e.getMessage();
			}
		}else{
			mensagem = "Erro ao editar";
		}
		return mensagem;
	}
	
	public String remover(Parada parada){
		String mensagem = null;
		if(existe(parada)){
			try {
				iRepositorioParada.editar(parada);
				mensagem = "Removido com sucesso";
			} catch (ParadaNaoEncontradaException e) {
				// TODO Auto-generated catch block
				mensagem = e.getMessage();
			}
		}else{
			mensagem = "Erro ao Remover";
		}
		return mensagem;
	}
	
	public Parada procurar(Long numero){
		Parada parada = null;
		try {
			parada = iRepositorioParada.procurar(numero);
		} catch (ParadaNaoEncontradaException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		return parada;
	}
	
	public Parada procurar(Long numeroIti, Integer ordemParada){
		Parada parada = null;
		try {
			parada = iRepositorioParada.procurar("itinerario.numero", numeroIti, "ordem", ordemParada);
		} catch (ParadaNaoEncontradaException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		return parada;
	}
	
	public List<Parada> listar(){
		return iRepositorioParada.listar();
	}
	
	public List<Parada> listar(String nomeCampo,boolean valorCampo){
		return iRepositorioParada.listar(nomeCampo,valorCampo);
	}
	
	public List<Parada> listar(String nomeCampo,Long valorCampo){
		return iRepositorioParada.listar(nomeCampo,valorCampo);
	}
	
	private boolean existe(Parada parada){
		boolean jaCadastrado = false;
		try {
			Parada paradaExiste = procurar(parada.getItinerario().getNumero(),parada.getOrdem());
			if(!paradaExiste.equals(null)){
				jaCadastrado = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return jaCadastrado;
	}

}
