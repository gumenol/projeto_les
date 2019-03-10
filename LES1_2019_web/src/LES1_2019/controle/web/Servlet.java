package LES1_2019.controle.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;

import LES1_2019.controle.web.command.ICommand;
import LES1_2019.controle.web.command.impl.AlterarCommand;
import LES1_2019.controle.web.command.impl.ConsultarCommand;
import LES1_2019.controle.web.command.impl.ExcluirCommand;
import LES1_2019.controle.web.command.impl.SalvarCommand;
import LES1_2019.controle.web.command.impl.VisualizarCommand;
import LES1_2019.controle.web.vh.IViewHelper;
import LES1_2019.controle.web.vh.impl.ProdutoViewHelper;
import LES1_2019.controle.web.vh.impl.CategoriaViewHelper;

import LES1_2019.core.aplicacao.Resultado;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Produto;
import LES1_2019.dominio.Categoria;

public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> viewhelpers;
	
	
	public Servlet() {
		super();
		commands = new HashMap<String, ICommand>();
		viewhelpers = new HashMap<String, IViewHelper>();
		
		commands.put("SALVAR-PRODUTO", new SalvarCommand());
		commands.put("ALTERAR-PRODUTO", new AlterarCommand());
		commands.put("EXCLUIR-PRODUTO", new ExcluirCommand());
		commands.put("CONSULTAR-PRODUTOS", new ConsultarCommand());
		commands.put("VISUALIZAR-PRODUTO", new VisualizarCommand());
		//falta tudo de categoria
		
		viewhelpers.put("/LES1_2019_web/SalvarProduto", new ProdutoViewHelper());
		viewhelpers.put("/LES1_2019_web/ExcluirProduto", new ProdutoViewHelper());
		viewhelpers.put("/LES1_2019_web/AlterarProduto", new ProdutoViewHelper());
		viewhelpers.put("/LES1_2019_web/MostrarProduto", new ProdutoViewHelper());
		
	}
		  @Override
		    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
		    		IOException {
			  	doProcessRequest(request, response);
			  	 System.out.println("pinto get");
		    }
		  	
			/**
			 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			 */
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				System.out.println("pinto post");
				doProcessRequest(request, response);
			}
			
			
			protected void doProcessRequest(HttpServletRequest request, 
					HttpServletResponse response) throws ServletException, IOException {
				//Obtem a url que invocou esta servlet (O que foi definido no methdo do form html)
				System.out.println("buceta");
				String uri = request.getRequestURI();
				System.out.println(uri);
				
				//Obtem a operacao executada
				String operacao = request.getParameter("operacao");
				System.out.println(operacao);
				
				//Obtem uma viewhelper indexado pela uri que invocou esta servlet
				IViewHelper vh = viewhelpers.get(uri);
				System.out.println(vh.getClass().getName());
				
				//O viewhelper retorna a entidade especifica para a tela que chamou esta servlet
				EntidadeDominio entidade =  vh.getEntidade(request);
				System.out.println(entidade);
				
				//Obtem o command para executar a respectiva operacao
				ICommand command = commands.get(operacao);
				/*Executa o command que chamarao fachada para executar a operacao requisitada
				 * o retorno e uma instancia da classe resultado que pode conter mensagens derro 
				 * ou entidades de retorno
				 */
				//System.out.println(command.getClass().getName());
				Resultado resultado = new Resultado();
				try {
					resultado = command.execute(entidade);
				}catch (Exception e) {
				}
				/*
				 * Executa o metodo setView do view helper especifio para definir como deveraser apresentado 
				 * o resultado para o usuario
				 */
				vh.setView(resultado, request, response);
	}
}
