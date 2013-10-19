package br.com.destino_certo.usuario.modelo;

public class UsuarioJaCadastradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsuarioJaCadastradoException(Usuario usuario) {
		// TODO Auto-generated constructor stub
		super("Usuario já cadastrado !"+ usuario.getNome());
	}

}
