package compiler.tree;

import java.util.LinkedList;

public class Programa {
	private LinkedList<DeclGlobal> declaracoes;

	public Programa() {
		declaracoes = new LinkedList<DeclGlobal>();
		this.verificarSemantica();
	}

	public void addLast(DeclGlobal dec) {
		this.declaracoes.addLast(dec);
	}
	
	public Boolean verificarSemantica() {
		for(DeclGlobal decl : declaracoes){
			if(!decl.verificarSemantica()) return false;
		}
		return true;
	}
	
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}
}