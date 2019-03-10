package LES1_2019.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.ExecutorCompletionService;

import LES1_2019.dominio.Categoria;
import LES1_2019.dominio.Produto;
import LES1_2019.dominio.EntidadeDominio;

public class CategoriaDAO extends AbstractJdbcDAO{

	public CategoriaDAO() {
		super("categorias", "id_categoria");
	}

	@Override
	public void salvar(EntidadeDominio entidade) {
	openConnection();
	PreparedStatement pst = null;
	Categoria objCategoria = (Categoria)entidade;
	
	
	try {
		connection.setAutoCommit(false);
		
		StringBuilder sql = new StringBuilder();
		//digitar query para salvar livro aqui
		sql.append("INSERT INTO categorias (nome_categoria, status_categoria) VALUES (?, ?)");
		pst = connection.prepareStatement(sql.toString());
		
		pst.setString(1, objCategoria.getNome());
		pst.setBoolean(2, objCategoria.getStatusCategoria());
		
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
		openConnection();
		PreparedStatement pst = null;
		Categoria objCategoria = (Categoria)entidade;
		if(objCategoria.getNome() != null) {
			try {
				connection.setAutoCommit(false);
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE categorias SET nome_categoria=?, status_categoria=? ");
				sql.append("WHERE id_categoria=?");
				
				pst = connection.prepareStatement(sql.toString());
				pst.setString(1, objCategoria.getNome());
				pst.setBoolean(2, objCategoria.getStatusCategoria());
				pst.setInt(3, objCategoria.getId());
				
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
	}
	
	@Override
	public void excluir(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst = null;
		Categoria objCategoria = (Categoria)entidade;
		
		try {
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE categorias SET status_categoria=? ");
			sql.append("WHERE id_categoria=?");
			
			pst = connection.prepareStatement(sql.toString());
			
			pst.setBoolean(1, false);
			pst.setInt(2,objCategoria.getId());
			
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
	public void ativar(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst = null;
		Categoria objCategoria = (Categoria)entidade;
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE categorias SET status_categoria=? ");
			sql.append("WHERE id_categoria=?");
			
			pst = connection.prepareStatement(sql.toString());
			
			pst.setBoolean(1, true);
			pst.setInt(2, objCategoria.getId());
			
			pst.executeUpdate();
			connection.commit();
		} catch (SQLException  e) {
			try {
				connection.rollback();
			} catch (SQLException el){
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
	public List<EntidadeDominio> consultar (EntidadeDominio entidade){
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		
		Categoria objCategoria = (Categoria)entidade;
		List<EntidadeDominio> categorias = new ArrayList<EntidadeDominio>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM categorias");
		
		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			ResultSet rs2;
			
			while(rs.next()) {
				Categoria cat = new Categoria();
				cat.setId(rs.getInt("id_categoria"));
				cat.setNome(rs.getString("nome_categoria"));
				cat.setStatusCategoria(rs.getBoolean("status_categoria"));
				
				categorias.add(cat);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return categorias;
		
	}
	
}
