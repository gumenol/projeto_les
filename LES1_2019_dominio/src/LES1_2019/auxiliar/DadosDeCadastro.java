package LES1_2019.auxiliar;


import java.util.ArrayList;

import LES1_2019.dominio.Categoria;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Marca;

public class DadosDeCadastro extends EntidadeDominio {

	private ArrayList<Marca> marcas = new ArrayList<>();
	private ArrayList<Categoria> categorias = new ArrayList<>();
	
	
	//Get e set de marcas
	public ArrayList<Marca> getMarcas() {
		return marcas;
	}
	public void setMarcas(ArrayList<Marca> marcas) {
		this.marcas = marcas;
	}	
	
	//Get e set de categorias
	public ArrayList<Categoria> getCategoria() {
		return categorias;
	}
	public void setCategoria(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}
}
