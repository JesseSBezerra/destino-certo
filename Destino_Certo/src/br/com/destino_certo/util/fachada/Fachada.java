package br.com.destino_certo.util.fachada;

import java.util.List;

import br.com.destino_certo.itinerario.controlador.ControladorItinerario;
import br.com.destino_certo.itinerario.modelo.IRepositorioItinerario;
import br.com.destino_certo.itinerario.modelo.Itinerario;
import br.com.destino_certo.itinerario.modelo.RepositorioItinerario;
import br.com.destino_certo.onibus.controlador.ControladorOnibus;
import br.com.destino_certo.onibus.modelo.IRepositorioOnibus;
import br.com.destino_certo.onibus.modelo.Onibus;
import br.com.destino_certo.onibus.modelo.RepositorioOnibus;
import br.com.destino_certo.parada.controlador.ControladorParada;
import br.com.destino_certo.parada.modelo.IRepositorioParada;
import br.com.destino_certo.parada.modelo.Parada;
import br.com.destino_certo.parada.modelo.RepositorioParada;
import br.com.destino_certo.taxi.controlador.ControladorTaxi;
import br.com.destino_certo.taxi.modelo.IRepositorioTaxi;
import br.com.destino_certo.taxi.modelo.RepositorioTaxi;
import br.com.destino_certo.taxi.modelo.Taxi;
import br.com.destino_certo.usuario.controlador.ControladorUsuario;
import br.com.destino_certo.usuario.modelo.IRepositorioUsuario;
import br.com.destino_certo.usuario.modelo.RepositorioUsuario;
import br.com.destino_certo.usuario.modelo.Usuario;

public class Fachada {

	private static Fachada instance;
	private ControladorUsuario controladorUsuario;
	private IRepositorioUsuario iRepositorioUsuario;
	private ControladorOnibus controladorOnibus;
	private IRepositorioOnibus iRepositorioOnibus;
	private ControladorItinerario controladorItinerario;
	private IRepositorioItinerario iRepositorioItinerario;
	private ControladorParada controladorParada;
	private IRepositorioParada iRepositorioParada;
	private ControladorTaxi controladorTaxi;
	private IRepositorioTaxi iRepositorioTaxi;

	public static Fachada getInstance() {
		if (instance == null) {
			instance = new Fachada();
		}
		return instance;

	}

	private Fachada() {
		// TODO Auto-generated constructor stub
		instanciarRepositorios();
	}

	private void instanciarRepositorios() {
		iRepositorioUsuario = new RepositorioUsuario();
		controladorUsuario = new ControladorUsuario(iRepositorioUsuario);
		iRepositorioOnibus = new RepositorioOnibus();
		controladorOnibus = new ControladorOnibus(iRepositorioOnibus);
		iRepositorioItinerario = new RepositorioItinerario();
		controladorItinerario = new ControladorItinerario(
				iRepositorioItinerario);
		iRepositorioParada = new RepositorioParada();
		controladorParada = new ControladorParada(iRepositorioParada);
	    iRepositorioTaxi = new RepositorioTaxi();
	    controladorTaxi = new ControladorTaxi(iRepositorioTaxi);
	}

	// TODO USUARIO

	public String usuarioCadastrar(Usuario usuario) {
		return controladorUsuario.cadastrar(usuario);
	}

	public String usuarioRemover(Usuario usuario) {
		return controladorUsuario.remover(usuario.getLogin());
	}

	public String usuarioEditar(Usuario usuario) {
		return controladorUsuario.editar(usuario);
	}

	public Usuario usuarioProcurar(String login) {
		return controladorUsuario.procurar(login);
	}

	public List<Usuario> listar() {
		return controladorUsuario.listar();
	}

	// TODO ONIBUS

	public String onibusCadastrar(Onibus onibus) {
		return controladorOnibus.cadastrar(onibus);
	}

	public String onibusRemover(Onibus onibus) {
		return controladorOnibus.remover(onibus);
	}

	public String onibusEditar(Onibus onibus) {
		return controladorOnibus.editar(onibus);
	}

	public Onibus onibusProcurar(Long numero) {
		return controladorOnibus.procurar(numero);
	}

	public List<Onibus> onibusListar() {
		return controladorOnibus.listar();
	}

	public List<Onibus> onibusListar(String nomeCampo, boolean valorCampo) {
		return controladorOnibus.listar(nomeCampo, valorCampo);
	}

	// TODO ITINERARIO

	public String itinerarioCadastrar(Itinerario itinerario) {
		return controladorItinerario.cadastrar(itinerario);
	}

	public String itinerarioRemover(Itinerario itinerario) {
		return controladorItinerario.remover(itinerario);
	}

	public String itinerarioEditar(Itinerario itinerario) {
		return controladorItinerario.editar(itinerario);
	}

	public Itinerario itinerarioProcurar(Long numero) {
		return controladorItinerario.procurar(numero);
	}

	public Itinerario itinerarioProcurar(Long numero, String nome) {
		return controladorItinerario.procurarn(numero, nome);
	}

	public List<Itinerario> itinerarioListar() {
		return controladorItinerario.listar();
	}

	public List<Itinerario> itinerarioListar(String nomeCampo,
			boolean valorCampo) {
		return controladorItinerario.listar(nomeCampo, valorCampo);
	}

	// TODO PARADA

	public String paradaCadastrar(Parada parada) {
		return controladorParada.cadastrar(parada);
	}

	public String paradaRemover(Parada parada) {
		return controladorParada.remover(parada);
	}

	public String paradaEditar(Parada parada) {
		return controladorParada.editar(parada);
	}

	public Parada paradaProcurar(Long numero) {
		return controladorParada.procurar(numero);
	}

	public Parada paradaProcurar(Long numeroIti, Integer ordemParada) {
		return controladorParada.procurar(numeroIti, ordemParada);
	}

	public List<Parada> paradaListar() {
		return controladorParada.listar();
	}

	public List<Parada> paradaListar(String nomeCampo, boolean valorCampo) {
		return controladorParada.listar(nomeCampo, valorCampo);
	}

	public List<Parada> paradaListar(String nomeCampo, Long valorCampo) {
		return controladorParada.listar(nomeCampo, valorCampo);
	}

	// TODO TAXI

	public String taxiCadastrar(Taxi taxi) {
		return controladorTaxi.cadastrar(taxi);
	}

	public String taxiRemover(Taxi taxi) {
		return controladorTaxi.remover(taxi);
	}

	public String taxiEditar(Taxi taxi) {
		return controladorTaxi.editar(taxi);
	}

	public Taxi taxiProcurar(Long numero) {
		return controladorTaxi.procurar(numero);
	}

	public Taxi taxiProcurar(Long numeroIti, Integer ordemTaxi) {
		return controladorTaxi.procurar(numeroIti, ordemTaxi);
	}

	public List<Taxi> taxiListar() {
		return controladorTaxi.listar();
	}

	public List<Taxi> taxiListar(String nomeCampo, boolean valorCampo) {
		return controladorTaxi.listar(nomeCampo, valorCampo);
	}

	public List<Taxi> taxiListar(String nomeCampo, Long valorCampo) {
		return controladorTaxi.listar(nomeCampo, valorCampo);
	}

}
