package compiler.tree.expressao;

import java.io.PrintWriter;
import java.util.LinkedList;

import compiler.exceptions.SemanticsException;
import compiler.tabela.Tabela;
import compiler.tree.Tipo;
import compiler.tree.comando.Comando;
import tests.TestParser;

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
		if(!ta.existsFunc(this.identificador)) TestParser.erros.add("Fun��o n�o foi criada");
		
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
		
		Tabela ta = Tabela.getInstance();
		if(ta.existsFunc(this.identificador)) {
			Tipo t = ta.getTipof(identificador);

			String params = "";
			for (int i = 0; i < listaExprs.size(); i++) {
				params += "I";
			}
			
			if (t == null) {
				file.println("\tinvokestatic YeledClass/" + identificador + "(" + params + ")V");
			} else {
				file.println("\tinvokestatic YeledClass/" + identificador + "(" + params + ")I");
			}
		}
	}
}
