package compiler.tree.expressao;

import java.io.PrintWriter;

import compiler.exceptions.SemanticsException;
import compiler.tree.Tipo;

public class ExprLogica implements Expressao {
	private Expressao exp1;
	private Expressao exp2;
	private String operLogico;

	public ExprLogica(Expressao e1, Expressao e2, String oper) {
		this.exp1 = e1;
		this.exp2 = e2;
		this.operLogico = oper;
	}
	
	@Override
	public void verificarSemantica() throws SemanticsException {
		exp1.verificarSemantica();
		exp2.verificarSemantica();
		
		if(exp1.getTipo()!= Tipo.BOOLEAN || exp2.getTipo() != Tipo.BOOLEAN){
			throw new SemanticsException("Expressão aritmética só suporta tipo booleano");
		}
	}

	@Override
	public Tipo getTipo() {
		return Tipo.BOOLEAN;
	}

	@Override
	public void gerarCodigoIntermediario(PrintWriter file) {
		
	}
}
