package compiler.tree.comando;

import java.io.PrintWriter;
import java.util.LinkedList;

import compiler.exceptions.SemanticsException;
import compiler.tabela.Declaracao;
import compiler.tabela.Tabela;
import compiler.tree.DeclGlobal;
import compiler.tree.Programa;
import compiler.tree.Tipo;
import tests.TestParser;

public class DeclVariavel implements Comando, DeclGlobal {
	public LinkedList<String> idents;
	private Tipo tipo;
	public static boolean GLOBAL = false;

	public DeclVariavel() {
		this.idents = new LinkedList<String>();
	}

	public DeclVariavel(LinkedList<String> ids, Tipo tipo) {
		this.idents = ids;
		this.tipo = tipo;
	}

	public DeclVariavel(String id, Tipo tipo) {
		this.idents = new LinkedList<String>();
		idents.addLast(id);
		this.tipo = tipo;
	}
	
	public LinkedList<String> getIdents() {
		return idents;
	}

	@Override
	public void verificarSemantica() throws SemanticsException {
		Tabela t = Tabela.getInstance();
		for(String nome : idents){
			if(!t.addVar(tipo, nome)) TestParser.erros.add("Nome da vari�vel j� est� sendo usado");
		}
	}

	@Override
	public void gerarCodigoIntermediario(PrintWriter file) {
		for (String nome : idents) {
			if (GLOBAL) {
				file.println(".field private static " + nome + " I");
				Programa.Variaveis.add(new Declaracao(Tipo.INT, nome, 0));
			} else {
				Programa.Variaveis.add(new Declaracao(Tipo.INT, nome, 1));
			}
		}
	}
}
