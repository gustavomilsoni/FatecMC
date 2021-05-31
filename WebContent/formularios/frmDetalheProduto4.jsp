<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="dominio.*, java.util.*"%>
<%@page import="util.Resultado"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="estilo.css" rel="stylesheet" type="text/css" />
<script src="script.js"></script>
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



				if (usuario != null) {
			%>
	
				<%
					out.print(usuario.getPessoa().getNome());
				%>

			<button><a href="/Less/formularios/Pedido?txtId=<%=usuario.getPessoa().getId()%>&OPERACAO=CONSULTAR_PEDIDOS_CLI">Minhas compras</a></button>
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


			<img src="detalhes4.png" width="100%" height="80%" />

			<form name="frmCadCli" action="ItemCarrinho" method="post">

			<input type="hidden" name="txtCodProd" value="4" />
				<div>
					<div class="caixaDeTextoLado">
						<div class="labelCaixaTexto">
							<label for="txtPreco">Preço</label>
						</div>
						<input type="text" id="txtPreco" name="txtPreco" size="15" />
					</div>

					<div class="caixaDeTextoLado">
						<div class="labelCaixaTexto">
							<label for="txtQuantidade">Quantidade</label>
						</div>
						<input type="text" id="txtQuantidade" name="txtQuantidade"
							size="15" />
					</div>
				</div>
		</div>
		<div class="botoesCad">
			<input type="submit" id="OPERACAO" name="OPERACAO" value="SALVAR" />
			<input type='button' value='VOLTAR' onclick='history.go(-1)' />

		</div>
		</form>

		<div class="rodape" />
	</div>
	<%

		if (result != null) {
			List<EntidadeDominio> entidades = result.getEntidades();
			if (result.getMsg() != null && !result.getMsg().equals("")) {
				out.println("<script>alert('" + result.getMsg() + "')</script>");

			}
		}
	%>
</body>
</html>