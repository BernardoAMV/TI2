package Instituicao.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import Instituicao.model.*;
import spark.Request;
import spark.Response;

public class InstituicaoDAO extends DAO {
	public InstituicaoDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	public boolean insert(Instituicao usuario) throws Exception {
	    boolean status = false;

	    try {
	        String sql = "INSERT INTO instituicoes (cnpj, nome, senha, email) VALUES (?, ?, ?, ?)";
	        PreparedStatement st = conexao.prepareStatement(sql);

	        // Define os parâmetros
	        st.setString(1, usuario.getCnpj());
	        st.setString(2, usuario.getNome());
	        st.setString(3, toMD5(usuario.getSenha()));
	        st.setString(4, usuario.getEmail());

	        // Executa a inserção
	        st.executeUpdate();

	        st.close();
	        status = true;
	    } catch (SQLException e) {
	        throw new RuntimeException("Erro ao inserir instituição: " + e.getMessage());
	    }

	    return status;
	}


	public boolean update(Instituicao usuario) throws Exception {
	    boolean status = false;

	    try {  
	        String sql = "UPDATE instituicoes SET nome = ?, senha = ?, email = ? WHERE cnpj = ?";
	        PreparedStatement st = conexao.prepareStatement(sql);

	        // Define os parâmetros
	        st.setString(1, usuario.getNome());
	        st.setString(2, toMD5(usuario.getSenha()));
	        st.setString(3, usuario.getEmail());
	        st.setString(4, usuario.getCnpj());

	        System.out.println(sql);

	        st.executeUpdate();

	        st.close();
	        status = true;
	    } catch (SQLException e) {  
	        throw new RuntimeException("Erro ao atualizar instituição: " + e.getMessage());
	    }

	    return status;
	}

	
	public boolean delete(String cnpj) {
		boolean status = false;
		try {  
			String sql = "DELETE FROM instituicoes WHERE cnpj = ?";
			PreparedStatement st = conexao.prepareStatement(sql);
			
			st.setString(1, cnpj);
			
			System.out.println(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Instituicao[] getAll(Request request, Response response) {
	    Instituicao[] instituicoes = null;

	    try {
	        String sql = "SELECT * FROM instituicoes";
	        PreparedStatement st = conexao.prepareStatement(sql);

	        ResultSet rs = st.executeQuery();

	        List<Instituicao> instituicaoList = new ArrayList<>();

	        while (rs.next()) {
	            Instituicao instituicao = new Instituicao(
	                    rs.getString("cnpj"),
	                    rs.getString("nome"),
	                    rs.getString("senha"),
	                    rs.getString("email")
	            );

	            instituicaoList.add(instituicao);
	            System.out.println(instituicao);
	        }

	        instituicoes = instituicaoList.toArray(new Instituicao[0]);

	        st.close();
	        rs.close();

	    } catch (SQLException e) {
	        System.err.println("Erro ao obter instituições: " + e.getMessage());
	    }

	    return instituicoes;
	}

	
	
	public String autenticar(String cnpj, String senha) throws Exception {
	    boolean resp = false;
	    String nome = "";
	    String contato = "";
	    String cnpjInstituicao = "";

	    try {
	        String sql = "SELECT * FROM instituicoes WHERE cnpj = ? AND senha = ?";
	        PreparedStatement st = conexao.prepareStatement(sql);

	        // Define os parâmetros
	        st.setString(1, cnpj);
	        st.setString(2, toMD5(senha));

	        System.out.println(sql);

	        ResultSet rs = st.executeQuery();
	        resp = rs.next();
	        nome += rs.getString("nome");
	        contato += rs.getString("email");
	        cnpjInstituicao += rs.getString("cnpj");
	        

	        System.out.println(resp);

	        st.close();
	        rs.close();
	    } catch (SQLException e) {
	        System.err.println("Erro ao autenticar instituição: " + e.getMessage());
	    }
	    
	    JSONObject user = new JSONObject();
	    user.put("nomeInstituicao", nome);
	    user.put("validated", resp);
	    user.put("contato", contato);
	    user.put("cnpj", cnpjInstituicao);

	    return user.toString();
	}

}
