package compiler.tree.expressao;

import java.io.PrintWriter;

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
	public void gerarCodigoIntermediario(PrintWriter file) {
		
	}

	@Override
	public Number getValor() {
		return null;
	}
}