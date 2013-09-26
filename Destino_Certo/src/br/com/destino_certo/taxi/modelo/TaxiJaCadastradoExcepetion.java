package br.com.destino_certo.taxi.modelo;

import java.io.Serializable;

public class TaxiJaCadastradoExcepetion extends Exception implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String message = "Ponto de taxi já cadastrado!";
	
	public TaxiJaCadastradoExcepetion() {
		// TODO Auto-generated constructor stub
		super(message);
	}
	

}
