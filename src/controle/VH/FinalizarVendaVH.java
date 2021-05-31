package controle.VH;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CupomDao;
import dominio.Carrinho;
import dominio.Cartao;
import dominio.Cliente;
import dominio.Cupom;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.FormaPgto;
import dominio.ItemCarrinho;
import dominio.ItemPedido;
import dominio.Pedido;
import dominio.Usuario;
import util.Resultado;

public class FinalizarVendaVH extends AbstractViewHelper{
	
	private Pedido pedido;
	private Cliente cli;
	private Endereco endEntrega;
	private Cartao cartaoPag;
	private Cupom cupom;
	private ItemPedido itemPedido;
	private Carrinho car;
	HttpSession session;
	private String txtCupom;
	private Usuario usuario;
	private CupomDao daoCupom;
	private double ValorPagamento;
	private List<EntidadeDominio>entidades;

	@SuppressWarnings("unchecked")
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		session = request.getSession();
		txtCupom = request.getParameter("txtCupom");
		usuario = (Usuario) session.getAttribute("usuario");
		ValorPagamento = Double.parseDouble(request.getParameter("txtValorPago"));
		cli = (Cliente)usuario.getPessoa();
		car = (Carrinho) session.getAttribute("carrinho");
		endEntrega = (Endereco) session.getAttribute("enderecoEntrega");
		cartaoPag = (Cartao) session.getAttribute("cartaoPag");
		pedido = new Pedido(new ArrayList<ItemPedido>(), cli, endEntrega);
		pedido.setValorPago(ValorPagamento);
		
		if(session.getAttribute("valorPagoAteMomento") != null)
			pedido.setValorPagoTotal(Double.parseDouble(session.getAttribute("valorPagoAteMomento").toString()));

		
		
		
		for(ItemCarrinho i : car.getItens()) {
			
			itemPedido = new ItemPedido(i.getQuantidade(), i.getLivro());
			pedido.getItens().add(itemPedido);
			
			
		}
		
		pedido.setFormasPgto(new ArrayList<FormaPgto>());
		
		if(cartaoPag != null && !cartaoPag.getNumCartao().equals("")){
			cartaoPag.setnValorParcela(pedido.getTotal());
			cartaoPag.setnQtdParcelas(1);
			pedido.getFormasPgto().add(cartaoPag);
		}
		
		if(!txtCupom.equals("") && txtCupom != null) {
			cupom = new Cupom(txtCupom);
			daoCupom = new CupomDao();
			itemPedido = ((List<ItemPedido>)(List<?>)daoCupom.consultar(cupom)).get(0);
			if(itemPedido == null)
				cupom = new Cupom(txtCupom);
			else
				cupom = itemPedido.getCupom();
			pedido.getFormasPgto().add(cupom);
		}
		pedido.setStatus("EM PROCESSAMENTO");
		return pedido;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
		session = request.getSession();
		entidades = resultado.getEntidades();
		

		
		if(resultado.getMsg().length() == 0) { 
			rd = request.getRequestDispatcher("index.jsp");		
			resultado.setMsg("Venda realizada com sucesso");
		}
		else {
			pedido = (Pedido) entidades.get(0);
			if(session.getAttribute("valorPagoAteMomento") != null)
				session.setAttribute("valorPagoAteMomento", Double.parseDouble(session.getAttribute("valorPagoAteMomento").toString()) + pedido.getValorPago());
			else
				session.setAttribute("valorPagoAteMomento", pedido.getValorPago());
			
			rd = request.getRequestDispatcher("frmCartaoPagamento.jsp");
		}
		

		
		
		enviarFront(resultado, request, response);
		

			
	}

}
