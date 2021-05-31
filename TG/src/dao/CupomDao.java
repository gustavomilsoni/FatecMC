package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import dominio.Cliente;
import dominio.Cupom;
import dominio.EntidadeDominio;
import dominio.Estoque;
import dominio.FormaPgto;
import dominio.ItemPedido;
import dominio.Pedido;


public class CupomDao extends AbstractDao {
	private Cliente cliente;
	
	private ItemPedido item;
	private Pedido pedido;
	private Cupom cupom;

	public CupomDao(Connection connection) {
		super(connection, "cupom", "cupom_id");

	}
	
	public CupomDao() {
		super( "cupom", "cupom_id");

	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		item = (ItemPedido) entidade;
		pst= null;
		sql.setLength(0);
		conectarBanco();
		
		sql.append("insert into ");
		sql.append(table);
		sql.append(" (cupom_numero,cupom_valor,cupom_item_id)");
		sql.append(" values (?,?,?)");
		
		try {
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, item.getCupom().getNumCupom());
			pst.setDouble(2, item.getCupom().getnValorParcela());
			pst.setDouble(3, item.getId());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		pst = null;
		sql.setLength(0);
		

		
		conectarBanco();
		
		sql.append("update ");
		sql.append(table);
		sql.append(" set cupom_numero=?, cupom_valor=?");
	    if(entidade instanceof Cupom)
	    	sql.append(" where cupom_id=?");
	    else
	    	sql.append(" where cupom_item_id=?");
		
		try {
			pst = connection.prepareStatement(sql.toString());
		    if(entidade instanceof Cupom){
				pst.setString(1, ((Cupom) entidade).getNumCupom());
				pst.setDouble(2, ((Cupom) entidade).getnValorParcela());
				pst.setInt(3, entidade.getId());
		    }
		    else
		    {
				item = (ItemPedido) entidade;
				pst.setString(1, item.getCupom().getNumCupom());
				pst.setDouble(2, item.getCupom().getnValorParcela());
				pst.setInt(3, item.getId());
		    }
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		pst= null;
		sql.setLength(0);
		conectarBanco();
		

		
		    if(entidade instanceof ItemPedido){
		    	if(entidade.getStatus() != null && entidade.getStatus().equals("item_pedido_troca")) {
					item = (ItemPedido) entidade;
					sql.append("select * from ");
					sql.append(table);
					sql.append(" where cupom_item_id=? ");
		    	}else
		    		return null;
				
		    } else if (entidade instanceof Pedido){
		        pedido = (Pedido) entidade;
				sql.append("select * from pedido as p join item_pedido as i on p.pedido_id = i.item_pedido_pedido_id ");
				sql.append("join cupom as c on i.item_pedido_id = c.cupom_item_id ");
				sql.append(" where pedido_cli_id=? ");
		        
		    }else if (entidade instanceof Cupom){
		    	
				cupom = (Cupom) entidade;
				sql.append("select * from ");
				sql.append(table);
				if(cupom.getId()==0)
					sql.append(" where cupom_numero like ? ");
				else
					sql.append(" where cupom_id = ? ");
		    	
		    }

		
		try {
			pst = connection.prepareStatement(sql.toString());
			
		    if(entidade instanceof ItemPedido){
				pst.setInt(1, item.getId());
				
		    } else if (entidade instanceof Pedido){
		    	pst.setInt(1, pedido.getCli().getId());
		        
		    }else if (entidade instanceof Cupom){
		    	if(entidade.getId()==0)
		    		pst.setString(1, cupom.getNumCupom());
		    	else
		    		pst.setInt(1, cupom.getId());
		    }
						
			resultset = pst.executeQuery();
			
			if (resultset.next()) {
				entidades = new ArrayList<EntidadeDominio>();
				
				do {
					
					cupom = new Cupom(resultset.getString("cupom_numero"), resultset.getDouble("cupom_valor"));
					cupom.setId(resultset.getInt("cupom_id"));
					item = new ItemPedido();
					item.setId(resultset.getInt("cupom_item_id"));
					item.setStatus("S");
					item.setCupom(cupom);
					entidades.add(item);


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
