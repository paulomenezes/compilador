package compiler.tree.expressao;

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
	public Boolean verificarSemantica() {
		if(expr1.getTipo()==Tipo.FLOAT || expr1.getTipo()==Tipo.INT){
			if(expr1.getTipo()!=expr2.getTipo())  return false;
		}
		else{
			if((!operador.equals("==") && !operador.equals("!=")) || expr1.getTipo()!=expr2.getTipo()){
				return false;
			}
		}
		
		boolean aux = expr1.verificarSemantica();
		if(aux) expr2.verificarSemantica();
		return aux;
	}

	@Override
	public Tipo getTipo() {
		return Tipo.BOOLEAN;
	}
	
	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}

}
