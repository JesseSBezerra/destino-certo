package br.com.destino_certo.taxi.modelo;

import java.io.Serializable;

public class TaxiNaoEncontradoException extends Exception implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String message = "Ponto de taxi não encontrado!";
	
	public TaxiNaoEncontradoException() {
		// TODO Auto-generated constructor stub
		super(message);
	}

}
