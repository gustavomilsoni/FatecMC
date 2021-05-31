package controle.VH.Consultar;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.VH.AbstractViewHelper;
import dominio.Carrinho;
import dominio.Cliente;
import dominio.EntidadeDominio;
import dominio.Usuario;
import util.Resultado;

public class ConsultarCarrinhoVH extends AbstractViewHelper {

	private Carrinho carrinho;
	private Cliente cli;
	private Usuario usuario;

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		session = request.getSession();
		usuario = (Usuario) session.getAttribute("usuario");
		cli = (Cliente) usuario.getPessoa();
		carrinho = new Carrinho();
		carrinho.setCliente(cli);
		return carrinho;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {

		

		if (resultado.getEntidades() != null) {
			carrinho = (Carrinho) resultado.getEntidades().get(0);

			if (carrinho.getItens() == null ||carrinho.getItens().isEmpty()) {

				resultado.setMsg("Não existe produto");
			}
		}
		rd = request.getRequestDispatcher("frmCarrinho.jsp");
		enviarFront(resultado, request, response);

	}

}
