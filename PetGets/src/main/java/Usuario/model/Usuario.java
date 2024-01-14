package Usuario.model;

public class Usuario {
	private String cpf;
	private String nome ;
	private String senha;
	private String email;
	private boolean isAdmin;
	
	public Usuario() {
		this.cpf = "";
		this.nome = "";
		this.senha = "";
		this.email = "";
		this.isAdmin = false;
	}
	
	public Usuario(String cpf, String nome, String senha, String email) {
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.isAdmin = false;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "{\"cpf\":\"" + cpf + "\", \"nome\":\"" + nome + "\", \"senha\"=\"" + senha + "\", \"email\"=\"" + email + "\", \"isAdmin\"=\"" + isAdmin + "\"}";
	}	
}
