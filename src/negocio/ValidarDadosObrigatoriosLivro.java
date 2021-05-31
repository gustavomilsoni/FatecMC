package negocio;

import dominio.EntidadeDominio;
import dominio.Livro;

public class ValidarDadosObrigatoriosLivro extends AbstractValidadorStrategy {

	private Livro livro;

	@Override
	public String processar(EntidadeDominio entidade) {
		livro = (Livro) entidade;
		sb.delete(0, sb.length());
		if (livro.getAltura().equals("") || livro.getAno().equals("") || livro.getAutor().equals("")
				|| livro.getCategoria().getId() == 0 || livro.getCodBarras().equals("") || livro.getEdicao().equals("")
				|| livro.getEditora().equals("") || livro.getGrpPreco().getId() == 0 || livro.getIsbn().equals("")
				|| livro.getLargura().equals("") || livro.getMargem() == 0 || livro.getNumPag().equals("")
				|| livro.getPeso().equals("") || livro.getProfundidade().equals("") || livro.getSinopse().equals(""))

			sb.append("Faltam dados");

		return verificaMsg();
	}

}
