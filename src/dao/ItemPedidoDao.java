package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dominio.EntidadeDominio;
import dominio.ItemPedido;
import dominio.Livro;
import dominio.Pedido;

public class ItemPedidoDao extends AbstractDao {
	
	private LivroDao daoLivro;
	
	private EstoqueDao daoEstoque;
    private CupomDao daoCupom;
	
	public ItemPedidoDao(Connection connection) {
		super(connection, "item_pedido","item_pedido_id");
		// TODO Auto-generated constructor stub
	}

	public ItemPedidoDao() {
		super("item_pedido");
		// TODO Auto-generated constructor stub
	}



	Pedido pedido;

	@Override
	public void salvar(EntidadeDominio entidade) {
		
		resultset = null;
		pst = null;
		sql.setLength(0);
		sb.setLength(0);

		conectarBanco();

		pedido = (Pedido) entidade;

		for (ItemPedido item : pedido.getItens()) {

			sql.append("insert into ");
			sql.append(table);
			sql.append("(item_pedido_livro_id, item_pedido_quantidade, item_pedido_valor, item_pedido_pedido_id)");
			sql.append("values(?,?,?,?)");

			try {

				connection.setAutoCommit(false);
				pst = connection.prepareStatement(sql.toString());
				pst.setInt(1, item.getLivro().getId());
				pst.setInt(2, item.getQuantidade());
				pst.setDouble(3, item.getLivro().getValorVenda());
				pst.setInt(4, pedido.getId());

				pst.executeUpdate();

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


	}

	@Override
	public void alterar(EntidadeDominio entidade) {

		sql.setLength(0);
		resultset = null;
		pst = null;
		conectarBanco();
		pedido = (Pedido) entidade;

		
		if(pedido.getStatus().equals("TROCA AUTORIZADA")) {
			
			for(ItemPedido item : pedido.getItens()) {
				if(item.getCupom() != null && item.getCupom().getId() == 0 && item.getStatus().equals("S")) {
					daoEstoque = new EstoqueDao(connection);
					daoEstoque.alterar(item.getLivro());
					daoCupom = new CupomDao(connection);
					daoCupom.salvar(item);
					
				}else if(item.getCupom() != null && item.getCupom().getId() > 0 && item.getStatus().equals("S")) {

					daoCupom = new CupomDao(connection);
					daoCupom.alterar(item);
					
				}
			}			
			
			for(ItemPedido item : pedido.getItens()) {
				if(item.getStatus().equals("S")) {
														
					daoEstoque = new EstoqueDao(connection);
					daoEstoque.alterar(item.getLivro());
					daoCupom = new CupomDao(connection);
					if(item.getCupom() != null) {

						daoCupom.alterar(item);
						
					}else {
						daoCupom.salvar(item);
					}
					
					
				}
			}
			
		}

		try {


		sql.append("update item_pedido set item_pedido_pedido_id=?, item_pedido_livro_id=?,item_pedido_quantidade=?, ");
		sql.append("item_pedido_valor=?, item_pedido_troca=? ");
		sql.append("WHERE item_pedido_id=?");

			for (ItemPedido item : pedido.getItens()) {

				pst = null;

				pst = connection.prepareStatement(sql.toString());
				pst.setInt(1, pedido.getId());
				pst.setInt(2, item.getLivro().getId());
				pst.setInt(3, item.getQuantidade());
				pst.setDouble(4, item.getLivro().getValorVenda());
				pst.setString(5, item.getStatus());
				pst.setInt(6, item.getId());

				pst.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		resultset = null;
		pst = null;
		sql.setLength(0);
		ItemPedido item;
		Livro livro;

		conectarBanco();

		pedido = (Pedido) entidade;

		sql.append("SELECT * from item_pedido WHERE item_pedido_pedido_id =?");

		try {

			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, pedido.getId());
			resultset = pst.executeQuery();

			daoLivro = new LivroDao();
			daoCupom = new CupomDao(connection);

			List<EntidadeDominio> livros = new ArrayList<EntidadeDominio>();
			List<EntidadeDominio> itens = new ArrayList<EntidadeDominio>();
			while (resultset.next()) {

				item = new ItemPedido();
				item.setQuantidade(resultset.getInt("item_pedido_quantidade"));
				livro = new Livro();
				livro.setId(resultset.getInt("item_pedido_livro_id"));
				livros = daoLivro.consultar(livro);
				item.setLivro((Livro) livros.get(0));
				item.setId(resultset.getInt("item_pedido_id"));
				item.setStatus("item_pedido_troca");
				entidades = daoCupom.consultar(item);
				if(entidades != null) {
					item.setCupom(((ItemPedido)entidades.get(0)).getCupom());
					item.setStatus("S");
				}
				itens.add(item);
				

			}
			return itens;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
