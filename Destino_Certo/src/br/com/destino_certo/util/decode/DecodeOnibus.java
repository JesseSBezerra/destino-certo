package br.com.destino_certo.util.decode;

import java.util.List;
import java.util.Map;

import br.com.destino_certo.onibus.modelo.Onibus;
import br.com.destino_certo.parada.modelo.Parada;
import br.com.destino_certo.paradaonibus.controlador.ControladorParadaOnibus;

public class DecodeOnibus {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map tracaRotaOnibus(String cDestino, String cOrigem) {
		Parada paradaOrigem, paradaDestino, paradaIntermediaria = null;
		Onibus onibusOD, onibusDI = null;
		ControladorParadaOnibus controladorParada = new ControladorParadaOnibus();
		Map mapa = Decode.consultaMaps(cOrigem, cDestino);
		LatLng origem = (LatLng) mapa.get("startLocation");
		LatLng destino = (LatLng) mapa.get("endLocation");

		/* AQUI O SISTEMA PEGA A PARADA MAIS PROXIMA DA ORIGEM E DO DESTINO - 1 */

		paradaOrigem = controladorParada.pegaParadaMaisProxima(origem);
		paradaDestino = controladorParada.pegaParadaMaisProxima(destino);

		/* AQUI ACABA 1 */

		boolean passsaAqui = false;

		/*
		 * AQUI O SISTEMA VERIFICA SE O ONIBUS PASSA NA PARADA DE ORIGEM E NA
		 * PARADA DE DESTINO
		 */

		passsaAqui = verificaSePassaOBusao(paradaOrigem, paradaDestino);
		if (passsaAqui) {
			mapa.put("paradaOrigem", paradaOrigem);
			mapa.put("paradaDestino", paradaDestino);
			onibusDI = pegaBusaoEmComum(paradaOrigem, paradaDestino);
			mapa.put("onibus", onibusDI);
			mapa.put("origemDestino", true);
			return mapa;
		}

		while (!passsaAqui) {
			List<Onibus> lista = paradaDestino.getOnibus();
			paradaIntermediaria = controladorParada.pegaParadaOrigem(origem,
					lista, paradaOrigem);
			passsaAqui = verificaSePassaOBusao(paradaIntermediaria,
					paradaDestino);
		}

		onibusDI = pegaBusaoEmComum(paradaOrigem, paradaIntermediaria);
		onibusOD = pegaBusaoEmComum(paradaIntermediaria, paradaDestino);

		mapa.put("paradaOrigem", paradaOrigem);
		mapa.put("paradaDestino", paradaDestino);
		mapa.put("paradaIntermediaria", paradaIntermediaria);
		mapa.put("onibusDI", onibusDI);
		mapa.put("onibusOD", onibusOD);
		mapa.put("localOrigem", origem);
		mapa.put("localDestino", destino);
		mapa.put("origemDestino", false);

		return mapa;

	}

	public static boolean verificaSePassaOBusao(Parada paradaOrigem,
			Parada paradaDestino) {
		for (Onibus onibusO : paradaOrigem.getOnibus()) {
			for (Onibus onibusD : paradaDestino.getOnibus()) {
				if (onibusO.getNumero() == onibusD.getNumero()) {
					return true;
				}
			}
		}
		return false;
	}

	public static Onibus pegaBusaoEmComum(Parada paradaOrigem,
			Parada paradaDestino) {
		for (Onibus onibusO : paradaOrigem.getOnibus()) {
			for (Onibus onibusD : paradaDestino.getOnibus()) {
				if (onibusO.getNumero() == onibusD.getNumero()) {
					return onibusD;
				}
			}
		}
		return null;
	}

}
