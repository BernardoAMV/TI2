package Animal.model;

public class Animal {
	private int id;
	private int idade;
	private String nome ;
	private String sexo;
	private String raca;
	private String especie;
	private String descricao;
	private String foto;
	private String responsavel;
	private String nomeResponsavel;
	private String contatoResponsavel;
	
	public Animal() {
		this.id = -1;
		this.idade = 0;
		this.nome = "";
		this.sexo = "";
		this.raca = "";
		this.especie = "";
		this.descricao = "";
		this.foto = "";
		this.responsavel = "";
		this.nomeResponsavel = "";
		this.contatoResponsavel = "";
	
	}
	
	public Animal(int idade, String nome, String sexo, String raca, String especie,String descricao, 
				  String foto, String responsavel, String nomeResponsavel, String contatoResponsavel, int id) {
		this.idade = idade;
		this.nome = nome;
		this.sexo = sexo;
		this.raca = raca;
		this.especie = especie;
		this.descricao = descricao;
		this.foto = foto;
		this.responsavel = responsavel;
		this.nomeResponsavel = nomeResponsavel;
		this.contatoResponsavel = contatoResponsavel;
		this.id = id;
	}
	public String getnomeResponsavel() {
        return nomeResponsavel;
    }
	public void setnomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
    }

    // Setter para o campo 'idade'
	public String getcontatoResponsavel() {
        return contatoResponsavel;
    }
	public void setcontatoResponsavel(String contatoResponsavel) {
		this.contatoResponsavel = contatoResponsavel;
    }

	 public int getId() {
	        return id;
	    }

	    // Getter para o campo 'idade'
	    public int getIdade() {
	        return idade;
	    }

	    // Setter para o campo 'idade'
	    public void setIdade(int idade) {
	        this.idade = idade;
	    }

	    // Getter para o campo 'nome'
	    public String getNome() {
	        return nome;
	    }

	    // Setter para o campo 'nome'
	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    // Getter para o campo 'sexo'
	    public String getSexo() {
	        return sexo;
	    }

	    // Setter para o campo 'sexo'
	    public void setSexo(String sexo) {
	        this.sexo = sexo;
	    }

	    // Getter para o campo 'raca'
	    public String getRaca() {
	        return raca;
	    }

	    // Setter para o campo 'raca'
	    public void setRaca(String raca) {
	        this.raca = raca;
	    }

	    // Getter para o campo 'especie'
	    public String getEspecie() {
	        return especie;
	    }

	    // Setter para o campo 'especie'
	    public void setEspecie(String especie) {
	        this.especie = especie;
	    }

	    // Getter para o campo 'descricao'
	    public String getDescricao() {
	        return descricao;
	    }

	    // Setter para o campo 'descricao'
	    public void setDescricao(String descricao) {
	        this.descricao = descricao;
	    }
	    
	    public void setFoto(String foto) {
	    	this.foto = foto;
	    }
	    
	    public String getFoto() {
	    	return foto;
	    }
	    public void setResponsavel(String responsavel) {
	        this.responsavel = responsavel;
	    }

	    
	    public String getResponsavel() {
	        return responsavel;
	    }
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"idade\":\""+ idade + "\", \"nome\":\"" + nome + "\", \"sexo\":\"" + sexo + "\", \"raca\":\"" + raca + "\", \"especie\"=\""
				+ especie + "\", \"descricao\"=\"" + descricao + "\", \"foto\"=\""+ foto + "\", \"responsavel\"=\"" + responsavel +"\", \"nomeResponsavel\"=\"" + 
				nomeResponsavel + "\", \"contatoResponsavel\"=\"" +  contatoResponsavel + "\"}";
	}	
}
