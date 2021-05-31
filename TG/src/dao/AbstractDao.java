package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import dominio.EntidadeDominio;
import util.Conexao;

public abstract class AbstractDao implements IDao {
	
	protected Connection connection = null;
	protected StringBuilder sql = new StringBuilder();
	protected StringBuilder sb = new StringBuilder();
	protected String table;
	protected String chavePrimaria;
    protected boolean ctrlTransaction = true;
    protected ResultSet resultset;
    protected PreparedStatement pst;
    protected SimpleDateFormat formatoDataNormal = new SimpleDateFormat("yyyy-MM-dd"); 
    protected List<EntidadeDominio> entidades;
    
    protected HashMap<String, String> SqlMapString;
    protected HashMap<String, Integer> SqlMapInt;
    protected HashMap<String, Double> SqlMapDouble;
    
    public AbstractDao(Connection connection,String table,String chavePrimaria) {
    	this.connection = connection;
    	this.table = table;
    	this.chavePrimaria = chavePrimaria;
    }
    
 
    public AbstractDao(String table,String chavePrimaria) {
      	this.table = table;
    	this.chavePrimaria = chavePrimaria;
    	
    }
    
    public AbstractDao(String table) {
      	this.table = table;
    	
    }
    
    public AbstractDao() {
    	
    }
    
    protected void openConnection() throws ClassNotFoundException, SQLException {
		this.connection = Conexao.getConnectionPostgres();
    }
    public void excluir(EntidadeDominio entidade) {
    	
		sql.setLength(0);
		sb.setLength(0);
		pst = null;
		conectarBanco();

		sql.append("DELETE FROM ");
		sql.append(table);
		sql.append(" WHERE ");
		sql.append(chavePrimaria);
		sql.append(" = ");
		sql.append("?");
		try{
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, entidade.getId());
			pst.executeUpdate();

		}catch(SQLException e){
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			    sb.append("Erro ao gravar. Tente novamente");
            }
	
    }
    
    protected void conectarBanco() {
		if (connection == null) {
			
			try {
				openConnection();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
    }
    
    protected void encerrarConexaoBanco() {
		try {
			pst.close();
			connection.close();
			connection = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    protected boolean verificarVazioOuNulo(String item) {
    	return (item == null || item.isEmpty());

    }


}
