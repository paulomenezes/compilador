package compiler.tree.comando;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import compiler.exceptions.SemanticsException;
import compiler.tabela.Tabela;


public class Bloco implements Comando {
	private List<Comando> comandos;

	public Bloco() {
		this.comandos = new ArrayList<Comando>();
	}

	public Bloco(List<Comando> comandos) {
		this.comandos = comandos;
	}

	public void add(Comando cmd) {
		this.comandos.add(cmd);
	}

	@Override
	public void verificarSemantica() throws SemanticsException {
		Tabela t = Tabela.getInstance();
		t.addEscopo();
		for(Comando c : comandos) c.verificarSemantica();
		t.removeEscopo();
	}

	@Override
	public void gerarCodigoIntermediario(PrintWriter file) {
		for (Comando comando : comandos) {
			DeclVariavel.GLOBAL = false;
			comando.gerarCodigoIntermediario(file);
		}
	}
}
