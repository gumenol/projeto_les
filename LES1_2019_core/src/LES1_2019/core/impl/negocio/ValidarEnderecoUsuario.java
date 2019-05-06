package LES1_2019.core.impl.negocio;

import LES1_2019.core.IStrategy;
import LES1_2019.dominio.Endereco;
import LES1_2019.dominio.EntidadeDominio;

public class ValidarEnderecoUsuario implements IStrategy {
	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		if(entidade instanceof Endereco) {
			Endereco objEndereco = (Endereco)entidade;
			System.out.println("entrou na regra");
			System.out.println(objEndereco.getCep());
			System.out.println(objEndereco.getRua());
			System.out.println(objEndereco.getBairro());
			System.out.println(objEndereco.getCidade());
			System.out.println(objEndereco.getEstado());
			System.out.println(objEndereco.getNumero());
			if(objEndereco.getCep()==null || objEndereco.getCep().equals("") ||
				objEndereco.getRua()==null || objEndereco.getRua().equals("") ||
				objEndereco.getBairro()==null || objEndereco.getBairro().equals("") ||
				objEndereco.getCidade()==null || objEndereco.getCidade().equals("") ||
				objEndereco.getEstado()==null || objEndereco.getEstado().equals("") ||
				objEndereco.getNumero()==null || objEndereco.getNumero().equals("")) {
				return "Todos os dados de endereço devem ser preenchidos!!";
			} else {
				objEndereco.getCep().replace("-","");
				if(objEndereco.getCep().length()<8 || objEndereco.getCep().length()>9) {
					return "CEP incorreto!";
				}
				if(objEndereco.getCep().matches(".*[a-z].*") || objEndereco.getCep().matches(".*[A-Z].*")){
					return "CEP só pode conter numeros!";
				}
				if(objEndereco.getNumero().matches(".*[a-z].*") || objEndereco.getNumero().matches(".*[A-Z].*")){
					return "Numero do endereço não pode conter letras!!";
				}
				return null;
			}
					
		}
		return "Erro";
	}
	
}