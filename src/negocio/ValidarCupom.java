package negocio;

import java.util.List;

import dao.CupomDao;
import dominio.*;

public class ValidarCupom extends AbstractValidadorStrategy {
	
	private Pedido pedido;
	private CupomDao daoCupom;
	private List<EntidadeDominio> entidades;
	private Cupom cupom;

	
	@Override
	public String processar(EntidadeDominio entidade) {
        pedido = (Pedido) entidade;
        daoCupom = new CupomDao();
        sb.setLength(0);
		for (FormaPgto forma : pedido.getFormasPgto()) {
			if (forma instanceof Cupom) {
				entidades = daoCupom.consultar(pedido);
				
				for(EntidadeDominio ent : entidades) {
					cupom = ((ItemPedido) ent).getCupom();
					if (cupom.getNumCupom().equals(((Cupom) forma).getNumCupom()) && cupom.getnValorParcela() > 0.0)
						return verificaMsg();
					
				}
				sb.append("Cumpom inválido");
			}

		}
		
		return verificaMsg();
		
	}

}
