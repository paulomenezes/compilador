package br.ufrpe.tree.expressao;

import br.ufrpe.tree.Tipo;

public class FloatLiteral implements Expressao {
	private Float floatLiteral;

	public FloatLiteral(Float floatLiteral) {
		this.floatLiteral = floatLiteral;
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
