package br.com.destino_certo.util.teste;


import java.util.List;

import br.com.destino_certo.itinerario.controlador.ControladorItinerario;
import br.com.destino_certo.itinerario.modelo.Itinerario;
import br.com.destino_certo.itinerario.modelo.RepositorioItinerario;
import br.com.destino_certo.onibus.controlador.ControladorOnibus;
import br.com.destino_certo.onibus.modelo.Onibus;
import br.com.destino_certo.onibus.modelo.RepositorioOnibus;
import br.com.destino_certo.parada.modelo.Parada;
import br.com.destino_certo.usuario.modelo.Usuario;
import br.com.destino_certo.util.fachada.Fachada;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    List<Parada> list ;
    
    Fachada fachada = Fachada.getInstance();
    
    list = fachada.paradaListar("itinerario.numero", 8715L);
    System.out.println(list.size());
    
    Parada parada = fachada.paradaProcurar(8714L,2);
    System.out.println(parada.getNome());
	}

}
