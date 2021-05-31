package negocio;

import dominio.EntidadeDominio;
import dominio.ItemPedido;
import dominio.Pedido;

public class ValidarDadosPedido extends AbstractValidadorStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Pedido pedido =  (Pedido) entidade;
		
		for(ItemPedido item : pedido.getItens()) {
			if(item.getQuantidade() < 1) {		
				sb.append("Quantidade errada do item "+item.getLivro().getTitulo());
				return verificaMsg();
			}
			
		}	

		return verificaMsg();
	}

}
