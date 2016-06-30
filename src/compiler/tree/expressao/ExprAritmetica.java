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
			if(exp2.getTipo()!=Tipo.INT) throw new SemanticsException("Tipo das express�es s�o diferentes");
		}
		else if(exp1.getTipo()==Tipo.FLOAT){
			if(exp2.getTipo()!=Tipo.FLOAT) throw new SemanticsException("Tipo das express�es s�o diferentes");
			if(operacao.equals("%")) throw new SemanticsException("Float com Float n�o suporta essa opera��o");
		}
		else if(exp1.getTipo()==Tipo.CHAR){
			if(exp2.getTipo()!=Tipo.INT) throw new SemanticsException("Express�o Aritm�tica s� suporta Float com Float, Int com Int e Char com Int");
			if(!operacao.equals("+") && !operacao.equals("-")) throw new SemanticsException("Char com Int n�o suporta essa opera��o");
		}
		else throw new SemanticsException("Express�o Aritm�tica s� suporta Float com Float, Int com Int e Char com Int");;		
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
