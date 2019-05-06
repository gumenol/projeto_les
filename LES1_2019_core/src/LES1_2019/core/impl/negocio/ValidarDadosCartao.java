package LES1_2019.core.impl.negocio;

import java.util.List;

import LES1_2019.core.IStrategy;
import LES1_2019.core.impl.dao.CartaoDAO;
import LES1_2019.dominio.Cartao;
import LES1_2019.dominio.EntidadeDominio;

public class ValidarDadosCartao implements IStrategy {

	public String processar(EntidadeDominio entidade) {
		System.out.println(entidade);
		if(entidade instanceof Cartao) {
			Cartao objCartao = (Cartao)entidade;
			Cartao card = new Cartao();
			CartaoDAO objCardDao = new CartaoDAO();
			if(objCartao.getNome_cartao()==null || objCartao.getNome_cartao()=="" ||
					objCartao.getNumero_cartao()==null ||objCartao.getNumero_cartao()=="" ||
					objCartao.getMes_cartao()==null || objCartao.getMes_cartao()=="" ||
					objCartao.getAno_cartao()==null || objCartao.getAno_cartao()=="" ||
					objCartao.getCvv_cartao()==null|| objCartao.getCvv_cartao()=="") {
				return "Todos os campos de dados de cartão devem ser preenchidos!";
			}
			
			if(objCartao.getNumero_cartao().length()<16 || objCartao.getNumero_cartao().length()>16) {
				return "O numero do cartao de credito deve conter 16 numeros!";
			} else if(objCartao.getNumero_cartao().matches(".*[a-z].*") || objCartao.getNumero_cartao().matches(".*[A-Z].*")){
				return "O numero do cartao de credito nao pode conter letras!";
			}
			
			List<EntidadeDominio> dadosCard = objCardDao.consultar(entidade);
			if(dadosCard!=null) {
				for(EntidadeDominio p:dadosCard) {
					card = (Cartao)p;
					if(objCartao.getNumero_cartao().equals(card.getNumero_cartao()) & 
							objCartao.getId_usuario_cartao()==card.getId_usuario_cartao()) {
						if(objCartao.getId()!=card.getId()) {
						return "O numero de cartao inserido ja esta cadastrado!";
						}
					}
				}
			}
			
			
			if(objCartao.getNome_cartao().matches(".*[1-9].*")) {
				return "O nome não pode conter números!";
			}
			
			if(objCartao.getId_bandeira_cartao()==0) {
				return "O cartao inserido nao corresponde a nenhuma bandeira e nao e valido!";
			}
			
			if(objCartao.getCvv_cartao().length()<3 || objCartao.getCvv_cartao().length()>4) {
				return "O CVV do cartao de credito deve conter entre 3 e 4 digitos!";
			} else if(objCartao.getCvv_cartao().matches(".*[a-z].*") || objCartao.getCvv_cartao().matches(".*[A-Z].*")) {
				return "O CVV do cartao deve conter apenas numeros!";
			}
			
			if(objCartao.getAno_cartao().length()<4) {
				return "O ano precisa ter 4 números! Exemplo: 2020";
			}

			return null;
		}
		return "Erro no processamento de cartao";
	}
	
}
