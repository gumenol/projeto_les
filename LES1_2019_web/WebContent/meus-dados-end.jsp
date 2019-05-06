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

<%
	String erro = "";
	//recebendo dados de endereços
	Resultado endereco = (Resultado)session.getAttribute("dadosEndereco");	

	if(session.getAttribute("dadosEndereco")==null){
		pageContext.forward("User?operacao=CONSULTAR");
	}
	
	try{
		Resultado resultProdutos = (Resultado)session.getAttribute("erro");
		erro = resultProdutos.getMensagem().trim();
		session.removeAttribute("erro");
		} catch (Exception e){
			
		}
	
	System.out.println(session.getAttribute("dadosEndereco")==null);
	List<EntidadeDominio> enderecos = endereco.getEntidades();
	Endereco end;
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
	int ids = 0;
	int idss = 0;
	int id2 = 0;
	String estado1 = "";
	String estado2 = "";
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
				<div style="display:flex; justify-content: space-around">
				<p><strong>SE O ENDERECO DE COBRANCA FOR O UNICO CADASTRADO, ELE SERA AUTOMATICAMENTE DEFINIDO COMO O DE ENTREGA</strong></p>
				<a href="User?operacao=CONSULTAR" class="primary-btn">Voltar</a>
					</div>
					<%
					if(enderecos!=null){
						for(EntidadeDominio e: enderecos){
						end = (Endereco)e;
						if(id2==0){
							estado1 = end.getEstado();
							ids = end.getId();
						} else {
							estado2 = end.getEstado();
							idss = end.getId();
						}
					out.print("<div class='col-md-6'>");
					out.print("<div class='billing-details'>");
							out.print("<div class='section-title'>");
							if(end.isEndereco_cobranca()){
								out.print("<h3 class='title'>Endereço de cobrança</h3>");
								out.print("</div>");
								out.print("<form id='cobranca' action='Enderecos' method='POST'>");
							} else {
								out.print("<h3 class='title'>Endereço de entrega</h3>");
								out.print("<form id='entrega' action='Enderecos' method='POST'>");
								out.print("</div>");
							}
							out.print("<div class='form-group'>");
								out.print("<input type='hidden' value='"+id+"' name='id-usuario'/>");
								out.print("<input type='hidden' value='"+end.getId()+"' name='id-endereco'/>");
							out.print("</div>");
							
							out.print("<div class='form-group'>");
								out.print("<p><strong>Rua:</strong></p>");
								out.print("<input class='input' type='text' id='rua' value='"+end.getRua()+"' name='rua' required oninvalid='this.setCustomValidity('O campo nome é obrigatório!')' oninput='setCustomValidity('')'/>");
							out.print("</div>");
							
							out.print("<div class='form-group'>");
							out.print("<p><strong>Bairro:</strong></p>");
							out.print("<input class='input' type='text' id='bairro' value='"+end.getBairro()+"' name='bairro' required oninvalid='this.setCustomValidity('O campo nome é obrigatório!')' oninput='setCustomValidity('')'/>");
							out.print("</div>");
							
							out.print("<div class='form-group'>");
							out.print("<p><strong>Cidade:</strong></p>");
							out.print("<input class='input' type='text' id='cidade' value='"+end.getCidade()+"' name='cidade' required oninvalid='this.setCustomValidity('O campo nome é obrigatório!')' oninput='setCustomValidity('')'/>");
							out.print("</div>");
							
							out.print("<div style='display:flex; justify-content:space-around; margin-right:60px'>");
								out.print("<div class='form-group'><p><strong>Numero:</strong></p>");
								out.print("<input class='input' type='text' id='numero' value='"+end.getNumero()+"' name='numero' required oninvalid='this.setCustomValidity('O campo é obrigatório!')' oninput='setCustomValidity('')'/>");
								out.print("</div>");
								out.print("<div class='form-group'><p><strong>Tipo endereço:</strong></p>");
									if(end.isEndereco_cobranca()){
									out.print("<input type='text' class='input' value='Cobranca' class='input' id='tpend' name='end-cobranca' disabled/>");
									out.print("<input type='hidden' class='input' value='true' class='input' id='tpend' name='end-cobranca'/>");
									} else {
									out.print("<input type='text' class='input' value='Entrega' class='input' id='tpend' name='end-cobranca' disabled/>");
									out.print("<input type='hidden' class='input' value='false' class='input' id='tpend' name='end-cobranca'/>");
									}
									out.print("</select>");
								out.print("</div>");
							out.print("</div>");
							out.print("<div style='display:flex; justify-content:space-around'>");
								out.print("<div class='form-group'>");
								out.print("<p><strong>CEP:</strong></p>");
									out.print("<input class='input' type='text' id='cep' value='"+end.getCep()+"' name='cep' required oninvalid='this.setCustomValidity('O campo nome é obrigatório!')' oninput='setCustomValidity('')'/>");
								out.print("</div>");
						out.print("<div class='form-group'>");
						out.print("<p><strong>UF:</strong></p>");
						out.print("<select name='uf' id='uf"+end.getId()+"' class='input' required oninvalid='this.setCustomValidity('O campo é obrigatório!')' oninput='setCustomValidity('')'>");
							out.print("<option value=''>Estado</option>");
							out.print("<option value='AC'>Acre</option>");
							out.print("<option value='AL'>Alagoas</option>");
							out.print("<option value='AP'>Amapá</option>");
							out.print("<option value='AM'>Amazonas</option>");
							out.print("<option value='BA'>Bahia</option>");
							out.print("<option value='CE'>Ceará</option>");
							out.print("<option value='DF'>Distrito Federal</option>");
							out.print("<option value='ES'>Espírito Santo</option>");
							out.print("<option value='GO'>Goiás</option>");
							out.print("<option value='MA'>Maranhão</option>");
							out.print("<option value='MT'>Mato Grosso</option>");
							out.print("<option value='MS'>Mato Grosso do Sul</option>");
							out.print("<option value='MG'>Minas Gerais</option>");
							out.print("<option value='PA'>Pará</option>");
							out.print("<option value='PB'>Paraíba</option>");
							out.print("<option value='PR'>Paraná</option>");
							out.print("<option value='PE'>Pernambuco</option>");
							out.print("<option value='PI'>Piauí</option>");
							out.print("<option value='RJ'>Rio de Janeiro</option>");
							out.print("<option value='RN'>Rio Grande do Norte</option>");
							out.print("<option value='RS'>Rio Grande do Sul</option>");
							out.print("<option value='RO'>Rondônia</option>");
							out.print("<option value='RR'>Roraima</option>");
							out.print("<option value='SC'>Santa Catarina</option>");
							out.print("<option value='SP'>São Paulo</option>");
							out.print("<option value='SE'>Sergipe</option>");
							out.print("<option value='TO'>Tocantins</option>");
							out.print("</select>");
						out.print("</div>");
					out.print("</div>");
				out.print("<div class='form-group'>");
					out.print("<p><strong>Complemento:</strong></p>");
						out.print("<textarea class='input' id='complemento' name='complemento'>"+end.getComplemento()+"</textarea>");
					out.print("</div>");
					out.print("<div style='display:flex; justify-content:space-around'>");
					out.print("<div style='text-align:center' class='form-group'>");
						out.print("<button class='primary-btn' type='submit' name='operacao' value='ALTERAR'>Alterar dados</button>");
					out.print("</div>");
					out.print("</form>");
					if(!end.isEndereco_cobranca()){
						out.print("<div style='text-align:center' class='form-group'>");
					out.print("<button name='exc' style='background-color:red' class='primary-btn' data-toggle='modal' data-target='#Excluir'>Excluir endereço</button>");
					}
					out.print("</div>");
				out.print("</div>");
				out.print("</div>");
			id2++;
					}
				} else {
					out.print("<div>Oops, voce bugou essa merda seu arrombado</div>");
				}
					if(id2==1){
						out.print("<div class='col-md-6'>");
						out.print("<div class='billing-details'>");
								out.print("<div class='section-title'>");
									out.print("<h3 class='title'>Endereço de entrega</h3>");
									out.print("</div><br><br>");
									out.print("<div style='display:flex; justify-content:space-around'>");
									out.print("<p><strong>Você ainda não tem nenhum endereço de entrega</strong></p>");
									out.print("</div>");
									out.print("<div style='display:flex; justify-content:space-around'>");
									out.print("<button name='cad' class='primary-btn' data-toggle='modal' data-target='#Cadastrar'>Cadastrar Novo endereço</button>");
									out.print("</div>");
									out.print("</div>");
								out.print("</div>");
								out.print("</div>");
					}
					%>
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
			
	<div class="modal fade" id="Cadastrar" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
     <div style="background-color:white" class="col-md-9 col-md-offset-2">
		<div class="billing-details">
			<div class="section-title">
			<h3 class="title">Cadastrar Endereço</h3>
        </div>
        <form id="Entrega" action="Enderecos" method="POST">
          <p>Confirme os dados do novo endereço</p>
          <input type="hidden" name="id-usuario" value="<%out.print(id);%>"/>
         	 <p><strong>CEP:</strong><input id="cep-modal" name="cep" class="input" type="text" /></p>
           	<p><strong>Rua:</strong><input id="rua-modal" name="rua" class="input" type=text /></p>
			<p><strong>Bairro:</strong><input id="bairro-modal" name="bairro" class="input" type="text" /></p>
			<p><strong>Cidade:</strong><input id="cidade-modal" name="cidade" class="input" type="text" /></p>
			<p><strong>Numero:</strong><input id="numero-modal" name="numero" class="input" type="text" /></p>
			<p><strong>Tipo endereço:</strong>
			<input id="tpend-modal" type="text" name="end-cobranca" value="Entrega" class="input" type="text" disabled/>
			<input id="tpend-modal" type="hidden" name="end-cobranca" value="false" class="input" type="text"/>
			</p>
			<p><strong>UF:</strong><select id="uf-modal" name="uf" class="input">
			<option value="">Estado</option>
							<option value="AC">Acre</option>
							<option value="AL">Alagoas</option>
							<option value="AP">Amapá</option>
							<option value="AM">Amazonas</option>
							<option value="BA">Bahia</option>
							<option value="CE">Ceará</option>
							<option value="DF">Distrito Federal</option>
							<option value="ES">Espírito Santo</option>
							<option value="GO">Goiás</option>
							<option value="MA">Maranhão</option>
							<option value="MT">Mato Grosso</option>
							<option value="MS">Mato Grosso do Sul</option>
							<option value="MG">Minas Gerais</option>
							<option value="PA">Pará</option>
							<option value="PB">Paraíba</option>
							<option value="PR">Paraná</option>
							<option value="PE">Pernambuco</option>
							<option value="PI">Piauí</option>
							<option value="RJ">Rio de Janeiro</option>
							<option value="RN">Rio Grande do Norte</option>
							<option value="RS">Rio Grande do Sul</option>
							<option value="RO">Rondônia</option>
							<option value="RR">Roraima</option>
							<option value="SC">Santa Catarina</option>
							<option value="SP">São Paulo</option>
							<option value="SE">Sergipe</option>
							<option value="TO">Tocantins</option>
			</select></p>
			<p><strong>Complemento:</strong><input id="comp-modal" name="complemento" class="input" type="text" /></p>
        	<div class="form-group">
       			<button id="cad2" type="submit" name="operacao" value="SALVAR" class="primary-btn">Cadastrar</button>
         		<button style="background-color:gray" type="button" class="primary-btn" data-dismiss="modal">Fechar</button>
       		 </div>
        </form>
        </div>
      </div>
    </div>
  </div>
  
  <div class="modal fade" id="Excluir" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div style="background-color:white" class="col-md-6 col-md-offset-3">
		<div class="billing-details">
			<div class="section-title">
			<h3 class="title">Confirmar exclusão</h3>
       		</div>
          <p>Deseja mesmo excluir esse endereço?</p>
           <p>Caso queira um endereço de entrega diferente do de cobrança, deverá fazer o cadastro de um endereço novo...</p>
           <br>
        <div class="form-group">
       		<a name="exclu1" href="Enderecos?id-endereco=<%out.print(idss); %>&operacao=EXCLUIR" class="primary-btn">Sim, quero excluir</a>
          <button style="background-color:gray" type="button" class="primary-btn" data-dismiss="modal">Fechar</button>
        </div>
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
	$(document).ready(function(){
		var id1 = "<%=ids%>";
		var id2 = "<%=idss%>";
		var est1 = "<%=estado1%>";
		var est2 = "<%=estado2%>";
		if(id1 != null || id1 != ""){
		document.getElementById("uf"+id1).value = est1;
			}
		if(id2 != null && est2 != ""){
		document.getElementById("uf"+id2).value = est2;
			}
		});
	
	</script>

</body>

</html>