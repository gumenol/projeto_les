package LES1_2019.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import LES1_2019.dominio.Cartao;
import LES1_2019.dominio.EntidadeDominio;

public class CartaoDAO extends AbstractJdbcDAO {

	public CartaoDAO() {
		super ("cartoes_credito", "id_cartao");
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst = null;
		Cartao objCartao = (Cartao)entidade;
		try {
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
					
			sql = new StringBuilder();
			
			sql.append("INSERT INTO cartoes_credito "
					+ "(numero_cartao, "
					+ "validade_cartao, "
					+ "cvv_cartao, "
					+ "nome_cartao, "
					+ "id_usuario_cartao, "
					+ "id_bandeira_cartao, "
					+ "status_cartao) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
			pst = connection.prepareStatement(sql.toString());
			
			pst.setString(1, objCartao.getNumero_cartao());
			pst.setString(2, objCartao.getAno_cartao()+objCartao.getMes_cartao()+"28");
			pst.setString(3, objCartao.getCvv_cartao());
			pst.setString(4, objCartao.getNome_cartao());
			pst.setInt(5, objCartao.getId_usuario_cartao());
			pst.setInt(6, objCartao.getId_bandeira_cartao());
			pst.setBoolean(7, true);
			
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
		System.out.println("ENTROU NA DAO cartao");		
		openConnection();
		PreparedStatement pst = null;
		Cartao objCartao = (Cartao)entidade;
		StringBuilder sql = new StringBuilder();
		
			try {
				connection.setAutoCommit(false);
				sql.append("UPDATE cartoes_credito SET numero_cartao=?, ");
				sql.append("validade_cartao=?, cvv_cartao=?, ");
				sql.append("nome_cartao=?, id_bandeira_cartao=? ");
				sql.append("WHERE id_cartao="+objCartao.getId()+" AND id_usuario_cartao="+objCartao.getId_usuario_cartao());
				
				pst = connection.prepareStatement(sql.toString());
			
				pst.setString(1, objCartao.getNumero_cartao());
				pst.setString(2, objCartao.getAno_cartao()+objCartao.getMes_cartao()+"28");
				pst.setString(3, objCartao.getCvv_cartao());
				pst.setString(4, objCartao.getNome_cartao());
				pst.setInt(5, objCartao.getId_bandeira_cartao());
				
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
		Cartao objCartao = (Cartao)entidade;
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM cartoes_credito ");
			sql.append("WHERE id_cartao=?");
			
			pst = connection.prepareStatement(sql.toString());
			
			pst.setInt(1, objCartao.getId());
			
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
		Cartao objCartao = (Cartao)entidade;
		StringBuilder sql = new StringBuilder();
		List<EntidadeDominio> cartoes = new ArrayList<EntidadeDominio>();
		
		System.out.println("ENTROU CONSULTAR cartoes");
		sql.append("SELECT * FROM cartoes_credito ");
		sql.append("WHERE 1=1");
		
		if(objCartao.getId_usuario_cartao()>0) {
			sql.append(" AND id_usuario_cartao="+objCartao.getId_usuario_cartao());
		}
		
		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			Calendar cal = Calendar.getInstance();
			while(rs.next()) {
				Cartao card = new Cartao();
				card.setId(rs.getInt("id_cartao"));
				card.setNumero_cartao(rs.getString("numero_cartao"));
				cal.setTime(rs.getDate("validade_cartao"));
				int mes = cal.get(Calendar.MONTH);
				mes = mes + 1;
				card.setAno_cartao(String.valueOf(cal.get(Calendar.YEAR)));
				card.setMes_cartao(String.valueOf(mes));
				card.setCvv_cartao(rs.getString("cvv_cartao"));
				card.setNome_cartao(rs.getString("nome_cartao"));
				card.setId_usuario_cartao(rs.getInt("id_usuario_cartao"));
				card.setId_bandeira_cartao(rs.getInt("id_bandeira_cartao"));
				card.setStatus_cartao(rs.getBoolean("status_cartao"));
				cartoes.add(card);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartoes;
	}
	
}