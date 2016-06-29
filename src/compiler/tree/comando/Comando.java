package compiler.tree.comando;

import compiler.tree.Tipo;

public interface Comando {
	Boolean verificarSemantica();
	String gerarCodigoIntermediario(String filename);
}
