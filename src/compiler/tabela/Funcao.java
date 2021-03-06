package compiler.tabela;

import java.util.LinkedList;

import compiler.tree.Tipo;

public class Funcao {
	private LinkedList<Declaracao> list;
	private Tipo tipo;
	private String nome;
	
	public Funcao(Tipo tipo, String nome) {
		this.list = new LinkedList<Declaracao>();
		this.tipo = tipo;
		this.nome = nome;
	}
	
	public LinkedList<Declaracao> getList() {
		return list;
	}
	
	public void setList(LinkedList<Declaracao> list) {
		this.list = list;
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
	
	public void addList(Declaracao dec) {
		list.addFirst(dec);
	}
	
	public boolean equals(Object obj) {
		Funcao f = (Funcao) obj;
		if(this.getNome().equals(f.getNome())) return true;
		else return false;
	}
	
	public String toString(){
		return this.getNome();
	}
	
	public Tipo getTipo(String nome){
		for(int i = 0; i < list.size(); i++){
			Declaracao d =list.get(i);
			if(d.getNome().equals(nome)) return d.getTipo();
		}
		return null;
	}
}
