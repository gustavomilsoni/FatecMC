package controle.VH;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dominio.EntidadeDominio;

import dominio.Pedido;
import util.Resultado;

public class DevolverProdutoVH extends AbstractViewHelper {
	

	private Pedido pedido;

	private List<Pedido> pedidos;

	@SuppressWarnings("unchecked")
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		session = request.getSession();
		pedidos = (List<Pedido>) session.getAttribute("pedidos");
		for(Pedido p : pedidos) {
			if(p.getId() == (Integer.parseInt(request.getParameter("txtPedidoId"))) ) {
				pedido = p;
				
				for(int i = 0; i < pedido.getItens().size() ; i++) {
					if(pedido.getItens().get(i).getId() == (Integer.parseInt(request.getParameter("txtId"))) ) {
						pedido.getItens().get(i).setStatus("S");
						break;
					}
				}
				break;
			}

		}
		
		pedido.setStatus("EM TROCA");
		pedido.setValorPago(pedido.getTotal());
		return pedido;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {


			rd = request.getRequestDispatcher("index.jsp");
			
			enviarFront(resultado, request, response);


		
	}

}
