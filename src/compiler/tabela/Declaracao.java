package compiler.tabela;

import compiler.tree.Programa;
import compiler.tree.Tipo;

public class Declaracao {
	private Tipo tipo;
	private String nome;
	private int memoria;
	private int escopo;
	
	public Declaracao(Tipo tipo, String nome, int escopo) {
		this.tipo = tipo;
		this.nome = nome;
		this.escopo = escopo;
		if (escopo == 1) {
			this.memoria = Programa.STACK_INDEX;
			Programa.STACK_INDEX++;
		}
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getMemoria() {
		return memoria;
	}
	
	public void setMemoria(int memoria) {
		this.memoria = memoria;
	}
	
	public boolean equals(Object obj) {
		Declaracao f = (Declaracao) obj;
		if(this.getNome().equals(f.getNome())) return true;
		else return false;
	}

	public String toString() {
		return this.getNome();
	}

	public int getEscopo() {
		return escopo;
	}

	public void setEscopo(int escopo) {
		this.escopo = escopo;
	}
}
