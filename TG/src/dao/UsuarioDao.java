package dao;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;


import dominio.Cliente;
import dominio.EntidadeDominio;
import dominio.Pessoa;
import dominio.Usuario;


public class UsuarioDao extends AbstractDao {

	private Usuario usuario;
	private ClienteDao daoCliente;
	private Cliente cliente;
	private List<EntidadeDominio> clientes;

	public UsuarioDao(Connection connection) {
		super(connection, "usuario", "usuario_id");

	}

	public UsuarioDao() {
		super("usuario", "usuario_id");

	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		usuario = (Usuario) entidade;
		pst = null;
		sql.setLength(0);

		conectarBanco();

		sql.append("insert into ");
		sql.append(table);
		sql.append(" (usuario_login,usuario_senha,usuario_adm)");
		sql.append(" values (?,?,?)");

		try {
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, usuario.getUsuario());
			pst.setString(2, usuario.getSenha());
			pst.setBoolean(3, usuario.isAdm());

			pst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		pst = null;
		sql.setLength(0);

		usuario = (Usuario) entidade;

		conectarBanco();

		sql.append("update ");
		sql.append(table);
		sql.append(" set usuario_login=?, usuario_senha=?");
		sql.append(" where usuario_id=?");

		try {
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, usuario.getUsuario());
			pst.setString(2, usuario.getSenha());
			pst.setInt(3, usuario.getId());

			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		pst = null;
		sql.setLength(0);
		conectarBanco();
		Usuario filtroUsuario = (Usuario) entidade;

		
		if(filtroUsuario == null) {
			return entidades;
		}else {
		
			sql.append("select * from ");
			sql.append(table);
			sql.append(" where usuario_login=?");
		}

		try {

			pst = connection.prepareStatement(sql.toString());
			
			if(filtroUsuario.getId() !=0) {
			    pst.setInt(1, filtroUsuario.getId());
			}else {
			    pst.setString(1, filtroUsuario.getUsuario());
			}




			resultset = pst.executeQuery();


			if (resultset.next()) {
				daoCliente = new ClienteDao();
				entidades = new ArrayList<EntidadeDominio>();
				
				
				do {
					

					usuario = new Usuario(resultset.getInt("usuario_id"),resultset.getString("usuario_login"),
							resultset.getString("usuario_senha"), resultset.getBoolean("usuario_adm"));
					
					if(filtroUsuario.getPessoa() == null) {
						

					cliente = new Cliente();
					cliente.setEmail(usuario.getUsuario());
					cliente.setUsuario(usuario);
					clientes = daoCliente.consultar(cliente);
					usuario.setPessoa((Cliente)clientes.get(0));
					}
					
					entidades.add(usuario);

				}while (resultset.next());
			} 
			return entidades;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
