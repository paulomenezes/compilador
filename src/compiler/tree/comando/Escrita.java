package compiler.tree.comando;

import java.io.PrintWriter;
import java.util.LinkedList;

import compiler.exceptions.SemanticsException;
import compiler.tree.expressao.Expressao;

public class Escrita implements Comando {
	private LinkedList<Expressao> expressao;

	public Escrita(LinkedList<Expressao> expressao) {
		this.expressao = expressao;
	}

	@Override
	public void verificarSemantica() throws SemanticsException {
		for(Expressao e : expressao) e.verificarSemantica();
	}

	@Override
	public void gerarCodigoIntermediario(PrintWriter file) {
		file.println("\tgetstatic java/lang/System/out Ljava/io/PrintStream;");
		file.print("\tldc \"");
		for (Expressao expressao2 : expressao) {
			expressao2.gerarCodigoIntermediario(file);
		}
		file.println("\"");
	    file.println("\tinvokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
	}
}
