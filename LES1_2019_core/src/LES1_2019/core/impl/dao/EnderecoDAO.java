package LES1_2019.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import LES1_2019.dominio.Endereco;
import LES1_2019.dominio.EntidadeDominio;

public class EnderecoDAO extends AbstractJdbcDAO {

	public EnderecoDAO() {
		super ("enderecos", "id_endereco");
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst = null;
		Endereco objEndereco = (Endereco)entidade;
		try {
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			
			sql = new StringBuilder();
			
			sql.append("INSERT INTO enderecos "
					+ "(rua_endereco, "
					+ "numero_endereco, "
					+ "bairro_endereco, "
					+ "cidade_endereco, "
					+ "estado_endereco, "
					+ "complemento_endereco, "
					+ "cep_endereco, "
					+ "id_usuario_endereco, "
					+ "endereco_cobranca) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pst = connection.prepareStatement(sql.toString());
			
			pst.setString(1, objEndereco.getRua());
			pst.setString(2, objEndereco.getNumero());
			pst.setString(3, objEndereco.getBairro());
			pst.setString(4, objEndereco.getCidade());
			pst.setString(5, objEndereco.getEstado());
			pst.setString(6, objEndereco.getComplemento());
			pst.setString(7, objEndereco.getCep());
			pst.setInt(8, objEndereco.getId_usuario_endereco());
			pst.setBoolean(9, objEndereco.isEndereco_cobranca());
			
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
		System.out.println("ENTROU NA DAO enderec");		
		openConnection();
		PreparedStatement pst = null;
		Endereco objEndereco = (Endereco)entidade;
		System.out.println(objEndereco.getId());
		System.out.println(objEndereco.getId_usuario_endereco());
		StringBuilder sql = new StringBuilder();
		
			try {
				connection.setAutoCommit(false);
				sql.append("UPDATE enderecos SET rua_endereco=?, ");
				sql.append("numero_endereco=?, bairro_endereco=?, ");
				sql.append("cidade_endereco=?, estado_endereco=?, ");
				sql.append("complemento_endereco=?, cep_endereco=?, ");
				sql.append("endereco_cobranca=? ");
				sql.append("WHERE id_endereco="+objEndereco.getId()+" AND id_usuario_endereco="+objEndereco.getId_usuario_endereco());
				
				pst = connection.prepareStatement(sql.toString());
			
				pst.setString(1, objEndereco.getRua());
				pst.setString(2, objEndereco.getNumero());
				pst.setString(3, objEndereco.getBairro());
				pst.setString(4, objEndereco.getCidade());
				pst.setString(5, objEndereco.getEstado());
				pst.setString(6, objEndereco.getComplemento());
				pst.setString(7, objEndereco.getCep());
				pst.setBoolean(8, objEndereco.isEndereco_cobranca());
				
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
		System.out.println("entrou na dao excluir");
		openConnection();
		PreparedStatement pst = null;
		Endereco objEndereco = (Endereco)entidade;
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM enderecos ");
			sql.append("WHERE id_endereco=?");
			
			pst = connection.prepareStatement(sql.toString());
			
			pst.setInt(1, objEndereco.getId());
			
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
		Endereco objEndereco = (Endereco)entidade;
		StringBuilder sql = new StringBuilder();
		List<EntidadeDominio> enderecos = new ArrayList<EntidadeDominio>();
		System.out.println("ENTROU CONSULTAR ENDERECOS");
		sql.append("SELECT * FROM enderecos ");
		sql.append("WHERE 1=1");
		
		if(objEndereco.getId_usuario_endereco()>0) {
			sql.append(" AND id_usuario_endereco="+objEndereco.getId_usuario_endereco());
		}
		
		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Endereco end = new Endereco();
				end.setId(rs.getInt("id_endereco"));
				end.setRua(rs.getString("rua_endereco"));
				end.setNumero(rs.getString("numero_endereco"));
				end.setBairro(rs.getString("bairro_endereco"));
				end.setCidade(rs.getString("cidade_endereco"));
				end.setEstado(rs.getString("estado_endereco"));
				end.setComplemento(rs.getString("complemento_endereco"));
				end.setCep(rs.getString("cep_endereco"));
				end.setEndereco_cobranca(rs.getBoolean("endereco_cobranca"));
				end.setId_usuario_endereco(rs.getInt("id_usuario_endereco"));
				enderecos.add(end);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enderecos;
	}
	
}