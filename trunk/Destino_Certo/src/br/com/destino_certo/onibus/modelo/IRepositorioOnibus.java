package br.com.destino_certo.onibus.modelo;

import java.util.List;

public interface IRepositorioOnibus {
	
	public void cadastrar(Onibus onibus) throws OnibusJaCadastradoException;
	public void editar(Onibus onibus) throws OnibusNaoEncontradoException;
	public void remover(Onibus onibus) throws OnibusNaoEncontradoException;
	public Onibus procurar(Long numero) throws OnibusNaoEncontradoException;
	public List<Onibus> listar();
	public List<Onibus> listar(String nomeCampo, boolean valorCampo);

}
