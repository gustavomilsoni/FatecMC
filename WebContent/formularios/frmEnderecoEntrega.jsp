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
	  <h3 class="title_confirmation">Endereço de entrega</h3>
      <div class="row">
        <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

          <!-- Shopping cart table -->
          <div class="table-responsive">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col" class="border-0 bg-light">
                    <div class="p-2 px-3 text-uppercase">Logradouro</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">número</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">cidade</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">UF</div>
                  </th>

                </tr>
              </thead>
              <tbody>
              
              	<%       
							Cliente cliente = (Cliente)usuario.getPessoa();
              				Endereco p;
							for (int i = 0; i < cliente.getEnderecos().size(); i++) {
								p = (Endereco) cliente.getEnderecos().get(i);

              	
			    %>
			  
					                <tr>
					                  <th scope="row" class="border-0">
					                    <div class="p-2">
					                      
					                      <div class="ml-3 d-inline-block align-middle">
					                        <h5 class="mb-0"> <a href="frmCartaoPagamento.jsp?txtId=<%=p.getId()%>&OPERACAO=END_ENTREGA" class="text-dark d-inline-block align-middle"><%=p.getLogradouro()%></a></h5>
					                      </div>
					                    </div>
					                  </th>

					                  <td class="border-0 align-middle"><strong><%=p.getNumero()%></strong></td>
					                  <td class="border-0 align-middle"><strong><%=p.getCidade().getNome()%></strong></td>
					                  <td class="border-0 align-middle"><strong><%=p.getCidade().getEstado().getUf()%></strong></td>

									</tr>

									
					<%
								}
								
					%>				
              

              </tbody>
            </table>
          </div>
          <!-- End -->
        </div>
      </div>
            <div class="billing_details">
                <div class="row">
                    <div class="col-lg-8">
                        <h3>Informação do novo endereço</h3>
                        <form class="row contact_form" action="#" method="post" novalidate="novalidate">
						
							<div class="col-md-12 form-group p_star">
                                <select class="country_select" name="cmbTipoEndereco">
									<option value="TipoEnd 1">Entrega</option>
									<option value="TipoEnd 2">Cobrança</option>
                                </select>
                            </div>
							<div class="col-md-12 form-group p_star">
                                <select class="country_select" name="cmbTipoLogradouro">
									<option value="TipoLograd 1">Rua</option>
									<option value="Tipolograd 2">Av</option>
									<option value="Tipolograd 2">Rodovia</option>
                                </select>
                            </div>

                            <div class="col-md-12 form-group">
                                <input type="text" class="form-control" id="company" name="txtLogradouro" placeholder="Logradouro">
                            </div>
							<div class="col-md-12 form-group">
                                <input type="text" class="form-control" id="company" name="txtNumero" placeholder="Número">
                            </div>
                            <div class="col-md-12 form-group">
                                <input type="text" class="form-control" id="company" name="txtBairro" placeholder="Bairro">
                            </div>
							<div class="col-md-12 form-group">
                                <input type="text" class="form-control" id="company" name="txtCep" placeholder="CEP">
                            </div>
                            <div class="col-md-12 form-group">
                                <input type="text" class="form-control" id="company" name="txtCidade" placeholder="Cidade">
                            </div>
                            <div class="col-md-12 form-group p_star">
                                <select class="country_select" name="cmbUf">
									<option value="SP">SP</option>
									<option value="AM">AM</option>
									<option value="ES">ES</option>
									<option value="MG">MG</option>
									<option value="RJ">RJ</option>
                                </select>
                            </div>
                            <div class="col-md-12 form-group p_star">
                                <select class="country_select" name="cmbPais">

									<option value="Brasil">Brasil</option>
									<option value="Argentina">Argentina</option>
									<option value="Uruguai">Uruguai</option>
                                </select>
                            </div>

                            <div class="col-md-12 form-group">
  
                                <textarea class="form-control" name="message" id="message" rows="1" placeholder="Observações"></textarea>
                            </div>
							
								<input class="btn btn-primary" type="submit" value="Adicionar">
								<button type="button" class="btn btn-outline-primary">Voltar</button>
                        </form>
						

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
						<h6>About Us</h6>
						<p>
							Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore dolore
							magna aliqua.
						</p>
					</div>
				</div>
				<div class="col-lg-4  col-md-6 col-sm-6">
					<div class="single-footer-widget">
						<h6>Newsletter</h6>
						<p>Stay update with our latest</p>
						<div class="" id="mc_embed_signup">

							<form target="_blank" novalidate="true" action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
							 method="get" class="form-inline">

								<div class="d-flex flex-row">

									<input class="form-control" name="EMAIL" placeholder="Enter Email" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Email '"
									 required="" type="email">


									<button class="click-btn btn btn-default"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></button>
									<div style="position: absolute; left: -5000px;">
										<input name="b_36c4fd991d266f23781ded980_aefe40901a" tabindex="-1" value="" type="text">
									</div>

									<!-- <div class="col-lg-4 col-md-4">
													<button class="bb-btn btn"><span class="lnr lnr-arrow-right"></span></button>
												</div>  -->
								</div>
								<div class="info"></div>
							</form>
						</div>
					</div>
				</div>
				<div class="col-lg-3  col-md-6 col-sm-6">
					<div class="single-footer-widget mail-chimp">
						<h6 class="mb-20">Instragram Feed</h6>
						<ul class="instafeed d-flex flex-wrap">
							<li><img src="img/i1.jpg" alt=""></li>
							<li><img src="img/i2.jpg" alt=""></li>
							<li><img src="img/i3.jpg" alt=""></li>
							<li><img src="img/i4.jpg" alt=""></li>
							<li><img src="img/i5.jpg" alt=""></li>
							<li><img src="img/i6.jpg" alt=""></li>
							<li><img src="img/i7.jpg" alt=""></li>
							<li><img src="img/i8.jpg" alt=""></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-2 col-md-6 col-sm-6">
					<div class="single-footer-widget">
						<h6>Follow Us</h6>
						<p>Let us be social</p>
						<div class="footer-social d-flex align-items-center">
							<a href="#"><i class="fa fa-facebook"></i></a>
							<a href="#"><i class="fa fa-twitter"></i></a>
							<a href="#"><i class="fa fa-dribbble"></i></a>
							<a href="#"><i class="fa fa-behance"></i></a>
						</div>
					</div>
				</div>
			</div>
			<div class="footer-bottom d-flex justify-content-center align-items-center flex-wrap">
				<p class="footer-text m-0"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
</p>
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
		Resultado result = (Resultado) request.getAttribute("resultado");
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