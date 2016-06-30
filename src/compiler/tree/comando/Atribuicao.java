package compiler.tree.comando;

import compiler.tree.Tipo;
import compiler.tree.expressao.Expressao;

public class Atribuicao implements Comando {
	private String identificador;
	private Expressao expressao;

	public Atribuicao(String ident, Expressao expressao) {
		this.identificador = ident;
		this.expressao = expressao;
	}

	@Override
	public Boolean verificarSemantica() {
		return expressao.verificarSemantica();
	}

	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}

}
