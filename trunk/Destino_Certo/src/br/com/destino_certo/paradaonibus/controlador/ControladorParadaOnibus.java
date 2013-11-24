package br.com.destino_certo.paradaonibus.controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.destino_certo.onibus.modelo.Onibus;
import br.com.destino_certo.parada.modelo.Parada;
import br.com.destino_certo.util.decode.LatLng;
import br.com.destino_certo.util.fachada.Fachada;



public class ControladorParadaOnibus {
	
	private Fachada fachada;
	private List<Parada> listaR;
	
	public ControladorParadaOnibus() {
		// TODO Auto-generated constructor stub
		fachada = Fachada.getInstance();
		listaR = fachada.paradaListar();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map identificaOnibus(LatLng minhaPosicao,LatLng ondeQueroIr){
		 double paradaProximaDestino = verificaParadaProxima(ondeQueroIr);
		 Parada parada = paradaProxima(paradaProximaDestino, ondeQueroIr);
		 Map mapa = new HashMap();
		 mapa.put("ondeEstou", minhaPosicao);
		 mapa.put("parada", parada);
		 return paradaProxima(mapa);
	}
	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public Map identificaOnibus(LatLng minhaPosicao,LatLng ondeQueroIr){
//		 double paradaProximaDestino = verificaParadaProxima(ondeQueroIr);
//		 Parada parada = paradaProxima(paradaProximaDestino, ondeQueroIr);
//		 Map mapa = new HashMap();
//		 mapa.put("ondeEstou", minhaPosicao);
//		 mapa.put("parada", parada);
//		 return paradaProxima(mapa);
//	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map paradaProxima(Map mapa){
		LatLng ondeEstou = (LatLng) mapa.get("ondeEstou");
		Parada parada = (Parada) mapa.get("parada");
		System.out.println(parada.getLocal());
		boolean existeProximaParada = false;
		double paradaproxima = verificaParadaProxima(ondeEstou);
		Parada parada0 = paradaProxima(paradaproxima, ondeEstou);
		Onibus onibusComum = null;
		if(parada.getOnibus() != null && parada.getOnibus().size()>0 && parada0.getOnibus()!= null && parada0.getOnibus().size()>0){
		for(Onibus onibus:parada0.getOnibus()){
			for(Onibus onibus2:parada.getOnibus()){
		       if(onibus.getNumero()==onibus2.getNumero()){
		          mapa.put("onibus", onibus2);
		          mapa.put("paradaCorreta", parada0);
		          mapa.put("existeProximaParada", existeProximaParada);
		          fachada = Fachada.getInstance();
		          listaR = fachada.paradaListar();
		          return mapa;
		}
			}
		}
		}if(onibusComum==null){
			 mapa.put("existeProximaParada", true);
			paradaproxima = verificaParadaProxima(ondeEstou, parada0);
		}
		
		return paradaProxima(mapa);
	}
	
	public double verificaParadaProxima(LatLng minhaPosicao,Parada paradaRemover){
		List<Double> lista = new ArrayList<Double>();
		listaR.remove(paradaRemover);
		System.out.println(listaR.size());
		for(Parada parada:listaR){
			double valor = getDistancia(minhaPosicao.getLatitude(), minhaPosicao.getLongitude(), parada.getLatitude(), parada.getLongitude());
			lista.add(valor);
		}
		return Collections.min(lista);
	}
	
//	public double verificaParadaProxima(LatLng minhaPosicao,List<Onibus> listaOnibus){
//		List<Double> lista = new ArrayList<Double>();
//		for(Parada parada:listaR){
//			double valor = getDistancia(minhaPosicao.getLatitude(), minhaPosicao.getLongitude(), parada.getLatitude(), parada.getLongitude());
//			lista.add(valor);
//		}
//		return Collections.min(lista);
//	}
	
	public Parada pegaParadaMaisProxima(LatLng destino){
		double valor = verificaParadaProxima(destino);
		Parada paradaProximaDestino = paradaProxima(valor, destino);
		return paradaProximaDestino;
	}
	
	public Parada pegaParadaOrigem(LatLng origem,List<Onibus> lista,Parada paradaR){
		double valor = verificaParadaProxima(origem,lista,paradaR);
		Parada paradaProximaDestino = paradaProxima(valor, origem,lista);
		return paradaProximaDestino;
	}
	
	
	
	public double verificaParadaProxima(LatLng minhaPosicao){
		List<Double> lista = new ArrayList<Double>();
		for(Parada parada:listaR){
			double valor = getDistancia(minhaPosicao.getLatitude(), minhaPosicao.getLongitude(), parada.getLatitude(), parada.getLongitude());
			lista.add(valor);
		}
		return Collections.min(lista);
	}
	
	public double verificaParadaProxima(LatLng minhaPosicao,List<Onibus> listaOnibus,Parada paradaR){
		List<Double> lista = new ArrayList<Double>();
		listaR.remove(paradaR);
		for(Parada parada:listaR){
			for(Onibus onibusD:listaOnibus){
				for(Onibus onibusO:parada.getOnibus()){
					if(onibusD.getNumero()==onibusO.getNumero()){
					double valor = getDistancia(minhaPosicao.getLatitude(), minhaPosicao.getLongitude(), parada.getLatitude(), parada.getLongitude());
					lista.add(valor);
					}
				}
			}
		}
		return Collections.min(lista);
	}
	
	public List<Parada> removerParada(List<Parada> paradaSelecao,Parada parada){
		paradaSelecao.remove(parada);
		return paradaSelecao;
	}
	
	
	public Parada paradaProxima(double pega,LatLng minhaPosicao,List<Onibus> listaOnibus){
		for(Parada parada:listaR){
			double valor = getDistancia(minhaPosicao.getLatitude(), minhaPosicao.getLongitude(), parada.getLatitude(), parada.getLongitude());
			if(valor==pega){
					return parada;
					}
		}
		return null;
	}
	
	
	public Parada paradaProxima(double pega,LatLng minhaPosicao){
		for(Parada parada:listaR){
			double valor = getDistancia(minhaPosicao.getLatitude(), minhaPosicao.getLongitude(), parada.getLatitude(), parada.getLongitude());
			if(valor==pega){
				return parada;
			}
		}
		return null;
	}
	

	
    public double getDistancia(double latitude, double longitude, double latitudePto, double longitudePto){  
        double dlon, dlat, a, distancia;  
        dlon = longitudePto - longitude;  
        dlat = latitudePto - latitude;  
        a = Math.pow(Math.sin(dlat/2),2) + Math.cos(latitude) * Math.cos(latitudePto) * Math.pow(Math.sin(dlon/2),2);  
        distancia = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));  
        return 6378140 * distancia; /* 6378140 is the radius of the Earth in meters*/  
    } 
    
}
