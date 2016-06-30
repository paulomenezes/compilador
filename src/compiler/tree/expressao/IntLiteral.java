package compiler.tree.expressao;

import compiler.tree.Tipo;

public class IntLiteral implements Expressao {
	private Integer intLiteral;

	public IntLiteral(Integer intLiteral) {
		this.intLiteral = intLiteral;
	}
	
	@Override
	public void verificarSemantica() {
		
	}

	@Override
	public Tipo getTipo() {
		return Tipo.INT;
	}

	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}
}
