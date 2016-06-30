package compiler.tree.expressao;

import compiler.tree.Tipo;

public class StringLiteral implements Expressao {

	private String stringLiteral;

	public StringLiteral(String stringLiteral) {
		this.stringLiteral = stringLiteral;

	}

	@Override
	public void verificarSemantica() {
		
	}

	@Override
	public Tipo getTipo() {
		return Tipo.STRING;
	}


	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}
}