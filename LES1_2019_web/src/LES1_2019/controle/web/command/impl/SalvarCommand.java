package LES1_2019.controle.web.command.impl;

import LES1_2019.core.aplicacao.Resultado;
import LES1_2019.dominio.EntidadeDominio;

public class SalvarCommand extends AbstractCommand {

	public Resultado execute(EntidadeDominio entidade) {
		return fachada.salvar(entidade);
	}
}
