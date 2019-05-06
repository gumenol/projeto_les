package LES1_2019.core.impl.negocio;

import java.util.List;

import LES1_2019.core.IStrategy;
import LES1_2019.core.impl.dao.UserDAO;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.User;

public class AlterarSenhaUser implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		if(entidade instanceof User) {
			User objUser = (User)entidade;
			UserDAO objUserDAO = new UserDAO();
			User user = new User();
			System.out.println(objUser.getSenha_usuario());
			List<EntidadeDominio> dadosUser = objUserDAO.consultar(entidade);
			if(objUser.getSenha_usuario()!=null) {
				for(EntidadeDominio p:dadosUser) {
					user = (User)p;
					if(user.getId()==objUser.getId()) {
						if(user.getSenha_usuario().equals(objUser.getSenha_usuario())){
							System.out.println("aaaa");
							System.out.println(objUser.getNova_senha());
							System.out.println(objUser.getNova_senha().length());
							if(objUser.getNova_senha().length()>=8) {
								return null;
							}
							else {
								return "A nova senha deve conter mais de 8 caracteres!";
							}
						}
					}
				}
				return "A senha inserida não confere com sua senha atual!";
			}
			else {
			if(objUser.getNome_usuario()==null || objUser.getNome_usuario().equals("")||
					objUser.getCpf_usuario()==null || objUser.getCpf_usuario().equals("") ||
					objUser.getEmail_usuario()==null || objUser.getEmail_usuario().equals("") ||
						objUser.getTelefone_usuario()==null || objUser.getTelefone_usuario().equals("")) {
				return "Para alterar seu cadastro, todos os dados devem ser preenchidos!";
			} else if (objUser.getNome_usuario()!=null || objUser.getCpf_usuario()!=null ||
					objUser.getEmail_usuario()!=null || objUser.getTelefone_usuario()!=null){
					for(EntidadeDominio p:dadosUser) {
						user = (User)p;
						if(objUser.getEmail_usuario().equals(user.getEmail_usuario()) && objUser.getId()!=user.getId()) {
							return "E-mail colocado não está disponivel para uso";
						} 
					}
					return null;
					}
				}
			}
		return "Erro na alteração";
	}
	
}