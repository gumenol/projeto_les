package LES1_2019.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import LES1_2019.dominio.Categoria;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Produto;

public class ProdutoDAO extends AbstractJdbcDAO {

	public ProdutoDAO() {
		super ("produtos", "id_produto");
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		System.out.println("entrou na dao");
		openConnection();
		PreparedStatement pst = null;
		Produto objProduto = (Produto)entidade;
		try {
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			//resgatando o id da marca antes de inserir o novo produto
			sql.append("SELECT id_marca FROM marcas WHERE nome_marca = " + objProduto.getMarca_produto());
			pst = connection.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			pst = null;
			
			objProduto.setId_marca_produto(rs.getInt("id_marca"));
			
			sql = new StringBuilder();
			
			sql.append("INSERT INTO produtos "
					+ "(nome_produto, "
					+ "valor_produto, "
					+ "descricao_produto, "
					+ "id_marca_produto, "
					+ "status_produto, "
					+ "id_categoria_produto) VALUES (?, ?, ?, ?, ?, ?)");
			pst = connection.prepareStatement(sql.toString());
			
			pst.setString(1, objProduto.getNome());
			pst.setBoolean(2, objProduto.getStatus_produto());
			pst.setDouble(3, objProduto.getValor_produto());
			pst.setString(4, objProduto.getDescricao_produto());
			pst.setInt(5, objProduto.getId_marca_produto());
			pst.setInt(6, objProduto.getCategoria().getId());
			
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
		Produto objProduto = (Produto)entidade;
		StringBuilder sql = new StringBuilder();
		
		if(objProduto.getNome()!= null &&
				objProduto.getValor_produto()>0 &&
				objProduto.getDescricao_produto()!= null &&
				objProduto.getId_marca_produto()>0 &&
				objProduto.getCategoria().getId()>0
				) {
			try {
				connection.setAutoCommit(false);
				sql.append("UPDATE produtos SET nome_produto=?, ");
				sql.append("valor_produto=?, descricao_produto=?, ");
				sql.append("id_marca_produto=?, id_categoria_produto=?, ");
				sql.append("status_produto=?");
				sql.append("WHERE id_produto=?");
				
				pst = connection.prepareStatement(sql.toString());
			
				pst.setString(1, objProduto.getNome());
				pst.setDouble(2, objProduto.getValor_produto());
				pst.setString(3, objProduto.getDescricao_produto());
				pst.setInt(4, objProduto.getId_marca_produto());
				pst.setInt(5, objProduto.getCategoria().getId());
				pst.setBoolean(6, objProduto.getStatus_produto());
				pst.setInt(7, objProduto.getId());
				
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
	}
	
	@Override
	public void excluir(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst = null;
		Produto objProduto = (Produto)entidade;
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE produtos SET status_produto=? ");
			sql.append("WHERE id_produto=?");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setBoolean(1, false);
			pst.setInt(2, objProduto.getId());
			
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
		openConnection();
		PreparedStatement pst = null;
		Produto objProduto = (Produto)entidade;
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE produtos SET status_produto=? ");
			sql.append("WHERE id_produto=?");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setBoolean(1, true);
			pst.setInt(2, objProduto.getId());
			
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
		StringBuilder sql = new StringBuilder();
		List<EntidadeDominio> produtos = new ArrayList<EntidadeDominio>();
		
		sql.append("SELECT produto.id_produto, produto.nome_produto, ");
		sql.append("marca.nome_marca, ");
		sql.append("categoria.nome_categoria, ");
		sql.append("produto.valor_produto, produto.descricao_produto, ");
		sql.append("produto.status_produto ");
		sql.append("FROM produtos AS produto ");
		sql.append("INNER JOIN categorias AS categoria ");
		sql.append("ON produto.id_categoria_produto = categoria.id_categoria ");
		sql.append("INNER JOIN marcas as marca ");
		sql.append("ON produto.id_marca_produto = marca.id_marca");
		
		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Produto prod = new Produto();
				prod.setCategoria(new Categoria());
				prod.setId(rs.getInt("id_produto"));
				prod.setNome(rs.getString("nome_produto"));
				prod.setMarca_produto(rs.getString("nome_marca"));
				prod.getCategoria().setNome(rs.getString("nome_categoria"));
				prod.setValor_produto(rs.getDouble("valor_produto"));
				prod.setDescricao_produto(rs.getString("descricao_produto"));
				prod.setStatus_produto(rs.getBoolean("status_produto"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}
	
}
