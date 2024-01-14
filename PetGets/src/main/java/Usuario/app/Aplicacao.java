package Usuario.app;
import Instituicao.service.*;
 
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import org.json.JSONObject;
import Instituicao.dao.InstituicaoDAO;
import Instituicao.dao.DAO;
import spark.Route;
import Instituicao.model.Instituicao;
import java.sql.*;
import java.util.List;

import Usuario.service.*;
import Animal.service.*;
import gpt.gptService.*;

public class Aplicacao {
	
	//private static InstituicaoDAO usuarioDAO = new InstituicaoDAO();
	
	private static InstituicaoService instituicaoService = new InstituicaoService();
	private static  UsuarioService usuarioService = new UsuarioService();
	private static AnimalService animalService = new AnimalService();
	private static gptService gptService = new gptService();
	
    public static void main(String[] args) {
        
        // post's e get's para as instituicoes
        get("/Instituicao/list", (request, response) -> instituicaoService.list(request, response));
        
        post("/Instituicao/insert", (request, response) -> instituicaoService.insert(request, response));
        
        get("/Instituicao/delete", (request, response) -> instituicaoService.delete(request, response));
        
        post("/Instituicao/update", (request, response) -> instituicaoService.update(request, response));
        
        post("/Instituicao/validate", (request, response) -> instituicaoService.validate(request, response));
        
        //post's e get's para os usuarios
        get("/usuario/list", (request, response) -> usuarioService.list(request, response));
        
        post("/usuario/insert", (request, response) -> usuarioService.insert(request, response));
        
        get("/usuario/delete", (request, response) -> usuarioService.delete(request, response));
        
        post("/usuario/update", (request, response) -> usuarioService.update(request, response));
        
        post("/usuario/validate", (request, response) -> usuarioService.validate(request, response));
        
        // post's e get's para os animais
        get("/animal/list", (request, response) -> animalService.list(request, response));
        
        post("/animal/insert", (request, response) -> animalService.insert(request, response));
        
        get("/animal/delete", (request, response) -> animalService.delete(request, response));
        
        post("/animal/update", (request, response) -> animalService.update(request, response));
        
        get("/animal/listMyPets", (request, response) -> animalService.listMeusPets(request, response));
        
        //posts e gets para o sistema inteligente
        
        post("/gpt/pedido", (request, response) -> gptService.fazerPedido(request, response));
        

        
}
    
}
