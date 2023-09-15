package app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import org.json.JSONObject;
import dao.UsuarioDAO;
import dao.DAO;
import spark.Route;
import model.Usuario;
import java.sql.*;
import java.util.List;



public class Aplicacao {
	
	private static UsuarioDAO usuarioDAO = new UsuarioDAO();
	
    public static void main(String[] args) {
        port(5554);
        
        staticFiles.location("/public");
        
        

        
        get("/usuario/list", (request, response) -> {
        	UsuarioDAO dao = new UsuarioDAO();
        	response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "POST,GET");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Max-Age", "86400");
			
        	
        	
        	Usuario[] usuarios = dao.getAll(request, response);
        	JSONObject users = new JSONObject();
        	
        	for(int i = 0; i < usuarios.length; i++ ) {
        		JSONObject user = new JSONObject();
        		user.put("codigo", usuarios[i].getCodigo());
        		user.put("login", usuarios[i].getLogin());
        		user.put("senha", usuarios[i].getSenha());
        		user.put("sexo", usuarios[i].getSexo());
        		users.append("usuarios", user);
        	}
        	
        	
        	
        	System.out.println(users.toString());
        	
        	return users.toString();
        });
        
        post("/usuario/insert", (request, response) -> {
        	UsuarioDAO dao = new UsuarioDAO();
        	response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "POST,GET");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Max-Age", "86400");
			
        	JSONObject users = new JSONObject(request.body());
        	
        	int codigo = users.getInt("codigo");
        	String login = users.getString("login");
        	String senha = users.getString("senha");
        	char sexo = users.getString("sexo").charAt(0);
        	Usuario user = new Usuario(codigo, login, senha, sexo);
        	dao.insert(user);
        	
        	
        	System.out.println(user.toString());
        	
        	return users.toString();
        });
        
        get("/usuario/delete", (request, response) -> {
        	UsuarioDAO dao = new UsuarioDAO();
        	response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "POST,GET");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Max-Age", "86400");
			
        	
        	
        	JSONObject users = new JSONObject();
        	
        	int codigo = Integer.parseInt(request.queryParams("codigo"));
        	dao.delete(codigo);
        	
        	System.out.println(users.toString());
        	
        	return users.toString();
        });
        
        post("/usuario/update", (request, response) -> {
        	UsuarioDAO dao = new UsuarioDAO();
        	response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Methods", "POST,GET");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Max-Age", "86400");
			
        	JSONObject users = new JSONObject(request.body());
        	
        	int codigo = users.getInt("codigo");
        	String login = users.getString("login");
        	String senha = users.getString("senha");
        	char sexo = users.getString("sexo").charAt(0);
        	
        	Usuario user = new Usuario(codigo, login, senha, sexo);
        	
        	dao.update(user);
        	
        	
        	System.out.println(user.toString());
        	
        	return users.toString();
        });
             
    }

	
}