<%@page import="LES1_2019.core.aplicacao.Resultado"%>
<%@page import="LES1_2019.dominio.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html lang="pt">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>Dashboard Administrador</title>

	
	<!-- Google font -->
	<link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet"/>

	<!-- Bootstrap -->
	<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

	<!-- Slick -->
	<link type="text/css" rel="stylesheet" href="css/slick.css" />
	<link type="text/css" rel="stylesheet" href="css/slick-theme.css" />

	<!-- nouislider -->
	<link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />

	<!-- Font Awesome Icon -->
	<link rel="stylesheet" href="css/font-awesome.min.css"/>

	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="css/style.css" />

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

	<link rel="stylesheet" href="geral.css" type="text/css"/>
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

</head>

<body>

		<%
	String erro = "";
	//recebendo dados de pedidos
	
	int t = 0;
	double valor = 0;
	
	Resultado resultado = (Resultado) session.getAttribute("dadosPedido");
	
	if(session.getAttribute("dadosPedido") != null){
		List<EntidadeDominio> pedidos = resultado.getEntidades();
	}else{
		pageContext.forward("User?operacao=CONSULTAR");
	}
	
	List<EntidadeDominio> pedidos = resultado.getEntidades();
	//dados de login
	int error = 0;
	Resultado login = (Resultado) session.getAttribute("login");
	if(session.getAttribute("login")==null){
		pageContext.forward("index.jsp");
	}
	try{
		List<EntidadeDominio> user = login.getEntidades();
		} catch (Exception e){
			error = 1;
	}
	String nomeUser = "";
	int id = 0;
	if(error==0){
	List<EntidadeDominio> user = login.getEntidades();
	User us = (User)user.get(0);
	nomeUser = us.getNome_usuario();
	id = us.getId();
	}
	StringBuilder sb = new StringBuilder();
	
	Pedido ped;
	
	for(EntidadeDominio ent:pedidos){
		ped = (Pedido)ent;
		t = pedidos.size();
		
	}
		%>

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
				</div>
				<div class="pull-right">
					<ul class="header-btns">
						<jsp:include page="minha-conta.jsp" />

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
				<!-- menu nav -->
				<div class="menu-nav">
					<span class="menu-header">Menu Administrador <i class="fa fa-bars"></i></span>
					<ul class="menu-list">
							<li>
								<a href="dashboard-admin.jsp">Pagina Principal</a>
							</li>
							<li class="dropdown default-dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">Produtos <i class="fa fa-caret-down"></i></a>
								<ul class="custom-menu">
									<li><a href="Produtos?operacao=CONSULTAR">Visualizar produtos</a></li>
									<li><a href="DadosProduto?operacao=CONSULTAR&editar=0">Cadastrar produtos</a></li>
								</ul>
							</li>
							<li class="dropdown default-dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">Categorias <i class="fa fa-caret-down"></i></a>
								<ul class="custom-menu">
									<li><a href="products.html">Visualizar categorias</a></li>
								</ul>
							</li>
							<li class="dropdown default-dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">Pedidos <i class="fa fa-caret-down"></i></a>
								<ul class="custom-menu">
									<li><a href="Pedidos?operacao=CONSULTAR-ADMIN">Visualizar pedidos</a></li>
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

			<!-- dashboard admin -->
			<div style="text-align: center">
				<div class="container">
				<div class="row">
					<div class="table-wrapper">
						<div class="table-title">
							<div class="row">
								<div class="col-sm-3">
									<h2>Todos os pedidos</h2>
								</div>
								
							</div>
						</div>
						<div class="col-sm-12">
							<% 
							if(t==0){
								out.print("<br><br><br><div style='display:flex; justify-content:space-around'>");
								out.print("<img height='200px' src='https://image.flaticon.com/icons/png/512/18/18296.png'>");
								out.print("</div><br><br>");
								
								out.print("<div style='display:flex; justify-content:space-around'>");
								out.print("<p><strong>Opa... Você não tem nenhum pedido ainda...</strong></p>");
								out.print("</div>");
							}else{
								out.print("<table class='table table-striped table-hover'>");
								out.print("<thead>");
								out.print("<th style='font-size: 14px'>#ID");
								out.print("</th>");
								out.print("<th style='font-size: 14px'>Produtos");
								out.print("</th>");
								out.print("<th style='font-size: 14px; width:10%'>Quantidade");
								out.print("</th>");
								out.print("<th style='font-size: 14px'>Subtotal");
								out.print("</th>");
								out.print("<th style='font-size: 14px'>Forma de pagamento");
								out.print("</th>");
								out.print("<th style='font-size: 14px'>Total");
								out.print("</th>");
								out.print("<th style='font-size: 14px'>Modificar status");
								out.print("</th>");
								out.print("</thead>");
								Pedido ped1;
							
								for(EntidadeDominio ent:pedidos){
									ped = (Pedido)ent;
									
									out.print("<tr>");
									out.print("<td>"+ped.getId()+"</td>");
									out.print("<td style='font-size:14px'>");
									for(Produto prod:ped.getProdutos()){
										if(prod.getPedido()==ped.getId()){
											out.print(prod.getNome()+"<br>");
										}
									}
									out.print("</td>");
									out.print("<td>");
									for(Produto prod:ped.getProdutos()){
										out.print(prod.getQtd_pedido()+"<br>");
									}
									out.print("</td>");
									
									out.print("<td>");
									for(Produto prod:ped.getProdutos()){
										if(prod.getPedido()==ped.getId()){
										out.print("R"+String.format("$%,.2f", prod.getSubtotal())+"<br>");
										}
									}
									out.print("</td>");
									int i=0;
									out.print("<td style='letter-spacing:1px'>");
									for(Cartao card:ped.getCartoes()){
										if(card.getPedido()==ped.getId()){
											
											i++;
										}
									}
									if(i==1){
										out.print("Pagamento com 1 cartão");
									}
									else{out.print("Pagamento com 2 cartões");
									}
									out.print("</td>");
									
								out.print("<td>R"+String.format("$%,.2f", ped.getValor_total())+"</td>");
								if(ped.getStatus().equals("Pagamento em analise")){
								out.print("<td><div><form action='Pedidos' method='POST'>"+
								"<input type='hidden' name='id-pedido' value='"+ped.getId()+"'/>"+
								"<input type='hidden' name='status-pedido' value='"+ped.getStatus()+"'/>"+
								"<button style='background: none; border:none' type='submit' name='operacao' value='CONFIRMAR-PEDIDO'><i style='color:green' class='material-icons'>check_box</i></button>"+
								"</form></div>"+
								"<div><form action='Pedidos' method='POST'>"+
									"<input type='hidden' name='id-pedido' value='"+ped.getId()+"'/>"+
									"<button style='background: none; border:none' type='submit' name='operacao' value='NEGAR-PEDIDO'><i style='color:red' class='material-icons'>cancel</i></button>"+
								"</form></div>"+
								"</td>");
								}else if(ped.getStatus().equals("Pedido negado")){
									out.print("<td><strong>Pedido negado</strong></td>");
								}else{
								out.print("<td><form action='Pedidos' method='POST'>"+
								"<input type='hidden' name='id-pedido' value='"+ped.getId()+"'/>"+
								"<input type='hidden' name='status-pedido' value='"+ped.getStatus()+"'/>"+
								"<button class='btn btn-primary' type='submit' name='operacao' value='CONFIRMAR-ENTREGA'>Confirmar entrega</button>"+
								"</form>"+
								"</td>");
								}
									
									out.print("</tr>");
								
								
								}
								out.print("</table>");
							}
							
							%>
						</div>
					</div>
				</div>
			</div>
		</div>
			<!-- dashboard admin -->
			<!-- /row -->
		<!-- /container -->
	<!-- /section -->
	<!-- jQuery Plugins -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/slick.min.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/jquery.zoom.min.js"></script>
	<script src="js/main.js"></script>
	<script type="text/javascript">
	window.onload = function () {
	    	// do the work after everything was loaded (DOM, media elements)
	    	var value = "<%=erro%>";
	    	var i = 0;
	        if(value!=""){
	        alert(value);
	    	}
	}
	</script>
</body>

</html>
