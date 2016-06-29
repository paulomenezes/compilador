package compiler.tree;

import java.util.LinkedList;
import java.util.List;

import compiler.tree.comando.DeclVariavel;

public class Assinatura {
	private LinkedList<DeclVariavel> paramFormais;
	private Tipo tipo;
	private String identificador;

	public Assinatura(String identificar,
			LinkedList<DeclVariavel> paramFormais, Tipo tipo) {
		this.paramFormais = paramFormais;
		this.tipo = tipo;
		this.identificador = identificar;
	}

	public Assinatura(String identificar, LinkedList<DeclVariavel> paramFormais) {
		this.paramFormais = paramFormais;
		this.identificador = identificar;
	}

	public Boolean verificarSemantica() {
		return null;
	}

	public String gerarCodigoIntermediario(String filename) {
		return null;
	}
}
