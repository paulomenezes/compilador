package compiler.tree;

public interface DeclGlobal {
	Boolean verificarSemantica() ;
	String gerarCodigoIntermediario(String filename);
}
