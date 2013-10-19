package br.com.destino_certo.usuario.modelo;

public class UsuarioNaoEncontradoExcepition extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static final String message = "Usuario não encontrado";
	
	public UsuarioNaoEncontradoExcepition() {
		// TODO Auto-generated constructor stub	
		super(message);
	}
	

}
