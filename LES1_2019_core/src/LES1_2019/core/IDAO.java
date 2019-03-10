package LES1_2019.core;

import java.sql.SQLException;
import java.util.List;

import LES1_2019.dominio.EntidadeDominio;


public interface IDAO {
	
	public void salvar(EntidadeDominio entidade) throws SQLException;
	public void alterar(EntidadeDominio entidade)throws SQLException;
	public void excluir(EntidadeDominio entidade)throws SQLException;
	public void ativar(EntidadeDominio entidade)throws SQLException;
	public List<EntidadeDominio> consultar(EntidadeDominio entidade)throws SQLException;
}
