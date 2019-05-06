<%@page import="LES1_2019.core.aplicacao.Resultado"%>
<%@page import="LES1_2019.dominio.*"%>
<%@page import="LES1_2019.auxiliar.DadosDeCadastro"%>
<%@page import="java.util.*"%>

<%
Resultado login = (Resultado) session.getAttribute("login");
try{
	List<EntidadeDominio> user = login.getEntidades();
	} catch (Exception e){
		String redirectURL = "login.jsp";
		pageContext.forward(redirectURL);
	}

	List<EntidadeDominio> user = login.getEntidades();
	
	User us = (User)user.get(0);
	StringBuilder sb = new StringBuilder();
	sb = new StringBuilder();
	sb.append(us.getNome_usuario());
	sb.append(us.getEnderecos().get(0).getRua());
out.print(sb.toString());
%>