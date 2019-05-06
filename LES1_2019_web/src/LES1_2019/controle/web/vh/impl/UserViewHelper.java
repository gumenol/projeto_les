package LES1_2019.controle.web.vh.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import LES1_2019.controle.web.vh.IViewHelper;
import LES1_2019.core.aplicacao.Resultado;
import LES1_2019.dominio.User;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Endereco;

public class UserViewHelper implements IViewHelper {

		public EntidadeDominio getEntidade(HttpServletRequest request) {
			String operacao = request.getParameter("operacao");
			User user = new User();
			Endereco endereco = new Endereco();
			System.out.println("ENTROU NA VH DE USER");
			
			try {
				if(user.getId()==null) {
					user.setId(Integer.parseInt(request.getParameter("id-usuario")));
				}
				
			} catch (Exception e){
					
					}
			
			
			try {
				user.setId(Integer.parseInt(request.getParameter("idusr")));
			} catch (Exception e){
					
					}			
			
			
			try {
				user.setNova_senha(request.getParameter("nova-senha"));
			} catch (Exception e) {
				
			}
			
			try {
				user.setCpf_usuario(request.getParameter("cpf-usuario"));
			} catch (Exception e){
					
					}
			try {
				user.setConfirmar_senha(request.getParameter("senha-usuario2"));
			} catch (Exception e){
					
					}
			
			try {
				user.setSenha_usuario(request.getParameter("senha-usuario"));
			} catch (Exception e){
					
					}
			
			try {
				user.setEmail_usuario(request.getParameter("email-usuario"));
			} catch (Exception e){
					
					}
			
			try {
				user.setNome_usuario(request.getParameter("nome-usuario").trim());
			} catch (Exception e){
					
					}
			
			try {
				user.setTelefone_usuario(request.getParameter("tel-usuario"));
			} catch (Exception e){
					
					}
			endereco.setEndereco_cobranca(true);
			
			try {
				endereco.setRua(request.getParameter("rua"));
			} catch (Exception e){
					
					}
			try {
				endereco.setCep(request.getParameter("cep"));
			} catch (Exception e){
					
					}
			try {
				endereco.setBairro(request.getParameter("bairro"));
			} catch (Exception e){
					
					}
			try {
				endereco.setCidade(request.getParameter("cidade"));
			} catch (Exception e){
					
					}
			try {
				endereco.setEstado(request.getParameter("uf"));
			} catch (Exception e){
					
					}
			try {
				endereco.setNumero(request.getParameter("numero"));
			} catch (Exception e){
					
					}
			try {
				endereco.setComplemento(request.getParameter("complemento"));
			} catch (Exception e){
					
					}
			try {
				
				user.setEnderecos(new ArrayList<Endereco>());
				user.getEnderecos().add(endereco);
				
			} catch (Exception e) {
				
			}
			System.out.println(user.getId());
			System.out.println(user.getNome_usuario());
			System.out.println(user.getEmail_usuario());
		return user;
	}

		@Override
		public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {
			RequestDispatcher rdisp = null;

			String operacao = request.getParameter("operacao");
			
			//salvar
			if(resultado.getMensagem() == null && operacao.equals("SALVAR")) {
				request.getSession().setAttribute("user", resultado);
				rdisp = request.getRequestDispatcher("user-cadastrado.jsp");
			}
			if(resultado.getMensagem() != null && operacao.equals("SALVAR")) {
				request.getSession().setAttribute("erro", resultado);
				
				rdisp = request.getRequestDispatcher("criar-cadastro.jsp");
			}
			
			//login
			if(resultado.getMensagem() == null && operacao.equals("LOGAR")) {
				request.getSession().setAttribute("login", resultado);
				List<EntidadeDominio> ent = resultado.getEntidades();
				User cli = (User)ent.get(0);
				if(cli.isAdmin()) {
					rdisp = request.getRequestDispatcher("Produtos?operacao=CONSULTAR");
				}else {
					rdisp = request.getRequestDispatcher("Produtos?operacao=INDEX");
				}
			}
			if(resultado.getMensagem() != null && operacao.equals("LOGAR")) {
				request.getSession().setAttribute("erro", resultado);
				System.out.println("set view erro");
				rdisp = request.getRequestDispatcher("login.jsp");
			}
			
			//consultar
			if(resultado.getMensagem() == null && operacao.equals("CONSULTAR")) {
				request.getSession().setAttribute("dadosUser", resultado);
				rdisp = request.getRequestDispatcher("meus-dados.jsp");
			}
			
			//alterar
			if(resultado.getMensagem() == null && operacao.equals("ALTERAR")) {
				request.getSession().setAttribute("resultado", resultado);
				rdisp = request.getRequestDispatcher("usersession.jsp");
				}
			if(resultado.getMensagem() != null && operacao.equals("ALTERAR")) {
				request.getSession().setAttribute("erro", resultado);
				rdisp = request.getRequestDispatcher("User?operacao=CONSULTAR");
				}
			
			if(resultado.getMensagem() == null && operacao.equals("ALTERAR-SENHA")) {
				request.getSession().setAttribute("resultado", resultado);
				rdisp = request.getRequestDispatcher("senha-alterada.jsp");
			}
			
			if(resultado.getMensagem() != null && operacao.equals("ALTERAR-SENHA")) {
				request.getSession().setAttribute("erro", resultado);
				rdisp = request.getRequestDispatcher("User?operacao=CONSULTAR");
			}

			if(resultado.getMensagem() == null && operacao.equals("EXCLUIR")) {
				request.getSession().setAttribute("userExcluido", resultado);
				rdisp = request.getRequestDispatcher("conta-excluida.jsp");
				}
			
			if(resultado.getMensagem() == null && operacao.equals("CARDSESSION")) {
				request.getSession().setAttribute("login", resultado);
				rdisp = request.getRequestDispatcher("cartao-alterado.jsp");
				}
			
			if(resultado.getMensagem() == null && operacao.equals("ENDSESSION")) {
				request.getSession().setAttribute("login", resultado);
				rdisp = request.getRequestDispatcher("endereco-alterado.jsp");
				}
			
			if(resultado.getMensagem() == null && operacao.equals("USERSESSION")) {
				request.getSession().setAttribute("login", resultado);
				rdisp = request.getRequestDispatcher("dados-user-alterados.jsp");
				}
			
			rdisp.forward(request, response);
		}
	
}