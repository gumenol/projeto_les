package LES1_2019.auxiliar;


import java.util.ArrayList;
import LES1_2019.dominio.*;

public class DadosDeCadastro extends EntidadeDominio {

	private ArrayList<Produto> Produtos = new ArrayList<>();
	private ArrayList<Categoria> Categoria = new ArrayList<>();
	
	//Get e set de produtos
	public ArrayList<Produto> getProdutos() {
		return Produtos;
	}
	public void setAProdutos(ArrayList<Produto> Produtos) {
		Produtos = Produtos;
	}
	
	//Get e set de categorias
	public ArrayList<Categoria> getCategoria() {
		return Categoria;
	}
	public void setCategoria(ArrayList<Categoria> Categoria) {
		Categoria = Categoria;
	}
}
