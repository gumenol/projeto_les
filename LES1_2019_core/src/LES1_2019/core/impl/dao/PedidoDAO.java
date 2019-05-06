package LES1_2019.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import LES1_2019.dominio.Cartao;
import LES1_2019.dominio.Endereco;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Pedido;
import LES1_2019.dominio.Produto;
import LES1_2019.dominio.User;


public class PedidoDAO extends AbstractJdbcDAO {

	public PedidoDAO() {
		super ("pedidos", "id_pedido");
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		openConnection();                                      // abre a conexão com o Banco
        PreparedStatement pst = null;
		Pedido objPedido = (Pedido) entidade;
		Pedido ped = new Pedido();
		//CupomPromocional cpP = new CupomPromocional();
		//ArrayList<CupomTroca> cpT = new ArrayList<CupomTroca>();
		//double ValorDesconto=0;
		double total=0;
		boolean flg=false;
		//CupomTroca cp;
		ResultSet rs;
		StringBuilder sql;

		if(objPedido != null) {
			if(!objPedido.isStatus_troca_pedido()) {
			try {
				sql = new StringBuilder();
				connection.setAutoCommit(false);
				java.util.Date dt = new java.util.Date();
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				System.out.println("VALOR TOTAL PEDIDO: "+objPedido.getValor_total());
				System.out.println("ID USER: "+objPedido.getUser().getId());
				System.out.println("CEP ENTREGA: "+objPedido.getEndEntrega().getCep());
				System.out.println("CEP Cobranca: "+objPedido.getEndCobranca().getCep());
				System.out.println("Num ENTREGA: "+objPedido.getEndEntrega().getNumero());
				System.out.println("Num Cobranca: "+objPedido.getEndCobranca().getNumero());
					String currentTime = sdf.format(dt);
				
				sql.append("INSERT INTO pedidos "
						+ "(valor_pedido, "
						+ "status_troca_pedido, "
						+ "id_usuario_pedido, "
						+ "rota_entrega_pedido, "
						+ "cep_endereco_entrega, "
						+ "cep_endereco_cobranca, "
						+ "num_endereco_cobranca, "
						+ "num_endereco_entrega, "
						+ "data_pedido) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				
				pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				
				pst.setDouble(1, objPedido.getValor_total());
				pst.setBoolean(2, false);
				pst.setInt(3, objPedido.getUser().getId());
				pst.setString(4, "Pagamento em analise");
				pst.setString(5, objPedido.getEndEntrega().getCep());
				pst.setString(6, objPedido.getEndCobranca().getCep());
				pst.setString(7, objPedido.getEndCobranca().getNumero());
				pst.setString(8, objPedido.getEndEntrega().getNumero());
				pst.setString(9, currentTime);
				
				System.out.println(pst);
				
				pst.executeUpdate();
				
				rs = pst.getGeneratedKeys();
				int num = 0;
				if(rs.next()) {
					num = rs.getInt(1);
					objPedido.setId(num);
				}
				
				
				for(Produto prod:objPedido.getProdutos()) {
					connection.setAutoCommit(false);
					try {
					sql = new StringBuilder();
					sql.append("INSERT INTO pedido_produtos (id_pedido_usuario, id_produto, id_qtd_produto, subtotal) "
							+ "VALUES (?, ?, ?, ?)");
					pst = connection.prepareStatement(sql.toString());
					pst.setInt(1, objPedido.getId());
					pst.setInt(2, prod.getId());
					pst.setInt(3, prod.getQtdtotal());
					pst.setDouble(4, prod.getSubtotal());
					
					pst.executeUpdate();
					connection.commit();
					} catch(SQLException e) {
						e.printStackTrace();
					}
				}
				
				for(Cartao cart:objPedido.getCartoes()) {
					
					try {
					connection.setAutoCommit(false);
					sql = new StringBuilder();
					sql.append("INSERT INTO pagamento_pedidos (id_pedido, num_cartao, valor_pagamento, bandeira_cartao) "
							+ "VALUES(?, ?, ?, ?)");
					pst = connection.prepareStatement(sql.toString());
					pst.setInt(1, objPedido.getId());
					pst.setString(2, cart.getNumero_cartao());
					pst.setDouble(3, cart.getValor_pago());
					pst.setInt(4, cart.getId_bandeira_cartao());
					
					System.out.println(pst);
					pst.executeUpdate();
					connection.commit();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
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
		
	}
	
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		openConnection();                                       // abre a conexão com o Banco
        PreparedStatement pst = null;
        Pedido objPedido = (Pedido) entidade;
        StringBuilder sql = new StringBuilder();
        if(objPedido != null) {
        	if(objPedido.isStatus_troca_pedido()) {
        		try {
        			if(objPedido.getRota_entrega_pedido().equals("Pagamento em analise") || objPedido.getRota_entrega_pedido().equals("Entregue")) {
        			connection.setAutoCommit(false);
        			sql.append("UPDATE pedidos set status_troca_pedido=?, rota_entrega_pedido=? ");
        			sql.append("WHERE id_pedido=?");
        			
        			pst = connection.prepareStatement(sql.toString());
        			
        			pst.setBoolean(1, objPedido.isStatus_troca_pedido());
        			pst.setString(2, "Troca solicitada");
        			pst.setInt(3, objPedido.getId());
        			
        			pst.executeUpdate();
        			connection.commit();
        			}
        			else if(objPedido.getRota_entrega_pedido().equals("Troca autorizada")) {
        				connection.setAutoCommit(false);
        				
        				java.util.Date dt = new java.util.Date();
        				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        					String currentTime = sdf.format(dt);
            			
            			sql = new StringBuilder();
            			sql.append("INSERT INTO cupons_troca (valor_cupom, id_pedido_cupom, id_usuario_cupom, status_cupom, datacriacao_cupom) ");
            			sql.append("VALUES (?, ?, ?, true, ?)");
            			
            			pst = connection.prepareStatement(sql.toString());
            			
            			pst.setDouble(1, objPedido.getValor_total());
            			pst.setInt(2, objPedido.getId());
            			pst.setInt(3, objPedido.getUser().getId());
            			pst.setBoolean(4, true);
            			pst.setString(5, currentTime);
            			
            			pst.executeUpdate();
            			connection.commit();
        			}
        		} catch (SQLException e) {
	        			try {
							connection.rollback();
						}catch (SQLException e1) {
							e1.printStackTrace();
						}
						e.printStackTrace();
					}  finally {
						try {
							pst.close();
							connection.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
        		}
        	else if(objPedido.getStatus()!=null) {
        		System.out.println("status nao e nulo!: "+objPedido.getStatus());
        		if(objPedido.getStatus().equals("Pagamento em analise")) {
	        		try {
	        		connection.setAutoCommit(false);
	    			sql.append("UPDATE pedidos set rota_entrega_pedido=? ");
	    			sql.append("WHERE id_pedido=?");
	    			
	    			pst = connection.prepareStatement(sql.toString());
	    			pst.setString(1, "Pagamento autorizado");
	    			pst.setInt(2, objPedido.getId());
	    			
	    			pst.executeUpdate();
	    			connection.commit();
	        		}catch (SQLException e) {
	        			try {
							connection.rollback();
						}catch (SQLException e1) {
							e1.printStackTrace();
						}
						e.printStackTrace();
					}  finally {
						try {
							pst.close();
							connection.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
        		}else if(objPedido.getStatus().equals("Pedido negado")) {
        			System.out.println("Entrou negar pedido");
        			try {
    	        		connection.setAutoCommit(false);
    	    			sql.append("UPDATE pedidos set rota_entrega_pedido=? ");
    	    			sql.append("WHERE id_pedido=?");
    	    			
    	    			pst = connection.prepareStatement(sql.toString());
    	    			pst.setString(1, "Pedido negado");
    	    			pst.setInt(2, objPedido.getId());
    	    			
    	    			pst.executeUpdate();
    	    			connection.commit();
    	        		}catch (SQLException e) {
    	        			try {
    							connection.rollback();
    						}catch (SQLException e1) {
    							e1.printStackTrace();
    						}
    						e.printStackTrace();
    					}  finally {
    						try {
    							pst.close();
    							connection.close();
    						} catch (SQLException e) {
    							e.printStackTrace();
    						}
    					}
        		}
        	}
        	else {
        		try {
        			System.out.println("ENTROU DENTRO DA DAO");
        			for(Produto prod:objPedido.getProdutos()) {
        				System.out.println("entrou for");
        				if(prod.getId()==objPedido.getId_item_atual()) {
        				if(prod.isRemover()) {
        					System.out.println("entrou remover");
        					connection.setAutoCommit(false);
        					
        					sql=new StringBuilder();
        					sql.append("SELECT qtd_estoque FROM estoque WHERE id_produto_estoque=?");
        					pst = connection.prepareStatement(sql.toString());
        					pst.setInt(1, prod.getId());
        					int qtd = 0;
        					ResultSet rs = pst.executeQuery();
        					if(rs.next()) {
        						qtd = rs.getInt("qtd_estoque");
        					}
		        			
        					connection.commit();
        					
        					connection.setAutoCommit(false);
		        			
		        			sql = new StringBuilder();
		        			sql.append("UPDATE estoque set qtd_estoque=?");
		        			sql.append(" WHERE id_produto_estoque=?");
		        			
		        			pst = connection.prepareStatement(sql.toString());
		        			
		        			qtd = qtd + (prod.getQtdremover());
		        			
		        			pst.setInt(1, qtd);
		        			pst.setInt(2, prod.getId());
		        			
		        			
		        			pst.executeUpdate();
		        			connection.commit();
        				}
        				else {
        					connection.setAutoCommit(false);
        					
        					sql=new StringBuilder();
        					sql.append("SELECT qtd_estoque FROM estoque WHERE id_produto_estoque=?");
        					pst = connection.prepareStatement(sql.toString());
        					pst.setInt(1, prod.getId());
        					int qtd = 0;
        					ResultSet rs = pst.executeQuery();
        					if(rs.next()) {
        						qtd = rs.getInt("qtd_estoque");
        					}
		        			
        					connection.commit();
        					connection.setAutoCommit(false);
        					
		        			sql = new StringBuilder();
		        			sql.append("UPDATE estoque set qtd_estoque=?");
		        			sql.append(" WHERE id_produto_estoque=?");
		        			
		        			pst = connection.prepareStatement(sql.toString());
		        			System.out.println("QUANTIDADE: "+qtd);
		        			System.out.println("QTD PRODUTO: "+prod.getQtd_pedido());
		        			
		        			qtd = qtd - (prod.getQtd_pedido());
		        			System.out.println("QTD FINAL: "+qtd);
		        			pst.setInt(1, qtd);
		        			pst.setInt(2, prod.getId());
		        			
		        			
		        			pst.executeUpdate();
		        			connection.commit();
        				}
        			}
        		}
        			
        		} catch (SQLException e) {
        			try {
						connection.rollback();
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}  finally {
					try {
						pst.close();
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
        		}
        	}
        		
        	}
        }
	
	@Override
		public List<EntidadeDominio> consultar(EntidadeDominio entidade){
		System.out.println("ENTROU CONSULTAR");
			PreparedStatement pst = null;
			PreparedStatement pst2 = null;
			PreparedStatement pst3 = null;
			ResultSet rs2;
			ResultSet rs3;
			Pedido objPedido = (Pedido) entidade;
			StringBuilder sql = new StringBuilder();
			List<EntidadeDominio> pedidos = new ArrayList<EntidadeDominio>();
			
			System.out.println("entidade: "+objPedido);
			System.out.println("user: "+objPedido.getUser());
			System.out.println("id: "+objPedido.getUser().getId());
			
			sql.append("SELECT * FROM pedidos AS pedido ");
			sql.append("INNER JOIN pedido_produtos AS produtos ");
			sql.append("ON pedido.id_pedido = produtos.id_pedido_usuario ");
			sql.append("INNER JOIN pagamento_pedidos AS pagamentos ");
			sql.append("ON pedido.id_pedido = pagamentos.id_pedido ");
			sql.append("INNER JOIN bandeira_cartoes AS bandeiras ");
			sql.append("ON pagamentos.bandeira_cartao = bandeiras.id_bandeira ");
			sql.append("WHERE 1=1");
			
			System.out.println("PASSOU ate aqui");
			if(objPedido.getUser().getId()!=null) {
				if(objPedido.getUser().getId()>0) {
				sql.append(" AND id_usuario_pedido = "+objPedido.getUser().getId());
				}
			}
			sql.append(" GROUP BY pedido.id_pedido");
			System.out.println("ANTES DO RS NEXT");
			
			try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Pedido ped = new Pedido();
				ped.setProdutos(new ArrayList<Produto>());
				ped.setUser(new User());
				ped.setEndEntrega(new Endereco());
				ped.setEndCobranca(new Endereco());
				ped.setId(rs.getInt("id_pedido"));
				ped.setValor_total(rs.getDouble("valor_pedido"));
				ped.setDataPedido(rs.getTimestamp("data_pedido"));
				ped.setStatus_troca_pedido(rs.getBoolean("status_troca_pedido"));
				ped.getUser().setId(rs.getInt("id_usuario_pedido"));
				ped.setStatus(rs.getString("rota_entrega_pedido"));
				ped.getEndEntrega().setCep(rs.getString("cep_endereco_entrega"));
				ped.getEndEntrega().setNumero(rs.getString("num_endereco_entrega"));
				ped.getEndCobranca().setCep(rs.getString("cep_endereco_cobranca"));
				ped.getEndCobranca().setNumero(rs.getString("num_endereco_cobranca"));
			
				
				sql = new StringBuilder();
				sql.append("SELECT * FROM pedido_produtos as ped");
				sql.append(" INNER JOIN produtos AS produto");
				sql.append(" ON ped.id_produto = produto.id_produto");
				sql.append(" WHERE ped.id_pedido_usuario = ?");
				
				pst2 = connection.prepareStatement(sql.toString());
				pst2.setInt(1, ped.getId());
				rs2 = pst2.executeQuery();
				int i = 0;
				
				for(i = 0; rs2.next(); i++) {
    				if(ped.getId() == rs2.getInt("id_pedido_usuario")) {
    					ped.getProdutos().add(new Produto());
    					ped.getProdutos().get(i).setNome(rs2.getString("nome_produto"));
    					ped.getProdutos().get(i).setQtd_pedido(rs2.getInt("id_qtd_produto"));
    					ped.getProdutos().get(i).setValor_produto(rs2.getDouble("valor_produto"));
    					ped.getProdutos().get(i).setId(rs2.getInt("id_produto"));
    					ped.getProdutos().get(i).setPedido(ped.getId());
    					ped.getProdutos().get(i).setSubtotal(rs2.getDouble("subtotal"));
    				}
    			}
				
				sql = new StringBuilder();
				sql.append("SELECT * FROM pagamento_pedidos ");
				sql.append("WHERE id_pedido = ?");
				
				pst3 = connection.prepareStatement(sql.toString());
				pst3.setInt(1, ped.getId());
				rs3 = pst3.executeQuery();
				
				for(i = 0; rs3.next(); i++) {
					if(ped.getId() == rs3.getInt("id_pedido")) {
						ped.getCartoes().add(new Cartao());
						ped.getCartoes().get(i).setNumero_cartao(rs3.getString("num_cartao"));
						ped.getCartoes().get(i).setPedido(ped.getId());
						ped.getCartoes().get(i).setId_bandeira_cartao(rs3.getInt("bandeira_cartao"));
					}
				}
				pedidos.add(ped);
				
			}
				} catch(SQLException e) {
					e.printStackTrace();
			}
			System.out.println(pedidos.size());
			return pedidos;
		}
	
	}
