package LES1_2019.core.impl.negocio;

import LES1_2019.core.IStrategy;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Produto;

public class ValidarDadosProduto implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Produto) {
			System.out.println("entrou na regra de negocio");
			Produto objProduto = (Produto)entidade;
			String nome_produto = null;
			String descricao_produto = null;
			int marca_produto = 0;
						
			try {
				System.out.println("entrou no trycatch");
				nome_produto = objProduto.getNome();
				descricao_produto = objProduto.getDescricao_produto();
				marca_produto = objProduto.getId_marca_produto();
			}catch(Exception e) {
				
			}
			System.out.println("dps do trycatch");
			if(objProduto.getCategoria().getId() == null 
					|| nome_produto == ""
					|| descricao_produto == ""
					|| objProduto.getImg_produto() == null
					|| objProduto.getImg_produto() == ""
					|| marca_produto == 0) {
				System.out.println("entrou no IF dados invalidos");
				if(objProduto.getId()!=null) {
					return "Para cadastrar um produto, e necessario preencher todos os dados obrigatorios!"+objProduto.getId();
				}
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
