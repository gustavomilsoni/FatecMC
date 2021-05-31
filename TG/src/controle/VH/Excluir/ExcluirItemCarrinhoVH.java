package controle.VH.Excluir;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.VH.AbstractViewHelper;
import dominio.Cliente;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.Livro;
import util.Resultado;

public class ExcluirItemCarrinhoVH extends AbstractViewHelper {

	private ItemCarrinho item;
	private Livro livro;
	private int id;
	private Cliente cliente;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		session = request.getSession();	
		cliente = (Cliente) session.getAttribute("cliente");
		
		id = Integer.valueOf(request.getParameter("txtCodProd"));

		livro = new Livro();
		livro.setId(id);
		

		item = new ItemCarrinho();
		
		item.setCarrinho(cliente.getCarrinho());
		item.setLivro(livro);

		return item;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
		
		rd = request.getRequestDispatcher("frmCarrinho.jsp");
		enviarFront(resultado, request, response);
		
	}

}
