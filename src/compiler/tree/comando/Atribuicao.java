package compiler.tree.comando;

import compiler.exceptions.SemanticsException;
import compiler.tabela.Tabela;
import compiler.tree.Tipo;
import compiler.tree.expressao.Expressao;

public class Atribuicao implements Comando {
	private String identificador;
	private Expressao expressao;

	public Atribuicao(String ident, Expressao expressao) {
		this.identificador = ident;
		this.expressao = expressao;
	}

	@Override
	public void verificarSemantica() throws SemanticsException {
		expressao.verificarSemantica();
		Tabela ta = Tabela.getInstance();
		Tipo tipo = ta.getTipo(identificador);
		if(tipo==null) throw new SemanticsException("Vari�vel n�o foi instanciada");
		else if(tipo!=expressao.getTipo()) throw new SemanticsException("Tipo da express�o n�o corresponde a vari�vel");
	}

	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}

}
