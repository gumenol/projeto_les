package LES1_2019.core.impl.negocio;

import java.util.List;

import LES1_2019.core.IStrategy;
import LES1_2019.core.impl.dao.ProdutoDAO;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Produto;

public class ValidarStatusProdutoAtivo implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		if(entidade instanceof Produto) {
			System.out.println("entrou na regra de negocio");
			Produto objProduto = (Produto)entidade;
			ProdutoDAO objProdDao = new ProdutoDAO();
			Produto prod = new Produto();
			
			List<EntidadeDominio> dadosProduto = objProdDao.consultar(entidade);
				if(dadosProduto!=null) {
					for(EntidadeDominio p:dadosProduto) {
						prod = (Produto)p;
						System.out.println(prod.getId());
						System.out.println(objProduto.getId());
						if(prod.getId()==(objProduto.getId())) {
							System.out.println("entrou no if de status");
							System.out.println(prod.getStatus_produto());
							if(prod.getStatus_produto() == true) {
								return "O produto já está com status de ATIVO!";
							}
						}
					}
				}
			}
		return null;
	}
	
}