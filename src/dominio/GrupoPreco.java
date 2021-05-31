package dominio;

public class GrupoPreco extends EntidadeDominio {
	private String nome = "";
	private double margem;
	
	public GrupoPreco(String nome, double margem) {
		this.nome = nome;
		this.margem = margem;
	}
	
	public GrupoPreco() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getMargem() {
		return margem;
	}

	public void setMargem(double margem) {
		this.margem = margem;
	}
	
	

}
