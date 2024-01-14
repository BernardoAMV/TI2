package Usuario.dao;

import java.sql.*;
import java.security.*;
import java.math.*;
import javax.crypto.*;
import java.util.Base64;

public class DAO {
	protected Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "petgets.postgres.database.azure.com";
		String mydatabase = "gssEncMode=disable";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/postgres?" + mydatabase;
		String username = "admin";
		String password = "Petgets2023!";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	
	public static String toMD5(String senha) throws Exception {
		MessageDigest m=MessageDigest.getInstance("MD5");
		m.update(senha.getBytes(),0, senha.length());
		return new BigInteger(1,m.digest()).toString(16);
	}
	public static String encryptNumber(int number, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(String.valueOf(number).getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}