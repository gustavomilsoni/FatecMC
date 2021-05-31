package controle.VH.Salvar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import controle.VH.AbstractViewHelper;
import dominio.Cliente;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.Livro;
import dominio.Usuario;
import util.Resultado;

public class SalvarItemCarrinhoVH extends AbstractViewHelper {

	private ItemCarrinho item;
	private Livro livro;
	private int quantidade;
	private double preco;
	private int id;
	private Usuario usuario;
	private Cliente cliente;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		session = request.getSession();	
		usuario = (Usuario) session.getAttribute("usuario");
		
		cliente = (Cliente)usuario.getPessoa();
		
		quantidade = Integer.valueOf(request.getParameter("txtQuantidade"));
		preco = Double.valueOf(request.getParameter("txtPreco"));
		id = Integer.valueOf(request.getParameter("txtCodProd"));

		livro = new Livro();
		livro.setId(id);
		livro.setValorVenda(Double.valueOf(preco));
		

		item = new ItemCarrinho(quantidade, livro,cliente.getCarrinho());

		return item;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
			session = request.getSession();	
			usuario = (Usuario) session.getAttribute("usuario");
			
			cliente = (Cliente)usuario.getPessoa();
			
			cliente.getCarrinho().getItens().add((ItemCarrinho) resultado.getEntidades().get(0));
			usuario.setPessoa(cliente);
			
			session.setAttribute("usuario", usuario);

			rd = request.getRequestDispatcher("index.jsp");

			
			enviarFront(resultado, request, response);;


	}

}
