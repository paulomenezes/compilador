package compiler.tree;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import compiler.exceptions.SemanticsException;

public class Programa {
	private LinkedList<DeclGlobal> declaracoes;
	
	public static Map<String, Integer> Variaveis = new HashMap<String, Integer>();
	public static int STACK_INDEX = 0;

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
		file.println(".method public <init>()V");
		file.println("\taload_0");
		file.println("\tinvokenonvirtual java/lang/Object/<init>()V");
		file.println("\treturn");
		file.println(".end method\n");
		
		for (DeclGlobal declGlobal : declaracoes) {
			declGlobal.gerarCodigoIntermediario(file);
		}
	}
}