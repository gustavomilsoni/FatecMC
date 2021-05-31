package dominio;

import java.text.DecimalFormat;

import java.util.List;

public class Pedido extends EntidadeDominio {
	private List<ItemPedido> itens;
	private Cliente cli;
	private Endereco endEntrega;
	private List<FormaPgto> formasPgto;
	private Grafico grafico;
	private Double valorPago = 0.0;
	private Double valorPagoTotal = 0.0;
	private DecimalFormat df;
	
	public Pedido(List<ItemPedido> itens, Cliente cli, Endereco endEntrega) {
		this.itens = itens;
		this.cli = cli;
		this.endEntrega = endEntrega;
	}
		

	
	

	public Double getValorPagoTotal() {
		return valorPagoTotal;
	}





	public void setValorPagoTotal(Double valorPagoTotal) {
		this.valorPagoTotal = valorPagoTotal;
	}





	public Grafico getGrafico() {
		return grafico;
	}





	public Double getValorPago() {
		return valorPago;
	}





	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}





	public void setGrafico(Grafico grafico) {
		this.grafico = grafico;
	}





	public List<FormaPgto> getFormasPgto() {
		return formasPgto;
	}



	public void setFormasPgto(List<FormaPgto> formasPgto) {
		this.formasPgto = formasPgto;
	}



	public Pedido() {
		
	}

	public Endereco getEndEntrega() {
		return endEntrega;
	}



	public void setEndEntrega(Endereco endEntrega) {
		this.endEntrega = endEntrega;
	}


	public Cliente getCli() {
		return cli;
	}


	public void setCli(Cliente cli) {
		this.cli = cli;
	}


	public List<ItemPedido> getItens() {
		return itens;
	}
	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
	

	public double getTotal() {
		double total = 0;
		for(ItemPedido item: itens) {
			total += item.getLivro().getValorVenda() * item.getQuantidade();
		}

		df = new DecimalFormat("###,##0.00");	
		String string = df.format(total);		
	    String[] part = string.split("[,]");
	    String string2 = part[0]+"."+part[1];
	    
		total = Double.parseDouble(string2);
		return total;
	}
	
}
