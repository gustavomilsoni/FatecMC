package dominio;

public class BandeiraCart extends EntidadeDominio{
	private String nome;
	
	public BandeiraCart(String nome) {
		this.nome = nome;
	}
	
	public BandeiraCart() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
