package LES1_2019.controle.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;
import LES1_2019.core.IFachada;
import LES1_2019.core.impl.controle.Fachada;

import LES1_2019.controle.web.command.ICommand;
import LES1_2019.controle.web.command.impl.AddSessionCommand;
import LES1_2019.controle.web.command.impl.AlterarCommand;
import LES1_2019.controle.web.command.impl.AtivarCommand;
import LES1_2019.controle.web.command.impl.ConsultarCommand;
import LES1_2019.controle.web.command.impl.ExcluirCommand;
import LES1_2019.controle.web.command.impl.LogarCommand;
import LES1_2019.controle.web.command.impl.SalvarCommand;
import LES1_2019.controle.web.command.impl.VisualizarCommand;
import LES1_2019.controle.web.vh.IViewHelper;
import LES1_2019.controle.web.vh.impl.ProdutoViewHelper;
import LES1_2019.controle.web.vh.impl.UserViewHelper;
import LES1_2019.controle.web.vh.impl.CartaoViewHelper;
import LES1_2019.controle.web.vh.impl.DadosDoProdutoViewHelper;
import LES1_2019.controle.web.vh.impl.EnderecoViewHelper;
import LES1_2019.controle.web.vh.impl.PedidoViewHelper;
import LES1_2019.core.aplicacao.Resultado;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Produto;
import LES1_2019.dominio.Categoria;
import LES1_2019.dominio.User;

public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> viewhelpers;
	
	public Servlet() {
		super();
		commands = new HashMap<String, ICommand>();
		viewhelpers = new HashMap<String, IViewHelper>();
		
		commands.put("SALVAR", new SalvarCommand());
		commands.put("ALTERAR", new AlterarCommand());
		commands.put("ALTERAR-SENHA", new AlterarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("INDEX", new ConsultarCommand());
		commands.put("VISUALIZAR", new VisualizarCommand());
		commands.put("ATIVAR", new AtivarCommand());
		commands.put("LOGAR", new LogarCommand());
		commands.put("CARDSESSION", new AddSessionCommand());
		commands.put("ENDSESSION", new AddSessionCommand());
		commands.put("USERSESSION", new AddSessionCommand());
		commands.put("ADDCARRINHO", new AlterarCommand());
		commands.put("VER-CARRINHO", new VisualizarCommand());
		commands.put("REMOVECARRINHO", new AlterarCommand());
		commands.put("FINALIZAR-COMPRA", new VisualizarCommand());
		commands.put("FAZER-PEDIDO", new SalvarCommand());
		commands.put("CONSULTAR-ADMIN", new ConsultarCommand());
		commands.put("CONFIRMAR-PEDIDO", new AlterarCommand());
		commands.put("NEGAR-PEDIDO", new AlterarCommand());
		
		
		viewhelpers.put("/LES1_2019_web/Produtos", new ProdutoViewHelper());
		viewhelpers.put("/LES1_2019_web/DadosProduto", new DadosDoProdutoViewHelper());
		viewhelpers.put("/LES1_2019_web/User", new UserViewHelper());
		viewhelpers.put("/LES1_2019_web/Enderecos", new EnderecoViewHelper());
		viewhelpers.put("/LES1_2019_web/Cartoes", new CartaoViewHelper());
		viewhelpers.put("/LES1_2019_web/Pedidos", new PedidoViewHelper());
	}
		  @Override
		    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
		    		IOException {
			  	doProcessRequest(request, response);
			  	 System.out.println("do get");
		    }
		  	
			/**
			 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			 */
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				System.out.println("do post");
				doProcessRequest(request, response);
			}
			
			
			protected void doProcessRequest(HttpServletRequest request, 
					HttpServletResponse response) throws ServletException, IOException {
				//Obtem a url que invocou esta servlet (O que foi definido no methdo do form html)
				String uri = request.getRequestURI();
				System.out.println(uri);
				
				//Obtem a operacao executada
				String operacao = request.getParameter("operacao");
				System.out.println(operacao);
				
							
				//Obtem uma viewhelper indexado pela uri que invocou esta servlet
				IViewHelper vh = viewhelpers.get(uri);
				//System.out.println(vh.getClass().getName());
				
				//O viewhelper retorna a entidade especifica para a tela que chamou esta servlet
				EntidadeDominio entidade =  vh.getEntidade(request);
				//System.out.println(entidade);
				
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
