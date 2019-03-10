package LES1_2019.controle.web.vh.impl;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import LES1_2019.controle.web.vh.IViewHelper;
import LES1_2019.core.aplicacao.Resultado;
import LES1_2019.dominio.Produto;
import LES1_2019.dominio.Categoria;
import LES1_2019.dominio.EntidadeDominio;

public class ProdutoViewHelper implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {
		System.out.println("entrou na viewhelper");
		String operacao = request.getParameter("operacao");
		Produto produto = new Produto();
		Categoria categoria = new Categoria();
		
	if(operacao.equals("SALVAR-PRODUTO")) {
		System.out.println("entrou no salvar produto");
		produto.setNome(request.getParameter("nome-produto"));
		categoria.setId(Integer.parseInt(request.getParameter("categoria-produto")));
		produto.setCategoria(categoria);
		produto.setMarca_produto(request.getParameter("marca-produto"));
		produto.setValor_produto(Double.parseDouble(request.getParameter("valor-produto")));
		try {
		produto.setDescricao_produto(request.getParameter("descricao-produto"));
		} catch (Exception e){
			
			}
		}
	else if (operacao.equals("ALTERAR-PRODUTO")) {
		produto.setId(Integer.parseInt(request.getParameter("id-produto")));
		double valor_produto = Double.parseDouble(request.getParameter("valor-produto"));
		produto.setNome(request.getParameter("nome-produto"));
		categoria.setId(Integer.parseInt(request.getParameter("categoria-produto")));
		produto.setCategoria(categoria);
		produto.setMarca_produto(request.getParameter("marca-produto"));
		produto.setValor_produto(valor_produto);
		produto.setStatus_produto(Boolean.valueOf(request.getParameter("status-produto")));
		try {
		produto.setDescricao_produto(request.getParameter("descricao-produto"));
		} catch (Exception e){
			
			}
		}
	else if(operacao.equals("EXCLUIR-PRODUTO")) {
		produto.setId(Integer.parseInt(request.getParameter("id-produto")));
		}
	else if(operacao.equals("VISUALIZAR-PRODUTO")) {
		try {
			request.getSession();
			produto.setId(Integer.parseInt(request.getParameter("id-produto")));
			
		} catch(Exception e) {
			
		}
	}
	return produto;
	}

public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		RequestDispatcher rdisp = null;
		String operacao = request.getParameter("operacao");
		//salvar
		if(resultado.getMensagem() == null && operacao.equals("SALVAR-PRODUTO")) {
			request.getSession().setAttribute("salvar", resultado);
			
			rdisp = request.getRequestDispatcher("produto-cadastrado.jsp");
		}
		//alterar
		if(resultado.getMensagem()== null && operacao.equals("ALTERAR-PRODUTO")) {
			request.getSession().setAttribute("alterar", resultado.getEntidades().get(0));
			
			rdisp = request.getRequestDispatcher("alterar-produtos.jsp");	
		}
		//excluir
		if(resultado.getMensagem() == null && operacao.equals("EXCLUIR-PRODUTO")) {
			request.getSession().setAttribute("excluir", resultado);
			
			rdisp = request.getRequestDispatcher("produto-excluido.jsp");
		}
	}
}