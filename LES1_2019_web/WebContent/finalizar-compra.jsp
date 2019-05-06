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

String erro="";
try{
	Resultado resultProdutos = (Resultado)session.getAttribute("erro");
	erro = resultProdutos.getMensagem().trim();
	session.removeAttribute("erro");
	} catch (Exception e){
		
	}

//carrinho
Pedido carrinho = new Pedido();
try{
	carrinho = (Pedido) session.getAttribute("carrinho");
}catch(Exception e){
	
}
int t = 0;
double valor = 0;

if(session.getAttribute("carrinho") != null){
	Pedido ped = (Pedido)session.getAttribute("carrinho");
	t = ped.getProdutos().size();
	valor = carrinho.getValor_total();
}
else{
	t = 0;
	String redirectURL = "Pedidos?operacao=VER-CARRINHO";
	pageContext.forward(redirectURL);
}
Pedido ped = (Pedido)session.getAttribute("carrinho");
User usr;

//login
	int error = 0;
	Resultado login = (Resultado) session.getAttribute("login");
	try{
		List<EntidadeDominio> user = login.getEntidades();
		} catch (Exception e){
			error = 1;
	}

	
	String nomeUser = "";
	
	List<EntidadeDominio> user = login.getEntidades();
	User us = (User)user.get(0);
	nomeUser = us.getNome_usuario();
	
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


	<!-- section -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!--  Product Details -->
				<div class="product product-details clearfix">
					<div style="display:flex; justify-content:space-around">
					
						<div class='col-md-10'style='height:20px'>
							<div class='billing-details'>
								<div class='section-title'>
									<h3 class='title'>Resumo do pedido</h3>
								</div>
							</div>
						</div>
						<div style="padding-top:5px"><a href="Produtos?operacao=INDEX" class="primary-btn">Voltar às compras!</a></div>
					</div>
						<div class='col-md-12'>
							<div class='billing-details'>
					<%
					if(error==1){
						out.print("<br><br><br><div style='display:flex; justify-content:space-around'>");
						out.print("<img height='200px' src='https://www.compraserrana.com.br/shopping/img/carrinho_vazio.png'>");
						out.print("</div><br><br>");
						
						out.print("<div style='display:flex; justify-content:space-around'>");
						out.print("<p><strong>Seu carrinho está vazio</strong></p>");
						out.print("</div>");
					}else{
						if(t==0){
							out.print("<br><br><br><div style='display:flex; justify-content:space-around'>");
							out.print("<img height='200px' src='https://www.compraserrana.com.br/shopping/img/carrinho_vazio.png'>");
							out.print("</div><br><br>");
							
							out.print("<div style='display:flex; justify-content:space-around'>");
							out.print("<p><strong>Seu carrinho está vazio</strong></p>");
							out.print("</div>");
						}else{
							out.print("<br><br>");
							out.print("<table class='shopping-cart-table table'>");
							out.print("<thead>");
							out.print("<th>Produto");
							out.print("</th>");
							out.print("<th>Preço");
							out.print("</th>");
							out.print("<th>Quantidade");
							out.print("</th>");
							out.print("<th>Subtotal");
							out.print("</th>");
							out.print("</thead>");
							for(Produto prod:ped.getProdutos()){
								out.print("<tr>");
								out.print("<td class='details'><a href='Produtos?id-produto="+prod.getId()+"&operacao=VISUALIZAR'>"+prod.getNome()+"</a></td>");
								out.print("<td style='font-size:15px'><strong>R"+String.format("$%,.2f", prod.getValor_produto())+"</strong></td>");
								out.print("<td style='font-size:15px'><strong>"+prod.getQtdtotal()+"</strong></td>");
								out.print("<td style='font-size:15px'><strong class='primary-color'>R"+String.format("$%,.2f", prod.getSubtotal())+"</strong></td>");
								out.print("</tr>");
							}
							out.print("</table>");
							out.print("<div class='col-md-6 col-md-offset-3'>");
							out.print("<div style='text-align: center'>"+
										"<table>"+
										"<thead>"+
										"<th>Subtotal do pedido</th>"+
										"<th></th>"+
										"<th>Valor da entrega</th>"+
										"<th></th>"+
										"<th>Valor total</th>"+
										"</thead>"+
									"</div>");
							out.print("<tr>");
							out.print("<td style='font-size:15px'><strong>R"+String.format("$%,.2f", ped.getValor_total())+"</strong></td>");
							out.print("<td style='font-size:15px'><strong>+</strong></td>");
							out.print("<td style='font-size:15px'><strong>Gratis!</strong></td>");
							out.print("<td style='font-size:15px'><strong>=</strong></td>");
							out.print("<td style='font-size:15px'><strong>R"+String.format("$%,.2f", ped.getValor_total())+"</strong></td>");
							out.print("</tr>");
							out.print("</table>");
						out.print("</div>");
						out.print("<br><br>");
						}
					}
					%>
						</div>
					</div>
					<form action="Pedidos" method="POST">
					<div class='col-md-12'>
							<div class='billing-details'>
								<div class='section-title'>
									<h3 class='title'>Forma de pagamento</h3>
								</div>
									<p><strong>Abaixo, selecione os cartões que deseja usar para efetuar o pagamento, e digite o valor pago por cartão.</strong></p>
							</div>
							<%
							sb = new StringBuilder();
							if(!us.getCartoes().isEmpty()){
							out.print("<br>");
							out.print("<div class='col-md-6 col-md-offset-3'>");
							sb.append("<table>");
							sb.append("<thead>");
							sb.append("<th>Numero Cartao</th>");
							sb.append("<th>Usar cartao nesta compra</th>");
							sb.append("<th>Valor</th>");
							sb.append("</thead>");
							int i=1;
							String num="";
							for(Cartao card:us.getCartoes()){
							sb.append("<tr>");
							sb.append("<td ");
							sb.append("style='letter-spacing:2px'><strong>************"+card.getNumero_cartao().substring(12)+"</strong>");
							if(card.getId_bandeira_cartao()==1){
								sb.append("<i id='icone-cartao' class='fab fa-cc-mastercard fa-2x'></i>");
							} else if(card.getId_bandeira_cartao()==2){
								sb.append("<i id='icone-cartao' class='fab fa-cc-visa fa-2x'></i>");
							} else if(card.getId_bandeira_cartao()==3){
								sb.append("<i id='icone-cartao' class='fab fa-cc-diners-club fa-2x'></i>");
							} else if(card.getId_bandeira_cartao()==4){
								sb.append("<i id='icone-cartao' class='fab fa-cc-jcb fa-2x'></i>");
							} else if(card.getId_bandeira_cartao()==5){
								sb.append("<i id='icone-cartao' class='fab fa-cc-discover fa-2x'></i>");
							} else if(card.getId_bandeira_cartao()==6){
								sb.append("<i id='icone-cartao' class='fab fa-cc-amex fa-2x'></i>");
							}
							sb.append("</td>");
							sb.append("<td><input type='checkbox' value='sim' name='usarcartao"+i+"'></td>");
							sb.append("<td><strong>R$   </strong><input style='width:100px' placeholder='100,00'class='input' type='text' name='valor-cartao"+i+"'></td>");
							sb.append("</tr>");
							i++;
							}
							sb.append("</table>");
							sb.append("</div>");
							out.print(sb.toString());
							} else {
								out.print("<br>");
								out.print("<div class='col-md-6 col-md-offset-3'>");
								out.print("<div style='text-align:center'>");
								out.print("<p style='font-size:15px'>Voce nao possui nenhum cartao cadastrado!</p>");
								out.print("<p style='font-size:15px'>Para continuar com seu pedido, cadastre ao menos 1 cartão</p>");
								out.print("<div><a class='primary-btn' href='Cartoes?idusr="+us.getId()+"&operacao=CONSULTAR'>Cadastrar cartão</a></div>");
								out.print("</div>");
								out.print("</div>");
							}
							%>
					</div>
					<br><br>
					<div style="padding-top:50px" class='col-md-12'>
							<div class='billing-details'>
								<div class='section-title'>
									<h3 class='title'>Dados de endereço</h3>
								</div>
									<p><strong>Verifique corretamente os dados de endereço.</strong></p>
							</div>
							<%
							sb = new StringBuilder();
							out.print("<br>");
							sb.append("<table>");
							sb.append("<thead>");
							sb.append("<th>Rua</th>");
							sb.append("<th>Bairro</th>");
							sb.append("<th>Cidade</th>");
							sb.append("<th>Numero</th>");
							sb.append("<th>Tipo do endereço</th>");
							sb.append("<th>CEP</th>");
							sb.append("<th>Estado</th>");
							sb.append("</thead>");
							int i=0;
							String num="";
							
							for(Endereco end:us.getEnderecos()){
							sb.append("<tr>");
							sb.append("<td style='letter-spacing:1px'>"+end.getRua()+"</td>");
							sb.append("<td style='letter-spacing:1px'>"+end.getBairro()+"</td>");
							sb.append("<td style='letter-spacing:1px'>"+end.getCidade()+"</td>");
							sb.append("<td style='letter-spacing:1px'>"+end.getNumero()+"</td>");
								if(us.getEnderecos().size()==1){
									sb.append("<td style='letter-spacing:1px'><strong>Cobrança e entrega</strong></td>");
								}else{
									if(i==0){
										sb.append("<td style='letter-spacing:1px'><strong>Cobrança</strong></td>");
									}else{
										sb.append("<td style='letter-spacing:1px'><strong>Entrega</strong></td>");
									}
								}
							sb.append("<td style='letter-spacing:1px'>"+end.getCep()+"</td>");
							sb.append("<td style='letter-spacing:1px'>"+end.getEstado()+"</td>");
							sb.append("</tr>");
							i++;
							}
							sb.append("</table><br><br>");
							
							sb.append("<div style='text-align:center'><button class='primary-btn' type='submit' name='operacao' value='FAZER-PEDIDO'>Confirmar pedido</button></div>");
							out.print(sb.toString());
							%>
						</div>
					</form>
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
	
	 <script type="text/javascript">
   		 function Carrinho () {
    	// do the work after everything was loaded (DOM, media elements)
    	var value = "<%=erro%>";
        if(value==="1"){
        alert("Para adicionar itens no carrinho, você precisa estar logado!");
        	}
    }
</script>

<script type="text/javascript">
   		 window.onload = function () {
    	// do the work after everything was loaded (DOM, media elements)
    	var value = "<%=erro%>";
        if(value!=""){
        alert(value);
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
