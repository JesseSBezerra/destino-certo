package br.com.destino_certo.taxi.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import br.com.destino_certo.taxi.modelo.IRepositorioTaxi;
import br.com.destino_certo.taxi.modelo.Taxi;
import br.com.destino_certo.taxi.modelo.TaxiJaCadastradoExcepetion;
import br.com.destino_certo.taxi.modelo.TaxiNaoEncontradoException;
import br.com.destino_certo.util.decode.Http;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ControladorTaxi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IRepositorioTaxi iRepositorioTaxi;

	public ControladorTaxi(IRepositorioTaxi iRepositorioTaxi) {
		// TODO Auto-generated constructor stub
		this.iRepositorioTaxi = iRepositorioTaxi;
	}
	
	public String cadastrar(Taxi taxi){
		String mensagem = null;
		if(existe(taxi)){
			mensagem = "Taxi já cadastrada!";
		}else{
			try {
				iRepositorioTaxi.cadastrar(taxi);
				mensagem = "Cadastrado com sucesso!";
			} catch (TaxiJaCadastradoExcepetion e) {
				// TODO Auto-generated catch block
				mensagem = e.getMessage();
			}
		}
		return mensagem;
	}
	
	public String editar(Taxi taxi){
		String mensagem = null;
		if(existe(taxi)){
			try {
				iRepositorioTaxi.editar(taxi);
				mensagem = "Editado com sucesso";
			} catch (TaxiNaoEncontradoException e) {
				// TODO Auto-generated catch block
				mensagem = e.getMessage();
			}
		}else{
			mensagem = "Erro ao editar";
		}
		return mensagem;
	}
	
	public String remover(Taxi taxi){
		String mensagem = null;
		if(existe(taxi)){
			try {
				iRepositorioTaxi.remover(taxi);
				mensagem = "Removido com sucesso";
			} catch (TaxiNaoEncontradoException e) {
				// TODO Auto-generated catch block
				mensagem = e.getMessage();
			}
		}else{
			mensagem = "Erro ao Remover";
		}
		return mensagem;
	}
	
	public Taxi procurar(Long numero){
		Taxi taxi = null;
		try {
			taxi = iRepositorioTaxi.procurar(numero);
		} catch (TaxiNaoEncontradoException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		return taxi;
	}
	
//	public Taxi procurar(Long numeroIti, Integer ordemTaxi){
//		Taxi taxi = null;
//		try {
//			taxi = iRepositorioTaxi.procurar("itinerario.numero", numeroIti, "ordem", ordemTaxi);
//		} catch (TaxiNaoEncontradoException e) {
//			// TODO Auto-generated catch block
//			System.err.println(e.getMessage());
//		}
//		return taxi;
//	}
	
	public List<Taxi> listar(){
		return iRepositorioTaxi.listar();
	}
	
	public List<Taxi> listar(String nomeCampo,boolean valorCampo){
		return iRepositorioTaxi.listar(nomeCampo,valorCampo);
	}
	
	public List<Taxi> listar(String nomeCampo,Long valorCampo){
		return iRepositorioTaxi.listar(nomeCampo,valorCampo);
	}
	
	public Map<String, String> consultaMaps(String origem, String destino){
	JSONParser parser = new JSONParser();
	String consulta = "http://maps.googleapis.com/maps/api/directions/json?origin="+origem+"&destination="+destino+"&sensor=false&mode=driving";
     Map<String, String> mapa = new HashMap<String, String>();	
	try {
		Http http = new Http();
        String retorno = http.chamaUrl(consulta);
		Object obj = parser.parse(retorno);
		JSONObject jsonObject = (JSONObject) obj;
		JSONArray msg = (JSONArray) jsonObject.get("routes");
		Object object = msg.get(0);
		JSONObject jsonObject1 = (JSONObject) object;
		
		JSONArray legs = (JSONArray) jsonObject1.get("legs");
		Object legsQ = legs.get(0);
		JSONObject jsonObjectL = (JSONObject) legsQ;
		JSONObject jsonObjectDs = (JSONObject) jsonObjectL.get("distance");
		String distancia = (String) jsonObjectDs.get("text");
		Long metros = (Long) jsonObjectDs.get("value");
		
		JSONObject jsonObject2 = (JSONObject) jsonObject1.get("overview_polyline");
		String polilyne = (String) jsonObject2.get("points");
		
		mapa.put("poly", polilyne);
		mapa.put("distancia", distancia);
		mapa.put("metros", metros.toString());
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
		return mapa;
	}
	
	private boolean existe(Taxi taxi){
		boolean jaCadastrado = false;
		try {
			Taxi taxiExiste = procurar(taxi.getNumero());
			if(!taxiExiste.equals(null)){
				jaCadastrado = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return jaCadastrado;
	}

}
