package controle.VH.Salvar;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controle.VH.AbstractViewHelper;
import dominio.Carrinho;
import dominio.Cartao;
import dominio.Cliente;
import dominio.Cupom;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Estoque;
import dominio.FormaPgto;
import dominio.ItemPedido;
import dominio.Pedido;
import util.Resultado;

public class SalvarPedidoVHold extends AbstractViewHelper{
	
	private Pedido pedido;
	private Cliente cli;
	private Endereco endEntrega;
	private Cartao cartaoPag;
	private Cupom cupom;
	private Carrinho car;
	HttpSession session;
	private String txtCupom;


	@SuppressWarnings("unchecked")
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		session = request.getSession();
		txtCupom = request.getParameter("txtCupom");
		car = (Carrinho) session.getAttribute("carrinho");
		endEntrega = (Endereco) session.getAttribute("enderecoEntrega");
		cartaoPag = (Cartao) session.getAttribute("cartaoPag");
		pedido = new Pedido((List<ItemPedido>) (List<?>)car.getItens(), cli, endEntrega);
		
		pedido.setFormasPgto(new ArrayList<FormaPgto>());
		
		if(cartaoPag != null && !cartaoPag.getNumCartao().equals("")){
			cartaoPag.setnValorParcela(pedido.getTotal());
			cartaoPag.setnQtdParcelas(1);
			pedido.getFormasPgto().add(cartaoPag);
		}
		
		if(!txtCupom.equals("") && txtCupom != null) {
			cupom = new Cupom(txtCupom);
			pedido.getFormasPgto().add(cupom);
		}
		pedido.setStatus("EM PROCESSAMENTO");
		return pedido;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
		
		
		if(resultado.getMsg().length() == 0) {
			rd = request.getRequestDispatcher("index.jsp");		
			
		}else {
			rd = request.getRequestDispatcher("frmConfirmacao.jsp");	
		}

		
		enviarFront(resultado, request, response);
		
	}

}
