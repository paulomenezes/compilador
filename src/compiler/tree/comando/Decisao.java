package compiler.tree.comando;

import java.io.PrintWriter;

import compiler.exceptions.SemanticsException;
import compiler.tree.Tipo;
import compiler.tree.expressao.ExprRelacional;
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
		
		if (comandoElse != null)
			comandoElse.verificarSemantica();
	}

	@Override
	public void gerarCodigoIntermediario(PrintWriter file) {
		String condicao = "if_icmpne";
		
		if (expressao instanceof ExprRelacional) {
			ExprRelacional expr = (ExprRelacional)expressao;
			
			switch (expr.getOperador()) {
				case "==":
					condicao = "if_icmpne";
					break;
				case "!=":
					condicao = "if_icmpeq";
					break;
				case "<":
					condicao = "if_icmpge";
					break;
				case "<=":
					condicao = "if_icmpgt";
					break;
				case ">":
					condicao = "if_icmple";
					break;
				case ">=":
					condicao = "if_icmplt";
					break;
			}
		}

		expressao.gerarCodigoIntermediario(file);
		
		if (comandoElse != null) {
			file.println("\t" + condicao + " parteElse\n");
			comandoIf.gerarCodigoIntermediario(file);
			file.println("\tgoto parteDepois");
			file.println();
			file.println("\tparteElse:");
			comandoElse.gerarCodigoIntermediario(file);
		} else {
			file.println("\t" + condicao + " parteDepois\n");
			comandoIf.gerarCodigoIntermediario(file);
		}
		
		file.println();
		file.println("\tparteDepois:");
	}

}
