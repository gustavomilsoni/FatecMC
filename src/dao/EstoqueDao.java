package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;


import dominio.EntidadeDominio;
import dominio.Estoque;

import dominio.Livro;


public class EstoqueDao extends AbstractDao {

	private Estoque estoque;
	private Livro livro;
	public EstoqueDao() {
		super("estoque", "estoque_id");

	}

	public EstoqueDao(Connection connection) {
		super(connection, "estoque", "estoque_id");

	}

	@Override
	public void salvar(EntidadeDominio entidade) {
	       livro = (Livro) entidade;

		pst = null;
		sql.setLength(0);
		
		sql.append("insert into ");
		sql.append(table);
		sql.append("(estoque_qnt_total, estoque_maior_valor_compra, estoque_livro_id) values");
		sql.append("(?,?,?)");	
		
		try {

			pst = connection.prepareStatement(sql.toString());

			pst.setInt(1, 0);
			pst.setDouble(2, 0.0);
			pst.setInt(3, livro.getId());

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

	@Override
	public void alterar(EntidadeDominio entidade) {
	       livro = (Livro) entidade;

		pst = null;
		sql.setLength(0);

		if (connection == null) {
			try {
				openConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {

			sql.append("update estoque set estoque_qnt_total=?, estoque_maior_valor_compra=? where estoque_id = ?");


			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, livro.getEstoque().getQuantidadeEstoque());
			pst.setDouble(2, livro.getEstoque().getMaiorValorCompra());
			pst.setInt(3, livro.getId());

			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
       livro = (Livro) entidade;

		pst = null;
		sql.setLength(0);

		conectarBanco();

		sql.append("select * from ");
		sql.append(table);
		sql.append(" where ");
		sql.append("estoque_livro_id");
		sql.append("=?");

		try {
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, livro.getId());

			resultset = pst.executeQuery();

			if (resultset.next()) {
				entidades = new ArrayList<EntidadeDominio>();
				
				do {
					
					estoque = new Estoque(resultset.getInt("estoque_qnt_total"));
					estoque.setMaiorValorCompra(resultset.getDouble("estoque_maior_valor_compra"));
					estoque.setId(resultset.getInt("estoque_id"));

					entidades.add(estoque);


				} while (resultset.next());
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entidades;

	}

}
