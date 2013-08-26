package br.com.destino_certo.itinerario.controlador;

import java.io.Serializable;
import java.util.List;

import br.com.destino_certo.itinerario.modelo.IRepositorioItinerario;
import br.com.destino_certo.itinerario.modelo.Itinerario;
import br.com.destino_certo.itinerario.modelo.ItinerarioJaCadastradoExcepetion;
import br.com.destino_certo.itinerario.modelo.ItinerarioNaoEncontradoException;

public class ControladorItinerario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IRepositorioItinerario iRepositorioItinerario;

	public ControladorItinerario(IRepositorioItinerario iRepositorioItinerario) {
		// TODO Auto-generated constructor stub
		this.iRepositorioItinerario = iRepositorioItinerario;
	}

	public String cadastrar(Itinerario itinerario) {
		String message = null;
        if(existe(itinerario)){
        	message = "Itinerario já cadastrado";
        }else{
        	try {
				iRepositorioItinerario.cadastrar(itinerario);
				message = "Itinerarico cadastrado com sucesso!";
			} catch (ItinerarioJaCadastradoExcepetion e) {
				// TODO Auto-generated catch block
				message = e.getMessage();
			}
        }
		return message;
	}

	public Itinerario procurar(Long numero) {
		Itinerario itinerario = null;
		try {
			itinerario = iRepositorioItinerario.procurar(numero);
		} catch (ItinerarioNaoEncontradoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return itinerario;
	}
	
	public String editar(Itinerario itinerario){
		String message = null;
		if(existe(itinerario)){
			try {
				iRepositorioItinerario.editar(itinerario);
				message = "Editado com sucesso!";
			} catch (ItinerarioNaoEncontradoException e) {
				// TODO Auto-generated catch block
				message = e.getMessage();
			}
		}else{
			message = "Erro ao editar!";
		}
		
		return message;
	}
	
	public String remover(Itinerario itinerario){
		String message = null;
		if(existe(itinerario)){
			try {
				itinerario.setAtivo(false);
				iRepositorioItinerario.editar(itinerario);
				message = "Removido com sucesso!";
			} catch (ItinerarioNaoEncontradoException e) {
				// TODO Auto-generated catch block
				message = e.getMessage();
			}
		}else{
			message = "Erro ao remover!";
		}
		
		return message;
	}

	public boolean existe(Itinerario itinerario) {
		boolean existe = false;
		try {
			Itinerario itinerarioExiste = procurar(itinerario.getNumero());
			if (itinerarioExiste != null) {
				existe = true;
			}
		} catch (Exception e) {

		}

		return existe;
	}
	
	public List<Itinerario> listar(){
		return iRepositorioItinerario.listar();
	}
	
	public List<Itinerario> listar(String nomeCampo,boolean valorCampo){
		return iRepositorioItinerario.listar(nomeCampo,valorCampo);
	}

}
