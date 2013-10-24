package br.com.destino_certo.util.teste;



import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.destino_certo.endereco.modelo.Endereco;
import br.com.destino_certo.util.fachada.Fachada;

public class CadastraRuas {

	/**
	 * @param args
	 */
	static Fachada fachada = Fachada.getInstance();
	
	public static void cadastrarEndereco(String rua, String bairro){
		Endereco endereco = new Endereco();
		endereco.setBairro(bairro);
		endereco.setCidade("Recife");
		endereco.setLogradouro(rua);
		fachada.enderecoCadastrar(endereco);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cadastraRuas(pegarBairros()); 
	  }
	
	public static void cadastraRuas(List<String> bairros){
		for(String bairro:bairros){
		    	
			try {
				Document doc = Jsoup.connect("http://www.brasilao.com/cep/recife/"+bairro+"/").get();
				Elements ruas = doc.select("a[href]");
				for(Element rua : ruas){
				    String valor = rua.text();
				    if(rua.attr("href").startsWith("/recife/")){
					System.out.println(valor);
					cadastrarEndereco(valor, bairro);
				    }
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				cadastrarRua(bairro);
			}
		}
	}
	
	public static void cadastrarRua(String bairro){
		try {
			Document doc = Jsoup.connect("http://www.brasilao.com/cep/recife/"+bairro+"/").get();
			Elements ruas = doc.select("a[href]");
			for(Element rua : ruas){
			    String valor = rua.text();
			    if(rua.attr("href").startsWith("/recife/")){
				System.out.println(valor);
				cadastrarEndereco(valor, bairro);
			    }
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("O Erro percistiu :"+e.getMessage()+ bairro);
		}	
	}
	
	public static List<String> pegarBairros(){
		List<String> bairros = new ArrayList<String>();
		try {
			Document doc = Jsoup.connect("http://www.brasilao.com/cep/recife/").get();
            Elements bairro = doc.select("div.countylist");
			
			for(Element element : bairro){
			    String valor = element.text();
			    if(!valor.contains("-")){
				bairros.add(concatena(valor));
				System.out.println(concatena(valor));
			    }
			    
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bairros;
	}
	
	public static String removeAcentos(String str) {
		  str = Normalizer.normalize(str, Normalizer.Form.NFD);
		  str = str.replaceAll("[^\\p{ASCII}]", "");
		  return str;
		}
	
	public static String concatena(String str){
		str = removeAcentos(str).toLowerCase();
		String[] stringQuebrado = str.split(" ");	
		String concat = "";
		
		for(int i = 0;i<stringQuebrado.length;i++){
			if(i>0){
			concat = concat + "-" +stringQuebrado[i];
			}else{
				concat = stringQuebrado[i];
			}
			
		}
		
		return concat;
	}
	 
	}