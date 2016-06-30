package compiler.tree.expressao;

import compiler.tree.Tipo;

public class ExpUnaria implements Expressao {
	private Expressao expr;
	private String operacao;

	public ExpUnaria(String operacao, Expressao expr) {
		this.operacao = operacao;
		this.expr = expr;
	}

	
	@Override
	public Boolean verificarSemantica() {
		if(operacao.equals("-")){
			if(expr.getTipo()!=Tipo.FLOAT && expr.getTipo()!=Tipo.INT) return false;
		}
		return true;
	}

	@Override
	public Tipo getTipo() {
		return expr.getTipo();
	}
	
	
	
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}
}