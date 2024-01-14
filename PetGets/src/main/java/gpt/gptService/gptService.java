package gpt.gptService;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;


import java.io.*;
import java.net.*;

import org.json.JSONException;
import org.json.JSONObject;
import Animal.dao.AnimalDAO;
import Animal.dao.DAO;
import Animal.model.Animal;
import gpt.model.*;
import spark.Request;
import spark.Route;
import Usuario.model.Usuario;
import java.sql.*;
import java.util.List;
import spark.Spark.*;
import spark.Response;


public class gptService{
	
	
	public gptService() {
	    
        port(5554);
        staticFiles.location("/public");
	}
	
	public String list() throws Exception {
		AnimalDAO dao = new AnimalDAO();
		
		
		Animal[] animais = dao.getAll();
    	JSONObject users = new JSONObject();
    	
    	for(int i = 0; i < animais.length; i++ ) {
    		JSONObject user = new JSONObject();
    		user.put("idadeAnimal", animais[i].getIdade());
    		user.put("nomeAnimal", animais[i].getNome());
    		user.put("sexoDoAnimal", animais[i].getSexo());
    		user.put("racaAnimal", animais[i].getRaca());
    		user.put("especieAnimal", animais[i].getEspecie());
    		user.put("descricaoAnimal", animais[i].getDescricao());
    		users.append("animais", user);
    	}
    	return users.toString();
    	
	}
	
	public String pergunta(Request request, Response response) throws JSONException {
		response.header("Access-Control-Allow-Origin", "*");
		response.header("Access-Control-Allow-Methods", "POST,GET");
		response.header("Access-Control-Allow-Headers", "*");
		response.header("Access-Control-Max-Age", "86400");
		
		JSONObject users = new JSONObject(request.body());
		String pergunta = users.getString("pergunta");
		
		return pergunta;
		
		
		
	}
	
	
	public String pedido(Request request, Response response, String animais) throws Exception{
		response.header("Access-Control-Allow-Origin", "*");
		response.header("Access-Control-Allow-Methods", "POST,GET");
		response.header("Access-Control-Allow-Headers", "*");
		response.header("Access-Control-Max-Age", "86400");
		
		JSONObject users = new JSONObject(request.body());
		String pergunta = users.getString("pergunta");
		
		
		
		GPT gpt = new GPT();
		
		String pedido = "Seu trabalho é ajudar um usuário a escolher um pet. " + "Seja direto e conciso,"
				+ " nossos animais são: " + animais + " . Responda apenas, e somente apenas, à perguntas relacionadas à animais,"
						+ " a requisicao do usuário é: " + pergunta;
		
		
		URI uri = new URI(gpt.getUrl());
		URL object = uri.toURL();
		
		HttpURLConnection connection = (HttpURLConnection) object.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Authorization", "Bearer " + gpt.getAPI_KEY());
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setDoOutput(true);
		
		String body = "{\"model\": \"" + gpt.getModel() + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" 
				+ pedido.replace("\"", "\\\"") + "\"}], \"temperature\": 1.0}";
		
		System.out.println(body);
		
		 OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
		 wr.write(body);
		 wr.flush();
		 wr.close();
         
         int responseCode = connection.getResponseCode();
         System.out.println("Código de Resposta: " + responseCode);
         System.out.println("Código de Resposta: " + new BufferedReader(new InputStreamReader(connection.getInputStream())));
         

         BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
         String inputLine;
         StringBuilder Response = new StringBuilder();

         while ((inputLine = in.readLine()) != null) {
             Response.append(inputLine);
         }
         String resposta = Response.toString();
         response.body(resposta);
         in.close();

         // Imprimir a resposta do servidor
         System.out.println("Resposta do Servidor: " + resposta);
         
         return resposta;
	}
	
	public String fazerPedido(Request request, Response response) throws Exception {
		String animais = list();
		
		String resposta = pedido(request, response, animais);
		return resposta;
		
		
		
		
	}
	
	
	
}