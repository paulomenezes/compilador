package br.ufrpe.tree;

public interface DeclGlobal {
	Boolean verificarSemantica() ;
	String gerarCodigoIntermediario(String filename);
}
