package compiler.tree.expressao;

import java.util.LinkedList;

import compiler.exceptions.SemanticsException;
import compiler.tabela.Tabela;
import compiler.tree.Tipo;
import compiler.tree.comando.Comando;

public class ChamadaFunc implements Expressao, Comando {
	private String identificador;
	private LinkedList<Expressao> listaExprs;

	public ChamadaFunc(String identificador, LinkedList<Expressao> listaExprs) {
		this.identificador = identificador;
		this.listaExprs = listaExprs;
	}
	
	@Override
	public void verificarSemantica() throws SemanticsException {
		Tabela ta = Tabela.getInstance();
		Tipo t = ta.getTipof(this.identificador);
		if(t == null) throw new SemanticsException("Fun��o n�o foi criada");
		
		for(Expressao e : listaExprs) e.verificarSemantica();
	}

	@Override
	public Tipo getTipo() {
		Tabela ta = Tabela.getInstance();
		return ta.getTipof(this.identificador);
	}
	
	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}
}
