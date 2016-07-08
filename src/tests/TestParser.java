package tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import compiler.syntax.Lexer;
import compiler.syntax.Parser;
import compiler.tree.Programa;
import java_cup.runtime.Symbol;


/**
 * Classe de teste para os parsers dos projetos. 
 *
 * Pablo Azevedo Sampaio
 * 05/10/2015
 */

public class TestParser {

	public static void main(String args[]) throws IOException {
		Lexer lexer; 
		Parser parser;
				
		if (args.length > 0) {
			try {
				lexer = new Lexer(new FileInputStream(args[0]));
				parser = new Parser(lexer);
	
				Symbol output = parser.parse();
				
				Programa p = (Programa) output.value;
				p.verificarSemantica();
				
				String filename = args[0];
				if (filename.contains(".yld")) {
					filename = filename.replace(".yld", ".asm");
				} else {
					filename += ".asm";
				}
				
				PrintWriter writer = new PrintWriter(filename, "UTF-8");
				p.gerarCodigoIntermediario(writer);
				writer.close();
				
				System.out.println("Sucesso: " + p);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.out.println("Digite o nome de um arquivo Yeled.");
		}
	}

}
