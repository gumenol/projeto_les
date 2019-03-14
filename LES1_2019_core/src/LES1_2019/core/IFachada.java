package LES1_2019.core;

import LES1_2019.core.aplicacao.Resultado;
import LES1_2019.dominio.EntidadeDominio;

public interface IFachada {
	public Resultado salvar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public Resultado alterar(EntidadeDominio entidade);
	public Resultado consultar(EntidadeDominio entidade);
	public Resultado visualizar(EntidadeDominio entidade);
	public Resultado ativar(EntidadeDominio entidade);
}
