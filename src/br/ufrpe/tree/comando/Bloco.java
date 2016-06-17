package br.ufrpe.tree.comando;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.tree.Tipo;


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
	public Boolean verificarSemantica() {
		return null;
	}

	@Override
	public String gerarCodigoIntermediario(String filename) {
		return null;
	}
}
