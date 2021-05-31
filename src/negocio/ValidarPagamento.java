package negocio;

import dominio.EntidadeDominio;
import dominio.Pedido;

public class ValidarPagamento extends AbstractValidadorStrategy {
	
	private Pedido pedido;

	@Override
	public String processar(EntidadeDominio entidade) {
		
		pedido = (Pedido) entidade;
		sb.setLength(0);
		if (pedido.getTotal() > pedido.getValorPago()+ pedido.getValorPagoTotal()) {

			sb.append("Valor incompleto. Selecione outro pagamento.");
		}


	
	return verificaMsg();

		

	}

}
