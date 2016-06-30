package compiler.tree.comando;

import compiler.exceptions.SemanticsException;
import compiler.tabela.Tabela;
import compiler.tree.Tipo;
import compiler.tree.expressao.Expressao;

public class Iteracao implements Comando {

	private Expressao expressao;
	private Comando comando;

	public Iteracao(Expressao expressao, Comando comando) {
		this.expressao = expressao;
		this.comando = comando;
	}

	@Override
	public void verificarSemantica() throws SemanticsException {	
		expressao.verificarSemantica();
		if(expressao.getTipo()==Tipo.BOOLEAN) throw new SemanticsException("Expressão de teste não é Boolean");

		comando.verificarSemantica();		
	}

	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}

}
