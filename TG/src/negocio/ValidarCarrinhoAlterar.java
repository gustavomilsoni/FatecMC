package negocio;

import java.util.List;

import dao.EstoqueDao;
import dominio.Carrinho;
import dominio.EntidadeDominio;
import dominio.Estoque;
import dominio.ItemCarrinho;
import dominio.ItemPedido;

public class ValidarCarrinhoAlterar extends AbstractValidadorStrategy {

	private Carrinho carrinho;
	private EstoqueDao daoEstoue;
	private List<Estoque> estoques;

	@SuppressWarnings("unchecked")
	@Override
	public String processar(EntidadeDominio entidade) {
		carrinho = (Carrinho) entidade;
		sb.setLength(0);
		daoEstoue = new EstoqueDao();

		for (ItemCarrinho itemAtual : carrinho.getItens()) {

			estoques = (List<Estoque>) (List<?>) daoEstoue.consultar(itemAtual.getLivro());

			if (itemAtual.getQuantidade() > estoques.get(0).getQuantidadeEstoque()) {
				sb.append("Quantidade acima do estoque do item " + itemAtual.getLivro().getTitulo());
				return verificaMsg();
			}

		}

		return verificaMsg();
	}

}
