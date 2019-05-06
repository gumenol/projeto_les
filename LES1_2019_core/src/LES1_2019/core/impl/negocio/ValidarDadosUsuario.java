package LES1_2019.core.impl.negocio;

import java.util.List;

import LES1_2019.core.IStrategy;
import LES1_2019.core.impl.dao.UserDAO;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.User;

public class ValidarDadosUsuario implements IStrategy {
	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		if(entidade instanceof User) {
			User objUser = (User)entidade;
			User user = new User();
			UserDAO userDAO = new UserDAO();
			System.out.println("entrou na regra");
			if(objUser.getNome_usuario()==null || objUser.getNome_usuario().equals("") ||
					objUser.getCpf_usuario()==null || objUser.getCpf_usuario().equals("") ||
						objUser.getEmail_usuario()==null || objUser.getEmail_usuario().equals("") ||
							objUser.getTelefone_usuario()==null || objUser.getTelefone_usuario().equals("") ||
								objUser.getSenha_usuario()==null || objUser.getSenha_usuario().equals("") ||
									objUser.getConfirmar_senha()==null|| objUser.getConfirmar_senha().equals("") ||
									objUser.getEnderecos().get(0).getRua() == null || objUser.getEnderecos().get(0).getRua().equals("") ||
									objUser.getEnderecos().get(0).getBairro() == null || objUser.getEnderecos().get(0).getBairro().equals("") ||
									objUser.getEnderecos().get(0).getCidade() == null || objUser.getEnderecos().get(0).getCidade().equals("") ||
									objUser.getEnderecos().get(0).getEstado() == null || objUser.getEnderecos().get(0).getEstado().equals("") ||
									objUser.getEnderecos().get(0).getCep() == null || objUser.getEnderecos().get(0).getCep().equals("") ||
									objUser.getEnderecos().get(0).getNumero() == null || objUser.getEnderecos().get(0).getNumero().equals("")) {
				return "Para efetuar o cadastro, todos os dados devem ser preenchidos!!";
			}
			else {
				objUser.getEnderecos().get(0).getCep().replace("-","");
				if(objUser.getEnderecos().get(0).getCep().length()<8 || objUser.getEnderecos().get(0).getCep().length()>9) {
					return "CEP incorreto!";
				}
				if(objUser.getEnderecos().get(0).getCep().matches(".*[a-z].*") || objUser.getEnderecos().get(0).getCep().matches(".*[A-Z].*")){
					return "CEP só pode conter numeros!";
				}
				List<EntidadeDominio> dadosUser = userDAO.consultar(entidade);
				for(EntidadeDominio p:dadosUser) {
					user = (User)p;
					if(objUser.getEmail_usuario().equals(user.getEmail_usuario())) {
						return "Esse e-mail já está em uso, por gentileza escolha outro...";
						}
					}
					return null;
				}
				
			}
		return null;
	}
	
}