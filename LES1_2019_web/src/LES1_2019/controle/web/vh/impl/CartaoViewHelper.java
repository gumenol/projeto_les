package LES1_2019.controle.web.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LES1_2019.controle.web.vh.IViewHelper;
import LES1_2019.core.aplicacao.Resultado;
import LES1_2019.dominio.Cartao;
import LES1_2019.dominio.EntidadeDominio;

public class CartaoViewHelper implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Cartao cartao = new Cartao();
		
		try {
			cartao.setId_usuario_cartao(Integer.parseInt(request.getParameter("idusr")));
		} catch (Exception e){
				
				}
		try {
			cartao.setId_usuario_cartao(Integer.parseInt(request.getParameter("id-usuario")));
		} catch (Exception e) {
			
		}
		
		try {
			cartao.setId(Integer.parseInt(request.getParameter("id-cartao")));
		} catch (Exception e) {
			
		}
		
		try {
			cartao.setId_bandeira_cartao(Integer.parseInt(request.getParameter("id-bandeira")));
		} catch (Exception e) {
			
		}
		
		try {
			cartao.setNumero_cartao(request.getParameter("numero-cartao"));
		} catch (Exception e) {
			
		}
		
		try {
			cartao.setAno_cartao(request.getParameter("ano-validade"));
		} catch (Exception e) {
			
		}
		
		try {
			cartao.setMes_cartao(request.getParameter("mes-validade"));
		} catch (Exception e) {
			
		}
		
		try {
			cartao.setCvv_cartao(request.getParameter("cvv-cartao"));
		} catch (Exception e) {
			
		}
		
		try {
			cartao.setNome_cartao(request.getParameter("nome-cartao"));
		} catch (Exception e) {
			
		}
		
		try {
			cartao.setStatus_cartao(Boolean.parseBoolean(request.getParameter("status-cartao")));
		} catch (Exception e) {
			
		}
		
	
	return cartao;
}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher rdisp = null;

		String operacao = request.getParameter("operacao");
				
		//salvar
		if(resultado.getMensagem() == null && operacao.equals("SALVAR")) {
			request.getSession().setAttribute("resultado", resultado);
			rdisp = request.getRequestDispatcher("cardsession.jsp");
			}
		
		if(resultado.getMensagem() != null && operacao.equals("SALVAR")) {
			request.getSession().setAttribute("erro", resultado);
			rdisp = request.getRequestDispatcher("Cartoes?operacao=CONSULTAR");
			}
		
		//consultar
		if(resultado.getMensagem() == null && operacao.equals("CONSULTAR")) {
			request.getSession().setAttribute("dadosCartao", resultado);
			rdisp = request.getRequestDispatcher("meus-dados-cartao.jsp");
					}
		
		//excluir
			if(resultado.getMensagem() == null && operacao.equals("EXCLUIR")) {
				request.getSession().setAttribute("resultado", resultado);
				rdisp = request.getRequestDispatcher("cardsession.jsp");
			}
			
		//alterar
		if(resultado.getMensagem() == null && operacao.equals("ALTERAR")) {
			request.getSession().setAttribute("resultado", resultado);
			rdisp = request.getRequestDispatcher("cardsession.jsp");
		}
		
		//alterar
		if(resultado.getMensagem() != null && operacao.equals("ALTERAR")) {
		request.getSession().setAttribute("erro", resultado);
		rdisp = request.getRequestDispatcher("Cartoes?operacao=CONSULTAR");
		}
		
		rdisp.forward(request, response);
	}

}