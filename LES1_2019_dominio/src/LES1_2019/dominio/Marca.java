package LES1_2019.dominio;

public class Marca extends EntidadeDominio {
	
	private int id_marca;
	private String nome_marca;
	
	public String getNome_marca() {
		return nome_marca;
	}
	public void setNome_marca(String nome_marca) {
		this.nome_marca = nome_marca;
	}
	public int getId_marca() {
		return id_marca;
	}
	public void setId_marca(int id_marca) {
		this.id_marca = id_marca;
	}
	
	
}
