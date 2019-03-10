package LES1_2019.core.impl.negocio;

import LES1_2019.core.IStrategy;
import LES1_2019.core.impl.dao.ProdutoDAO;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Produto;
import java.util.List;

//provavelmente desnecessario

public class ProdutoMesmoNome implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		if(entidade instanceof Produto) {
			Produto objProduto = (Produto)entidade;
			if(objProduto.getId()<= 0 || objProduto.getNome()==null) {
				return null;
			} else {
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
		}
		return null;
	}
	
}
