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
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
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

<canvas id="myChart"></canvas>



<script>
	
	var ctx = document.getElementById('myChart').getContext('2d');
	var chart = new Chart(ctx, {
	    type: 'line',
	    data: {
	        labels: ['2000', '2001', '2002', '2003', '2004', '2005'],
	        datasets: [{
	                label: 'Crecimento Populacional',
	                data: [173448346, 175885229, 178276128, 180619108, 182911487, 185150806],
	                backgroundColor: "rgba(255, 34, 0, 0.3)",
	                borderColor: "#0000ff"
	            },
	            {
	                label: 'Exemplo de Gráfico Comparativo',
	                data: [173448346, 185150806, 175885229, 182911487, 178276128, 180619108],
	                backgroundColor: "rgba(0, 255, 0, 0.3)",
	                borderColor: "#002200"
	            }
	        ]
	    }
	});
</script>
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
			
			result = null;
			request.setAttribute("resultado", result);
		}
	%>
</body>
</html>