package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.*;


public class ItemPgtoCartaoDao extends AbstractDao {

	private Pedido pedido;
	private Cartao cartao;
    private CartaoDao daoCartao;

	public ItemPgtoCartaoDao(Connection connection) {
		super(connection, "item_pgto_cartao", "item_pgto_cartao_id");

	}
	
	public ItemPgtoCartaoDao() {
		super( "item_pgto_cartao", "item_pgto_cartao_id");

	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		pedido = (Pedido) entidade;
		pst= null;
		sql.setLength(0);
		conectarBanco();
		
		sql.append("insert into ");
		sql.append(table);
		sql.append("(item_pgto_cartao_valor_parcela,item_pgto_cartao_qnt_parcelas,item_pgto_cartao_pedido_id,item_pgto_cartao_cartao_id)");
		sql.append("values(?,?,?,?)");

		pedido = (Pedido) entidade;

		for (FormaPgto forma : pedido.getFormasPgto()) {
			if (forma instanceof Cartao) {

				try {
					pst = connection.prepareStatement(sql.toString());
					pst.setDouble(1, forma.getnValorParcela());
					pst.setInt(2, forma.getnQtdParcelas());
					pst.setInt(3, pedido.getId());
					pst.setInt(4, forma.getId());

					pst.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		
		
		
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		pst = null;
		sql.setLength(0);
		
		pedido = (Pedido) entidade;
		
		
		sql.append("update ");
		sql.append(table);
		sql.append(" set item_pgto_cartao_valor_parcela=?, item_pgto_cartao_qnt_parcelas=?");
		sql.append(" where item_pgto_cartao_id=?");
		
		
		for(FormaPgto pgto : pedido.getFormasPgto()) {
			conectarBanco();
		    if(pgto instanceof Cartao){
		    	try {
					pst = connection.prepareStatement(sql.toString());
					pst.setDouble(1, pgto.getnValorParcela());
					pst.setInt(2, pgto.getnQtdParcelas());
					pst.setInt(3, pgto.getId());
					
					pst.executeUpdate();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    } 
		}
		

			
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		pst = null;
		sql.setLength(0);
		pedido = (Pedido) entidade;

		conectarBanco();
		
		sql.append("select * from ");
		sql.append(table);
		sql.append(" where item_pgto_cartao_pedido_id=?");
		
		try {

			pst = connection.prepareStatement(sql.toString());

			pst.setInt(1, pedido.getId());

			resultset = pst.executeQuery();
			entidades = new ArrayList<EntidadeDominio>();
			daoCartao = new CartaoDao();
			
			while (resultset.next()) {
				
				cartao = new Cartao();
				cartao.setId(resultset.getInt("item_pgto_cartao_cartao_id"));
				cartao = (Cartao)daoCartao.consultar(cartao).get(0);
				
				cartao.setnValorParcela(resultset.getDouble("item_pgto_cartao_valor_parcela"));
				cartao.setnQtdParcelas(resultset.getInt("item_pgto_cartao_qnt_parcelas"));


				entidades.add(cartao);
				
			}
			return entidades;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
