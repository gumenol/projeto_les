package LES1_2019.dominio;

import java.util.ArrayList;
import java.util.Date;

public class User extends EntidadeDominio {

	private String nome_usuario;
	private String cpf_usuario;
	private String email_usuario;
	private String senha_usuario;
	private String confirmar_senha;
	private String nova_senha;
	private String telefone_usuario;
	private boolean isAdmin;
	private boolean status_usuario;
	Date data_cadastro;
	private ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
	private ArrayList<Cartao> cartoes = new ArrayList<Cartao>();
	
	/*
	 * private ArrayList<CupomTroca> cupons = new ArrayList<CupomTroca>();
	 */ 

	//nome usuario
	public String getNome_usuario() {
		return nome_usuario;
	}

	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}
	
	//cpf usuario
	public String getCpf_usuario() {
		return cpf_usuario;
	}

	public void setCpf_usuario(String cpf_usuario) {
		this.cpf_usuario = cpf_usuario;
	}

	
	//email usuario
	public String getEmail_usuario() {
		return email_usuario;
	}

	public void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}

	//senha usuario
	public String getSenha_usuario() {
		return senha_usuario;
	}

	public void setSenha_usuario(String senha_usuario) {
		this.senha_usuario = senha_usuario;
	}
	
	//confirmar senha
	public String getConfirmar_senha() {
		return confirmar_senha;
	}

	public void setConfirmar_senha(String confirmar_senha) {
		this.confirmar_senha = confirmar_senha;
	}

	//telefone usuario
	public String getTelefone_usuario() {
		return telefone_usuario;
	}

	public void setTelefone_usuario(String telefone_usuario) {
		this.telefone_usuario = telefone_usuario;
	}

	//usuario admin - true false
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	//status usuario - ativo inativo
	public boolean isStatus_usuario() {
		return status_usuario;
	}

	public void setStatus_usuario(boolean status_usuario) {
		this.status_usuario = status_usuario;
	}

	//data cadastro
	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	//endereco
	public ArrayList<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(ArrayList<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public String getNova_senha() {
		return nova_senha;
	}

	public void setNova_senha(String nova_senha) {
		this.nova_senha = nova_senha;
	}
	
	public ArrayList<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(ArrayList<Cartao> cartoes) {
		this.cartoes = cartoes;
	}
	
}
