package LES1_2019.core.impl.negocio;

import LES1_2019.core.IStrategy;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Produto;

public class ValidarValorProduto implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Produto) {
			Produto objProduto = (Produto)entidade;
			double valor_produto = 0;
			try {
				valor_produto = objProduto.getValor_produto();
			} catch (Exception e) {
				
			}
			if(valor_produto == 0 || valor_produto < 0) {
				if(objProduto.getId()!=null) {
				return "Valor do produto preenchido incorretamente!"+objProduto.getId();
				}
				return "Valor do produto preenchido incorretamente!";
			}
		}
		else {
			return "Opcao valida apenas para valores de PRODUTOS!";
		}
		return null;
	}

}
