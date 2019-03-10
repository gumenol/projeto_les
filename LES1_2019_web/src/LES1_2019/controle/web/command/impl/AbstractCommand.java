package LES1_2019.controle.web.command.impl;

import LES1_2019.controle.web.command.ICommand;
import LES1_2019.core.IFachada;
import LES1_2019.core.impl.controle.Fachada;

public abstract class AbstractCommand implements ICommand{
	protected IFachada fachada = new Fachada();
}
