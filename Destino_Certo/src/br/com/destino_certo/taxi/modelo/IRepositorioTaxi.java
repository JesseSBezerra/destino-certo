package br.com.destino_certo.taxi.modelo;

import java.util.List;


public interface IRepositorioTaxi {

public void cadastrar(Taxi taxi) throws TaxiJaCadastradoExcepetion;
public void editar(Taxi taxi) throws TaxiNaoEncontradoException;
public void remover(Taxi taxi) throws TaxiNaoEncontradoException;
public Taxi procurar(Long numero) throws TaxiNaoEncontradoException;
public Taxi procurar(String nomeCampo, Long valorCampo,String nomeCampo0, Integer valorCampo0) throws TaxiNaoEncontradoException;
public List<Taxi> listar();
public List<Taxi> listar(String nomeCampo, boolean valorCampo);
public List<Taxi> listar(String nomeCampo, Long valorCampo);
}
