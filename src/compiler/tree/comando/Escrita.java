package compiler.tree.comando;

import java.util.LinkedList;

import compiler.exceptions.SemanticsException;
import compiler.tree.expressao.Expressao;

public class Escrita implements Comando {
	private LinkedList<Expressao> expressao;

	public Escrita(LinkedList<Expressao> expressao) {
		this.expressao = expressao;
	}

	@Override
	public void verificarSemantica() throws SemanticsException {
		for(Expressao e : expressao) e.verificarSemantica();
	}

	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}
}
