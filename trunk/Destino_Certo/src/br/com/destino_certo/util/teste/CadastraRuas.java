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
import br.com.destino_certo.onibus.modelo.Onibus;
import br.com.destino_certo.parada.modelo.Parada;
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
//		pegarCodigosDeParadasGrandeRecife();
		associarParadasAOnibus();
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
	
	public static void associarParadasAOnibus(){
		RepositorioCodigoParada repositorioCodigoParada = new RepositorioCodigoParada();
		for(CodigosParadas codigosParadas :repositorioCodigoParada.listar()){
			System.out.println(codigosParadas.getCodigoParada());
		try {
			associar(codigosParadas.getCodigo(), codigosParadas.getCodigoParada());
		}catch (Exception e) {
			// TODO Auto-generated catch block
//			associar(codigosParadas.getCodigo(), codigosParadas.getCodigoParada());
			e.printStackTrace();
		}
		}
	}
	public static void testarCodigosParadas(){
		RepositorioCodigoParada repositorioCodigoParada = new RepositorioCodigoParada();
		for(CodigosParadas codigosParadas :repositorioCodigoParada.listar()){
			System.out.println(codigosParadas);
		}
	}
	
	public static void associar(int codigoGRParada, int codigoParada){
		Fachada fachada = Fachada.getInstance();
		Parada parada = fachada.paradaProcurar(new Long(codigoParada));
		Onibus onibus;
		List<Onibus> lista = new ArrayList<Onibus>();
		try{
			Document doc = Jsoup.connect("http://200.238.84.28/site/consulta/itinerarios_linhas_parada.asp?codigo_parada="+codigoGRParada).get();
            Elements bairro = doc.select("tr");
			boolean comecar = false;
			for(Element element : bairro){
			    String valor = element.text();
			    if(comecar==true){
			    String[] mestra = valor.split(" ");
			    if(!valor.isEmpty() && valor.length()>5){	
			      onibus = fachada.onibusProcurar(Long.parseLong(mestra[0]));
			      lista.add(onibus);
			       System.out.println(valor+ " Chave "+ mestra[0]+ "Custo "+ mestra[mestra.length-1] + valor.length());
			    }
			    }
			    if(valor.equals("Linha Nome Tarifa")){
			    	comecar = true;
			    }    }
			parada.setOnibus(lista);
			fachada.paradaEditar(parada);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static List<String> testey(){
		List<String> bairros = new ArrayList<String>();
		try {
			Document doc = Jsoup.connect("http://200.238.84.28/site/consulta/itinerarios_linhas_parada.asp?codigo_parada=7810").get();
            Elements bairro = doc.select("tr");
			boolean comecar = false;
			for(Element element : bairro){
			    String valor = element.text();
			    if(comecar==true){
			    String chave = element.val();
			    String[] mestra = valor.split(" ");
			    if(!valor.isEmpty() && valor.length()>5){
			    System.out.println(valor+ " Chave "+ mestra[0]+ "Custo "+ mestra[mestra.length-1] + valor.length());
			    }
			    }
			    if(valor.equals("Linha Nome Tarifa")){
			    	comecar = true;
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bairros;
	};
	
	
	public static List<String> pegarCodigosDeParadasGrandeRecife(){
		List<String> bairros = new ArrayList<String>();
		RepositorioCodigoParada repositorioCodigoParada = new RepositorioCodigoParada();
		try {
			Document doc = Jsoup.connect("http://200.238.84.28/site/consulta/itinerarios_linhas_parada.asp?codigo_parada=7810").get();
            Elements bairro = doc.select("option");
			CodigosParadas codigosParadas;
			for(Element element : bairro){
			    String valor = element.text();
			    String[] codigoParada = valor.split(" ");
			    String chave = element.val();
			    try{
			    codigosParadas = new CodigosParadas(Integer.parseInt(chave), valor,Integer.parseInt(codigoParada[0]));
			    repositorioCodigoParada.cadastrar(codigosParadas);
			    System.out.println(codigosParadas + " codigo "+ codigoParada[0]);
			    }catch(Exception e){
			    	
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bairros;
	};
	
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