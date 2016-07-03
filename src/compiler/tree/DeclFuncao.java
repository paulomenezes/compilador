package compiler.tree;

import java.io.PrintWriter;

import compiler.exceptions.SemanticsException;
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
		assinatura.verificarSemantica();
		bloco.verificarSemantica();
	}

	@Override
	public void gerarCodigoIntermediario(PrintWriter file) {
		file.println(".method public static " + assinatura.getIdentificador() + "([Ljava/lang/String;)V");
		file.println("\t.limit locals " + LOCALS);
		file.println("\t.limit stack " + STACK);
		file.println();
		
		bloco.gerarCodigoIntermediario(file);

		file.println("\treturn");
		file.println(".end method\n");
	}

}
