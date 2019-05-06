package LES1_2019.dominio;

public class Endereco extends EntidadeDominio {
	
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String complemento;
	private boolean endereco_cobranca;
	private String cep;
	private String rua;
	private int id_usuario_endereco;
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public boolean isEndereco_cobranca() {
		return endereco_cobranca;
	}
	public void setEndereco_cobranca(boolean endereco_cobranca) {
		this.endereco_cobranca = endereco_cobranca;
	}
	
	public int getId_usuario_endereco() {
		return id_usuario_endereco;
	}
	public void setId_usuario_endereco(int id_usuario_endereco) {
		this.id_usuario_endereco = id_usuario_endereco;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
}
