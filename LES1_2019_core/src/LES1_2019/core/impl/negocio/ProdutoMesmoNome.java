package LES1_2019.core.impl.negocio;

import java.util.List;

import LES1_2019.core.IStrategy;
import LES1_2019.core.impl.dao.ProdutoDAO;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Produto;

//provavelmente desnecessario

public class ProdutoMesmoNome implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		if(entidade instanceof Produto) {
			System.out.println("entrou na regra de negocio");
			Produto objProduto = (Produto)entidade;
			System.out.println(objProduto.getNome());
			
			ProdutoDAO objProdDao = new ProdutoDAO();
			Produto prod = new Produto();
			List<EntidadeDominio> dadosProduto = objProdDao.consultar(entidade);
				if(dadosProduto!=null) {
					for(EntidadeDominio p:dadosProduto) {
						prod = (Produto)p;
						if(prod.getNome().equals(objProduto.getNome())) {
							return "Ja existe um produto cadastrado com esse nome";
						}
					}
				}
			}
		return null;
	}
	
}
