<%@page import="LES1_2019.core.aplicacao.Resultado"%>
<%@page import="LES1_2019.dominio.*"%>
<%@page import="LES1_2019.dominio.Produto"%>
<%@page import="LES1_2019.auxiliar.DadosDeCadastro"%>
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

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

	<link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">

</head>

<body>
		<%
		
		Resultado ResultadoProdutos = (Resultado) session.getAttribute("produtos");
		Resultado ResultadoDados = (Resultado) session.getAttribute("dados");
		
		int id = Integer.parseInt(request.getParameter("idprd"));
		
		List<EntidadeDominio> dados = ResultadoDados.getEntidades();
		List<EntidadeDominio> produtos = ResultadoProdutos.getEntidades();
		Produto prd;
		
		DadosDeCadastro itens = (DadosDeCadastro)dados.get(0);
		StringBuilder sb;
	
		%>
	<!-- HEADER -->
	<%
	sb = new StringBuilder();
	for(EntidadeDominio ent : produtos){
		prd = (Produto)ent;
		sb.append(prd.getNome());
	}
	%>
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
						<!-- Account -->
						<li class="header-account dropdown default-dropdown">
							<div class="dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="true">
								<div class="header-btns-icon">
									<i class="fa fa-user-o"></i>
								</div>
								<strong class="text-uppercase">Olá, Administrador <i class="fa fa-caret-down"></i></strong>
							</div>
							<a href="#" class="text-uppercase">Sair</a>
							<ul class="custom-menu">
								<li><a href="#"><i class="fa fa-user-o"></i> Minha conta</a></li>
							</ul>
						</li>
						<!-- /Account -->

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
									<li><a href="MostrarProduto?operacao=CONSULTAR-PRODUTOS">Visualizar produtos</a></li>
									<li><a href="DadosProduto?operacao=CONSULTAR-PRODUTOS">Cadastrar produtos</a></li>
								</ul>
							</li>
							<li class="dropdown default-dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">Categorias <i class="fa fa-caret-down"></i></a>
								<ul class="custom-menu">
									<li><a href="products.html">Visualizar categorias</a></li>
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
		<div class="container" style="text-align: center">
			<!-- row -->
			<!-- dashboard admin -->
			<div style="text-align: center">
				<form id="form" action="SalvarProduto" method="POST" class="form-horizontal">
					<section class="panel">
						<header class="panel-heading" style="padding-right:200px">
						<div class="col-sm-1">
								<a href="MostrarProduto?operacao=CONSULTAR-PRODUTOS" style="background-color:grey; color:white" class="btn btn-default">Voltar para a listagem</a>
							</div>
							<h2 class="panel-title">Digite os dados do produto</h2>
							<p class="panel-subtitle">
								Todos os dados abaixo devem ser preenchidos para cadastrar um produto!.
							</p>
						</header>
						<div class="panel-body" style="padding-right:100px">
							<div class="form-group">
								<label class="col-sm-3 control-label">Nome do produto <span class="required">*</span></label>
								<div class="col-sm-6">
								<%
								sb = new StringBuilder();
								for(EntidadeDominio ent : produtos){
									prd = (Produto)ent;
								if(prd.getId()==id){
									sb.append("<input id='nome' type='text' name='nome-produto' value='"+prd.getNome());
									sb.append(" 'class='col-sm-3 form-control' ");
									sb.append("required oninvalid='this.setCustomValidity");
									sb.append("('O campo nome é obrigatório!') oninput='setCustomValidity('')/>");
									}
								}
								out.print(sb.toString());
								%>
									
								</div>
							</div>
							<div class="form-group" style="margin-bottom:5px">
								<label class="col-sm-3 control-label">Categoria do produto<span class="required">*</span></label>
								<div class="col-sm-4">
								<select name="categoria-produto" id="categoria" class="form-control" required oninvalid="this.setCustomValidity('O campo categoria é obrigatorio!')" oninput="setCustomValidity('')"/>
										<option value="">Selecione uma categoria</option>
								<% 
									sb = new StringBuilder();
									for(Categoria cat : itens.getCategoria()){
								
										sb.append("<option value='"+ cat.getId()+"'>");
										sb.append(cat.getNome()+"</option>");
									}
									out.print(sb.toString());
								%>
									</select>
									<label class="error" for="categoria"></label>
								</div>
							</div>
							<div class="form-group" style="margin-bottom:4px">
								<label class="col-sm-3 control-label">Marca do produto<span class="required">*</span></label>
								<div class="col-sm-4">
									<select name="marca-produto" id="marca" class="form-control" required oninvalid="this.setCustomValidity('O campo marca é obrigatorio!')" oninput="setCustomValidity('')"/>
										<option value="">Selecione uma marca</option>
								<% 
										sb = new StringBuilder();
										for(Marca m : itens.getMarcas()){
								
										sb.append("<option value='"+ m.getId()+"'>");
										sb.append(m.getNome_marca()+"</option>");
									}
									out.print(sb.toString());
								%>
									</select>
									<label class="error" for="marca"></label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">Valor do produto em reais <span class="required">*</span></label>
								<div class="col-sm-4">
									<%
								sb = new StringBuilder();
								for(EntidadeDominio ent : produtos){
									prd = (Produto)ent;
								if(prd.getId()==id){
									sb.append("<input id='valor' type='text' name='valor-produto' value='"+prd.getValor_produto());
									sb.append(" 'class='col-sm-3 form-control' placeholder='Apenas numeros...'");
									sb.append("required oninvalid='this.setCustomValidity");
									sb.append("('O campo valor é obrigatório!') oninput='setCustomValidity('')/>");
									}
								}
								out.print(sb.toString());
								%>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">Descricao do produto <span class="required">*</span></label>
								<div class="col-sm-9">
									<%
								sb = new StringBuilder();
								for(EntidadeDominio ent : produtos){
									prd = (Produto)ent;
								if(prd.getId()==id){
									sb.append("<textarea id='descricao' type='text' name='descricao-produto' value='"+prd.getDescricao_produto());
									sb.append("' rows='5' class='form-control' ");
									sb.append("required oninvalid='this.setCustomValidity");
									sb.append("('O campo descricao é obrigatório!') oninput='setCustomValidity('')></textarea>");
									}
								}
								out.print(sb.toString());
								%>
								</div>
							</div>										
						</div>
						<footer class="panel-footer">
							<div class="row" style="padding-left:100px">
								<div class="col-sm-9 col-sm-offset-3">
									<button type="submit" name="operacao" value="SALVAR-PRODUTO" class="btn btn-primary">Alterar</button>
									<button style="background-color:grey; color:white" type="reset" class="btn btn-default">Resetar campos</button>
								</div>
							</div>
						</footer>
					</section>
				</form>
			</div>
			<!-- dashboard admin -->
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /section -->

	<!-- jQuery Plugins -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/slick.min.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/jquery.zoom.min.js"></script>
	<script src="js/main.js"></script>

</body>

</html>
