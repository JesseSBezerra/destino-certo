package br.com.destino_certo.itinerario.modelo;

import java.util.List;


public interface IRepositorioItinerario {

public void cadastrar(Itinerario itinerario) throws ItinerarioJaCadastradoExcepetion;
public void editar(Itinerario itinerario) throws ItinerarioNaoEncontradoException;
public void remover(Itinerario itinerario) throws ItinerarioNaoEncontradoException;
public Itinerario procurar(Long numero) throws ItinerarioNaoEncontradoException;
public List<Itinerario> listar();
public List<Itinerario> listar(String nomeCampo, boolean valorCampo);
}
