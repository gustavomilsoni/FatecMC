package controle.VH.Alterar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.VH.IViewHelper;
import dominio.BandeiraCart;
import dominio.Cartao;
import dominio.Cidade;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Estado;
import dominio.Pais;
import dominio.Usuario;
import util.Resultado;

public class AlterarClienteVH implements IViewHelper {

	private List<Endereco> enderecos;
	private List<Cartao> cartoes;
	private Cliente cliente;
	private Usuario user;
	private int id;

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		id = Integer.parseInt(request.getParameter("txtId"));

		String nome = request.getParameter("txtNome");
		String genero = request.getParameter("cmbGenero");
		String cpf = request.getParameter("txtCpf");
		String dataNasc = request.getParameter("txtDataNasc");
		String tipoFone = request.getParameter("cmbTipoTel");
		String telefone = request.getParameter("txtTelefone");
		String email = request.getParameter("txtEmail");
		String senha = request.getParameter("txtSenha");
		String confirmacaoSenha = request.getParameter("txtConfirmacaoSenha");
		// String[] testes = request.getParameterValues("teste");
		String tipoLograd = request.getParameter("cmbTipoLogradouro");
		String logradouro = request.getParameter("txtLogradouro");
		String numLograd = request.getParameter("txtNumero");
		String bairro = request.getParameter("txtBairro");
		String cep = request.getParameter("txtCep");
		String observacao = request.getParameter("txtObservacao");
		String bandeira = request.getParameter("cmbBandeira");
		String numCart = request.getParameter("txtNumCartao");
		String validade = request.getParameter("txtValidade");
		String cvv = request.getParameter("txtCvv");
		String tipoEndereco = request.getParameter("cmbTipoEndereco");
		String nomeCartao = request.getParameter("txtNomeCart");

		Pais pais = new Pais(request.getParameter("cmbPais"));
		Estado uf = new Estado(request.getParameter("cmbUf"), pais);
		Cidade cidade = new Cidade(request.getParameter("txtCidade"), uf);

		BandeiraCart bandeiraCart = new BandeiraCart(bandeira);

		Endereco endereco = new Endereco(tipoEndereco, tipoLograd, logradouro, numLograd, bairro, cep, observacao,
				cidade);
		Cartao cartao = new Cartao(nomeCartao, numCart, cvv, validade, bandeiraCart);

		cartoes = new ArrayList<Cartao>();
		cartoes.add(cartao);
		
		enderecos = new ArrayList<Endereco>();
		enderecos.add(endereco);

		cliente = new Cliente(id, telefone, tipoFone, enderecos, nome, cpf, cartoes, genero, dataNasc, email);
		


		user = new Usuario(cliente, senha, confirmacaoSenha, false);


		cliente.setUsuario(user);
		return cliente;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("resultado", resultado);
		RequestDispatcher rd = null;
		if (cliente.getUsuario().isAdm()) {
			if (resultado.getMsg() != null && !resultado.getMsg().equals("")) {

			} else {
				rd = request.getRequestDispatcher("frmAdmInicial.jsp");
			}
		} else {
			if (resultado.getMsg() != null && !resultado.getMsg().equals("")) {
				rd = request.getRequestDispatcher("frmCadCli.jsp");
			} else {
				rd = request.getRequestDispatcher("index.jsp");
			}

		}

		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
