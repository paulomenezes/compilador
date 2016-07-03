package compiler.tree.comando;

import java.io.PrintWriter;

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
		if(expressao.getTipo()!=Tipo.BOOLEAN) throw new SemanticsException("Express�o de teste n�o � Boolean");
		
		comandoIf.verificarSemantica();
		comandoElse.verificarSemantica();
	}

	@Override
	public void gerarCodigoIntermediario(PrintWriter file) {
		expressao.gerarCodigoIntermediario(file);
		file.println("\tif_icmpeq parteElse\n");
		comandoIf.gerarCodigoIntermediario(file);
		file.println("\tgoto parteDepois");
		file.println();
		file.println("\tparteElse:");
		comandoElse.gerarCodigoIntermediario(file);
		file.println();
		file.println("\tparteDepois:");
	}

}
