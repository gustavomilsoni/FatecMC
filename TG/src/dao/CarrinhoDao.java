package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import dominio.Carrinho;
import dominio.Cartao;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.ItemPedido;
import dominio.Livro;
import dominio.Pedido;

public class CarrinhoDao extends AbstractDao {


	private Carrinho carrinho;
	private List<EntidadeDominio> carrinhos;
	private List<EntidadeDominio> livros;
	private ItemCarrinhoDao itemDao;
	private LivroDao daoLivro;

	public CarrinhoDao() {
		super("carrinho", "carrinho_id");
	}
	
	public CarrinhoDao(Connection connection) {
		super(connection, "carrinho","carrinho_id");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) {

		resultset = null;
		pst = null;
		sql.setLength(0);
		sb.setLength(0);

		conectarBanco();

		carrinho = (Carrinho) entidade;

		carrinhos = consultar(carrinho);
		
		resultset = null;
		pst = null;
		sql.setLength(0);
		sb.setLength(0);

		try {
			connection.setAutoCommit(false);
		} catch (SQLException e2) {

			e2.printStackTrace();
		}

		if (carrinhos == null) {
			sql.append("insert into ");
			sql.append(table);
			sql.append("(carrinho_cliente_id)");
			sql.append("values(?)");

			try {

				pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				pst.setInt(1, carrinho.getCliente().getId());

				pst.executeUpdate();
				resultset = pst.getGeneratedKeys();

				if (resultset.next()) {

					carrinho.setId(resultset.getInt(1));
				}

			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sb.append("Erro ao gravar. Tente novamente");

			}

		} else {

			carrinho.setId(carrinhos.get(0).getId());
			daoLivro = new LivroDao();
			livros = daoLivro.consultar(carrinho.getItens().get(0).getLivro());
			carrinho.getItens().get(0).setLivro((Livro) livros.get(0));
			itemDao = new ItemCarrinhoDao(connection);
			itemDao.salvar(carrinho);
		}


		try {
			connection.commit();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} finally {
			encerrarConexaoBanco();
		}

	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void excluir(EntidadeDominio entidade) {
		carrinho = (Carrinho) entidade;
		itemDao = new ItemCarrinhoDao();
		for(ItemCarrinho i : carrinho.getItens()) {
			 itemDao.excluir(i);
		}
		super.excluir(entidade);
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

		resultset = null;
		pst = null;
		sql.setLength(0);
		conectarBanco();
		if (entidade == null) {
			return null;
		}

		carrinho = (Carrinho) entidade;

		if (carrinho.getId() != 0) {
			sql.append("SELECT * from carrinho WHERE carrinho_id =?");

		} else {
			sql.append("SELECT * from carrinho WHERE carrinho_cliente_id = ?");
		}
		try {
	
			pst = connection.prepareStatement(sql.toString());

			if (carrinho.getId() != 0) {
				pst.setInt(1, carrinho.getId());
			} else {
				pst.setInt(1, carrinho.getCliente().getId());
			}

			resultset = pst.executeQuery();
			ItemCarrinhoDao itemDao;
			List<EntidadeDominio> itensCarrinho = new ArrayList<EntidadeDominio>();
			List<EntidadeDominio> carrinhos = new ArrayList<EntidadeDominio>();
			itemDao = new ItemCarrinhoDao(connection);
			if (resultset.next()) {
				Carrinho car = new Carrinho();
				do {

					car.setId(resultset.getInt("carrinho_id"));

					itensCarrinho = itemDao.consultar(car);
					car.setItens(new ArrayList<ItemCarrinho>());

					for (EntidadeDominio ent : itensCarrinho) {
						car.getItens().add((ItemCarrinho) ent);
					}
					carrinhos.add(car);

				} while (resultset.next());
				return carrinhos;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
