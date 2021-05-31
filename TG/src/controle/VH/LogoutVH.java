package controle.VH;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Cliente;
import dominio.EntidadeDominio;
import dominio.Pessoa;
import dominio.Usuario;
import util.Resultado;

public class LogoutVH extends AbstractViewHelper {

	private Usuario usuario;


	
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {

		session = request.getSession();
		usuario = (Usuario) session.getAttribute("usuario");

		return usuario;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {

		usuario = null;
		session.invalidate();
		session = request.getSession();
		session.setAttribute("usuario", usuario);

		rd = request.getRequestDispatcher("index.jsp");


		enviarFront(resultado, request, response);

	}

}
