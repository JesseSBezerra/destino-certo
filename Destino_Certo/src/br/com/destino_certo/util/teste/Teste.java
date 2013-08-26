package br.com.destino_certo.util.teste;


import br.com.destino_certo.onibus.controlador.ControladorOnibus;
import br.com.destino_certo.onibus.modelo.Onibus;
import br.com.destino_certo.onibus.modelo.RepositorioOnibus;
import br.com.destino_certo.usuario.modelo.Usuario;
import br.com.destino_certo.util.fachada.Fachada;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
////		for(int i = 0; i<=5;i++){
	Usuario usuario = new Usuario();
	Onibus onibus = new Onibus();
//	    ControladorUsuario controladorUsuario = new ControladorUsuario(new RepositorioUsuario());
//	    usuario.setNome("Jessé dos Santos Bezerra");
	    usuario.setLogin("mamão0");
//	    usuario.setSenha("1");
//	    usuario.setEmail("jessebezerra@hotmail.com.br");
//	    controladorUsuario.cadastrar(usuario);
//////		}
//	     usuario = controladorUsuario.procurar("JBezerra");
//	     usuario.setLogin("eerfe");
//	    usuario.setEmail("pinpolho");
//	    System.out.println(controladorUsuario.editar(usuario));
//	    
//	    System.out.println(controladorUsuario.listar().size());
//	Fachada fachada = Fachada.getInstance();
//	 System.out.println(fachada.usuarioRemover(usuario));
	    ControladorOnibus controladorOnibus = new ControladorOnibus(new RepositorioOnibus());
	    onibus.setAtivo(true);
	    onibus.setNumero(111129L);
	    onibus.setNome("Teste");
	    System.out.println(controladorOnibus.listar().size());
	    System.out.println(controladorOnibus.listar("ativo", true).size());

	}

}
