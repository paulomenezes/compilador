package br.ufrpe.tree.comando;

import java.util.LinkedList;

import br.ufrpe.tree.Tipo;
import br.ufrpe.tree.expressao.Expressao;

public class Escrita implements Comando {
	private LinkedList<Expressao> expressao;

	public Escrita(LinkedList<Expressao> expressao) {
		this.expressao = expressao;
	}

	@Override
	public Boolean verificarSemantica() {
		return null;
	}

	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}
}
