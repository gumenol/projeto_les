package LES1_2019.core.impl.negocio;

import java.util.List;

import LES1_2019.core.IStrategy;
import LES1_2019.core.impl.dao.UserDAO;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.User;

public class AddSession implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		if(entidade instanceof User) {
			User objUser = (User)entidade;
			UserDAO objUserDao = new UserDAO();
			System.out.println(objUserDao);
			User user = new User();
			System.out.println(user);
			List<EntidadeDominio> dadosUser = objUserDao.consultar(entidade);
			System.out.println("CRIOU LISTA DE DADOS");
			System.out.println(dadosUser);
			System.out.println("id: "+objUser.getId());
					for(EntidadeDominio p:dadosUser) {
						user = (User)p;
						System.out.println("Entrou for");
						System.out.println(user);
						if(user.getId() == objUser.getId()){
								objUser.setNome_usuario(user.getNome_usuario());
								objUser.setId(user.getId());
								objUser.setAdmin(user.isAdmin());
								objUser.setEnderecos(user.getEnderecos());
								objUser.setCartoes(user.getCartoes());
								System.out.println("ADICIONOU A SESSAO");
								return null;
							}
						}
			}
		System.out.println("CAIU ERRO");
		return "Erro";
	}
	
}