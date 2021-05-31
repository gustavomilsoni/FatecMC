package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dominio.Categoria;

import dominio.EntidadeDominio;
import dominio.Estoque;
import dominio.GrupoPreco;
import dominio.Livro;

public class LivroDao extends AbstractDao {

	private Livro livro;
	private EstoqueDao daoEstoque;
	private Estoque estoque;

	public LivroDao() {
		super("livro", "livro_id");

	}

	public LivroDao(Connection connection) {
		super(connection, "livro", "livro_id");

	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		resultset = null;
		pst = null;
		sql.setLength(0);
		sb.setLength(0);

		conectarBanco();

		livro = (Livro) entidade;

		sql.append("insert into ");
		sql.append(table);
		sql.append(
				"(livro_autor, livro_categoria_id, livro_ano, livro_situacao, livro_titulo,livro_editora,livro_edicao,livro_isbn,");
		sql.append(
				"livro_num_pag, livro_sinopse, livro_altura, livro_largura, livro_peso,livro_grupo_preco_id,livro_cod_barras,livro_profundidade,livro_margem)");
		sql.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		try {

			connection.setAutoCommit(false);
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, livro.getAutor());
			pst.setInt(2, livro.getCategoria().getId());
			pst.setString(3, livro.getAno());
			pst.setString(4, livro.getStatus());
			pst.setString(5, livro.getTitulo());
			pst.setString(6, livro.getEditora());
			pst.setString(7, livro.getEdicao());
			pst.setString(8, livro.getIsbn());
			pst.setString(9, livro.getNumPag());
			pst.setString(10, livro.getSinopse());
			pst.setString(11, livro.getAltura());
			pst.setString(12, livro.getLargura());
			pst.setString(13, livro.getPeso());
			pst.setInt(14, livro.getGrpPreco().getId());
			pst.setString(15, livro.getCodBarras());
			pst.setString(16, livro.getProfundidade());
			pst.setDouble(17, livro.getMargem());

			pst.executeUpdate();
			resultset = pst.getGeneratedKeys();

			if (resultset.next()) {
				livro.setId(resultset.getInt(1));

			}
			daoEstoque = new EstoqueDao(connection);
			daoEstoque.salvar(entidade);
			

			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sb.append("Erro ao gravar. Tente novamente");
		} finally {
			encerrarConexaoBanco();
		}

	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		sql.setLength(0);
		pst = null;
		livro = (Livro) entidade;
		conectarBanco();

		try {

			sql = new StringBuilder();

			sql.append(
					"update livro set livro_autor=?,livro_categoria_id=?,livro_ano=?,livro_situacao=?,livro_titulo=?,livro_editora=?, ");
			sql.append(
					"livro_edicao=?, livro_isbn=?,livro_num_pag=?,livro_sinopse=?,livro_altura=?,livro_largura=?,livro_peso=?, ");
			sql.append("livro_grupo_preco_id=?,livro_cod_barras=?,livro_profundidade=?,livro_margem=?");
			sql.append(" where livro_id = ?");

			connection.setAutoCommit(false);
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, livro.getAutor());
			pst.setInt(2, livro.getCategoria().getId());
			pst.setString(3, livro.getAno());
			pst.setString(4, livro.getStatus());
			pst.setString(5, livro.getTitulo());
			pst.setString(6, livro.getEditora());
			pst.setString(7, livro.getEdicao());
			pst.setString(8, livro.getIsbn());
			pst.setString(9, livro.getNumPag());
			pst.setString(10, livro.getSinopse());
			pst.setString(11, livro.getAltura());
			pst.setString(12, livro.getLargura());
			pst.setString(13, livro.getPeso());
			pst.setInt(14, livro.getGrpPreco().getId());
			pst.setString(15, livro.getCodBarras());
			pst.setString(16, livro.getProfundidade());
			pst.setDouble(17, livro.getMargem());
			pst.setInt(18, livro.getId());

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
		} finally {
			encerrarConexaoBanco();
		}

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		pst = null;
		sql.setLength(0);
		conectarBanco();

		Livro filtroLivro = (Livro) entidade;

		sql.append("SELECT * from precificacao as p join livro as l on p.precificacao_id = l.livro_grupo_preco_id ");
		sql.append("join estoque as e on l.livro_id = e.estoque_livro_id join categoria as c ");
		sql.append("on l.livro_categoria_id = c.categoria_id");
		sql.append(" WHERE 1=1");

		SqlMapString = new HashMap<String, String>();
		SqlMapInt = new HashMap<String, Integer>();
		SqlMapDouble = new HashMap<String, Double>();

		boolean bTitulo = verificarVazioOuNulo(filtroLivro.getTitulo());
		if (!bTitulo)
			SqlMapString.put(" and livro_titulo Like ?", filtroLivro.getTitulo());
		boolean bId = filtroLivro.getId() == 0;
		if (!bId)
			SqlMapInt.put(" and livro_id=?", filtroLivro.getId());
		if (!verificarVazioOuNulo(filtroLivro.getIsbn()))
			SqlMapString.put(" and livro_isbn Like ?", filtroLivro.getIsbn());
		if (!verificarVazioOuNulo(filtroLivro.getAutor()))
			SqlMapString.put(" and livro_autor Like ?", filtroLivro.getAutor());
		if (!verificarVazioOuNulo(filtroLivro.getEditora()))
			SqlMapString.put(" and livro_editora Like ?", filtroLivro.getEditora());
		if (!verificarVazioOuNulo(filtroLivro.getAno()))
			SqlMapString.put(" and livro_ano Like ?", filtroLivro.getAno());
		if (!verificarVazioOuNulo(filtroLivro.getEdicao()))
			SqlMapString.put(" and livro_edicao Like ?", filtroLivro.getEdicao());
		if (!verificarVazioOuNulo(filtroLivro.getNumPag()))
			SqlMapString.put(" and livro_num_pag Like ?", filtroLivro.getNumPag());
		if (!verificarVazioOuNulo(filtroLivro.getSinopse()))
			SqlMapString.put(" and livro_sinopse Like ?", filtroLivro.getSinopse());
		if (!verificarVazioOuNulo(filtroLivro.getAltura()))
			SqlMapString.put(" and livro_altura Like ?", filtroLivro.getAltura());
		if (!verificarVazioOuNulo(filtroLivro.getPeso()))
			SqlMapString.put(" and livro_peso Like ?", filtroLivro.getPeso());
		if (!verificarVazioOuNulo(filtroLivro.getCodBarras()))
			SqlMapString.put(" and livro_cod_barras Like ?", filtroLivro.getCodBarras());
		if (!verificarVazioOuNulo(filtroLivro.getProfundidade()))
			SqlMapString.put(" and livro_profundidade Like ?", filtroLivro.getProfundidade());
		if (!verificarVazioOuNulo(filtroLivro.getLargura()))
			SqlMapString.put(" and livro_largura Like ?", filtroLivro.getLargura());
		if (filtroLivro.getMargem() != 0.0)
			SqlMapDouble.put(" and livro_margem=?", filtroLivro.getMargem());

		int i = 1;

		for (Map.Entry me : SqlMapString.entrySet()) {
			sql.append(me.getKey());

		}

		for (Map.Entry me : SqlMapInt.entrySet()) {
			sql.append(me.getKey());

		}

		for (Map.Entry me : SqlMapDouble.entrySet()) {
			sql.append(me.getKey());

		}

		try {
			pst = connection.prepareStatement(sql.toString());

			for (Map.Entry me : SqlMapString.entrySet()) {
				pst.setString(i, "%" + me.getValue().toString() + "%");

				i++;
			}
			for (Map.Entry me : SqlMapInt.entrySet()) {
				pst.setInt(i, Integer.valueOf(me.getValue().toString()));

				i++;
			}

			for (Map.Entry me : SqlMapDouble.entrySet()) {
				pst.setDouble(i, Double.valueOf(me.getValue().toString()));

				i++;
			}

			resultset = pst.executeQuery();
			List<EntidadeDominio> livros = new ArrayList<EntidadeDominio>();
			daoEstoque = new EstoqueDao(connection);

			if (resultset.next()) {
				do {

					estoque = new Estoque();
					Categoria categoria = new Categoria(resultset.getString("categoria_nome"));
					categoria.setId(Integer.valueOf(resultset.getString("categoria_id")));
					GrupoPreco precificacao = new GrupoPreco(resultset.getString("precificacao_nome"),
							resultset.getDouble("precificacao_margem"));
					precificacao.setId(Integer.valueOf(resultset.getString("precificacao_id")));
					livro = new Livro(resultset.getInt("livro_id"), resultset.getString("livro_titulo"),
							resultset.getString("livro_isbn"), resultset.getString("livro_autor"),
							resultset.getString("livro_situacao"), resultset.getString("livro_editora"),
							resultset.getString("livro_ano"), resultset.getString("livro_edicao"),
							resultset.getString("livro_num_pag"), resultset.getString("livro_sinopse"),
							resultset.getString("livro_altura"), resultset.getString("livro_peso"),
							resultset.getString("livro_cod_barras"), resultset.getString("livro_profundidade"),
							precificacao, categoria, resultset.getString("livro_largura"), estoque,
							(resultset.getDouble("livro_margem")/100 * resultset.getDouble("estoque_maior_valor_compra"))
									+ resultset.getDouble("estoque_maior_valor_compra"),
							resultset.getDouble("livro_margem"));

					livro.setEstoque((Estoque) daoEstoque.consultar(livro).get(0));

					livros.add(livro);
				}while (resultset.next());
				return livros;
			} else
				return null;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return null;

	}

}
