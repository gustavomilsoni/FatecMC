package dominio;

public class Estoque extends EntidadeDominio {
	private int quantidadeEstoque;
	private double maiorValorCompra;
	
	public Estoque() {
		
	}
	

	public Estoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public double getMaiorValorCompra() {
		return maiorValorCompra;
	}

	public void setMaiorValorCompra(double maiorValorCompra) {
		this.maiorValorCompra = maiorValorCompra;
	}

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

}
