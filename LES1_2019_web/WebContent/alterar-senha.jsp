<%@page import="LES1_2019.core.aplicacao.Resultado"%>
<%@page import="LES1_2019.dominio.*"%>
<%@page import="LES1_2019.auxiliar.DadosDeCadastro"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html lang="pt">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>Alterar senha</title>

	<!-- Google font -->
	<link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">

	<!-- Bootstrap -->
	<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

	<!-- Slick -->
	<link type="text/css" rel="stylesheet" href="css/slick.css" />
	<link type="text/css" rel="stylesheet" href="css/slick-theme.css" />

	<!-- nouislider -->
	<link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />

	<!-- Font Awesome Icon -->
	<link rel="stylesheet" href="css/font-awesome.min.css">

	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="css/style.css" />

<%
	String nomeUser = "";
	int id=0;
	int erro = 0;
	User us;
	Resultado login = (Resultado) session.getAttribute("login");
	if(session.getAttribute("login")==null){
		pageContext.forward("index.jsp");
	}
	try{
		List<EntidadeDominio> user = login.getEntidades();
		} catch (Exception e){
			erro = 1;
	}
	
	Resultado dadosUser = (Resultado) session.getAttribute("dadosUser");
	if(session.getAttribute("dadosUser")==null){
		pageContext.forward("User?operacao=CONSULTAR.jsp");
	}
	List<EntidadeDominio> dadosUsuario = dadosUser.getEntidades();
	
	if(erro==0){
	List<EntidadeDominio> user = login.getEntidades();
	us = (User)user.get(0);
	nomeUser = us.getNome_usuario();
	id = us.getId();
	}

	
	StringBuilder sb = new StringBuilder();
	/* sb = new StringBuilder();
	sb.append(us.getNome_usuario());
	sb.append(us.getEnderecos().get(0).getRua());
out.print(sb.toString()); */
%>

</head>
<body>
	<!-- HEADER -->
	<header>
			<!-- header -->
			<div id="header">
				<div class="container">
					<div class="pull-left">
						<!-- Logo -->
						<div class="header-logo">
							<a class="logo" href="Produtos?operacao=INDEX">
								<img src="./img/logo.png" alt="">
							</a>
						</div>
						<!-- /Logo -->
	
						<!-- Search -->
						<div class="header-search">
							<form>
								<input class="input search-input" type="text" placeholder="Procurar um produto">
								<button class="search-btn"><i class="fa fa-search"></i></button>
							</form>
						</div>
						<!-- /Search -->
					</div>
					<div class="pull-right">
						<ul class="header-btns">
							<jsp:include page="minha-conta.jsp" />
	
							<jsp:include page="carrinho.jsp" />
	
							<!-- Mobile nav toggle-->
							<li class="nav-toggle">
								<button class="nav-toggle-btn main-btn icon-btn"><i class="fa fa-bars"></i></button>
							</li>
							<!-- / Mobile nav toggle -->
						</ul>
					</div>
				</div>
				<!-- header -->
			</div>
			<!-- container -->
		</header>
		<!-- /HEADER -->

	<!-- NAVIGATION -->
	<div id="navigation">
		<!-- container -->
		<div class="container">
			<div id="responsive-nav">
				<!-- category nav -->
				<div class="category-nav show-on-click">
					<span class="category-header">Busque por marca <i class="fa fa-list"></i></span>
					<ul class="category-list">
						<li class="dropdown side-dropdown">
							<a href="#" class="dropdown-toggle" aria-expanded="true">Apple <i class="fa fa-angle-right"></i></a>
						</li>
						<li class="dropdown side-dropdown">
							<a href="#" class="dropdown-toggle" aria-expanded="true">Samsung <i class="fa fa-angle-right"></i></a>
						</li>
						<li class="dropdown side-dropdown">
								<a href="#" class="dropdown-toggle" aria-expanded="true">Xiaomi <i class="fa fa-angle-right"></i></a>
						</li>
						<li class="dropdown side-dropdown">
								<a href="#" class="dropdown-toggle" aria-expanded="true">Razer <i class="fa fa-angle-right"></i></a>
						</li>
					</ul>
				</div>
				<!-- /category nav -->

				<!-- menu nav -->
				<div class="menu-nav">
					<span class="menu-header">Menu <i class="fa fa-bars"></i></span>
					<ul class="menu-list">
						<li class="dropdown default-dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">   Categorias de produtos <i class="fa fa-caret-down"></i></a>
							<ul class="custom-menu">
								<li><a href="index.html">Celulares</a></li>
								<li><a href="products.html">Relogios</a></li>
								<li><a href="product-page.html">Fones de Ouvido</a></li>
								<li><a href="checkout.html">Computadores</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- menu nav -->
			</div>
		</div>
		<!-- /container -->
	</div>
	<!-- /NAVIGATION -->
<body>
	<!-- HEADER -->
	<header>
			<!-- header -->
			<div id="header">
				<div class="container">
					<div class="pull-left">
						<!-- Logo -->
						<div class="header-logo">
							<a class="logo" href="#">
								<img src="./img/logo.png" alt="">
							</a>
						</div>
						<!-- /Logo -->
	
						<!-- Search -->
						<div class="header-search">
							<form>
								<input class="input search-input" type="text" placeholder="Procurar um produto">
								<button class="search-btn"><i class="fa fa-search"></i></button>
							</form>
						</div>
						<!-- /Search -->
					</div>
					<div class="pull-right">
						<ul class="header-btns">
							<!-- Account -->
						<li class="header-account dropdown default-dropdown">
							<div class="dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="true">
								<div class="header-btns-icon">
									<i class="fa fa-user-o"></i>
								</div>
							<% 
							sb = new StringBuilder();
							
							if(nomeUser != ""){
							sb.append("<strong class='text-uppercase'>"+nomeUser+" <i class='fa fa-caret-down'></i></strong>");
							sb.append("</div>");
							sb.append("<div style ='display:flex'>");
							sb.append("<a href='logout.jsp' class='text-uppercase'>Logout</a></div>");
							sb.append("<ul class='custom-menu'><li><a href='#'><i class='fa fa-user-o'></i> Meus dados</a></li></ul>");
							
							} else {
							sb.append("<strong class='text-uppercase'>Minha conta<i class='fa fa-caret-down'></i></strong>");
							sb.append("</div>");
							sb.append("<div style ='display:flex'>");
							sb.append("<a href='login.jsp' class='text-uppercase'>Login</a> / <a style = 'padding-right: 40px' href='criar-cadastro.jsp' class='text-uppercase'>Cadastrar</a></div>");	
							sb.append("<ul class='custom-menu'><li><a href='login.jsp'><i class='fa fa-user-o'></i> Meus dados</a></li></ul>");
							}
							out.print(sb.toString());
							%>
						</li>
						<!-- /Account -->
	
							<!-- Cart -->
							<li class="header-cart dropdown default-dropdown">
								<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
									<div class="header-btns-icon">
										<i class="fa fa-shopping-cart"></i>
										<span class="qty">4</span>
									</div>
									<strong class="text-uppercase">Meu carrinho</strong>
									<br>
									<span>R$50,00</span>
								</a>
								<div class="custom-menu">
									<div id="shopping-cart">
										<div class="shopping-cart-list">
											<div class="product product-widget">
												<div class="product-thumb">
													<img src="./img/thumb-product01.jpg" alt="">
												</div>
												<div class="product-body">
													<h3 class="product-price">R$25,00 <span class="qty">x3</span></h3>
													<h2 class="product-name"><a href="#">Tenis top</a></h2>
												</div>
												<button class="cancel-btn"><i class="fa fa-trash"></i></button>
											</div>
											<div class="product product-widget">
												<div class="product-thumb">
													<img src="./img/thumb-product01.jpg" alt="">
												</div>
												<div class="product-body">
													<h3 class="product-price">R$25,00 <span class="qty">x3</span></h3>
													<h2 class="product-name"><a href="#">Tenis top</a></h2>
												</div>
												<button class="cancel-btn"><i class="fa fa-trash"></i></button>
											</div>
										</div>
										<div class="shopping-cart-btns">
											<button class="main-btn">Ver carrinho</button>
											<button class="primary-btn">Checkout <i class="fa fa-arrow-circle-right"></i></button>
										</div>
									</div>
								</div>
							</li>
							<!-- /Cart -->
	
							<!-- Mobile nav toggle-->
							<li class="nav-toggle">
								<button class="nav-toggle-btn main-btn icon-btn"><i class="fa fa-bars"></i></button>
							</li>
							<!-- / Mobile nav toggle -->
						</ul>
					</div>
				</div>
				<!-- header -->
			</div>
			<!-- container -->
		</header>
		<!-- /HEADER -->

	<!-- NAVIGATION -->
	<div id="navigation">
		<!-- container -->
		<div class="container">
			<div id="responsive-nav">
				<!-- category nav -->
				<div class="category-nav show-on-click">
					<span class="category-header">Busque por marca <i class="fa fa-list"></i></span>
					<ul class="category-list">
						<li class="dropdown side-dropdown">
							<a href="#" class="dropdown-toggle" aria-expanded="true">Apple <i class="fa fa-angle-right"></i></a>
						</li>
						<li class="dropdown side-dropdown">
							<a href="#" class="dropdown-toggle" aria-expanded="true">Samsung <i class="fa fa-angle-right"></i></a>
						</li>
						<li class="dropdown side-dropdown">
								<a href="#" class="dropdown-toggle" aria-expanded="true">Xiaomi <i class="fa fa-angle-right"></i></a>
						</li>
						<li class="dropdown side-dropdown">
								<a href="#" class="dropdown-toggle" aria-expanded="true">Razer <i class="fa fa-angle-right"></i></a>
						</li>
					</ul>
				</div>
				<!-- /category nav -->

				<!-- menu nav -->
				<div class="menu-nav">
					<span class="menu-header">Menu <i class="fa fa-bars"></i></span>
					<ul class="menu-list">
						<li class="dropdown default-dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">   Categorias de produtos <i class="fa fa-caret-down"></i></a>
							<ul class="custom-menu">
								<li><a href="index.html">Celulares</a></li>
								<li><a href="products.html">Relogios</a></li>
								<li><a href="product-page.html">Fones de Ouvido</a></li>
								<li><a href="checkout.html">Computadores</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- menu nav -->
			</div>
		</div>
		<!-- /container -->
	</div>
	<!-- /NAVIGATION -->

	<!-- section -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!--  Product Details -->
				<div class="product product-details clearfix">
					<div class="col-md-6 col-md-offset-3">
						<div class="billing-details">
							<div class="section-title">
								<h3 class="title">Senha</h3>
							</div>
							<form action="User" method="POST">
							<div class="form-group">
								<input type="hidden" name="id-usuario" <%out.print("value='"+id+"'"); %>/>
							</div>
							<div class="form-group">
							<p><strong>Senha atual:</strong></p>
								<input class="input" type="text" name="senha-usuario" required oninvalid="this.setCustomValidity('O campo é obrigatório!')" oninput="setCustomValidity('')"/>
							</div>
							<div class="form-group">
							<p><strong>Senha nova:</strong></p>
								<input class="input" type="text" name="senha-usuario2" required oninvalid="this.setCustomValidity('O campo é obrigatório!')" oninput="setCustomValidity('')"/>
							</div>
							<div style="text-align:center" class="form-group">
								<button class="primary-btn" type="submit" name="operacao" value="ALTERAR-SENHA">Alterar senha</button>
							</div>
							</form>
						</div>
					</div>

				</div>
				<!-- /Product Details -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /section -->


	<!-- FOOTER -->
	<footer id="footer" class="section section-grey">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
	
				</div>
				<!-- /row -->
				<hr>
				<!-- row -->
				<div class="row">
					<div class="col-md-8 col-md-offset-2 text-center">
						<!-- footer copyright -->
						<div class="footer-copyright">
								<div class="footer-logo">
										<a class="logo" href="#">
								<img src="./img/logo.png" alt="">
							  </a>
									</div>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							Copyright &copy;<script>document.write(new Date().getFullYear());</script> menolStore - A loja mais top <i class="fa fa-heart-o" aria-hidden="true"></i>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							<!-- footer social -->
							<ul class="footer-social">
									<li><a href="#"><i class="fa fa-facebook"></i></a></li>
									<li><a href="#"><i class="fa fa-twitter"></i></a></li>
									<li><a href="#"><i class="fa fa-instagram"></i></a></li>
									<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
									<li><a href="#"><i class="fa fa-pinterest"></i></a></li>
								</ul>
								<!-- /footer social -->
						</div>
						<!-- /footer copyright -->
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</footer>
		<!-- /FOOTER -->
	

	<!-- jQuery Plugins -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/slick.min.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/jquery.zoom.min.js"></script>
	<script src="js/main.js"></script>

</body>

</html>
