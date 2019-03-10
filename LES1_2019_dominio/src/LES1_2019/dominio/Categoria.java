package LES1_2019.dominio;

public class Categoria extends EntidadeDominio {

	private boolean status_categoria;
	private String nome_categoria;
	
	//getters e setters status categoria
	public boolean getStatusCategoria() {
		return status_categoria;
	}
	
	public void setStatusCategoria(boolean status) {
		status_categoria = status;
	}
	
	public String getNome() {
		return nome_categoria;
	}
	public void setNome(String nome) {
		this.nome_categoria = nome;
	}
}
