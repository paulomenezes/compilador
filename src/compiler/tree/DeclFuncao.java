package compiler.tree;

import compiler.tree.comando.Bloco;

public class DeclFuncao implements DeclGlobal {
	private Assinatura assinatura;
	private Bloco bloco;
	
	public DeclFuncao(Assinatura assinatura, Bloco bloco) {
		this.assinatura = assinatura;
		this.bloco = bloco;
	}

	@Override
	public Boolean verificarSemantica() {
		boolean aux = assinatura.verificarSemantica();
		if(aux) aux = bloco.verificarSemantica();
		return aux;	
	}

	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}

}
