package compiler.tree;

import compiler.exceptions.SemanticsException;
import compiler.tree.comando.Bloco;

public class DeclFuncao implements DeclGlobal {
	private Assinatura assinatura;
	private Bloco bloco;
	
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
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}

}
