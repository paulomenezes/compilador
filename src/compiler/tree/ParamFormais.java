package compiler.tree;

import java.io.PrintWriter;
import java.util.LinkedList;

import compiler.tabela.Declaracao;
import compiler.tree.comando.DeclVariavel;

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
	
	public void gerarCodigoIntermediario(PrintWriter file) {
		for (DeclVariavel declVariavel : paramFormais) {
			declVariavel.gerarCodigoIntermediario(file);
		}
	}
}
