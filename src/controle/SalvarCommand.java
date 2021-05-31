package controle;

import dominio.EntidadeDominio;
import util.Resultado;

public class SalvarCommand extends AbstractCommand {
	@Override
	public Resultado executar(EntidadeDominio entidade) {

		return fachada.salvar(entidade);
	}
}
