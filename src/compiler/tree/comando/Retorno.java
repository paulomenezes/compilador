package compiler.tree.comando;

import compiler.exceptions.SemanticsException;
import compiler.tree.expressao.Expressao;

public class Retorno implements Comando {
	private Expressao expressao;

	public Retorno(Expressao expr) {
		this.expressao = expr;
	}

	@Override
	public void verificarSemantica() throws SemanticsException {
		expressao.verificarSemantica();
	}

	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}

}
