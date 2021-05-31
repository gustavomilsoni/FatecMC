package dao;

import java.sql.SQLException;
import java.util.List;

import dominio.EntidadeDominio;

public class DaoGenerico extends AbstractDao {
	
	private String nomeClasse;
	
	private String nomeTabela;

	@Override
	public void salvar(EntidadeDominio entidade) {
		
		resultset = null;
		pst = null;
		sql.setLength(0);
		sb.setLength(0);
		
		nomeClasse = entidade.getClass().getSimpleName();
		
		nomeTabela = nomeClasse.substring(0,1).toLowerCase(); // esse (0,1) significa a partir do elemento (0) pegue até o primeiro elemento
															  // ex: abcde ele retorna "a" se fosse (2,4) retorna "cd"
		for(int i = 1;i< nomeClasse.length();i++) {
			if(Character.isUpperCase(nomeClasse.charAt(i))) {
				nomeTabela += "_" + nomeClasse.substring(i,i+1).toLowerCase();
			}
			
		}
		
		


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
		
		
		
		sql.append("insert into ");
		sql.append(nomeClasse);
		sql.append("(cli_cartao_bandeira,cli_cartao_numero,cli_cartao_validade,cli_cartao_cvv,cli_cartao_cli_id,cli_cartao_nome)");		
		sql.append("values(?,?,?,?,?,?)");
		
		
		
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
