package Instituicao.model;

public class Instituicao {

		private String cnpj;
		private String nome ;
		private String senha;
		private String email;
		
		public Instituicao() {
			this.cnpj = "";
			this.nome = "";
			this.senha = "";
			this.email = "";
		}
		
		public Instituicao(String cnpj, String nome, String senha, String email) {
			this.cnpj = cnpj;
			this.nome = nome;
			this.senha = senha;
			this.email = email;
		}

		public String getCnpj() {
			return cnpj;
		}

		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
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
			return "{\"cnpj\":\"" + cnpj + "\", \"nome\":\"" + nome + "\", \"senha\"=\"" + senha + "\", \"email\"=\"" + email + "\"}";
		}	
	}
