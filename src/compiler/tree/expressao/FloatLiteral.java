package compiler.tree.expressao;

import java.io.PrintWriter;

import compiler.tree.Tipo;

public class FloatLiteral implements Expressao {
	private Float floatLiteral;

	public FloatLiteral(Float floatLiteral) {
		this.floatLiteral = floatLiteral;
	}

	
	@Override
	public void verificarSemantica() {
		
	}

	@Override
	public Tipo getTipo() {
		return Tipo.FLOAT;
	}


	@Override
	public void gerarCodigoIntermediario(PrintWriter file) {
		
	}
}
