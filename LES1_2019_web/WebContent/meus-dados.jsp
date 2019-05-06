<%@page import="LES1_2019.core.aplicacao.Resultado"%>
<%@page import="LES1_2019.dominio.*"%>
<%@page import="LES1_2019.auxiliar.DadosDeCadastro"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html lang="pt">

<head>
<script>
	 function validarCPF(cpf) {	
			cpf = cpf.replace(/[^\d]+/g,'');
			var a = true;
			if(cpf == '') return false;	
			// Elimina CPFs invalidos conhecidos	
			if (cpf.length != 11 || 
				cpf == "00000000000" || 
				cpf == "11111111111" || 
				cpf == "22222222222" || 
				cpf == "33333333333" || 
				cpf == "44444444444" || 
				cpf == "55555555555" || 
				cpf == "66666666666" || 
				cpf == "77777777777" || 
				cpf == "88888888888" || 
				cpf == "99999999999"){
					a = false;
			}
			// Valida 1o digito	
			add = 0;	
			for (i=0; i < 9; i ++)		
				add += parseInt(cpf.charAt(i)) * (10 - i);	
				rev = 11 - (add % 11);	
				if (rev == 10 || rev == 11)		
					rev = 0;	
				if (rev != parseInt(cpf.charAt(9))){	
					a = false;		
				}
			// Valida 2o digito	
			add = 0;	
			for (i = 0; i < 10; i ++)		
				add += parseInt(cpf.charAt(i)) * (11 - i);	
			rev = 11 - (add % 11);	
			if (rev == 10 || rev == 11)	
				rev = 0;	
			if (rev != parseInt(cpf.charAt(10))){
				a = false;
			}
			if(!a)
			{
			alert("CPF invalido!");	
				}
		}
	 </script>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>Meus dados</title>

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
	
	<script>
	 function validarCPF(cpf) {	
			cpf = cpf.replace(/[^\d]+/g,'');
			var a = true;
			if(cpf == '') return false;	
			// Elimina CPFs invalidos conhecidos	
			if (cpf.length != 11 || 
				cpf == "00000000000" || 
				cpf == "11111111111" || 
				cpf == "22222222222" || 
				cpf == "33333333333" || 
				cpf == "44444444444" || 
				cpf == "55555555555" || 
				cpf == "66666666666" || 
				cpf == "77777777777" || 
				cpf == "88888888888" || 
				cpf == "99999999999"){
					a = false;
			}
			// Valida 1o digito	
			add = 0;	
			for (i=0; i < 9; i ++)		
				add += parseInt(cpf.charAt(i)) * (10 - i);	
				rev = 11 - (add % 11);	
				if (rev == 10 || rev == 11)		
					rev = 0;	
				if (rev != parseInt(cpf.charAt(9))){	
					a = false;		
				}
			// Valida 2o digito	
			add = 0;	
			for (i = 0; i < 10; i ++)		
				add += parseInt(cpf.charAt(i)) * (11 - i);	
			rev = 11 - (add % 11);	
			if (rev == 10 || rev == 11)	
				rev = 0;	
			if (rev != parseInt(cpf.charAt(10))){
				a = false;
			}
			if(!a)
			{
			alert("CPF invalido!");	
				}
		}
	 </script>
	

<%
	String email = "";
	String cpf = "";
	String telefone = "";
	String nomeUser = "";
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
	
	String erro = "";
	try{
	Resultado resultProdutos = (Resultado)session.getAttribute("erro");
	erro = resultProdutos.getMensagem().trim();
	session.removeAttribute("erro");
	} catch (Exception e){
		
	}
	
	Resultado dadosUser = (Resultado) session.getAttribute("dadosUser");
	List<EntidadeDominio> dadosUsuario = dadosUser.getEntidades();
	
	if(error==0){
	List<EntidadeDominio> user = login.getEntidades();
	us = (User)user.get(0);
	nomeUser = us.getNome_usuario();
	id = us.getId();
	}
	
	for(EntidadeDominio ent : dadosUsuario){
		us = (User)ent;
		if(us.getId() == id){
			email = us.getEmail_usuario();
			cpf = us.getCpf_usuario();
			telefone = us.getTelefone_usuario();
			break;
		}
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

	<!-- section -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!--  Product Details -->
				<div class="product product-details clearfix">
					<div class="col-md-6">
						<div class="billing-details">
							<div class="section-title">
								<h3 class="title">Seus dados</h3>
							</div>
							<div class="form-group">
								<p><strong>Nome:</strong></p>
								<input id="nome" class="input" type="text" name="nome" <%out.print(" value='"+nomeUser+"'"); %> required oninvalid="this.setCustomValidity('O campo nome é obrigatório!')" oninput="setCustomValidity('')"/>
							</div>
							<div class="form-group">
							<p><strong>Email:</strong></p>
								<input id="email" class="input" type="email" name="email" <%out.print(" value='"+email+"'"); %> required oninvalid="this.setCustomValidity('O campo é obrigatório!')" oninput="setCustomValidity('')"/>
							</div>
							<div class="form-group">
							<p><strong>CPF:</strong></p>
								<input id="cpf" class="input" type="text" onblur="validarCPF(this.value)" name="cpf" <%out.print(" value='"+cpf+"'"); %> required oninvalid="this.setCustomValidity('O campo é obrigatório!')" oninput="setCustomValidity('')"/>
							</div>
							<div class="form-group">
							<p><strong>Telefone:</strong></p>
								<input id="tel" class="input" type="text" name="tel" <%out.print(" value='"+telefone+"'"); %>  required oninvalid="this.setCustomValidity('O campo é obrigatório!')" oninput="setCustomValidity('')"/>
							</div>
							<div style="text-align:center" class="form-group">
									<button name="alter" class="primary-btn" onclick="PegarDados()" data-toggle="modal" data-target="#Alterar">Alterar dados</button>
							</div>
							<br><br>
							<div class="section-title">
								<h3 class="title">Senha</h3>
							</div>
							<div style="text-align:center" class="form-group">
								<button name="senha1" class="primary-btn" data-toggle="modal" data-target="#Senha">Alterar senha</button>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="billing-details">
							<div class="section-title">
								<h3 class="title">Seus Endereços</h3>
							</div><br>
							<div style="text-align:center" class="form-group">
								<a name="enderecos1" class="primary-btn" <%out.print("href='Enderecos?idusr="+id+"&operacao=CONSULTAR'");%>>Visualizar endereços cadastrados</a>
							</div>
							<br>
							<br>
							<div class="section-title">
								<h3 class="title">Seus Cartões</h3>
							</div>
							<div style="text-align:center" class="form-group">
								<a class="primary-btn" <%out.print("href='Cartoes?idusr="+id+"&operacao=CONSULTAR'");%>>Visualizar cartões cadastrados</a>
							</div>
							<br>
							<br>
							<div class="section-title">
								<h3 class="title">Seus pedidos</h3>
							</div>
							<div style="text-align:center" class="form-group">
								<a class="primary-btn" <%out.print("href='Pedidos?idusr="+id+"&operacao=CONSULTAR'");%>>Visualizar pedidos</a>
							</div>
							<br>
							<br>
							<div class="section-title">
								<h3 class="title">Seus Cupons</h3>
							</div>
							<div style="text-align:center" class="form-group">
								<a class="primary-btn" href="#">Visualizar cupons</a>
							</div>
						</div>
					</div>

				</div>
				<!-- /Product Details -->
			</div>
			<!-- /row -->
		</div><br><br>
		<div style="display:flex; justify-content:space-around">
		<button style="background-color:red" class="primary-btn" data-toggle="modal" data-target="#myModal">Excluir sua conta</button>				
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
			<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div style="background-color:white" class="col-md-6 col-md-offset-3">
		<div class="billing-details">
			<div class="section-title">
			<h3 class="title">Confirmar exclusão</h3>
       		</div>
          <p>Deseja mesmo excluir sua conta?</p>
           <p>Caso queira voltar a usar sua conta no futuro, deverá ativá-la novamente...</p>
           <br>
        <div class="form-group">
       		<a href="User?id-usuario=<%out.print(id); %>&operacao=EXCLUIR" class="primary-btn">Sim, quero excluir</a>
          <button style="background-color:gray" type="button" class="primary-btn" data-dismiss="modal">Fechar</button>
        </div>
      </div>
      </div>
    </div>
  </div>
  
   <div class="modal fade" id="Senha" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
     <div style="background-color:white" class="col-md-6 col-md-offset-3">
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
								<input class="input" type="password" name="senha-usuario" required oninvalid="this.setCustomValidity('O campo é obrigatório!')" oninput="setCustomValidity('')"/>
							</div>
							<div class="form-group">
							<p><strong>Senha nova:</strong></p>
								<input class="input" type="password" name="nova-senha" required oninvalid="this.setCustomValidity('O campo é obrigatório!')" oninput="setCustomValidity('')"/>
							</div>
							<div style="text-align:center" class="form-group">
								<button class="primary-btn" type="submit" name="operacao" value="ALTERAR-SENHA">Alterar senha</button>
								<button style="background-color:gray" type="button" class="primary-btn" data-dismiss="modal">Fechar</button>
							</div>
							</form>
						</div>
					</div>
      
    </div>
  </div>
  
  <div class="modal fade" id="Alterar" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
     <div style="background-color:white" class="col-md-6 col-md-offset-3">
		<div class="billing-details">
			<div class="section-title">
			<h3 class="title">Confirmar alteração</h3>
        </div>
        <form action="User" method="POST">
          <p>Confirme os dados a serem alterados</p>
			<input type="hidden" name="id-usuario" <%out.print("value='"+id+"'"); %>/>
           	<p><strong>Nome:</strong><input id="nome-modal" name="nome-usuario" class="input" type="text" readonly/></p>
			<p><strong>Email:</strong><input id="email-modal" name="email-usuario" class="input" type="text" readonly/></p>
			<p><strong>CPF:</strong><input id="cpf-modal" name="cpf-usuario" class="input" type="text" readonly/></p>
			<p><strong>Telefone:</strong><input id="tel-modal" name="tel-usuario" class="input" type="text" readonly/></p>			
        	<div class="form-group">
       			<button id="alter2" type="submit" name="operacao" value="ALTERAR" class="primary-btn">Sim, quero alterar</button>
         		<button style="background-color:gray" type="button" class="primary-btn" data-dismiss="modal">Fechar</button>
       		 </div>
        </form>
        </div>
      </div>
    </div>
  </div>
  
		</footer>
		<!-- /FOOTER -->
	

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
        if(value!=""){
        alert(value);
        	}
    	}
	</script>
	<script type="text/javascript">
	function PegarDados() {
		  document.getElementById("nome-modal").value = document.getElementById("nome").value;
		  document.getElementById("email-modal").value = document.getElementById("email").value;
		  document.getElementById("cpf-modal").value = document.getElementById("cpf").value;
		  document.getElementById("tel-modal").value = document.getElementById("tel").value;
		}
	</script>
</body>

</html>
