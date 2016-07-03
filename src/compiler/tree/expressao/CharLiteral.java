package compiler.tree.expressao;

import java.io.PrintWriter;

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
	public void gerarCodigoIntermediario(PrintWriter file) {
		file.print(charLiteral.replace("'", ""));
	}

	@Override
	public Number getValor() {
		// TODO Auto-generated method stub
		return null;
	}
}
