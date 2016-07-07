package compiler.tree.expressao;

import java.io.PrintWriter;
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
		
		if(!ta.existsFunc(this.identificador)) throw new SemanticsException("Função não foi criada");
		
		for(Expressao e : listaExprs) e.verificarSemantica();
	}

	@Override
	public Tipo getTipo() {
		Tabela ta = Tabela.getInstance();
		return ta.getTipof(this.identificador);
	}
	
	@Override
	public void gerarCodigoIntermediario(PrintWriter file) {
		for (Expressao expressao : listaExprs) {
			expressao.gerarCodigoIntermediario(file);
		}
		
		file.println("\tinvokestatic YeledClass/" + identificador + "()V");
	}
}
