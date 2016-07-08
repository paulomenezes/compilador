package compiler.tree.expressao;

import java.io.PrintWriter;

import compiler.exceptions.SemanticsException;
import compiler.tree.Tipo;
import tests.TestParser;

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
			if(exp2.getTipo()!=Tipo.INT) TestParser.erros.add("Tipo das express�es s�o diferentes");
		}
		else if(exp1.getTipo()==Tipo.FLOAT){
			if(exp2.getTipo()!=Tipo.FLOAT) TestParser.erros.add("Tipo das express�es s�o diferentes");
			if(operacao.equals("%")) TestParser.erros.add("Float com Float n�o suporta essa opera��o");
		}
		else if(exp1.getTipo()==Tipo.CHAR){
			if(exp2.getTipo()!=Tipo.INT) TestParser.erros.add("Express�o Aritm�tica s� suporta Float com Float, Int com Int e Char com Int");
			if(!operacao.equals("+") && !operacao.equals("-")) TestParser.erros.add("Char com Int n�o suporta essa opera��o");
		}
		else TestParser.erros.add("Express�o Aritm�tica s� suporta Float com Float, Int com Int e Char com Int");;		
	}
	
	@Override
	public Tipo getTipo() {
		return exp1.getTipo();
	}

	@Override
	public void gerarCodigoIntermediario(PrintWriter file) {
		exp1.gerarCodigoIntermediario(file);
		exp2.gerarCodigoIntermediario(file);

		switch (operacao) {
			case "+":
				file.println("\tiadd\n");
				break;
			case "-":
				file.println("\tisub\n");
				break;
			case "*":
				file.println("\timul\n");
				break;
			case "/":
				file.println("\tidiv\n");
				break;
			case "%":
				file.println("\tirem\n");
				break;
		} 
	}
}
