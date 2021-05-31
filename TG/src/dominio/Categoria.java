package dominio;

public class Categoria extends EntidadeDominio {
    private String nomeCategoria = "";
    
    

	public Categoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public Categoria() {

	}
	

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
    
    
}
