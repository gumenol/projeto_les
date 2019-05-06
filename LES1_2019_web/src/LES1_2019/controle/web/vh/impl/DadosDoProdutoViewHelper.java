package LES1_2019.controle.web.vh.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LES1_2019.controle.web.vh.IViewHelper;
import LES1_2019.core.aplicacao.Resultado;
import LES1_2019.auxiliar.DadosDeCadastro;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Marca;
import LES1_2019.dominio.Categoria;

public class DadosDoProdutoViewHelper implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {
		DadosDeCadastro dadosProd = new DadosDeCadastro();
		return dadosProd;
	}
	
	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String operacao = request.getParameter("editar");
		System.out.println("Entrou no lista dados do produto");
		RequestDispatcher rdisp = null;
		DadosDeCadastro dadosCad = new DadosDeCadastro();
		if(operacao.equals("1")) {
			request.getSession().setAttribute("dados", resultado);
			dadosCad = (DadosDeCadastro)resultado.getEntidades().get(0);
			rdisp = request.getRequestDispatcher("alterar-produto-admin.jsp");
		}
		else {
		request.getSession().setAttribute("dados", resultado);
		dadosCad = (DadosDeCadastro)resultado.getEntidades().get(0);
		rdisp = request.getRequestDispatcher("cadastrar-produto-admin.jsp");
		}
		rdisp.forward(request, response);
		
	}	
}
