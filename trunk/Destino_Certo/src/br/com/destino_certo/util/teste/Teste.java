package br.com.destino_certo.util.teste;


import java.util.List;

import javax.faces.context.FacesContext;

import br.com.destino_certo.endereco.modelo.Endereco;
import br.com.destino_certo.itinerario.controlador.ControladorItinerario;
import br.com.destino_certo.itinerario.modelo.Itinerario;
import br.com.destino_certo.itinerario.modelo.RepositorioItinerario;
import br.com.destino_certo.onibus.controlador.ControladorOnibus;
import br.com.destino_certo.onibus.modelo.Onibus;
import br.com.destino_certo.onibus.modelo.RepositorioOnibus;
import br.com.destino_certo.parada.modelo.Parada;
import br.com.destino_certo.taxi.controlador.ControladorTaxi;
import br.com.destino_certo.taxi.modelo.RepositorioTaxi;
import br.com.destino_certo.usuario.modelo.Usuario;
import br.com.destino_certo.util.decode.Decode;
import br.com.destino_certo.util.decode.LatLng;
import br.com.destino_certo.util.fachada.Fachada;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//    List<Parada> list ;
//    
//    Fachada fachada = Fachada.getInstance();
//   Itinerario itinerario = fachada.itinerarioProcurar(102L, "teste");
//   System.out.println(itinerario.getNumero());
////    list = fachada.paradaListar("itinerario.numero", 8715L);
////    System.out.println(list.size());
//    
//    Parada parada = fachada.paradaProcurar(8714L,2);
//    System.out.println(parada.getNome());
		
//		ControladorTaxi controladorTaxi = new ControladorTaxi(new RepositorioTaxi());
//		controladorTaxi.consultaMaps("Avenida+Conselheiro+Rosa+e+Silva+Aflitos+Recife+BR", "Avenida+Santos+Dumont+Aflitos+Recife+BR");
//		for(LatLng latLng:Decode.decodePolyLine(controladorTaxi.consultaMaps("Avenida+Conselheiro+Rosa+e+Silva+Aflitos+Recife+BR", "Avenida+Santos+Dumont+Aflitos+Recife+BR").get("poly"))){
//			System.out.println(latLng.getLatitude()+"long :"+latLng.getLongitude());
//		}
		Endereco endereco = Fachada.getInstance().procurar("Rua Samuel Campelo");
		System.out.println(endereco.getCodeBusca());
	}

}
