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


<%
int erro = 0;
Resultado login = (Resultado) session.getAttribute("login");
	try{
		List<EntidadeDominio> user = login.getEntidades();
		} catch (Exception e){
			erro = 1;
	}
	String nomeUser = "";
	if(erro==0){
	List<EntidadeDominio> user = login.getEntidades();
	User us = (User)user.get(0);
	nomeUser = us.getNome_usuario();
	}
	StringBuilder sb = new StringBuilder();
	/* sb = new StringBuilder();
	sb.append(us.getNome_usuario());
	sb.append(us.getEnderecos().get(0).getRua());
out.print(sb.toString()); */
%>
				<!-- Account -->
						<li class="header-account dropdown default-dropdown">
							<div class="dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="true">
								<div name="dados" class="header-btns-icon">
									<i class="fa fa-user-o"></i>
								</div>
							<% 
							sb = new StringBuilder();
							
							if(nomeUser != ""){
							sb.append("<strong class='text-uppercase'>"+nomeUser+" <i class='fa fa-caret-down'></i></strong>");
							sb.append("</div>");
							sb.append("<div style ='display:flex'>");
							sb.append("<a name='logout' href='logout.jsp' class='text-uppercase'>Logout</a></div>");
							sb.append("<ul class='custom-menu'><li><a name='acessar' href='User?operacao=CONSULTAR'><i class='fa fa-user-o'></i> Meus dados</a></li></ul>");
							
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