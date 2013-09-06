package br.com.destino_certo.parada.modelo;

import java.util.List;


public interface IRepositorioParada {

public void cadastrar(Parada parada) throws ParadaJaCadastradaExcepetion;
public void editar(Parada parada) throws ParadaNaoEncontradaException;
public void remover(Parada parada) throws ParadaNaoEncontradaException;
public Parada procurar(Long numero) throws ParadaNaoEncontradaException;
public List<Parada> listar();
public List<Parada> listar(String nomeCampo, boolean valorCampo);
public List<Parada> listar(String nomeCampo, Long valorCampo);
}
