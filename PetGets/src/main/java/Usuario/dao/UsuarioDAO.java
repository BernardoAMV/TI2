package Usuario.dao;

import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Usuario.model.Usuario;
import spark.Request;
import spark.Response;

public class UsuarioDAO extends DAO {
	public UsuarioDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	public boolean insert(Usuario usuario) throws Exception {
		boolean status = false;
		try {  
			String sql = "INSERT INTO usuario (cpf, nome, senha, email, isadmin) "
				       + "VALUES (?, ?, ?, ?, ?)";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setString(1, usuario.getCpf());
			st.setString(2, usuario.getNome());
			st.setString(3, toMD5(usuario.getSenha()));
			st.setString(4, usuario.getEmail());
			st.setBoolean(5, false);
			
			System.out.println(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean update(Usuario usuario) throws Exception {
		boolean status = false;
		try {  
			String sql = "UPDATE usuario SET nome = ?, senha = ?, email = ? WHERE cpf = ?";
			PreparedStatement st = conexao.prepareStatement(sql);
			
			
			st.setString(1, usuario.getCpf());
			st.setString(2, usuario.getNome());
			st.setString(3, toMD5(usuario.getSenha()));
			st.setString(4, usuario.getEmail());
			st.setBoolean(5, false);
			
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(String cpf) {
		boolean status = false;
		try {  
			
			String sql = "DELETE FROM usuario WHERE cpf = ?";
			PreparedStatement st = conexao.prepareStatement(sql);
			
			st.setString(1, cpf);
			
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Usuario[] getAll(Request request, Response response) {
	    Usuario[] usuarios = null;

	    try {
	        String sql = "SELECT * FROM usuario";
	        PreparedStatement st = conexao.prepareStatement(sql);

	        ResultSet rs = st.executeQuery();

	        List<Usuario> usuarioList = new ArrayList<>();

	        while (rs.next()) {
	            Usuario usuario = new Usuario(
	                    rs.getString("cpf"),
	                    rs.getString("nome"),
	                    rs.getString("senha"),
	                    rs.getString("email")
	            );

	            usuarioList.add(usuario);
	            System.out.println(usuario);
	        }

	        usuarios = usuarioList.toArray(new Usuario[0]);

	        st.close();
	        rs.close();

	    } catch (SQLException e) {
	        System.err.println("Erro ao obter usuários: " + e.getMessage());
	    }

	    return usuarios;
	}

	
	
	public String autenticar(String cpf, String senha) throws Exception {
	    boolean resp = false;
	    boolean isAdmin = false;
	    String nome = "";
	    String contato = "";
	    String cpfUser = "";
	    JSONObject user = new JSONObject();

	    try {
	        String sql = "SELECT * FROM usuario WHERE cpf = ?";
	        PreparedStatement st = conexao.prepareStatement(sql);
	        st.setString(1, cpf);

	        System.out.println(sql);
	        ResultSet rs = st.executeQuery();

	        if (rs.next()) {
	            String senhaBanco = rs.getString("senha");
	            String senhaDigitada = DAO.toMD5(senha);

	            System.out.println(senhaBanco + " == " + senhaDigitada);

	            
	            if (senhaBanco.equals(senhaDigitada)) {
	                resp = true;
	            }

	            isAdmin = rs.getBoolean("isadmin");
	            nome+= rs.getString("nome");
	            contato += rs.getString("email");
	            cpfUser += rs.getString("cpf");
	        }

	        st.close();
	        rs.close();

	    } catch (Exception e) {
	        System.err.println(e.getMessage());
	    }

	    user.put("isAdmin", isAdmin);
	    user.put("authenticated", resp);
	    user.put("nome", nome);
	    user.put("contato", contato);
	    user.put("cpf", cpfUser);
	    return user.toString();
	}

	
		
	}	

