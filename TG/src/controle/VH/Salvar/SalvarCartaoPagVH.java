package controle.VH.Salvar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.VH.AbstractViewHelper;
import dao.CartaoDao;
import dominio.BandeiraCart;
import dominio.Cartao;
import dominio.Cliente;
import dominio.EntidadeDominio;
import dominio.Usuario;
import util.Resultado;

public class SalvarCartaoPagVH extends AbstractViewHelper {

	private Cartao cartao;
	private BandeiraCart bandeira;
	private String titularCart;
	private String cvv;
	private String numCart;
	private String validade;
	private String bandeiraCart;
	private Usuario usuario;
	private Cliente cliente;
	private CartaoDao daoCartao;
	
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		session = request.getSession();
		titularCart = request.getParameter("txtNomeCart");
		cvv = request.getParameter("txtCvv");
		numCart = request.getParameter("txtNumCartao");
		validade = request.getParameter("txtValidade");
		bandeiraCart = request.getParameter("cmbBandeira");
		
		bandeira = new BandeiraCart(bandeiraCart);
		cartao = new Cartao(titularCart, numCart, cvv, validade, bandeira);
		usuario = (Usuario) session.getAttribute("usuario");
		cliente = (Cliente)usuario.getPessoa();
		cartao.setCliente(cliente);


		session.setAttribute("cartaoPag", cartao);
		
		return cartao;


	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
		session = request.getSession();
		cartao = (Cartao) session.getAttribute("cartaoPag");
		daoCartao = new CartaoDao();
		cartao = (Cartao) (daoCartao.consultar(cartao)).get(0);
		session.setAttribute("cartaoPag", cartao);
		
		if(session.getAttribute("usuario") == null) {
			usuario = (Usuario) session.getAttribute("usuario");
			Cliente cliente = (Cliente)usuario.getPessoa();
			cliente.getCartoes().add(cartao);
			usuario.setPessoa(cliente);
			session.setAttribute("usuario", usuario);
		}
		
		
		rd = request.getRequestDispatcher("frmConfirmacao.jsp");		
		
		
		
		enviarFront(resultado, request, response);
		
	}

}
