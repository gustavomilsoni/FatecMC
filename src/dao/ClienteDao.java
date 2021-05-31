package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dominio.Carrinho;
import dominio.Cartao;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Pedido;
import dominio.Pessoa;
import dominio.Usuario;
import util.Conexao;

public class ClienteDao extends AbstractDao {

	private Cliente cliente;
	private List<Endereco> enderecos;
	private List<Cartao> cartoes;
	private List<Pedido> pedidos;
	private CliEnderecoDao daoEnd;
	private CartaoDao daoCart;
	private PedidoDao daoPedido;
	private Pedido pedido;
	private UsuarioDao daoUsuario;
	private List<EntidadeDominio> carrinhos;
	private CarrinhoDao daoCarrinho;
	private Carrinho carrinho;
	private Usuario usuario;

	public ClienteDao() {
		super("cliente", "cli_id");

	}
	

	public ClienteDao(Connection connection) {
		super(connection,"cliente", "cli_id");

	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void salvar(EntidadeDominio entidade) {

		resultset = null;
		pst = null;
		sql.setLength(0);
		sb.setLength(0);
		conectarBanco();
		cliente = (Cliente) entidade;

		sql.append("insert into ");
		sql.append(table);
		sql.append(
				"(CLI_NOME, CLI_CPF, CLI_GENERO, CLI_DATA_NASC,cli_telefone,cli_tipo_telefone,cli_email,cli_usuario_id)");
		sql.append("values(?,?,?,?,?,?,?,?)");

		try {

			connection.setAutoCommit(false);
			daoUsuario = new UsuarioDao(connection);
			daoUsuario.salvar(cliente.getUsuario());
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getCpf());
			pst.setString(3, cliente.getGenero());
			pst.setString(4, cliente.getDataNascimento());
			pst.setString(5, cliente.getTelefone());
			pst.setString(6, cliente.getTipoFone());
			pst.setString(7, cliente.getEmail());
			pst.setInt(8, ((List<Usuario>) (List<?>) daoUsuario.consultar(cliente.getUsuario())).get(0).getId());
			pst.executeUpdate();

			resultset = pst.getGeneratedKeys();

			if (resultset.next()) {
				cliente.setId(resultset.getInt(1));

			}

			CliEnderecoDao daoEnd = new CliEnderecoDao(connection);
			daoEnd.salvar(cliente);

			CartaoDao daoCart = new CartaoDao(connection);
			daoCart.salvar(cliente);

			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sb.append("Erro ao gravar. Tente novamente");
		}
		encerrarConexaoBanco();

	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		sql.setLength(0);
		pst = null;
		cliente = (Cliente) entidade;

		try {
			connection = Conexao.getConnectionPostgres();
			connection.setAutoCommit(false);

			sql.append(
					"UPDATE cliente SET cli_nome=?, cli_cpf=?,cli_genero=?,cli_data_nasc=?,cli_tipo_telefone=?,cli_telefone=?, ");
			sql.append(" cli_email=?,cli_senha=?");
			sql.append("WHERE cli_id = ?");

			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getCpf());
			pst.setString(3, cliente.getGenero());
			pst.setString(4, cliente.getDataNascimento());
			pst.setString(5, cliente.getTipoFone());
			pst.setString(6, cliente.getTelefone());
			pst.setString(7, cliente.getEmail());
			pst.setString(8, cliente.getUsuario().getSenha());
			pst.setInt(9, cliente.getId());

			pst.executeUpdate();
			connection.commit();
		} catch (ClassNotFoundException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		encerrarConexaoBanco();

	}

	public void excluir(EntidadeDominio entidade) {

		super.excluir(entidade);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		pst = null;
		sql.setLength(0);
		conectarBanco();

		Cliente filtroCliente = (Cliente) entidade;

		if (filtroCliente.getId() != 0) {
			sql.append("SELECT * from cliente WHERE cli_id =?");

		} else if (!"".equals(filtroCliente.getNome()) && filtroCliente.getNome() != null) {
			sql.append("SELECT * from cliente WHERE cli_nome like ?");
		} else if (!"".equals(filtroCliente.getCpf()) && filtroCliente.getCpf() != null) {
			sql.append("SELECT * from cliente WHERE cli_cpf like ?");

		} else if (filtroCliente.getEmail() != null && !filtroCliente.getEmail().equals("")) {
			sql.append("SELECT * from cliente WHERE cli_email like ?");
		}
		try {

			pst = connection.prepareStatement(sql.toString());

			if (filtroCliente.getId() != 0) {
				pst.setInt(1, filtroCliente.getId());
			} else if (!"".equals(filtroCliente.getNome()) && filtroCliente.getNome() != null) {
				pst.setString(1, "%" + filtroCliente.getNome() + "%");
			} else if (!"".equals(filtroCliente.getCpf()) && filtroCliente.getCpf() != null) {
				pst.setString(1, "%" + filtroCliente.getCpf() + "%");
			} else if (!"".equals(filtroCliente.getEmail()) && filtroCliente.getEmail() != null) {
				pst.setString(1, "%" + filtroCliente.getEmail() + "%");
			}

			resultset = pst.executeQuery();
			List<EntidadeDominio> clientes = new ArrayList<EntidadeDominio>();
			enderecos = new ArrayList<Endereco>();
			cartoes = new ArrayList<Cartao>();
			pedidos = new ArrayList<Pedido>();

			daoEnd = new CliEnderecoDao();
			daoCart = new CartaoDao(connection);
			daoPedido = new PedidoDao();
			daoUsuario = new UsuarioDao(connection);

			while (resultset.next()) {
				


				cliente = new Cliente(resultset.getInt("cli_id"), resultset.getString("cli_telefone"),
						resultset.getString("cli_tipo_telefone"), enderecos, resultset.getString("cli_nome"),
						resultset.getString("cli_cpf"), cartoes, resultset.getString("cli_genero"),
						resultset.getString("cli_data_nasc"), resultset.getString("cli_email"));

				usuario = new Usuario();
				usuario.setId(Integer.parseInt(resultset.getString("cli_usuario_id")));
				if (filtroCliente.getUsuario() == null ||filtroCliente.getUsuario().getId() != 0) 
					cliente.setUsuario(filtroCliente.getUsuario());
				
				else
					
					cliente.setUsuario(((List<Usuario>) (List<?>) daoUsuario.consultar(usuario)).get(0));
				

				for (EntidadeDominio ent : daoCart.consultar(cliente)) {
					cartoes.add((Cartao) ent);
				}

				for (EntidadeDominio ent : daoEnd.consultar(cliente)) {
					enderecos.add((Endereco) ent);
				}

				pedido = new Pedido();
				pedido.setCli(cliente);
				entidades = daoPedido.consultar(pedido);
				if (entidades != null) {
					for (EntidadeDominio ent : entidades) {
						pedidos.add((Pedido) ent);
					}
				}

				cliente.setPedidos(pedidos);
				cliente.setEnderecos(enderecos);

				cliente.setCartoes(cartoes);

				clientes.add(cliente);
			}
			

			if (filtroCliente.getUsuario() != null && filtroCliente.getCarrinho()== null 
					&& !((Cliente) clientes.get(0)).getUsuario().isAdm()) {
				daoCarrinho = new CarrinhoDao(connection);
				carrinho = new Carrinho((Cliente) clientes.get(0));
				carrinhos = daoCarrinho.consultar(carrinho);

				if (carrinhos == null) {
					daoCarrinho.salvar(carrinho);
					carrinhos = daoCarrinho.consultar(carrinho);
				}

				carrinho = (Carrinho) carrinhos.get(0);
				carrinho.setCliente((Cliente) clientes.get(0));
				cliente = (Cliente) clientes.get(0);
				cliente.setCarrinho(carrinho);
				clientes = new ArrayList<EntidadeDominio>();
				clientes.add(cliente);

			}

			return clientes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
