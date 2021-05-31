package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dominio.Carrinho;
import dominio.Cartao;
import dominio.Cidade;
import dominio.Cliente;
import dominio.Contador;
import dominio.Cupom;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Estado;
import dominio.FormaPgto;
import dominio.Grafico;
import dominio.ItemPedido;
import dominio.Livro;
import dominio.Pais;
import dominio.Pedido;
import dominio.Usuario;
import negocio.IStrategy;
import util.Conexao;

public class PedidoDao extends AbstractDao {
	
	private Pedido pedido;
	private Cliente cliente;
	private List<EntidadeDominio> clientes;
	private List<EntidadeDominio> pedidos;
	private List<EntidadeDominio> carrinhos;
	private List<EntidadeDominio> contadores;
	private List<ItemPedido> itensPedido;
	private double total;
	private Endereco endEntrega;
	private Carrinho carrinho;
	private List<EntidadeDominio> enderecos;
	private List<FormaPgto> formasPgto;
	private Cupom cupom;
	private ItemPedido item;

	//DAOs utilizados
	
	private ItemPedidoDao daoItemPedido;
	private CliEnderecoDao daoEndereco;
	private ClienteDao daoCli;
	private CarrinhoDao daoCarrinho;
	private CupomDao daoCupom;
	private ItemPgtoCartaoDao daoItemPgtoCartao;
	private itemPgtoCupomDao daoItemPgtoCupom;
	private CartaoDao daoCartao;

	//----------------------------------------------
	
	public PedidoDao() {
		super("pedido", "pedido_id");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void salvar(EntidadeDominio entidade) {
   		resultset = null;
		pst = null;
		sql.setLength(0);
		sb.setLength(0);

		conectarBanco();
		
		pedido = (Pedido) entidade;

		carrinho = new Carrinho();

		daoCarrinho = new CarrinhoDao(connection);
		carrinhos = daoCarrinho.consultar(pedido.getCli().getCarrinho());
		daoCarrinho.excluir(carrinhos.get(0));
		
		sql.append("insert into ");
		sql.append(table);
		sql.append("(pedido_cli_id, pedido_total, pedido_status,pedido_cliente_end_id)");
		sql.append("values(?,?,?,?)");
		
		try {
			connection.setAutoCommit(false);
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, pedido.getCli().getId());
			pst.setDouble(2, getTotalPedido(pedido));
			pst.setString(3, "EM PROCESSAMENTO");
			pst.setInt(4, pedido.getEndEntrega().getId());
			pst.executeUpdate();
			
			resultset = pst.getGeneratedKeys();

			if (resultset.next()) {
				pedido.setId(resultset.getInt(1));

			}
			
			/*for (FormaPgto forma : pedido.getFormasPgto()) {
				if (forma instanceof Cartao) {
					if(forma.getId()==0) {
						daoCartao = new CartaoDao();
						daoCartao.salvar(forma);
						pedido.getFormasPgto().add(((List<FormaPgto>)(List<?>)daoCartao.consultar(forma)).get(0));
						pedido.getFormasPgto().remove(forma);
						break;
					}
				}
			}
			*/
			daoItemPedido = new ItemPedidoDao(connection);
			
			daoItemPedido.salvar(pedido);
			
			daoItemPgtoCartao = new ItemPgtoCartaoDao(connection);
			daoItemPgtoCartao.salvar(pedido);
			
			daoItemPgtoCupom = new itemPgtoCupomDao(connection);
			daoItemPgtoCupom.salvar(pedido);
	
			
			connection.commit();
			

		} catch (SQLException e2) {
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

	private double getTotalPedido(Pedido pedido) {

		total = 0;

		for (ItemPedido item : pedido.getItens()) {
			total += (item.getQuantidade() * item.getLivro().getValorVenda());
		}

		return total;

	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		pst = null;
		sql.setLength(0);
		
		pedido = (Pedido) entidade;
		
		conectarBanco();


		try {

		connection.setAutoCommit(false);
			
		sql.append("update ");
		sql.append(table);
		sql.append(" set pedido_status=?, pedido_total=?, pedido_cliente_end_id=?");
		sql.append(" where pedido_id=? ");		
		
		pst = connection.prepareStatement(sql.toString());
		pst.setString(1, pedido.getStatus());
		pst.setDouble(2, pedido.getTotal());
		pst.setInt(3, pedido.getEndEntrega().getId());
		pst.setInt(4, pedido.getId());
		
		
		pst.executeUpdate();
		
		daoItemPedido = new ItemPedidoDao(connection);
		
		daoItemPedido.alterar(pedido);
		

		
		connection.commit();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	@SuppressWarnings("unchecked")
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		pst = null;
		sql.setLength(0);
		conectarBanco();

		Pedido filtroPedido = (Pedido) entidade;
		
		if(filtroPedido.getStatus() != null  && filtroPedido.getStatus().equals("EM TROCA")) {
			sql.append("select * from pedido ");
			sql.append(" where pedido_status like ?");
			
			
		}else if(filtroPedido.getStatus() != null  && filtroPedido.getStatus().equals("Grafico")) {
			
			sql.append("SELECT pedido_status,SUM(CASE WHEN (EXTRACT(MONTH FROM pedido_data) = 1) THEN 1 ELSE 0 END) Qtd_Jan,");
			sql.append("SUM(CASE WHEN (EXTRACT(MONTH FROM pedido_data) = 2) THEN 1 ELSE 0 END) Qtd_Fev,");
			sql.append("SUM(CASE WHEN (EXTRACT(MONTH FROM pedido_data) = 3) THEN 1 ELSE 0 END) Qtd_Mar,");
			sql.append("SUM(CASE WHEN (EXTRACT(MONTH FROM pedido_data) = 4) THEN 1 ELSE 0 END) Qtd_Abr,");
			sql.append("SUM(CASE WHEN (EXTRACT(MONTH FROM pedido_data) = 5) THEN 1 ELSE 0 END) Qtd_Mai,");
			sql.append("SUM(CASE WHEN (EXTRACT(MONTH FROM pedido_data) = 6) THEN 1 ELSE 0 END) Qtd_Jun,");
			sql.append("SUM(CASE WHEN (EXTRACT(MONTH FROM pedido_data) = 7) THEN 1 ELSE 0 END) Qtd_Jul,");
			sql.append("SUM(CASE WHEN (EXTRACT(MONTH FROM pedido_data) = 8) THEN 1 ELSE 0 END) Qtd_Ago,");
			sql.append("SUM(CASE WHEN (EXTRACT(MONTH FROM pedido_data) = 9) THEN 1 ELSE 0 END) Qtd_Set,");
			sql.append("SUM(CASE WHEN (EXTRACT(MONTH FROM pedido_data) = 10) THEN 1 ELSE 0 END) Qtd_Out,");
			sql.append("SUM(CASE WHEN (EXTRACT(MONTH FROM pedido_data) = 11) THEN 1 ELSE 0 END) Qtd_Nov,");
			sql.append("SUM(CASE WHEN (EXTRACT(MONTH FROM pedido_data) = 12) THEN 1 ELSE 0 END) Qtd_Dez");
			
			sql.append(" FROM pedido");
			sql.append(" where pedido_data BETWEEN ? and ? and (pedido_status=? or pedido_status=?)");
			sql.append(" GROUP BY pedido_status");
		
			
		}
		else if (filtroPedido.getStatus() != null  && filtroPedido.getStatus().equals("TROCA AUTORIZADA")) {
			sql.append("select * from pedido as p join item_pedido as i on p.pedido_id = i.item_pedido_pedido_id ");
			sql.append("join cupom as c on i.item_pedido_id = c.cupom_item_id ");
			sql.append(" where pedido_cli_id=? ");;
			
		}else if (filtroPedido.getId() != 0) {
			sql.append("select * from pedido ");
			sql.append("where pedido_id =? ");
			
		
		} else if (filtroPedido.getCli().getId() != 0) {
			sql.append("select * from pedido ");
			sql.append("where pedido_cli_id =? ");		
			
		}
		try {

			pst = connection.prepareStatement(sql.toString());
			
			if(filtroPedido.getStatus() != null  && filtroPedido.getStatus().equals("EM TROCA")) {
				pst.setString(1, filtroPedido.getStatus());
				
			}else if (filtroPedido.getStatus() != null  && filtroPedido.getStatus().equals("TROCA AUTORIZADA")) {
				pst.setInt(1, filtroPedido.getCli().getId());
			}else if(filtroPedido.getStatus() != null  && filtroPedido.getStatus().equals("Grafico")) {
				pst.setDate(1, filtroPedido.getGrafico().getDataInicial());
				pst.setDate(2, filtroPedido.getGrafico().getDatafinal());
				pst.setString(3, "EM PROCESSAMENTO");
				pst.setString(4, "TROCA AUTORIZADA");
			}else if (filtroPedido.getId() != 0) {
				pst.setInt(1, filtroPedido.getId());
			} else if (filtroPedido.getCli() != null &&  filtroPedido.getCli().getId() != 0) {
				pst.setInt(1, filtroPedido.getCli().getId());
			}

			resultset = pst.executeQuery();
			
			if(filtroPedido.getStatus() != null  && filtroPedido.getStatus().equals("Grafico")) {
				
				
				if (resultset.next()) {
					contadores = new ArrayList<EntidadeDominio>();	
					Contador cont;
					do {
						

						cont = new Contador();
						cont.setStatus(resultset.getString("pedido_status"));
						cont.setMes("Jan");
						cont.setQuantidade(resultset.getInt("qtd_jan"));
						contadores.add(cont);
						
						cont = new Contador();
						cont.setStatus(resultset.getString("pedido_status"));
						cont.setMes("Fev");
						cont.setQuantidade(resultset.getInt("qtd_fev"));
						contadores.add(cont);
						
						cont = new Contador();
						cont.setStatus(resultset.getString("pedido_status"));
						cont.setMes("Mar");
						cont.setQuantidade(resultset.getInt("qtd_mar"));
						contadores.add(cont);
						
						cont = new Contador();
						cont.setStatus(resultset.getString("pedido_status"));
						cont.setMes("Abr");
						cont.setQuantidade(resultset.getInt("qtd_abr"));
						contadores.add(cont);
						
						cont = new Contador();
						cont.setStatus(resultset.getString("pedido_status"));
						cont.setMes("Mai");
						cont.setQuantidade(resultset.getInt("qtd_mai"));
						contadores.add(cont);
						
						cont = new Contador();
						cont.setStatus(resultset.getString("pedido_status"));
						cont.setMes("Jun");
						cont.setQuantidade(resultset.getInt("qtd_jun"));
						contadores.add(cont);
						
						cont = new Contador();
						cont.setStatus(resultset.getString("pedido_status"));
						cont.setMes("Jul");
						cont.setQuantidade(resultset.getInt("qtd_jul"));
						contadores.add(cont);
						
						cont = new Contador();
						cont.setStatus(resultset.getString("pedido_status"));
						cont.setMes("Ago");
						cont.setQuantidade(resultset.getInt("qtd_ago"));
						contadores.add(cont);
						
						cont = new Contador();
						cont.setStatus(resultset.getString("pedido_status"));
						cont.setMes("Set");
						cont.setQuantidade(resultset.getInt("qtd_set"));
						contadores.add(cont);
						
						cont = new Contador();
						cont.setStatus(resultset.getString("pedido_status"));
						cont.setMes("Out");
						cont.setQuantidade(resultset.getInt("qtd_out"));
						contadores.add(cont);
						
						cont = new Contador();
						cont.setStatus(resultset.getString("pedido_status"));
						cont.setMes("Nov");
						cont.setQuantidade(resultset.getInt("qtd_nov"));
						contadores.add(cont);
						
						cont = new Contador();
						cont.setStatus(resultset.getString("pedido_status"));
						cont.setMes("Dez");
						cont.setQuantidade(resultset.getInt("qtd_dez"));
						contadores.add(cont);

					}while (resultset.next());
				} 
				


				encerrarConexaoBanco();
		
				return contadores;
				
			}
			pedidos = new ArrayList<EntidadeDominio>();
			daoEndereco = new CliEnderecoDao(connection);
			daoItemPedido = new ItemPedidoDao(connection);
			daoCli = new ClienteDao(connection);
			daoCupom = new CupomDao(connection);
			daoItemPgtoCartao = new ItemPgtoCartaoDao(connection);
			daoItemPgtoCupom = new itemPgtoCupomDao(connection);
			
			if (resultset.next()) {
				do {
					if(filtroPedido.getCli() == null || filtroPedido.getCli().getId() == 0) {
						cliente = new Cliente();
						cliente.setId(resultset.getInt("pedido_cli_id"));
						clientes = daoCli.consultar(cliente);
						cliente = (Cliente)clientes.get(0);
						
					}else {
						cliente = filtroPedido.getCli();
					}


				
								


				
					
					itensPedido = new ArrayList<ItemPedido>();
					formasPgto = new ArrayList<FormaPgto>();
					pedido = new Pedido();
					pedido.setId(resultset.getInt("pedido_id"));
					pedido.setCli(cliente);
					
					endEntrega = new Endereco();
					endEntrega.setId(resultset.getInt("pedido_cliente_end_id"));
					pedido.setEndEntrega(endEntrega);
					enderecos = daoEndereco.consultar(pedido);
					endEntrega = (Endereco)enderecos.get(0);
					entidades = daoItemPgtoCartao.consultar(pedido);
					for(Cartao cart : (List<Cartao>)(List<?>) entidades) {
							formasPgto.add(cart);
					}
					
					entidades = daoItemPgtoCupom.consultar(pedido);
					for(Cupom cup : (List<Cupom>)(List<?>) entidades) {
						if(cup.getnValorParcela() > 0.0)
							formasPgto.add(cup);
					}
						
                    pedido.setFormasPgto(formasPgto);  
					pedido.setEndEntrega(endEntrega);
					pedido.setStatus(resultset.getString("pedido_status"));
					

					for(EntidadeDominio ent : daoItemPedido.consultar(pedido)) {
						item = (ItemPedido)ent;
						
						//if(ent.getStatus() != null && ent.getStatus().equals("S")){
							//cupom = (Cupom)(daoCupom.consultar(ent)).get(0);
							//item.setCupom(cupom);
						//}
						itensPedido.add(item);
					}
					
					pedido.setItens(itensPedido);					
					pedidos.add(pedido);
					
				} while (resultset.next());
			} else {
				return null;
			}
			encerrarConexaoBanco();
			return pedidos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}


	}

}
