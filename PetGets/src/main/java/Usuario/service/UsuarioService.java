package Usuario.service;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import org.json.JSONException;
import org.json.JSONObject;
import Usuario.dao.UsuarioDAO;
import Usuario.dao.DAO;
import spark.Request;
import spark.Route;
import Usuario.model.Usuario;
import java.sql.*;
import java.util.List;
import spark.Spark.*;
import spark.Response;


public class UsuarioService {
	

	
	public UsuarioService() {
    
        port(5554);
        staticFiles.location("/public");
	}
        

        
        public String list(Request request, Response response)throws JSONException {
        	UsuarioDAO dao = new UsuarioDAO();
        	response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "POST,GET");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Max-Age", "86400");
			
        	
        	
        	Usuario[] usuarios = dao.getAll(request, response);
        	JSONObject users = new JSONObject();
        	
        	for(int i = 0; i < usuarios.length; i++ ) {
        		JSONObject user = new JSONObject();
        		user.put("cnpj", usuarios[i].getCpf());
        		user.put("nomeInstituicao", usuarios[i].getNome());
        		user.put("senha", usuarios[i].getSenha());
        		user.put("contato", usuarios[i].getEmail());
        		users.append("usuarios", user);
        	}
        	
        	
        	
        	System.out.println(users.toString());
        	
        	return users.toString();
        }
        
        public String insert(Request request, Response response) throws Exception{
        	UsuarioDAO dao = new UsuarioDAO();
        	
        	response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "POST,GET");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Max-Age", "86400");
			
        	JSONObject users = new JSONObject(request.body());
        	
        	String cpf = users.getString("cnpj");
        	String nome = users.getString("nomeInstituicao");
        	String senha = users.getString("senha");
        	String email = users.getString("contato");
        	Usuario user = new Usuario(cpf, nome, senha, email);
        	dao.insert(user);
        	
        	
        	System.out.println(user.toString());
        	
        	return users.toString();
        }
        
        public String delete(Request request, Response response){
        	UsuarioDAO dao = new UsuarioDAO();
        	response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "POST,GET");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Max-Age", "86400");
			
        	
        	
        	JSONObject users = new JSONObject();
        	
        	String cpf = request.queryParams("cnpj"); //
        	dao.delete(cpf);
        	
        	System.out.println(users.toString());
        	
        	return users.toString();
        }
        
        public String update(Request request, Response response)throws Exception{
        	UsuarioDAO dao = new UsuarioDAO();
        	response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "POST,GET");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Max-Age", "86400");
			
        	JSONObject users = new JSONObject(request.body());
        	
        	String cpf = users.getString("cnpj");
        	String nome = users.getString("nomeInstituicao");
        	String senha = users.getString("senha");
        	String email = users.getString("contato");
        	
        	Usuario user = new Usuario(cpf, nome, senha, email);
        	
        	boolean resp = dao.update(user);
        	System.out.println(resp);
        	
        	System.out.println(user.toString());
        	
        	return users.toString();
        }
        
    public String validate(Request request, Response response)throws Exception {
    	UsuarioDAO dao = new UsuarioDAO();
    	response.header("Access-Control-Allow-Origin", "*");
		response.header("Access-Control-Allow-Methods", "POST,GET");
		response.header("Access-Control-Allow-Headers", "*");
		response.header("Access-Control-Max-Age", "86400");
		
    	JSONObject users = new JSONObject(request.body());
    	
    	String cpf = users.getString("cnpj");
    	String senha = users.getString("senha");
    	
    	String resp = dao.autenticar(cpf, senha);
    	System.out.println(resp);
    	
    	response.status(200);
    	
    	return resp;
    }
    }
