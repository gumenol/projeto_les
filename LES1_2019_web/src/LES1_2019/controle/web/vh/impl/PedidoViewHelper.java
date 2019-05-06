package LES1_2019.controle.web.vh.impl;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import LES1_2019.dominio.*;
import LES1_2019.controle.web.vh.IViewHelper;
import LES1_2019.core.aplicacao.Resultado;
import LES1_2019.core.impl.dao.UserDAO;

public class PedidoViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Pedido pedido = new Pedido();
		boolean flgTroca;
		Produto dados;
		/*CupomTroca = new CupomTroca();*/
		Resultado resultado = (Resultado) request.getSession().getAttribute("login");
		if(request.getSession().getAttribute("login")!=null) {
		List<EntidadeDominio> us = resultado.getEntidades();
		}else {
			pedido.setStatus("Erro");
			return pedido;
		}
		List<EntidadeDominio> us = resultado.getEntidades();		
		User user = (User)us.get(0);
		ArrayList<Produto> produtos;
		double total = 0;
		int qtd = 0;
		double subtotal = 0;
		
		if(operacao.equals("CONSULTAR")) {
			
			Pedido ped = new Pedido();
			
			try {
				ped.getUser().setId(Integer.parseInt(request.getParameter("idusr")));
				System.out.println(ped.getUser().getId());
			} catch (Exception e){
					
					}
			return ped;
			
		}
		
		
		if(operacao.equals("VISUALIZAR")) {
			
			Pedido ped = new Pedido();
			
			try {
				ped.setId(Integer.parseInt(request.getParameter("id-pedido")));
			} catch (Exception e){
					
					}
			return ped;
			
		}
		
		
		if(operacao.equals("CONFIRMAR-PEDIDO")) {
			Pedido ped = new Pedido();
			try {
				ped.setId(Integer.parseInt(request.getParameter("id-pedido")));
			} catch (Exception e){
					
					}
			try {
				ped.setStatus(request.getParameter("status-pedido"));
			}catch(Exception e) {
				
			}
			
			try {
				ped.setStatus_troca_pedido(false);
			}catch(Exception e) {
				
			}
			
			
			return ped;
			
		}
		
		if(operacao.equals("NEGAR-PEDIDO")) {
			Pedido ped = new Pedido();
			try {
				ped.setId(Integer.parseInt(request.getParameter("id-pedido")));
			} catch (Exception e){
					
					}
			try {
				ped.setStatus("Pedido negado");
			}catch(Exception e) {
				
			}
			
			try {
				ped.setStatus_troca_pedido(false);
			}catch(Exception e) {
				
			}
			
			
			return ped;
			
		}
		
		
		
		if(operacao.equals("FAZER-PEDIDO")) {
			pedido = (Pedido) request.getSession().getAttribute("carrinho");
			
			Cartao card1 = new Cartao();
			Cartao card2 = new Cartao();
			ArrayList<Cartao> cartoes = new ArrayList<Cartao>();
			Endereco endCobranca = new Endereco();
			Endereco endEntrega = new Endereco();
			if(user.getEnderecos().size()==1) {
				System.out.println("ENTROU ENDERECO UNICOOOOOO");
				endCobranca.setCep(user.getEnderecos().get(0).getCep());
				endCobranca.setNumero(user.getEnderecos().get(0).getNumero());
				pedido.setEndEntrega(endCobranca);
				pedido.setEndCobranca(endCobranca);
			} else {
				endCobranca.setCep(user.getEnderecos().get(0).getCep());
				endCobranca.setNumero(user.getEnderecos().get(0).getNumero());
				pedido.setEndCobranca(endCobranca);
				System.out.println("CEP cobranca: "+pedido.getEndCobranca().getCep());
				System.out.println("Numero cobranca: "+pedido.getEndCobranca().getNumero());
				
				endEntrega.setCep(user.getEnderecos().get(1).getCep());
				endEntrega.setNumero(user.getEnderecos().get(1).getNumero());
				pedido.setEndEntrega(endEntrega);
				System.out.println("CEP entrega: "+pedido.getEndEntrega().getCep());
				System.out.println("Numero Entrega: "+pedido.getEndEntrega().getNumero());
			}
			String usarcartao1 = "";
			String usarcartao2 = "";
			double valorpago2 = 0;
			double valorpago1 = 0;
	
			usarcartao1 = request.getParameter("usarcartao1");
			
			usarcartao2 = request.getParameter("usarcartao2");
			
			if(request.getParameter("valor-cartao2")!=null) {
				if(request.getParameter("valor-cartao2")!="") {
					System.out.println("entrou aqui essa porra"+request.getParameter("valor-cartao2"));
					valorpago2 = Double.parseDouble(request.getParameter("valor-cartao2"));
				}
			}
			if(request.getParameter("valor-cartao1")!=null) {
				if(request.getParameter("valor-cartao1")!="") {
					valorpago1 = Double.parseDouble(request.getParameter("valor-cartao1"));
				}
			}
			if(user.getCartoes().size()==1) {
				if(usarcartao1!=null) {
					if(usarcartao1.equals("sim")) {
						card1.setNumero_cartao(user.getCartoes().get(0).getNumero_cartao());
						card1.setId_bandeira_cartao(user.getCartoes().get(0).getId_bandeira_cartao());
						card1.setValor_pago(valorpago1);
						cartoes.add(card1);
						pedido.setCartoes(cartoes);
					}
				}
			}else {
				System.out.println("ENTROU CARTOES 1");
				if(usarcartao1!=null) {
					if(usarcartao1.equals("sim")) {
						card1.setNumero_cartao(user.getCartoes().get(0).getNumero_cartao());
						card1.setId_bandeira_cartao(user.getCartoes().get(0).getId_bandeira_cartao());
						card1.setValor_pago(valorpago1);
						cartoes.add(card1);
						System.out.println(card1.getNumero_cartao());
						System.out.println(card1.getValor_pago());
					}
				}
				if(usarcartao2!=null) {
					if(usarcartao2.equals("sim")) {
						System.out.println("ENTROU CARTOES 2");
						card2.setNumero_cartao(user.getCartoes().get(1).getNumero_cartao());
						card2.setId_bandeira_cartao(user.getCartoes().get(1).getId_bandeira_cartao());
						card2.setValor_pago(valorpago2);
						cartoes.add(card2);
						System.out.println(card2.getNumero_cartao());
						System.out.println(card2.getValor_pago());
					}
				}
				pedido.setCartoes(cartoes);
			}
			pedido.setStatus_troca_pedido(false);
			return pedido;
		}
		
		if(operacao.equals("REMOVECARRINHO")) {
			pedido = (Pedido) request.getSession().getAttribute("carrinho");
			produtos = pedido.getProdutos();
				for(Produto produto:produtos) {
					if(produto.getId() == Integer.parseInt(request.getParameter("id-produto"))) {
						produto.setRemover(true);
						produto.setQtd_estoque(1);
						produto.setQtdremover(Integer.parseInt(request.getParameter("qtd")));
						pedido.setId_item_atual(Integer.parseInt(request.getParameter("id-produto")));
					}
				}
				pedido.setProdutos(produtos);
				
				return pedido;
			}
		
		if(operacao.equals("ADDCARRINHO")) {
			if(request.getSession().getAttribute("carrinho") != null) {
				pedido = (Pedido) request.getSession().getAttribute("carrinho");
				produtos = pedido.getProdutos();
				Produto produto = new Produto();
				produtos = pedido.getProdutos();
				for(Produto prod:pedido.getProdutos()) {
					if(prod.getId() == Integer.parseInt(request.getParameter("id-produto"))) {
						System.out.println("----------------");
						
						if(request.getParameter("qtd-estoque")!=""){
							if(request.getParameter("qtd-estoque")!=null) {
							prod.setQtd_estoque(Integer.parseInt(request.getParameter("qtd-estoque")));
							prod.setQtd_pedido(Integer.parseInt(request.getParameter("qtd")));
								if(prod.getQtd_pedido()>prod.getQtd_estoque()) {
									prod.setId(Integer.parseInt(request.getParameter("id-produto")));
									produtos.add(produto);
									pedido.setProdutos(produtos);
									return pedido;
								}
							}
						}
						prod.setQtd_pedido(Integer.parseInt(request.getParameter("qtd")));
						produto.setQtd_estoque(0);
						prod.setQtdtotal(prod.getQtdtotal()+Integer.parseInt(request.getParameter("qtd")));
						prod.setQtd_pedido_anterior(prod.getQtd_pedido());
						System.out.println("VALOR ATUAL DO PEDIDO: "+pedido.getValor_total());
						qtd = Integer.parseInt(request.getParameter("qtd")) + (prod.getQtd_pedido()-prod.getQtd_pedido_anterior());
						System.out.println("QUANTIDADE DO PEDIDO ATUAL:"+prod.getQtd_pedido());
						
						System.out.println("QUANTIDADE ANTERIOR: "+prod.getQtd_pedido_anterior());
						System.out.println("----------------");
						
						pedido.setId_item_atual(Integer.parseInt(request.getParameter("id-produto")));
						subtotal = qtd* prod.getValor_produto();
						System.out.println("SUBTOTAL = "+qtd+" * "+prod.getValor_produto()+" = "+ subtotal);
						total = pedido.getValor_total();
						System.out.println("TOTAL pedido: "+ total);
						total += subtotal;
						System.out.println("TOTAL + SUB: "+total);
						prod.setSubtotal(prod.getValor_produto()*prod.getQtdtotal());
						prod.setQtd_pedido(qtd);
						produto.setQtdremover(0);
						prod.setRemover(false);
						pedido.setValor_total(total);
						return pedido;
					}
				}
			
				if(request.getParameter("qtd-estoque")!=""){
					if(request.getParameter("qtd-estoque")!=null) {
					produto.setQtd_estoque(Integer.parseInt(request.getParameter("qtd-estoque")));
					produto.setQtd_pedido(Integer.parseInt(request.getParameter("qtd")));
						if(produto.getQtd_pedido()>produto.getQtd_estoque()) {
							produto.setId(Integer.parseInt(request.getParameter("id-produto")));
							produtos.add(produto);
							pedido.setProdutos(produtos);
							return pedido;
						}
					}
				}
				
				produto.setQtd_pedido(Integer.parseInt(request.getParameter("qtd")));
				produto.setQtd_estoque(0);
				produto.setQtd_pedido_anterior(0);
				pedido.setId_item_atual(Integer.parseInt(request.getParameter("id-produto")));
				produto.setId(Integer.parseInt(request.getParameter("id-produto")));
				produto.setNome(request.getParameter("nome-produto"));
				produto.setValor_produto(Double.parseDouble(request.getParameter("valor-produto")));
				produto.setQtdtotal(produto.getQtd_pedido());
				produto.setQtdremover(0);
				produto.setSubtotal(produto.getValor_produto()*produto.getQtd_pedido());
				total = pedido.getValor_total() + produto.getSubtotal();
				produto.setRemover(false);
				pedido.setValor_total(total);
				produtos.add(produto);
				pedido.setProdutos(produtos);
				
				return pedido;
			} else {
				
				Pedido ped = new Pedido();
				ped.setUser(new User());
				
				produtos = new ArrayList<Produto>();
				Produto produto = new Produto();
				
				if(request.getParameter("qtd-estoque")!=""){
					if(request.getParameter("qtd-estoque")!=null) {
					produto.setQtd_estoque(Integer.parseInt(request.getParameter("qtd-estoque")));
					produto.setQtd_pedido(Integer.parseInt(request.getParameter("qtd")));
						if(produto.getQtd_pedido()>produto.getQtd_estoque()) {
							produto.setId(Integer.parseInt(request.getParameter("id-produto")));
							produtos.add(produto);
							ped.setProdutos(produtos);
							return ped;
						}
					}
				}
				
				produto.setQtd_pedido(Integer.parseInt(request.getParameter("qtd")));
				produto.setQtd_estoque(0);
				ped.getUser().setNome_usuario(user.getNome_usuario());
				ped.getUser().setId(user.getId());
				ped.setId_item_atual(Integer.parseInt(request.getParameter("id-produto")));
				produto.setId(Integer.parseInt(request.getParameter("id-produto")));
				produto.setNome(request.getParameter("nome-produto"));
				produto.setValor_produto(Double.parseDouble(request.getParameter("valor-produto")));
				produto.setQtd_pedido_anterior(0);
				produto.setQtdremover(0);
				produto.setQtd_pedido(Integer.parseInt(request.getParameter("qtd")));
				produto.setQtdtotal(produto.getQtd_pedido());
				produto.setSubtotal(produto.getQtd_pedido()*Double.parseDouble(request.getParameter("valor-produto")));
				produto.setRemover(false);
				
				produtos.add(produto);
				ped.setProdutos(produtos);
				ped.setValor_total(produto.getSubtotal());
				return ped;
			}
		}
		
		return pedido;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMensagem() == null && operacao.equals("ADDCARRINHO")) {
			Pedido pedido = (Pedido) resultado.getEntidades().get(0);
			for(Produto prod:pedido.getProdutos()) {
				if(prod.isRemover()) {
					prod.setRemover(false);	
					break;
				}
			}
			resultado.getEntidades().remove(0);
			resultado.getEntidades().add(pedido);
			request.getSession().setAttribute("carrinho", resultado.getEntidades().get(0));
			
			d = request.getRequestDispatcher("add-carrinho.jsp");
		}
		
		if(resultado.getMensagem() != null && operacao.equals("ADDCARRINHO")) {
			String id = resultado.getMensagem().substring(0, 2);
			String msg = resultado.getMensagem().substring(2);
			id = id.trim();
			System.out.println("id-produto: "+id);
			resultado.setMensagem(msg);
			request.getSession().setAttribute("erro", resultado);
			d = request.getRequestDispatcher("Produtos?id-produto="+id+"&operacao=VISUALIZAR");
		}
		
		
		if(resultado.getMensagem() == null && operacao.equals("REMOVECARRINHO")) {
			Pedido pedido = (Pedido) resultado.getEntidades().get(0);
			for(Produto prod:pedido.getProdutos()) {
				if(prod.isRemover()) {
					prod.setQtdtotal(prod.getQtdtotal()-prod.getQtdremover());
					pedido.setValor_total(pedido.getValor_total()-(prod.getValor_produto()*prod.getQtdremover()));
					prod.setSubtotal(prod.getValor_produto()*prod.getQtdtotal());
					if(prod.getQtdtotal()==0) {
						pedido.getProdutos().remove(prod);
						break;
					}
					
				}
			}
			resultado.getEntidades().remove(0);
			resultado.getEntidades().add(pedido);
			request.getSession().setAttribute("carrinho", resultado.getEntidades().get(0));
			
			d = request.getRequestDispatcher("addd-carrinho.jsp");
		}
		
		if(resultado.getMensagem() != null && operacao.equals("REMOVECARRINHO")) {
			
			request.getSession().setAttribute("erro", resultado);
			
			d = request.getRequestDispatcher("meu-carrinho.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("VER-CARRINHO")) {
			
			request.getSession().setAttribute("visualizar", resultado);
			d = request.getRequestDispatcher("meu-carrinho.jsp");
			}
		
		if(resultado.getMensagem() == null && operacao.equals("FINALIZAR-COMPRA")) {
			
			request.getSession().setAttribute("visualizar", resultado);
			d = request.getRequestDispatcher("finalizar-compra.jsp");
			}
		
		if(resultado.getMensagem() == null && operacao.equals("FAZER-PEDIDO")) {
			
			request.getSession().setAttribute("pedidook", resultado);
			d = request.getRequestDispatcher("pedido-realizado.jsp");
			}
		
		if(resultado.getMensagem() != null && operacao.equals("FAZER-PEDIDO")) {
			
			request.getSession().setAttribute("erro", resultado);
			d = request.getRequestDispatcher("finalizar-compra.jsp");
			}

		if(resultado.getMensagem() == null && operacao.equals("CONSULTAR")) {
		
			request.getSession().setAttribute("dadosPedido", resultado);
			d = request.getRequestDispatcher("meus-dados-pedidos.jsp");
		}
		if(resultado.getMensagem() == null && operacao.equals("CONSULTAR-ADMIN")) {
			
			request.getSession().setAttribute("dadosPedido", resultado);
			d = request.getRequestDispatcher("dashboard-admin-visualizar-pedidos.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("CONFIRMAR-PEDIDO")) {
			
			request.getSession().setAttribute("pedidoConfirmado", resultado);
			d = request.getRequestDispatcher("pedido-atualizado.jsp");
			
			}
		
		if(resultado.getMensagem() == null && operacao.equals("VISUALIZAR")) {
			
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("pedido.jsp");
			
			}
		
		if(resultado.getMensagem() == null && operacao.equals("NEGAR-PEDIDO")) {
			
			request.getSession().setAttribute("pedidoNegado", resultado);
			d = request.getRequestDispatcher("pedido-atualizado.jsp");
			
			}
		
		d.forward(request, response);
	}
}