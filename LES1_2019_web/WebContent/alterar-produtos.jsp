<!doctype html>
<html class="fixed">
	<head>
	<style></style>
		<!-- Basic -->
		<meta charset="UTF-8">

		<title>Gerenciar produtos</title>
		<meta name="keywords" content="HTML5 Admin Template" />
		<meta name="description" content="Porto Admin - Responsive HTML5 Template">
		<meta name="author" content="okler.net">

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

		<!-- Web Fonts  -->
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

		<!-- Vendor CSS -->
		<link rel="stylesheet" type="text/css" href="./assets/vendor/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="./assets/vendor/font-awesome/css/font-awesome.css" />
		<link rel="stylesheet" type="text/css" href="./assets/vendor/magnific-popup/magnific-popup.css" />
		<link rel="stylesheet" type="text/css" href="./assets/vendor/bootstrap-datepicker/css/datepicker3.css" />

		<!-- Specific Page Vendor CSS -->
		<link rel="stylesheet" href="./assets/vendor/select2/select2.css" />
		<link rel="stylesheet" href="./assets/vendor/jquery-datatables-bs3/assets/css/datatables.css" />

		<!-- Theme CSS -->
		<link rel="stylesheet" href="./assets/stylesheets/theme.css" />

		<!-- Skin CSS -->
		<link rel="stylesheet" href="./assets/stylesheets/skins/default.css" />

		<!-- Theme Custom CSS -->
		<link rel="stylesheet" href="./assets/stylesheets/theme-custom.css">

		<!-- Head Libs -->
		<script src="assets/vendor/modernizr/modernizr.js"></script>

	</head>
	<body>
		<section class="body">

			<!-- start: header -->
			<header class="header">
				<div class="logo-container">
					<a href="../" class="logo">
						<img src="assets/images/logo.png" style="padding-left:20px" height="35" alt="Porto Admin" />
					</a>
					<div class="visible-xs toggle-sidebar-left" data-toggle-class="sidebar-left-opened" data-target="html" data-fire-event="sidebar-left-opened">
						<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
					</div>
				</div>
			
				<!-- start: search & user box -->
				<div class="header-right">
					
					<span class="separator"></span>
			
					<div id="userbox" class="userbox">
						<a href="#" data-toggle="dropdown">
							<figure class="profile-picture">
								<img src="assets/images/!logged-user.jpg" alt="Joseph Doe" class="img-circle" data-lock-picture="assets/images/!logged-user.jpg" />
							</figure>
							<div class="profile-info">
								<span class="name">Administrador</span>
								<span class="role"></span>
							</div>
			
							<i class="fa custom-caret"></i>
						</a>
			
						<div class="dropdown-menu">
							<ul class="list-unstyled">
								<li class="divider"></li>
								<li>
									<a role="menuitem" tabindex="-1" href="pages-user-profile.html"><i class="fa fa-user"></i> My Profile</a>
								</li>
								<li>
									<a role="menuitem" tabindex="-1" href="pages-signin.html"><i class="fa fa-power-off"></i> Logout</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- end: search & user box -->
			</header>
			<!-- end: header -->

			<div class="inner-wrapper">
				<!-- start: sidebar -->
				<aside id="sidebar-left" class="sidebar-left">
				
					<div class="sidebar-header">
						<div class="sidebar-title" style="color:#ddd; font-size:20px">
							Navegação
						</div>
						<div class="sidebar-toggle hidden-xs" data-toggle-class="sidebar-left-collapsed" data-target="html" data-fire-event="sidebar-left-toggle">
							<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
						</div>
					</div>
				
					<div class="nano">
						<div class="nano-content">
							<nav id="menu" class="nav-main" role="navigation">
								<ul class="nav nav-main">
									<li>
										<a href="index.html">
											<i class="fa fa-home" aria-hidden="true"></i>
											<span>Página principal</span>
										</a>
									</li>
									
									<li class="nav-parent">
										<a>
											<i class="fa fa-copy" aria-hidden="true"></i>
											<span>Pages</span>
										</a>
										<ul class="nav nav-children">
											<li>
												<a href="pages-blank.html">
													 Blank Page
												</a>
											</li>
										</ul>
									</li>
									<li class="nav-parent">
										<a>
											<i class="fa fa-tasks" aria-hidden="true"></i>
											<span>UI Elements</span>
										</a>
										<ul class="nav nav-children">
											<li>
												<a href="ui-elements-buttons.html">
													 Buttons
												</a>
											</li>
										</ul>
									</li>
									<li class="nav-parent">
										<a>
											<i class="fa fa-list-alt" aria-hidden="true"></i>
											<span>Forms</span>
										</a>
										<ul class="nav nav-children">
											<li>
												<a href="forms-basic.html">
													 Basic
												</a>
											</li>
											<li>
												<a href="forms-advanced.html">
													 Advanced
												</a>
											</li>
											<li>
												<a href="forms-validation.html">
													 Validation
												</a>
											</li>
											<li>
												<a href="forms-layouts.html">
													 Layouts
												</a>
											</li>
											<li>
												<a href="forms-wizard.html">
													 Wizard
												</a>
											</li>
											<li>
												<a href="forms-code-editor.html">
													 Code Editor
												</a>
											</li>
										</ul>
									</li>
									<li class="nav-parent nav-expanded nav-active">
										<a>
											<i class="fa fa-table" aria-hidden="true"></i>
											<span>Gerenciar produtos</span>
										</a>
										<ul class="nav nav-children">
											<li class="nav-active">
												<a href="mostrar-produtos.html">
													 Visualizar produtos
												</a>
											</li>
											<li class="nav-active">
												<a href="cadastrar-produtos.html">
													 Cadastrar produtos
												</a>
											</li>
										</ul>
									</li>
								</ul>
							</nav>
						</div>
				
					</div>
				
				</aside>
				<!-- end: sidebar -->

				<section role="main" class="content-body">
					<header class="page-header">
						<h2>Alterar produto</h2>
					</header>

					<!-- start: page -->
						<section class="panel">
							<div class="panel-body">
								
							<form id="form" action="forms-validation.html" class="form-horizontal">
								<section class="panel">
									<header class="panel-heading">
										<h2 class="panel-title">Digite os dados que deseja alterar</h2>
										<p class="panel-subtitle">
											Todos os dados abaixo devem ser preenchidos para alterar um produto!
										</p>
									</header>
									<div class="panel-body" style="padding-right:200px">
										<div class="form-group">
											<label class="col-sm-3 control-label">Nome do produto <span class="required">*</span></label>
											<div class="col-sm-6">
												<input id="id-nome-produto" type="text" name="nome-produto" class="col-sm-3 form-control"
												required oninvalid="this.setCustomValidity('O campo nome é obrigatório!')" oninput="setCustomValidity('')"/>
											</div>
										</div>
										<div class="form-group" style="margin-bottom:5px">
											<label class="col-sm-3 control-label">Categoria do produto<span class="required">*</span></label>
											<div class="col-sm-4">
												<select name="categoria-produto" id="categoria" class="form-control" required oninvalid="this.setCustomValidity('O campo nome é obrigatório!')" oninput="setCustomValidity('')"/>
													<option value="">Selecione uma categoria</option>
													<option value="Celulares">Celulares</option>
													<option value="Headsets">Headsets</option>
													<option value="Computadores">Computadores</option>
													<option value="Pinto">Pinto</option>
												</select>
												<label class="error" for="categoria"></label>
											</div>
										</div>
										<div class="form-group" style="margin-bottom:4px">
											<label class="col-sm-3 control-label">Marca do produto<span class="required">*</span></label>
											<div class="col-sm-4">
												<select name="marca-produto" id="marca" class="form-control" required oninvalid="this.setCustomValidity('O campo nome é obrigatório!')" oninput="setCustomValidity('')"/>
													<option value="">Selecione uma marca</option>
													<option value="apple">Epou</option>
													<option value="samsung">Samsung</option>
													<option value="positivo">Positivo</option>
													<option value="pinto">Pinto</option>
												</select>
												<label class="error" for="marca"></label>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">Valor do produto em reais <span class="required">*</span></label>
											<div class="col-sm-4">
												<input type="text" name="valor-produto" placeholder="Apenas números..." class="col-sm-3 form-control" required oninvalid="this.setCustomValidity('O campo nome é obrigatório!')" oninput="setCustomValidity('')"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">Descricao do produto <span class="required">*</span></label>
											<div class="col-sm-9">
												<textarea name="descricao-produto" rows="5" class="form-control" required oninvalid="this.setCustomValidity('O campo nome é obrigatório!')" oninput="setCustomValidity('')"/></textarea>
											</div>
										</div>										
									</div>
									<footer class="panel-footer">
										<div class="row" style="padding-left:100px">
											<div class="col-sm-9 col-sm-offset-3">
												<button class="btn btn-primary">Cadastrar produto</button>
												<button type="reset" class="btn btn-default">Resetar campos</button>
											</div>
										</div>
									</footer>
								</section>
							</form>
								<div class="col-sm-3" style="padding-right:130px">
									<button onclick="window.location='mostrar-produtos.html'" class="btn btn-default">Voltar</button>
								</div>
						</div>
						</section>
					<!-- end: page -->
				</section>
			</div>
		</section>
		
		<!-- Vendor -->
		<script src="assets/vendor/jquery/jquery.js"></script>
		<script src="assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
		<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
		<script src="assets/vendor/nanoscroller/nanoscroller.js"></script>
		<script src="assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
		<script src="assets/vendor/magnific-popup/magnific-popup.js"></script>
		<script src="assets/vendor/jquery-placeholder/jquery.placeholder.js"></script>
		
		<!-- Specific Page Vendor -->
		<script src="assets/vendor/select2/select2.js"></script>
		<script src="assets/vendor/jquery-datatables/media/js/jquery.dataTables.js"></script>
		<script src="assets/vendor/jquery-datatables-bs3/assets/js/datatables.js"></script>
		
		<!-- Theme Base, Components and Settings -->
		<script src="assets/javascripts/theme.js"></script>
		
		<!-- Theme Custom -->
		<script src="assets/javascripts/theme.custom.js"></script>
		
		<!-- Theme Initialization Files -->
		<script src="assets/javascripts/theme.init.js"></script>


		<!-- Examples -->
		<script src="assets/javascripts/tables/examples.datatables.editable.js"></script>
	</body>
</html>