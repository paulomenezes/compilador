package compiler.tree.comando;

import java.io.PrintWriter;

import compiler.exceptions.SemanticsException;
import compiler.tabela.Tabela;
import compiler.tree.Programa;
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
	public void gerarCodigoIntermediario(PrintWriter file) {
		expressao.gerarCodigoIntermediario(file);
		
		if (!Programa.Variaveis.containsKey(identificador)) {
			Programa.Variaveis.put(identificador, Programa.STACK_INDEX);	
			
			file.println("\tistore " + Programa.STACK_INDEX + "  ; salva " + identificador);
			
			Programa.STACK_INDEX++;
		} else {
			file.println("\tistore " + Programa.Variaveis.get(identificador));
		}
		
		file.println();
	}
}
