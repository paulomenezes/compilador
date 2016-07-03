package compiler.tree;

import java.io.PrintWriter;

import compiler.exceptions.SemanticsException;

public interface DeclGlobal {
	void verificarSemantica() throws SemanticsException ;
	void gerarCodigoIntermediario(PrintWriter file);
}
