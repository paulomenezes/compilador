package compiler.tree.comando;

import java.io.PrintWriter;

import compiler.exceptions.SemanticsException;

public interface Comando {
	void verificarSemantica() throws SemanticsException;
	void gerarCodigoIntermediario(PrintWriter file);
}
