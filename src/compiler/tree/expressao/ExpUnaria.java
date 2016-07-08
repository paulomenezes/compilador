package compiler.tree.expressao;

import java.io.PrintWriter;

import compiler.exceptions.SemanticsException;
import compiler.tree.Tipo;
import tests.TestParser;

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
			if(expr.getTipo()!=Tipo.FLOAT && expr.getTipo()!=Tipo.INT) TestParser.erros.add("Expressão Unária só suporta Floa e Int");
		}
	}

	@Override
	public Tipo getTipo() {
		return expr.getTipo();
	}	
	
	public void gerarCodigoIntermediario(PrintWriter file) {
		
	}
}