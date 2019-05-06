package LES1_2019.core.impl.negocio;

import LES1_2019.core.IStrategy;
import LES1_2019.dominio.Cartao;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Pedido;

public class ValidarPagamento implements IStrategy {

	public String processar(EntidadeDominio entidade) {
		System.out.println("ENTROU REGRA");
		System.out.println(entidade);
		double total = 0;
		if(entidade instanceof Pedido) {
			Pedido objPedido = (Pedido)entidade;
			if(objPedido.getCartoes().size()==1) {
				//tem apenas um cartao
				for(Cartao card:objPedido.getCartoes()) {
					if(card.getValor_pago()<objPedido.getValor_total()) {
						return "O pedido tem valor total de "+objPedido.getValor_total()+" e voce digitou "+card.getValor_pago();
					}
				}
			}else
			{
				
				for(Cartao card:objPedido.getCartoes()) {
					total = total+ card.getValor_pago();
				}
				if(total<objPedido.getValor_total()) {
					return "O valor total do pedido é de "+objPedido.getValor_total()+" e a os cartoes somados "+total;
				}else if(total>objPedido.getValor_total()) {
					return "Voce digitou um valor maior que do pedido! Pedido:"+objPedido.getValor_total()+" Cartoes somados: "+total;
				}
			}
			return null;
		}
		return "Erro no processamento do pedido";
	}
	
}