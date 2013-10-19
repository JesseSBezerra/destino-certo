package br.com.destino_certo.parada.modelo;

import java.io.Serializable;

public class ParadaJaCadastradaExcepetion extends Exception implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String message = "Parada já cadastrada!";
	
	public ParadaJaCadastradaExcepetion() {
		// TODO Auto-generated constructor stub
		super(message);
	}
	

}
