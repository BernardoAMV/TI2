package Instituicao.service;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import org.json.JSONException;
import org.json.JSONObject;
import Instituicao.dao.InstituicaoDAO;
import Instituicao.dao.DAO;
import spark.Request;
import spark.Response;
import spark.Route;
import Instituicao.model.Instituicao;
import java.sql.*;
import java.util.List;
import spark.Spark.*;



public class InstituicaoService {
	
	
        public InstituicaoService() {
        	staticFiles.location("/public");
        	port(5554);
        }
     
        

        
        public String list(Request request, Response response) throws JSONException{
        	InstituicaoDAO dao = new InstituicaoDAO();
        	response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "POST,GET");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Max-Age", "86400");
			
        	
        	
        	Instituicao[] usuarios = dao.getAll(request, response);
        	JSONObject users = new JSONObject();
        	
        	for(int i = 0; i < usuarios.length; i++ ) {
        		JSONObject instituicao = new JSONObject();
        		instituicao .put("cnpjInstituicao", usuarios[i].getCnpj());
        		instituicao .put("nomeDaInstituicao", usuarios[i].getNome());
        		instituicao .put("senhaInstituicao", usuarios[i].getSenha());
        		instituicao .put("emailInstituicao", usuarios[i].getEmail());
        		users.append("instituicoes", instituicao );
        	}
        	
        	
        	
        	System.out.println(users.toString());
        	
        	return users.toString();
        }
        
        public String insert(Request request, Response response) throws Exception{
        	InstituicaoDAO dao = new InstituicaoDAO();
        	
        	response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "POST,GET");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Max-Age", "86400");
			
        	JSONObject users = new JSONObject(request.body());
        	
        	String cnpj = users.getString("cnpjInstituicao");
        	String nome = users.getString("nomeDaInstituicao");
        	String senha = users.getString("senhaInstituicao");
        	String email = users.getString("emailInstituicao");
        	Instituicao user = new Instituicao(cnpj, nome, senha, email);
        	dao.insert(user);
        	
        	
        	System.out.println(user.toString());
        	
        	return users.toString();
        }
        
        public String delete(Request request, Response response) {
        	InstituicaoDAO dao = new InstituicaoDAO();
        	response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "POST,GET");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Max-Age", "86400");
			
        	
        	
        	JSONObject users = new JSONObject();
        	
        	String cnpj = request.queryParams("cnpjInstituicao"); //
        	dao.delete(cnpj);
        	
        	System.out.println(users.toString());
        	
        	return users.toString();
        }
        
        public String update(Request request, Response response) throws Exception {
        	
        	InstituicaoDAO dao = new InstituicaoDAO();
        	response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "POST,GET");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Max-Age", "86400");
			
        	JSONObject users = new JSONObject(request.body());
        	
        	String cnpj = users.getString("cnpjInstituicao");
        	String nome = users.getString("nomeDaInstituicao");
        	String senha = users.getString("senhaInstituicao");
        	String email = users.getString("emailInstituicao");
        	
        	Instituicao user = new Instituicao(cnpj, nome, senha, email);
        	
        	dao.update(user);
        	
        	
        	System.out.println(user.toString());
        	
        	return users.toString();
        }
        
        
        public String validate (Request request, Response response) throws Exception {
        	
            
        	InstituicaoDAO dao = new InstituicaoDAO();
        	response.header("Access-Control-Allow-Origin", "*");
    		response.header("Access-Control-Allow-Methods", "POST,GET");
    		response.header("Access-Control-Allow-Headers", "*");
    		response.header("Access-Control-Max-Age", "86400");
    		
        	JSONObject users = new JSONObject(request.body());
        	
        	String cnpj = users.getString("cnpjInstituicao");
        	String senha = users.getString("senhaInstituicao");
        	
        	return dao.autenticar(cnpj, senha);
        
        }   
  
}