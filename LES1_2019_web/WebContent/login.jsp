<%@page import="LES1_2019.core.aplicacao.Resultado"%>
<%@page import="LES1_2019.dominio.*"%>
<%@page import="LES1_2019.auxiliar.DadosDeCadastro"%>
<%@page import="java.util.*"%>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">
<!-- Font Awesome Icon -->
	<link rel="stylesheet" href="css/font-awesome.min.css">

	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	
	<%
			
			String erro = "";
			try{
			Resultado resultProdutos = (Resultado)session.getAttribute("erro");
			erro = resultProdutos.getMensagem().trim();
			session.removeAttribute("erro");
			} catch (Exception e){
				
			}
		%>
	</head>
<body style="background-color:rgb(211, 212, 210)">		
				<div style="text-align:center; padding-top:10px">
				<div style="padding-right:900px"><h3>MenolStore</h3><a style="background-color:grey; color:white" class="btn btn-default" href="Produtos?operacao=INDEX">Voltar para a homepage</a></div>
<hr style="border-color:gray">            
            <h1 class="title_text">Login</h1>

                            <p class="fft-text">Faça seu login para fazer suas compras!</p>
                        <form id="cadastro" action="User" class="form-horizontal form-signin" method="post">

               	 <div class="form-group" style="padding-left:200px">
					<label class="col-sm-3 control-label">Email<span class="required">*</span></label>
						<div class="col-sm-4">
							<input style="background-color:white" type="text" name="email-usuario" class="input" placeholder="Ex:carlos@hotmail.com" required oninvalid="this.setCustomValidity('O campo é obrigatório!')" oninput="setCustomValidity('')"/>
						</div>
				</div>
			
				<div class="form-group" style="padding-left:200px">
					<label class="col-sm-3 control-label">Senha<span class="required">*</span></label>
						<div class="col-sm-4">
							<input style="background-color:white" type="password" name="senha-usuario" placeholder="Digite sua senha..." class="input" required oninvalid="this.setCustomValidity('O campo é obrigatório!')" oninput="setCustomValidity('')"/>
						</div>
				</div>
				
				<div class="form-group"><br>
					<div>
						<button  type="submit" name="operacao" value="LOGAR" class="primary-btn">Entrar</button>
					</div>
				</div>                
					</form>
					<hr style="border-color:gray"><br><br>
					<div><span>Não possui uma conta? Cadastre-se!</span>
					<div><a style="color:white" class="primary-btn" href="criar-cadastro.jsp">Cadastrar!</a></div>
					</div>
        </div>
 <script type="text/javascript">
   		 window.onload = function () {
    	// do the work after everything was loaded (DOM, media elements)
    	var value = "<%=erro%>";
        if(value!=""){
        alert(value);
        	}
    }
</script>
						</body>
						</html>