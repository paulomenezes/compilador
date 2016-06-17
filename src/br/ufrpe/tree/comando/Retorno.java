package br.ufrpe.tree.comando;

import br.ufrpe.tree.Tipo;
import br.ufrpe.tree.expressao.Expressao;

public class Retorno implements Comando {
	private Expressao expressao;

	public Retorno(Expressao expr) {
		this.expressao = expr;
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
