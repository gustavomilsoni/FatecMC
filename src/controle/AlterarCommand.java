package controle;

import dominio.EntidadeDominio;
import util.Resultado;

public class AlterarCommand extends AbstractCommand {
	@Override
	public Resultado executar(EntidadeDominio entidade) {

		return fachada.alterar(entidade);
	}
}
