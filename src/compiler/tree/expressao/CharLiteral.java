package compiler.tree.expressao;

import compiler.tree.Tipo;

public class CharLiteral implements Expressao {
	private String charLiteral;

	public CharLiteral(String charLiteral) {
		this.charLiteral = charLiteral;
	}

	public Tipo getTipo() {
		return Tipo.CHAR;
	}

	@Override
	public void verificarSemantica() {
		
	}

	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}
}
