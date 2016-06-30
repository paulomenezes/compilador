package compiler.tree.comando;

import java.util.LinkedList;

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
	public Boolean verificarSemantica() {
		Tabela t = Tabela.getInstance();
		Object value = null;
		switch (tipo){
			case BOOLEAN :
				value = new Boolean(null);
				break;
			case INT :
				value = new Integer(null);
				break;
			case CHAR :
				value = new Character((char) 0);
				break;
			case STRING :
				value = new String();
				break;
			case FLOAT :
				value = new Float(null);
				break;
		}
		for(String nome : idents){
			if(!t.addVar(tipo, nome, value)) return false;
		}
		return true;
	}

	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}

}
