package controle.VH;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.EntidadeDominio;

import dominio.Pedido;
import util.Resultado;

public class AutorizarTrocaVH extends AbstractViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		session = request.getSession();
		Resultado resultado = (Resultado) session.getAttribute("resultado");
		Pedido pedido;

		if (resultado.getEntidades() != null) {

			for (EntidadeDominio ent : resultado.getEntidades()) {
				
				if (ent.getId() == (Integer.parseInt(request.getParameter("txtPedidoId")))) {

					
					for (int i = 0; i < ((Pedido)ent).getItens().size(); i++) {
						if (((Pedido)ent).getItens().get(i).getId() == (Integer.parseInt(request.getParameter("txtId")))) {
							((Pedido)ent).getItens().get(i).setStatus("S");
							ent.setStatus("TROCA AUTORIZADA");
							pedido = (Pedido)ent;
							pedido.setValorPago(pedido.getTotal());
							return pedido;
						}
					}

					
				}

			}


		}
		return null;

	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
		rd = request.getRequestDispatcher("frmAdmInicial.jsp");
		enviarFront(resultado, request, response);
	}

}
