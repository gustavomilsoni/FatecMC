package controle;

import dominio.EntidadeDominio;
import util.Resultado;

public interface ICommand {
	public Resultado executar(EntidadeDominio entidade);

}
