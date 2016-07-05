package compiler.tree.comando;

import java.io.PrintWriter;
import java.util.LinkedList;

import compiler.exceptions.SemanticsException;
import compiler.tabela.Tabela;
import compiler.tree.DeclGlobal;
import compiler.tree.Tipo;

public class DeclVariavel implements Comando, DeclGlobal {
	private LinkedList<String> idents;
	private Tipo tipo;

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
			if(!t.addVar(tipo, nome)) throw new SemanticsException("Nome da variável já está sendo usado");
		}
	}

	@Override
	public void gerarCodigoIntermediario(PrintWriter file) {
		
	}
}
