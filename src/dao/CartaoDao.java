package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.BandeiraCart;
import dominio.Cartao;
import dominio.Cidade;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Estado;
import dominio.FormaPgto;
import dominio.Pais;

public class CartaoDao extends AbstractDao {
	private Cartao cartao;
	private Cliente cliente;
    private List<EntidadeDominio> cartoes;
    private BandeiraCart bandeira;
    private BandeiraDao daoBandeira;
    
    private BandeiraDao daoBand;
	
	public CartaoDao(Connection connection) {
		super(connection, "cliente_cartao","cli_cartao_id");

	}
	public CartaoDao() {
		super("cliente_cartao","cli_cartao_id");
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public void salvar(EntidadeDominio entidade) {
			
		resultset = null;
		pst = null;
		sql.setLength(0);
		sb.setLength(0);

		conectarBanco();
				
		sql.append("insert into ");
		sql.append(table);
		sql.append("(cli_cartao_bandeira_id,cli_cartao_numero,cli_cartao_validade,cli_cartao_cvv,cli_cartao_cli_id,cli_cartao_nome)");		
		sql.append("values(?,?,?,?,?,?)");


		daoBandeira = new BandeiraDao(connection);		
		

		if (entidade instanceof Cartao) {
			cartao = (Cartao) entidade;
			try {
				pst = connection.prepareStatement(sql.toString());
				pst.setInt(1, ((List<BandeiraCart>)(List<?>)daoBandeira.consultar(cartao.getBandeira())).get(0).getId());
				pst.setString(2, cartao.getNumCartao());
				pst.setString(3, cartao.getValidade());
				pst.setString(4, cartao.getCvv());
				pst.setInt(5, cartao.getCliente().getId());
				pst.setString(6, cartao.getNomeCartao());	
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else {
			cliente = (Cliente) entidade;
			for (Cartao cart : cliente.getCartoes()) {

				try {
					pst = connection.prepareStatement(sql.toString());
					pst.setInt(1, ((List<BandeiraCart>)(List<?>)daoBandeira.consultar(cart.getBandeira())).get(0).getId());
					pst.setString(2, cart.getNumCartao());
					pst.setString(3, cart.getValidade());
					pst.setString(4, cart.getCvv());
					pst.setInt(5, cliente.getId());
					pst.setString(6, cart.getNomeCartao());
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		pst = null;
		sql.setLength(0);
		conectarBanco();
		
		if(entidade instanceof Cliente) {					

			sql.append("SELECT * from cliente_cartao WHERE cli_cartao_cli_id =?");
		
		}else if (entidade instanceof Cartao) {
			if(entidade.getId() != 0)
				sql.append("SELECT * from cliente_cartao WHERE cli_cartao_id =?");
			else
				sql.append("SELECT * from cliente_cartao WHERE cli_cartao_numero like ?");
		}		

		try {

			pst = connection.prepareStatement(sql.toString());
			

			
			if(entidade.getId() != 0)
				pst.setInt(1, entidade.getId());
			else
				pst.setString(1, ((Cartao) entidade).getNumCartao());
				
			resultset = pst.executeQuery();
			cartoes = new ArrayList<EntidadeDominio>();
			new ArrayList<EntidadeDominio>();
			daoBand = new BandeiraDao();
			
			while (resultset.next()) {
				
				bandeira = new BandeiraCart();
				bandeira.setId(resultset.getInt("cli_cartao_bandeira_id"));
								
				for(EntidadeDominio ent : daoBand.consultar(bandeira)) {
					bandeira = (BandeiraCart)ent;
					
				}
				
				
				cartao = new Cartao(resultset.getInt("cli_cartao_id"),resultset.getString("cli_cartao_nome"), resultset.getString("cli_cartao_numero"),
						resultset.getString("cli_cartao_cvv"),resultset.getString("cli_cartao_validade"),
						bandeira);


					cartoes.add(cartao);
				
			}
			return cartoes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
