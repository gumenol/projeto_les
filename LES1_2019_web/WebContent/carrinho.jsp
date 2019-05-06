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

	<!-- Google font -->
	<link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">

	<!-- Bootstrap -->
	<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

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
}

StringBuilder sb;

	/* sb = new StringBuilder();
	sb.append(us.getNome_usuario());
	sb.append(us.getEnderecos().get(0).getRua());
out.print(sb.toString()); */
%>
				
						<!-- Cart -->
						<li class="header-cart dropdown default-dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
								<div class="header-btns-icon">
					<%
							sb = new StringBuilder();
									sb.append("<i class='fa fa-shopping-cart'></i>");
									sb.append("<span class='qty'>"+t+"</span>");
									sb.append("</div>");
									sb.append("<strong class='text-uppercase'>Meu carrinho</strong>");
									sb.append("<br>");
									if(valor != 0){
										sb.append("<span>R"+String.format("$%,.2f", valor)+"</span>");
									}else {
										sb.append("<span>R$ 0,00</span>");
									}
									sb.append("</a>");
									sb.append("<div style='width:40px; display:flex; justify-content:space-around' class='custom-menu'>");
									sb.append("<a href='Pedidos?operacao=VER-CARRINHO' class='main-btn'>Ver carrinho <i class='fas fa-arrow-circle-right'></i></a>");
									sb.append("</div>");
									out.print(sb.toString());
					%>
						</li>
						<!-- /Cart -->