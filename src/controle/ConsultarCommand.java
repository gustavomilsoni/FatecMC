package controle;

import dominio.EntidadeDominio;
import util.Resultado;

public class ConsultarCommand extends AbstractCommand {
	@Override
	public Resultado executar(EntidadeDominio entidade) {
		return fachada.consultar(entidade);
	}
}
