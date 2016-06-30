package compiler.tree.expressao;

import compiler.tabela.Tabela;
import compiler.tree.Tipo;

public class ExprIdentificador implements Expressao {
	private String identificador;

	public ExprIdentificador(String identificador) {
		this.identificador = identificador;
	}

	@Override
	public Boolean verificarSemantica() {
		return null;
	}

	@Override
	public Tipo getTipo() {
		Tabela t = Tabela.getInstance();
		return t.getTipo(this.identificador);
	}

	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}

}
