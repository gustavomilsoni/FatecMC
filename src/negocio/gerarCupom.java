package negocio;

import dominio.Cupom;
import dominio.EntidadeDominio;
import dominio.ItemPedido;
import dominio.Pedido;

public class gerarCupom extends AbstractValidadorStrategy {
	
	Pedido pedido;

	@Override
	public String processar(EntidadeDominio entidade) {
		pedido = (Pedido) entidade;
		
		if(pedido.getStatus().equals("TROCA AUTORIZADA")){
			for(ItemPedido item : pedido.getItens()) {
				if(item.getStatus().equals("S") && item.getCupom()== null) {
					
					item.setCupom(new Cupom(pedido.getCli().getId()+""+pedido.getId()+""+item.getId(), 
							item.getLivro().getValorVenda() * item.getQuantidade()));
					
					item.getCupom().setnValorParcela(item.getLivro().getValorVenda() * item.getQuantidade());
					
					item.getLivro().getEstoque().setQuantidadeEstoque(item.getQuantidade() +
							item.getLivro().getEstoque().getQuantidadeEstoque());
                    
					
				}
			}
			
		}
				
		
		
		// TODO Auto-generated method stub
		return null;
	}

}
