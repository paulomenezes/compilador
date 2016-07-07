package compiler.tree.expressao;

import java.io.PrintWriter;

import compiler.exceptions.SemanticsException;
import compiler.tabela.Declaracao;
import compiler.tabela.Tabela;
import compiler.tree.Programa;
import compiler.tree.Tipo;

public class ExprIdentificador implements Expressao {
	private String identificador;

	public ExprIdentificador(String identificador) {
		this.identificador = identificador;
	}

	@Override
	public void verificarSemantica() throws SemanticsException {
		Tabela ta = Tabela.getInstance();
		Tipo t = ta.getTipo(this.identificador);
		if(t == null) throw new SemanticsException("Variável não instanciada");
		
	}

	@Override
	public Tipo getTipo() {
		Tabela t = Tabela.getInstance();
		return t.getTipo(this.identificador);
	}

	@Override
	public void gerarCodigoIntermediario(PrintWriter file) {
		for (Declaracao declaracao : Programa.Variaveis) {
			if (declaracao.getNome().equals(identificador)) {
				if (declaracao.getEscopo() == 1)
					file.println("\tiload " + declaracao.getMemoria() + "  ; carrega " + identificador);
				else 
					file.println("\tgetstatic YeledClass/" + declaracao.getNome() + " I ; carrega global " + identificador);
				break;
			}
		}
	}
}
