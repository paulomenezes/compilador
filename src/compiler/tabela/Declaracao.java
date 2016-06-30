package compiler.tabela;

import compiler.tree.Tipo;

public class Declaracao {
	private Tipo tipo;
	private String nome;
	private Object valor;
	private int escopo;
	
	public Declaracao(Tipo tipo, String nome, Object valor, int escopo) {
		this.tipo = tipo;
		this.nome = nome;
		this.valor = valor;
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
	
	public Object getValor() {
		return valor;
	}
	
	public void setValor(Object valor) {
		this.valor = valor;
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
