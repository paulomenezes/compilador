package compiler.tree;

import java.util.LinkedList;

public class Programa {
	private LinkedList<DeclGlobal> declaracoes;

	public Programa() {
		declaracoes = new LinkedList<DeclGlobal>();
	}

	public void addLast(DeclGlobal dec) {
		this.declaracoes.addLast(dec);
	}
	
	public Boolean verificarSemantica() {
		return null;
	}
	
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}
}