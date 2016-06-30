package compiler.tree.expressao;

import compiler.exceptions.SemanticsException;
import compiler.tree.Tipo;

public interface Expressao {
	void verificarSemantica() throws SemanticsException;
	Tipo getTipo();
	String gerarCodigoIntermediario(String filename);
}
