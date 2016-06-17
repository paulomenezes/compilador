package br.ufrpe.tree.comando;

import br.ufrpe.tree.Tipo;

public interface Comando {
	Boolean verificarSemantica();
	String gerarCodigoIntermediario(String filename);
}
