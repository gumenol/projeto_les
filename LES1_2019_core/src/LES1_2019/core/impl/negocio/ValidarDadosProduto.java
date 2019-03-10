package LES1_2019.core.impl.negocio;

import LES1_2019.core.IStrategy;
import LES1_2019.dominio.Categoria;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Produto;

public class ValidarDadosProduto implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Produto) {
			System.out.println("entrou na regra de negocio");
			Produto objProduto = (Produto)entidade;
			Categoria objCategoria = null;
			String nome_produto = null;
			String descricao_produto = null;
			double valor_produto = 0;
			String marca_produto = null;
			
			try {
				System.out.println("entrou no trycatch");
				objCategoria = objProduto.getCategoria();
				nome_produto = objProduto.getNome();
				descricao_produto = objProduto.getDescricao_produto();
				valor_produto = objProduto.getValor_produto();
				marca_produto = objProduto.getMarca_produto();
			}catch(Exception e) {
				
			}
			System.out.println("dps do trycatch");
			if(objCategoria == null 
					|| nome_produto == null
					|| descricao_produto == null
					|| marca_produto == null
					|| valor_produto < 0) {
				System.out.println("entrou no IF cu");
				return "Para cadastrar um produto, e necessario preencher todos os dados obrigatorios!";
			}
			System.out.println("antes do if de equals");
			if(nome_produto.trim().equals("")
					|| descricao_produto.equals("")
					|| marca_produto.equals("")) {
				System.out.println("dentro do if de equals");
				return "Para cadastrar um produto, e necessario preencher todos os dados obrigatorios!";
			}
		}
		else {
			return "O cadastro de um produto é necessário!";
		}
		System.out.println("retornou nulo");
		return null;
	}
}
