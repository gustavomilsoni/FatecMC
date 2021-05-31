package controle;

import dominio.EntidadeDominio;
import util.Resultado;

public class ExcluirCommand extends AbstractCommand {
	@Override
	public Resultado executar(EntidadeDominio entidade) {
		return fachada.excluir(entidade);
	}
}
