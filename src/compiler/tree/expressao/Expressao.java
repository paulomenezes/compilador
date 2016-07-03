package compiler.tree.expressao;

import java.io.PrintWriter;

import compiler.exceptions.SemanticsException;
import compiler.tree.Tipo;

public interface Expressao {
	void verificarSemantica() throws SemanticsException;
	Tipo getTipo();
	void gerarCodigoIntermediario(PrintWriter file);
	
	Number getValor();
}
