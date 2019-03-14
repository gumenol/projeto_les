package LES1_2019.dominio;

public class Marca extends EntidadeDominio {
	
	private String nome_marca;
	private boolean status_marca;
	
	public boolean getStatus_marca() {
		return status_marca;
	}
	public void setStatus_marca(boolean status_marca) {
		this.status_marca = status_marca;
	}
	public String getNome_marca() {
		return nome_marca;
	}
	public void setNome_marca(String nome_marca) {
		this.nome_marca = nome_marca;
	}
	
	
	
}
