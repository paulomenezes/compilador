package compiler.tree.expressao;

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
	public Boolean verificarSemantica() {
		if(exp1.getTipo()==Tipo.INT){
			if(exp2.getTipo()!=Tipo.INT) return false;
		}
		else if(exp1.getTipo()==Tipo.FLOAT){
			if(exp2.getTipo()!=Tipo.FLOAT || operacao.equals("%")) return false;
		}
		else if(exp1.getTipo()==Tipo.CHAR){
			if(exp2.getTipo()!=Tipo.INT || (!operacao.equals("+") && !operacao.equals("-"))) return false;
		}
		else return false;
		
		boolean aux = exp1.verificarSemantica();
		if(aux) exp2.verificarSemantica();
		return aux;
		
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
