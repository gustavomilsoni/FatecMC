package controle.VH.Consultar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Formatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.VH.AbstractViewHelper;
import dominio.EntidadeDominio;
import dominio.Grafico;
import dominio.Pedido;
import util.Resultado;

public class ConsultarGraficoVH extends AbstractViewHelper {
	
	private Pedido pedido;
	private Grafico grafico;
	private String dataInicial;
	private String dataFinal;
	private DateFormat fmt;
	private java.sql.Date data;
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		pedido = new Pedido();
		pedido.setStatus("Grafico");
		grafico = new Grafico();
		dataInicial = request.getParameter("txtDataInicial");
		dataFinal = request.getParameter("txtDataFinal");
		

		fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			data = new java.sql.Date(fmt.parse(dataInicial).getTime());
			grafico.setDataInicial(data);
			
			data = new java.sql.Date(fmt.parse(dataFinal).getTime());
			grafico.setDatafinal(data);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		pedido.setGrafico(grafico);
		//Date data_formatada = new SimpleDateFormat(formato).parse(texto);
		return pedido;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {


		rd = request.getRequestDispatcher("frmPesquisa.jsp");

		enviarFront(resultado, request, response);
		
	}

}
