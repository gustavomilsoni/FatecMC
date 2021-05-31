package dominio;

public class ItemCarrinho extends EntidadeDominio {
	private int quantidade;
	private Livro livro;
	private Carrinho carrinho;

	
	public ItemCarrinho(int quantidade, Livro livro,Carrinho carrinho) {
		this.quantidade = quantidade;
		this.livro = livro;
		this.carrinho = carrinho;
	}
	
	public ItemCarrinho() {
		
	}
	

	public Carrinho getCarrinho() {
		return carrinho;
	}




	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	
	
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	

}
