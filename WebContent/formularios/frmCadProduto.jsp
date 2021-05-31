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
					
					Livro livro = new Livro();
				
					if (result != null){
						if(result.getEntidades()!= null){
							livro = ((List<Livro>)(List<?>)result.getEntidades()).get(0);
						}
					}
					Usuario usuario =  (Usuario) session.getAttribute("usuario");
	
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
		<form name="frmCadProduto" action="CadProd" method="post">
		
		<input type="hidden" name="txtCodProd" value="<%=livro.getId() %>" />
  <div class="form-row">
  
     <legend>Dados do produto</legend>
	 </br>
	<div class="form-group col-md-6">
      <label for="txtTitulo">Título</label>
      <input type="text" class="form-control" id="txtTitulo" name="txtTitulo" value="<%=livro.getTitulo() %>">
    </div>
    
    <div class="form-group col-md-6">
      <label for="txtAno">Ano</label>
      <input type="text" class="form-control" id="txtAno" name="txtAno" value="<%=livro.getAno() %>">
    </div>
    	<div class="form-group col-md-6">
      <label for="txtAutor">Autor</label>
      <input type="text" class="form-control" id="txtAutor" name="txtAutor" value="<%=livro.getAutor() %>">
    </div>
    
    <div class="form-group col-md-6">
      <label for="txtEdicao">Edição</label>
      <input type="text" class="form-control" id="txtEdicao" name="txtEdicao" value="<%=livro.getEdicao() %>">
    </div>
    	<div class="form-group col-md-6">
      <label for="txtEditora">Editora</label>
      <input type="text" class="form-control" id="txtEditora" name="txtEditora" value="<%=livro.getEditora() %>">
    </div>
    
    <div class="form-group col-md-6">
      <label for="txtIsbn">ISBN</label>
      <input type="text" class="form-control" id="txtIsbn" name="txtIsbn" value="<%=livro.getIsbn() %>">
    </div>
    	<div class="form-group col-md-6">
      <label for="txtNumPagina">Quantidade de páginas</label>
      <input type="text" class="form-control" id="txtNumPagina" name="txtNumPagina" value="<%=livro.getNumPag() %>">
    </div>
    
    <div class="form-group col-md-6">
      <label for="txtPeso">Peso</label>
      <input type="text" class="form-control" id="txtPeso" name="txtPeso" value="<%=livro.getPeso() %>">
    </div>
    	<div class="form-group col-md-6">
      <label for="txtCodBarras">Código de barras</label>
      <input type="text" class="form-control" id="txtCodBarras" name="txtCodBarras" value="<%=livro.getCodBarras() %>">
    </div>
    
    <div class="form-group col-md-6">
      <label for="txtAltura">Altura</label>
      <input type="text" class="form-control" id="txtAltura" name="txtAltura" value="<%=livro.getAltura() %>">
    </div>
    <div class="form-group col-md-6">
      <label for="txtProfundidade">Profundidade</label>
      <input type="text" class="form-control" id="txtProfundidade" name="txtProfundidade" value="<%=livro.getProfundidade() %>">
    </div>
    
    <div class="form-group col-md-6">
      <label for="txtLargura">Largura</label>
      <input type="text" class="form-control" id="txtLargura" name="txtLargura" value="<%=livro.getLargura() %>">
    </div>  
      
	<div class="form-group col-md-6">
      <label for="cmbCategoria">Categoria</label>
      <select id="cmbCategoria" name="cmbCategoria" class="form-control">
		<option value="1" <% if(livro.getCategoria().getId() ==1){out.print("selected");} %>>JAVA</option>
		<option value="2" <% if(livro.getCategoria().getId() ==2){out.print("selected");} %>>C++</option>
		<option value="3" <% if(livro.getCategoria().getId() ==3){out.print("selected");} %>>PYTHON</option>
      </select>
    </div>
    <div class="form-group col-md-6">
      <label for="cmbGrupoPreco">Precificação</label>
      <select id="cmbGrupoPreco" name="cmbGrupoPreco" class="form-control">
		<option value="1" <% if(livro.getGrpPreco().getId() ==1){out.print("selected");} %>>GRUPO 1</option>
		<option value="2" <% if(livro.getGrpPreco().getId() ==2){out.print("selected");} %>>GRUPO 2</option>
      </select>
    </div>
    
    
  	<div class="form-group col-md-6">
      <label for="txtMargem">Margem</label>
      <input type="text" class="form-control" name="txtMargem" id="txtMargem" value="<%=livro.getMargem() %>">
    </div>
    <div class="form-group col-md-6">
      <label for="txtSinopse">Sinopse</label>
      <input type="text" class="form-control" name="txtSinopse" id="txtSinopse" value="<%=livro.getSinopse() %>">
    </div>
	
	
 
 </div>
			<input type="submit" id="OPERACAO" name="OPERACAO" value="SALVAR" />
			<input type="submit" id="OPERACAO" name="OPERACAO" value="CONSULTAR" /> 
			<input type="submit" id="OPERACAO" name="OPERACAO" value="ALTERAR" />
			<input type="submit" id="OPERACAO" name="OPERACAO" value="EXCLUIR" />  
			
			<button>
					<a href="frmAdmInicial.jsp">VOLTAR</a>
			</button>

</form>
	</div>	
	</br>	
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