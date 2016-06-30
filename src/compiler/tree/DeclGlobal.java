package compiler.tree;

import compiler.exceptions.SemanticsException;

public interface DeclGlobal {
	void verificarSemantica() throws SemanticsException ;
	String gerarCodigoIntermediario(String filename);
}
