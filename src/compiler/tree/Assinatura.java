package compiler.tree;

import java.io.PrintWriter;
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

	public void gerarCodigoIntermediario(PrintWriter file) {
		String t = "I";
		if (tipo == null)
			t = "V";
		
		if (identificador.equals("main"))
			file.println(".method public static " + identificador + "([Ljava/lang/String;)" + t);
		else {
			String params = "";
			for (int i = 0; i < paramFormais.size(); i++) {
				params += "I";
			}
			
			file.println(".method public static " + identificador + "(" + params + ")" + t);
		}
		
		for (int i = 0; i < paramFormais.size(); i++) {
			file.println("\tiload " + i);
		}
	}
	
	public String getIdentificador() {
		return identificador;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
}
