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
import LES1_2019.dominio.Produto;
import LES1_2019.dominio.Categoria;
import LES1_2019.dominio.EntidadeDominio;

public class ProdutoViewHelper implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Produto produto = new Produto();
		Categoria categoria = new Categoria();
	
		
		try {
			if(produto.getId()==null) {
			produto.setId(Integer.parseInt(request.getParameter("id-produto")));
			}
		} catch (Exception e){
				
				}		
		
		try {
			int idProduto = Integer.parseInt(request.getParameter("idprd"));
			produto.setId(idProduto);
			} catch (Exception e) {
			}
		
		try {
			produto.setValor_produto(Double.parseDouble(request.getParameter("valor-produto")));
		} catch (Exception e){
				
				}
		try {
			produto.setId_marca_produto(Integer.parseInt(request.getParameter("marca-produto")));
		} catch (Exception e){
				
				}
		
		try {
			categoria.setId(Integer.parseInt(request.getParameter("categoria-produto")));
		} catch (Exception e){
				
				}
		
		try {
			produto.setQtd_estoque(Integer.parseInt(request.getParameter("qtd-estoque")));
		} catch (Exception e){
				
				}
		
		try {
			produto.setCategoria(categoria);
		} catch (Exception e){
				
				}
		
		try {
			produto.setNome(request.getParameter("nome-produto").trim());
		} catch (Exception e){
				
				}
		
		try {
			produto.setDescricao_produto(request.getParameter("descricao-produto"));
		} catch (Exception e){
				
				}
		
		try {
			produto.setImg_produto(request.getParameter("img-produto"));
		} catch (Exception e){
				
				}
		
		
	return produto;
}

public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		RequestDispatcher rdisp = null;
		String operacao = request.getParameter("operacao");
		//salvar
		if(resultado.getMensagem() == null && operacao.equals("SALVAR")) {
			request.getSession().setAttribute("produto", resultado);
			
			rdisp = request.getRequestDispatcher("produto-cadastrado.jsp");
		}
		if(resultado.getMensagem()!=null&&operacao.equals("SALVAR"))
		{
			request.getSession().setAttribute("produto", resultado);
			rdisp = request.getRequestDispatcher("DadosProduto?operacao=CONSULTAR&editar=0");
		}
		//alterar
		if(resultado.getMensagem()== null && operacao.equals("ALTERAR")) {
			request.getSession().setAttribute("produto", resultado);
			
			rdisp = request.getRequestDispatcher("produto-alterado.jsp");	
		}
		if(resultado.getMensagem()!=null&&operacao.equals("ALTERAR"))
		{	request.getSession().setAttribute("produto", resultado);
			String id = resultado.getMensagem();
			String erro = resultado.getMensagem();
			id = id.replaceAll("\\D+","");
			erro = erro.replaceAll("[^A-Za-z]", " ");
			resultado.setMensagem(erro);
			rdisp = request.getRequestDispatcher("DadosProduto?idprd="+id+"&operacao=CONSULTAR&editar=1");
		}
		//excluir
		if(resultado.getMensagem() == null && operacao.equals("EXCLUIR")) {
			request.getSession().setAttribute("produto", resultado);
			
			rdisp = request.getRequestDispatcher("produto-excluido.jsp");
		}
		if(resultado.getMensagem() != null && operacao.equals("EXCLUIR")) {
			request.getSession().setAttribute("produto", resultado);
			
			rdisp = request.getRequestDispatcher("Produtos?operacao=CONSULTAR");
		}
		
		//ativar
		if(resultado.getMensagem() == null && operacao.equals("ATIVAR")) {
			request.getSession().setAttribute("produto", resultado);
			
			rdisp = request.getRequestDispatcher("produto-ativado.jsp");
		}
		if(resultado.getMensagem() != null && operacao.equals("ATIVAR")) {
			request.getSession().setAttribute("produto", resultado);
			
			rdisp = request.getRequestDispatcher("Produtos?operacao=CONSULTAR");
		}
		
		//consultar
		if(resultado.getMensagem() == null && operacao.equals("CONSULTAR")) {
			System.out.println("Entrou na setview");
			request.getSession().setAttribute("produtos", resultado);
			
			rdisp = request.getRequestDispatcher("dashboard-admin-visualizar-produtos.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("INDEX")) {
			System.out.println("Entrou na setview");
			request.getSession().setAttribute("produtos", resultado);
			
			rdisp = request.getRequestDispatcher("index.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("CARRINHO")) {
			System.out.println("Entrou na setview");
			request.getSession().setAttribute("resultado", resultado);
			
			rdisp = request.getRequestDispatcher("Produtos?operacao=CARSESSION");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("CARSESSION")) {
			request.getSession().setAttribute("carrinho", resultado);
			
			rdisp = request.getRequestDispatcher("Produtos?operacao=INDEX");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("VISUALIZAR")) {
			System.out.println("Entrou na setview visualizar");
			
			request.getSession().setAttribute("visualizar", resultado);
			
			rdisp = request.getRequestDispatcher("produto.jsp");
		}
		
		rdisp.forward(request, response);
	}
}