package LES1_2019.controle.web.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LES1_2019.controle.web.vh.IViewHelper;
import LES1_2019.core.aplicacao.Resultado;
import LES1_2019.dominio.Endereco;
import LES1_2019.dominio.EntidadeDominio;

public class EnderecoViewHelper implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Endereco endereco = new Endereco();
		System.out.println("ENTROU NA VH endereco");
		try {
			endereco.setId_usuario_endereco(Integer.parseInt(request.getParameter("idusr")));
		} catch (Exception e){
				
				}
		
		try {
			endereco.setEndereco_cobranca(Boolean.parseBoolean(request.getParameter("end-cobranca")));
		} catch (Exception e){
				
				}
		
		try {
			endereco.setId_usuario_endereco(Integer.parseInt(request.getParameter("id-usuario")));
		} catch (Exception e){
				
				}
		
		try {
			endereco.setId(Integer.parseInt(request.getParameter("id-endereco")));
		} catch (Exception e){
				
				}
		
		
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
		
		System.out.println(endereco.getNumero());
	return endereco;
}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher rdisp = null;

		String operacao = request.getParameter("operacao");
		
		//consultar
		if(resultado.getMensagem() == null && operacao.equals("CONSULTAR")) {
			request.getSession().setAttribute("dadosEndereco", resultado);
			rdisp = request.getRequestDispatcher("meus-dados-end.jsp");
		}
		
		//alterar
		if(resultado.getMensagem() == null && operacao.equals("ALTERAR")) {
			request.getSession().setAttribute("resultado", resultado);
			rdisp = request.getRequestDispatcher("endsession.jsp");
		}
		//alterar
		if(resultado.getMensagem() != null && operacao.equals("ALTERAR")) {
				request.getSession().setAttribute("erro", resultado);
				rdisp = request.getRequestDispatcher("Enderecos?operacao=CONSULTAR");
		}
		
		//excluir
		if(resultado.getMensagem() == null && operacao.equals("EXCLUIR")) {
			request.getSession().setAttribute("resultado", resultado);
			rdisp = request.getRequestDispatcher("endsession.jsp");
			}
				
		//salvar
		if(resultado.getMensagem() == null && operacao.equals("SALVAR")) {
			request.getSession().setAttribute("resultado", resultado);
			rdisp = request.getRequestDispatcher("endsession.jsp");
			}
		//salvar
		if(resultado.getMensagem() != null && operacao.equals("SALVAR")) {
			request.getSession().setAttribute("erro", resultado);
			rdisp = request.getRequestDispatcher("Enderecos?operacao=CONSULTAR");
					}
		
		rdisp.forward(request, response);
	}

}