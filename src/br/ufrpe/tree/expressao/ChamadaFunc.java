package br.ufrpe.tree.expressao;

import java.util.LinkedList;

import br.ufrpe.tree.Tipo;
import br.ufrpe.tree.comando.Comando;

public class ChamadaFunc implements Expressao, Comando {
	private String identificador;
	private LinkedList<Expressao> listaExprs;

	public ChamadaFunc(String identificador, LinkedList<Expressao> listaExprs) {
		this.identificador = identificador;
		this.listaExprs = listaExprs;
	}
	
	@Override
	public Boolean verificarSemantica() {
		return null;
	}

	@Override
	public Tipo getTipo() {
		return null;
	}
	
	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}
}
