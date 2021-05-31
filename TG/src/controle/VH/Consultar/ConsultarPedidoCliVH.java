package controle.VH.Consultar;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controle.VH.AbstractViewHelper;
import dominio.Cliente;
import dominio.EntidadeDominio;
import dominio.Pedido;
import dominio.Usuario;
import util.Resultado;

public class ConsultarPedidoCliVH extends AbstractViewHelper {

	private Pedido pedido;
	private Usuario usuario;

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		session = request.getSession();
		pedido = new Pedido();
		usuario = (Usuario) session.getAttribute("usuario");
		pedido.setCli((Cliente)usuario.getPessoa());

		return pedido;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
		session = request.getSession();
		if (resultado.getEntidades() != null)
			session.setAttribute("pedidos", (List<Pedido>) (List<?>) resultado.getEntidades());
		else
			session.setAttribute("pedidos", resultado.getEntidades());

		rd = request.getRequestDispatcher("frmPedidos.jsp");

		enviarFront(resultado, request, response);

	}

}
