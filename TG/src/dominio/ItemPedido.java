package dominio;

public class ItemPedido extends EntidadeDominio {
	private int quantidade;
	private Livro livro;
	private Cupom cupom;

	
	public ItemPedido(int quantidade, Livro livro) {
		this.quantidade = quantidade;
		this.livro = livro;
	}
	
	

	public Cupom getCupom() {
		return cupom;
	}



	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}



	public ItemPedido() {
		
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
