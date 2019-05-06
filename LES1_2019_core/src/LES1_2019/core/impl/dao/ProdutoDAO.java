package LES1_2019.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

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
		System.out.println("criou objetos");
		try {
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			/*//resgatando o id da marca antes de inserir o novo produto
			sql.append("SELECT id_marca FROM marcas WHERE marcas.nome_marca = `"+marca+"`");
			System.out.println(sql);
			pst = connection.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			
			objProduto.setId_marca_produto(rs.getInt("id_marca"));
			try {
				System.out.println(objProduto.getId_marca_produto());
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println();
			System.out.println("Selecionou marca");
			
			pst = null;*/
			
			
			
			sql = new StringBuilder();
			
			sql.append("INSERT INTO produtos "
					+ "(nome_produto, "
					+ "valor_produto, "
					+ "descricao_produto, "
					+ "id_marca_produto, "
					+ "id_categoria_produto, "
					+ "status_produto, "
					+ "imagem_produto) VALUES (?, ?, ?, ?, ?, ?, ?)");
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, objProduto.getNome());
			pst.setDouble(2, objProduto.getValor_produto());
			pst.setString(3, objProduto.getDescricao_produto());
			pst.setInt(4, objProduto.getId_marca_produto());
			pst.setInt(5, objProduto.getCategoria().getId());
			pst.setBoolean(6, true);
			pst.setString(7, objProduto.getImg_produto());
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			
			int id = 0;
			if(rs.next()) {
				id=rs.getInt(1);
				objProduto.setId(id);
			}
			
			connection.commit();
			
			connection.setAutoCommit(false);
			
			sql = new StringBuilder();
			sql.append("INSERT INTO estoque "
					+ "(id_produto_estoque, qtd_estoque) "
					+ "VALUES (?, 0)");
			
			pst = connection.prepareStatement(sql.toString());
			
			pst.setInt(1, objProduto.getId());
			
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
		System.out.println("ENTROU NA DAO");
		openConnection();
		PreparedStatement pst = null;
		Produto objProduto = (Produto)entidade;
		StringBuilder sql = new StringBuilder();
		
		System.out.println(objProduto.getNome()+" "+ 
		objProduto.getValor_produto()+" "+
		objProduto.getDescricao_produto()+" "+
		objProduto.getId_marca_produto()+" "+objProduto.getCategoria().getId());
		
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
				sql.append("id_marca_produto=?, id_categoria_produto=?, imagem_produto=? ");
				sql.append("WHERE id_produto=?");
				
				pst = connection.prepareStatement(sql.toString());
			
				pst.setString(1, objProduto.getNome());
				pst.setDouble(2, objProduto.getValor_produto());
				pst.setString(3, objProduto.getDescricao_produto());
				pst.setInt(4, objProduto.getId_marca_produto());
				pst.setInt(5, objProduto.getCategoria().getId());
				pst.setString(6, objProduto.getImg_produto());
				pst.setInt(7, objProduto.getId());
				
				pst.executeUpdate();
				connection.commit();
				
				connection.setAutoCommit(false);
				
				sql = new StringBuilder();
				sql.append("UPDATE estoque "
						+ "SET qtd_estoque=? WHERE id_produto_estoque = "+objProduto.getId());
				
				pst = connection.prepareStatement(sql.toString());
				
				pst.setInt(1, objProduto.getQtd_estoque());
				
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
		} /*else if(objProduto.getId()!=null) {
			sql = new StringBuilder();
			try {
			connection.setAutoCommit(false);
			
			sql.append("SELECT qtd_estoque from estoque "
					+ "WHERE id_produto_estoque = "+objProduto.getId());
			pst = connection.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			int qtd = 0;
			if(rs.next()) {
				qtd=rs.getInt("qtd_estoque");
				qtd--;
			}
			
			connection.commit();
			connection.setAutoCommit(false);
			
			sql.append("UPDATE estoque "
					+ "SET qtd_estoque="+qtd
					+ "WHERE id_produto_estoque = "+objProduto.getId());
			pst = connection.prepareStatement(sql.toString());
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
		}*/
	}
	
	@Override
	public void excluir(EntidadeDominio entidade) {
		System.out.println("entrou na dao excluir");
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
			System.out.println(objProduto.getId());
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
		System.out.println("entrou na dao ativar");
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
			System.out.println("id produto na dao"+ objProduto.getId());
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
		sql.append("SELECT produto.id_produto, produto.nome_produto, produto.imagem_produto, ");
		sql.append("marca.nome_marca, ");
		sql.append("categoria.nome_categoria, ");
		sql.append("produto.valor_produto, produto.descricao_produto, ");
		sql.append("produto.status_produto, est.qtd_estoque ");
		sql.append("FROM produtos AS produto ");
		sql.append("INNER JOIN categorias AS categoria ");
		sql.append("ON produto.id_categoria_produto = categoria.id_categoria ");
		sql.append("INNER JOIN marcas as marca ");
		sql.append("ON produto.id_marca_produto = marca.id_marca ");
		sql.append("INNER JOIN estoque as est ");
		sql.append("ON produto.id_produto = est.id_produto_estoque ");
		sql.append("ORDER BY produto.id_produto asc");
		
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
				prod.setImg_produto(rs.getString("imagem_produto"));
				prod.setQtd_estoque(rs.getInt("qtd_estoque"));
				produtos.add(prod);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}
	
}
