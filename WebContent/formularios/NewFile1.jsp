<%@page import="org.jfree.data.xy.XYSeriesCollection"%>
<%@page import="org.jfree.data.xy.XYDataset"%>
<%@page import="org.jfree.data.xy.XYSeries"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="dominio.*, java.util.*"%>
<%@page import="util.Resultado"%>
<%@page import="java.io.*" %>
<%@page import="org.jfree.chart.*" %>
<%@page import="org.jfree.data.general.*" %>
<%@page import="org.jfree.chart.plot.*" %>
<%@page import="org.jfree.data.category.DefaultCategoryDataset"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LESS</title>
<link href="estilo.css" rel="stylesheet" type="text/css" />
</head>
</head>
<body>

	<div id="container">

		<div class="banneracima">

			<div align="center">
				<H2>E-COMMERCE DE LIVROS PARA PROGRAMADORES</H2>
			</div>


			<%
				Resultado result = (Resultado) request.getAttribute("resultado");
				Usuario usuario = (Usuario) session.getAttribute("usuario");
				Contador contador;
				List<Contador> contadores = new ArrayList<Contador>();

				
				if (usuario != null) {
			%>
	
				<%
					out.print(usuario.getPessoa().getNome());
				%>

			<button><a href="/Less/formularios/Pedido?txtId=<%=usuario.getPessoa().getId()%>&OPERACAO=CONSULTAR_PEDIDOS_CLI">Minhas compras</a></button>
			<button><a href="/Less/formularios/Cupons?txtId=<%=usuario.getPessoa().getId()%>&OPERACAO=CONSULTAR">Meus cupons</a></button>
			<button>Logout</button>
			<div align="right">
				<button><a href="/Less/formularios/Carrinho?txtId=<%=usuario.getPessoa().getId()%>&OPERACAO=CONSULTAR">Carrinho</a></button>
			</div>
			<%
				} else {
			%>
				<form name="frmLogin" action="Login" method="post">	
				
					<div class="caixaDeTextoLado">
						<div class="labelCaixaTexto">
							<label for="txtEmail">Email</label>					
						</div>
						<input type="text" id="txtEmail" name="txtEmail" size="40" /> 
					</div>
	
					<div class="caixaDeTextoLado">
						<div class="labelCaixaTexto">
							<label for="txtSenha">Senha</label>					
						</div>	
						<input type="text" id="txtSenha" name="txtSenha" size="15" /> 
					</div>
										
					<input type="submit" id="OPERACAO" name="OPERACAO" value="LOGIN" />
					<a href=frmCadCli.jsp>Cadastre-se</a>
					
				</form>

			<%
				}
			%>



		</div>
		<div class="menu">
			<ul>
				<li><a href="index.html">LÓGICA</a></li>
				<li><a href="java.jsp">JAVA</a></li>
				<li><a href="c.jsp">C/C++/C#</a></li>
				<li><a href="vb.jsp">VB</a></li>
			</ul>
		</div>
		<div class="containerCad">
	<%	
	 DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
    line_chart_dataset.addValue( 15 , "schools" , "1970" );
    line_chart_dataset.addValue( 30 , "schools" , "1980" );
    line_chart_dataset.addValue( 60 , "schools" , "1990" );
    line_chart_dataset.addValue( 120 , "schools" , "2000" );
    line_chart_dataset.addValue( 240 , "schools" , "2010" ); 
    line_chart_dataset.addValue( 300 , "schools" , "2014" );

    JFreeChart chart= ChartFactory.createLineChart(
       "Schools Vs Years","Year",
       "Schools Count",
       line_chart_dataset,PlotOrientation.VERTICAL,
       true,true,false);

            
     
		response.setContentType("image/JPEG");
		OutputStream sa = response.getOutputStream();
		ChartUtilities.writeChartAsJPEG(sa, chart, 600, 600);
%>

		</div>
		<div class="botoesCad">

			<input type='button' value='VOLTAR' onclick='history.go(-1)' />
		</div>
		</form>
		<div class="rodape" />


	</div>
	<%

		if (result != null) {
			if (result.getMsg() != null && !result.getMsg().equals("")) {
				out.println("<script>alert('" + result.getMsg() + "')</script>");

			}
		}
	%>
</body>
</html>