package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.BandeiraCart;
import dominio.Cartao;
import dominio.Cupom;
import dominio.EntidadeDominio;
import dominio.FormaPgto;
import dominio.ItemPedido;
import dominio.Pedido;

public class itemPgtoCupomDao extends AbstractDao {

	private Pedido pedido;
	private Cupom cupom;
	private CupomDao daoCupom;
	private ItemPedido item;
	

	public itemPgtoCupomDao(Connection connection) {
		super(connection, "item_pgto_cupom", "item_pgto_cupom_id");

	}
	
	public itemPgtoCupomDao() {
		super( "item_pgto_cupom", "item_pgto_cupom_id");

	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		pedido = (Pedido) entidade;
		pst= null;
		sql.setLength(0);

		
		sql.append("insert into ");
		sql.append(table);
		sql.append(" (item_pgto_cupom_cupom_id,item_pgto_cupom_valor,item_pgto_cupom_pedido_id)");
		sql.append(" values (?,?,?)");
		
		for(FormaPgto pgto : pedido.getFormasPgto()) {
			conectarBanco();
		    if(pgto instanceof Cupom){
		    	try {
		    		double valorRestante;
		    		valorRestante = pgto.getnValorParcela() - pedido.getTotal();
		    		if(valorRestante < 0.0)
		    			valorRestante = 0.0;
		    		
		    		daoCupom = new CupomDao();
		    		//pgto.setnValorParcela(pedido.getTotal() - pgto.getnValorParcela());
		    		if(pedido.getTotal() < pgto.getnValorParcela()  )
		    			pgto.setnValorParcela(pedido.getTotal());
					pst = connection.prepareStatement(sql.toString());
					pst.setInt(1, pgto.getId());
					pst.setDouble(2, pgto.getnValorParcela());
					pst.setInt(3, pedido.getId());
					
					pst.executeUpdate();
					pgto.setnValorParcela(valorRestante);
					daoCupom.alterar(pgto);
					
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
		
		conectarBanco();
		
		sql.append("update ");
		sql.append(table);
		sql.append(" set item_pgto_cartao_valor_parcela=?, item_pgto_cartao_qnt_parcelas=?");
		sql.append(" where item_pgto_cartao_id=?");
		
		
		for(FormaPgto pgto : pedido.getFormasPgto()) {
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
		sql.append(" where item_pgto_cupom_pedido_id=?");

		try {

			pst = connection.prepareStatement(sql.toString());

			pst.setInt(1, pedido.getId());

			resultset = pst.executeQuery();

			daoCupom = new CupomDao();
			entidades = new ArrayList<EntidadeDominio>();

			while (resultset.next()) {

				cupom = new Cupom();
				cupom.setId(resultset.getInt("item_pgto_cupom_cupom_id"));
				item = (ItemPedido) daoCupom.consultar(cupom).get(0);
				cupom = item.getCupom();
				cupom.setnQtdParcelas(1);
				cupom.setnValorParcela(resultset.getDouble("item_pgto_cupom_valor"));
				entidades.add(cupom);

			}
			return entidades;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
