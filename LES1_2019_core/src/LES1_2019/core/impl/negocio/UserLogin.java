package LES1_2019.core.impl.negocio;

import java.util.List;

import LES1_2019.core.IStrategy;
import LES1_2019.core.impl.dao.UserDAO;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.User;

public class UserLogin implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		if(entidade instanceof User) {
			User objUser = (User)entidade;
			
			UserDAO objUserDao = new UserDAO();
			User user = new User();
			List<EntidadeDominio> dadosUser = objUserDao.consultar(entidade);
				if(dadosUser!=null) {
					for(EntidadeDominio p:dadosUser) {
						user = (User)p;
						System.out.println("user digitado: "+objUser.getEmail_usuario());
						System.out.println("user do banco: "+user.getEmail_usuario());
						System.out.println("senha digitado: "+objUser.getSenha_usuario());
						System.out.println("senha do banco: "+user.getSenha_usuario());
						if(user.getEmail_usuario().equals(objUser.getEmail_usuario()) &&
								user.getSenha_usuario().equals(objUser.getSenha_usuario())){
							if(!user.isStatus_usuario()) {
								return "Sua conta foi previamente excluida! Ative-a novamente para fazer login!";
							}else {
								System.out.println("Entou aqui");
								if(user.isAdmin()) {
									objUser.setNome_usuario(user.getNome_usuario());
									objUser.setId(user.getId());
									objUser.setAdmin(user.isAdmin());
								}else {
									objUser.setNome_usuario(user.getNome_usuario());
									objUser.setId(user.getId());
									objUser.setEnderecos(user.getEnderecos());
									objUser.setCartoes(user.getCartoes());	
								}
								return null;
								}
							}
						}
					return "E-mail ou senha invalidos!";
					}
				else {
					return "Erro no login";
				}
			}
		return "Erro no login";
	}
	
}