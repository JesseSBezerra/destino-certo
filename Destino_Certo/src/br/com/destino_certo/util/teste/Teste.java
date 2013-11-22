package br.com.destino_certo.util.teste;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class Teste
 */
@WebServlet("/Teste")
public class Teste extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Teste() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HashMap<String,String> mapa = new HashMap<String,String>();
		mapa.put("message","My first JSON application");
		JSONObject jsonObject = JSONObject.fromObject(mapa);
		response.setContentType("application/json");
		String acao = request.getParameter("acao");
		String valor = "mamão";
		try{
		String outra = request.getParameter("outra");
		if(outra.equals("teste")){
			valor = request.getParameter("valor");
		}
		}catch(Exception e){
			
		}
		if(acao.equals("teste")){
		mapa.put("ola",valor);
		jsonObject.accumulateAll(mapa);
		}
		
		PrintWriter out = response.getWriter();
		out.print(jsonObject);
		out.flush();

	}

}
