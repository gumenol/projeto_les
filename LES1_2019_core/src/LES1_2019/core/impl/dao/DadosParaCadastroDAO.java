package LES1_2019.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import LES1_2019.auxiliar.DadosDeCadastro;
import LES1_2019.dominio.Categoria;
import LES1_2019.dominio.EntidadeDominio;
import LES1_2019.dominio.Marca;

public class DadosParaCadastroDAO extends AbstractJdbcDAO {
	
	public DadosParaCadastroDAO() {
		super("table", "idTable");
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		System.out.println("entrou na dao");
		openConnection();
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		DadosDeCadastro dados = new DadosDeCadastro();
		Marca m = new Marca();
		Categoria cat = new Categoria();
		
		dados.setCategoria(new ArrayList<Categoria>());
		dados.setMarcas(new ArrayList<Marca>());
		
		try {
			
			sql = new StringBuilder();
			sql.append("SELECT * FROM categorias");
			pst = connection.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				cat = new Categoria();
				cat.setNome(rs.getString("nome_categoria"));
				cat.setId(rs.getInt("id_categoria"));
				boolean statusCat= rs.getBoolean("status_categoria");
				System.out.println(cat.getNome());
				if(statusCat) {
					System.out.println("Categoria adicionada");
				dados.getCategoria().add(cat);
				}
			}
			
			sql = new StringBuilder();
			sql.append("SELECT * FROM marcas");
			pst = connection.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			
			while(rs.next()) {
				m = new Marca();
				m.setNome_marca(rs.getString("nome_marca"));
				m.setId(rs.getInt("id_marca"));
				boolean statusMarca = rs.getBoolean("status_marca");
				System.out.println(m.getNome_marca());
				if(statusMarca) {
					System.out.println("marca adicionada");
				dados.getMarcas().add(m);
				}
		}
			
		ArrayList<EntidadeDominio> utility = new ArrayList<EntidadeDominio>();	
		utility.add(dados);
		
		return utility;
	
	} catch (SQLException e) {
		e.printStackTrace();
		}
	return null;
	}
}

