package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dominio.Cartao;
import dominio.Cidade;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Estado;
import dominio.Pais;
import dominio.Pedido;
import negocio.IStrategy;

public class CliEnderecoDao extends AbstractDao {
	private Endereco endereco;
	private Cliente cliente;
    private List<EntidadeDominio> enderecos;
    private Estado estado;
    private Cidade cidade;
    private Pais pais;

	public CliEnderecoDao(Connection connection) {
		super(connection, "cliente_endereco","cli_end_id");
		// TODO Auto-generated constructor stub
	}

	public CliEnderecoDao(String table) {
		super("cliente_endereco","cli_end_id");
		// TODO Auto-generated constructor stub
	}

	public CliEnderecoDao() {

	}

	@Override
	public void salvar(EntidadeDominio entidade) {

		resultset = null;
		pst = null;
		sql.setLength(0);
		sb.setLength(0);

		conectarBanco();
		
	

		sql.append("insert into ");
		sql.append(table);
		sql.append("(cli_end_tipo, cli_end_tipo_logradouro, cli_end_logradouro, cli_end_numero");
		sql.append(",cli_end_bairro,cli_end_cep,cli_end_cidade,cli_end_uf,cli_end_pais,cli_end_observacao,cli_end_cli_id)");
		sql.append("values(?,?,?,?,?,?,?,?,?,?,?)");

		cliente = (Cliente) entidade;

		for (Endereco end : cliente.getEnderecos()) {

			try {
				pst = connection.prepareStatement(sql.toString());
				pst.setString(1, end.getTipoEndereco());
				pst.setString(2, end.getTipoLogradouro());
				pst.setString(3, end.getLogradouro());
				pst.setString(4, end.getNumero());

				pst.setString(5, end.getBairro());
				pst.setString(6, end.getCep());
				pst.setString(7, end.getCidade().getNome());
				pst.setString(8, end.getCidade().getEstado().getUf());
				pst.setString(9, end.getCidade().getEstado().getPais().getNome());
				pst.setString(10, end.getObservacoes());
				pst.setInt(11, cliente.getId());

				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			sql.append("SELECT * from cliente_endereco WHERE cli_end_cli_id =?");
			
		}else if(entidade instanceof Pedido) {
			sql.append("SELECT * from cliente_endereco WHERE cli_end_Id =?");
		}
	

		try {
	
			pst = connection.prepareStatement(sql.toString());
			if(entidade instanceof Cliente) {
				pst.setInt(1, entidade.getId());
			}else if(entidade instanceof Pedido) {
				pst.setInt(1, ((Pedido) entidade).getEndEntrega().getId());
			}
			resultset = pst.executeQuery();
			enderecos = new ArrayList<EntidadeDominio>();
			while (resultset.next()) {
				pais = new Pais(resultset.getString("cli_end_pais"));
				estado = new Estado(resultset.getString("cli_end_uf"), pais);
				cidade = new Cidade(resultset.getString("cli_end_cidade"), estado);
				

				endereco = new Endereco(resultset.getInt("cli_end_id"),resultset.getString("cli_end_tipo"), resultset.getString("cli_end_tipo_logradouro"), 
						resultset.getString("cli_end_logradouro"), resultset.getString("cli_end_numero"), resultset.getString("cli_end_bairro"),
						resultset.getString("cli_end_cep"), resultset.getString("cli_end_observacao"), cidade);


				enderecos.add(endereco);
				
			}
			return enderecos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
