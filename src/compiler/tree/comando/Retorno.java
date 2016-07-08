package compiler.tree.comando;

import java.io.PrintWriter;

import compiler.exceptions.SemanticsException;
import compiler.tabela.Tabela;
import compiler.tree.expressao.Expressao;
import tests.TestParser;

public class Retorno implements Comando {
	private Expressao expressao;

	public Retorno(Expressao expr) {
		this.expressao = expr;
	}

	@Override
	public void verificarSemantica() throws SemanticsException {
		expressao.verificarSemantica();
		Tabela t = Tabela.getInstance();
		if(t.getTipoUltima()!=expressao.getTipo()) TestParser.erros.add("Tipo do retorno diferente do tipo da fun��o");
	}

	@Override
	public void gerarCodigoIntermediario(PrintWriter file) {
		expressao.gerarCodigoIntermediario(file);
		file.println("\tireturn");
	}

}
