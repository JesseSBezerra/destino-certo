package br.com.destino_certo.endereco.controlador;

import java.text.Normalizer;
import java.util.List;

import br.com.destino_certo.endereco.modelo.Endereco;
import br.com.destino_certo.endereco.modelo.IRepositorioEndereco;

public class ControladorEndereco {

	private IRepositorioEndereco iRepositorioEndereco;
	
	public ControladorEndereco(IRepositorioEndereco iRepositorioEndereco) {
		// TODO Auto-generated constructor stub
		this.iRepositorioEndereco = iRepositorioEndereco;
	}
	
	public void cadastrar(Endereco endereco){
		if(verifica(endereco)){
		endereco.setLogradouro(removeAcentos(endereco.getLogradouro().trim()));	
		endereco.setCodeBusca(rotinaCadastro(endereco));
		iRepositorioEndereco.cadastrar(endereco);
		}
	}
	
	public Endereco procurar(String logradouro){
		try{
			return iRepositorioEndereco.procurar(logradouro.trim());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String editar(Endereco endereco){
		String mensagem = "";
		try{
			iRepositorioEndereco.remover(endereco);
			cadastrar(endereco);
			mensagem = "Editado com sucesso!";
		}catch(Exception e){
			mensagem = "Erro ao editar!";
		}
		return mensagem;
	}
	
	public String remover(Endereco endereco){
		String mensagem = "";
		try{
			iRepositorioEndereco.remover(endereco);
			mensagem = "Removido com sucesso!";
		}catch(Exception e){
			mensagem = "Erro ao remover!";
		}
		return mensagem;
	}
	
	

	public String removeAcentos(String str) {
		  str = Normalizer.normalize(str, Normalizer.Form.NFD);
		  str = str.replaceAll("[^\\p{ASCII}]", "");
		  return str;
		}
	
	public boolean verifica(Endereco endereco){
		boolean existe = true;
		try{
		Endereco endereco2 = iRepositorioEndereco.procurar(endereco.getLogradouro().trim());
		if(endereco2 != null){
			System.out.println(endereco2.getLogradouro());
			existe = false;
		}
		}catch(Exception e){
			
		}
		return existe;
	}

	
	public String rotinaCadastro(Endereco endereco){
		String concat = null;
		try{
			String[] enderecoCompleo = endereco.getLogradouro().split(" ");
			for(int i = 0;i<enderecoCompleo.length;i++){				
				concat = concat + removeAcentos(enderecoCompleo[i])+"+";
			}
			concat = concat.replaceAll("null", "");
			concat = concat +alteraNome(endereco.getBairro())+""+endereco.getCidade()+"+BR";
		}catch(Exception e){
			
		}
		return concat;
	}
	
	private String alteraNome(String nome){
		nome = removeAcentos(nome);
		String[] stringQuebrado = nome.split(" ");	
		String concat = "";
		for(int i = 0;i<stringQuebrado.length;i++){				
			concat = concat + stringQuebrado[i]+"+";
		}
		return concat;
	}
	
	
	
	public List<Endereco> lista(){
		return iRepositorioEndereco.listar();
	}
	
}
