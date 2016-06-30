package compiler.tree.comando;

import compiler.exceptions.SemanticsException;
import compiler.tabela.Tabela;
import compiler.tree.Tipo;
import compiler.tree.expressao.Expressao;

public class Decisao implements Comando {
	private Expressao expressao;
	private Comando comandoIf;
	private Comando comandoElse;

	/**
	 * Construtor para if-else completo.
	 */
	public Decisao(Expressao expr, Comando cmdIf, Comando cmdElse) {
		this.expressao = expr;
		this.comandoIf = cmdIf;
		this.comandoElse = cmdElse;
	}

	/**
	 * Construtor para if sem else.
	 */
	public Decisao(Expressao expr, Comando cmdIf) {
		this.expressao = expr;
		this.comandoIf = cmdIf;
	}

	@Override
	public void verificarSemantica() throws SemanticsException {	
		expressao.verificarSemantica();
		if(expressao.getTipo()!=Tipo.BOOLEAN) throw new SemanticsException("Expressão de teste não é Boolean");
		
		comandoIf.verificarSemantica();
		comandoElse.verificarSemantica();
	}

	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}

}
