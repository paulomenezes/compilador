package compiler.tree;

import java.util.LinkedList;

import compiler.exceptions.SemanticsException;

public class Programa {
	private LinkedList<DeclGlobal> declaracoes;

	public Programa() throws SemanticsException {
		declaracoes = new LinkedList<DeclGlobal>();
	}

	public void addLast(DeclGlobal dec) {
		this.declaracoes.addLast(dec);
	}
	
	public void verificarSemantica() throws SemanticsException {
		for(DeclGlobal decl : declaracoes) decl.verificarSemantica();
	}
	
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}
}