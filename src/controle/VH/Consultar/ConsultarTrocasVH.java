package controle.VH.Consultar;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.VH.AbstractViewHelper;
import dominio.EntidadeDominio;
import dominio.Pedido;
import dominio.Usuario;
import util.Resultado;

public class ConsultarTrocasVH extends AbstractViewHelper {
	
	private Pedido pedido;

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		pedido = new Pedido();
		pedido.setStatus("EM TROCA");
		
		return pedido;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
		session = request.getSession();
		session.setAttribute("resultado", resultado);
		rd = request.getRequestDispatcher("frmAdm.jsp");
		enviarFront(resultado, request, response);
		
	}
	


}
