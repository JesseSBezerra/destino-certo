package br.com.destino_certo.itinerario.modelo;

import java.io.Serializable;

public class ItinerarioJaCadastradoExcepetion extends Exception implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String message = "Itinerario já cadastrado!";
	
	public ItinerarioJaCadastradoExcepetion() {
		// TODO Auto-generated constructor stub
		super(message);
	}
	

}
