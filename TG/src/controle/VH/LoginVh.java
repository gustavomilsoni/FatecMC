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

public class LoginVh extends AbstractViewHelper {

	private Usuario usuario;
	private String email;
	private String senha;

	
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {

		email = request.getParameter("txtEmail");
		senha = request.getParameter("txtSenha");

		if (email == null) {
			email = "";
		}

		if (senha == null) {
			senha = "";
		}



		usuario = new Usuario();
		usuario.setUsuario(email);
		usuario.setSenha(senha);


		return usuario;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
		session = request.getSession();
		usuario = (Usuario) resultado.getEntidades().get(0);

		session.setAttribute("usuario", usuario);
		if (usuario.isAdm()) {
			rd = request.getRequestDispatcher("frmAdmInicial.jsp");

		} else {

			rd = request.getRequestDispatcher("index.jsp");

		}

		enviarFront(resultado, request, response);

	}

}
