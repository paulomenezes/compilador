package compiler.tree.comando;

import compiler.exceptions.SemanticsException;

public interface Comando {
	void verificarSemantica() throws SemanticsException;
	String gerarCodigoIntermediario(String filename);
}
