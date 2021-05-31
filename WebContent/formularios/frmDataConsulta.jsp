<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="dominio.*, java.util.*"%>
<%@page import="util.Resultado"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LIVROS</title>
<link href="estilo.css" rel="stylesheet" type="text/css" />
<script src="script.js"></script>
</head>
</head>
<body>

	<div id="container">


		<div class="banneracima">

			<div align="center">
				<H2>E-COMMERCE DE LIVROS PARA PROGRAMADORES</H2>
			</div>


			<%

	
				List<Pedido> pedidos = (ArrayList<Pedido>) session.getAttribute("pedidos");
			
				Usuario usuario = (Usuario) session.getAttribute("usuario");


				if (usuario != null) {
			%>

			<%
				out.print(usuario.getPessoa().getNome());
			%>

			<button><a href="/Less/formularios/Pedido?txtId=<%=usuario.getPessoa().getId()%>&OPERACAO=CONSULTAR_PEDIDOS_CLI">Minhas compras</a></button>
			<button>Logout</button>
			<div align="right">
				<button>
					<a
						href="/Less/formularios/Carrinho?txtId=<%=usuario.getPessoa().getId()%>&OPERACAO=CONSULTAR">Carrinho</a>
				</button>
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


			<div class="vitrineCad" style="overflow: auto">

				<form name="frmPedidos" action="frmConfirmacao.jsp" method="post">

					<br />
					<div align="center">
						<h2>Últimas vendas</h2>
					</div>
					
					<br />
					
					
					<TABLE class='minhaTabela' BORDER="1" WIDTH="100%" CELLPADDING="2" CELLSPACING="1">

						<thead>
							<TR>
								<TH COLSPAN="3"><H7>Vendas</H7></TH>
							</TR>



							<TR>
								<TH>Cliente</TH>
								<TH>Total</TH>
								<TH>Status</TH>
							</TR>
						</thead>
						<tbody id="lstItem">
							<%
								StringBuilder sbRegistro = new StringBuilder();
								StringBuilder sbLink = new StringBuilder();
	
								for (int i = 0; i < pedidos.size(); i++) {
									Pedido p = pedidos.get(i);
									sbRegistro.setLength(0);
									sbLink.setLength(0);
	
									//	<a href="nome-do-lugar-a-ser-levado">descrição</a>
	
									sbRegistro.append("<TR ALIGN='CENTER'>");
	
									sbLink.append("<a href=frmDetalhePedido.jsp?");
									sbLink.append("txtId=");
									sbLink.append(p.getId());
									sbLink.append("&");
									sbLink.append("OPERACAO=");
									sbLink.append("VISUALIZAR");
	
									sbLink.append(">");
	
									sbRegistro.append("<TD>");
									sbRegistro.append(sbLink.toString());
									sbRegistro.append(p.getCli().getNome());
									sbRegistro.append("</a>");
									sbRegistro.append("</TD>");
	
									sbRegistro.append("<TD>");
									sbRegistro.append(sbLink.toString());
									sbRegistro.append(p.getTotal());
									sbRegistro.append("</a>");
									sbRegistro.append("</TD>");
	
									sbRegistro.append("<TD>");
									sbRegistro.append(sbLink.toString());
									sbRegistro.append(p.getStatus());	
									sbRegistro.append("</a>");
									sbRegistro.append("</TD>");

									sbRegistro.append("</TR>");
	
									out.print(sbRegistro.toString());
	
								}
							%>

						</tbody>
					</TABLE>

					<br />
			</div>



		</div>
		<div class="botoesCad">

			<input type='button' value='VOLTAR' onclick='history.go(-1)' />
		</div>
		</form>
		<div class="rodape" />


	</div>
	<%
		Resultado result = (Resultado) request.getAttribute("resultado");
		if (result != null) {
			List<EntidadeDominio> entidades = result.getEntidades();
			if (result.getMsg() != null && !result.getMsg().equals("")) {
				out.println("<script>alert('" + result.getMsg() + "')</script>");

			}
			
			result = null;
			request.setAttribute("resultado", result);
		}
	%>
</body>
</html>