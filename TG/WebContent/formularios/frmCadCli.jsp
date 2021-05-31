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

	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Favicon-->
	<link rel="shortcut icon" href="img/fav.png">
	<!-- Author Meta -->
	<meta name="author" content="CodePixar">
	<!-- Meta Description -->
	<meta name="description" content="">
	<!-- Meta Keyword -->
	<meta name="keywords" content="">
	<!-- meta character set -->
	<meta charset="UTF-8">
	<!-- Site Title -->
	<title>E commerce</title>

	<!--
            CSS
            ============================================= -->
	<link rel="stylesheet" href="css/linearicons.css">
	<link rel="stylesheet" href="css/owl.carousel.css">
	<link rel="stylesheet" href="css/themify-icons.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/nice-select.css">
	<link rel="stylesheet" href="css/nouislider.min.css">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/main.css">
</head>
<body>



	<!-- Start Header Area -->
	<header class="header_area sticky-header">
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light main_box">
				<div class="container">
					<!-- Brand and toggle get grouped for better mobile display -->
					<a class="navbar-brand logo_h" href="index.html"><img src="img/logo.png" alt=""></a>
				    <div class="navbar-toggler">
						<ul class="nav navbar-nav menu_nav ml-auto">
							

						</ul>

					</div>
					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
					 aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<div class="collapse navbar-collapse offset">
						<ul class="nav navbar-nav menu_nav ml-auto">
							

						</ul>

					</div>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
					
				<%
					Resultado result = (Resultado) request.getAttribute("resultado");

					Usuario usuario = (Usuario) session.getAttribute("usuario");
	
					if (usuario != null) {
				%>	
						<ul class="nav navbar-nav menu_nav ml-auto">
						<li class="nav-item active"><a class="nav-link" href="index.jsp">Home</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Sobre</a></li>
						<li class="nav-item"><a class="nav-link" href="/Less/formularios/Pedido?txtId=<%=usuario.getPessoa().getId()%>&OPERACAO=CONSULTAR_PEDIDOS_CLI">Minhas compras</a></li>
						<li class="nav-item"><a class="nav-link" href="/Less/formularios/Cupons?txtId=<%=usuario.getPessoa().getId()%>&OPERACAO=CONSULTAR">Meus cupons</a></li>
						<li class="nav-item"><a class="nav-link" href="Login?OPERACAO=LOGOUT">Logout</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="nav-item"><a href="/Less/formularios/Carrinho?txtId=<%=usuario.getPessoa().getId()%>&OPERACAO=CONSULTAR" class="cart"><span class="ti-bag"></span></a></li>
						
				
					</ul>
				<%
					}else{	
						
				%>	


						<ul class="nav navbar-nav menu_nav ml-auto">
							<li class="nav-item active"><a class="nav-link" href="index.jsp">Home</a></li>
							<li class="nav-item"><a class="nav-link" href="#">Sobre</a></li>
							<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
						</ul>

				<%
					}	
						
				%>	
					</div>
				</div>
			</nav>
		</div>
 
	</header>
	<!-- End Header Area -->
	
	<!-- Início Whatsapp -->
	<div id="whatsapp">
	
	   <a href="http://api.whatsapp.com/send?1=pt_BR&phone=551135547060"><img src="img/elements/whats.png" width="40px" height="40px" alt=""></a>
	</div>
		
	<!-- Fim Whatsapp -->

	<!-- Start Banner Area -->
		<div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
		  <div class="carousel-inner">
			<div class="carousel-item active">
			  <img class="d-block w-100" src="img/banner/banner-principal.jpg" alt="Primeiro Slide">
			</div>
		  </div>
        </div>
		
		<br/>
		<br/>
		<br/>
	<!-- End Banner Area -->

	<!--================Login Box Area =================-->
	<div class="container">
<form name="frmCadCli" action="CadCli" method="post">
  <div class="form-row">
  
     <legend>Dados do cliente</legend>
	 </br>
	<div class="form-group col-md-6">
      <label for="txtNome">Nome</label>
      <input type="text" class="form-control" id="txtNome" name="txtNome" placeholder="">
    </div>
	<div class="form-group col-md-6">
      <label for="cmbGenero">Sexo</label>
      <select id="cmbGenero" name="cmbGenero" class="form-control">
			<option selected></option>
			<option value="SP">Masculino</option>
			<option value="AM">Feminino</option>

      </select>
    </div>
  	<div class="form-group col-md-6">
      <label for="txtCpf">CPF</label>
      <input type="text" class="form-control" name="txtCpf" id="txtCpf" placeholder="">
    </div>
	
	<div class="form-group col-md-6">
      <label for="txtDataNasc">Data de nascimento</label>
      <input type="date" class="form-control" id="txtDataNasc" name="txtDataNasc" placeholder="">
    </div>
	<div class="form-group col-md-6">
      <label for="cmbTipoTel">Tipo de Telefone</label>
      <select id="cmbTipoTel" name="cmbTipoTel" class="form-control">
			<option selected></option>
			<option value="TipoTel A">Comercial</option>
			<option value="TipoTel B">Residencial</option>
      </select>
    </div>
    <div class="form-group col-md-6">
      <label for="txtTelefone">Telefone</label>
      <input type="text" class="form-control" id="txtTelefone" name="txtTelefone" placeholder="">
    </div>
	
	<div class="form-group col-md-6">
      <label for="txtEmail">Email</label>
      <input type="text" class="form-control" id="txtEmail" name="txtEmail" placeholder="">
    </div>
    <div class="form-group col-md-3">
      <label for="txtSenha">Senha</label>
      <input type="password" class="form-control" id="txtSenha" name="txtSenha" placeholder="">
    </div>
	<div class="form-group col-md-3">
      <label for="txtConfirmacaoSenha">Confirmação da senha</label>
      <input type="password" class="form-control" id="txtConfirmacaoSenha" name="txtConfirmacaoSenha" placeholder="">
    </div>
 
</br>
</br>
    <legend>Informações do endereço</legend>
	</br>
	
	<div class="form-group col-md-6">
      <label for="cmbTipoEndereco">Tipo de endereço</label>
      <select id="cmbTipoEndereco" name="cmbTipoEndereco" class="form-control">
			<option selected></option>
			<option value="TipoEnd 1">Entrega</option>
			<option value="TipoEnd 2">Cobrança</option>
      </select>
    </div>
	<div class="form-group col-md-6">
      <label for="cmbTipoLogradouro">Tipo de logradouro</label>
      <select id="cmbTipoLogradouro" name="cmbTipoLogradouro" class="form-control">
			<option selected></option>
			<option value="TipoLograd 1">Rua</option>
			<option value="TipoLograd 2">Avenida</option>
      </select>
    </div>
  <div class="form-group col-md-6"">
    <label for="txtLogradouro">Logradouro</label>
    <input type="text" class="form-control" id="txtLogradouro" name="txtLogradouro" placeholder="">
  </div>
  <div class="form-group col-md-6"">
    <label for="txtNumero">número</label>
    <input type="text" class="form-control" id="txtNumero" name="txtNumero" placeholder="">
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="txtBairro">Bairro</label>
      <input type="text" class="form-control" id="txtBairro" name="txtBairro">
    </div>
    <div class="form-group col-md-6">
      <label for="inputCity">Cidade</label>
      <input type="text" class="form-control" id="inputCity">
    </div>
    <div class="form-group col-md-4">
      <label for="cmbUf">Estado</label>
      <select id="cmbUf" name="cmbUf" class="form-control">
        <option selected></option>
			<option value="SP">SP</option>
			<option value="AM">AM</option>
			<option value="ES">ES</option>
			<option value="MG">MG</option>
			<option value="RJ">RJ</option>
      </select>
    </div>
	
	 <div class="form-group col-md-4">
      <label for="cmbPais">País</label>
      <select id="cmbPais" class="form-control" name="cmbPais">
        <option selected></option>
			<option value="SP">Brasil</option>
			<option value="AM">Argentina</option>
			<option value="ES">Uruguai</option>

      </select>
    </div>
    <div class="form-group col-md-2">
      <label for="txtCep">CEP</label>
      <input type="text" class="form-control" id="txtCep" name="txtCep">
    </div>
	
	<div class="form-group col-md-2">
      <label for="txtObservacao">Observações</label>
      <input type="text" class="form-control" id="txtObservacao" name="txtObservacao">
    </div>
  </div>
</br>
</br>

    <legend>Informações do cartão</legend>
	</br>
	
	<div class="form-group col-md-6">
      <label for="txtNomeCart">Nome do titular</label>
      <input type="text" class="form-control" id="txtNomeCart" name="txtNomeCart">
    </div>
	<div class="form-group col-md-6">
      <label for="txtNumCartao">Número do cartão</label>
      <input type="text" class="form-control" id="txtNumCartao" name="txtNumCartao">
    </div>
	 <div class="form-group col-md-6">
      <label for="cmbBandeira">Bandeira</label>
      <select id="cmbBandeira" class="form-control" name="cmbBandeira">
        <option selected></option>
			<option value="Visa">Visa</option>
			<option value="Master">Master</option>
      </select>
    </div>
	<div class="form-group col-md-6">
      <label for="txtValidade">Validade</label>
      <input type="text" class="form-control" id="txtValidade" name="txtValidade">
    </div>
	<div class="form-group col-md-6">
      <label for="txtCvv">CVV</label>
      <input type="text" class="form-control" id="txtCvv" name="txtCvv">
    </div>
 
 </div>


<input type="submit" id="OPERACAO" name="OPERACAO" value="SALVAR" />

<input type='button' value='VOLTAR' onclick='history.go(-1)' />
</form>
	</div>		
	<!--================End Login Box Area =================-->

	<!-- start footer Area -->
	<footer class="footer-area section_gap">
		<div class="container">
			<div class="row">
				<div class="col-lg-3  col-md-6 col-sm-6">
					<div class="single-footer-widget">
						<h6>Sobre nós</h6>
						<p>
							O e-commerce de livros para programadores esta no mercado desde 2020, e vem conquistando seus clientes com muita atenção e respeito desde então.
							
						</p>
					</div>
				</div>
	
	
				<div class="col-lg-2 col-md-6 col-sm-6">
					<div class="single-footer-widget">
						<h6>Sigam-nos</h6>
						<p>nas redes sociais</p>
						<div class="footer-social d-flex align-items-center">
							<a href="#"><i class="fa fa-facebook"></i></a>
							<a href="#"><i class="fa fa-twitter"></i></a>

						</div>
					</div>
				</div>
			</div>


		</div>
	</footer>
	<!-- End footer Area -->


	<script src="js/vendor/jquery-2.2.4.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
	 crossorigin="anonymous"></script>
	<script src="js/vendor/bootstrap.min.js"></script>
	<script src="js/jquery.ajaxchimp.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery.sticky.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<!--gmaps Js-->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
	<script src="js/gmaps.min.js"></script>
	<script src="js/main.js"></script>
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