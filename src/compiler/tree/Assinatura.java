package compiler.tree;

import java.util.LinkedList;

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

	public Boolean verificarSemantica() {
		Tabela t = Tabela.getInstance();
		boolean aux = t.addFunc(tipo,identificador);
		if(aux){
			for(DeclVariavel dec : paramFormais){
				aux = dec.verificarSemantica();
				if(!aux) break;
			}
		}
		return aux;
	}

	public String gerarCodigoIntermediario(String filename) {
		return null;
	}
}
