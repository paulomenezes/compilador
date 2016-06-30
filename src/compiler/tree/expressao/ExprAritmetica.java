package compiler.tree.expressao;

import compiler.exceptions.SemanticsException;
import compiler.tree.Tipo;

public class ExprAritmetica implements Expressao {

	private Expressao exp1;
	private Expressao exp2;
	private String operacao;

	public ExprAritmetica(Expressao exp1, Expressao exp2, String operacao) {
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.operacao = operacao;
	}

	@Override
	public void verificarSemantica() throws SemanticsException {
		exp1.verificarSemantica();
		exp2.verificarSemantica();
		if(exp1.getTipo()==Tipo.INT){
			if(exp2.getTipo()!=Tipo.INT) throw new SemanticsException("Tipo das expressões são diferentes");
		}
		else if(exp1.getTipo()==Tipo.FLOAT){
			if(exp2.getTipo()!=Tipo.FLOAT) throw new SemanticsException("Tipo das expressões são diferentes");
			if(operacao.equals("%")) throw new SemanticsException("Float com Float não suporta essa operação");
		}
		else if(exp1.getTipo()==Tipo.CHAR){
			if(exp2.getTipo()!=Tipo.INT) throw new SemanticsException("Expressão Aritmética só suporta Float com Float, Int com Int e Char com Int");
			if(!operacao.equals("+") && !operacao.equals("-")) throw new SemanticsException("Char com Int não suporta essa operação");
		}
		else throw new SemanticsException("Expressão Aritmética só suporta Float com Float, Int com Int e Char com Int");;		
	}
	
	@Override
	public Tipo getTipo() {
		return exp1.getTipo();
	}

	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}

}
