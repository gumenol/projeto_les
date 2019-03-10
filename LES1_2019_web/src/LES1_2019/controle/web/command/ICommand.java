package LES1_2019.controle.web.command;

import LES1_2019.core.aplicacao.Resultado;
import LES1_2019.dominio.EntidadeDominio;

public interface ICommand {

	public Resultado execute(EntidadeDominio entidade);
}
