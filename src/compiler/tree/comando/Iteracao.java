package compiler.tree.comando;

import java.io.PrintWriter;

import compiler.exceptions.SemanticsException;
import compiler.tree.Tipo;
import compiler.tree.expressao.Expressao;
import tests.TestParser;

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
		if (expressao.getTipo() != Tipo.BOOLEAN) 
			TestParser.erros.add("Expressão de teste não é Boolean");

		comando.verificarSemantica();		
	}

	@Override
	public void gerarCodigoIntermediario(PrintWriter file) {
		file.println("\tgoto teste");
		file.println("\tlaco:");
		comando.gerarCodigoIntermediario(file);
		file.println("\tteste:");
		expressao.gerarCodigoIntermediario(file);
		file.println("\tif_icmplt laco\n\n");
	}

}
