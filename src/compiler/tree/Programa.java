package compiler.tree;

import java.io.PrintWriter;
import java.util.LinkedList;

import compiler.exceptions.SemanticsException;
import compiler.tabela.Declaracao;
import compiler.tree.comando.DeclVariavel;

public class Programa {
	private LinkedList<DeclGlobal> declaracoes;
	
	public static LinkedList<Declaracao> Variaveis = new LinkedList<>();
	public static int STACK_INDEX = 1;

	public Programa() throws SemanticsException {
		declaracoes = new LinkedList<DeclGlobal>();
	}

	public void addLast(DeclGlobal dec) {
		this.declaracoes.addLast(dec);
	}
	
	public void verificarSemantica() throws SemanticsException {
		for(DeclGlobal decl : declaracoes) decl.verificarSemantica();
	}
	
	public void gerarCodigoIntermediario(PrintWriter file) {
		file.println(".class public YeledClass");
		file.println(".super java/lang/Object\n");

		DeclVariavel.GLOBAL = true;
		for (DeclGlobal declGlobal : declaracoes) {
			if (declGlobal instanceof DeclVariavel)
				declGlobal.gerarCodigoIntermediario(file);
		}
		
		file.println("\n.method public <init>()V");
		file.println("\taload_0");
		file.println("\tinvokenonvirtual java/lang/Object/<init>()V");
		file.println("\treturn");
		file.println(".end method\n");
		
		DeclVariavel.GLOBAL = false;
		for (DeclGlobal declGlobal : declaracoes) {
			if (declGlobal instanceof DeclFuncao)
				declGlobal.gerarCodigoIntermediario(file);
		}
	}
}