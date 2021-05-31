package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static String driver;
	private static String url;
	private static String user;
	private static String password;

	public static Connection getConnectionPostgres() throws ClassNotFoundException, SQLException {
		url = "jdbc:postgresql://localhost:5432/postgres";
		user = "postgres";
		password = "fatec123";
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection(url,user,password);
		
		return conn;		

	}

	public static Connection getConnectionH2() throws ClassNotFoundException, SQLException {
		driver = "org.h2.Driver";
		url = "jdbc:h2:~/test";	
		user = "sa";
		password = "";
		return getConnection();

	}

	static Connection getConnection() throws ClassNotFoundException, SQLException {

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, user, password);

		return conn;

	}


}
