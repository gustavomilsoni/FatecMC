package controle.VH.Excluir;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.VH.AbstractViewHelper;
import dominio.Cliente;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.Livro;
import util.Resultado;

public class ExcluirIProdutoVH extends AbstractViewHelper {

	private Livro livro;
	private int id;

	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		id = Integer.valueOf(request.getParameter("txtCodProd"));

		livro = new Livro();
		livro.setId(id);

		return livro;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
		
		resultado.setMsg("Livro excluído com sucesso");
		rd = request.getRequestDispatcher("frmCadProduto.jsp");
		enviarFront(resultado, request, response);
		
	}

}
