package dominio;

import java.util.List;

public class Carrinho extends EntidadeDominio {
	private List<ItemCarrinho> itens;
	private Cliente cliente;
	

	
	public Carrinho(List<ItemCarrinho> itens, Cliente cliente) {
		this.itens = itens;
		this.cliente = cliente;
	}
	
	public Carrinho( Cliente cliente) {
		this.cliente = cliente;
	}

	public Carrinho() {}

	public List<ItemCarrinho> getItens() {
		return itens;
	}

	public void setItens(List<ItemCarrinho> itens) {
		this.itens = itens;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public double getTotal() {
		double total = 0;
		for(ItemCarrinho item: itens) {
			total += item.getLivro().getValorVenda() * item.getQuantidade();
		}
			
		
		return total;
		
		
	}
	
	
}
