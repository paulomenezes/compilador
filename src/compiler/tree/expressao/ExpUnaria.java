package compiler.tree.expressao;

import java.io.PrintWriter;

import compiler.exceptions.SemanticsException;
import compiler.tree.Tipo;

public class ExpUnaria implements Expressao {
	private Expressao expr;
	private String operacao;

	public ExpUnaria(String operacao, Expressao expr) {
		this.operacao = operacao;
		this.expr = expr;
	}

	
	@Override
	public void verificarSemantica() throws SemanticsException {
		expr.verificarSemantica();
		if(operacao.equals("-")){
			if(expr.getTipo()!=Tipo.FLOAT && expr.getTipo()!=Tipo.INT) throw new SemanticsException("Expressão Unária só suporta Floa e Int");
		}
	}

	@Override
	public Tipo getTipo() {
		return expr.getTipo();
	}	
	
	public void gerarCodigoIntermediario(PrintWriter file) {
		
	}

	@Override
	public Number getValor() {
		return expr.getValor();
	}
}