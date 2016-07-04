package compiler.tree.expressao;

import java.io.PrintWriter;

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
	public void gerarCodigoIntermediario(PrintWriter file) {
		file.println("\tbipush " + intLiteral);
	}
}
