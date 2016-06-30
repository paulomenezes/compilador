package compiler.tabela;

import compiler.tree.Tipo;

public class Declaracao {
	private Tipo tipo;
	private String nome;
	private int escopo;
	
	public Declaracao(Tipo tipo, String nome, int escopo) {
		this.tipo = tipo;
		this.nome = nome;
		this.escopo = escopo;
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

	public int getEscopo() {
		return escopo;
	}

	public void setEscopo(int escopo) {
		this.escopo = escopo;
	}
	
	public boolean equals(Object obj) {
		Declaracao f = (Declaracao) obj;
		if(this.getNome().equals(f.getNome()) && this.getEscopo()==f.escopo) return true;
		else return false;
	}

	public String toString() {
		return this.getNome() + " " + this.getEscopo();
	}
	
}
