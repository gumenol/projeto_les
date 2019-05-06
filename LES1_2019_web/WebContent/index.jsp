<%@page import="LES1_2019.core.aplicacao.Resultado"%>
<%@page import="LES1_2019.dominio.*"%>
<%@page import="LES1_2019.auxiliar.DadosDeCadastro"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html lang="pt">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	
	<title>menolStore</title>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
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
int erro = 0;
Resultado login = (Resultado) session.getAttribute("login");
	try{
		List<EntidadeDominio> user = login.getEntidades();
		} catch (Exception e){
			erro = 1;
	}

	Resultado resultado = (Resultado) session.getAttribute("produtos");
	List<EntidadeDominio> DadosProd = null;
	List<EntidadeDominio> DadosOK = null;
	Produto prd;
	if(session.getAttribute("produtos")!=null){
		try{
			DadosOK = resultado.getEntidades();
			} catch (Exception e){
				String redirectURL = "Produtos?operacao=INDEX";
				pageContext.forward(redirectURL);
			}
		DadosOK = resultado.getEntidades();
		DadosProd = new ArrayList<EntidadeDominio>(DadosOK);
		Collections.shuffle(DadosProd);
		if(DadosProd.isEmpty()){
			String redirectURL = "Produtos?operacao=INDEX";
			pageContext.forward(redirectURL);
		}
	}
	if(DadosOK.isEmpty() || DadosOK==null){
		String redirectURL = "Produtos?operacao=INDEX";
		pageContext.forward(redirectURL);
	}
	if(DadosProd.isEmpty() || DadosProd==null){
		String redirectURL = "Produtos?operacao=INDEX";
		pageContext.forward(redirectURL);
	}
	
	String nomeUser = "";
	if(erro==0){
	List<EntidadeDominio> user = login.getEntidades();
	User us = (User)user.get(0);
	nomeUser = us.getNome_usuario();
	}
	StringBuilder sb = new StringBuilder();
	int id0=0;
	int id1=0;
	int id2=0;
	/* sb = new StringBuilder();
	sb.append(us.getNome_usuario());
	sb.append(us.getEnderecos().get(0).getRua());
out.print(sb.toString()); */
%>

</head>

<body onload="login()">
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
							<li class="dropdown default-dropdown"><a class="dropdown-toggle" data-toggle="dropdown">   Categorias de produtos <i class="fa fa-caret-down"></i></a>
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

	<!-- HOME -->
	
	<!-- /HOME -->

	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
					<!-- banner -->
					<div class="col-md-3 col-sm-6 col-xs-6">
						<div class="banner banner-2">
							<img src="./img/banner15.jpg" alt="">
							<div class="banner-caption">
								<h2 class="white-color">Novos<br>Produtos</h2>
								<button class="primary-btn">Comprar!</button>
							</div>
						</div>
					</div>
					<!-- /banner -->
					<!-- Product Single -->
				<%
				sb = new StringBuilder();
				int i = 0;
				for(EntidadeDominio ent : DadosProd){
					prd = (Produto)ent;
					if(prd.getStatus_produto() & prd.getQtd_estoque()>15){
					sb.append("<div class='col-md-3 col-sm-6 col-xs-6'>");
					sb.append("<div class='product product-single'>");
					sb.append("<a href='Produtos?id-produto="+prd.getId()+"&operacao=VISUALIZAR'><div class='product-thumb'>");//3
					sb.append("<div class='product-label'>");
					sb.append("<span>Novidade</span>");
					sb.append("</div>");
					sb.append("<img src='"+prd.getImg_produto()+"'>");
					sb.append("</div></a>");
						sb.append("	<div class='product-body'>");
							sb.append("	<h3 class='product-price'>R"+String.format("$%,.2f", prd.getValor_produto())+" </h3>");
								sb.append("	<div class='product-rating'>");
									sb.append("		<i class='fa fa-star'></i>");
										sb.append("	<i class='fa fa-star'></i>");
										sb.append("	<i class='fa fa-star'></i>");
										sb.append("	<i class='fa fa-star'></i>");
								sb.append("<i class='fa fa-star-o empty'></i>");
								sb.append("</div>");
								sb.append("<h2 class='product-name'><a href='#'>"+prd.getNome()+" | "+prd.getCategoria().getNome()+"</a></h2>");
								sb.append("<div class='product-btns'>");
								sb.append("<form action='Pedidos' method='POST'>");
								sb.append("<input type='hidden' name='id-produto' value='"+prd.getId()+"'>");
								sb.append("<input type='hidden' name='nome-produto' value='"+prd.getNome()+"'>");
								sb.append("<input type='hidden' name='valor-produto' value='"+prd.getValor_produto()+"'>");
								sb.append("<input type='hidden' name='qtd' value='1'>");
								if(erro==0){
									sb.append("<button type='submit' onclick='Carrinho()' name='operacao' value='ADDCARRINHO' class='primary-btn add-to-cart'><i class='fa fa-shopping-cart'></i> Adicionar ao carrinho</button>");
								} else{
									sb.append("<button type='button' onclick='Carrinho()' name='operacao' value='ADDCARRINHO' class='primary-btn add-to-cart'><i class='fa fa-shopping-cart'></i> Adicionar ao carrinho</button>");
								}
								sb.append("</form>");
								sb.append("</div>");
							sb.append("	</div>");
						sb.append("</div>");
						sb.append("</div>");
						if(i==0){
							id0 = prd.getId();
						}
						if(i==1){
							id1 = prd.getId();
						}
						if(i==2){
							id2 = prd.getId();
						}
						i++;
					}
					if(i==3){
						break;
					}
				}
				out.print(sb.toString());
				%>
					<!-- /Product Single -->
				</div>
				<!-- /row -->
			<!-- row -->
				<!-- section title -->
				<div class="col-md-12">
					<div class="section-title">
						<h2 class="title">Outros produtos</h2>
					</div>
				</div>
				<!-- section title -->

		<% 		
				sb = new StringBuilder();
				i = 0;
				for(EntidadeDominio ent : DadosProd){
					prd = (Produto)ent;
					if(prd.getStatus_produto()){
						if(!(prd.getId()==id0 || prd.getId()==id1 || prd.getId()==id2)){
							if(i==0){
								sb.append("<div class='row'>");
							}
					sb.append("<div class='col-md-3 col-sm-6 col-xs-6'>");//1
					sb.append("<div class='product product-single'>");//2
					sb.append("<a href='Produtos?id-produto="+prd.getId()+"&operacao=VISUALIZAR'><div class='product-thumb'>");//3
					if(prd.getQtd_estoque()==0){
					sb.append("<div class='product-label'>");
					sb.append("<span>Esgotado</span>");
					sb.append("</div>");
					}
					sb.append("<img src='"+prd.getImg_produto()+"'>");
					sb.append("</div></a>");//3
						sb.append("	<div class='product-body'>");//4
							sb.append("	<h3 class='product-price'>R"+String.format("$%,.2f", prd.getValor_produto())+" </h3>");
								sb.append("	<div class='product-rating'>");//5
									sb.append("<i class='fa fa-star'></i>");
										sb.append("	<i class='fa fa-star'></i>");
										sb.append("	<i class='fa fa-star'></i>");
										sb.append("	<i class='fa fa-star'></i>");
								sb.append("<i class='fa fa-star-o empty'></i>");
								sb.append("</div>");//5
								sb.append("<h2 class='product-name'><a href='#'>"+prd.getNome()+" | "+prd.getCategoria().getNome()+"</a></h2>");
								sb.append("<div class='product-btns'>");//6
								sb.append("<form action='Pedidos' method='POST'>");
								sb.append("<input type='hidden' name='id-produto' value='"+prd.getId()+"'>");
								sb.append("<input type='hidden' name='nome-produto' value='"+prd.getNome()+"'>");
								sb.append("<input type='hidden' name='valor-produto' value='"+prd.getValor_produto()+"'>");
								sb.append("<input type='hidden' name='qtd' value='1'>");
								if(prd.getQtd_estoque()==0){
									sb.append("<button type='button' style='background-color:red' name='operacao' value='ADDCARRINHO' class='primary-btn add-to-cart'><i class='fas fa-shipping-fast'></i>  Produto esgotado</button>");
								} else {
									if(erro==1){
										sb.append("<button type='button' onclick='Carrinho()' name='operacao' value='ADDCARRINHO' class='primary-btn add-to-cart'><i class='fa fa-shopping-cart'></i> Adicionar ao carrinho</button>");
									} else {
										sb.append("<button type='submit' onclick='Carrinho()' name='operacao' value='ADDCARRINHO' class='primary-btn add-to-cart'><i class='fa fa-shopping-cart'></i> Adicionar ao carrinho</button>");
									}
								}
								sb.append("</form>");
								sb.append("</div>");//6
							sb.append("	</div>");//4
						sb.append("</div>");
						sb.append("</div>");
						i++;
							if(i==4){
								sb.append("</div>");
								i=0;
							}
						}
					}
				}
				out.print(sb.toString());
			%>
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
	
	 <script type="text/javascript">
   		 function Carrinho () {
    	// do the work after everything was loaded (DOM, media elements)
    	var value = "<%=erro%>";
        if(value==="1"){
        alert("Para adicionar itens no carrinho, você precisa estar logado!");
        	}
    }
</script>
	
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/slick.min.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/jquery.zoom.min.js"></script>
	<script src="js/main.js"></script>

</body>

</html>
