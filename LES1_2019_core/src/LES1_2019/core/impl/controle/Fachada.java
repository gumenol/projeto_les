package LES1_2019.core.impl.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import LES1_2019.auxiliar.DadosDeCadastro;
import LES1_2019.core.IDAO;
//import geral
import LES1_2019.core.IFachada;
import LES1_2019.core.IStrategy;
//import resultado
import LES1_2019.core.aplicacao.Resultado;
import LES1_2019.core.impl.dao.CartaoDAO;
//import daos
import LES1_2019.core.impl.dao.CategoriaDAO;
import LES1_2019.core.impl.dao.DadosParaCadastroDAO;
import LES1_2019.core.impl.dao.EnderecoDAO;
import LES1_2019.core.impl.dao.PedidoDAO;
import LES1_2019.core.impl.dao.ProdutoDAO;
import LES1_2019.core.impl.dao.UserDAO;
//import regras de negocio
import LES1_2019.core.impl.negocio.AddSession;
import LES1_2019.core.impl.negocio.AlterarProdutoMesmoNome;
import LES1_2019.core.impl.negocio.AlterarSenhaUser;
import LES1_2019.core.impl.negocio.Carrinho;
import LES1_2019.core.impl.negocio.ProdutoMesmoNome;
import LES1_2019.core.impl.negocio.UserLogin;
import LES1_2019.core.impl.negocio.ValidarCpfNumeroCasaUsuario;
import LES1_2019.core.impl.negocio.ValidarDadosCartao;
import LES1_2019.core.impl.negocio.ValidarDadosProduto;
import LES1_2019.core.impl.negocio.ValidarDadosUsuario;
import LES1_2019.core.impl.negocio.ValidarEnderecoUsuario;
import LES1_2019.core.impl.negocio.ValidarPagamento;
import LES1_2019.core.impl.negocio.ValidarStatusProdutoAtivo;
import LES1_2019.core.impl.negocio.ValidarStatusProdutoInativo;
import LES1_2019.core.impl.negocio.ValidarValidadeCartao;
import LES1_2019.core.impl.negocio.ValidarValorProduto;
import LES1_2019.dominio.Cartao;
import LES1_2019.dominio.Categoria;
import LES1_2019.dominio.Endereco;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Pedido;
import LES1_2019.dominio.Produto;
import LES1_2019.dominio.User;


public class Fachada implements IFachada {
	private Map<String, IDAO> daos;
	private Map<String, Map<String, List<IStrategy>>> rns;
	private Resultado objResultado;
	
	public Fachada() {
		daos = new HashMap<String, IDAO>();
		rns = new HashMap<String, Map<String, List<IStrategy>>>();
		
		CategoriaDAO objCategoriaDAO = new CategoriaDAO();
		ProdutoDAO objProdutoDAO = new ProdutoDAO();
		DadosParaCadastroDAO objDadosProduto = new DadosParaCadastroDAO();
		UserDAO objUserDao = new UserDAO();
		EnderecoDAO objEnderecoDAO = new EnderecoDAO();
		CartaoDAO objCartaoDAO = new CartaoDAO();
		PedidoDAO objPedidoDAO = new PedidoDAO();
		
		daos.put(Categoria.class.getName(), objCategoriaDAO);
		daos.put(Produto.class.getName(), objProdutoDAO);
		daos.put(DadosDeCadastro.class.getName(), objDadosProduto);
		daos.put(User.class.getName(), objUserDao);
		daos.put(Endereco.class.getName(), objEnderecoDAO);
		daos.put(Cartao.class.getName(), objCartaoDAO);
		daos.put(Pedido.class.getName(), objPedidoDAO);
		
		//criando objetos das classes de regras de negocio
		ValidarDadosProduto objValidarDadosProduto = new ValidarDadosProduto();
		ValidarValorProduto objValidarValorProduto = new ValidarValorProduto();
		ProdutoMesmoNome objProdutoMesmoNome = new ProdutoMesmoNome();
		//ValidarDadosCategoria objValidarDadosCategoria = new ValidarDadosCategoria();
		AlterarProdutoMesmoNome objAlterarProdutoMesmoNome = new AlterarProdutoMesmoNome();
		ValidarStatusProdutoInativo objValidarStatusProdutoInativo = new ValidarStatusProdutoInativo();
		ValidarStatusProdutoAtivo objValidarStatusProdutoAtivo = new ValidarStatusProdutoAtivo();
		UserLogin objUserLogin = new UserLogin();
		ValidarDadosUsuario objValidarDadosUsuario = new ValidarDadosUsuario();
		ValidarCpfNumeroCasaUsuario objValidarCpfNumeroUsuario = new ValidarCpfNumeroCasaUsuario();
		AlterarSenhaUser objAlterarSenhaUser = new AlterarSenhaUser();
		ValidarEnderecoUsuario objValidarEndereco = new ValidarEnderecoUsuario();
		AddSession objAddSession = new AddSession();
		ValidarDadosCartao objValidarDadosCartao = new ValidarDadosCartao();
		ValidarValidadeCartao objValidadeCartao = new ValidarValidadeCartao();
		Carrinho objCarrinho = new Carrinho();
		ValidarPagamento objPagamento = new ValidarPagamento();
		
		//adicionando os objetos das classes de regras a array list de salvar produtos
		List<IStrategy> rnsSalvarProduto = new ArrayList<IStrategy>();
		rnsSalvarProduto.add(objValidarDadosProduto);
		rnsSalvarProduto.add(objProdutoMesmoNome);
		rnsSalvarProduto.add(objValidarValorProduto);
		
		//alterar produto
		List<IStrategy> rnsAlterarProduto = new ArrayList<IStrategy>();
		rnsAlterarProduto.add(objAlterarProdutoMesmoNome);
		rnsAlterarProduto.add(objValidarValorProduto);
		rnsAlterarProduto.add(objValidarDadosProduto);
		
		//excluir produto
		List<IStrategy> rnsExcluirProduto = new ArrayList<IStrategy>();
		rnsExcluirProduto.add(objValidarStatusProdutoInativo);
		
		//ativar produto
		List<IStrategy> rnsAtivarProduto = new ArrayList<IStrategy>();
		rnsAtivarProduto.add(objValidarStatusProdutoAtivo);
		
		//login do usuario
		List<IStrategy>rnsUserLogin = new ArrayList<IStrategy>();
		rnsUserLogin.add(objUserLogin);
		
		//cadastro usuario
		List<IStrategy>rnsCadUser = new ArrayList<IStrategy>();
		rnsCadUser.add(objValidarDadosUsuario);
		rnsCadUser.add(objValidarCpfNumeroUsuario);
		
		List<IStrategy>rnsAddSession = new ArrayList<IStrategy>();
		rnsAddSession.add(objAddSession);
		
		//alteracao usuario
		List<IStrategy>rnsAlterarSenhaUser = new ArrayList<IStrategy>();
		rnsAlterarSenhaUser.add(objAlterarSenhaUser);
		rnsAlterarSenhaUser.add(objValidarCpfNumeroUsuario);
		
		//Alterar endereco usuario cadastrado
		List<IStrategy>rnsAlterarEndereco = new ArrayList<IStrategy>();
		rnsAlterarEndereco.add(objValidarEndereco);
		
		List<IStrategy>rnsValidarCartao = new ArrayList<IStrategy>();
		rnsValidarCartao.add(objValidarDadosCartao);
		rnsValidarCartao.add(objValidadeCartao);
		
		List<IStrategy>rnsCarrinho = new ArrayList<IStrategy>();
		rnsCarrinho.add(objCarrinho);
		
		List<IStrategy>rnsPagamento = new ArrayList<IStrategy>();
		rnsPagamento.add(objPagamento);
		
		/*
		 * //adicionando os objetos das classes de regras a array list de salvar
		 * categorias List<IStrategy> rnsSalvarCategoria = new ArrayList<IStrategy>();
		 * rnsSalvarCategoria.add(objValidarDadosCategoria);
		 */
		//criando um hasmap das listas com as regras de negocio
		Map<String, List<IStrategy>> rnsProduto = new HashMap<String, List<IStrategy>>();
		/*
		 * Map<String, List<IStrategy>> rnsCategoria = new HashMap<String,
		 * List<IStrategy>>();
		 */
		Map<String, List<IStrategy>> rnsUser = new HashMap<String, List<IStrategy>>();
		
		Map<String,List<IStrategy>> rnsEndereco = new HashMap<String, List<IStrategy>>();
		
		Map<String, List<IStrategy>> rnsCartao = new HashMap<String, List<IStrategy>>();
		
		Map<String, List<IStrategy>> rnsPedido = new HashMap<String, List<IStrategy>>();
		
		//adicionando as operacoes que irao utilizar o map com a lista de regras
		rnsProduto.put("SALVAR", rnsSalvarProduto);
		rnsProduto.put("ALTERAR", rnsAlterarProduto);
		rnsProduto.put("EXCLUIR", rnsExcluirProduto);
		rnsProduto.put("ATIVAR", rnsAtivarProduto);
		
		//regras user
		rnsUser.put("LOGAR", rnsUserLogin);
		rnsUser.put("SALVAR", rnsCadUser);
		rnsUser.put("ALTERAR", rnsAlterarSenhaUser);
		rnsUser.put("SESSION", rnsAddSession);
		
		rnsEndereco.put("SALVAR", rnsAlterarEndereco);
		rnsEndereco.put("ALTERAR", rnsAlterarEndereco);
		
		rnsCartao.put("SALVAR", rnsValidarCartao);
		rnsCartao.put("ALTERAR", rnsValidarCartao);
		
		rnsPedido.put("ALTERAR", rnsCarrinho);
		rnsPedido.put("SALVAR", rnsPagamento);
		
		/*
		 * rnsCategoria.put("SALVAR", rnsSalvarCategoria); rnsCategoria.put("ALTERAR",
		 * rnsSalvarCategoria);
		 */
		
		rns.put(Produto.class.getName(), rnsProduto);
		/* rns.put(Categoria.class.getName(), rnsCategoria); */
		rns.put(User.class.getName(), rnsUser);
		rns.put(Endereco.class.getName(), rnsEndereco);
		rns.put(Cartao.class.getName(), rnsCartao);
		rns.put(Pedido.class.getName(), rnsPedido);
		
	}
	
	private String executarRegras(EntidadeDominio entidade, String operacao) {
		String nmClasse = entidade.getClass().getName();
		StringBuilder msg = new StringBuilder();
		
		Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);
		
		if(regrasOperacao != null) {
			List<IStrategy> regras = regrasOperacao.get(operacao);
			
			if(regras != null) {
				for(IStrategy s:regras) {
					String m = s.processar(entidade);
					System.out.println(m);
					if(m != null) {
						msg.append(m);
						break;
					}
				}
			}
		}
		
		if(msg.length()>0)
			return msg.toString();
		else
			return null;
	}

	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		objResultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		System.out.println(nmClasse);
		System.out.println(entidade);
		String msg = executarRegras(entidade, "SALVAR");
		System.out.println("antes da dao");
		if(msg == null) {
			IDAO objDao = daos.get(nmClasse);
			try {
				System.out.println(objDao);
				objDao.salvar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				objResultado.setEntidades(entidades);
			} catch (SQLException e){
				e.printStackTrace();
				objResultado.setMensagem("Nao foi possivel salvar!");
			}
		} else {
			objResultado.setMensagem(msg);
		}
		return objResultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		objResultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		
		String msg = executarRegras(entidade, "EXCLUIR");
		
		if(msg == null) {
			IDAO objDao = daos.get(nmClasse);
			try {
				objDao.excluir(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				objResultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				objResultado.setMensagem("Nao foi possivel salvar!");
			}
		} else {
			objResultado.setMensagem(msg);
		}
		return objResultado;
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		System.out.println("ENTROU ALTERAR");
		objResultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		String msg = executarRegras(entidade, "ALTERAR");
		if(msg == null) {
			IDAO objDao = daos.get(nmClasse);
			try {
				System.out.println("ENTROU ANTES DAO");
				objDao.alterar(entidade);
				System.out.println("PASSOU DA DAO");
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				objResultado.setEntidades(entidades);
			} catch (SQLException e){
				e.printStackTrace();
				objResultado.setMensagem("Nao foi possivel alterar!");
			}
		} else {
			objResultado.setMensagem(msg);
		}
		return objResultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		objResultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		String msg = executarRegras(entidade, "CONSULTAR");
		if(msg == null) {
			IDAO objDao = daos.get(nmClasse);
			System.out.println(nmClasse);
			System.out.println("criou obj de dao");
			try {
				List<EntidadeDominio> consultar = objDao.consultar(entidade);
				objResultado.setEntidades(consultar);
				System.out.println("setou entidade");
			} catch (SQLException e) {
				e.printStackTrace();
				objResultado.setMensagem("Nao foi possivel fazer a consulta!");
				System.out.println("erro dao consulta");
			}
		} else {
			objResultado.setMensagem(msg);
			System.out.println("setou mensagem nula");
		}
		System.out.println("retornou");
		return objResultado;
	}

	@Override
	public Resultado visualizar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		objResultado = new Resultado();
		objResultado.setEntidades(new ArrayList<EntidadeDominio>(0));
		objResultado.getEntidades().add(entidade);
		return objResultado;
	}
	
	@Override
	public Resultado ativar(EntidadeDominio entidade) {
		System.out.println("entrou fachada ativar");
		objResultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		
		String msg = executarRegras(entidade, "ATIVAR");
		
		if(msg == null) {
			IDAO objDao = daos.get(nmClasse);
			try {
				objDao.ativar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				objResultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				objResultado.setMensagem("Nao foi possivel salvar!");
			}
		} else {
			objResultado.setMensagem(msg);
		}
		return objResultado;
	}
	
	@Override
	public Resultado logar(EntidadeDominio entidade) {
		objResultado = new Resultado();
		System.out.println("ENTROU FACHADA");
		String msg = executarRegras(entidade, "LOGAR");
		if(msg == null) {
			List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			entidades.add(entidade);
			objResultado.setEntidades(entidades);
		} else {
			objResultado.setMensagem(msg);
		}
		return objResultado;
	}
	
	@Override
	public Resultado session(EntidadeDominio entidade) {
		objResultado = new Resultado();
		String msg = executarRegras(entidade, "SESSION");
		if(msg == null) {
			List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			entidades.add(entidade);
			objResultado.setEntidades(entidades);
		} else {
			objResultado.setMensagem(msg);
		}
		return objResultado;
	}
	
}