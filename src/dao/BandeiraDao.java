package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.BandeiraCart;

import dominio.EntidadeDominio;


public class BandeiraDao extends AbstractDao {
		
	private BandeiraCart bandeira;
	private List<EntidadeDominio> bandeiras;
	
    public BandeiraDao() {
        super("bandeira", "bandeira_id");

    }
    
    public BandeiraDao(Connection connection) {
        super(connection,"bandeira", "bandeira_id");

    }
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		pst = null;
		sql.setLength(0);
		bandeira = (BandeiraCart) entidade;
		conectarBanco();
		if(bandeira.getId()>0)
			sql.append("SELECT * from bandeira WHERE bandeira_id =?");
		else {
			sql.append("SELECT * from bandeira WHERE bandeira_nome = upper(?)");
		}

		try {

			pst = connection.prepareStatement(sql.toString());
			if(bandeira.getId()>0)
				pst.setInt(1, bandeira.getId());
			else {
				pst.setString(1, bandeira.getNome());
			}
			resultset = pst.executeQuery();
			bandeiras = new ArrayList<EntidadeDominio>();

			while (resultset.next()) {

				
				bandeira = new BandeiraCart(resultset.getString("bandeira_nome"));
				bandeira.setId(resultset.getInt("bandeira_id"));


				bandeiras.add(bandeira);
				
			}
			return bandeiras;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
