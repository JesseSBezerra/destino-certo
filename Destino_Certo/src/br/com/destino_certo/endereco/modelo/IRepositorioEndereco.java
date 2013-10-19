package br.com.destino_certo.endereco.modelo;

import java.util.List;

public interface IRepositorioEndereco {

	public void cadastrar(Endereco endereco);
	public void editar(Endereco endereco);
	public void remover(Endereco endereco);
	public Endereco procurar(Integer id);
	public Endereco procurar(String logradouro);
	public List<Endereco> listar();
	
	
}
