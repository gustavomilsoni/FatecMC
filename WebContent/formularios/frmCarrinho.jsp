<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="dominio.*, java.util.*"%>
<%@page import="util.Resultado"%>
<%@page import="java.text.DecimalFormat"%>

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
			<%
			
				DecimalFormat df = new DecimalFormat("#,###.00");
				double total = 0.0;
				Resultado result = (Resultado) request.getAttribute("resultado");
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				Carrinho car = new Carrinho();
				if (result != null){
					if(result.getEntidades()!= null){
						
						entidades = result.getEntidades();
						car = (Carrinho) entidades.get(0);
						session.setAttribute("carrinho", car);
					}
				}	
				

			%>


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
	<div class="px-4 px-lg-0">


  <div class="pb-5">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

          <!-- Shopping cart table -->
          <div class="table-responsive">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col" class="border-0 bg-light">
                    <div class="p-2 px-3 text-uppercase">Livro</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">Valor</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">Quantidade</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">Excluir</div>
                  </th>
                </tr>
              </thead>
              <tbody>
              
             
  			<%            
							if (result != null) {

								for (int i = 0; i < car.getItens().size(); i++) {
									ItemCarrinho p = (ItemCarrinho) car.getItens().get(i);
									total += (p.getQuantidade() * p.getLivro().getValorVenda());

			  %>
			  
					                <tr>
					                  <th scope="row" class="border-0">
					                    <div class="p-2">
					                      
					                      <div class="ml-3 d-inline-block align-middle">
					                        <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block align-middle"><%=p.getLivro().getTitulo()%></a></h5><span class="text-muted font-weight-normal font-italic d-block">Category: Watches</span>
					                      </div>
					                    </div>
					                  </th>
					                  <td class="border-0 align-middle"><strong><%=p.getLivro().getValorVenda()%></strong></td>
					                  <td class="border-0 align-middle"><strong><%=p.getQuantidade()%></strong></td>
					                  <td class="border-0 align-middle"><a href="#" class="text-dark"><i class="fa fa-trash"></i></a></td>
									</tr>

									
					<%
								}
							}	
					%>				

								
					
              	</tbody>
						

            </table>
          </div>
          <!-- End -->
        </div>
      </div>

        <div class="col-lg-6">
          <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Total </div>
          <div class="p-4">
            <ul class="list-unstyled mb-4">

              <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total</strong>
                <h5 class="font-weight-bold"><%=total%></h5>
              </li>
            </ul>
			<a href="frmEnderecoEntrega.jsp" class="btn btn-dark rounded-pill py-2 btn-block">COMPRAR</a>
			
          </div>
        </div>
      </div>

    </div>
  </div>
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
</body>

</html>