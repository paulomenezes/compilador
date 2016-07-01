package tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import compiler.syntax.Lexer;
import compiler.syntax.Parser;
import compiler.tabela.Tabela;
import compiler.tree.Programa;
import compiler.tree.Tipo;
import java_cup.runtime.Symbol;


/**
 * Classe de teste para os parsers dos projetos. 
 *
 * Pablo Azevedo Sampaio
 * 05/10/2015
 */

public class TestParser {

	public static void main(String args[]) throws IOException {
		Lexer lexer;   //importem o lexer do projeto de vocês !!!
		Parser parser;
		
        /*BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String arquivo;
		
		if (args.length == 0) {
			System.out.print("Digite o nome do arquivo: ");
			arquivo = in.readLine();
		} else {
			arquivo = args[0];
		}	*/
		
		try {
			lexer = new Lexer(new FileInputStream("exemplo1"));
			parser = new Parser(lexer);

			Symbol output = parser.parse();
			
			Programa p = (Programa) output.value;
			p.verificarSemantica();
			
			System.out.println("Sucesso: " + p);
		
		} catch (Exception e) {
			System.err.println(e.getMessage());
			//e.printStackTrace();
		}

	}

}
