package negocio;

import java.util.List;

import dao.EstoqueDao;
import dominio.Carrinho;
import dominio.EntidadeDominio;
import dominio.Estoque;
import dominio.ItemCarrinho;
import dominio.ItemPedido;

public class ValidarItemCarrinho extends AbstractValidadorStrategy {

	private ItemCarrinho item;
	private EstoqueDao daoEstoue;
	private List<Estoque> estoques;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public String processar(EntidadeDominio entidade) {
		item = (ItemCarrinho) entidade;
		sb.setLength(0);
		daoEstoue = new EstoqueDao();
		
		estoques = (List<Estoque>)(List<?>)daoEstoue.consultar(item.getLivro());
		
		if(item.getQuantidade() > estoques.get(0).getQuantidadeEstoque()){      
			sb.append("Quantidade acima do estoque");
			return verificaMsg();
		}
		
		for(ItemCarrinho i : item.getCarrinho().getItens()){

				if (item.getLivro().getId() == i.getLivro().getId()) {
					sb.append("Item já incluso no carrinho");
					return verificaMsg();
				}
				



		}
		
			
		return verificaMsg();
	}

}
