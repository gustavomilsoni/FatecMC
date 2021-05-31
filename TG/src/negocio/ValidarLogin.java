package negocio;

import java.util.List;

import dao.UsuarioDao;
import dominio.Cliente;
import dominio.EntidadeDominio;
import dominio.Usuario;

public class ValidarLogin extends AbstractValidadorStrategy {

	private Cliente cliente;
	private UsuarioDao daoUsuario;
	private List<EntidadeDominio> usuarios;

	@SuppressWarnings("unchecked")
	@Override
	public String processar(EntidadeDominio entidade) {

		cliente = (Cliente) entidade;
		daoUsuario = new UsuarioDao();
		usuarios = daoUsuario.consultar(cliente);
		if (usuarios != null) {

			for (Usuario user : (List<Usuario>) (List<?>) usuarios) {
				if (cliente.getUsuario().getUsuario().equals(user.getUsuario())) {

					if (!cliente.getUsuario().getSenha().equals(user.getSenha()))
						sb.append("Senha errada ");

					return verificaMsg();
				}
			}
		} else {
			sb.append("Usuario errado ou inexistente ");
		}

		return verificaMsg();
	}

}
