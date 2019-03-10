package LES1_2019.core.impl.negocio;

import LES1_2019.core.IStrategy;
import LES1_2019.dominio.Categoria;
import LES1_2019.dominio.EntidadeDominio;

public class ValidarDadosCategoria implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Categoria) {
			Categoria objCategoria = (Categoria)entidade;
			String nome_categoria = null;
			
			try {
				nome_categoria = objCategoria.getNome();
			}catch(Exception e) {
				
			}
			
			if(nome_categoria == null) {
				return "Para cadastrar uma categoria, e necessario preencher todos os dados obrigatorios!";
			}
			if(nome_categoria.trim().equals("")) {
				return "Para cadastrar um produto, e necessario preencher todos os dados obrigatorios!";
			}
		}
		else {
			return "O cadastro de uma categoria é necessário!";
		}
		return null;
	}
}