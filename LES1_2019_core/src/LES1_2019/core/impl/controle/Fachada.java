package LES1_2019.core.impl.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import LES1_2019.core.IDAO;
//import geral
import LES1_2019.core.IFachada;
import LES1_2019.core.IStrategy;
//import resultado
import LES1_2019.core.aplicacao.Resultado;
//import daos
import LES1_2019.core.impl.dao.CategoriaDAO;
import LES1_2019.core.impl.dao.ProdutoDAO;
import LES1_2019.core.impl.negocio.ProdutoMesmoNome;
import LES1_2019.core.impl.negocio.ValidarDadosCategoria;
//import regras de negocio
import LES1_2019.core.impl.negocio.ValidarDadosProduto;
import LES1_2019.dominio.Categoria;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Produto;


public class Fachada implements IFachada {
	private Map<String, IDAO> daos;
	private Map<String, Map<String, List<IStrategy>>> rns;
	private Resultado objResultado;
	
	public Fachada() {
		daos = new HashMap<String, IDAO>();
		rns = new HashMap<String, Map<String, List<IStrategy>>>();
		
		CategoriaDAO objCategoriaDAO = new CategoriaDAO();
		ProdutoDAO objProdutoDAO = new ProdutoDAO();
		
		daos.put(Categoria.class.getName(), objCategoriaDAO);
		daos.put(Produto.class.getName(), objProdutoDAO);
		
		//criando objetos das classes de regras de negocio
		ValidarDadosProduto objValidarDadosProduto = new ValidarDadosProduto();
		ProdutoMesmoNome objProdutoMesmoNome = new ProdutoMesmoNome();
		ValidarDadosCategoria objValidarDadosCategoria = new ValidarDadosCategoria();
		
		//adicionando os objetos das classes de regras a array list de salvar produtos
		List<IStrategy> rnsSalvarProduto = new ArrayList<IStrategy>();
		rnsSalvarProduto.add(objValidarDadosProduto);
		rnsSalvarProduto.add(objProdutoMesmoNome);
		
		//adicionando os objetos das classes de regras a array list de salvar categorias
		List<IStrategy> rnsSalvarCategoria = new ArrayList<IStrategy>();
		rnsSalvarCategoria.add(objValidarDadosCategoria);
		
		//criando um hasmap das listas com as regras de negocio
		Map<String, List<IStrategy>> rnsProduto = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsCategoria = new HashMap<String, List<IStrategy>>();
		
		//adicionando as operacoes que irao utilizar o map com a lista de regras
		rnsProduto.put("SALVAR", rnsSalvarProduto);
		
		rnsCategoria.put("SALVAR", rnsSalvarCategoria);
		rnsCategoria.put("ALTERAR", rnsSalvarCategoria);
		
		rns.put(Produto.class.getName(), rnsProduto);
		rns.put(Categoria.class.getName(), rnsCategoria);
		
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
						msg.append("\n");
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
		String msg = executarRegras(entidade, "SALVAR");
		System.out.println(msg);
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
		objResultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		String msg = executarRegras(entidade, "ALTERAR");
		if(msg == null) {
			IDAO objDao = daos.get(nmClasse);
			try {
				objDao.alterar(entidade);
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
	public Resultado consultar(EntidadeDominio entidade) {
		objResultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		String msg = executarRegras(entidade, "CONSULTAR");
		
		if(msg == null) {
			IDAO objDao = daos.get(nmClasse);
			try {
				objResultado.setEntidades(objDao.consultar(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				objResultado.setMensagem("Nao foi possivel fazer a consulta!");
			}
		} else {
			objResultado.setMensagem(msg);
		}
		return objResultado;
	}

	@Override
	public Resultado visualizar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		objResultado = new Resultado();
		objResultado.setEntidades(new ArrayList<EntidadeDominio>(1));
		objResultado.getEntidades().add(entidade);
		return objResultado;
	}
	
}