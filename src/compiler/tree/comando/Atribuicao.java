package compiler.tree.comando;

import java.io.PrintWriter;

import compiler.exceptions.SemanticsException;
import compiler.tabela.Declaracao;
import compiler.tabela.Tabela;
import compiler.tree.Programa;
import compiler.tree.Tipo;
import compiler.tree.expressao.Expressao;
import tests.TestParser;

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
		if(tipo==null) TestParser.erros.add("Vari�vel n�o foi instanciada");
		else if(tipo!=expressao.getTipo()) TestParser.erros.add("Tipo da express�o n�o corresponde a vari�vel");
	}

	@Override
	public void gerarCodigoIntermediario(PrintWriter file) {
		expressao.gerarCodigoIntermediario(file);
		
		for (Declaracao declaracao : Programa.Variaveis) {
			if (declaracao.getNome().equals(identificador)) {
				if (declaracao.getEscopo() > 0) 
					file.println("\tistore " + declaracao.getMemoria() + "  ; salva " + identificador);
				else
					file.println("\tputstatic YeledClass/" + identificador + " I ; salva global " + identificador);
			}
		}
		
		file.println();
	}
}
