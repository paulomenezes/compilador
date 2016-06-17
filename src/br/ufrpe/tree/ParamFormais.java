package br.ufrpe.tree;

import java.util.LinkedList;

import br.ufrpe.tree.comando.DeclVariavel;

public class ParamFormais {
	private LinkedList<DeclVariavel> paramFormais;

	public ParamFormais(LinkedList<DeclVariavel> paramFormais) {
		this.paramFormais = paramFormais;
	}

	public ParamFormais() {
		paramFormais = new LinkedList<DeclVariavel>();
	}

	public void add(DeclVariavel d) {
		paramFormais.addLast(d);
	}
	
	public Boolean verificarSemantica() {
		return null;
	}
	
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}
}
