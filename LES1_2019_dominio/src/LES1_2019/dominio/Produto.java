package LES1_2019.dominio;

public class Produto extends EntidadeDominio {

	private String nome_produto;
	private double valor_produto;
	private String descricao_produto;
	private String marca_produto;
	private int id_marca_produto;
	private boolean status_produto;
	private String img_produto;
	private int qtd_estoque;
	private int qtd_pedido;
	private int qtd_pedido_anterior;
	private boolean remover;
	private double subtotal;
	private int qtdtotal;
	private int qtdremover;
	private int pedido;
	private Categoria objCategoria = new Categoria();
		
	public int getPedido() {
		return pedido;
	}
	public void setPedido(int pedido) {
		this.pedido = pedido;
	}
	
	public int getQtdremover() {
		return qtdremover;
	}
	public void setQtdremover(int qtdremover) {
		this.qtdremover = qtdremover;
	}
	
	//getters e setters nome produto
	public String getNome() {
		return nome_produto;
	}
	public void setNome(String nome) {
		this.nome_produto = nome;
	}
	//getters e setters valor produto
	public double getValor_produto() {
		return valor_produto;
	}
	public void setValor_produto(double valor_produto) {
		this.valor_produto = valor_produto;
	}
	//getters e setters descricao produto
	public String getDescricao_produto() {
		return descricao_produto;
	}
	public void setDescricao_produto(String descricao_produto) {
		this.descricao_produto = descricao_produto;
	}
	//getters e setters marca produto
	public String getMarca_produto() {
		return marca_produto;
	}
	public void setMarca_produto(String marca_produto) {
		this.marca_produto = marca_produto;
	}
	//getters e setters status produto
	public boolean getStatus_produto() {
		return status_produto;
	}
	public void setStatus_produto(boolean status_produto) {
		this.status_produto = status_produto;
	}
	//getters e setters id da marca do produto
	public int getId_marca_produto() {
		return id_marca_produto;
	}
	public void setId_marca_produto(int id_marca_produto) {
		this.id_marca_produto = id_marca_produto;
	}
	
	//getters e setters categoria do produto
	public Categoria getCategoria() {
		return objCategoria;
	}
	public void setCategoria(Categoria objCategoria) {
		this.objCategoria = objCategoria;
	}
	
	public String getImg_produto() {
		return img_produto;
	}
	public void setImg_produto(String img_produto) {
		this.img_produto = img_produto;
	}
	
	public int getQtd_estoque() {
		return qtd_estoque;
	}
	public void setQtd_estoque(int qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}
	
	public int getQtd_pedido() {
		return qtd_pedido;
	}
	public void setQtd_pedido(int qtd_pedido) {
		this.qtd_pedido = qtd_pedido;
	}
	
	public boolean isRemover() {
		return remover;
	}
	public void setRemover(boolean remover) {
		this.remover = remover;
	}
	
	public int getQtd_pedido_anterior() {
		return qtd_pedido_anterior;
	}
	public void setQtd_pedido_anterior(int qtd_pedido_anterior) {
		this.qtd_pedido_anterior = qtd_pedido_anterior;
	}
	
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	public int getQtdtotal() {
		return qtdtotal;
	}
	public void setQtdtotal(int qtdtotal) {
		this.qtdtotal = qtdtotal;
	}
	
}
