package br.com.destino_certo.onibus.modelo;

import java.io.Serializable;

public class OnibusJaCadastradoException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String message = "Onibus já cadastrado!";
	
	
	public OnibusJaCadastradoException() {
		// TODO Auto-generated constructor stub
		super(message);
	}

}
