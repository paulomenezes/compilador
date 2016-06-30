package compiler.tree;

import java.util.LinkedList;

import compiler.exceptions.SemanticsException;
import compiler.tabela.Tabela;
import compiler.tree.comando.DeclVariavel;

public class Assinatura {
	private LinkedList<DeclVariavel> paramFormais;
	private Tipo tipo;
	private String identificador;

	public Assinatura(String identificar,
			LinkedList<DeclVariavel> paramFormais, Tipo tipo) {
		this.paramFormais = paramFormais;
		this.tipo = tipo;
		this.identificador = identificar;
	}

	public Assinatura(String identificar, LinkedList<DeclVariavel> paramFormais) {
		this.paramFormais = paramFormais;
		this.identificador = identificar;
	}

	public void verificarSemantica() throws SemanticsException {
		Tabela t = Tabela.getInstance();
		if(!t.addFunc(tipo,identificador)) throw new SemanticsException("Nome da Função já está sendo usada");
		for(DeclVariavel dec : paramFormais) dec.verificarSemantica();
	}

	public String gerarCodigoIntermediario(String filename) {
		return null;
	}
}
