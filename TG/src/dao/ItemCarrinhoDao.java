package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Carrinho;
import dominio.EntidadeDominio;
import dominio.ItemCarrinho;
import dominio.Livro;

public class ItemCarrinhoDao extends AbstractDao {

	private ItemCarrinho item;
	private Livro livro;
	private Carrinho carrinho;

	public ItemCarrinhoDao() {
		super("item_carrinho","item_carrinho_id");

	}

	public ItemCarrinhoDao(Connection connection) {
		super(connection, "item_carrinho", "item_carrinho_id");

	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		resultset = null;
		pst = null;
		sql.setLength(0);
		sb.setLength(0);

		conectarBanco();

		item = (ItemCarrinho) entidade;

		sql.append("insert into ");
		sql.append(table);
		sql.append("(item_carrinho_livro_id, item_carrinho_quantidade, item_carrinho_valor, item_carrinho_carrinho_id)");
		sql.append("values(?,?,?,?)");

		try {

			connection.setAutoCommit(false);
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, item.getLivro().getId());
			pst.setInt(2, item.getQuantidade());
			pst.setDouble(3, item.getLivro().getValorVenda());
			pst.setInt(4, item.getCarrinho().getId());

			pst.executeUpdate();
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
		
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		resultset = null;
		pst = null;
		sql.setLength(0);


		conectarBanco();

		carrinho = (Carrinho) entidade;

		sql.append("SELECT * from item_carrinho WHERE item_carrinho_carrinho_id =?");

		try {

			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, carrinho.getId());
			resultset = pst.executeQuery();

			LivroDao daoLivro = new LivroDao();

			List<EntidadeDominio> livros = new ArrayList<EntidadeDominio>();
			List<EntidadeDominio> itens = new ArrayList<EntidadeDominio>();
			while (resultset.next()) {

				item = new ItemCarrinho();
				item.setQuantidade(resultset.getInt("item_carrinho_quantidade"));
				item.setId(resultset.getInt("item_carrinho_id"));
				livro = new Livro();
				livro.setId(resultset.getInt("item_carrinho_livro_id"));
				livros = daoLivro.consultar(livro);
				item.setLivro((Livro) livros.get(0));
				itens.add(item);

			}
			return itens;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
