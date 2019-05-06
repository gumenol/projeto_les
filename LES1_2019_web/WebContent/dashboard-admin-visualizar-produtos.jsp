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

	<link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">

	<link rel="stylesheet" href="geral.css" type="text/css"/>
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

</head>

<body>

		<%
		
		int id=0;
		int error = 0;
		User us;
		Resultado login = (Resultado) session.getAttribute("login");
		if(session.getAttribute("login")==null){
			pageContext.forward("index.jsp");
		}
		try{
			List<EntidadeDominio> user = login.getEntidades();
			} catch (Exception e){
				error = 1;
		}
		String nomeUser="";
		boolean admin = true;
		if(error==0){
			List<EntidadeDominio> user = login.getEntidades();
			us = (User)user.get(0);
			nomeUser = us.getNome_usuario();
			id = us.getId();
			admin = us.isAdmin();
			}
		if(!admin){
			pageContext.forward("index.jsp");
		}
			String erro = "";
			try{
			Resultado resultProdutos = (Resultado)session.getAttribute("produto");
			erro = resultProdutos.getMensagem().trim();
			session.removeAttribute("produto");
			} catch (Exception e){
				
			}
			
			Resultado resultado = (Resultado) session.getAttribute("produtos");
			List<EntidadeDominio> DadosProd = resultado.getEntidades();
			StringBuilder sb;
			Produto prd;
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
					<div class="table-wrapper">
						<div class="table-title">
							<div class="row">
								<div class="col-sm-3">
									<h2>Todos os produtos</h2>
								</div>
								<div class="col-sm-2">
									<a id="cadastrar" href="DadosProduto?operacao=CONSULTAR&editar=0" class="btn btn-success"><span>Cadastrar novo produto</span></a>					
								</div>
							</div>
						</div>
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th style="font-size: 14px">ID</th>
									<th style="font-size: 14px">Nome produto</th>
									<th style="font-size: 14px">Categoria produto</th>
									<th style="font-size: 14px">Marca produto</th>
									<th style="font-size: 14px">Descricao produto</th>
									<th style="font-size: 14px">Valor produto</th>
									<th style="font-size: 14px">Status produto</th>
									<th style="font-size: 14px">Qtd em estoque</th>
									<th style="font-size: 14px">A��es</th>
								</tr>
							</thead>
							<tbody>
							<% sb = new StringBuilder();
							for(EntidadeDominio ent : DadosProd){
								prd = (Produto)ent;
								sb = new StringBuilder();
								sb.append("<tr>");
									sb.append("<td>" + prd.getId()+"</td>");
									sb.append("<td>" + prd.getNome() + "</td>");
									sb.append("<td>" + prd.getCategoria().getNome() + "</td>");
									sb.append("<td>" + prd.getMarca_produto() + "</td>");
									sb.append("<td>" + prd.getDescricao_produto() + "</td>");
									sb.append("<td>" + "R$" + prd.getValor_produto() + "</td>");
									if(!prd.getStatus_produto()){
										sb.append("<td>" + "INATIVO" + "</td>");
									} else {
										sb.append("<td>" + "ATIVO" + "</td>");
									}
									sb.append("<td>" + prd.getQtd_estoque() + "</td>");
									sb.append("<td>");
									if(!prd.getStatus_produto()){
										sb.append("<a id='Ativar"+prd.getId()+"' href='Produtos?idprd="+prd.getId()+"&operacao=ATIVAR' title='Ativar' class='blue-text text-darken-2'><i class='material-icons dp48'>autorenew</i></a>");
									} else {
										sb.append("<a id='Excluir"+prd.getId()+"' href='Produtos?idprd="+prd.getId()+"&operacao=EXCLUIR' title='Deletar' class='delete'><i class='material-icons dp48'>clear</i></a>");
									}
									
	 								sb.append("<a id='Alterar"+prd.getId()+"' href='DadosProduto?idprd="+prd.getId()+"&operacao=CONSULTAR&editar=1' title='Editar' class='edit'><i class='material-icons'>&#xE254</i></a>");
									sb.append("</td>");
									sb.append("</tr>");
									out.print(sb.toString());
							}
							%>
							</tbody>
						</table>
					</div>
			</div>
		</div>
			<!-- dashboard admin -->
			<!-- /row -->
		<!-- /container -->
		</div>
	<!-- /section -->
</div>
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
