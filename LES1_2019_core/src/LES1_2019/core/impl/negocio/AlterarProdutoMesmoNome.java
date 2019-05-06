package LES1_2019.core.impl.negocio;

import java.util.List;

import LES1_2019.core.IStrategy;
import LES1_2019.core.impl.dao.ProdutoDAO;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Produto;


public class AlterarProdutoMesmoNome implements IStrategy {

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
						if(prod.getNome().equals(objProduto.getNome())) {
							System.out.println("entrou no if de nomes");
							System.out.println("produto do form: "+objProduto.getNome() + objProduto.getId());
							System.out.println("produto do banco: "+prod.getNome() + prod.getId());
							if(prod.getId() == objProduto.getId()) {
								break;
							}
							else {
								return "O nome do produto alterado ja existe!"+objProduto.getId();
							}
						}
					}
				}
			}
		return null;
	}
	
}
