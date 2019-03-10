package LES1_2019.core.aplicacao;

import java.util.List;
import LES1_2019.dominio.EntidadeDominio;

public class Resultado extends EntidadeAplicacao{

	private String mensagem;
	private List<EntidadeDominio> entidades;
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public List<EntidadeDominio> getEntidades(){
		return entidades;
	}
	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}
}
