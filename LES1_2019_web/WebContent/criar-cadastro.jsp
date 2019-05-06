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
	 <!-- Adicionando Javascript -->
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
    <script type="text/javascript">
    function limpa_formulário_cep() {
            //Limpa valores do formulário de cep.
            document.getElementById('rua').value=("");
            document.getElementById('bairro').value=("");
            document.getElementById('cidade').value=("");
            document.getElementById('uf').value=("");
    }

    function meu_callback(conteudo) {
        if (!("erro" in conteudo)) {
            //Atualiza os campos com os valores.
            document.getElementById('rua').value=(conteudo.logradouro);
            document.getElementById('bairro').value=(conteudo.bairro);
            document.getElementById('cidade').value=(conteudo.localidade);
            document.getElementById('uf').value=(conteudo.uf);
        } //end if.
        else {
            //CEP não Encontrado.
            limpa_formulário_cep();
            alert("CEP não encontrado.");
        }
    }
        
    function pesquisacep(valor) {

        //Nova variável "cep" somente com dígitos.
        var cep = valor.replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep != "") {

            //Expressão regular para validar o CEP.
            var validacep = /^[0-9]{8}$/;

            //Valida o formato do CEP.
            if(validacep.test(cep)) {

                //Preenche os campos com "..." enquanto consulta webservice.
                document.getElementById('rua').value="Preenchendo...";
                document.getElementById('bairro').value="Preenchendo...";
                document.getElementById('cidade').value="Preenchendo...";

                //Cria um elemento javascript.
                var script = document.createElement('script');

                //Sincroniza com o callback.
                script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

                //Insere script no documento e carrega o conteúdo.
                document.body.appendChild(script);

            } //end if.
            else {
                //cep é inválido.
                limpa_formulário_cep();
                alert("Formato de CEP inválido.");
            }
        } //end if.
        else {
            //cep sem valor, limpa formulário.
            limpa_formulário_cep();
        }
    };

    </script>
    
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
				<div style="display:flex">
				<div style="padding-left:100px"><h3>MenolStore</h3><a style="background-color:grey; color:white" class="btn btn-default" href="Produtos?operacao=INDEX">Voltar para a homepage</a></div>
				<div style="padding-top:5px; padding-left:700px"><h4>Já possui uma conta? Faça login!</h4><a style="color:white" class="primary-btn" href="login.jsp">Login</a></div>
				</div>
<hr style="border-color:gray">            
            <h1 class="title_text">Criar conta</h1>

                            <p><strong>Dados pessoais</strong></p>
                        <form id="cadastro" action="User" class="form-horizontal form-signin" method="post">
                <div class="form-group" style="padding-left:200px">
					<label class="col-sm-3 control-label">Nome completo<span class="required">*</span></label>
						<div class="col-sm-4">
							<input style="background-color:white" type="text" name="nome-usuario" class="input" placeholder="Carlos Sumare" required oninvalid="this.setCustomValidity('O campo nome é obrigatório!')" oninput="setCustomValidity('')"/>
						</div>
				</div>

				<div class="form-group" style="padding-left:200px">
					<label class="col-sm-3 control-label">CPF<span class="required">*</span></label>
						<div class="col-sm-4">
							<input style="background-color:white" id="cpf" onblur="validarCPF(this.value)" type="text" name="cpf-usuario" class="input" placeholder="XXX.XXX.XXX-XX" required oninvalid="this.setCustomValidity('O campo é obrigatório!')" oninput="setCustomValidity('')"/>
						</div>
				</div>

               <div class="form-group" style="padding-left:200px">
					<label class="col-sm-3 control-label">Email<span class="required">*</span></label>
						<div class="col-sm-4">
							<input style="background-color:white" type="email" name="email-usuario" class="input" placeholder="carlos@hotmail.com" required oninvalid="this.setCustomValidity('O campo é obrigatório!')" oninput="setCustomValidity('')"/>
						</div>
				</div>
				
				<div class="form-group" style="padding-left:200px">
					<label class="col-sm-3 control-label">Telefone<span class="required">*</span></label>
						<div class="col-sm-4">
							<input style="background-color:white" type="text" name="tel-usuario" class="input" placeholder="11 99999-9999" required oninvalid="this.setCustomValidity('O campo é obrigatório!')" oninput="setCustomValidity('')"/>
						</div>
				</div>
				
				<div class="form-group" style="padding-left:200px">
					<label class="col-sm-3 control-label">Senha<span class="required">*</span></label>
						<div class="col-sm-4">
							<input style="background-color:white" type="password" name="senha-usuario" placeholder="Minimo de 8 digitos..." class="input" required oninvalid="this.setCustomValidity('O campo é obrigatório!')" oninput="setCustomValidity('')"/>
						</div>
				</div>
				
				<div class="form-group" style="padding-left:200px">
					<label class="col-sm-3 control-label">Repita a senha<span class="required">*</span></label>
						<div class="col-sm-4">
							<input style="background-color:white" type="password" name="senha-usuario2" placeholder="As senhas devem ser iguais!" class="input" required oninvalid="this.setCustomValidity('O campo é obrigatório!')" oninput="setCustomValidity('')"/>
						</div>
				</div>
				<p><strong>Dados de endereço - Busque pelo CEP:</strong></p>
				
				<div class="form-group" style="padding-left:190px">
					<label class="col-sm-3 control-label">CEP<span class="required">*</span></label>
						<div style="display:flex">
						<div class="col-sm-3">
							<input style="background-color:white" type="text" id="cep" name="cep" placeholder="Digite seu CEP" class="input" required oninvalid="this.setCustomValidity('O campo é obrigatório!')" oninput="setCustomValidity('')"/>
						</div>
							<input name="buscacep" type="button" class="primary-btn" value="Clique para buscar" onclick="pesquisacep(cep.value)"></input>
						</div>
				</div>
				
				<div class="form-group" style="padding-left:200px">
					<label class="col-sm-3 control-label">Rua<span class="required">*</span></label>
						<div class="col-sm-4">
							<input style="background-color:white" type="text" id="rua" name="rua" placeholder="Digite a rua" class="input"/>
						</div>
				</div>
				
				<div class="form-group" style="padding-left:200px">
					<label class="col-sm-3 control-label">Bairro<span class="required">*</span></label>
						<div class="col-sm-4">
							<input style="background-color:white" type="text" id="bairro" name="bairro" placeholder="Digite o bairro" class="input"/>
						</div>
				</div>
				
				<div class="form-group" style="padding-left:200px">
					<label class="col-sm-3 control-label">Cidade<span class="required">*</span></label>
						<div class="col-sm-4">
							<input style="background-color:white" type="text" id="cidade" name="cidade" placeholder="Digite cidade" class="input"/>
						</div>
				</div>
				
				<div class="form-group" style="padding-left:200px">
						<label class="col-sm-3 control-label">UF<span class="required">*</span></label>
							<div class="col-sm-3">
							<select style="background-color:white" id="uf" name="uf" class="input">
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
							</select>
						</div>
				</div>
					<div class="form-group" style="padding-left:200px">
						<label class="col-sm-3 control-label">Número<span class="required">*</span></label>
							<div class="col-sm-2">
							<input style="background-color:white" type="text" id="numero" name="numero" placeholder="Digite o número" class="input" required oninvalid="this.setCustomValidity('O campo é obrigatório!')" oninput="setCustomValidity('')"/>
							</div>
					</div>
				
				<div class="form-group" style="padding-left:200px">
					<label class="col-sm-3 control-label">Complemento<span class="required">*</span></label>
						<div class="col-sm-4">
							<input style="background-color:white" type="text" id="complemento" name="complemento" placeholder="Digite complemento" class="input"/>
						<br><br>
						<span>O endereço acima digitado será automaticamente usado como endereço de cobrança!</span>
						<span>Você poderá alterar essa opção após o cadastro...</span>
						</div>
				</div>
				
				<div class="form-group">
					<div>
						<button type="submit" name="operacao" value="SALVAR" class="primary-btn">Cadastrar!</button>
					</div>
				</div>              
					</form>
					<hr style="border-color:gray">
					<div><span>Já possui uma conta? Faça login!</span>
					<div><a style="color:white" class="primary-btn" href="login.jsp">Login</a></div>
					</div><br>
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