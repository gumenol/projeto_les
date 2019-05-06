package LES1_2019.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.mysql.jdbc.Statement;

import LES1_2019.dominio.Cartao;
import LES1_2019.dominio.Endereco;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.User;


public class UserDAO extends AbstractJdbcDAO {

	public UserDAO() {
		super ("usuarios", "id_usuario");
	}
	
	
	public void salvar(EntidadeDominio entidade) {
		System.out.println("entrou na dao users");
		openConnection();
		PreparedStatement pst = null;
		User objUser = (User)entidade;
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String currentTime = sdf.format(dt);
			
		try {
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();		
			sql = new StringBuilder();
			
			sql.append("INSERT INTO usuarios "
					+ "(nome_usuario, "
					+ "cpf_usuario, "
					+ "email_usuario, "
					+ "senha_usuario, "
					+ "telefone_usuario, "
					+ "status_usuario, "
					+ "datacriacao_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, objUser.getNome_usuario());
			pst.setString(2, objUser.getCpf_usuario());
			pst.setString(3, objUser.getEmail_usuario());
			pst.setString(4, objUser.getSenha_usuario());
			pst.setString(5, objUser.getTelefone_usuario());
			pst.setBoolean(6, true);
			pst.setString(7, currentTime);
			
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			
			int id = 0;
			if(rs.next()) {
				id=rs.getInt(1);
				objUser.setId(id);
			}
			
			connection.commit();
			
			connection.setAutoCommit(false);
			
			sql = new StringBuilder();
			
			sql.append("INSERT INTO enderecos (");
			sql.append("rua_endereco,");
			sql.append(" numero_endereco,");
			sql.append(" bairro_endereco,");
			sql.append(" cidade_endereco,");
			sql.append(" estado_endereco,");
			sql.append(" complemento_endereco,");
			sql.append(" endereco_cobranca,");
			sql.append(" cep_endereco,");
			sql.append(" id_usuario_endereco) ");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			pst = connection.prepareStatement(sql.toString());
			
			pst.setString(1, objUser.getEnderecos().get(0).getRua());
			pst.setString(2, objUser.getEnderecos().get(0).getNumero());
			pst.setString(3, objUser.getEnderecos().get(0).getBairro());
			pst.setString(4, objUser.getEnderecos().get(0).getCidade());
			pst.setString(5, objUser.getEnderecos().get(0).getEstado());
			pst.setString(6, objUser.getEnderecos().get(0).getComplemento());
			pst.setBoolean(7, true);
			pst.setString(8, objUser.getEnderecos().get(0).getCep());
			pst.setInt(9, objUser.getId());
			
			pst.executeUpdate();
			
			connection.commit();
			
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException el) {
					el.printStackTrace();
				}
			e.printStackTrace();
			} finally {
				try {
					pst.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	
	@Override
	public void alterar(EntidadeDominio entidade) {
		System.out.println("ENTROU NA DAO alterar user");
		openConnection();
		PreparedStatement pst = null;
		User objUser = (User)entidade;
		StringBuilder sql = new StringBuilder();
		
			try {
			if(objUser.getNova_senha()==null) {
				System.out.println("entrou no sql");
				connection.setAutoCommit(false);
				sql.append("UPDATE usuarios SET nome_usuario=?, ");
				sql.append("cpf_usuario=?, email_usuario=?, ");
				sql.append("telefone_usuario=? ");
				sql.append("WHERE id_usuario=?");
				
				pst = connection.prepareStatement(sql.toString());
			
				pst.setString(1, objUser.getNome_usuario());
				pst.setString(2, objUser.getCpf_usuario());
				pst.setString(3, objUser.getEmail_usuario());
				pst.setString(4, objUser.getTelefone_usuario());
				pst.setInt(5, objUser.getId());
				
			} else {
				System.out.println(objUser.getNova_senha());
				System.out.println("entrou no sql senha");
				connection.setAutoCommit(false);
				sql.append("UPDATE usuarios SET senha_usuario=? ");
				sql.append("WHERE id_usuario=?");
				
				pst = connection.prepareStatement(sql.toString());
				
				pst.setString(1, objUser.getNova_senha());
				pst.setInt(2, objUser.getId());
				
			}
			
				pst.executeUpdate();
				connection.commit();
				
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException el) {
					el.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				try {
					pst.close();
					connection.close();
				} catch (SQLException e){
					e.printStackTrace();
				}
			}
	}
	
	@Override
	public void excluir(EntidadeDominio entidade) {
		System.out.println("entrou na dao excluir cliente");
		openConnection();
		PreparedStatement pst = null;
		User objUser = (User)entidade;
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE usuarios SET status_usuario=? ");
			sql.append("WHERE id_usuario=?");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setBoolean(1, false);
			pst.setInt(2, objUser.getId());
			pst.executeUpdate();
			connection.commit();
			
		} catch(SQLException e) {
			try {
				connection.rollback();
			} catch(SQLException el) {
				el.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void ativar(EntidadeDominio entidade) {
		System.out.println("entrou na dao ativar cliente");
		openConnection();
		PreparedStatement pst = null;
		User objUser = (User)entidade;
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE usuarios SET status_usuario=? ");
			sql.append("WHERE id_usuario=?");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setBoolean(1, true);
			pst.setInt(2, objUser.getId());
			pst.executeUpdate();
			connection.commit();
			
		} catch(SQLException e) {
			try {
				connection.rollback();
			} catch(SQLException el) {
				el.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade){
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		StringBuilder sql = new StringBuilder();
		List<EntidadeDominio> users = new ArrayList<EntidadeDominio>();
		
		sql.append("SELECT * FROM usuarios");
		
		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			ResultSet rs2;
			ResultSet rs3;
			Calendar cal = Calendar.getInstance();
			while(rs.next()) {
				User usuario = new User();
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setNome_usuario(rs.getString("nome_usuario"));
				usuario.setSenha_usuario(rs.getString("senha_usuario"));
				usuario.setCpf_usuario(rs.getString("cpf_usuario"));
				usuario.setEmail_usuario(rs.getString("email_usuario"));
				usuario.setData_cadastro(rs.getDate("datacriacao_usuario"));
				usuario.setTelefone_usuario(rs.getString("telefone_usuario"));
				usuario.setStatus_usuario(rs.getBoolean("status_usuario"));
				usuario.setAdmin(rs.getBoolean("admin"));
				usuario.setEnderecos(new ArrayList<Endereco>());
				
				if(!usuario.isAdmin()) {
				sql = new StringBuilder();
				sql.append("SELECT * FROM enderecos WHERE id_usuario_endereco = ?");
				pst2 = connection.prepareStatement(sql.toString());
				pst2.setInt(1, usuario.getId());
				rs2 = pst2.executeQuery();
				int i;
				for(i = 0; rs2.next();i++) {
					if(usuario.getId() == rs2.getInt("id_usuario_endereco")) {
						usuario.getEnderecos().add(new Endereco());
						usuario.getEnderecos().get(i).setRua(rs2.getString("rua_endereco"));
						usuario.getEnderecos().get(i).setBairro(rs2.getString("bairro_endereco"));
						usuario.getEnderecos().get(i).setNumero(rs2.getString("numero_endereco"));
						usuario.getEnderecos().get(i).setCidade(rs2.getString("cidade_endereco"));
						usuario.getEnderecos().get(i).setEstado(rs2.getString("estado_endereco"));
						usuario.getEnderecos().get(i).setComplemento(rs2.getString("complemento_endereco"));
						usuario.getEnderecos().get(i).setCep(rs2.getString("cep_endereco"));
						usuario.getEnderecos().get(i).setEndereco_cobranca(rs2.getBoolean("endereco_cobranca"));
						
					}
				}
				
				sql = new StringBuilder();
				sql.append("SELECT * FROM cartoes_credito WHERE id_usuario_cartao = ?");
				pst3 = connection.prepareStatement(sql.toString());
				pst3.setInt(1, usuario.getId());
				rs3 = pst3.executeQuery();
				int n;
				for(n = 0; rs3.next();n++) {
					if(usuario.getId() == rs3.getInt("id_usuario_cartao")) {
						usuario.getCartoes().add(new Cartao());
						usuario.getCartoes().get(n).setNumero_cartao(rs3.getString("numero_cartao"));
						cal.setTime(rs3.getDate("validade_cartao"));
						int mes = cal.get(Calendar.MONTH);
						
						System.out.println(cal.get(Calendar.MONTH));
						mes = mes + 1;
						usuario.getCartoes().get(n).setMes_cartao(String.valueOf(mes));
						usuario.getCartoes().get(n).setAno_cartao(String.valueOf(cal.get(Calendar.YEAR)));
						usuario.getCartoes().get(n).setCvv_cartao(rs3.getString("cvv_cartao"));
						usuario.getCartoes().get(n).setNome_cartao(rs3.getString("nome_cartao"));
						usuario.getCartoes().get(n).setId_usuario_cartao(rs3.getInt("id_usuario_cartao"));
						usuario.getCartoes().get(n).setId_bandeira_cartao(rs3.getInt("id_bandeira_cartao"));						
					}
				}
				
				}
				users.add(usuario);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	
}
