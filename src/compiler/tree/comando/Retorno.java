package compiler.tree.comando;

import compiler.exceptions.SemanticsException;
import compiler.tabela.Tabela;
import compiler.tree.expressao.Expressao;

public class Retorno implements Comando {
	private Expressao expressao;

	public Retorno(Expressao expr) {
		this.expressao = expr;
	}

	@Override
	public void verificarSemantica() throws SemanticsException {
		expressao.verificarSemantica();
		Tabela t = Tabela.getInstance();
		if(t.getTipoUltima()!=expressao.getTipo()) throw new SemanticsException("Tipo do retorno diferente do tipo da função");
	}

	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}

}
