package Animal.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Animal.model.Animal;
import spark.Request;
import spark.Response;

public class AnimalDAO extends DAO {
	public AnimalDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	public boolean insert(Animal animal) throws Exception {
	    boolean status = false;

	    try {
	        String sql = "INSERT INTO animais (idade, nome, sexo, raca, especie, descricao, responsavel, foto, nomeresponsavel, contatoresponsavel) " +
	                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement st = conexao.prepareStatement(sql);

	        // Define os parâmetros
	        st.setInt(1, animal.getIdade());
	        st.setString(2, animal.getNome());
	        st.setString(3, animal.getSexo());
	        st.setString(4, animal.getRaca());
	        st.setString(5, animal.getEspecie());
	        st.setString(6, animal.getDescricao());
	        st.setString(7, animal.getResponsavel());
	        st.setString(8, animal.getFoto());
	        st.setString(9, animal.getnomeResponsavel());
	        st.setString(10, animal.getcontatoResponsavel());

	        // Executa a inserção
	        st.executeUpdate();

	        st.close();
	        status = true;
	    } catch (SQLException e) {
	        throw new RuntimeException("Erro ao inserir animal: " + e.getMessage());
	    }

	    return status;
	}


	


	
	
	public boolean update(Animal animal) throws Exception {
	    boolean status = false;
	    try {  
	        String sql = "UPDATE animais SET idade = ?, nome = ?, sexo = ?, raca = ?, especie = ?, descricao = ?, responsavel = ?, nomeResponsavel = ?, contatoResponsavel = ? WHERE id = ?";
	        PreparedStatement st = conexao.prepareStatement(sql);
	        
	        st.setInt(1, animal.getIdade());
	        st.setString(2, animal.getNome());
	        st.setString(3, animal.getSexo());
	        st.setString(4, animal.getRaca());
	        st.setString(5, animal.getEspecie());
	        st.setString(6, animal.getDescricao());
	        st.setString(7, animal.getResponsavel());
	        st.setString(8, animal.getnomeResponsavel());
	        st.setString(9, animal.getcontatoResponsavel());
	        st.setInt(10, animal.getId());

	        System.out.println(sql);
	        st.executeUpdate();
	        st.close();
	        status = true;
	    } catch (SQLException u) {  
	        throw new RuntimeException(u);
	    }
	    return status;
	}

	
	public boolean delete(int id) {
	    boolean status = false;
	    try {  
	        String sql = "DELETE FROM animais WHERE id = ?";
	        PreparedStatement st = conexao.prepareStatement(sql);

	        // Define o parâmetro
	        st.setInt(1, id);

	        // Executa a exclusão
	        st.executeUpdate();

	        st.close();
	        status = true;
	    } catch (SQLException e) {  
	        throw new RuntimeException("Erro ao excluir animal: " + e.getMessage());
	    }
	    return status;
	}

	
	public Animal[] getAll() {
	    Animal[] animais = null;

	    try {
	        String sql = "SELECT * FROM animais";
	        PreparedStatement st = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

	        ResultSet rs = st.executeQuery();

	        if (rs.next()) {
	            rs.last();
	            System.out.println(sql);
	            animais = new Animal[rs.getRow()];
	            rs.beforeFirst();

	            for (int i = 0; rs.next(); i++) {
	                animais[i] = new Animal(
	                        rs.getInt("idade"),
	                        rs.getString("nome"),
	                        rs.getString("sexo"),
	                        rs.getString("raca"),
	                        rs.getString("especie"),
	                        rs.getString("descricao"),
	                        rs.getString("foto"),
	                        rs.getString("responsavel"),
	                        rs.getString("nomeResponsavel"),
	                        rs.getString("contatoResponsavel"),
	                        rs.getInt("id"));
	    	                
	                System.out.println(animais[i]);
	            }
	        }

	        rs.close();
	        st.close();

	    } catch (SQLException e) {
	        System.err.println(e.getMessage());
	    }

	    return animais;
	}

	public Animal[] getAllMyPets(String responsavel ) {
		Animal[] animais = null;
		
		try {
			String sql = "SELECT * FROM animais WHERE responsavel = ?";
			PreparedStatement st = conexao.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			st.setString(1,responsavel);
			
			
			ResultSet rs = st.executeQuery();
			
			
			
	        if(rs.next()){ 
	        	
	        	 rs.last();
	        	 System.out.println(sql);
	        	 animais = new Animal[rs.getRow()];
	        	 rs.beforeFirst();
	        	 
	        	 for(int i = 0;rs.next();i++) {
	        		 	
						animais[i] = new Animal(rs.getInt("idade"),  rs.getString("nome"), rs.getString("sexo"), 
									 rs.getString("raca"), rs.getString("especie"), rs.getString("descricao"),
									 rs.getString("foto"), rs.getString("responsavel"), rs.getString("nomeResponsavel"), 
									 rs.getString("contatoResponsavel"), rs.getInt("id"));
					       System.out.println(animais[i]);
					}
	        }
	        
	        rs.close();
	        st.close();
	        
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return animais;		
	}
	
	
	/*public boolean autenticar(String cpf, String senha) {
		boolean resp = false;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario WHERE cpf = '" + cpf + "' AND senha = '" + toMD5(senha)  + "'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			resp = rs.next();
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return resp;
		
	}*/
	
}
