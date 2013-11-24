package br.com.destino_certo.util.decode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Decode {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map consultaMaps(String origem, String destino){
		JSONParser parser = new JSONParser();
		String consulta = "http://maps.googleapis.com/maps/api/directions/json?origin="+origem+"&destination="+destino+"&sensor=false&mode=driving";
	     Map mapa = new HashMap();	
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
			
            //Trabalando pegando a localização de partidada e fim com base em latitude e longitude
			
		    JSONObject jsonObjectSL = (JSONObject)jsonObjectL.get("start_location");
		    Double start_lat = (Double) jsonObjectSL.get("lat");
		    Double start_lon = (Double) jsonObjectSL.get("lng");
		    LatLng startLocation = new LatLng(start_lat, start_lon);
		    
		    JSONObject jsonObjectEL = (JSONObject)jsonObjectL.get("end_location");
		    Double end_lat = (Double) jsonObjectEL.get("lat");
		    Double end_lon = (Double) jsonObjectEL.get("lng");
		    LatLng endLocation = new LatLng(end_lat, end_lon);
		    
			//fim
			
			JSONObject jsonObjectDs = (JSONObject) jsonObjectL.get("distance");
			String distancia = (String) jsonObjectDs.get("text");
			Long metros = (Long) jsonObjectDs.get("value");
			
			JSONObject jsonObject2 = (JSONObject) jsonObject1.get("overview_polyline");
			String polilyne = (String) jsonObject2.get("points");
			
			mapa.put("startLocation", startLocation);
			mapa.put("endLocation", endLocation);
			mapa.put("poly", polilyne);
			mapa.put("distancia", distancia);
			mapa.put("metros", metros.toString());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
			return mapa;
		}
	
	public static List<LatLng> decodePolyLine(String poly) {
			  ArrayList<LatLng> poly1 = new ArrayList<LatLng>();
			  int index = 0, len = poly.length();
			  int lat = 0, lng = 0;
			  while (index < len) {
			   int b, shift = 0, result = 0;
			   do {
			    b = poly.charAt(index++) - 63;
			    result |= (b & 0x1f) << shift;
			    shift += 5;
			   } while (b >= 0x20);
			   int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			   lat += dlat;
			   shift = 0;
			   result = 0;
			   do {
			    b = poly.charAt(index++) - 63;
			    result |= (b & 0x1f) << shift;
			    shift += 5;
			   } while (b >= 0x20);
			   int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			   lng += dlng;
			   LatLng p = new LatLng(((lat / 1E5)),
			     ((lng / 1E5)));
			   poly1.add(p);
			  }
			  return poly1;
	}
}

