package negocio;

import dominio.Cartao;
import dominio.EntidadeDominio;


public class ValidarDadosCartao extends AbstractValidadorStrategy {
	
	private Cartao cartao;

	@Override
	public String processar(EntidadeDominio entidade) {

		cartao = (Cartao)entidade;
		

			if (cartao.getBandeira().getNome() == "" || cartao.getNomeCartao() == "" || cartao.getCvv() == ""
					|| cartao.getValidade() == "" || cartao.getNumCartao() == "") {
	
				sb.append("Faltam dados do cartão");
			}


		
		return verificaMsg();

	}

}
