package dominio;

public class Estado extends EntidadeDominio{
	private String nome;
	private Pais pais;
	private String uf;
	
	
	public Estado(String uf, Pais pais) {
		this.uf = uf;
		this.pais = pais;
	}
	
	

	public String getUf() {
		return uf;
	}



	public void setUf(String uf) {
		this.uf = uf;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	
}
