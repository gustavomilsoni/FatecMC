package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.Conexao;

public class TesteConexao {

	public static void main(String[] args) {
		try {
			Connection cx = Conexao.getConnectionPostgres();

			if (cx == null) {
				System.out.println("CONEXÃO NÃO ESTABELECIDA");
			} else {
				//PreparedStatement pst = null;

				//pst = cx.prepareStatement("INSERT INTO cliente (cli_nome)values ('teste')");

				//pst.executeUpdate();
				System.out.println("CONEXÃO ESTABELECIDA");
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
