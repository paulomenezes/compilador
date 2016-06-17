package br.ufrpe.tree.expressao;

import br.ufrpe.tree.Tipo;

public interface Expressao {
	Boolean verificarSemantica() ;
	Tipo getTipo();
	String gerarCodigoIntermediario(String filename);
}
