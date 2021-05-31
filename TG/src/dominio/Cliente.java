package dominio;

import java.util.ArrayList;
import java.util.List;



public class Cliente extends Pessoa{
	
	private List<Cartao> cartoes;
	private Carrinho carrinho;
	private List<Pedido> pedidos;
	private List<Cupom> cupons;

	
	public Cliente() {
		super();
	}

    public Cliente(String fone, String tipofone, List<Endereco> enderecos, String nome, String cpf,List<Cartao> cartoes,String genero, 
    		String dataNasc,String email) {
		super(fone, tipofone, enderecos, nome, genero,dataNasc, cpf,email);
		this.cartoes = cartoes;


		
	}
    public Cliente(int id, String fone, String tipofone, List<Endereco> enderecos, String nome, String cpf,List<Cartao> cartoes,String genero, 
    		String dataNasc,String email) {
		super(fone, tipofone, enderecos, nome, genero,dataNasc, cpf,email);
		this.cartoes = cartoes;
		super.setId(id);

		
	}
    

   
    
	public List<Cupom> getCupons() {
		cupons = new ArrayList<Cupom>();
		for(Pedido ped : pedidos) {
			for(ItemPedido it : ped.getItens()) {
				if(it.getCupom().getNumCupom()!= null && it.getCupom().getNumCupom() !="") {
					cupons.add(it.getCupom());
				}
			}
		}
		
		return cupons;
	}
	
	


	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public void setCupons(List<Cupom> cupons) {
		this.cupons = cupons;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}





	public List<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}
   
    
	
}