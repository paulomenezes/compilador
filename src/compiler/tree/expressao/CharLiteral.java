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
		file.println("\tgetstatic java/lang/System/out Ljava/io/PrintStream;");
		file.print("\tldc \"");
		file.println(charLiteral.replace("'", "") + "\"");
		file.println("\tinvokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
	}
}
