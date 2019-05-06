package LES1_2019.core.impl.negocio;

import LES1_2019.core.IStrategy;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Pedido;
import LES1_2019.dominio.Produto;

public class Carrinho implements IStrategy {

	public String processar(EntidadeDominio entidade) {
		System.out.println("ENTROU REGRA");
		System.out.println(entidade);
		if(entidade instanceof Pedido) {
			Pedido objPedido = (Pedido)entidade;
			for(Produto prod:objPedido.getProdutos()) {
				System.out.println("QTD ESTOQUE"+prod.getQtd_estoque());
				if(prod.getQtd_estoque()!=0) {
					System.out.println("ENTROU pra verificar");
					if(prod.isRemover()) {
						if(prod.getQtdremover()>prod.getQtdtotal()) {
							prod.setRemover(false);
							prod.setQtd_estoque(0);
							return "Voce nao pode remover "+prod.getQtdremover()+" pois tem apenas "+prod.getQtdtotal()+" em seu carrinho!";
						}
					}else {
						System.out.println("ADICIONANDO");
						System.out.println("qtd colocada: "+prod.getQtd_pedido());
						System.out.println("qtd disp: "+prod.getQtd_estoque());
						if(prod.getQtd_pedido()>prod.getQtd_estoque()) {
							objPedido.getProdutos().remove(prod);
							return prod.getId()+" Temos apenas "+prod.getQtd_estoque()+" em estoque!";
						}
					}
				}
			}
			return null;
		}
		return "Erro no processamento do carrinho";
	}
	
}