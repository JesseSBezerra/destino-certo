package br.com.destino_certo.itinerario.modelo;

import java.io.Serializable;

public class ItinerarioNaoEncontradoException extends Exception implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String message = "Itinerário não encontrado!";
	
	public ItinerarioNaoEncontradoException() {
		// TODO Auto-generated constructor stub
		super(message);
	}

}
