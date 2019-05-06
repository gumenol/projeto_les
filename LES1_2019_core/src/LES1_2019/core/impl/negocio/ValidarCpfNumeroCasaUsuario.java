package LES1_2019.core.impl.negocio;

import LES1_2019.core.IStrategy;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.User;

public class ValidarCpfNumeroCasaUsuario implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		if(entidade instanceof User) {
			User objUser = (User)entidade;
			System.out.println("entrou validacao cpf");
			
			if(objUser.getCpf_usuario()!=null) {
			//valida tamanho cpf
			if(objUser.getCpf_usuario().length()!=11) {
				return "CPF deve ter 11 números!";
				}
			//valida letras em cpf
			if (objUser.getCpf_usuario().matches(".*[a-z].*") || objUser.getCpf_usuario().matches(".*[A-Z].*")) { 
				return "O CPF não pode conter letras!";
				}
			}
			if(objUser.getEnderecos().get(0).getNumero()!=null) {
			//valida letras no numero do endereco
			if(objUser.getEnderecos().get(0).getNumero().matches(".*[a-z].*") || objUser.getEnderecos().get(0).getNumero().matches(".*[A-Z].*")) {
				return "O numero da casa do endereço pode conter apenas numeros!";
				}
			}
			
			if(objUser.getTelefone_usuario()!=null) {
			//valida letras no telefone
			if(objUser.getTelefone_usuario().matches(".*[a-z].*") || objUser.getTelefone_usuario().matches(".*[A-Z].*")) {
				return "O telefone pode conter apenas numeros!";
				}
			
			//valida total de numeros no telefone
			if(objUser.getTelefone_usuario().length()!=11) {
				return "O telefone deve conter 11 números - DDD + Telefone!";
				}
			}
			if(objUser.getSenha_usuario()!=null) {
				//valida tamanho da senha
				if(objUser.getSenha_usuario().length()<8) {
					return "A senha deve conter ao menos 8 caracteres!";
					}
				
				//valida senhas iguais
				if(objUser.getConfirmar_senha()!=null) {
					if(!objUser.getSenha_usuario().equals(objUser.getConfirmar_senha())) {
						return "As senhas inseridas não conferem!";
					}
				}
			}
		}
		return null;
	}
	
}