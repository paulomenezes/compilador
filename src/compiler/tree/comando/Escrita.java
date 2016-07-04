package compiler.tree.comando;

import java.io.PrintWriter;
import java.util.LinkedList;

import compiler.exceptions.SemanticsException;
import compiler.tree.expressao.CharLiteral;
import compiler.tree.expressao.Expressao;
import compiler.tree.expressao.IntLiteral;

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
		for (Expressao expr : expressao) {
			expr.gerarCodigoIntermediario(file);
			
			if (!(expr instanceof CharLiteral)) {
				file.println("\tgetstatic java/lang/System/out Ljava/io/PrintStream;");
				file.println("\tswap");
				file.println("\tinvokevirtual java/io/PrintStream/println(I)V");
			}
		}
	}
}
