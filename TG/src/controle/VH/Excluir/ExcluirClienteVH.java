package controle.VH.Excluir;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.VH.AbstractViewHelper;
import dominio.Cliente;
import dominio.EntidadeDominio;
import util.Resultado;

public class ExcluirClienteVH extends AbstractViewHelper {

	private Cliente cliente;

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {

		if (request.getParameter("txtId") != null) {
			cliente = new Cliente();

			cliente.setId(Integer.parseInt(request.getParameter("txtId")));

		}
		return cliente;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
		
		session = request.getSession();
		cliente = (Cliente) session.getAttribute("cliente");
		if (cliente.getUsuario().isAdm()) {
			if (resultado.getMsg().length() == 0) {
				rd = request.getRequestDispatcher("frmAdmInicial.jsp");
			} else {
				
			}
		} else {
			if (resultado.getMsg().length() == 0) {
				rd = request.getRequestDispatcher("index.jsp");
				
			} else {
				rd = request.getRequestDispatcher("frmCadCli.jsp");
			}

		}


		enviarFront(resultado, request, response);

	}

}
