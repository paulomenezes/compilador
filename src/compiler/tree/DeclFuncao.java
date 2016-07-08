package compiler.tree;

import java.io.PrintWriter;
import java.util.Iterator;

import compiler.exceptions.SemanticsException;
import compiler.tabela.Tabela;
import compiler.tree.comando.Bloco;

public class DeclFuncao implements DeclGlobal {
	private Assinatura assinatura;
	private Bloco bloco;
	
	private static final int LOCALS = 10;
	private static final int STACK = 10;
	
	public DeclFuncao(Assinatura assinatura, Bloco bloco) {
		this.assinatura = assinatura;
		this.bloco = bloco;
	}

	@Override
	public void verificarSemantica() throws SemanticsException {
		Tabela ta = Tabela.getInstance();
		ta.createFunc();
		assinatura.verificarSemantica();
		bloco.verificarSemantica();
		ta.removeFunc();
	}

	@Override
	public void gerarCodigoIntermediario(PrintWriter file) {
		assinatura.gerarCodigoIntermediario(file);
		
		file.println("\t.limit locals " + LOCALS);
		file.println("\t.limit stack " + STACK);
		file.println();
		
		bloco.gerarCodigoIntermediario(file);

		if (assinatura.getTipo() == null)
			file.println("\treturn");
		
		file.println(".end method\n");
				
		for (int i = Programa.Variaveis.size() - 1; i >= 0; i--) {
			if (Programa.Variaveis.get(i).getEscopo() > 0) {
				Programa.Variaveis.remove(i);
				Programa.STACK_INDEX--;
			}
		}
	}

}
