package compiler.tree.expressao;

import java.io.PrintWriter;

import compiler.exceptions.SemanticsException;
import compiler.tree.Tipo;

public class ExprRelacional implements Expressao {

	private Expressao expr1;
	private Expressao expr2;
	private String operador;

	public ExprRelacional(Expressao expr1, Expressao expr2, String operador) {
		this.expr1 = expr1;
		this.expr2 = expr2;
		this.operador = operador;
	}
	
	@Override
	public void verificarSemantica() throws SemanticsException {
		expr1.verificarSemantica();
		expr2.verificarSemantica();
		
		if(expr1.getTipo()==Tipo.FLOAT || expr1.getTipo()==Tipo.INT){
			if(expr1.getTipo()!=expr2.getTipo())  throw new SemanticsException("Tipo das expressões são diferentes");
		}
		else{
			if(!operador.equals("==") && !operador.equals("!="))  throw new SemanticsException("Essa operação só suporta Float com Float e Int com Int");
			if(expr1.getTipo()!=expr2.getTipo()) throw new SemanticsException("Tipo das expressões são diferentes");
		}
	}

	@Override
	public Tipo getTipo() {
		return Tipo.BOOLEAN;
	}
	
	@Override
	public void gerarCodigoIntermediario(PrintWriter file) {
		expr1.gerarCodigoIntermediario(file);
		expr2.gerarCodigoIntermediario(file);
	}

	@Override
	public Number getValor() {
		return null;
	}

}
