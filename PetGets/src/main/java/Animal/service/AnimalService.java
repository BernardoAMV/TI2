package Animal.service;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import org.json.JSONException;
import org.json.JSONObject;
import Animal.dao.AnimalDAO;
import Animal.dao.DAO;
import Animal.model.Animal;
import spark.Request;
import spark.Route;
import Usuario.model.Usuario;
import java.sql.*;
import java.util.List;
import spark.Spark.*;
import spark.Response;


public class AnimalService {
	

	
	public AnimalService() {
    
        port(5554);
        staticFiles.location("/public");
	}
        

        
        public String list(Request request, Response response)throws JSONException {
        	AnimalDAO dao = new AnimalDAO();
        	response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "POST,GET");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Max-Age", "86400");
			
        	
        	
        	Animal[] animais = dao.getAll();
        	JSONObject users = new JSONObject();
        	
        	for(int i = 0; i < animais.length; i++ ) {
        		JSONObject user = new JSONObject();
        		user.put("idAnimal", animais[i].getId());
        		user.put("idadeAnimal", animais[i].getIdade());
        		user.put("nomeAnimal", animais[i].getNome());
        		user.put("sexoDoAnimal", animais[i].getSexo());
        		user.put("racaAnimal", animais[i].getRaca());
        		user.put("especieAnimal", animais[i].getEspecie());
        		user.put("descricaoAnimal", animais[i].getDescricao());
        		user.put("fotoAnimal", animais[i].getFoto());
        		user.put("responsavel", animais[i].getResponsavel());
        		user.put("nomeResponsavel", animais[i].getnomeResponsavel());
        		user.put("contatoResponsavel", animais[i].getcontatoResponsavel());
        		users.append("animais", user);
        	}
        	
        	
        	
        	System.out.println(users.toString());
        	
        	return users.toString();
        }
        public String listMeusPets(Request request, Response response)throws JSONException {
        	AnimalDAO dao = new AnimalDAO();
        	response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "POST,GET");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Max-Age", "86400");
			
        	String responsavel = request.queryParams("responsavel");
        	
        	Animal[] animais = dao.getAllMyPets(responsavel);
        	JSONObject users = new JSONObject();
        	
        	for(int i = 0; i < animais.length; i++ ) {
        		JSONObject user = new JSONObject();
        		user.put("idAnimal", animais[i].getId());
        		user.put("idadeAnimal", animais[i].getIdade());
        		user.put("nomeAnimal", animais[i].getNome());
        		user.put("sexoDoAnimal", animais[i].getSexo());
        		user.put("racaAnimal", animais[i].getRaca());
        		user.put("especieAnimal", animais[i].getEspecie());
        		user.put("descricaoAnimal", animais[i].getDescricao());
        		user.put("fotoAnimal", animais[i].getFoto());
        		user.put("responsavel", animais[i].getResponsavel());
        		user.put("nomeResponsavel", animais[i].getnomeResponsavel());
        		user.put("contatoResponsavel", animais[i].getcontatoResponsavel());
        		users.append("animais", user);
        	}
        	
        	
        	
        	System.out.println(users.toString());
        	
        	return users.toString();
        }
        
        public String insert(Request request, Response response) throws Exception{
        	AnimalDAO dao = new AnimalDAO();
        	
        	response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "POST,GET");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Max-Age", "86400");
			
        	JSONObject users = new JSONObject(request.body());
        	
        	int idade = users.getInt("idadeAnimal");
        	String nome = users.getString("nomeAnimal");
        	String sexo = users.getString("sexoDoAnimal");
        	String raca = users.getString("racaAnimal");
        	String especie = users.getString("especieAnimal");
        	String descricao = users.getString("descricaoAnimal");
        	String responsavel = users.getString("responsavel");
        	String foto = users.getString("fotoAnimal");
        	String nomeResponsavel = users.getString("nomeResponsavel");
        	String contatoResponsavel = users.getString("contatoResponsavel");
        	Animal user = new Animal(idade, nome, sexo, raca, especie, descricao, foto, responsavel,nomeResponsavel,contatoResponsavel, -1);
        	dao.insert(user);
        	
        	
        	System.out.println(user.toString());
        	
        	return users.toString();
        }
        
        public String delete(Request request, Response response){
        	AnimalDAO dao = new AnimalDAO();
        	response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "POST,GET");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Max-Age", "86400");
			
        	
        	
        	JSONObject users = new JSONObject();
        	
        	int id = Integer.parseInt(request.queryParams("id")); //
        	dao.delete(id);
        	
        	System.out.println(users.toString());
        	
        	return users.toString();
        }
        
        public String update(Request request, Response response)throws Exception{
        	AnimalDAO dao = new AnimalDAO();
        	response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "POST,GET");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Max-Age", "86400");
			
        	JSONObject users = new JSONObject(request.body());
        	
        	int idade = users.getInt("idadeAnimal");
        	String nome = users.getString("nomeAnimal");
        	String sexo = users.getString("sexoDoAnimal");
        	String raca = users.getString("racaAnimal");
        	String especie = users.getString("especieAnimal");
        	String descricao = users.getString("descricaoAnimal");
        	String foto = users.getString("fotoAnimal");
        	String responsavel = users.getString("responsavel");
        	String nomeResponsavel = users.getString("nomeResponsavel");
        	String contatoResponsavel = users.getString("contatoResponsavel");
        	Animal user = new Animal(idade, nome, sexo, raca, especie, descricao, foto, responsavel,nomeResponsavel,contatoResponsavel, -1);
        	
        	boolean resp = dao.update(user);
        	System.out.println(resp);
        	
        	System.out.println(user.toString());
        	
        	return users.toString();
        }
        
    /*public boolean validate(Request request, Response response)throws JSONException {
    	UsuarioDAO dao = new UsuarioDAO();
    	response.header("Access-Control-Allow-Origin", "*");
		response.header("Access-Control-Allow-Methods", "POST,GET");
		response.header("Access-Control-Allow-Headers", "*");
		response.header("Access-Control-Max-Age", "86400");
		
    	JSONObject users = new JSONObject(request.body());
    	
    	String cpf = users.getString("cnpj");
    	String senha = users.getString("senha");
    	
    	return dao.autenticar(cpf, senha);
    }*/
    }
