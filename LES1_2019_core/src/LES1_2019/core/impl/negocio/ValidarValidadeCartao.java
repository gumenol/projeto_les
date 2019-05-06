package LES1_2019.core.impl.negocio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import LES1_2019.core.IStrategy;
import LES1_2019.dominio.Cartao;
import LES1_2019.dominio.EntidadeDominio;

public class ValidarValidadeCartao implements IStrategy {

	public String processar(EntidadeDominio entidade) {
		System.out.println(entidade);
		if(entidade instanceof Cartao) {
			Cartao objCartao = (Cartao)entidade;
			
			Date hoje = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String validade = "28-"+objCartao.getMes_cartao()+"-"+objCartao.getAno_cartao();
			Date val = null;
			try {
				val = sdf.parse(validade);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			if(val.before(hoje)) {
				return "O cartao inserido ja esta vencido!";
			}
			
			return null;
		}
		return "Erro no processamento de cartao";
	}
	
}